package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logica.Pedido;
import logica.Producto;

public class ConsultasMyShop {
	
	public static List<Pedido> getPedidos() throws SQLException{
		List<Pedido> pedidos= new ArrayList<Pedido>();
		List<Producto> productos= new ArrayList<Producto>();
		int cantidad = 0;
		ResultSet rs=Database.getInstance().executeQuery("SELECT * "+
																" FROM pedido "+
																" ORDER BY Fecha ");
		while(rs.next()){
			String consulta="SELECT * "+
						" FROM productoPedido, Producto "+
						" WHERE productoPedido.idproducto=producto.idproducto "+
						" AND productoPedido.idpedido=? ";
			ResultSet rsProductos=Database.getInstance().executePreparedQuery(consulta, rs.getString(1));
			while(rsProductos.next()){
				productos.add(new Producto(rsProductos.getString(1),rsProductos.getString(5),rsProductos.getDouble(8),rsProductos.getString(6),"A3"));
				cantidad=cantidad+rsProductos.getInt(3);
			}
			pedidos.add(new Pedido(rs.getString(1),rs.getDate(4),cantidad,rs.getDouble(3),productos));
		}
		
		return pedidos;
		
	}
	
}
