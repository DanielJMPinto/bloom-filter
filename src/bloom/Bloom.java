package bloom;

import static java.lang.Math.*;

public class Bloom {
	int[] bloom;
	private int k;

	public Bloom(int bloom, int k) {
		this.bloom = new int[bloom];
		this.k=k;
	}

	public int[] getBloom() {
		return bloom;
	}
	
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
	
	public int string2hash(String str) {		
		int hashcode=5381;
		for (int i = 0; i < str.length(); i++) {
			hashcode = (hashcode << 5) + hashcode + str.charAt(i);
		}
		return abs(hashcode);
	}
	
	public int count(String str) {
		int a =0;
		int n= (string2hash(str) % bloom.length )+ 1;
		a += bloom[n];
		return a;
	}
	
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