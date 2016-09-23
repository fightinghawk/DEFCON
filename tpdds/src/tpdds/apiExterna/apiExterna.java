package tpdds.apiExterna;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface apiExterna {
	 public <T> List<T> obtenerDatos(String fuente, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException;

}
