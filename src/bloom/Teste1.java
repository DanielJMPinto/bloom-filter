package bloom;

import java.io.*;
/**
 * @brief Bloom filter test class
 */
public class Teste1 {
	/**
	 * @brief Main program
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Bloom gutemberg = new Bloom(10000000,100);
		File file = new File("docs/gutemberg.txt"); 
		BufferedReader br = new BufferedReader(new FileReader(file));
		String str;
		while ((str = br.readLine()) != null) {
			String[] words = new String[1];
			String[] splitted = str.split(" ");
			for (int n=0;n<splitted.length;n++) {
				words[0]=splitted[n];
				gutemberg.insert(words);
			}
		}
		br.close();
		System.out.println("A palavra toward aparece "+gutemberg.count("toward")+ " vezes");
		System.out.println("A palavra see aparece "+gutemberg.count("see")+ " vezes");
		System.out.println("A palavra the aparece "+gutemberg.count("the")+ " vezes");
	}
}