package GameElements;

public class HumanPlayer {
	private String name;
	private Bead[] set;
	
	public HumanPlayer (String identity) {
		name=identity;
		set=new Bead[32];
		for (int i=0; i<32; i++) {
			set[i]=new Bead(i+1);
			}
	}

}