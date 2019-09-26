package bloom;

public class Contador {
	private int count;

	public Contador() {
		this.count = 0;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public int Increment() {
		double m=Math.random();
		if(m<0.5) {
			count++;
		}
		return count;
	}
}