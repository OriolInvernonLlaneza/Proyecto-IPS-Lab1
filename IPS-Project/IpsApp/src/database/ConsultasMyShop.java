package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logica.GrupoProducto;
import logica.Paquete;
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
		ResultSet rs= instance.executeQuery(" SELECT pedido.idpedido, pedido.idusuario, fecha, sum(cantidad), precio_pedido, direccion "+
												" FROM pedido, productoPedido " +
												" WHERE pedido.idpedido = productoPedido.idpedido " +
												" AND pedido.idpedido NOT IN(SELECT OrdenTrabajo.idPedido FROM  OrdenTrabajo WHERE estado <>'Asignada') "+
												" GROUP BY pedido.idpedido, pedido.idusuario, fecha, precio_pedido,direccion " +
												" ORDER BY fecha ");
		while(rs.next()){
			List<GrupoProducto> productos= new ArrayList<GrupoProducto>();
			//idPedido, idUsuario, preciopedido, direccion, fecha
												//String id, String nombre, String descripcion, double precio, double stock, String localizacion, String codigoBarras
			String consulta=" SELECT DISTINCT producto.idproducto, producto.producto_nombre, producto.descripcion_producto, producto.precio, producto.stock, producto.localizacion,producto.codigo_barras, productopedido.cantidad "+
						" FROM ProductoPedido, Producto "+ 
						 " WHERE ProductoPedido.idproducto = Producto.idproducto "+ 
						 " AND ProductoPedido.idpedido =  ? ";
			ResultSet rsProductos = instance.executePreparedQuery(consulta, rs.getString(1));
			while(rsProductos.next()){
				// idProducto, producto_nombre, descripcion_producto, codigo_barras, stock, precio -- cantidad

				Producto producto = new Producto(rsProductos.getString(1),rsProductos.getString(2), rsProductos.getString(3), rsProductos.getDouble(4),rsProductos.getDouble(5), rsProductos.getString(6), rsProductos.getString(7));

				GrupoProducto grupo = new GrupoProducto(producto, rsProductos.getInt(8));
				productos.add(grupo);
			}
			//pedido.idpedido, pedido.idusuario, fecha, sum(cantidad), precio_pedido, direccion
			pedidos.add(new Pedido(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getInt(4), rs.getDouble(5), rs.getString(6), productos));
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
			//idProducto, producto_nombre, descripcion_producto, codigo_barras, stock, precio, 
			productos.add(new Producto(set.getString(1), set.getString(2), set.getString(3), set.getDouble(5), set.getDouble(6), "A3", set.getString(4)));
		}
		return productos;
		
	}

	public static void crearUsuario(String id, String nombre, String apellido) throws SQLException{
		String insertar = " INSERT INTO Usuario VALUES (?, ?, ?, ?)";
		//id, contrase�a, nombre, apellido
		instance.executeUpdate(insertar, id, new GeneradorContrasena(nombre).generarID(), nombre, apellido);
	}
	
	/**
	 * Dado un contenedor de los datos para un Pedido. Procesarlo e introducirlo en la base de datos. A�adiendo las cantidades asociadas a cada producto
	 * @param pedido El pedido en cuestion
	 * @throws SQLException Podria darse que el usuario especificado por este pedido no se encuentre en la base de datos
	 */
	public static void crearPedido(Pedido pedido) throws SQLException{
		double precioTotal = pedido.getPrecio();
		
		//pedido, usuario, precio, direccion, fecha
		String insertar = " INSERT INTO Pedido VALUES ( ? , ? , ? , ? , ? ) ";
		instance.executeUpdate(insertar, pedido.getId(), pedido.getIdUsuario(), precioTotal, "direccionBase", new java.sql.Timestamp(System.currentTimeMillis()));
		
		//idProducto, idPedido, cantidad

		//Statement statement = instance.returnStatement();
		String sql = "INSERT INTO ProductoPedido VALUES (?, ?, ?)";
		PreparedStatement statement = instance.returnPreparedStatement(sql);
		
		instance.cambiarAutoCommit(false);
		for(GrupoProducto producto : pedido.getAgrupacion().values()){
				statement.setString(1, producto.getID());
				statement.setString(2, pedido.getId());
				statement.setInt(3, producto.getCantidad());
				statement.addBatch();
		}
		
		statement.executeBatch();
		instance.commit();
		instance.cambiarAutoCommit(true);
		
	}
	
	private static String ultimaID() throws SQLException{
		String consulta = " SELECT idPedido FROM "
				+ " (SELECT idPedido "
				+ " FROM Pedido "
				+ " ORDER BY idPedido DESC) "
						+ "WHERE rownum = 1 ";
		//Podriamos estar frente a nuestro primer pedido, asi que el resultset devolveria un SELECT vac�o
		//Para evitar eso, pregunto si el cursor del SELECT esta detras de la primera fila (como una forma de -1 o 0)
		ResultSet id = instance.executeQuery(consulta) ;
		if(id.next())
			return id.getString(1);
		else
			return "0";
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
	
	/**Insertar una orden de trabajo en la base de datos
	 * @param idAlmacenero
	 * @param idPedido
	 * @throws SQLException
	 */
	public static void crearUnaOrdenDeTrabajo(String idAlmacenero, String idPedido) throws SQLException{
		String insertar = " INSERT INTO OrdenTrabajo VALUES ( ? , ? , ?) ";
		instance.executeUpdate(insertar, idAlmacenero, idPedido, "Asignada");	
	}
	
	/**Cambiar el estado de una orden de trabajo
	 * @param nuevoEstado
	 * @param idPedido
	 * @throws SQLException
	 */
	public static void cambiarEstadoOrdenDeTrabajo(String nuevoEstado, String idPedido) throws SQLException {
		String consulta = "UPDATE OrdenTrabajo set estado = ? where idPedido = ?";
		instance.executeUpdate(consulta, nuevoEstado, idPedido);
		
	}
	
	/**Insertar un paquete a la base de datos
	 * @param paquete
	 * @throws SQLException
	 */
	public static void addPaquete(Paquete paquete) throws SQLException {
		String consulta = "INSERT into Paquete values(?,?,?)";
		instance.executeUpdate(consulta, paquete.getId(), paquete.getIdPedido(), paquete.getFechaEnvoltura());
	}
	
	public static void crearIncidencia(String idPedido, String idAlmacenero, String descripcion) throws SQLException{
		String consulta= " INSERT INTO incidencia VALUES(?, ?, ?) ";
		instance.executeUpdate(consulta, idPedido,idAlmacenero,descripcion);
	}
	
	
}
