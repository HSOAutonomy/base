package hso.autonomy.util.command;

/**
 * Special implementation of the ICommand interface for unit tests
 *
 * @author Klaus Dorer
 */
public class TestCommand implements ICommand
{
	private final String name;

	private boolean mergeable;

	/**
	 * Constructor
	 *
	 * @param name Command name
	 */
	public TestCommand(String name)
	{
		this.name = name;
		mergeable = false;
	}

	@Override
	public boolean perform()
	{
		return true;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public void undo()
	{
	}

	@Override
	public boolean equals(Object obj)
	{
		return (obj instanceof TestCommand) && name.equals(((TestCommand) obj).name);
	}

	@Override
	public boolean isMergeable(ICommand newCommand)
	{
		return mergeable;
	}

	/**
	 * Set "mergeable" flag, for unit testing only!
	 *
	 * @param mergeable True if mergeable
	 */
	public void setMergeable(boolean mergeable)
	{
		this.mergeable = mergeable;
	}

	@Override
	public void merge(ICommand newCommand)
	{
	}

	@Override
	public int hashCode()
	{
		return name.hashCode() + super.hashCode();
	}
}
