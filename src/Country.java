
public class Country {
	private String name;
	private LinkedList cities;
	private double totalTourists;

	public Country() {
		super();
		this.totalTourists = 0;
		this.cities = new LinkedList();
	}

	public Country(String name) {
		super();
		this.name = name;
		this.totalTourists = 0;
		this.cities = new LinkedList();
	}

	// return the name

	public String getName() {
		return name;
	}

	// the name to set
	public void setName(String name) {
		this.name = name;
	}

	// return the cities
	public LinkedList getCities() {
		return cities;
	}

	// the cities to set
	public void setCities(LinkedList cities) {
		this.cities = cities;
	}

	// return the totalTourists
	public double getTotalTourists() {
		return totalTourists;
	}

	// the totalTourists to set
	public void setTotalTourists(double totalTourists) {
		this.totalTourists = totalTourists;
	}

	// add a city to the country
	public void addCity(City c) {
		this.cities.addFirst(c);
		this.totalTourists += c.getNumberOfTourists();

	}

	// prints the cities list
	public void printCities() {
		this.cities.printList();
	}

	public String citiesPrint() {
		return cities.print();
	}
}
