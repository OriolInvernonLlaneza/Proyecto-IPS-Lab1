package gui;

import java.awt.BorderLayout;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelListener;

import Util.CambiarCodigo;
import Util.ModeloEditableUnaCelda;
import Util.ModeloNoEditable;
import database.ConsultasMyShop;
import logica.Almacenero;
import logica.GrupoProducto;
import logica.OrdenDeTrabajo;
import logica.Pedido;
import logica.Producto;

public class VentanaAlmacenero extends JFrame {
	
	private ResourceManager manager;

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelPedidos;
	private JScrollPane spPedidos;
	private JButton btnEmpezar;
	private JPanel panelBotonesPedidos;
	private JButton btnRefrescar;
	private JTable tPedidos;
	
	private ModeloNoEditable modeloTPedidos;
	private ModeloEditableUnaCelda modeloTOT;
	
	private Pedido pedidoElegido;
	
	private List<Producto> productos1;
	private List<Producto> productos2;
	
	private List<Pedido> pedidos;
	
	private JPanel panelOT;
	private JPanel panelListOT;
	private JPanel panelBotonesOT;
	private JButton btnNotificar;
	private JButton btnAcabar;
	private JScrollPane spOT;
	private JTable tOT;
	
	TableModelListener cambiarCodigo;
	
	private VentanaNotificacion vN;
	private JPanel panelBotonesGeneral;
	private JButton btnSalir;
	

	private Almacenero almacenero; 
	DialogoUltimaComprobacion dialogoUltimaComprobacion;


	private LoginWindow login;
	
	
	
	//Este inicializar es para testear en caso de fallo de la base de datos.
	private void inicializar2(){
		productos1=new ArrayList<Producto>();
		productos2=new ArrayList<Producto>();
		
		Producto prod1 = new Producto("25", "Girasol","asa",5.90, 200, "A2", "cod1");
		Producto prod2 = new Producto("25", "Mandarina","asa", 0.10, 200, "B3", "cod2");
		Producto prod3 = new Producto("18", "Teclado","asa", 49.99, 200, "A3", "cod3");
		
		Producto prod4 = new Producto("233", "Teclado3","asa1", 49.99, 200, "A3", "cod5");
		Producto prod5 = new Producto("233", "Teclado2","asa1", 49.99, 200, "A3", "cod4");
		
		List<GrupoProducto> grupo = new ArrayList<GrupoProducto>();
		grupo.add(new GrupoProducto(prod1, 1));
		grupo.add(new GrupoProducto(prod2, 1));
		grupo.add(new GrupoProducto(prod3, 1));
		
		List<GrupoProducto> grupo2 = new ArrayList<GrupoProducto>();
		grupo.add(new GrupoProducto(prod4, 1));
		grupo.add(new GrupoProducto(prod5, 2));
		
		pedidos=new ArrayList<Pedido>();
		pedidos.add(new Pedido("78", "us1",new Date(2010, 5, 14),20,20, "Calle memes",grupo));
		pedidos.add(new Pedido("128", "us2",new Date(2004,11,20),15,15, "Calle memes 2",grupo2));
		
		//Mio
		almacenero.setOrdenDeTrabajoActual(new OrdenDeTrabajo(almacenero,
				new Pedido("78", "us1",new Date(2010, 5, 14),20,20, "Calle memes",grupo) , "Asignada"));
		
	}
	
	//Inicializar teniendo en cuenta la base de datos.
	private void inicializar() throws SQLException{
		pedidos=ConsultasMyShop.getPedidos();
	}
	
