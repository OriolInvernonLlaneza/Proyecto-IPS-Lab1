package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Producto;

import java.awt.TextArea;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class VentanaNotificacion extends JDialog {
	
	private VentanaAlmacenero aT;
	private  List<Producto> productosEnFalta;
	private ResourceManager manager;

	private final JPanel contentPanel = new JPanel();

	private void localizar(){
		//Notificación
	}

	/**
	 * Create the dialog.
	 */
	//Método para redactar el miniinforme del area de texto.
	private String redactar(){
		Date fecha = new Date();
		StringBuilder sb= new StringBuilder();
		sb.append(manager.getString("dia"));
		sb.append(fecha);
		sb.append("\n");
		sb.append("------------------------------------------------------------------ \n");
		if(productosEnFalta.size()==0){
			sb.append(manager.getString("vacio"));
		}
		else{
		sb.append(manager.getString("productosEnFalta") +  "\n");
			for(Producto producto: productosEnFalta){
				sb.append(manager.getString("producto") + ": ");
				sb.append(producto.getId());
				sb.append(" ");
				sb.append(manager.getString("nombre") + ": ");
				sb.append(producto.getNombre());
				sb.append("\n");
			}
		}
		
		return sb.toString();
		
	}
	
	public VentanaNotificacion(VentanaAlmacenero aT, List<Producto> productosEnFalta) {
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
			TextArea txtNotificacion = new TextArea();
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
	}

}
