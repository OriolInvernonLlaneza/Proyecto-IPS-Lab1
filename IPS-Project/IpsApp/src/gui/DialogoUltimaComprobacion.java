package gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;

import javax.swing.JButton;

import logica.Almacenero;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class DialogoUltimaComprobacion extends JDialog {
	
	private Almacenero almacenero;
	private JButton btnComprobar;
	private DialogoGenerarPaquete dialogoGenerarPaquete;
	private DialogoUltimaComprobacion myself = this;
	private JScrollPane scrollPane;
	private JTextArea txtrElPedidoSe;

	/**
	 * Create the dialog.
	 */
	public DialogoUltimaComprobacion(Almacenero almacenero) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);
		this.almacenero = almacenero;
		setBounds(100, 100, 600, 360);
		getContentPane().setLayout(null);
		getContentPane().add(getBtnComprobar());
		getContentPane().add(getScrollPane());
	}
	private JButton getBtnComprobar() {
		if (btnComprobar == null) {
			btnComprobar = new JButton("Comprobar");
			btnComprobar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					boolean productosCorrectos = almacenero.comprobarProductosRecogidos(almacenero.getOrdenDeTrabajoActual());
					if(productosCorrectos) {
						dialogoGenerarPaquete = new DialogoGenerarPaquete(myself, almacenero);
						dialogoGenerarPaquete.setLocationRelativeTo(null);
						dialogoGenerarPaquete.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Los productos recogidos no se corresponden con los de la orden de trabajo");
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
			scrollPane.setViewportView(getTxtrElPedidoSe());
		}
		return scrollPane;
	}
	private JTextArea getTxtrElPedidoSe() {
		if (txtrElPedidoSe == null) {
			txtrElPedidoSe = new JTextArea();
			txtrElPedidoSe.setWrapStyleWord(true);
			txtrElPedidoSe.setLineWrap(true);
			txtrElPedidoSe.setText("El pedido se ha marcado para empaquetar, coloca los productos de la orden de trabajo sobre la cinta transportadora y pulsa el bot\u00F3n 'Comprobar' para revisar que los productos son los correctos.");
		}
		return txtrElPedidoSe;
	}
}
