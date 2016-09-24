package tpdds.pois;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class LocalesComerciales extends Poi  {
	
	@Column(name="rubro")
	private String rubro;

	/**
	 * @return the rubro
	 */
	public String getRubro() {
		return rubro;
	}

	/**
	 * @param rubro the rubro to set
	 */
	public void setRubro(String rubro) {
		this.rubro = rubro;
	}

	public LocalesComerciales(Poi poi,String rubro){
		super(poi);
		super.setDtype(this.getClass().getSimpleName());
		this.rubro=rubro;
	}
	
	public LocalesComerciales(){}
}
