package tpdds.pois;

import javax.persistence.Entity;

@Entity
public class Bancos extends Poi {
	
	public Bancos(Poi poi) {
		super(poi);
		super.setDtype(this.getClass().getSimpleName());
	}
	
	public Bancos(){}
}
