package bloom;

import java.io.*;
import java.util.*;
/**
 * @brief Bloom filter test class
 */
public class Teste2 {
	/**
	 * @brief Main program
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		MinHash min = new MinHash(100);
		File file = new File("docs/u.data"); 
		BufferedReader br = new BufferedReader(new FileReader(file));
		Map<String, ArrayList<String>> movies = new HashMap<String, ArrayList<String>>();
		String str;
		while ((str = br.readLine()) != null) {
			ArrayList<String> lista = new ArrayList<>();
			String[] splitted = str.split("	");
			if(movies.containsKey(splitted[0])) {
				lista=movies.get(splitted[0]);
				lista.add(splitted[1]);
			}
			movies.put(splitted[0], lista);
		}
		min.minhashing(movies);
		br.close();
	}

}
