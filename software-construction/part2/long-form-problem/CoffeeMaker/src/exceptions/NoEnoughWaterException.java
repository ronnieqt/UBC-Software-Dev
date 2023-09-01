package exceptions;

public class NoEnoughWaterException extends Exception
{
    private double m_water_amount;

    public NoEnoughWaterException(double water_amount)
    {
        super("There is not enough water supplied to the machine");
        m_water_amount = water_amount;
    }
}
