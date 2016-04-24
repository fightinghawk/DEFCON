package tpdds;

public class Location { 
	
    public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	private double longitude;
    private double latitude;   
   
    // Latitud y longitud se colocan en grados
    public Location(double latitude, double longitude) {
        this.latitude  = latitude;
        this.longitude = longitude;
    }

    // return string representation of this point
    public String toString() {
        return " (" + latitude + ", " + longitude + ")";
    }

}
