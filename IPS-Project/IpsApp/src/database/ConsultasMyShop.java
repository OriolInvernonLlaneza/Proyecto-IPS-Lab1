package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import logica.OrdenDeTrabajo;
import logica.Pedido;
import logica.Producto;
import logica.GrupoProducto;

public class ConsultasMyShop {
	
	private static Database instance = Database.getInstance();
	private static String ultimaIDPedido;
	
	
	/**
	 * Obtiene los pedidos existentes en la base de datos
	 * @return Una lista con los pedidos existentes
	 * @throws SQLException
	 */
	public static List<Pedido> getPedidos() throws SQLException{
		List<Pedido> pedidos= new ArrayList<Pedido>();
		ResultSet rs= instance.executeQuery("SELECT pedido.idpedido,pedido.idusuario,fecha,sum(cantidad),precio_pedido,direccion"+
												" FROM pedido, productoPedido " +
												" WHERE pedido.idpedido= productoPedido.idpedido "
												+" GROUP BY pedido.idpedido,pedido.idusuario,fecha,precio_pedido,direccion "+
												" ORDER BY fecha ");
		while(rs.next()){
			List<GrupoProducto> productos= new ArrayList<GrupoProducto>();
			//idPedido, idUsuario, preciopedido, direccion, fecha
			
			String consulta=" SELECT DISTINCT producto.idproducto,producto.producto_nombre, producto.descripcion_producto,producto.stock, producto.precio "+
						" FROM ProductoPedido, Producto "+ 
						 " WHERE ProductoPedido.idproducto = Producto.idproducto "+ 
						 " AND ProductoPedido.idpedido =  ?";
			ResultSet rsProductos = Database.getInstance().executePreparedQuery(consulta, rs.getString(1));
			while(rsProductos.next()){
				// idProducto, idPedido, cantidad -- idProducto, producto_nombre, descripcion_producto, stock, precio
				Producto producto = new Producto(rsProductos.getString(1),rsProductos.getString(2), rsProductos.getString(3), rsProductos.getDouble(4),rsProductos.getDouble(5),"A3");
				GrupoProducto grupo = new GrupoProducto(producto, rsProductos.getInt(3));
				productos.add(grupo);
			}
			//idPedido, idUsuario, preciopedido, direccion, fecha
			pedidos.add(new Pedido(rs.getString(1), rs.getString(2), rs.getDate(3),rs.getInt(4), rs.getDouble(5), rs.getString(6), productos));
		}
		
		return pedidos;
		
	}
	
	/**
	 * Obtiene una lista con los productos que tenemos
	 * @return Lista de productos
	 * @throws SQLException
	 */
	public static List<Producto> getProductos() throws SQLException{
		List<Producto> productos = new ArrayList<>();
		String consulta = " SELECT * FROM Producto";
		
		ResultSet set = instance.executeQuery(consulta);
		
		while (set.next()){
			//idProducto, producto_nombre, descripcion_producto, stock, precio
			productos.add(new Producto(set.getString(1), set.getString(2), set.getString(3), set.getDouble(4), set.getDouble(5), "A3"));
		}
		return productos;
		
	}
	
	public static void crearPedido(Pedido pedido) throws SQLException{
		double precioTotal = pedido.getPrecio();
		Date fecha = Calendar.getInstance().getTime();
		
		//pedido, usuario, precio, direccion, fecha
		String insertar = String.format("INSERT INTO Pedido VALUES ( %s, %s, %f, %s, " + fecha + ") ", pedido.getId(), pedido.getIdUsuario(), precioTotal, "direccionBase");
		instance.executeQuery(insertar);
		
		//idProducto, idPedido, cantidad

		HashMap<String, GrupoProducto> agrupacion = pedido.getAgrupacion();
		Statement statement = instance.returnStatement();
		
		for(GrupoProducto producto : pedido.getAgrupacion().values()){
				String sql = "INSERT INTO ProductosPedido VALUES (" + producto.getID() + ", " + pedido.getId() + ", " + producto.getCantidad() + ")"; 
				statement.addBatch(sql);
		}
		
		statement.executeBatch();
		
		
	}
	
	private static String ultimaID() throws SQLException{
		String consulta = " SELECT idPedido FROM "
				+ " (SELECT idPedido "
				+ " FROM Pedido "
				+ " ORDER BY idPedido DESC) "
						+ "WHERE rownum = 1 ";
		//Podr�amos estar frente a nuestro primer pedido, as� que el resultset devolver�a un SELECT vac�o
		//Para evitar eso, pregunto si el cursor del SELECT est� detras de la primera fila (como una forma de -1 o 0)
		ResultSet id = instance.executeQuery(consulta) ;
		return id.isBeforeFirst() ? "0" : id.getString(1);
	}
	
	public static String getSiguienteIDPedido() throws SQLException{
		String id;
		if(ultimaIDPedido == null)
			id = ultimaID();
		else
			id = ultimaIDPedido;
		
		int intId = Integer.parseInt(id);
		return String.valueOf(intId + 1);
		
	}
	
	public static void cambiarEstadoOrdenDeTrabajo(String nuevoEstado, String idPedido) throws SQLException {
		String consulta = "UPDATE OrdenTrabajo set estado = ? where idAlmacenero = ? and idPedido = ?";
		instance.executePreparedQuery(consulta, nuevoEstado, idPedido);
		
	}
	
	
}
