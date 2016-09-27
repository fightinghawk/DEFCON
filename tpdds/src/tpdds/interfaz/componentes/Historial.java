package tpdds.interfaz.componentes;

import java.util.Date;

public class Historial {
	
	

	private String usuarios_user_id;
	private String cri_tipo;
	private String cri_contenido;
	private Integer resultados;

	private String fecha;
	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the usuarios_user_id
	 */
	public String getUsuarios_user_id() {
		return usuarios_user_id;
	}

	/**
	 * @param usuarios_user_id the usuarios_user_id to set
	 */
	public void setUsuarios_user_id(String usuarios_user_id) {
		this.usuarios_user_id = usuarios_user_id;
	}

	/**
	 * @return the cri_tipo
	 */
	public String getCri_tipo() {
		return cri_tipo;
	}

	/**
	 * @param cri_tipo the cri_tipo to set
	 */
	public void setCri_tipo(String cri_tipo) {
		this.cri_tipo = cri_tipo;
	}

	/**
	 * @return the cri_contenido
	 */
	public String getCri_contenido() {
		return cri_contenido;
	}

	/**
	 * @param cri_contenido the cri_contenido to set
	 */
	public void setCri_contenido(String cri_contenido) {
		this.cri_contenido = cri_contenido;
	}

	/**
	 * @return the resultados
	 */
	public Integer getResultados() {
		return resultados;
	}

	/**
	 * @param resultados the resultados to set
	 */
	public void setResultados(Integer resultados) {
		this.resultados = resultados;
	}

	public String getCriteriosShow()
	{
		String resultado = null;
		String criterio_tipo = "Tipo: " +  this.cri_tipo;
		String criterio_contenido =  " Contenido: "+ this.cri_contenido;
		resultado = (new StringBuilder()).append(criterio_tipo).append(criterio_contenido).toString();
		
		return resultado;
	}

}
