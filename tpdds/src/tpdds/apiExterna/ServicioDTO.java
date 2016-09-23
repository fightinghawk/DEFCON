package tpdds.apiExterna;

import java.util.ArrayList;

import tpdds.apiExterna.RangoServicioDTO;

public class ServicioDTO {

  private String nombreServicio;
  private ArrayList<RangoServicioDTO> servicios;

  public ServicioDTO(String suNombreServicio){
    this.nombreServicio = suNombreServicio;
    servicios = new ArrayList<>();
  }
  
  public String getNombreServicio(){
    return this.nombreServicio;
  }
  
  public void setNombreServicio(String nombreServicio){
   this.nombreServicio = nombreServicio;
  }
  
  public ArrayList<RangoServicioDTO> getRangoServicios(){
    return this.servicios;
  }
  
  public void agregarRangoServicio(RangoServicioDTO rangoServicio){
    this.servicios.add(rangoServicio);
  }
  
}
  
