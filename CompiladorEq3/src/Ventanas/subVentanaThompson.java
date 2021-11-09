package Ventanas;

import clases.*;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.event.InternalFrameListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import javax.swing.event.InternalFrameEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class subVentanaThompson extends JInternalFrame implements InternalFrameListener, ActionListener {
	private static final long serialVersionUID = -4182129582341592930L;
	private JTextPane txtLectura;
	private JScrollPane scrollPane;
	private JButton vtnSeleccionar;
	private String erP;
	static String[] partesAlfabeto;
	static List<String> partesExpresion;
	static String[] partesTexto;

	
	private JPanel panel;
	private JLabel lblER;
	private JTextField txtER;
	private JButton btnConvertirER;
	
	private JScrollPane panelTexto;
	private JTextArea textAreaImpresion;
	private JTable table;
	private JScrollPane panelTabla;
	private JLabel lblNewLabelR;
	
	public static String alTh;
	private String [] columnNames;
	private String [] columnas;
    public subVentanaThompson() {
		setResizable(true);
		addInternalFrameListener(this);
		
		alTh="alTh";
		
		setTitle("Algoritmo de Thomson");
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setBounds(0, 0, 891, 720);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		scrollPane = new JScrollPane();
		lblER = new JLabel("Escoja el archivo de texto:");
		
		txtER = new JTextField();
		txtER.setColumns(10);
		txtLectura = new JTextPane();
		scrollPane.setViewportView(txtLectura);
		
		btnConvertirER = new JButton("Convertir");
		btnConvertirER.addActionListener(this);
		vtnSeleccionar = new JButton("Seleccionar archivo");
		vtnSeleccionar.addActionListener(this);
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblER)
					.addGap(8)
					.addComponent(vtnSeleccionar)
					.addContainerGap(599, Short.MAX_VALUE))
			.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGap(600)
					.addComponent(btnConvertirER)
					.addContainerGap(600, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblER)
						.addComponent(vtnSeleccionar))
					.addContainerGap(102, Short.MAX_VALUE))
			.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addGap(50)
						.addComponent(btnConvertirER))
					.addContainerGap(102, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		panelTexto = new JScrollPane();
		
		textAreaImpresion = new JTextArea();
		panelTexto.setViewportView(textAreaImpresion);
		
		panelTabla = new JScrollPane();
		
		lblNewLabelR = new JLabel("Resultado:");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panelTabla, GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panelTexto, GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE)))
					.addGap(21))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabelR, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(779, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabelR)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelTexto, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelTabla, GroupLayout.PREFERRED_SIZE, 394, GroupLayout.PREFERRED_SIZE)
					.addGap(37))
		);
		getContentPane().setLayout(groupLayout);
	}
	protected void acccionSeleccionar(ActionEvent e) {
		int indiceActual,asciiValor;
		byte[] bytes;
		String[] arrayAyuda,arrayExpresionAyuda;
		JFileChooser fc = new JFileChooser(".\\archivos de prueba");
		int seleccion= fc.showOpenDialog(panel);
		BufferedReader br= null;
		if(seleccion == JFileChooser.APPROVE_OPTION) {
			
			File fichero = fc.getSelectedFile();
			
			try(FileReader fr= new FileReader(fichero)){				
				br = new BufferedReader(fr); 
		         String linea,cadena="";
		         Integer i = 0; 
		         while((linea=br.readLine())!=null){
		            i++;
		            cadena+=linea+"\n";
				}
		        partesTexto = cadena.split("\n");
		 		String[] renglonTexto = new String[(partesTexto.length - 1) * 2];
		 		partesAlfabeto = new String[(partesTexto.length - 1)];
		 		arrayExpresionAyuda = partesTexto[partesTexto.length - 1].split(" ");
		 		if(partesTexto.length > 2) {
		 			for(indiceActual = 0; indiceActual < partesTexto.length - 1; indiceActual++) {
		 				arrayAyuda = partesTexto[indiceActual].split("=| ");
		 				if(arrayAyuda.length > 2)
		 					arrayAyuda[1] = arrayAyuda[arrayAyuda.length - 1];
		 				renglonTexto[indiceActual * 2] = arrayAyuda[0];
		 				renglonTexto[indiceActual * 2 + 1] = arrayAyuda[1];
		 				partesAlfabeto[indiceActual] = renglonTexto[indiceActual*2];
		 			}
		 			for(indiceActual = 0; indiceActual < partesAlfabeto.length; indiceActual++) {
		 				bytes = partesAlfabeto[indiceActual].getBytes(StandardCharsets.US_ASCII);
		 				asciiValor = bytes[0];
		 				//System.out.println(asciiValor);
		 				if(asciiValor > 96 && asciiValor < 123) {
		 					asciiValor -= 32;
		 				}
		 				arrayExpresionAyuda[0] = arrayExpresionAyuda[0].replace(partesAlfabeto[indiceActual],Character.toString((char)asciiValor));
		 			}
		 		}else {
		 			partesAlfabeto = partesTexto[0].split(" ");
		 			partesAlfabeto = partesAlfabeto[0].split("");
		 		}
		 		partesExpresion = Arrays.asList(arrayExpresionAyuda[0].split(""));
		        erP = arrayExpresionAyuda[0];
		        /*System.out.println(arrayExpresionAyuda[0]);
		        int ja = 0;
				while(ja < partesAlfabeto.length) {
					System.out.println(partesAlfabeto[ja]);
					ja++;
				}*/
		        txtLectura.setText(cadena);
			}catch(IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	private void generarTabla(Automata afn) {
		// TODO Auto-generated method stub
		String [][] matriz=null;
		int estados=afn.getEstados().size();
		columnNames=addX(0,columnNames,"Estados");
		int i,j;
		for(i=0;i<estados;i++) {
			columnNames=addX(i+1,columnNames,String.valueOf(i));
		}
		for(i=0;i<estados-1;i++) {
			columnas=addX(0,columnas,String.valueOf(i));
			ArrayList<Transicion> trans = afn.getEstados(i).getTransiciones();
			for(j=0;j<estados;j++) {			
				for(Transicion tmp : trans) {
					if(tmp.getFin().getId()==Integer.valueOf(j)) {
						columnas=addX(j+1,columnas,tmp.getSimbolo());
						break;
					}
					else {
						columnas=addX(j+1,columnas,"-");
					}
				}
			}
			matriz=addXY(i,0,matriz,columnas);
			columnas=null;
		}
		
		columnas=addX(0,columnas,String.valueOf(estados-1));
		for(i=0;i<estados;i++) {
			columnas=addX(i+1,columnas,"-");
		}
		
		
		matriz=addXY(estados-1,0,matriz,columnas);
		table = new JTable(matriz,columnNames);
		panelTabla.setViewportView(table);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == vtnSeleccionar) {
			acccionSeleccionar(e);
		}
		if (e.getSource() == btnConvertirER) {
			actionPerformedBtnConvertirER(e);
		}
	}
	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this) {
			internalFrameClosingThis(e);
		}
	}
	@Override
	public void internalFrameClosed(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void internalFrameIconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void internalFrameActivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}
	protected void internalFrameClosingThis(InternalFrameEvent e) {
		dispose();
		alTh=null;
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
	
	protected void actionPerformedBtnConvertirER(ActionEvent e) {
		convertiraPostfija conversion = new convertiraPostfija();
		String auxEP = erP;
		try{
            erP = conversion.convertirPostfija(erP); //convierte la expresion regular a una posfija
        }
        catch(Exception e1){
            System.out.println("La expresion est� mal escrita :( \n");
        }
		String exprecion = new String(erP);
        algoritmoThompson algoritmoThompson = new algoritmoThompson(exprecion); //recibe la expresion regular
        algoritmoThompson.constructor(partesAlfabeto); // construye el automata utilizando el algoritmo de thompson
        Automata afnResultante =  algoritmoThompson.getAfn(); //guarda el automata creado
        String message="Expresion regular ingresada: " + auxEP +"\r\nExpresion regular posfija: "+ erP+"\r\n";
        message += afnResultante.cadenaAutomata(afnResultante.getLenguajeR());
      
        textAreaImpresion.setText(message);
        generarTabla(afnResultante);
        
	}
}



