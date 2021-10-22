package Thompson;

public class algoritmo {

	
	static String entrada = "a*|((a|b)*b)*bb*";
	static char[] aCaracteres = entrada.toCharArray();	

    public static void main(String[] args) {
       	
    	//Boolean esParteDelAlfabeto= letraActual>64 && letraActual<91|| letraActual>96 && letraActual<123;    	
    	
    	ReturnCerradura resultado = tieneCerradura();
    	
    	
    }
    
    class ReturnCerradura{
    	public ReturnCerradura(Boolean tieneCerradura,int posicion) {
    		this.posicion = posicion;
    		this.tieneCerradura = tieneCerradura;
    	}
    	public Boolean tieneCerradura;
    	public int posicion;
    }
    
    public static ReturnCerradura tieneCerradura() {
    	Boolean esCerradura;
    	for(int indiceActual = 0; indiceActual < aCaracteres.length; indiceActual++) {
    		char letraActual = aCaracteres[indiceActual];
    		Boolean esCerraduraDeKleen = letraActual == '*';
    		Boolean esCerraduraPositiva = letraActual == '+';
    		Boolean esCerraduraOpcional = letraActual == '?';    		
    		Boolean esUnion = letraActual == '|';    		
    		esCerradura = esCerraduraOpcional ||esCerraduraPositiva ||esCerraduraDeKleen;
    		if(esCerradura) {
    			System.out.println("Encontre cerradura en la posicion: "+(indiceActual+1));
    			ReturnCerradura resultado = new ReturnCerradura(esCerradura,indiceActual);
    			return resultado;
    		}		
    	}
    	esCerradura = false;
    	ReturnCerradura resultado = new ReturnCerradura(esCerradura,0);
    	return resultado;
    }

    

	
}
