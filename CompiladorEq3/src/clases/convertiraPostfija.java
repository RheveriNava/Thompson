package clases;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class convertiraPostfija {
	
	private final Map<Character,Integer> mapaPrecedencia;
	
	 // Creamos un mapa para poder reconocer la precedencia de cada 
	 // operador y asi ordenarlos cuando realicemos la conversion.
	public convertiraPostfija() {
		Map<Character,Integer> mapa = new HashMap<>();
		mapa.put('(',1);
		mapa.put('|',2);
		mapa.put('.',3);
		mapa.put('?',4);
		mapa.put('*',4);
		mapa.put('+',4);
		mapaPrecedencia=Collections.unmodifiableMap(mapa);
	}
	 //Obtener la precedencia del caracter que enviamos
	private Integer getPrecedencia(Character c) {
		Integer precedencia = mapaPrecedencia.get(c);
		if(precedencia == null) {
			return (Integer)6;
		}
		else {
			return precedencia;
		}	
	}
	 //Cuenta parentesis izquierdos
	private int cuentaParentesisIzq(String er) {
		int cont=0;
		Character c;
		for(int i=0;i<er.length();i++) {
			c=er.charAt(i);
			if(c=='(') {
				cont++;
			}
		}
		return cont;
	}

	 //Cuenta parentesis derechos
	private int cuentaParentesisDer(String er) {
		int cont=0;
		Character c;
		for(int i=0;i<er.length();i++) {
			c=er.charAt(i);
			if(c==')') {
				cont++;
			}
		}
		return cont;
	}

	 //Si a la ER le hacen falta parentesis de algun lado, este metodo se las coloca
	private String balanceoParentesis(String er) {
		int izq=cuentaParentesisIzq(er),der=cuentaParentesisDer(er);
		while(izq!=der) {
			if(izq>der) {
				er+=")";
			}
			else {
				er="("+er;
			}
			izq=cuentaParentesisIzq(er);
			der=cuentaParentesisDer(er);
		}
		return er;
	}
	
	 //Transforma las concatenaciones implicitas (Ej= ab) en explicitas (Ej= a.b)
	public String concatenacionExplicita(String er) {
		/*er=abreviarCerraduraEpsilon(er);
		er=abreviarCerraduraPositiva(er);*/
		String erExplicita = new String();
		List<Character> operadores = Arrays.asList('|','?','+','*');
		List<Character> operadoresBinarios = Arrays.asList('|');
		for(int i=0;i<er.length();i++) {
			Character c1 = er.charAt(i);
			if(i+1 < er.length()) {
				Character c2=er.charAt(i+1);
				erExplicita+=c1;
				if(!c1.equals('(') && !c2.equals(')') && !operadores.contains(c2) && !operadoresBinarios.contains(c1)) {
					erExplicita+='.';
				}
			}
		}
		erExplicita += er.charAt(er.length()-1);
		return erExplicita;
	}
	//Metodo para convertir una expresion infija a posfija
	public  String convertirPostfija(String er) {
		String postfix = new String();
        /*er = abreviarOr(er);
		er = abreviarAnd(er);*/
		er = balanceoParentesis(er); //agrega parentesis si no lo hay
		Stack<Character> stack = new Stack<>();
		String erConcatenacionEx = concatenacionExplicita(er);
		for (Character c : erConcatenacionEx.toCharArray()) {
			switch (c) {
				case '(':
					stack.push(c);
					break;
				case ')':
					while (!stack.peek().equals('(')) {
						postfix += stack.pop();
					}
					stack.pop();
					break;
				default:
					while (stack.size() > 0) {
						Character peekedChar = stack.peek();
						Integer peekedCharPrecedence = getPrecedencia(peekedChar);
						Integer currentCharPrecedence = getPrecedencia(c);
						if (peekedCharPrecedence >= currentCharPrecedence) {
							postfix += stack.pop();
                                                       
						} 
						else{
							break;              
						}
					}
					stack.push(c);
					break;
			}

		}
		while (stack.size() > 0)
			postfix += stack.pop();
		return postfix;
	}
}

