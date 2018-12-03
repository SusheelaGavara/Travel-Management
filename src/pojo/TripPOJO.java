package pojo;


public class TripPOJO {

	private int package_id;
	private String package_name;
	private String source;
	private String destination;

	private String departure_date;

	private String hotel_name;

	private int no_of_nights;

	private String mode_of_transport;

	private int discount;

	private String trip_desc;

	private int package_cost;

	public int getPackage_id() {
		return package_id;
	}

	public void setPackage_id(int package_id) {
		this.package_id = package_id;
	}

	public String getPackage_name() {
		return package_name;
	}

	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDeparture_date() {
		return departure_date;
	}

	public void setDeparture_date(String departure_date) {
		this.departure_date = departure_date;
	}
	public String getHotel_name() {
		return hotel_name;
	}

	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}
	public int getNo_of_nights() {
		return no_of_nights;
	}

	public void setNo_of_nights(int no_of_nights) {
		this.no_of_nights = no_of_nights;
	}
	public String getMode_of_transport() {
		return mode_of_transport;
	}

	public void setMode_of_transport(String mode_of_transport) {
		this.mode_of_transport = mode_of_transport;
	}
	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public String getTrip_desc() {
		return trip_desc;
	}

	public void setTrip_desc(String trip_desc) {
		this.trip_desc = trip_desc;
	}
	public int getPackage_cost() {
		return package_cost;
	}

	public void setPackage_cost(int package_cost) {
		this.package_cost = package_cost;
	}
}
