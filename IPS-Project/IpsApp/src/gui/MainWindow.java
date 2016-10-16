package gui;
 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Util.ModeloNoEditableSpinner;
import Util.SpinnerEditor;
import Util.SpinnerRenderer;
import database.ConsultasMyShop;
import logica.GrupoProducto;
import logica.Producto;
 
public class MainWindow extends JFrame {
	
	//Nico pls que funcione (?)
 
    /**
     *
     */
    private ResourceManager manager;
 
    private DefaultListModel<GrupoProducto> carritoListaModelo = new DefaultListModel<GrupoProducto>();
    private List<Producto> productos;
    
    private final int SPINNER_COLUMN = 2;
 
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
    private JTextField txtPrecioTotal;
    private JPanel panelBotonesPedido;
    private JButton btnConfirmarPedido;
    private JButton btnCancelarPedido;
    private JScrollPane scrollPaneTabla;
    private JPanel panelSearch;
    private JTable tableProductos;
 
    private ModeloNoEditableSpinner modeloTProductos;
    private JButton btnCalcular;
    private JTextPane taDescripcion;
    
    private void localizar() {
    	setTitle(manager.getString("titulo"));
    	
    	//Borders
    	manager.formatBorder(panelProductos, "producto");
    	manager.formatBorder(panelListaCarrito, "label_carrito");
    	//Descripciï¿½n:
    	
    	//Botones
    	btnRemove.setToolTipText(manager.getString("btn_eliminarCarrito"));
    	btnAdd.setToolTipText(manager.getString("btn_anadirCarrito"));
        btnConfirmarPedido.setText(manager.getString("confirmar"));
        btnConfirmarPedido.setToolTipText(manager.getString("btn_confirmarCarrito"));
        btnCancelarPedido.setText(manager.getString("cancelar"));
        btnCancelarPedido.setToolTipText(manager.getString("btn_cancelarCarrito"));
        btnCalcular.setText(manager.getString("btn_calcularCarrito"));
        btnCalcular.setToolTipText("Calcular el precio total del carrito");
        
        btnCalcular.setMnemonic(manager.getChar("mnm_calcular"));
        btnRemove.setMnemonic(manager.getChar("mnm_eliminar"));
        btnCancelarPedido.setMnemonic(manager.getChar("mnm_cancelar"));
        btnConfirmarPedido.setMnemonic(manager.getChar("mnm_confirmar"));
        
        //modeloTProductos.setColumnIdentifiers(new String[] {manager.getString("nombre"), manager.getString("precio"), manager.getString("cantidad")});
        
        //Labels
        lblPrecioTotal.setDisplayedMnemonic(manager.getChar("mnm_precio"));
        lblPrecioTotal.setText(manager.getString("label_precioTotal"));
    	
    }
 
    private String getDescripcionProductos(int fila) {
        return productos.get(fila).getDescripcion();
    }
 
