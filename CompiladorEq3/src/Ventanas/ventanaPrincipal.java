package Ventanas;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class ventanaPrincipal extends JFrame implements ActionListener {
	private static final long serialVersionUID = -6923555055223640562L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnAnalisis;
	private JMenuItem mntmThomson;
	private JMenuItem mntmConjuntos;
	private JMenuItem mntmALexico;
	public static JDesktopPane escritorio;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaPrincipal frame = new ventanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ventanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 800);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnAnalisis = new JMenu("An\u00E1lisis L\u00E9xico");
		menuBar.add(mnAnalisis);
		
		mntmThomson = new JMenuItem("Algoritmo de Thomson");
		mntmThomson.addActionListener(this);
		mnAnalisis.add(mntmThomson);
		
		mntmConjuntos = new JMenuItem("Construcci\u00F3n de conjuntos");
		mntmConjuntos.addActionListener(this);
		mnAnalisis.add(mntmConjuntos);
		
		mntmALexico = new JMenuItem("An\u00E1lisis L\u00E9xico");
		mntmALexico.addActionListener(this);
		mnAnalisis.add(mntmALexico);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		escritorio = new JDesktopPane();
		contentPane.add(escritorio, BorderLayout.CENTER);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmThomson) {
			actionPerformedMntmThomson(e);
		}
		if (e.getSource() == mntmConjuntos) {
			actionPerformedMntmConjuntos(e);
		}
		if (e.getSource() == mntmALexico) {
			actionPerformedMntmALexico(e);
		}
	}
	protected void actionPerformedMntmThomson(ActionEvent e) {
		String alTh=subVentanaThompson.alTh;
		try {
			if(alTh==null) {
				subVentanaThompson thomson = new subVentanaThompson();
				escritorio.add(thomson);
				thomson.setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(null, "Ya est� activo");
			}
		}catch(Exception e1) {
			
		}
	}
	protected void actionPerformedMntmConjuntos(ActionEvent e) {
		String con=subVentanaConjuntos.conjuntos;
		try {
			if(con==null) {
				subVentanaConjuntos conjuntos = new subVentanaConjuntos();
				escritorio.add(conjuntos);
				conjuntos.setVisible(true);
			}
			else{
				JOptionPane.showMessageDialog(null, "Ya est� activo");
			}
		}catch(Exception e2) {
			
		}
	}
	protected void actionPerformedMntmALexico(ActionEvent e) {
		String analizador=guiAnalizador.guiAnalisis;
		try {
			if(analizador==null) {
				guiAnalizador analisis = new guiAnalizador();
				escritorio.add(analisis);
				analisis.setVisible(true);
				
			}
			else {
				JOptionPane.showMessageDialog(null, "Ya est� activo");
			}
		}catch(Exception e3) {
				
		}
	}
}
