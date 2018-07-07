
public class City {

	private int rank; // the rank of the city
	private String cityN; // the city name
	private double numberOfTourists; // the number of tourists in millions

	// constructor with all arguments
	public City(int rank, String cityN, double numberOfTourists) {
		super();
		this.rank = rank;
		this.cityN = cityN;
		this.numberOfTourists = numberOfTourists;
	}

	// returns the rank
	public int getRank() {
		return rank;
	}

	// to set rank
	public void setRank(int rank) {
		this.rank = rank;
	}

	// returns the city name
	public String getCityN() {
		return cityN;
	}

	// to set city name
	public void setCity(String city) {
		this.cityN = city;
	}

	// returns the number of tourists
	public double getNumberOfTourists() {
		return numberOfTourists;
	}

	// to set the number of tourists
	public void setNumberOfTourists(double numberOfTourists) {
		this.numberOfTourists = numberOfTourists;
	}
}