    private void rellenarTablaProductos() {
        Object[] nuevaFila = new Object[3];
        try {
        	productos = ConsultasMyShop.getProductos();
			for (Producto producto : productos) {
			    nuevaFila[0] = producto;
			    nuevaFila[1] = producto.getPrecio();
			    modeloTProductos.addRow(nuevaFila);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    //-------------------SWING CODE----------------------------
    
    /**
     * Create the frame.
     */
    public MainWindow() {
    	manager = ResourceManager.getResourceManager();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 726, 571);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        contentPane.add(getPanelProductos(), BorderLayout.CENTER);
        contentPane.add(getPanelCarrito(), BorderLayout.EAST);
        contentPane.add(getPanelBotonesPedido(), BorderLayout.SOUTH);
 
        
        localizar();
        rellenarTablaProductos();
    }
 
    private JPanel getPanelProductos() {
        if (panelProductos == null) {
            panelProductos = new JPanel();
            panelProductos.setBorder(
                    new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
            panelProductos.setLayout(new BorderLayout(0, 0));
            panelProductos.add(getScrollPaneTabla(), BorderLayout.CENTER);
            panelProductos.add(getPanelSearch(), BorderLayout.NORTH);
            panelProductos.add(getTaDescripcion(), BorderLayout.SOUTH);
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
            panelListaCarrito.setBorder(
                    new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
            btnAdd.setMnemonic('+');
            btnAdd.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    int fila = tableProductos.getSelectedRow();
                    if (fila != -1) {
                    	Producto producto = (Producto)modeloTProductos.getValueAt(fila, 0);
                    	int cantidad = (int) modeloTProductos.getValueAt(fila, SPINNER_COLUMN);
                    	GrupoProducto unidad = new GrupoProducto(producto, cantidad);
                    	if(carritoListaModelo.contains(unidad)){
                    		for(Object objeto : carritoListaModelo.toArray()){
                    			GrupoProducto encontrado = (GrupoProducto) objeto;
                    			if(encontrado.equals(unidad))
                    				encontrado.setCantidad(encontrado.getCantidad() + unidad.getCantidad());
                    		}
                    	}
                    	else
                    		carritoListaModelo.addElement(unidad);
                    	listCarrito.repaint();
                    }
                }
            });
        }
        return btnAdd;
    }
 
    private JButton getBtnRemove() {
        if (btnRemove == null) {
            btnRemove = new JButton("-");
            btnRemove.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent arg0) {
            		if(!listCarrito.isSelectionEmpty()){
            			GrupoProducto unidad = (GrupoProducto) listCarrito.getSelectedValue();
            			if(unidad.getCantidad() > 1){
            				unidad.quitarProducto();
            				listCarrito.repaint();
            			}
            			else
            				carritoListaModelo.remove(listCarrito.getSelectedIndex());
            		}
            	}
            });
            btnRemove.setMnemonic('-');
        }
        return btnRemove;
    }
 
    private JPanel getPanelPrecio() {
        if (panelPrecio == null) {
            panelPrecio = new JPanel();
            panelPrecio.add(getLblPrecioTotal());
            panelPrecio.add(getTxtPrecioTotal());
            panelPrecio.add(getBtnCalcular());
        }
        return panelPrecio;
    }
 
    private JLabel getLblPrecioTotal() {
        if (lblPrecioTotal == null) {
        	lblPrecioTotal = new JLabel();
        	lblPrecioTotal.setLabelFor(getTxtPrecioTotal());
        }
        return lblPrecioTotal;
    }
 
    private JTextField getTxtPrecioTotal() {
        if (txtPrecioTotal == null) {
            txtPrecioTotal = new JTextField();
            txtPrecioTotal.setEditable(false);
            txtPrecioTotal.setColumns(10);
        }
        return txtPrecioTotal;
    }
 
    private JPanel getPanelBotonesPedido() {
        if (panelBotonesPedido == null) {
            panelBotonesPedido = new JPanel();
            panelBotonesPedido.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
            panelBotonesPedido.add(getBtnConfirmarPedido());
            panelBotonesPedido.add(getBtnCancelarPedido());
        }
        return panelBotonesPedido;
    }
 
    private JButton getBtnConfirmarPedido() {
        if (btnConfirmarPedido == null) {
            btnConfirmarPedido = new JButton();
            btnConfirmarPedido.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent arg0) {
            		List<GrupoProducto> carrito = new ArrayList<GrupoProducto>();
            		for(Object object : carritoListaModelo.toArray())
            			carrito.add((GrupoProducto)object);
            		DialogoPedido dialogo = new DialogoPedido(carrito);
            		dialogo.setVisible(true);
            	}
            });
            btnConfirmarPedido.setAlignmentX(Component.RIGHT_ALIGNMENT);
        }
        return btnConfirmarPedido;
    }
 
    private JButton getBtnCancelarPedido() {
        if (btnCancelarPedido == null) {
            btnCancelarPedido = new JButton();
            btnCancelarPedido.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent arg0) {
            		System.exit(0);
            		//Deberia cerrar esta ventana y hacer visible la de login. sys exit no bueno ser
            	}
            });
            btnCancelarPedido.setAlignmentX(Component.RIGHT_ALIGNMENT);
        }
        return btnCancelarPedido;
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
            String[] nombreColumnas = {manager.getString("nombre"), manager.getString("precio"), manager.getString("cantidad")};
            modeloTProductos = new ModeloNoEditableSpinner(nombreColumnas, 0);
 
            tableProductos = new JTable(modeloTProductos);
 
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
 
    private JButton getBtnCalcular() {
        if (btnCalcular == null) {
            btnCalcular = new JButton();
            btnCalcular.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		double precio = 0;
            		for(Object producto : carritoListaModelo.toArray()){
            			GrupoProducto grupo = (GrupoProducto)producto;
            			precio+= grupo.getCantidad() * grupo.getProducto().getPrecio();
            		}
            		txtPrecioTotal.setText(String.valueOf(precio));
            		
            	}
            });
        }
        return btnCalcular;
    }
 
	private JTextPane getTaDescripcion() {
		if (taDescripcion == null) {
			taDescripcion = new JTextPane();
			taDescripcion.setEditable(false);
			Border border = BorderFactory.createLineBorder(Color.BLACK);
			border = BorderFactory.createTitledBorder(border, "Descripción:");
			taDescripcion.setBorder(border);
		}
		return taDescripcion;
	}
}