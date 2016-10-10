package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logica.Pedido;
import logica.Producto;

public class ConsultasMyShop {
	
	private static Database instance = Database.getInstance();
	
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
				// idProducto, idPedido, cantidad, idProducto, producto_nombre, descripcion_producto, stock, precio
				productos.add(new Producto(rsProductos.getString(1),rsProductos.getString(5), rsProductos.getString(6), rsProductos.getDouble(7),rsProductos.getDouble(8),"A3"));
				cantidad=cantidad+rsProductos.getInt(3);
			}
			pedidos.add(new Pedido(rs.getString(1),rs.getDate(4),cantidad,rs.getDouble(3),productos));
		}
		
		return pedidos;
		
	}
	
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
	
	public static void crearPedido(List<Producto> productos, String clienteID){
		//String insertar = "INSERT INTO Pedido VALUES ()"
	}
	
}
