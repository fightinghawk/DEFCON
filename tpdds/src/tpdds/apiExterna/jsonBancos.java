package tpdds.apiExterna;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class jsonBancos extends jsonHTTP {

	public List<BancoExterna> FiltrarBancos(String url, String path, String banco, String servicio)
			throws JsonParseException, JsonMappingException, IOException, InterruptedException {
		
		String pathfinal = "/".concat(path);
		String pathbanco = "?banco=".concat(banco.replace(" ", "%20"));
		String pathservicio = "&servicio=".concat(servicio);
		
		String fuente = url.concat(pathfinal.concat(pathbanco.concat(pathservicio)));
		
		Thread.sleep(1000);

		return (this.obtenerDatos(fuente, BancoExterna.class));

	}

}
