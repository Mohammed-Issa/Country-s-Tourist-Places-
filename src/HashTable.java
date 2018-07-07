
public class HashTable {
	private static int SIZE = 101; // the max size
	private Entry[] map = null; // array for saving hash entries
	private int counter = 0; // count the entries.

	// constructor
	public HashTable() {
		this(SIZE);
		counter = 0;
	}

	// constructor
	public HashTable(int N) {
		SIZE = N;
		counter = 0;
		map = new Entry[SIZE];
		for (int i = 0; i < SIZE; i++) {
			map[i] = null;
		}
	}

	// return the sIZE
	public int getSIZE() {
		return SIZE;
	}

	// return the counter
	public int getCounter() {
		return counter;
	}

	public Entry atIndex(int i) {
		return map[i];
	}

	// insert to hash table
	public void put(String key, Entry value) throws HashtableException {

		if (counter == SIZE)
			throw new HashtableException("Table full");

		int h = hash(key);
		int i = 1;
		while (map[h] != null) {
			h = (h + i * i) % SIZE; // location is the hash function
			i++;

		}

		map[h] = value;
		counter++;
	}

	// find and return a hash entry
	public Entry get(String country) {
		int i = 1;
		int h = hash(country);

		while (map[h] != null) {
			if (map[h].getCountryName().compareTo(country) == 0) {
				return map[h]; // return object if the keys are equal
			}
			h = (h + i * i) % SIZE;
			i++;
		}
		return null;
	}

	// to delete an entry from the hash table
	public Entry remove(String key) {
		int i = 1;
		int h = hash(key);

		while (map[h] != null) {
			if (map[h].getCountryName().compareTo(key) == 0) {
				Entry e = map[h];
				map[h] = null;
				return e; // return object if the keys are equal
			}
			h = (h + i * i) % SIZE;
			i++;
		}
		return null;
	}

	// the hashing function
	public int hash(String key) {
		int k = key.length();
		int sum = 0;
		for (int i = 0; i < k - 1; i++) {
			sum += key.charAt(i) << 5;
		}
		sum = sum % SIZE;

		if (sum < 0) {
			sum += SIZE;
		}
		return sum;
	}

	// print the hash table
	public void printTable() {
		for (int i = 0; i < map.length; i++) {
			if (map[i] == null)
				System.out.print("null --> ");
			else
				System.out.print(map[i].getCountryName() + " --> ");
		}
		System.out.print("\n");
	}
}

class Entry {

	private String countryName;
	private int numOfCities;
	private double totalTouristsNum;

	public Entry() {
		super();
	}

	public Entry(String countryName, int numOfCities, double totalTouristsNum) {
		super();
		this.countryName = countryName;
		this.numOfCities = numOfCities;
		this.totalTouristsNum = totalTouristsNum;
	}

	// return the countryName

	public String getCountryName() {
		return countryName;
	}

	// the countryName to set

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	// return the numOfCities

	public int getNumOfCities() {
		return numOfCities;
	}

	// the numOfCities to set

	public void setNumOfCities(int numOfCities) {
		this.numOfCities = numOfCities;
	}

	// return the totalTouristsNum

	public double getTotalTouristsNum() {
		return totalTouristsNum;
	}

	// the totalTouristsNum to set

	public void setTotalTouristsNum(double totalTouristsNum) {
		this.totalTouristsNum = totalTouristsNum;
	}

}

class HashtableException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HashtableException(String message) {
		super(message);
	}
}
