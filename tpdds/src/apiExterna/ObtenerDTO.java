package apiExterna;

import java.util.ArrayList;

public abstract class ObtenerDTO implements CentroDTO{
  
  public ArrayList<CentroDTO> listaCentroDTO;
  
  public ArrayList<CentroDTO> agregarCentroDTO(CentroDTO centroDTO) {
		this.listaCentroDTO.add(centroDTO);
		return this.listaCentroDTO;
	}
		
  public ArrayList<CentroDTO> obtenerCentros(String parametro){
      CentroDTO centroDTOEncontrado = null;
        for (CentroDTO centroDTO : this.listaCentroDTO){
          if((this.buscarPorCalle(parametro))||(this.buscarPorZona(parametro))){
            centroDTOEncontrado = centroDTO;
          }
        }
      return centroDTOEncontrado;
  }
  
  public boolean buscarPorCalle(String calle){
    boolean encontrado = false;
    for (CentroDTO centroDTO : this.listaCentroDTO){
      if(calle.toUpperCase().equals(centroDTO.getDomicilio().toUpperCase())){
        encontrado = true;
      }
    }
    return econtrado;
  }
  
  public boolean buscarPorZona(String zona){
			boolean encontrado = false;
			for (CentroDTO centroDTO : this.listaCentroDTO){
		    ArraList<String> listaZonasCentroDTO = CentroDTO.getZonas();
		      for (int pos=0 : listaZonasCentroDTO){
    			  if(listaZonaCentroDTO.get(pos).toUpperCase.equals(zona)){
    				  encontrado = true;
    				}
    		 }
    	}
    	return encontrado;
  }
    
