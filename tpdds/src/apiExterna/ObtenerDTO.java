package apiExterna;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class ObtenerDTO {
  
  public ArrayList<CentroDTO> listaCentroDTO;
  
  public ArrayList<CentroDTO> agregarCentroDTO(CentroDTO centroDTO) {
  	this.listaCentroDTO.add(centroDTO);
	return this.listaCentroDTO;
  }
		
  public ArrayList<CentroDTO> obtenerCentros(String parametro) throws InterruptedException{
  	ArrayList<CentroDTO> centroDTOEncontrado = new ArrayList<CentroDTO>();
        	for (CentroDTO centroDTO : this.listaCentroDTO){
        		if((this.buscarPorCalle(parametro))||(this.buscarPorZona(parametro))){
            			centroDTOEncontrado.add(centroDTO);
          		}
        	}
        	Thread.sleep(10000);
      	return centroDTOEncontrado;
  }
  
  public boolean buscarPorCalle(String calle){
  	boolean encontrado = false;
  	for (CentroDTO centroDTO : this.listaCentroDTO){
      		if(calle.toUpperCase().equals(centroDTO.getDomicilio().toUpperCase())){
        		encontrado = true;
      		}
    	}
    	return encontrado;
  }
  
  public boolean buscarPorZona(String zona){
	  boolean encontrado = false;
	  for (CentroDTO centroDTO : this.listaCentroDTO){
		  HashSet<String> listaZonasCentroDTO = centroDTO.getZonas();

		  if(listaZonasCentroDTO.contains(zona.toUpperCase()))
				  {
			  encontrado = true;
				  }

	  }
	  return encontrado;
  }
}
    
