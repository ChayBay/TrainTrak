// Made by Chason Bibeau

public class Train {
	int ID;
	String Origin;
	String Destination;
	String Departure;
	String Arrival;
	
	public Train(int ID, String Origin, String Destination,
				 String Departure, String Arrival) {
		this.ID = ID;
		this.Origin = Origin;
		this.Destination = Destination;
		this.Departure = Departure;
		this.Arrival = Arrival;
	}
	
	public int getID() {
		return ID;
	}
	public String getOrigin() {
		return Origin;
	}
	public String getDestination() {
		return Destination;
	}
	public String getDeparture() {
		return Departure;
	}
	public String getArrival() {
		return Arrival;
	}
	@Override
	public String toString() {
		return ("The train ID: " + this.getID() +
				" || Origin: " + this.getOrigin() +
	        	" || Destination: " + this.getDestination() +
	        	" || Departure: " + this.getDeparture() +
	        	" || Arrival: " + this.getArrival());
	}
}
