package GPS;

public class GPGLL {
	
	private float latitude;
	private String latitude_direction;
	private float longitude;
	private String longitude_direction;
	private String status;
	
	
	public GPGLL(float latitude, String latitude_direction, float longitude, String longitude_direction, String status) {
		this.latitude = latitude;
		this.latitude_direction = latitude_direction;
		this.longitude = longitude;
		this.longitude_direction = longitude_direction;
		this.status = status;
	}
	
	public String toString() {
		return "latitude = " + this.latitude;
				
				
	}
	
	

}
