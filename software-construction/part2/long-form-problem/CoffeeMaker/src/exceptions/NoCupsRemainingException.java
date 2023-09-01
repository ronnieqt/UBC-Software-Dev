package exceptions;

public class NoCupsRemainingException extends Exception
{
    public NoCupsRemainingException()
    {
        super("There is no cups remaining");
    }
}
