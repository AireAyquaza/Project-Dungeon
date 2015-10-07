/**
 * @author Matthieu Lepers & Verschaeve Théo
 * @version 1.0.0
 * @description Represents all availables commands
 * Special thanks to our professor Damien Cassou for teaching us refactoring
 */
package dungeon;

import java.util.Random;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import dungeon.commands.Command;
import dungeon.entity.Player;
import dungeon.entity.types.PlayerEthnicGroup;
import dungeon.rooms.EmptyRoom;
import dungeon.rooms.Room;

public class Main
{
	private boolean colorOutput;
	
	/**
	 * The main method
	 * @param argv
	 * 		The argument when you run
	 * @throws InstantiationException
	 * 		Will never appear
	 * @throws IllegalAccessException
	 * 		Will never appear
	 */
	public static void main(String argv[]) throws InstantiationException, IllegalAccessException
	{
		Command.addAllSubType();
		Main main = new Main();
		main.prepareConsole(true);
		
		Dungeon d = new Dungeon();
		
		presentAllPlayerEthnicGroups();
		interpretChatacterSelection(d);
		createDungeon(d);
		
		System.out.println("Type 'help' to see all available commands.\n");
		
		/* Game part */
		while (!d.gameIsLost() && !d.getPlayer().isDead() && !d.gameIsWon())
		{
			System.out.print("> ");
			System.out.flush();
			String command = d.getScanner().nextLine();
			d.interpretCommand(command);
			System.out.println();
		}
		
		if (d.gameIsLost())
		{
			main.releaseConsole();
		}
	}
	
	/* ----- Generations ----- */
	public static void createDungeon(Dungeon d) throws InstantiationException, IllegalAccessException
	{
		Random r = new Random();
		int nbLayers = 5 + r.nextInt(6);
		
		/* Creating dungeon's rooms */
		Room entrance = new EmptyRoom("Entrance",d.getPlayer(),d);
		d.setEntrance(entrance);
		d.setRooms(d.generateRoomsList(entrance, nbLayers));
		d.generateRoomLinks(nbLayers);
	}
	
	/* ----- Actions ----- */
	private static void interpretChatacterSelection(Dungeon d)
	{
		Random r = new Random();
		
		/* Get the user input for player selection */
		try
		{
			System.out.print("> ");
			System.out.flush();
			int i = Integer.parseInt(d.getScanner().nextLine());
			if (i >= 1 && i <= PlayerEthnicGroup.values().length)
			{
				PlayerEthnicGroup group = PlayerEthnicGroup.values()[i - 1];
				d.setPlayer(new Player(group));
			}
			else
			{
				throw new NumberFormatException();
			}
		}
		catch (NumberFormatException e)
		{
			System.out.println("Your value is not correct! A random player was chosen !");
			int n = r.nextInt(PlayerEthnicGroup.values().length);
			PlayerEthnicGroup group = PlayerEthnicGroup.values()[n];
			d.setPlayer(new Player(group));
		}
		System.out.println("You chose: " + d.getPlayer().getFaction().getName());
		System.out.println();
	}
	
	/* ----- Printers ----- */
	private static void presentAllPlayerEthnicGroups()
	{
		/* Enum all players */
		System.out.println("Welcome to Dungeon Project, to select a Player, type his number.");
		for (int i = 0; i < PlayerEthnicGroup.values().length; i++)
		{
			System.out.println(String.valueOf(i+1) + ". " + PlayerEthnicGroup.values()[i].presentation());
		}
	}
	
	/** 
	 * Prepares the console for either plain or color output.  Caller's should make sure to call {@link #releaseConsole()} when they are done reporting diagnostics.
	 * @param color {@code true} to output diagnostics in color.
	 */
	private void prepareConsole(boolean color)
	{
		colorOutput = color;
		if (color)
		{
			AnsiConsole.systemInstall();
		}
		else
		{
			System.setProperty(Ansi.DISABLE,"true");
		}
	}
	
	/** 
	 * Returns the console to its state prior to the last call of  {@link #prepareConsole(boolean)}.
	 */
	private void releaseConsole()
	{
		if (colorOutput)
		{
			AnsiConsole.systemUninstall();
		}
		else
		{
			System.setProperty(Ansi.DISABLE,"false");
		}
	}
}