	//Refresca la informacion de pedidos cancelando la orden de trabajo actual.
	private void refrescar(){
		modeloTPedidos.setRowCount(0);
		modeloTOT.setRowCount(0);
		try {
			inicializar();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	//Cuando se refresca se vuelve a llenar tras borrar los contenidos de los pedidos
		private void refrescarTablaPedidos(){
			refrescar();
			RellenarTablaPedidos();
		}
	
	//Metodo para  añadir las filas correspondientes en la tabla de pedidos
	private void RellenarTablaPedidos(){
		Object[] nuevaFila = new Object[3];
		for(Pedido pedido : pedidos){
			nuevaFila[0] = pedido.getId();
			nuevaFila[1] = pedido.getFecha();
			nuevaFila[2] = pedido.getTamano();
			modeloTPedidos.addRow(nuevaFila);
		}
	}
	
	//Probando
	
	//Metodo para añadir las filas correspondientes al producto elegido en la tabla de pedidos.
	private void RellenarTablaOT(Pedido pedido){
		Object[] nuevaFila = new Object[5];
		for(GrupoProducto grupo : pedido.getAgrupacion().values()){
			Producto producto = grupo.getProducto();
			nuevaFila[0] = producto.getId();
			nuevaFila[1] = producto.getNombre();
			nuevaFila[2] = producto.getLocalizacion();
			//nuevaFila[3] = producto.g
			nuevaFila[3] = grupo.getCantidad();
			modeloTOT.addRow(nuevaFila);
		}
	}
	
	
	//Metodo usando la estructura de nico que aplica los principios de internacionalizacion a la aplicacion.
	private void localizar(){
		this.setTitle(manager.getString("titulo"));
		
		
		//Botones
		btnSalir.setText(manager.getString("salir"));
		btnRefrescar.setText(manager.getString("refrescar"));
		btnEmpezar.setText(manager.getString("empezar"));
		btnAcabar.setText(manager.getString("acabar"));
		btnNotificar.setText(manager.getString("notificar"));
		
		//Tabla
		modeloTPedidos.setColumnIdentifiers(new String[] {manager.getString("pedidoid"), manager.getString("fecha"), manager.getString("tamano")});
		modeloTOT.setColumnIdentifiers(new String[] {manager.getString("productoid"), manager.getString("nombre"), manager.getString("localizacion"), manager.getString("cantidad"), manager.getString("encontrado")});
		
	}

	/**
	 * Create the frame.
	 */
	public VentanaAlmacenero(LoginWindow login) {
		this.login = login;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				btnSalir.doClick();
				
			}
		});
		manager = ResourceManager.getResourceManager();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1100, 694);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanelPedidos());
		contentPane.add(getPanelOT(), BorderLayout.EAST);
		contentPane.add(getPanelBotonesGeneral(), BorderLayout.SOUTH);
		
		//Por ahora funcionaremos solo con este almacenero.
		almacenero= new Almacenero("alm01", "cntrsAlmacenero01", "Almacenero01", "AlmaceneroApellido01");
		
		
		try {
			inicializar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		localizar();
		RellenarTablaPedidos();
	}

	private JPanel getPanelPedidos() {
		if (panelPedidos == null) {
			panelPedidos = new JPanel();
			panelPedidos.setBorder(new TitledBorder(null, manager.getString("panelPedidos"), TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
			btnEmpezar = new JButton();
			btnEmpezar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int elegido=tPedidos.getSelectedRow();
					if(elegido!=-1){
						tOT.getModel().removeTableModelListener(cambiarCodigo);
						modeloTOT.setRowCount(0);
						pedidoElegido=pedidos.get(elegido);
						RellenarTablaOT(pedidoElegido);
						almacenero.setOrdenDeTrabajoActual(new OrdenDeTrabajo(almacenero, pedidoElegido, "En curso"));
						cambiarCodigo=new CambiarCodigo(pedidoElegido.getCodigos(),modeloTOT);
						//El modelo debe cambiar las celdas no editables por todas editables
						modeloTOT.restart();
						//El modelo debe añadir de nuevo el listener con la nueva informacion.
						tOT.getModel().addTableModelListener(cambiarCodigo); 
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
			btnRefrescar = new JButton();
			btnRefrescar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				
					int result = JOptionPane.showConfirmDialog(null,manager.getString("avisoRefrescar"),manager.getString("tituloARefrescar"), JOptionPane.YES_NO_OPTION);
					if(result== JOptionPane.YES_OPTION){
						refrescarTablaPedidos();
					}
				}
			});
		}
		return btnRefrescar;
	}
	private JTable getTPedidos() {
		if (tPedidos == null) {
			modeloTPedidos= new ModeloNoEditable(new String[3], 0);
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
			panelOT.setBorder(new TitledBorder(null, manager.getString("panelOT"), TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
			btnNotificar = new JButton();
			VentanaAlmacenero aT= this;
			btnNotificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					// Hay que solucionarlo
					
//					if(modeloTOT.getRowCount() == 0)
//						return;
//					List<GrupoProducto> productosEnFalta= new ArrayList<Producto>(); //La lista de productos que vamos a notificar en el dialogo.
//					for(int row=0; row<pedidoElegido.getTamano();row++){
//						if(Boolean.parseBoolean(tOT.getValueAt(row, 3).toString()) == false){//La tabla solo trabaja con String asi que lo apaï¿½amos asi
//							productosEnFalta.add(pedidoElegido.getProductos().get(row));//Si no esta cogido lo aï¿½adimos para notificar
//						}
//					}
//					vN= new VentanaNotificacion(aT,productosEnFalta);
//					vN.setLocationRelativeTo(aT);
//					vN.setVisible(true);
					
					//AQUI SE GENERA EL NUEVO DIALOGO CON LA INCIDENCIA TENIENDO EN CUANTA LOS OBJETOS QUE FALTAN.
				}
			});
		}
		return btnNotificar;
	}
	private JButton getBtnAcabar() {
		if (btnAcabar == null) {
			btnAcabar = new JButton();
			btnAcabar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(false) {
						//Si todos los checkboxes no estan completos => dialogo informativo
					}
					else {
						try {
							casoPruebaPedro();
							//OrdenDeTrabajo ot = almacenero.getOrdenDeTrabajoActual();
							//almacenero.marcarParaEmpaquetar(ot);
							dialogoUltimaComprobacion = new DialogoUltimaComprobacion(almacenero);
							dialogoUltimaComprobacion.setLocationRelativeTo(null);
							dialogoUltimaComprobacion.setVisible(true);
							
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, "Ha habido un problema al marcar para empaquetar");
						}
					}
					
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
			//Cambiar este 10
			modeloTOT= new ModeloEditableUnaCelda(new String[5],0,10);
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
	private JPanel getPanelBotonesGeneral() {
		if (panelBotonesGeneral == null) {
			panelBotonesGeneral = new JPanel();
			panelBotonesGeneral.setLayout(new BorderLayout(0, 0));
			panelBotonesGeneral.add(getBtnSalir(), BorderLayout.EAST);
		}
		return panelBotonesGeneral;
	}
	private JButton getBtnSalir() {
		if (btnSalir == null) {
			btnSalir = new JButton();
			VentanaAlmacenero ventana = this;
			btnSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int result = JOptionPane.showConfirmDialog(null,manager.getString("avisoSalir"),manager.getString("tituloASalir"), JOptionPane.YES_NO_OPTION);
					if(result== JOptionPane.YES_OPTION){
						ventana.setVisible(false);
						login.windowVisible(true);
					}
				}

			});
		}
		return btnSalir;
	}
	
	private void casoPruebaPedro() throws SQLException {
		almacenero = new Almacenero("alm01", "cont233", "Nombre", "Apellido1 Apellido2");
		productos1=new ArrayList<Producto>();
		productos2=new ArrayList<Producto>();
		
		Producto prod1 = new Producto("25", "Girasol","asa",5.90, 200, "A2", "cod1");
		Producto prod2 = new Producto("25", "Mandarina","asa", 0.10, 200, "B3", "cod2");
		Producto prod3 = new Producto("18", "Teclado","asa", 49.99, 200, "A3", "cod3");
		
		
		List<GrupoProducto> grupo = new ArrayList<GrupoProducto>();
		grupo.add(new GrupoProducto(prod1, 1));
		grupo.add(new GrupoProducto(prod2, 1));
		grupo.add(new GrupoProducto(prod3, 1));
		
		
		almacenero.setOrdenDeTrabajoActual(new OrdenDeTrabajo(almacenero,
				new Pedido("2", "us1",new Date(2010, 5, 14),20,20, "Calle memes",grupo) , "Asignada"));
		
		//almacenero.insertarOrdenDeTrabajo(almacenero.getOrdenDeTrabajoActual());
		
	}
}
