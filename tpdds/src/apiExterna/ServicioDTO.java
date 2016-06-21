package apiExterna;

import java.util.ArrayList;

public class ServicioDTO {

  private String nombreServicio;
  private ArrayList<RangoServiciosDto> servicios;

  public ServicioDTO(String suNombreServicio){
    this.nombreServicio = suNombreServicio;
    servicios = new ArraList<>();
  }
  
  public String getNombreServicio(){
    return this.nombreServicio;
  }
  
  public void setNombreServicio(String nombreServicio){
   this.nombreServicio = nombreServicio;
  }
  
  public ArraList<RangoServiciosDTO> getRangoServicios(){
    return this.servicios;
  }
  
  public void agregarRangoServicio(RangoServicioDTO rangoServicio){
    this.servicios.add(rangoServicio);
  }
  
}
  
