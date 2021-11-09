package clases;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeSet;

public class Automata implements Serializable{
	private static final long serialVersionUID = -5667415587097018458L;
	public static String EPSILON = "ε";
    public static char EPSILON_CHAR = EPSILON.charAt(0);
	private Estado inicial = new Estado(null);
    private final ArrayList<Estado> aceptacion;
    private final ArrayList<Estado> estados;
    private TreeSet<String> alfabeto;
    private String tipo;
    private String[] Expre;
    private String lenguajeR;

    public Automata(){
        this.estados = new ArrayList<Estado>();
        this.aceptacion = new ArrayList<Estado>();
        this.alfabeto = new TreeSet<>();
        this.Expre = new String[3];
    } 

    public Estado getInicial() {
        return this.inicial;
    }

    public void setInicial(Estado inicial) {
        this.inicial = inicial;
    }
    
    public ArrayList<Estado> getEstadosAceptacion(){
        return aceptacion;
    }

    public void addEstadosAceptacion(Estado fin) {
        this.aceptacion.add(fin);
    }

    public ArrayList<Estado> getEstados() {
        return estados;
    }

    public Estado getEstados(int index){
        return estados.get(index);
    }

    public void addEstados(Estado estado) {
        this.estados.add(estado);
    }

    public TreeSet<String> getAlfabeto() {
        return alfabeto;
    }
     // Metodo para crear alfabeto
    public void crearAlfabeto(String[] Expre, String expre) {
    	
        for (int indiceActual = 0; indiceActual < Expre.length; indiceActual++){
            if (Expre[indiceActual] != "|" && Expre[indiceActual] != "." && Expre[indiceActual] != "*" && Expre[indiceActual] != "+" && Expre[indiceActual] != "?" && Expre[indiceActual] != EPSILON)
            	this.alfabeto.add(Expre[indiceActual]);
        }
        for (Character ch: expre.toCharArray()){
            if (ch != '|' && ch != '.' && ch != '*' && ch != '+' && ch != '?' && ch != EPSILON_CHAR && ch != 'L' && ch != 'D')
                this.alfabeto.add(Character.toString(ch));
        }
    }

    public void setAlfabeto(TreeSet<String> alfabeto){
        this.alfabeto=alfabeto;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public String getTipo(){
        return this.tipo;
    }

    public String[] getResultadoExpre() {
        return Expre;
    }

    public void addResultadoExpre(int key, String value) {
        this.Expre[key] = value;
    }
    
    public void reverse(){
    	String revertida = "";
    	for(int i = this.lenguajeR.length() - 1; i >= 0; i--){
    		revertida += this.lenguajeR.charAt(i);
    	}
    	this.lenguajeR = revertida;
    }

    // Metodo para imprimir los estados del automata y sus transiciones
    public void impresionAutomata(){
        System.out.println("|------------------------		|" + this.tipo + "		-----------------|\n");
        System.out.println("|Alfabeto				|" + this.alfabeto + "|\n"
        + "|Estado inicial				|" + this.inicial + "|\n"
        + "Conjutos de estados de aceptacion	|" + this.aceptacion + "|\n"
        + "|Conjunto de Estados			|" + this.estados.toString() + "|\n"
        + "Conjunto de transiciones ");
        System.out.println(this.lenguajeR + this.lenguajeR.length());
        System.out.println("|---------------------------------------------------------------------|");
        for(int i = 0; i < this.estados.size(); i++ ){
            ArrayList<Transicion> trans = this.estados.get(i).getTransiciones();
            System.out.println("Estado: " + i);
            for(Transicion tmp : trans){
                System.out.println("Caracter " + tmp.getSimbolo() + " Transiciones:" + tmp);
            }
            System.out.println("");
        }
    }
    public String cadenaAutomata(String leng) {
    	String cad;
    	cad="|------------------------		|" + this.tipo + "|		-----------------|\r\n";
    	cad+="|Alfabeto				|" + this.alfabeto + "|\r\n"
    			+ "|Estado inicial				|" + this.inicial + "|\r\n"
    	        + "|Conjutos de estados de aceptacion		|" + this.aceptacion + "|\r\n"
    	        + "|Conjunto de Estados			|" + this.estados.toString() + "|\r\n";
    	return cad;
    }
    public String getLenguajeR() {
        return lenguajeR;
    }

    public void setLenguajeR(String lenguajeR) {
        this.lenguajeR = lenguajeR;
    }
}
