package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import database.ConsultasMyShop;
import logica.GrupoProducto;

public class DialogoNotificacion extends JDialog {
	private  List<GrupoProducto> productosEnFalta;
	private String idPedido;
	private String idAlmacenero;
	private ResourceManager manager;
	
	//Componente de la VENTANA ALMACENERO APRA TENER ACESO A REFRESCAR TABLAS.
	private JButton btnRefrescar;
	
	private JPanel contentPanel;
	private JPanel pnBotones;
	private JPanel pnOpciones;
	private JPanel pnTxtArea;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JCheckBox checkFaltaProd;
	private JCheckBox checkOtrosMotiv;
	private final ButtonGroup grupoMotivos = new ButtonGroup();
	private JTextArea txtNotificacion;
	private JLabel lblDescripcion;
	
	private final ButtonGroup grupoNotificacion = new ButtonGroup();

	private void localizar(){
		checkFaltaProd.setText(manager.getString("falta_productos"));
		checkOtrosMotiv.setText(manager.getString("chk_otros_motivos"));
		checkOtrosMotiv.setToolTipText(manager.getString("chk_otros_motivos"));

		btnCancelar.setText(manager.getString("cancelar"));
		btnAceptar.setText(manager.getString("aceptar"));
		
		lblDescripcion.setText(manager.getString("descripcion") + ": ");
	}

	/**
	 * Create the dialog.
	 */
	//Metodo para redactar el miniinforme del area de texto.

	private String redactar(){
		Date fecha = new Date();
		StringBuilder sb= new StringBuilder();
		sb.append(manager.getString("dia"));
		sb.append(manager.cambiarFechaAZona(fecha));
		sb.append("\n");
		sb.append("------------------------------------------------------------------ \n");
		if(productosEnFalta.size()==0){
			sb.append(manager.getString("vacio"));
		}
		else{
		sb.append(manager.getString("productosEnFalta") +  "\n");
			for(GrupoProducto producto: productosEnFalta){
				sb.append(manager.getString("producto") + ": ");
				sb.append(producto.getID());
				sb.append(" ");
				sb.append(manager.getString("nombre") + ": ");
				sb.append(producto.getProducto().getNombre());
				sb.append("\n");
			}
		}
		
		return sb.toString();
		
	}

	
	public DialogoNotificacion(List<GrupoProducto> productosEnFalta,String idPedido, String idAlmacenero, JButton btnRefrescar) {
		this.productosEnFalta=productosEnFalta;
		this.idPedido=idPedido;
		this.idAlmacenero=idAlmacenero;
		this.btnRefrescar=btnRefrescar;
		manager = ResourceManager.getResourceManager();
		setTitle(manager.getString("notificacionTitulo"));
		setBounds(100, 100, 520, 416);
		getContentPane().add(getContentPanel(), BorderLayout.CENTER);
		
		localizar();
	}

	private JPanel getContentPanel() {
		if (contentPanel == null) {
			contentPanel = new JPanel();
			contentPanel.setLayout(new BorderLayout(0, 0));
			contentPanel.add(getPnBotones(), BorderLayout.SOUTH);
			contentPanel.add(getPnOpciones(), BorderLayout.NORTH);
			contentPanel.add(getPnTxtArea());
		}
		return contentPanel;
	}
	private JPanel getPnBotones() {
		if (pnBotones == null) {
			pnBotones = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnBotones.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnBotones.add(getBtnAceptar());
			pnBotones.add(getBtnCancelar());
		}
		return pnBotones;
	}
	private JPanel getPnOpciones() {
		if (pnOpciones == null) {
			pnOpciones = new JPanel();
			pnOpciones.add(getCheckFaltaProd());
			pnOpciones.add(getCheckOtrosMotiv());
		}
		return pnOpciones;
	}
	private JPanel getPnTxtArea() {
		if (pnTxtArea == null) {
			pnTxtArea = new JPanel();
			pnTxtArea.setLayout(new BorderLayout(0, 0));
			pnTxtArea.add(getTxtNotificacion(), BorderLayout.CENTER);
			pnTxtArea.add(getLblDescripcion(), BorderLayout.NORTH);
		}
		return pnTxtArea;
	}
	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						if(!(txtNotificacion.getText().equals(""))){
							ConsultasMyShop.crearIncidencia(idPedido, idAlmacenero, txtNotificacion.getText());
							//Mirar esto bien.
							ConsultasMyShop.cambiarEstadoOrdenDeTrabajo("Incidencia", idPedido);
							
						}
						else
							JOptionPane.showMessageDialog(null, manager.getString("informacion_faltante"), manager.getString("titulo_error_descripcion"),JOptionPane.OK_OPTION);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					btnRefrescar.doClick();
					dispose();
				}
			});		
		
		}
		return btnAceptar;
	}
		
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		return btnCancelar;
	}
	private JCheckBox getCheckFaltaProd() {
		if (checkFaltaProd == null) {
			checkFaltaProd = new JCheckBox("Falta de productos");
			checkFaltaProd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					txtNotificacion.setText(redactar());
					txtNotificacion.setEditable(false);
				}
			});
			grupoMotivos.add(checkFaltaProd);
			checkFaltaProd.setSelected(true);
		}
		return checkFaltaProd;
	}
	private JCheckBox getCheckOtrosMotiv() {
		if (checkOtrosMotiv == null) {
			checkOtrosMotiv = new JCheckBox();
			checkOtrosMotiv.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					txtNotificacion.setEditable(true);
					txtNotificacion.setText("");
				}
			});
			grupoMotivos.add(checkOtrosMotiv);
		}
		return checkOtrosMotiv;
	}
	private JTextArea getTxtNotificacion() {
		if (txtNotificacion == null) {
			txtNotificacion = new JTextArea();
			txtNotificacion.setText(redactar());
		}
		return txtNotificacion;
	}
	private JLabel getLblDescripcion() {
		if (lblDescripcion == null) {
			lblDescripcion = new JLabel();
		}
		return lblDescripcion;
	}
}
