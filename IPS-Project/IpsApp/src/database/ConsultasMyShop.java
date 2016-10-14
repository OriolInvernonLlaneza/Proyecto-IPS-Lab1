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
import logica.UnidadProducto;

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
			List<Producto> productos= new ArrayList<Producto>();
			//idPedido, idUsuario, preciopedido, direccion, fecha
			
			String consulta=" SELECT DISTINCT producto.idproducto,producto.producto_nombre, producto.descripcion_producto,producto.stock, producto.precio "+
						" FROM ProductoPedido, Producto "+ 
						 " WHERE ProductoPedido.idproducto = Producto.idproducto "+ 
						 " AND ProductoPedido.idpedido =  ?";
			ResultSet rsProductos = Database.getInstance().executePreparedQuery(consulta, rs.getString(1));
			while(rsProductos.next()){
				// idProducto, idPedido, cantidad -- idProducto, producto_nombre, descripcion_producto, stock, precio
				productos.add(new Producto(rsProductos.getString(1),rsProductos.getString(2), rsProductos.getString(3), rsProductos.getDouble(4),rsProductos.getDouble(5),"A3"));
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
		/*
		 * JORGE SOLUCIONA ESTO
		 * JORGE SOLUCIONA ESTO
		 * JORGE SOLUCIONA ESTO
		 * JORGE SOLUCIONA ESTO
		 * JORGE SOLUCIONA ESTO
		 * JORGE SOLUCIONA ESTO
		 *
		 * 
		 * */
		double precioTotal = 0;
		for(Producto producto : pedido.getProductos())
			precioTotal += producto.getPrecio();
		Date fecha = Calendar.getInstance().getTime();
		
		//pedido, usuario, precio, direccion, fecha
		String insertar = String.format("INSERT INTO Pedido VALUES ( %s, %s, %f, %s, " + fecha + ") ", pedido.getId(), pedido.getIdUsuario(), precioTotal, "direccionBase");
		instance.executeQuery(insertar);
		
		//idProducto, idPedido, cantidad

		HashMap<String, UnidadProducto> agrupacion = pedido.getAgrupacion();
		Statement statement = instance.returnStatement();
		
		for(Producto producto : pedido.getProductos()){
				String sql = "INSERT INTO ProductosPedido VALUES (" + producto.getId() + ", " + pedido.getId() + ", " + agrupacion.get(producto.getId()).getCantidad() + ")"; 
				statement.addBatch(sql);
				//builder.append(" INSERT INTO ProductosPedido VALUES (" + producto.getId() + ", " + pedido.getId() + ", " + agrupacion.get(producto.getId()).getCantidad() + "); \n");
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
	
	public static void crearUnaOrdenDeTrabajo(String idAlmacenero, String idPedido) throws SQLException{
		String consulta = "INSERT INTO OrdenTrabajo VALUES (?,? , Procesando)";
		instance.executePreparedQuery(idAlmacenero, idPedido);
		
	}
	
	public static void cambiarEstadoOrdenDeTrabajo(String nuevoEstado, String idPedido) throws SQLException {
		String consulta = "UPDATE OrdenTrabajo set estado = ? where idAlmacenero = ? and idPedido = ?";
		instance.executePreparedQuery(consulta, nuevoEstado, idPedido);
		
	}
	
	
}
