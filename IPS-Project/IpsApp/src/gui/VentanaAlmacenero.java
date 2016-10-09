package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Util.ModeloCheckBox;
import Util.ModeloNoEditable;
//import javafx.scene.control.CheckBox;
import logica.Pedido;
import logica.Producto;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaAlmacenero extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelPedidos;
	private JScrollPane spPedidos;
	private JButton btnEmpezar;
	private JPanel panelBotonesPedidos;
	private JButton btnRefrescar;
	private JTable tPedidos;
	
	private ModeloNoEditable modeloTPedidos;
	private ModeloCheckBox modeloTOT;
	
	private Pedido pedidoElegido;
	
	private Producto[] productos1={new Producto(25, "Girasol", "A2")};
	private Producto[] productos2={new Producto(25, "Mandarina", "B3"),new Producto(18, "Teclado", "A3") };
	
	private Pedido[] pedidos={new Pedido(78,new Date(2010, 5, 14),20,productos1), new Pedido(128,new Date(2004,11,20),15,productos2), new Pedido(154,new Date(2018,4,5),78,productos1)};
	
	
	private JPanel panelOT;
	private JPanel panelListOT;
	private JPanel panelBotonesOT;
	private JButton btnNotificar;
	private JButton btnAcabar;
	private JScrollPane spOT;
	private JTable tOT;
	
	private VentanaNotificacion vN;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAlmacenero frame = new VentanaAlmacenero();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//Método para añadir las filas correspondientes en la tabla de pedidos
	private void RellenarTablaPedidos(){
		Object[] nuevaFila = new Object[3];
		for(Pedido pedido : pedidos){
			nuevaFila[0] = pedido.getId();
			nuevaFila[1] = pedido.getFecha();
			nuevaFila[2] = pedido.getTamano();
			modeloTPedidos.addRow(nuevaFila);
		}
	}
	
	//Método para añadir las filas correspondientes al producto elegido en la tabla de pedidos.
	private void RellenarTablaOT(Pedido pedido){
		Object[] nuevaFila = new Object[4];
		for(Producto producto : pedido.getProductos()){
			nuevaFila[0] = producto.getId();
			nuevaFila[1] = producto.getNombre();
			nuevaFila[2] = producto.getLocalizacion();
			nuevaFila[3] = false;
			modeloTOT.addRow(nuevaFila);
		}
	}

	/**
	 * Create the frame.
	 */
	public VentanaAlmacenero() {
		setTitle("Zona de trabajo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 694);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanelPedidos());
		contentPane.add(getPanelOT(), BorderLayout.EAST);
		
		RellenarTablaPedidos();
	}

	private JPanel getPanelPedidos() {
		if (panelPedidos == null) {
			panelPedidos = new JPanel();
			panelPedidos.setBorder(new TitledBorder(null, "Pedidos:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelPedidos.setLayout(new BorderLayout(0, 0));
			panelPedidos.add(getSpPedidos());
			panelPedidos.add(getPanelBotonesPedidos(), BorderLayout.SOUTH);
		}
		return panelPedidos;
	}
	private JScrollPane getSpPedidos() {
		if (spPedidos == null) {
			spPedidos = new JScrollPane();
			spPedidos.setViewportView(getTPedidos());
		}
		return spPedidos;
	}
	private JButton getBtnEmpezar() {
		if (btnEmpezar == null) {
			btnEmpezar = new JButton("Empezar");
			btnEmpezar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int elegido=tPedidos.getSelectedRow();
					if(elegido!=-1){
						modeloTOT.setRowCount(0);
						pedidoElegido=pedidos[elegido];
						RellenarTablaOT(pedidoElegido);
					}
				}
			});
		}
		return btnEmpezar;
	}
	private JPanel getPanelBotonesPedidos() {
		if (panelBotonesPedidos == null) {
			panelBotonesPedidos = new JPanel();
			panelBotonesPedidos.setLayout(new BorderLayout(0, 0));
			panelBotonesPedidos.add(getBtnEmpezar(), BorderLayout.EAST);
			panelBotonesPedidos.add(getBtnRefrescar(), BorderLayout.WEST);
		}
		return panelBotonesPedidos;
	}
	private JButton getBtnRefrescar() {
		if (btnRefrescar == null) {
			btnRefrescar = new JButton("Refrescar");
			btnRefrescar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Se volvería a realizar la consulta
					
					
				}
			});
		}
		return btnRefrescar;
	}
	private JTable getTPedidos() {
		if (tPedidos == null) {
			String[] nombreColumnas = {"PedidoID", "Fecha", "Tamaño"};
			modeloTPedidos= new ModeloNoEditable(nombreColumnas, 0);
			tPedidos = new JTable(modeloTPedidos);
			tPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tPedidos.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					int fila = tPedidos.getSelectedRow();
					if(fila != -1){
						//taDescripcion.setText(inmobiliaria.getDescripcionMansion(fila));
						//CAMBIAR AQUI
					}
				}
			});
			tPedidos.getTableHeader().setReorderingAllowed(false);
			tPedidos.setCellSelectionEnabled(false);
			tPedidos.setRowSelectionAllowed(true);
		}
			
		return tPedidos;
	}
	private JPanel getPanelOT() {
		if (panelOT == null) {
			panelOT = new JPanel();
			panelOT.setBorder(new TitledBorder(null, "Orden de trabajo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelOT.setLayout(new BorderLayout(0, 0));
			panelOT.add(getPanelListOT());
			panelOT.add(getPanelBotonesOT(), BorderLayout.SOUTH);
		}
		return panelOT;
	}
	private JPanel getPanelListOT() {
		if (panelListOT == null) {
			panelListOT = new JPanel();
			panelListOT.setLayout(new BorderLayout(0, 0));
			panelListOT.add(getSpOT());

		}
		return panelListOT;
	}
	private JPanel getPanelBotonesOT() {
		if (panelBotonesOT == null) {
			panelBotonesOT = new JPanel();
			panelBotonesOT.setLayout(new BorderLayout(0, 0));
			panelBotonesOT.add(getBtnNotificar(), BorderLayout.WEST);
			panelBotonesOT.add(getBtnAcabar(), BorderLayout.EAST);
		}
		return panelBotonesOT;
	}
	private JButton getBtnNotificar() {
		if (btnNotificar == null) {
			btnNotificar = new JButton("Notificar");
			VentanaAlmacenero aT= this;
			btnNotificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					List<Producto> productosEnFalta= new ArrayList<Producto>(); //La lista de productos que vamos a notificar en el dialogo.
					for(int row=0; row<pedidoElegido.getProductos().length;row++){
						if(tOT.getValueAt(row, 3).toString().equals("false")){//La tabla solo trabaja con String asi que lo apañamos asi
							productosEnFalta.add(pedidoElegido.getProductos()[row]);//Si no esta cogido lo añadimos para notificar
						}
					}
					vN= new VentanaNotificacion(aT,productosEnFalta);
					vN.setLocationRelativeTo(aT);
					vN.setVisible(true);
					
					//AQUI SE GENERA EL NUEO DIALOGO CON LA INCIDENCIA TENIENDO EN CUANTA LOS OBJETOS QUE FALTAN.
				}
			});
		}
		return btnNotificar;
	}
	private JButton getBtnAcabar() {
		if (btnAcabar == null) {
			btnAcabar = new JButton("Acabar");
			btnAcabar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					//AQUI SE PASA A ENVIO EL PAQUETE.
				}
			});
		}
		return btnAcabar;
	}
	private JScrollPane getSpOT() {
		if (spOT == null) {
			spOT = new JScrollPane();
			spOT.setViewportView(getTOT());
		}
		return spOT;
	}
	private JTable getTOT() {
		if (tOT == null) {
			String[] nombreColumnas = {"ProductoID", "Nombre", "Localizacion","Encontrado"};
			modeloTOT= new ModeloCheckBox(nombreColumnas, 0);
			tOT = new JTable(modeloTOT);
			tOT.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					int fila = tPedidos.getSelectedRow();
					if(fila != -1){
						//taDescripcion.setText(inmobiliaria.getDescripcionMansion(fila));
						//CAMBIAR AQUI
					}
				}
			});
			tOT.getTableHeader().setReorderingAllowed(false);
		}
		return tOT;
	}
}
