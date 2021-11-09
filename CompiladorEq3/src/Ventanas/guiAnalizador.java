package Ventanas;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import clases.AnalizadorLex;
import clases.Diccionario;
import clases.TablaLex;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;

public class guiAnalizador extends JInternalFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4660006955706767867L;
	/**
	 * Launch the application.
	 */
	public static String guiAnalisis;
	private JPanel panel;
	private JScrollPane panelTablaTokens;
	private JLabel lblNewLabel;
	private JButton btnSeleccionar;
	private JScrollPane panelTablaErrores;
	private JScrollPane scrollPane;
	private JTextPane txtLectura;
	private JLabel lblNewLabel_1;
	
	private String [] tokenColumnas;
	
	private String [] erroresColumnas;
	private JTable tablaTokens;
	private JTable tablaErrores;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//guiAnalizador frame = new guiAnalizador();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public guiAnalizador() {
		setTitle("An\u00E1lisis L\u00E9xico");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		guiAnalisis="guiAnalisis";
		setBounds(0, 0, 891, 720);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		
		panelTablaTokens = new JScrollPane();
		
		panelTablaErrores = new JScrollPane();
		
		scrollPane = new JScrollPane();
		
		lblNewLabel_1 = new JLabel("Resultado:");
		
		lblNewLabel_2 = new JLabel("Tabla de Tokens");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblNewLabel_3 = new JLabel("Tabla de errores");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panelTablaTokens, GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
					.addGap(9)
					.addComponent(panelTablaErrores, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
					.addGap(1))
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 348, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 865, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1)
					.addContainerGap(813, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(168)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED, 315, Short.MAX_VALUE)
					.addComponent(lblNewLabel_3)
					.addGap(168))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_1)
					.addGap(22)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
					.addGap(7)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(lblNewLabel_3))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panelTablaTokens, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panelTablaErrores)
							.addGap(8))))
		);
		
		txtLectura = new JTextPane();
		scrollPane.setViewportView(txtLectura);
		
		lblNewLabel = new JLabel("Escoja el archivo de texto:");
		
		btnSeleccionar = new JButton("Seleccionar archivo");
		btnSeleccionar.addActionListener(this);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(btnSeleccionar)
					.addContainerGap(599, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(btnSeleccionar))
					.addContainerGap(102, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
		
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSeleccionar) {
			actionPerformedBtnSeleccionar(e);
		}
	}
	protected void actionPerformedBtnSeleccionar(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		int seleccion= fc.showOpenDialog(panel);
		BufferedReader br= null;
		ArrayList<String> cadenas = new ArrayList<String>();
		if(seleccion == JFileChooser.APPROVE_OPTION) {
			
			File fichero = fc.getSelectedFile();
			
			try(FileReader fr= new FileReader(fichero)){
				//llenarPanelLectura(fr);
				
				br = new BufferedReader(fr); 
		         String linea,cadena="";
		         Integer i = 0; 
		         while((linea=br.readLine())!=null){
		            i++;
		            cadena+=linea+"\r\n";
		            
		            //System.out.println(linea); 
		            cadenas.add(linea);
				}
		        analizador(cadenas);
		        txtLectura.setText(cadena);
			}catch(IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	private void llenarPanelLectura(FileReader fr) {
		try {
			String cadena="";
			int valor=fr.read();
			while(valor!=-1) {
				cadena= cadena+ (char)valor;
				valor= fr.read();
			}
			txtLectura.setText(cadena);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void analizador(ArrayList<String> expresiones) {
		
        Diccionario palabrasReserv = new Diccionario();
        
        AnalizadorLex analisis = new AnalizadorLex(palabrasReserv);
        analisis.generarTabla(expresiones);
        generarTablas(analisis);
        
	}

	private void generarTablas(AnalizadorLex analisis) {
		
        String [] columnNames= {"No. L�nea","Token","Lexema"};
        
        //llenar la tabla de tokens
        String [][] matrizTokens = null;
        TablaLex tablaTok = new TablaLex();
        int i;
        for(i=0;i<analisis.getAnalisis().size();i++){
        	tablaTok=analisis.getAnalisis().get(i);
        	tokenColumnas=addX(0,tokenColumnas,String.valueOf(tablaTok.getId()));
        	tokenColumnas=addX(1,tokenColumnas,tablaTok.getToken());
        	tokenColumnas=addX(2,tokenColumnas,tablaTok.getLexema());
        	matrizTokens=addXY(i,0,matrizTokens,tokenColumnas);
        }
        
        //llenar la tabla de errores
        String [][] matrizErrores= null;
        TablaLex tablaE= new TablaLex();
        if(analisis.getErrores().size()==0) {
    		String [] sinErrores= {"","",""};
        	matrizErrores=addXY(0,0,matrizErrores,sinErrores);
    	}
        else {
        	for(i=0;i<analisis.getErrores().size();i++){	
        		tablaE=analisis.getErrores().get(i);
        		erroresColumnas=addX(0,erroresColumnas,String.valueOf(tablaE.getId()));
        		erroresColumnas=addX(1,erroresColumnas,tablaE.getToken());
        		erroresColumnas=addX(2,erroresColumnas,tablaE.getLexema());
        		matrizErrores=addXY(i,0,matrizErrores,erroresColumnas);
        	}
        }
        
        //activar las tablas
        tablaTokens=new JTable(matrizTokens,columnNames);
        panelTablaTokens.setViewportView(tablaTokens);
        tablaErrores= new JTable(matrizErrores,columnNames);
        panelTablaErrores.setViewportView(tablaErrores);
	}
	public static String[] addX(int n, String arr[], String x) 
    { 
        int i; 
  
        // create a new array of size n+1 
        String newarr[] = new String[n + 1]; 
        for (i = 0; i < n; i++) 
            newarr[i] = arr[i]; 
  
        newarr[n] = x; 
  
        return newarr; 
    } 
	public static String[][] addXY(int n,int n2, String arr[][], String x[]) 
    { 
        int i; 
  
        // create a new array of size n+1 
        String newarr[][] = new String[n + 1][n2]; 
  
        for (i = 0; i < n; i++) 
            newarr[i] = arr[i]; 
  
        newarr[n] = x; 
  
        return newarr; 
    } 
}

