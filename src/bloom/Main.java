package bloom;
import java.io.*;
import java.util.*; 
public class Main {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		Bloom players = new Bloom(1000000,100);
		MinHash min = new MinHash(100);
		Contador count = new Contador();
		Map<String, ArrayList<String>> playersStats = new HashMap<String, ArrayList<String>>();
		
		File file = new File("players.txt"); 
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		int c=0;
		String str;
		while ((str = br.readLine()) != null) {
			String[] nomes = new String[1];
			ArrayList<String> info = new ArrayList<>();
			String[] splitted = str.split(" ");
			
			if (splitted[0].contains(",")) {
				String[] names = splitted[0].split(",");
				nomes[0]=names[0]+" "+names[1];
			}else {
				nomes[0]=splitted[0];
			}
			
			if (splitted[1].contains(",")) {
				String[] teams = splitted[1].split(",");
				info.add(teams[0]+" "+teams[1]);
				
			}else {
				info.add(splitted[1]);
			}
			info.add(splitted[2]);
			
			players.insert(nomes);
			c=count.Increment();
			playersStats.put(nomes[0],info);
	  	}
		br.close();
		System.out.println("Foram inseridos cerca de " + c*2 + " jogadores no bloom filter");
		System.out.println(" ");
		System.out.println("Verificacao da presenca de um jogador no bloom filter");
		System.out.println("-----------------------------------------------------");
		String op;
		do {
			System.out.print("> ");
			String verificar = sc.nextLine();
			if(players.check(verificar)==true) {
				System.out.println("O jogador "+verificar+" esta no bloom filter");
			}else {
				System.out.println("O jogador "+verificar+" nao esta no bloom filter");
			}
			System.out.println("Deseja verificar outro jogador (s/n)");
			System.out.print("> ");
			op = sc.nextLine();
			while(!op.equals("s") && !op.equals("n")) {
				System.out.println("Opcao invalida");
				op = sc.nextLine();
			}
		}while(op.equals("s"));
		System.out.println(" ");
		System.out.println("Deseja verificar todos os jogadores que tem a mesma posicao e clube na premier league (s/n)");
		System.out.print("> ");
		op = sc.nextLine();
		while(!op.equals("s") && !op.equals("n")) {
			System.out.println("Opcao invalida");
			op = sc.nextLine();
		}
		if(op.equals("s")) {
			min.minhashing(playersStats);
		}	
		sc.close();
	}

}
