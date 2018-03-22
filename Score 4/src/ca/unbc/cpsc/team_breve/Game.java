package ca.unbc.cpsc.team_breve;

public class Game {
	
	//initialize all pieces for the game
	private Gui gui;
	private Board board;
	private Referee ref;
	private Player human;
	private Player opponent;	
	
	
	public Game () 
	{	
		board=new Board();	
		human=new Player();
		opponent=new AIPlayer();
		ref = new Referee(board, human, opponent, gui);
		gui = new Gui(board, ref);
	}
	
	//Main method to start the game
	public static void main (String []args)
	{
		int gameContinue = 0;
		
		Game g = new Game();
		g.ref.start();
		
		while (gameContinue = 0)
		{
			while (ref.checkGameState() != WIN || ref.checkGameState() != LOSE || ref.checkGameState() != DRAW)
			{
				int turn = 0;
				
				if (turn = 0)
				{
					ref.saveLocation(human.requestMoveLocation());
					ref.setPlayedLocation(human);
					opponent.placeCopyBead(human.getId(),ref.getSavedLocation());
					
					gui.repaint();
					
					counter++;
				}
				else if (turn = 1)
				{
					ref.saveLocation(opponent.requestMoveLocation());
					ref.setPlayedLocation(opponent);
					opponent.placeCopyBead(opponent.getId(),ref.getSavedLocation());
					
					gui.repaint();
					
					counter = 0;
				}
				
			}
			
			if (ref.checkGameState() = WIN)
			{
				JFrame frame = new JFrame();
				frame.setSize(200,100);
				frame.setTitle("You Win!");
				frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				
				WinComponent winComponent = new WinComponent();
				frame.add(winComponent);
				
				frame.setVisible(true);
			}
			else if (ref.checkGameState() = LOSE)
			{
				JFrame frame = new JFrame();
				frame.setSize(200,100);
				frame.setTitle("You Lose!");
				frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				
				LoseComponent loseComponent = new LoseComponent();
				frame.add(loseComponent);
				
				frame.setVisible(true);
			}
			
			ref.reset();
		}
	}
	
	
	public WinComponent
		extends JComponent
	{
		@Override
		public void paintComponent(Graphics g)
		{
			Graphics2D g2 = (Graphics2D) g;
			
			g2.drawString("You Win!",75,50)
			
		}
	}
	
	public LoseComponent
		extends JComponent
	{
		@Override
		public void paintComponent(Graphics g)
		{
			Graphics2D g2 = (Graphics2D) g;
			
			g2.drawString("You Lose!",75,50)
			
		}
	}
}
