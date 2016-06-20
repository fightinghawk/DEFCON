package apiExterna;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class jsonHTTP implements apiExterna {

	@Override
	public <T> List<T> obtenerDatos(String fuente, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper jsonMapper = new ObjectMapper();

		URL url = new URL(fuente);

		List<T> listaJson = jsonMapper.readValue(url, new TypeReference<List<T>>() {
		});

		return listaJson;

	}

	public void mostrarLista(@SuppressWarnings("rawtypes") List lista) {
		@SuppressWarnings("rawtypes")
		Iterator it = lista.iterator();
		while (it.hasNext()) {
			Object objeto = it.next();
			System.out.println(objeto);
		}

	}
}
