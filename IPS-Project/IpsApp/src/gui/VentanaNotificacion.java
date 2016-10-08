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


public class VentanaNotificacion extends JDialog {
	
	private AlmaceneroTrabajo aT;
	private  List<Producto> productosEnFalta;

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			VentanaNotificacion dialog = new VentanaNotificacion();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	private String redactar(){
		Date fecha = new Date();
		StringBuilder sb= new StringBuilder();
		sb.append("Día: ");
		sb.append(fecha);
		sb.append("\n");
		sb.append("------------------------------------------------------------------ \n");
		sb.append("Productos en falta: \n");
		for(Producto producto: productosEnFalta){
			sb.append("Producto: ");
			sb.append(producto.getId());
			sb.append("Nombre: ");
			sb.append(producto.getName());
			sb.append("\n");
		}
		
		return sb.toString();
		
	}
	
	public VentanaNotificacion(AlmaceneroTrabajo aT, List<Producto> productosEnFalta) {
		this.aT=aT;
		this.productosEnFalta=productosEnFalta;
		setTitle("Notificacion");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			TextArea textArea = new TextArea();
			textArea.setEditable(false);
			contentPanel.add(textArea);
			Date fecha = new Date();
			System.out.println (fecha);
			textArea.setText(redactar());
		}
		{
			JPanel panelBotones = new JPanel();
			panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(panelBotones, BorderLayout.SOUTH);
			{
				JButton btnOK = new JButton("OK");
				btnOK.setActionCommand("OK");
				panelBotones.add(btnOK);
				getRootPane().setDefaultButton(btnOK);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.setActionCommand("Cancel");
				panelBotones.add(btnCancelar);
			}
		}
	}

}
