package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import database.ConsultasMyShop;
import database.GeneradorIDUsuario;
import logica.GrupoProducto;
import logica.Pedido;
import logica.Producto;

public class DialogoPedido extends JDialog {
	
	private Pedido pedido;
	private ResourceManager manager;
	private List<GrupoProducto> productos;
	private HashMap<String, GrupoProducto> agrupacion;
	
	private JPanel pnDialogo;
	private JPanel pnBotones;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JPanel pnResumen;
	private JPanel pnCampos;
	private JPanel pnApellidos;
	private JPanel pnNombre;
	private JLabel lblNombre;
	private JTextField txNombre;
	private JLabel lblApellidos;
	private JTextField txApellidos;
	private JTextArea txtResumen;
	private JScrollPane scResumenText;

	
	private String escribirResumen(){
		double precio = 0;
		
		StringBuilder str = new StringBuilder(manager.cambiarFechaAZona(Calendar.getInstance().getTime()));
		str.append("\n");
		str.append("---------------------------");
		str.append("\n");
		str.append(manager.getString("pedido") + ": ");
		str.append("\n");
		for(GrupoProducto unidad : productos){
			Producto producto = unidad.getProducto();
			precio+= producto.getPrecio() * unidad.getCantidad();
			str.append("\t" + producto.getNombre() + " - " + producto.getPrecio() + " - " + manager.getString("unidades") + ": " + unidad.getCantidad() + "\n");
		}
		str.append("\n");
		str.append(manager.getString("precioTotal") + ": " + precio);
		return str.toString();
	}
	
	private void localizar(){
		setTitle(manager.getString("titulo_pedido"));
		
		btnAceptar.setText(manager.getString("aceptar"));
		btnCancelar.setText(manager.getString("cancelar"));
		
		lblNombre.setText(manager.getString("nombre") + ":");
		lblApellidos.setText(manager.getString("apellidos") + ":");
	}
	
	

	/**
	 * Create the dialog.
	 */
	public DialogoPedido(List<GrupoProducto> productos) {
		manager = ResourceManager.getResourceManager();
		this.productos = productos;
		
		setModal(true);
		setBounds(100, 100, 536, 394);
		getContentPane().add(getPnDialogo(), BorderLayout.CENTER);
		getContentPane().add(getPnBotones(), BorderLayout.SOUTH);
		
		txtResumen.setText(escribirResumen());
		txtResumen.setCaretPosition(0);
		
		localizar();

	}
	
	//-----------------------------SWING ------------------------------------

