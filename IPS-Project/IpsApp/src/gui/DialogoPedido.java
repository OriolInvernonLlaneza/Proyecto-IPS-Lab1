package gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;

import database.ConsultasMyShop;
import database.GeneradorIDUsuario;
import logica.Pedido;
import logica.Producto;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Calendar;

import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogoPedido extends JDialog {
	
	private Pedido pedido;
	private ResourceManager manager;
	
	private JPanel pnDialogo;
	private JPanel panel_1;
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

	
	private String escribirResumen(){
		StringBuilder str = new StringBuilder(manager.cambiarFechaAZona(Calendar.getInstance().getTime()));
		str.append("\n");
		str.append("---------------------------");
		str.append("\n");
		str.append(manager.getString("pedido") + ": ");
		str.append("\n");
		for(Producto producto : pedido.getProductos())
			str.append("\t" + producto.getNombre() + " - " + producto.getPrecio() + " - " + manager.getString("unidades") + ": " + pedido.getCantidad(producto.getId()) + "\n");
		str.append("\n");
		str.append(manager.getString("precioTotal") + ": " + pedido.getPrecio());
		return str.toString();
	}
	
	private void localizar(){
		btnAceptar.setText(manager.getString("aceptar"));
		btnCancelar.setText(manager.getString("cancelar"));
		
		lblNombre.setText(manager.getString("nombre") + ":");
		lblApellidos.setText(manager.getString("apellidos") + ":");
	}
	
	

	/**
	 * Create the dialog.
	 */
	public DialogoPedido() {
		manager = ResourceManager.getResourceManager();
		localizar();
		
		setModal(true);
		setBounds(100, 100, 536, 394);
		getContentPane().add(getPnDialogo(), BorderLayout.CENTER);
		getContentPane().add(getPanel_1(), BorderLayout.SOUTH);
		
		txtResumen.setText(escribirResumen());
		txtResumen.setCaretPosition(0);

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
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_1.add(getBtnAceptar());
			panel_1.add(getBtnCancelar());
		}
		return panel_1;
	}
	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton();
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(txApellidos.getText() == "" && txNombre.getText() == ""){
						JOptionPane.showMessageDialog(null, manager.getString("camposVacios"), manager.getString("error"), JOptionPane.ERROR_MESSAGE);
						return;
					}else{
//						String idUsuario = new GeneradorIDUsuario(txNombre.getText(), txApellidos.getText()).generarID();
//						Pedido pedido = new Pedido(
//								ConsultasMyShop.getSiguienteIDPedido(), idUsuario, Calendar.getInstance().getTime()
//								, )
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
			pnResumen.add(getTxtResumen());
		}
		return pnResumen;
	}
	private JPanel getPnCampos() {
		if (pnCampos == null) {
			pnCampos = new JPanel();
			pnCampos.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			GridBagLayout gbl_pnCampos = new GridBagLayout();
			gbl_pnCampos.columnWidths = new int[]{245, 10, 225, 0};
			gbl_pnCampos.rowHeights = new int[]{10, 0, 0};
			gbl_pnCampos.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_pnCampos.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			pnCampos.setLayout(gbl_pnCampos);
			GridBagConstraints gbc_pnNombre = new GridBagConstraints();
			gbc_pnNombre.fill = GridBagConstraints.BOTH;
			gbc_pnNombre.insets = new Insets(0, 0, 0, 5);
			gbc_pnNombre.gridx = 0;
			gbc_pnNombre.gridy = 1;
			pnCampos.add(getPnNombre(), gbc_pnNombre);
			GridBagConstraints gbc_pnApellidos = new GridBagConstraints();
			gbc_pnApellidos.gridwidth = 2;
			gbc_pnApellidos.insets = new Insets(0, 0, 0, 5);
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
		}
		return txtResumen;
	}
}
