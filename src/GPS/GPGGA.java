package GPS;


public class GPGGA {
	
	private String time;
	private float latitude;
	private String latitude_direction;
	private float longitude;
	private String longitude_direction;
	private int quality;
	
	
	public GPGGA(String time, float latitude, String latitude_direction, float longitude, String longitude_direction, int quality) {
		this.time = time;
		this.latitude = latitude;
		this.latitude_direction = latitude_direction;
		this.longitude = longitude;
		this.longitude_direction = longitude_direction;
		this.quality = quality;
	}
	
	public String toString() {
		return "time = " + this.time;
	}
	
}
