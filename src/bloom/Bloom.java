package bloom;

import static java.lang.Math.*;
/**
 * Bloom Filter class
 */
public class Bloom {
	/*! @brief Bloom filter */
	int[] bloom;
	/*! @brief Number of times a string will be inserted in the bloom filter with a different hash value */
	private int k;

	/**
	 * @brief Class constructor
	 */
	public Bloom(int bloom, int k) {
		this.bloom = new int[bloom];
		this.k=k;
	}
	
	/**
	 * @brief Inserts the data into the bloom filter
	 */
	public int[] insert(String[] nomes){
		int[] hashnomes = new int[nomes.length];
		for(int i = 0; i < nomes.length; i++){
			String str = nomes[i];
			for(int j = 0; j < k; j++){
				int hash = (string2hash(str) % bloom.length )+ 1;
				hashnomes[i] = hash;
				bloom[hashnomes[i]]+=1;
				str= str+j;
			}
		}
		return bloom;
	}
	
	/**
	 * @brief Deletes data from the bloom filter
	 */
	public int[] delete(String[] nomes){
		int[] hashnomes = new int[nomes.length];
		for(int i = 0; i < nomes.length; i++){
			String str = nomes[i];
			for(int j = 0; j < k; j++){
				int hash = (string2hash(str) % bloom.length )+ 1;
				hashnomes[i] = hash;
				if(bloom[hashnomes[i]]>0) {
					bloom[hashnomes[i]]-=1;
				}	
				str= str+j;
			}
		}
		return bloom;
	}
	
	/**
	 * @brief Hash function
	 */
	public int string2hash(String str) {		
		int hashcode=5381;
		for (int i = 0; i < str.length(); i++) {
			hashcode = (hashcode << 5) + hashcode + str.charAt(i);
		}
		return abs(hashcode);
	}
	
	/**
	 * @brief Used the count the number of times a string has been inserted in the bloom filter
	 */
	public int count(String str) {
		int a =0;
		int n= (string2hash(str) % bloom.length )+ 1;
		a += bloom[n];
		return a;
	}
	
	/**
	 * @brief Checks if the bloom filter contains a string
	 */
	public boolean check(String str) {
		boolean verify=false;
		int[] pos = new int[k];
		for(int i = 0; i < k; i++){	
			int n= (string2hash(str) % bloom.length )+ 1;
			int a = bloom[n];
			pos[i] = a;
			str = str + i;
		}
		for(int j = 0; j < pos.length; j++){
			if(pos[j] != 0){
				verify=true;
			}else{
				verify=false;
			}
		}
		return verify;
	}
}