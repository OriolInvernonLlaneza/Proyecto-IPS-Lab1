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
	private JButton btnGenerar;
	
	Almacenero almacenero;
	DialogoUltimaComprobacion dialogoUltimaComprobacion;
	private JScrollPane scInstrucciones;
	private JTextArea txtInstrucciones;
	private ResourceManager manager;
	
	private void localizar(){
		txtInstrucciones.setText(manager.getString("instrucciones_empaquetado"));
		btnGenerar.setText(manager.getString("boton_generar"));
	}
	

	/**
	 * Create the dialog.
	 */
	public DialogoGenerarPaquete(DialogoUltimaComprobacion dialogoUltimaComprobacion, Almacenero almacenero) {
		this.dialogoUltimaComprobacion = dialogoUltimaComprobacion;
		manager = ResourceManager.getResourceManager();
		setModal(true);
		setResizable(false);
		this.almacenero = almacenero;
		setBounds(100, 100, 600, 360);
		getContentPane().setLayout(null);
		getContentPane().add(getBtnGenerar());
		getContentPane().add(getScInstrucciones());
		
		localizar();

	}
	private JButton getBtnGenerar() {
		if (btnGenerar == null) {
			btnGenerar = new JButton();
			btnGenerar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//try {
						//almacenero.empaquetar(almacenero.getOrdenDeTrabajoActual());
						JOptionPane.showMessageDialog(null, manager.getString("elementos_impresos"));
						dispose();
						dialogoUltimaComprobacion.dispose();
					//} catch (SQLException e1) {
						//JOptionPane.showMessageDialog(null, manager.getString("error_empaquetado"));
					//}
					
				}
			});
			btnGenerar.setBounds(203, 252, 211, 34);
		}
		return btnGenerar;
	}
	private JScrollPane getScInstrucciones() {
		if (scInstrucciones == null) {
			scInstrucciones = new JScrollPane();
			scInstrucciones.setBounds(10, 11, 574, 192);
			scInstrucciones.setViewportView(getTxtInstrucciones());
		}
		return scInstrucciones;
	}
	private JTextArea getTxtInstrucciones() {
		if (txtInstrucciones == null) {
			txtInstrucciones = new JTextArea();
			txtInstrucciones.setEditable(false);
			txtInstrucciones.setLineWrap(true);
			txtInstrucciones.setWrapStyleWord(true);
		}
		return txtInstrucciones;
	}
}
