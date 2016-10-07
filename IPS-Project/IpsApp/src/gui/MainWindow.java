package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.border.BevelBorder;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private ResourceManager manager;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelProductos;
	private JPanel panelCarrito;
	private JPanel panelListaCarrito;
	private JList listaCarrito;
	private JPanel panelCarritoBotones;
	private JButton btnAñadir;
	private JButton btnEliminar;
	private JPanel panelLista;
	private JPanel panelTituloCarrito;
	private JLabel lblCarritoEnCurso;
	private JPanel panelTablaProductos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void localizar(){
		lblCarritoEnCurso.setText(manager.getString("label_carrito"));
	}
	
	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setTitle("MyShop");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 519, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanelProductos(), BorderLayout.CENTER);
		contentPane.add(getPanelCarrito(), BorderLayout.EAST);
		
		manager = ResourceManager.getResourceManager();
		localizar();
	}
	private JPanel getPanelProductos() {
		if (panelProductos == null) {
			panelProductos = new JPanel();
			panelProductos.setLayout(new BorderLayout(0, 0));
			panelProductos.add(getPanelTablaProductos(), BorderLayout.CENTER);
		}
		return panelProductos;
	}
	private JPanel getPanelCarrito() {
		if (panelCarrito == null) {
			panelCarrito = new JPanel();
			panelCarrito.setLayout(new BoxLayout(panelCarrito, BoxLayout.X_AXIS));
			panelCarrito.add(getPanelListaCarrito());
		}
		return panelCarrito;
	}
	private JPanel getPanelListaCarrito() {
		if (panelListaCarrito == null) {
			panelListaCarrito = new JPanel();
			panelListaCarrito.setLayout(new BorderLayout(0, 0));
			panelListaCarrito.add(getPanelLista(), BorderLayout.CENTER);
			panelListaCarrito.add(getPanelCarritoBotones(), BorderLayout.SOUTH);
			panelListaCarrito.add(getPanelTituloCarrito(), BorderLayout.NORTH);
		}
		return panelListaCarrito;
	}
	private JList getListaCarrito() {
		if (listaCarrito == null) {
			listaCarrito = new JList();
		}
		return listaCarrito;
	}
	private JPanel getPanelCarritoBotones() {
		if (panelCarritoBotones == null) {
			panelCarritoBotones = new JPanel();
			panelCarritoBotones.add(getBtnAñadir());
			panelCarritoBotones.add(getBtnEliminar());
		}
		return panelCarritoBotones;
	}
	private JButton getBtnAñadir() {
		if (btnAñadir == null) {
			btnAñadir = new JButton("+");
		}
		return btnAñadir;
	}
	private JButton getBtnEliminar() {
		if (btnEliminar == null) {
			btnEliminar = new JButton("-");
		}
		return btnEliminar;
	}
	private JPanel getPanelLista() {
		if (panelLista == null) {
			panelLista = new JPanel();
			panelLista.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			panelLista.setBackground(Color.WHITE);
			panelLista.add(getListaCarrito());
		}
		return panelLista;
	}
	private JPanel getPanelTituloCarrito() {
		if (panelTituloCarrito == null) {
			panelTituloCarrito = new JPanel();
			panelTituloCarrito.add(getLblCarritoEnCurso());
		}
		return panelTituloCarrito;
	}
	private JLabel getLblCarritoEnCurso() {
		if (lblCarritoEnCurso == null) {
			lblCarritoEnCurso = new JLabel("");
		}
		return lblCarritoEnCurso;
	}
	private JPanel getPanelTablaProductos() {
		if (panelTablaProductos == null) {
			panelTablaProductos = new JPanel();
		}
		return panelTablaProductos;
	}
}
