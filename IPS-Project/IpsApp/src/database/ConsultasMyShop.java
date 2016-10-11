package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import logica.Pedido;
import logica.Producto;

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
		List<Producto> productos= new ArrayList<Producto>();
		int cantidad = 0;
		ResultSet rs= instance.executeQuery("SELECT * "+
														" FROM pedido "+
														" ORDER BY Fecha ");
		while(rs.next()){
			String consulta="SELECT * "+
						" FROM productoPedido, Producto "+
						" WHERE productoPedido.idproducto=producto.idproducto "+
						" AND productoPedido.idpedido=? ";
			ResultSet rsProductos=Database.getInstance().executePreparedQuery(consulta, rs.getString(1));
			while(rsProductos.next()){
				// idProducto, idPedido, cantidad -- idProducto, producto_nombre, descripcion_producto, stock, precio
				productos.add(new Producto(rsProductos.getString(1),rsProductos.getString(5), rsProductos.getString(6), rsProductos.getDouble(7),rsProductos.getDouble(8),"A3"));
				cantidad=cantidad+rsProductos.getInt(3);
			}
			//idPedido, idUsuario, preciopedido, direccion, fecha
			pedidos.add(new Pedido(rs.getString(1),rs.getDate(5),cantidad, rs.getDouble(3), rs.getString(4), productos));
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
	
	public static void crearPedido(Pedido pedido, String clienteID) throws SQLException{
		double precioTotal = 0;
		for(Producto producto : pedido.getProductos())
			precioTotal += producto.getPrecio();
		Date fecha = Calendar.getInstance().getTime();
		
		//pedido, usuario, precio, direccion, fecha
		String insertar = String.format("INSERT INTO Pedido VALUES ( %d, %s, %f, %s, " + fecha + ") ", pedido.getId(), clienteID, precioTotal, "direccionBase");
		instance.executeQuery(insertar);
		
		//idProducto, idPedido, cantidad
		
		StringBuilder builder = new StringBuilder();
		for(Producto producto : pedido.getProductos()){
				//builder.append(String.format(" INSERT INTO ProductosPedido VALUES (%s, %s, %d) ", producto.getId(), pedido.getId(), ) + "\n");
		}
		
		
	}
	
	public static String ultimaID() throws SQLException{
		String consulta = " SELECT idPedido FROM "
				+ " (SELECT idPedido "
				+ " FROM Pedido "
				+ " ORDER BY idPedido DESC) "
				+ " WHERE rownum = 1 ";
		//Podríamos estar frente a nuestro primer pedido, así que el resultset devolvería un SELECT vacío
		//Para evitar eso, pregunto si el cursor del SELECT está detras de la primera fila (como una forma de -1 o 0)
		ResultSet id = instance.executeQuery(consulta) ;
		return id.isBeforeFirst() ? id.getString(1) : "00"; 
	}
	
}
