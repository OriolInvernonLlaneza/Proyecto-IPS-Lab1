package gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import logica.Almacenero;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

public class DialogoGenerarPaquete extends JDialog {
	private JButton btnGenerarEtiquetaY;
	
	Almacenero almacenero;
	DialogoUltimaComprobacion dialogoUltimaComprobacion;
	private JScrollPane scrollPane;
	private JTextArea txtrLosProductosSe;

	/**
	 * Create the dialog.
	 */
	public DialogoGenerarPaquete(DialogoUltimaComprobacion dialogoUltimaComprobacion, Almacenero almacenero) {
		this.dialogoUltimaComprobacion = dialogoUltimaComprobacion;
		setModal(true);
		setResizable(false);
		this.almacenero = almacenero;
		setBounds(100, 100, 600, 360);
		getContentPane().setLayout(null);
		getContentPane().add(getBtnGenerarEtiquetaY());
		getContentPane().add(getScrollPane());

	}
	private JButton getBtnGenerarEtiquetaY() {
		if (btnGenerarEtiquetaY == null) {
			btnGenerarEtiquetaY = new JButton("Generar etiqueta y albaran");
			btnGenerarEtiquetaY.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//try {
						//almacenero.empaquetar(almacenero.getOrdenDeTrabajoActual());
						JOptionPane.showMessageDialog(null, "Etiqueta y albaran impresos, la aplicación volverá a la pantalla principal");
						dispose();
						dialogoUltimaComprobacion.dispose();
					//} catch (SQLException e1) {
						//JOptionPane.showMessageDialog(null, "Ha habido un error al empaquetar");
					//}
					
				}
			});
			btnGenerarEtiquetaY.setBounds(203, 252, 211, 34);
		}
		return btnGenerarEtiquetaY;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 11, 574, 192);
			scrollPane.setViewportView(getTxtrLosProductosSe());
		}
		return scrollPane;
	}
	private JTextArea getTxtrLosProductosSe() {
		if (txtrLosProductosSe == null) {
			txtrLosProductosSe = new JTextArea();
			txtrLosProductosSe.setLineWrap(true);
			txtrLosProductosSe.setWrapStyleWord(true);
			txtrLosProductosSe.setText("Los productos se corresponden con los de la orden de trabajo. A continuacion, colocalos en un paquete y cierralo. Tras ello, pulsa el boton 'Generar etiqueta y albaran' para que el albaran y la etiqueta se impriman, una vez impresos,  pegalos en el paquete.");
		}
		return txtrLosProductosSe;
	}
}
