package clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AnalizadorLex {
	//private PalbrasKey diccionario;
	private ArrayList<TablaLex> analisis= new ArrayList<>();
	private ArrayList<TablaLex> errores = new ArrayList<>();
	private ArrayList<Automata> autoAnalisis = new ArrayList<>();
	private HashMap<String,Pattern> patrones = new HashMap<>();
	private Diccionario palabrasReserv;
	
	public AnalizadorLex(){
		establecePatrones();
	}
	
	public AnalizadorLex(Diccionario palabrasReserv2){
		this.palabrasReserv =  palabrasReserv2;
		establecePatrones();
	}
	/**
	 * Metodo que nos genera la tabla de tokens y la tabla de errores
	 * @param codigo, recibe las lineas de codigo escrito
	 * 
	 */
	public void generarTabla(ArrayList<String> codigo){
		int numLinea = 1;
		//Al ser codigo un array de strings leera linea por linea
		for(String linea: codigo){
			System.out.println();
			System.out.println(linea);
			//Almacenara todas las palabras que contenga la linea de codigo en un arraylist
			ArrayList<String> palabras = palabrasxLinea(linea);
			//Recorrera palabra a palabra
			for(String pl : palabras){
				System.out.println(pl);
				String[] alfa = pl.split("");
				//Creara un automata finito no determinista con la palabra que encuentre
				if(oper(numLinea, pl))
					break;
				System.out.println(pl);
				convertiraPostfija converse = new convertiraPostfija();
				String erPl = converse.convertirPostfija(pl);
				algoritmoThompson noPl = new algoritmoThompson(erPl);
				noPl.constructor(alfa);
				Automata afnPl = noPl.getAfn();
				//crea un automata finito determinista que se guardara en un arreglo
				algoritmoConstrucciónConjuntos detPl = new algoritmoConstrucciónConjuntos();
				detPl.conversionAFN(afnPl);
				Automata afdPl = detPl.getAfd();
				//recorrera nuestro arreglo de palabras reservadas 
				boolean band = false;
				String result="";
				for( String tReserv : palabrasReserv.getPalabraReservada()){
					//si nuestro automata genero una palabra reservada entonces la agregaremos a la tabla de tokens y a la lista de automatas
					//System.out.println(afdPl.getLenguajeR());
					//System.out.println(tReserv);
					//System.out.println();
					if(tReserv.contentEquals(afdPl.getLenguajeR())){
						band = true;
						result = tReserv;
						break;
					}	
				}
				if(band){
					TablaLex tablaT = new TablaLex(numLinea, result, afdPl.getLenguajeR());
					afdPl.setTipo(result); //le asignamos que tipo de palabra ser� nuestro automata
					analisis.add(tablaT); // agregamos elementos a la tabla
					autoAnalisis.add(afdPl); //agregamos automata
				}
				else{
					palabrasNoR(numLinea, afdPl); //llamamos a palabras no reservadas
				}
				
			}
			numLinea++;
		}
	}
	
	/**
	 * Metodo para ver si la expresion regular es un digito, cadena, caracter etc y si no es agregar a tabla de errores
	 * @param numLinea
	 * @param afd
	 */
	public void palabrasNoR(int numLinea, Automata afd){
		//recorre el mapa de patrones
		HashMap.Entry<String,Pattern> find = null;
		boolean b = false;
		for(HashMap.Entry<String,Pattern> entry : patrones.entrySet()){
			b=false;
			//ingresa la expresion regular para ver si encuentra algun patron
			Matcher m = entry.getValue().matcher(afd.getLenguajeR());
			//System.out.println(entry.getValue());
			b = m.matches();
			if(b){
				find = entry;
				//System.out.println(find.getKey());
				//lo agrega con el tipo de dato que va a ser
				TablaLex tablaT = new TablaLex(numLinea, find.getKey(), afd.getLenguajeR());
				afd.setTipo(find.getKey());
				analisis.add(tablaT);
				autoAnalisis.add(afd);
				break;
			}
			
		}
		if(!b){
			// si no se econtro ni en las palabras reservadas ni en las no reservadas sale y agregar a tabla de errores
			TablaLex tablaE = new TablaLex(numLinea,"errorLex", afd.getLenguajeR());
			errores.add(tablaE);
		}
		
	}
	
	/**
	 * Metodo para extraer todas las palabras de la linea sin contar espacios
	 * @param linea
	 * @return
	 */
	public ArrayList<String> palabrasxLinea(String linea){
		ArrayList<String> palabras = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(linea);
		while(st.hasMoreTokens()){
			String pTemp = st.nextToken();
			System.out.println(pTemp);
			palabras.add(pTemp);
		}
		return palabras;
	}
	
	public void setPalabrasReserv(Diccionario palabrasReserv){
		this.palabrasReserv = palabrasReserv;
	}
	
	/**
	 * metodo para establecer patrones de busqueda
	 */
	public void establecePatrones(){
		this.patrones.put("digito",Pattern.compile("\\d"));
		this.patrones.put("numero",Pattern.compile("\\d+"));
		this.patrones.put("ident",Pattern.compile("[a-zA-Z][a-zA-Z_0-9]*"));
		this.patrones.put("cadena",Pattern.compile("\"[a-zA-Z_0-9]+\""));
		this.patrones.put("caracter",Pattern.compile("\'.\'"));
		this.patrones.put("float",Pattern.compile("\\d+(,)\\d+"));
	 }
	
	public boolean oper(int id, String cad){
		boolean res = false;
		TablaLex operacion = new TablaLex();
		//System.out.println();
		//System.out.println(cad);
		//System.out.println();
		switch(cad){
			case "+":
				operacion.setId(id);
				operacion.setToken("operador");
				operacion.setLexema("+");
				analisis.add(operacion);
				res  = true;
				break;
			case "*":
				operacion.setId(id);
				operacion.setToken("operador");
				operacion.setLexema("*");
				analisis.add(operacion);
				res  = true;
				break;
			case "?":
				operacion.setId(id);
				operacion.setToken("operador");
				operacion.setLexema("?");
				analisis.add(operacion);
				res  = true;
				break;
			case "(":
				operacion.setId(id);
				operacion.setToken("parentesis A");
				operacion.setLexema("(");
				analisis.add(operacion);
				res  = true;
				break;
			case ")":
				operacion.setId(id);
				operacion.setToken("parentesis C");
				operacion.setLexema(")");
				analisis.add(operacion);
				res  = true;
				break;
		}
		return res;
	}

	public ArrayList<TablaLex> getAnalisis() {
		return analisis;
	}

	public void setAnalisis(ArrayList<TablaLex> analisis) {
		this.analisis = analisis;
	}

	public ArrayList<TablaLex> getErrores() {
		return errores;
	}

	public void setErrores(ArrayList<TablaLex> errores) {
		this.errores = errores;
	}

	public ArrayList<Automata> getAutoAnalisis() {
		return autoAnalisis;
	}

	public void setAutoAnalisis(ArrayList<Automata> autoAnalisis) {
		this.autoAnalisis = autoAnalisis;
	}

	public HashMap<String, Pattern> getPatrones() {
		return patrones;
	}

	public void setPatrones(HashMap<String, Pattern> patrones) {
		this.patrones = patrones;
	}

	public Diccionario getPalabrasReserv() {
		return palabrasReserv;
	}
	
}
