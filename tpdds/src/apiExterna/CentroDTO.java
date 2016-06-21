package apiExterna;

import java.util.ArrayList;
import apiExterna.ServiciosDTO;

public class CentroDTO {
  
  private int numeroComuna;
  private HashSet<String> zonas;
  private String nombreDirector;
  private String domicilio;
  private String telefono;
  private ArrayList<ServiciosDTO> listaServicios;
  
  public CentroDTO(int numComuna, HasSet<String> zonas, String nomDir, String domicilio, String tel, ArrayList<ServiciosDTO> listaServicios){
    this.numeroComuna = numComuna;
    this.nombreDirector = nomDir;
    this.domicilio = domicilio;
    this.telefono = tel;
    this.zonas = zonas;
    this.listaServicios = listaServicios;
  }
  
  public int getComuna(){
    return this.numeroComuna;
  }
  public void setComuna(int numeroComuna){
    this.numeroComuna = numeroComuna;
  }
  
  public HashSet<String> getZonas(){
    return this.zonas;
  }
  public void setZonas(HashSet<String> <onas){
    this.zonas = zonas;
  }
  
  public String getNombreDirector(){
    return this.nombreDirector;
  }
  public void setNombreDirector(String nombreDirector){
    this.nombreDirector = nombreDirector;
  }
  
  public String getDomicilio(){
    return this.domicilio;
  }
  public void setDomicilio(String domicilio){
    this.domicilio = domicilio;
  }
  
  public String getTelefono(){
    return this.telefono;
  }
  public void setTelefono(String telefono){
    this.telefono = telefono;
  }
  
  public ArrayList<ServiciosDTO> getServiciosDTO(){
    return this.listaServicios;
  }
  public void setServiciosDTO(ArrayList<ServiciosDTO> listaServicios){
    this.listaServicios = listaServicios;
  }
