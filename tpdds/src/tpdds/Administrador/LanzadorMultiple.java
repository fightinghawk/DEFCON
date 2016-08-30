package tpdds.Administrador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

public class LanzadorMultiple extends Proceso {

	LinkedList<Proceso> lanzador;
	
	public LanzadorMultiple() {
		super("Lanzador multiple");
		this.lanzador = new LinkedList<>();
	}
	
	public void agregarProceso(Proceso exe){
		lanzador.addLast(exe);
	}

	@Override
	public void ejecutarme() throws IOException, ClassNotFoundException, SQLException {
		for (Proceso proceso : lanzador) {
			proceso.ejecutarme();
		}
	}	
}
