package exceptions;

public class StaleCoffeeException extends Exception
{
    private int m_time_since_last_brew;

    public StaleCoffeeException(int time_since_last_brew)
    {
        super("The coffee machine is stale");
        m_time_since_last_brew = time_since_last_brew;
    }
}
