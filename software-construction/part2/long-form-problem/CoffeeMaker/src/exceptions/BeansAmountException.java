package exceptions;

public class BeansAmountException extends Exception
{
    private double m_beans;

    protected BeansAmountException(double beans, String message)
    {
        super(beans + message);
        m_beans = beans;
    }

    public double get_beans()
    {
        return m_beans;
    }
}
