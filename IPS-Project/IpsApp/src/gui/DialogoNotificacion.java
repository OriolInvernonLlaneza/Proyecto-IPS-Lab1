package gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import javax.swing.JTextArea;

import database.ConsultasMyShop;
import logica.GrupoProducto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;

public class DialogoNotificacion extends JDialog {
	private  List<GrupoProducto> productosEnFalta;
	private String idPedido;
	private String idAlmacenero;
	private ResourceManager manager;
	
	
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

	
	public DialogoNotificacion(List<GrupoProducto> productosEnFalta,String idPedido, String idAlmacenero) {
		this.productosEnFalta=productosEnFalta;
		this.idPedido=idPedido;
		this.idAlmacenero=idAlmacenero;
		manager = ResourceManager.getResourceManager();
		setBounds(100, 100, 450, 300);
		getContentPane().add(getContentPanel(), BorderLayout.CENTER);

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
						if(!(txtNotificacion.getText().equals("")))
							ConsultasMyShop.crearIncidencia(idPedido, idAlmacenero, txtNotificacion.getText());
						else
							JOptionPane.showMessageDialog(null, "Se debe introducir información relacionada con la incidencia.","Error descripcion",JOptionPane.OK_OPTION);;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
			checkOtrosMotiv = new JCheckBox("New check box");
			checkOtrosMotiv.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					txtNotificacion.setEditable(true);
					txtNotificacion.setText("");
				}
			});
			grupoMotivos.add(checkOtrosMotiv);
			checkOtrosMotiv.setToolTipText("Otros motivos");
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
			lblDescripcion = new JLabel("Descripcion:");
		}
		return lblDescripcion;
	}
}
