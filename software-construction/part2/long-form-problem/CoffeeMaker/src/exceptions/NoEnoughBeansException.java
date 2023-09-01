package exceptions;

public class NoEnoughBeansException extends BeansAmountException
{
    public NoEnoughBeansException(double beans)
    {
        super(beans, " is not enough beans");
    }
}
