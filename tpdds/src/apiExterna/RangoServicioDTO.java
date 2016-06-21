package apiExterna;

public class RangoServicioDTO {

  private int horarioDesde;
  private int horarioHasta;
  private int minutosDesde;
  private int minutosHasta;
  private int nroDiaSemana;

  public RangoServicioDTO(int suHorarioDesde, int suHorarioHasta, int suMinutosDesde, int suMinutosHasta, int suNroDiaSemana){
    this.horarioDesde = suHorarioDesde;
    this.horarioHasta = suHorarioHasta;
    this.minutosDesde = suMinutosDesde;
    this.minutosHasta = suMinutosHasta;
    this.nroDiaSemana = suNroDiaSemana;
  }
  
  public int getHorarioDesde(){
    return this.horarioDesde;
  }
  
  public void setHorarioDesde(int horarioDesde){
    this.horarioDesde = horarioDesde;
  }
  
  public int getHorarioHasta(){
    return this.horarioHasta;
  }
  
  public void setHorarioHasta(int horarioHasta){
    this.horarioHasta = horarioHasta;
  }
  
  public int getMinutosDesde(){
    return this.minutosDesde;
  }
  
  public void setMinutosDesde(int minutosDesde){
    this.minutosDesde = minutosDesde;
  }
  
  public int getMinutosHasta(){
    return this.minutosHasta;
  }
  
  public void setMinutosHasta(int minutosHasta){
    this.minutosHasta = minutosHasta;
  }
  
  public int getNroDiaSemana(){
    return this.nroDiaSemana;
  }
  
  public void setNroDiaSemana(int nroDiaSemana){
    this.nroDiaSemana = nroDiaSemana;
  }
  }
