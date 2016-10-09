package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

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
			GridBagLayout gbl_panelSeleccion = new GridBagLayout();
			/*gbl_panelSeleccion.columnWidths = new int[]{131, 141, 151, 0};
			gbl_panelSeleccion.rowHeights = new int[]{209, 0};
			gbl_panelSeleccion.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panelSeleccion.rowWeights = new double[]{0.0, Double.MIN_VALUE};*/
			panelSeleccion.setLayout(gbl_panelSeleccion);
			GridBagConstraints gbc_panelUsuario = new GridBagConstraints();
			gbc_panelUsuario.insets = new Insets(0, 0, 10, 0);
			gbc_panelUsuario.gridx = 1;
			gbc_panelUsuario.gridy = 0;
			panelSeleccion.add(getBtnUsuario(), gbc_panelUsuario);
			GridBagConstraints gbc_panelMayorista = new GridBagConstraints();
			gbc_panelMayorista.insets = new Insets(0, 0, 10, 0);
			gbc_panelMayorista.gridx = 1;
			gbc_panelMayorista.gridy = 1;
			panelSeleccion.add(getBtnMayorista(), gbc_panelMayorista);
			GridBagConstraints gbc_panelAlmacenero = new GridBagConstraints();
			gbc_panelAlmacenero.gridx = 1;
			gbc_panelAlmacenero.gridy = 2;
			panelSeleccion.add(getBtnAlmacenero(), gbc_panelAlmacenero);
		}
		return panelSeleccion;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("");
			btnCancelar.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					windowClosing(true);
				}
			});
		}
		return btnCancelar;
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
			btnUsuario.setPreferredSize(new Dimension(120,33));
			btnUsuario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					MainWindow window = new MainWindow();
					window.setVisible(true);
					windowHiding(false);
				}
			});
		}
		return btnUsuario;
	}
	private JButton getBtnMayorista() {
		if (btnMayorista == null) {
			btnMayorista = new JButton("");
			btnMayorista.setPreferredSize(new Dimension(120, 33));
		}
		return btnMayorista;
	}
	private JButton getBtnAlmacenero() {
		if (btnAlmacenero == null) {
			btnAlmacenero = new JButton("");
			btnAlmacenero.setPreferredSize(new Dimension(120, 33));
			btnAlmacenero.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					VentanaAlmacenero windowA = new VentanaAlmacenero();
					windowA.setVisible(true);
					windowHiding(false);
				}
			});
		}
		return btnAlmacenero;
	}

	private void windowClosing(boolean exit) {
		this.dispose();
		if(exit)
			System.exit(0);
	}
	
	private void windowHiding(boolean hide){
		this.setVisible(hide);
	}
}
