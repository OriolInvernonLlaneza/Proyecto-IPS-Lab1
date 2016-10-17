package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.ConsultasMyShop;
import logica.Almacenero;
import logica.GrupoProducto;
import logica.Producto;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;


@SuppressWarnings("serial")
public class VentanaNotificacion extends JDialog {
	
	private VentanaAlmacenero aT;
	private  List<GrupoProducto> productosEnFalta;
	private ResourceManager manager;

	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup grupoNotificacion = new ButtonGroup();
	private TextArea txtNotificacion;

	private void localizar(){
		
	}

	/**
	 * Create the dialog.
	 */
	//Mï¿½todo para redactar el miniinforme del area de texto.
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
	
	public VentanaNotificacion(VentanaAlmacenero aT, List<GrupoProducto> productosEnFalta,String idPedido, String idAlmacenero) {
		this.aT=aT;
		this.productosEnFalta=productosEnFalta;
		manager = ResourceManager.getResourceManager();
		setTitle(manager.getString("notificacionTitulo"));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			txtNotificacion = new TextArea();
			txtNotificacion.setEditable(false);
			contentPanel.add(txtNotificacion);
			txtNotificacion.setText(redactar());
		}
		{
			JPanel pnBotones = new JPanel();
			FlowLayout fl_pnBotones = (FlowLayout) pnBotones.getLayout();
			fl_pnBotones.setAlignment(FlowLayout.RIGHT);
			contentPanel.add(pnBotones, BorderLayout.SOUTH);
			{
				JButton btnAceptar = new JButton(manager.getString("aceptar"));
				btnAceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							ConsultasMyShop.crearIncidencia(idPedido, idAlmacenero, txtNotificacion.getText());
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						dispose();
					}
				});
				pnBotones.add(btnAceptar);
			}
			{
				JButton btnCancelar = new JButton(manager.getString("cancelar"));
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				pnBotones.add(btnCancelar);
			}
		}
		{
			JPanel panelOpciones = new JPanel();
			contentPanel.add(panelOpciones, BorderLayout.NORTH);
			{
				JCheckBox checkFaltaProd = new JCheckBox("Falta de Productos");
				checkFaltaProd.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						txtNotificacion.setText(redactar());
						txtNotificacion.setEditable(false);
					}
				});
				grupoNotificacion.add(checkFaltaProd);
				checkFaltaProd.setSelected(true);
				panelOpciones.add(checkFaltaProd);
			}
			{
				JCheckBox checkOtrosMotiv = new JCheckBox("Otros Motivos");
				checkOtrosMotiv.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						txtNotificacion.setEditable(true);
						txtNotificacion.setText("Por favor, escriba aquí los motivos por los cuales no se peude acabar la orden de trabajo.");
					}
				});
				grupoNotificacion.add(checkOtrosMotiv);
				panelOpciones.add(checkOtrosMotiv);
			}
		}
	}

}
