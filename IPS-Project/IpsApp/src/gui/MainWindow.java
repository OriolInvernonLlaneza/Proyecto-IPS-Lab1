package gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Util.ModeloNoEditable;
import Util.ModeloNoEditableSpinner;
import Util.SpinnerEditor;
import Util.SpinnerRenderer;
import logica.Pedido;
import logica.Producto;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTable;
import java.awt.TextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.FlowLayout;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private ResourceManager manager;

	private DefaultListModel carritoListaModelo = new DefaultListModel<>();
	private DefaultListModel productosListaModelo = new DefaultListModel<>();

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelProductos;
	private JPanel panelCarrito;
	private JPanel panelListaCarrito;
	private JScrollPane scrollPaneCarrito;
	private JList listCarrito;
	private JPanel panelBotonesLista;
	private JButton btnAdd;
	private JButton btnRemove;
	private JPanel panelPrecio;
	private JLabel lblPrecioTotal;
	private JTextField textFieldPrecioTotal;
	private JPanel panelBotonesPedido;
	private JButton btnConfirmarPedido;
	private JButton btnCancelarPedido;
	private JPanel panelAux;
	private JScrollPane scrollPaneTabla;
	private JPanel panelSearch;
	private JTable tableProductos;

	private ModeloNoEditableSpinner modeloTProductos;
	private JScrollPane scrollPaneDescripcion;
	private JTextArea taDescripcion;
	private JButton btnCalcular;

	private ArrayList<Producto> productos;

	private void localizar() {
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
		contentPane.add(getPanelBotonesPedido(), BorderLayout.SOUTH);

		manager = ResourceManager.getResourceManager();
		localizar();
		pedirProductosDatabase();
		rellenarTablaProductos();
		carritoListaModelo.addElement("blah");
		carritoListaModelo.addElement("blah2");
		carritoListaModelo.addElement("blah3");
	}

	private JPanel getPanelProductos() {
		if (panelProductos == null) {
			panelProductos = new JPanel();
			// TODO Nico toma texto pa tu body
			panelProductos.setBorder(
					new TitledBorder(null, "Productos:", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
			panelProductos.setLayout(new BorderLayout(0, 0));
			panelProductos.add(getScrollPaneTabla(), BorderLayout.CENTER);
			panelProductos.add(getPanelSearch(), BorderLayout.NORTH);
			panelProductos.add(getScrollPaneDescripcion(), BorderLayout.SOUTH);
		}
		return panelProductos;
	}

	private JPanel getPanelCarrito() {
		if (panelCarrito == null) {
			panelCarrito = new JPanel();
			panelCarrito.setBorder(null);
			panelCarrito.setLayout(new BorderLayout(0, 0));
			panelCarrito.add(getPanelListaCarrito());
			panelCarrito.add(getPanelPrecio(), BorderLayout.SOUTH);
		}
		return panelCarrito;
	}

	private JPanel getPanelListaCarrito() {
		if (panelListaCarrito == null) {
			panelListaCarrito = new JPanel();
			// TODO Nico toma texto pa tu body
			panelListaCarrito.setBorder(
					new TitledBorder(null, "Carrito en curso:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelListaCarrito.setLayout(new BorderLayout(0, 0));
			panelListaCarrito.add(getScrollPaneCarrito(), BorderLayout.CENTER);
			panelListaCarrito.add(getPanelBotonesLista(), BorderLayout.SOUTH);
		}
		return panelListaCarrito;
	}

	private JScrollPane getScrollPaneCarrito() {
		if (scrollPaneCarrito == null) {
			scrollPaneCarrito = new JScrollPane();
			scrollPaneCarrito.setViewportView(getListCarrito());
		}
		return scrollPaneCarrito;
	}

	private JList getListCarrito() {
		if (listCarrito == null) {
			listCarrito = new JList<Producto>();
			listCarrito.setModel(carritoListaModelo);
		}
		return listCarrito;
	}

	private JPanel getPanelBotonesLista() {
		if (panelBotonesLista == null) {
			panelBotonesLista = new JPanel();
			panelBotonesLista.add(getBtnAdd());
			panelBotonesLista.add(getBtnRemove());
		}
		return panelBotonesLista;
	}

	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("+");
			btnAdd.setToolTipText("a\u00F1adir un producto al carrito");
			btnAdd.setMnemonic('a');
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int fila = tableProductos.getSelectedRow();
					if (fila != -1) {
						carritoListaModelo.addElement(tableProductos.getValueAt(fila, 0));
					}
				}
			});
		}
		return btnAdd;
	}

	private JButton getBtnRemove() {
		if (btnRemove == null) {
			btnRemove = new JButton("-");
			// TODO Nico toma texto pa tu body
			btnRemove.setToolTipText("Eliminar una unidad del carrito");
			btnRemove.setMnemonic('r');
		}
		return btnRemove;
	}

	private JPanel getPanelPrecio() {
		if (panelPrecio == null) {
			panelPrecio = new JPanel();
			panelPrecio.add(getLblPrecioTotal());
			panelPrecio.add(getTextFieldPrecioTotal());
			panelPrecio.add(getBtnCalcular());
		}
		return panelPrecio;
	}

	private JLabel getLblPrecioTotal() {
		if (lblPrecioTotal == null) {
			// TODO Nico toma texto pa tu body
			lblPrecioTotal = new JLabel("Precio total:");
			lblPrecioTotal.setLabelFor(getTextFieldPrecioTotal());
			lblPrecioTotal.setDisplayedMnemonic('p');
		}
		return lblPrecioTotal;
	}

	private JTextField getTextFieldPrecioTotal() {
		if (textFieldPrecioTotal == null) {
			textFieldPrecioTotal = new JTextField();
			textFieldPrecioTotal.setEditable(false);
			textFieldPrecioTotal.setColumns(10);
		}
		return textFieldPrecioTotal;
	}

	private JPanel getPanelBotonesPedido() {
		if (panelBotonesPedido == null) {
			panelBotonesPedido = new JPanel();
			GridBagLayout gbl_panelBotonesPedido = new GridBagLayout();
			gbl_panelBotonesPedido.columnWidths = new int[] { 79, 75, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			gbl_panelBotonesPedido.rowHeights = new int[] { 23, 0 };
			gbl_panelBotonesPedido.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0,
					0.0, 0.0, Double.MIN_VALUE };
			gbl_panelBotonesPedido.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
			panelBotonesPedido.setLayout(gbl_panelBotonesPedido);
			GridBagConstraints gbc_panelAux = new GridBagConstraints();
			gbc_panelAux.insets = new Insets(0, 0, 0, 5);
			gbc_panelAux.fill = GridBagConstraints.BOTH;
			gbc_panelAux.gridx = 9;
			gbc_panelAux.gridy = 0;
			panelBotonesPedido.add(getPanelAux(), gbc_panelAux);
			GridBagConstraints gbc_btnConfirmarPedido = new GridBagConstraints();
			gbc_btnConfirmarPedido.anchor = GridBagConstraints.PAGE_END;
			gbc_btnConfirmarPedido.insets = new Insets(0, 0, 0, 5);
			gbc_btnConfirmarPedido.gridx = 10;
			gbc_btnConfirmarPedido.gridy = 0;
			panelBotonesPedido.add(getBtnConfirmarPedido(), gbc_btnConfirmarPedido);
			GridBagConstraints gbc_btnCancelarPedido = new GridBagConstraints();
			gbc_btnCancelarPedido.insets = new Insets(0, 0, 0, 5);
			gbc_btnCancelarPedido.anchor = GridBagConstraints.LINE_END;
			gbc_btnCancelarPedido.gridx = 11;
			gbc_btnCancelarPedido.gridy = 0;
			panelBotonesPedido.add(getBtnCancelarPedido(), gbc_btnCancelarPedido);
		}
		return panelBotonesPedido;
	}

	private JButton getBtnConfirmarPedido() {
		if (btnConfirmarPedido == null) {
			// TODO Nico toma texto pa tu body
			btnConfirmarPedido = new JButton("Confirmar");
			btnConfirmarPedido.setToolTipText("Confirmar un pedido con el carrito actual");
			btnConfirmarPedido.setMnemonic('C');
			btnConfirmarPedido.setAlignmentX(Component.RIGHT_ALIGNMENT);
		}
		return btnConfirmarPedido;
	}

	private JButton getBtnCancelarPedido() {
		if (btnCancelarPedido == null) {
			// TODO Nico toma texto pa tu body
			btnCancelarPedido = new JButton("Cancelar");
			btnCancelarPedido.setToolTipText("Cancelar carrito y salir");
			btnCancelarPedido.setMnemonic('n');
			btnCancelarPedido.setAlignmentX(Component.RIGHT_ALIGNMENT);
		}
		return btnCancelarPedido;
	}

	private JPanel getPanelAux() {
		if (panelAux == null) {
			panelAux = new JPanel();
		}
		return panelAux;
	}

	private JScrollPane getScrollPaneTabla() {
		if (scrollPaneTabla == null) {
			scrollPaneTabla = new JScrollPane();
			scrollPaneTabla.setViewportView(getTableProductos());
		}
		return scrollPaneTabla;
	}

	private JPanel getPanelSearch() {
		if (panelSearch == null) {
			panelSearch = new JPanel();
			// Para cuando nos pidan la busqueda
		}
		return panelSearch;
	}

	private JTable getTableProductos() {
		if (tableProductos == null) {
			final int SPINNER_COLUMN = 2;
			String[] nombreColumnas = { "Nombre", "Precio", "Cantidad" };
			modeloTProductos = new ModeloNoEditableSpinner(nombreColumnas, 0);

			tableProductos = new JTable();
			tableProductos = new JTable(modeloTProductos);

			SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0, 0, 50, 1);
			tableProductos.getColumnModel().getColumn(SPINNER_COLUMN).setCellRenderer(new SpinnerRenderer());
			tableProductos.getColumnModel().getColumn(SPINNER_COLUMN).setCellEditor(new SpinnerEditor());

			tableProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			
			tableProductos.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					int fila = tableProductos.getSelectedRow();
					if (fila != -1) {
						if (arg0.getClickCount() == 2) {
							//carritoListaModelo.addElement(tableProductos.getValueAt(fila, 0));
						} else {
							taDescripcion.setText(getDescripcionProductos(fila));
						}
					}
				}
			});
			tableProductos.getTableHeader().setReorderingAllowed(false);
			tableProductos.setCellSelectionEnabled(false);
			tableProductos.setRowSelectionAllowed(true);
		}
		return tableProductos;
	}

	private String getDescripcionProductos(int fila) {
		return productos.get(fila).getDescripcion();
	}

	private void pedirProductosDatabase() {
		ArrayList<Producto> p = new ArrayList<Producto>();
		p.add(new Producto("25", "Mandarina", 0.10, "asa", "B3"));
		p.add(new Producto("18", "Teclado", 49.99, "asasdaa", "A3"));
		productos = p;
	}

	private void rellenarTablaProductos() {
		Object[] nuevaFila = new Object[3];
		for (Producto producto : productos) {
			nuevaFila[0] = producto.getNombre();
			nuevaFila[1] = producto.getPrecio();
			modeloTProductos.addRow(nuevaFila);
		}
	}

	private JScrollPane getScrollPaneDescripcion() {
		if (scrollPaneDescripcion == null) {
			scrollPaneDescripcion = new JScrollPane();
			scrollPaneDescripcion.setViewportView(getTaDescripcion());
			Border aux = BorderFactory.createLineBorder(Color.BLACK);
			Border borde = BorderFactory.createTitledBorder(aux, "Descripción:");
			scrollPaneDescripcion.setBorder(borde);
		}
		return scrollPaneDescripcion;
	}

	private JTextArea getTaDescripcion() {
		if (taDescripcion == null) {
			taDescripcion = new JTextArea();
			taDescripcion.setEditable(false);
		}
		return taDescripcion;
	}

	private JButton getBtnCalcular() {
		if (btnCalcular == null) {
			// TODO Nico toma texto pa tu body
			btnCalcular = new JButton("Calcular");
			btnCalcular.setToolTipText("Calcular el precio total del carrito");
			btnCalcular.setMnemonic('l');
		}
		return btnCalcular;
	}

}
