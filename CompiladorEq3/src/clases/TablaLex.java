package clases;

public class TablaLex {
	private int id;
	private String token;
	private String lexema;
		
	public TablaLex(){
		
	}
	public TablaLex(int id, String token, String lexema){
		this.id = id;
		this.token = token;
		this.lexema = lexema;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
			this.id = id;
	}
	public String getToken() {
			return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getLexema() {
		return lexema;
	}
	public void setLexema(String lexema) {
		this.lexema = lexema;
	}
	
	public String toString(){
		return id + "\t" + token + "\t" + lexema + "\n";
	}
			
}
