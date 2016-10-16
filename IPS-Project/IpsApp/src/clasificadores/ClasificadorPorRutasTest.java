package clasificadores;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import logica.Almacen;
import logica.Producto;

public class ClasificadorPorRutasTest {
	
	private Almacen<Producto> almacen;
	private Set<Producto> productos;
	private Producto prod1, prod2, prod3, prod4, prod5;
	
	@Before
	public void setup(){
		this.almacen = new Almacen<Producto>(3, 10);
		this.productos = new HashSet<>();
		prod1 = new Producto("25", "Girasol","asa",5.90, 200, "105", "cod1");
		prod2 = new Producto("25", "Mandarina","asa", 0.10, 200, "314", "cod2");
		prod3 = new Producto("18", "Teclado","asa", 49.99, 200, "209", "cod3");
		
		prod4 = new Producto("233", "Teclado3","asa1", 49.99, 200, "110", "cod5");
		prod5 = new Producto("233", "Teclado2","asa1", 49.99, 200, "210", "cod4");
		
		productos.add(prod1);
		productos.add(prod2);
		productos.add(prod3);
		productos.add(prod5);
		productos.add(prod4);
	}
	
	

	@Test
	public void test() {
		ClasificadorPorRutas<Producto> clas = new ClasificadorPorRutas<>(almacen, productos.size());
		List<Producto> resultado = clas.ordenar(productos);
		
		assertEquals(prod4, resultado.get(0));
		assertEquals(prod1, resultado.get(1));
		assertEquals(prod3, resultado.get(2));
		assertEquals(prod5, resultado.get(3));
		assertEquals(prod2, resultado.get(4));
		
	}

}
