package ca.unbc.cpsc.team_breve;

import java.util.Scanner;
import ca.unbc.cpsc.score4.exceptions.PlayerException;

import ca.unbc.cpsc.team_breve.enums.*;
import java.util.Random;

public class Player implements ca.unbc.cpsc.score4.interfaces.Player{
	
	//Scanner is needed for the Player.requestMoveLocation()
	Scanner in = new Scanner(System.in);
	//Random is needed for the player id
	Random rand = new Random();
	
	//Member variables
	private int id;
	
	//Getter method for id to use with AIPlayer
	public int getId() {
		return this.id;
	}
	
	private Location playerLastPlayed;
	
	//Getter method for playerLastPlayed
	public Location getPlayerLastPlayed(){
		return this.playerLastPlayed;
	}
	
	//Setter method for playerLastPLayed
	public void setPlayerLastPlayed(Location loc)
	{
		this.playerLastPlayed = loc;
	}
	
	private boolean pegClicked;
	
	//Setter method for pegClicked
	public void setPegClicked(boolean clicked)
	{
		pegClicked = clicked;
	}
	
	private Colour colour;
	
	//Getter method for our Colour to be used with AIPlayer
	public Colour getColour() {
		return this.colour;
	}
	
	private int opponentsId;
	
	//Getter method for the opponents id to use with AIPlayer
	public int getOpponentsId() {
		return this.opponentsId;
	}
	
	private Colour opponentsColour;
	
	//Getter method for the opponents Colour
	public Colour getOpponentColour() {
		return this.opponentsColour;
	}
	
	private GameOverStatus gamestatus;
	
	private Location opponentsLastLocation;
	
	//Getter method for the opponentsLastLocation
	public Location getOpponentsLastLocation() {
		return this.opponentsLastLocation;
	}
		
	//Player constructor
	public Player(){
		id = rand.nextInt(Integer.MAX_VALUE) + 0;
		colour = null; //A player object will be assigned a color by the referee
		gamestatus = null;
		opponentsLastLocation = null;
		opponentsColour = null;
		playerLastPlayed = null;
		}
	
	//The referee will use this method to assign our Player a Colour
	public void startGameAs(Colour c) {
		colour = c;
		opponentsColour = (colour == Colour.White) ? Colour.Black: Colour.White;//Sets the opponents Colour
	}
	
	//This method resets the Player 
	public void reset() throws PlayerException{
		try {
		id = rand.nextInt(Integer.MAX_VALUE) + 0;
		colour = null;
		opponentsId = 0;
		gamestatus = null;
		opponentsLastLocation = null;
		playerLastPlayed = null;
		}
		catch(GameStateException gse)
		{
			throw new PLayerException("Bad reset", gse);
		}
	}
		
	//This method will record the ID of the opponent. Not sure what we want to do with it yet
	public void noteOpponentsId(int id) throws PLayerException{
		try {
		opponentsId = id;
		if(this.getOpponentsId() == id) {//Checks to make sure the PLayer doesn't share their id with the opponent
			id = rand.nextInt(Integer.MAX_VALUE) + 0;
		}
		}
		catch(GameStateException gse)
		{
			throw new PLayerException("Bad noteOpponentsId", gse);
		}
	}
	
	//The Referee will use this to get a Location from the PLayer. Will be different for AIplayer
	public Location requestMoveLocation() throws InterruptedException {
		while(true)
		{
			Thread.sleep(10);
			if(pegClicked)
			{
				pegClicked = false;
				break;
			}
		}
		return playerLastPlayed;
	}
	
	//Referee calls this method if our Location could not accept a bead
	public Location retry() throws InterruptedException {
		System.out.println("Please enter a new location.");
		return this.requestMoveLocation();
	}
	
	//Notes the last Location that the opponent played. not sure what we want to do with this yet
	public void opponentPlays(Location ell) throws PLayerException{
		try {
		opponentsLastLocation = ell;
		}
		catch(GameStateException gse)
		{
			throw new PLayerException("Bad opponentPlays", gse);
		}
	}
	
	//Referee sends the Player a GameOverStatus when the game ends. 
	public void noteGameOver(GameOverStatus gameoverstatus) throws PlayerException{
		try {
		gamestatus = gameoverstatus;
		}
		catch(GameStateException gse)
		{
			throw new PLayerException("Bad reset", gse);
		}
	}
	
	//This is the getAI method. Not sure if it belong in Player
	public Player getAI() {
		Player player = new AIPlayer();
		return player;
	}
	
	
}