	private JPanel getPnDialogo() {
		if (pnDialogo == null) {
			pnDialogo = new JPanel();
			GridBagLayout gbl_pnDialogo = new GridBagLayout();
			gbl_pnDialogo.columnWidths = new int[]{0, 0, 0};
			gbl_pnDialogo.rowHeights = new int[]{152, 209, 0};
			gbl_pnDialogo.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
			gbl_pnDialogo.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
			pnDialogo.setLayout(gbl_pnDialogo);
			GridBagConstraints gbc_pnCampos = new GridBagConstraints();
			gbc_pnCampos.gridwidth = 2;
			gbc_pnCampos.insets = new Insets(0, 0, 5, 5);
			gbc_pnCampos.fill = GridBagConstraints.BOTH;
			gbc_pnCampos.gridx = 0;
			gbc_pnCampos.gridy = 0;
			pnDialogo.add(getPnCampos(), gbc_pnCampos);
			GridBagConstraints gbc_pnResumen = new GridBagConstraints();
			gbc_pnResumen.gridwidth = 2;
			gbc_pnResumen.insets = new Insets(0, 0, 0, 5);
			gbc_pnResumen.fill = GridBagConstraints.BOTH;
			gbc_pnResumen.gridx = 0;
			gbc_pnResumen.gridy = 1;
			pnDialogo.add(getPnResumen(), gbc_pnResumen);
		}
		return pnDialogo;
	}
	private JPanel getPnBotones() {
		if (pnBotones == null) {
			pnBotones = new JPanel();
			FlowLayout fl_pnBotones = (FlowLayout) pnBotones.getLayout();
			fl_pnBotones.setAlignment(FlowLayout.RIGHT);
			pnBotones.setBorder(new LineBorder(new Color(0, 0, 0)));
			pnBotones.add(getBtnAceptar());
			pnBotones.add(getBtnCancelar());
		}
		return pnBotones;
	}
	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton();
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(txApellidos.getText() == "" && txApellidos.getText().length() >=3 && txNombre.getText().length() >=3 && txNombre.getText() == ""){
						JOptionPane.showMessageDialog(null, manager.getString("camposVacios"), manager.getString("error"), JOptionPane.ERROR_MESSAGE);
						return;
					}else{
						String idUsuario = new GeneradorIDUsuario(txNombre.getText(), txApellidos.getText()).generarID();
						String nombre = txNombre.getText();
						String apellido = txApellidos.getText();
						double precio = 0;
						int cantidad = 0;
						for(GrupoProducto producto : productos){
							precio+=producto.getProducto().getPrecio() * producto.getCantidad();
							cantidad += producto.getCantidad();
						}
						String idPedido = "idFalloBase";
						try {
							idPedido = ConsultasMyShop.getSiguienteIDPedido();
							Pedido pedido = new Pedido(idPedido, idUsuario, Calendar.getInstance().getTime(),cantidad,precio,"direccionPrueba", productos);
							ConsultasMyShop.crearUsuario(idUsuario, nombre, apellido);
							ConsultasMyShop.crearPedido(pedido);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "Pedido creado correctamente");
						dispose();
						
					}
				}
			});
		}
		return btnAceptar;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton();
		}
		return btnCancelar;
	}
	private JPanel getPnResumen() {
		if (pnResumen == null) {
			pnResumen = new JPanel();
			pnResumen.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			pnResumen.setLayout(new BoxLayout(pnResumen, BoxLayout.X_AXIS));
			pnResumen.add(getScResumenText());
		}
		return pnResumen;
	}
	private JPanel getPnCampos() {
		if (pnCampos == null) {
			pnCampos = new JPanel();
			pnCampos.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			GridBagLayout gbl_pnCampos = new GridBagLayout();
			gbl_pnCampos.columnWidths = new int[]{245, 10, 225, 0};
			gbl_pnCampos.rowHeights = new int[]{10, 0, 0, 0, 0};
			gbl_pnCampos.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_pnCampos.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			pnCampos.setLayout(gbl_pnCampos);
			GridBagConstraints gbc_pnNombre = new GridBagConstraints();
			gbc_pnNombre.fill = GridBagConstraints.BOTH;
			gbc_pnNombre.insets = new Insets(0, 0, 5, 5);
			gbc_pnNombre.gridx = 0;
			gbc_pnNombre.gridy = 1;
			pnCampos.add(getPnNombre(), gbc_pnNombre);
			GridBagConstraints gbc_pnApellidos = new GridBagConstraints();
			gbc_pnApellidos.insets = new Insets(0, 0, 5, 0);
			gbc_pnApellidos.gridwidth = 2;
			gbc_pnApellidos.fill = GridBagConstraints.BOTH;
			gbc_pnApellidos.gridx = 1;
			gbc_pnApellidos.gridy = 1;
			pnCampos.add(getPnApellidos(), gbc_pnApellidos);
		}
		return pnCampos;
	}
	private JPanel getPnApellidos() {
		if (pnApellidos == null) {
			pnApellidos = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnApellidos.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnApellidos.add(getLblApellidos());
			pnApellidos.add(getTxApellidos());
		}
		return pnApellidos;
	}
	private JPanel getPnNombre() {
		if (pnNombre == null) {
			pnNombre = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnNombre.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnNombre.add(getLblNombre());
			pnNombre.add(getTxNombre());
		}
		return pnNombre;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel();
		}
		return lblNombre;
	}
	private JTextField getTxNombre() {
		if (txNombre == null) {
			txNombre = new JTextField();
			txNombre.setColumns(10);
		}
		return txNombre;
	}
	private JLabel getLblApellidos() {
		if (lblApellidos == null) {
			lblApellidos = new JLabel();
		}
		return lblApellidos;
	}
	private JTextField getTxApellidos() {
		if (txApellidos == null) {
			txApellidos = new JTextField();
			txApellidos.setColumns(10);
		}
		return txApellidos;
	}
	private JTextArea getTxtResumen() {
		if (txtResumen == null) {
			txtResumen = new JTextArea();
			txtResumen.setEditable(false);
		}
		return txtResumen;
	}
	private JScrollPane getScResumenText() {
		if (scResumenText == null) {
			scResumenText = new JScrollPane();
			scResumenText.setViewportView(getTxtResumen());
		}
		return scResumenText;
	}
}
