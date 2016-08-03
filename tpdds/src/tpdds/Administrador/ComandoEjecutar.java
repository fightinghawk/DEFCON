package tpdds.Administrador;

import java.io.IOException;
import java.sql.SQLException;

public interface ComandoEjecutar {
	public void ejecutarme() throws IOException, ClassNotFoundException, SQLException;
}
