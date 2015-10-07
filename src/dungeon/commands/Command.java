/**
 * @author Matthieu Lepers & Verschaeve Th�o
 * @version 1.0.1
 * @description Represent a command
 */
package dungeon.commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

import dungeon.Dungeon;

public abstract class Command
{
	protected String argv;
	protected Dungeon d;
	protected String argument;
	protected String description;
	protected boolean hide = false;
	
	public static final Map<String, Class<? extends Command>> COMMANDS = new HashMap<String, Class<? extends Command>>();
	
	/**
	 * Command constructor
	 */
	public Command()
	{
		this.argv = null;
		this.d = null;
		this.argument = "";
		this.description = "";
	}
	
	/**
	 * The method who execute the command
	 */
	public abstract void execute();
	
	/* ----- Setters ----- */
	/**
	 * Set the attributes
	 */
	public void setAttributes(String argv, Dungeon d)
	{
		this.argv = argv;
		this.d = d;
	}
	
	/**
	 * Add all commands to the static COMMANDS hashmap
	 */
	public static void addAllSubType()
	{
		Reflections r = new Reflections("dungeon.commands");
		Set<Class<? extends Command>> clazzSet = r.getSubTypesOf(Command.class);
		
		for (Class<? extends Command> clazz : clazzSet)
		{
			String name = clazz.getSimpleName().replaceAll("([A-Z][a-z]+)([A-Z][a-z]+)?Command", "$1 $2");
			name = name.replaceAll("([A-Z][a-z]+)( [A-Z][a-z]+)?( +)?","$1$2");
			
			Command.COMMANDS.put(name.toLowerCase(), clazz);
		}
	}
	
	/* ----- Booleans ----- */
	/**
	 * Check is this command as argument or not
	 * @return <code>true</code> if has, false else
	 */
	public boolean hasArgument()
	{
		return !this.argument.equals("");
	}
	
	/**
	 * Test if this command is a secret command or not
	 * @return <code>true</code> is this command is a secret command, false else
	 */
	public boolean isHide()
	{
		return this.hide;
	}
	
	/* ----- Printers ----- */
	/**
	 * Print all commands with her argument and description
	 */
	public static void printCommands() throws IllegalAccessException, InstantiationException
	{
		Set<String> keys = Command.COMMANDS.keySet();
		
		String[] cmdArray = new String[keys.size()];
		
		int i = 0;
		for (String key : keys)
		{
			Command c = Command.COMMANDS.get(key).newInstance();
			String name = c.getClass().getSimpleName().replaceAll("([A-Z][a-z]+)([A-Z][a-z]+)?Command", "$1 $2");
			name = name.replaceAll("([A-Z][a-z]+)( [A-Z][a-z]+)?( +)?","$1$2");
			
			if (!c.isHide())
			{
				if (c.hasArgument())
				{
					cmdArray[i] = name.toLowerCase() + " " + c.argument + " - " + c.description;
				}
				else
				{
					cmdArray[i] = name.toLowerCase() + " - " + c.description;
				}
			}
			i++;
		}
		
		Arrays.sort(cmdArray);
		
		for (int j = 0; j < cmdArray.length; j++)
		{
			System.out.println(cmdArray[j]);
		}
	}
	
	/* ----- Utils ----- */
	/**
	 * Create a command filter.
	 * @return the filter
	 */
	public static String craftRegex()
	{
		String regex = "";
		
		Set<String> keys = Command.COMMANDS.keySet();
		
		for (String key : keys)
		{
			regex += key.toLowerCase() + "|";
		}
		
		regex = regex.substring(0, regex.length() - 1);
		
		return "(" + regex + ")" + " ?([A-Za-z0-9]+)?";
	}
}