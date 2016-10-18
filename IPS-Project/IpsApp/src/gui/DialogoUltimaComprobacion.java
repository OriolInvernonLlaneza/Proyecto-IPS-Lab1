package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import logica.Almacenero;

public class DialogoUltimaComprobacion extends JDialog {
	
	private Almacenero almacenero;
	private JButton btnComprobar;
	private DialogoGenerarPaquete dialogoGenerarPaquete;
	private DialogoUltimaComprobacion myself = this;
	private JScrollPane scrollPane;
	private JTextArea txtResultadoPedido;
	
	private ResourceManager manager;
	
	private void localizar(){
		btnComprobar.setText(manager.getString("comprobar"));
		txtResultadoPedido.setText(manager.getString("pedido_marcado_empaquetar"));
	}

	/**
	 * Create the dialog.
	 */
	public DialogoUltimaComprobacion(Almacenero almacenero) {
		manager = ResourceManager.getResourceManager();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);
		this.almacenero = almacenero;
		setBounds(100, 100, 600, 360);
		getContentPane().setLayout(null);
		getContentPane().add(getBtnComprobar());
		getContentPane().add(getScrollPane());
		
		localizar();
	}
	private JButton getBtnComprobar() {
		if (btnComprobar == null) {
			btnComprobar = new JButton();
			btnComprobar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					boolean productosCorrectos = almacenero.comprobarProductosRecogidos(almacenero.getOrdenDeTrabajoActual());
					if(productosCorrectos) {
						dialogoGenerarPaquete = new DialogoGenerarPaquete(myself, almacenero);
						dialogoGenerarPaquete.setLocationRelativeTo(null);
						dialogoGenerarPaquete.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, manager.getString("productos_no_concuerdan"));
					}
				}
			});
			btnComprobar.setBounds(238, 248, 121, 41);
		}
		return btnComprobar;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 11, 574, 181);
			scrollPane.setViewportView(getTxtResultadoPedido());
		}
		return scrollPane;
	}
	private JTextArea getTxtResultadoPedido() {
		if (txtResultadoPedido == null) {
			txtResultadoPedido = new JTextArea();
			txtResultadoPedido.setWrapStyleWord(true);
			txtResultadoPedido.setLineWrap(true);
		}
		return txtResultadoPedido;
	}
}
