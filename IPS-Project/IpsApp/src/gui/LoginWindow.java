package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginWindow extends JFrame {

	private ResourceManager manager;
	
	private JPanel contentPane;
	private JPanel panelControl;
	private JPanel panelSeleccion;
	private JButton btnCancelar;
	private JPanel panelUsuario;
	private JPanel panelMayorista;
	private JPanel panelAlmacenero;
	private JPanel panelTitulo;
	private JButton btnUsuario;
	private JButton btnMayorista;
	private JButton btnAlmacenero;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow frame = new LoginWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void localizar(){
		this.setTitle(manager.getString("titulo"));
		
		//Botones
		btnCancelar.setText(manager.getString("cancelar"));
		btnUsuario.setText(manager.getString("usuario"));
		btnMayorista.setText(manager.getString("mayorista"));
		btnAlmacenero.setText(manager.getString("almacenero"));
	}
	
	/**
	 * Create the frame.
	 */
	public LoginWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanelControl(), BorderLayout.SOUTH);
		contentPane.add(getPanelSeleccion(), BorderLayout.CENTER);
		contentPane.add(getPanelTitulo(), BorderLayout.NORTH);
		
		manager = ResourceManager.getResourceManager();
		localizar();
	}

	private JPanel getPanelControl() {
		if (panelControl == null) {
			panelControl = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelControl.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			panelControl.add(getBtnCancelar());
		}
		return panelControl;
	}
	private JPanel getPanelSeleccion() {
		if (panelSeleccion == null) {
			panelSeleccion = new JPanel();
			panelSeleccion.setLayout(new BoxLayout(panelSeleccion, BoxLayout.X_AXIS));
			panelSeleccion.add(getPanelUsuario());
			panelSeleccion.add(getPanelMayorista());
			panelSeleccion.add(getPanelAlmacenero());
		}
		return panelSeleccion;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("");
		}
		return btnCancelar;
	}
	private JPanel getPanelUsuario() {
		if (panelUsuario == null) {
			panelUsuario = new JPanel();
			panelUsuario.setLayout(new BorderLayout(0, 0));
			panelUsuario.add(getBtnUsuario());
		}
		return panelUsuario;
	}
	private JPanel getPanelMayorista() {
		if (panelMayorista == null) {
			panelMayorista = new JPanel();
			panelMayorista.setLayout(new BorderLayout(0, 0));
			panelMayorista.add(getBtnMayorista());
		}
		return panelMayorista;
	}
	private JPanel getPanelAlmacenero() {
		if (panelAlmacenero == null) {
			panelAlmacenero = new JPanel();
			panelAlmacenero.setLayout(new BorderLayout(0, 0));
			panelAlmacenero.add(getBtnAlmacenero());
		}
		return panelAlmacenero;
	}
	private JPanel getPanelTitulo() {
		if (panelTitulo == null) {
			panelTitulo = new JPanel();
		}
		return panelTitulo;
	}
	private JButton getBtnUsuario() {
		if (btnUsuario == null) {
			btnUsuario = new JButton("");
			btnUsuario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					MainWindow window = new MainWindow();
					window.setVisible(true);
				}
			});
		}
		return btnUsuario;
	}
	private JButton getBtnMayorista() {
		if (btnMayorista == null) {
			btnMayorista = new JButton("");
		}
		return btnMayorista;
	}
	private JButton getBtnAlmacenero() {
		if (btnAlmacenero == null) {
			btnAlmacenero = new JButton("");
		}
		return btnAlmacenero;
	}
}
