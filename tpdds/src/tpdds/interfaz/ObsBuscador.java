package tpdds.interfaz;

public class ObsBuscador {
	private String tipo;
	private String content;
	
	public ObsBuscador(String tipo, String content) {
		super();
		this.tipo = tipo;
		this.content = content;
	}
	public String getTipo() {
		return tipo;
	}
	public String getContent() {
		return content;
	}
	
	
}
