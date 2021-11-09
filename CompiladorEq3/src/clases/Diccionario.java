package clases;

import java.util.ArrayList;

public class Diccionario{
    private ArrayList<String> PalabraReservada = new ArrayList<>();
    
    public ArrayList<String> getPalabraReservada() {
		return PalabraReservada;
	}

	public void setPalabraReservada(ArrayList<String> palabraReservada) {
		PalabraReservada = palabraReservada;
	}

	public Diccionario(){
		PalabraReservada.add(",");
        PalabraReservada.add("=");
        PalabraReservada.add("+");
        PalabraReservada.add("(");
        PalabraReservada.add(")");
        PalabraReservada.add("{");
        PalabraReservada.add("}");
        PalabraReservada.add("int");
        PalabraReservada.add("Char");
        PalabraReservada.add("-");
        PalabraReservada.add("*");
        PalabraReservada.add("while");
        PalabraReservada.add("for");
        PalabraReservada.add("do");
        PalabraReservada.add("float");
        PalabraReservada.add("long");
        PalabraReservada.add("main");
        PalabraReservada.add("return");
        PalabraReservada.add("!");
        PalabraReservada.add("|");
        PalabraReservada.add("<");
        PalabraReservada.add(">");
        PalabraReservada.add("&");
        PalabraReservada.add("if");
        PalabraReservada.add("sizeof");
        PalabraReservada.add("else");
        PalabraReservada.add("double");
        PalabraReservada.add("switch");
        PalabraReservada.add("while");
        PalabraReservada.add("case");
        PalabraReservada.add("break");
        PalabraReservada.add("printf");
        PalabraReservada.add(";");
        PalabraReservada.add("scanf");
        PalabraReservada.add("[");
        PalabraReservada.add("]");
        PalabraReservada.add("void");
        PalabraReservada.add("unsigned");
        PalabraReservada.add("long");
        PalabraReservada.add("short");
        PalabraReservada.add("signed");
        PalabraReservada.add("const");
        PalabraReservada.add("volatile");
        PalabraReservada.add("#");
        PalabraReservada.add("include");
        PalabraReservada.add("stdio");
        PalabraReservada.add("?");
        PalabraReservada.add("/");
        PalabraReservada.add("default");
        PalabraReservada.add("getch");
        PalabraReservada.add("stdin");
        PalabraReservada.add("putc");
        PalabraReservada.add("gets");
        PalabraReservada.add("fgets");
        PalabraReservada.add("fputs");
        PalabraReservada.add("fscanf");
        PalabraReservada.add("fread");
        PalabraReservada.add("fwrite");
        PalabraReservada.add("fprintf");
        PalabraReservada.add("FILE");
        PalabraReservada.add("argv");
        PalabraReservada.add("buffer");
        PalabraReservada.add("rewind");
        PalabraReservada.add("fseek");
        PalabraReservada.add("malloc");
        PalabraReservada.add("struct");
        PalabraReservada.add("NULL");
        PalabraReservada.add("exit");
        PalabraReservada.add("free");
        PalabraReservada.add("%");
        PalabraReservada.add("string");
        PalabraReservada.add("strcmp");
        PalabraReservada.add("strcpy");
        PalabraReservada.add("strlen");
        PalabraReservada.add("define");
    }
}