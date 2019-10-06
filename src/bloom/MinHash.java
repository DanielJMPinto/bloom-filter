package bloom;

import java.util.*;
import static java.lang.Math.*;
/**
 * @brief The class MinHash is used to test the similarity between two insertions of the bloom filter
 */
public class MinHash {
	/*! @brief Random value to be used in a string to hash function*/
	private int a;
	/*! @brief Random value to be used in a string to hash function*/
	private int b;
	/*! @brief Value to be used in a string to hash function*/
	private int p;
	/*! @brief Number of cicles used to discover the minimum hash value for a string*/
	private int k;
	private int [][] minimum;
	private double [][] jacc = new double [500][500];
	Set<String> keys = new HashSet<String>();
	
	/**
	 * @brief Class constructor
	 */
	public MinHash(int k) {
		this.k = k;
	}

	/**
	 * @brief Function used to generate random values to a and b
	 */
	public void iniciarhash() {
		Random gerador = new Random();
		a=gerador.nextInt();
		b=gerador.nextInt();
		p=1234577;
	}
	
	/**
	 * @brief Hash function
	 */
	public int string2hash(String str, int a, int b, int p) {
		int hash =0;
		for (int i=0;i<str.length();i++) {
			hash+=(a*str.charAt(i)+b)%p;
		}
		return abs(hash);
	}
	
	/**
	 * @brief the function minhashing() discovers the minimum hash value for a given string
	 */
	public void minhashing(Map<String, ArrayList<String>> playersStats) {
		keys = playersStats.keySet();
		minimum= new int[keys.size()][k];
		for (int h =0; h<k; h++) {
			iniciarhash();
			int index =0;
			for(String key : keys) {
				int min=string2hash(playersStats.get(key).get(0),a,b,p);
				for(int i=1;i<playersStats.get(key).size();i++){
					int hash2=string2hash(playersStats.get(key).get(i),a,b,p);
					if (hash2<min) {
						min=hash2;
					}
				}
				minimum[index][h]=min;
				index++;
			}
		}
		jaccard();
	}
	
	/**
	 * @brief Makes the jaccard coefficient between two samples
	 */
	public void jaccard() {
		jacc=new double[keys.size()][keys.size()];
		for(int i=0;i<keys.size();i++) {
			for(int j=i+1;j<keys.size();j++) {
				int sum=0;
				for(int ind=0;ind<k;ind++) {
					if(minimum[i][ind]==minimum[j][ind]) {
						sum++;
					}
				}
				jacc[i][j]=1-(double)sum/k;	
			}
		}
		similar();
	}


	/**
	 * @brief Checks if two samples are similar
	 */
	public void similar() {
		for(int i=0;i<keys.size();i++) {
			for(int j=i+1;j<keys.size();j++) {
				if(jacc[i][j]<0.15) {
					int count=0;
					for(String str:keys) {
						if(count==i) {
							//System.out.print("O utilizador "+str+" e similar ao utilizador "); //utilizado para o teste2
							System.out.print("O jogador "+str+" joga na mesma posicao e clube que o jogador ");
						}
						count++;
					}
					int count2=0;
					for(String str2:keys) {
						if(count2==j) {
							System.out.println(str2);
						}
						count2++;
					}
				}	
			}	
		}	
	}
}