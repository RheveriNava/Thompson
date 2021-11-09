package Ventanas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import clases.algoritmoConstrucciónConjuntos;
import clases.algoritmoThompson;
import clases.Automata;
import clases.convertiraPostfija;
import clases.Transicion;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

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
public class subVentanaConjuntos extends JInternalFrame implements InternalFrameListener, ActionListener {
	private static final long serialVersionUID = -7820329215579170824L;
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
	
	public static String conjuntos;
	private String [] columnNames;
	private String [] columnas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//guiConjuntos frame = new guiConjuntos();
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
	public subVentanaConjuntos() {
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		addInternalFrameListener((InternalFrameListener) this);
		setTitle("Construcci\u00F3n de Conjuntos");
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
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == vtnSeleccionar) {
			acccionSeleccionar(e);
		}
		if (e.getSource() == btnConvertirER) {
			actionPerformedBtnConvertirConjuntos(e);
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
		conjuntos=null;
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
		        txtLectura.setText(cadena);
			}catch(IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	protected void actionPerformedBtnConvertirConjuntos(ActionEvent e) {
		
		//String er=textConjuntos.getText();
		String er = erP;
		String auxEP = erP;
		convertiraPostfija conversion = new convertiraPostfija();
		String erP = new String();
		try{
            erP = conversion.convertirPostfija(er); //convierte la expresion regular a una forma posfija
        }
        catch(Exception e1){
            System.out.println("La expresion est� mal escrita :( \n");
        }
		algoritmoConstrucciónConjuntos convertidor = new algoritmoConstrucciónConjuntos();
		algoritmoThompson algoritmoThompson = new algoritmoThompson(erP);
        String message=new String();
        
        //System.out.println("Expresion regular ingresada: " + er);
        //System.out.println("Expresion regular posfija: " + erP);
        
        message+="Expresion regular ingresada: " + auxEP+"\r\nExpresion regular posfija: " + erP+"\r\n";
        
        algoritmoThompson.constructor(partesAlfabeto); // construye el automata utilizando el algoritmo de thompson
        Automata afnResultante =  algoritmoThompson.getAfn(); //guarda el automata creado
        
        //afnResultante.impresionAutomata(); //imprime el automata
        
        
        convertidor.conversionAFN(afnResultante);
        Automata afdResultante = convertidor.getAfd();
        message+=afdResultante.cadenaAutomata(afdResultante.getLenguajeR());
        textAreaImpresion.setText(message);
       
        generarTabla(afdResultante);
	}
	private void generarTabla(Automata afd) {
		// TODO Auto-generated method stub
		String [][] matriz=null;
		int estados=afd.getEstados().size();
		columnNames=addX(0,columnNames,"Estados");
		int i,j;
		for(i=0;i<estados;i++) {
			columnNames=addX(i+1,columnNames,String.valueOf(i));
		}
		
		/**
		for(int h=0;h<columnNames.length;h++) {
			System.out.println(columnNames[h]);
		}
		System.out.println("filas");
		*/
		
		for(i=0;i<estados;i++) {
			columnas=addX(0,columnas,String.valueOf(i));
			ArrayList<Transicion> trans = afd.getEstados(i).getTransiciones();
			for(j=0;j<estados;j++) {	
				String simbolo=new String();
				boolean encontrado=false,multiple=false;
				for(Transicion tmp : trans) {
					
					
					/**
					if(tmp.getFin().getId()==Integer.valueOf(j)) {
						simbolo+=tmp.getSimbolo();
						columnas=addX(j+1,columnas,simbolo);
						break;
					}
					else {
						columnas=addX(j+1,columnas,"-");
					}
					*/
						
						if(tmp.getFin().getId()==Integer.valueOf(j)) {
							if(multiple==true) {
								simbolo+=",";
							}
							simbolo+=tmp.getSimbolo();
							encontrado=true;
							multiple=true;
						}
						
						
						
					
				}
				
				if(encontrado==false) {
					simbolo+="-";
				}
				columnas=addX(j+1,columnas,simbolo);
			}
			
			/**
			for(int h=0;h<columnas.length;h++) {
				System.out.println(columnas[h]);
			}
			System.out.println();
			*/
			
			matriz=addXY(i,0,matriz,columnas);
			columnas=null;
		}
		/**
		for(int h=0;h<matriz.length;h++) {
			for(int k=0;k<matriz[h].length;k++) {
				System.out.print(matriz[h][k]);
			}
			System.out.println();
		}
		*/
		
		table = new JTable(matriz,columnNames);
		panelTabla.setViewportView(table);
		
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
