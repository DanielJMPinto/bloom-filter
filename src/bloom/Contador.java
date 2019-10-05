package bloom;
/**
 * @brief The class Contador is used to count the number of insertions in the bloom filter
 */
public class Contador {
	/**
	 * @brief Variable that stores the number of insertions
	 */
	private int count;

	/**
	 * @brief Class constructor
	 * 
	 * Once called, sets the value of the variable count to 0
	 */
	public Contador() {
		this.count = 0;
	}
	
	/**
	 * @brief Function used to increment the value of the variable count
	 */
	public int Increment() {
		double m=Math.random();
		if(m<0.5) {
			count++;
		}
		return count;
	}
}