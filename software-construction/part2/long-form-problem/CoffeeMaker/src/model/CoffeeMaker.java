package model;

import exceptions.*;

/**
 * A coffee maker used to train baristas.
 * Class invariant: cups remaining >= 0, time since last brew >= 0
 */

public class CoffeeMaker {

    private int m_time_since_last_brew;
    private int m_cups_remaining;

    public CoffeeMaker(){
        m_time_since_last_brew = 100;
        m_cups_remaining = 0;
    }

    // getters
    public int getTimeSinceLastBrew() {
        return m_time_since_last_brew;
    }
    public int getCupsRemaining() {
        return m_cups_remaining;
    }

    // EFFECTS: return true if there are coffee cups remaining
    public boolean areCupsRemaining() {
        return (getCupsRemaining() > 0);
    }

    //EFFECTS: sets time since last brew
    //         an exception will be thrown when time < 0
    public void setTimeSinceLastBrew(int time) throws Exception
    {
        if (time < 0) {
            throw new Exception("The provided `time` parameter (" + time + ") is < 0");
        }
        m_time_since_last_brew = time;
    }

    //EFFECTS: sets cups remaining to full (20 cups) and time since last brew to 0
    //         an exception will be thrown when beans < 2.4 cups or > 2.6 cups or water <= 14.75 cups
    public void brew(double beans, double water) throws BeansAmountException, NoEnoughWaterException
    {
        if (beans < 2.4) {
            throw new NoEnoughBeansException(beans);
        }
        if (beans > 2.6) {
            throw new TooManyBeansException(beans);
        }
        if (water <= 14.75) {
            throw new NoEnoughWaterException(water);
        }
        m_cups_remaining = 20;
        m_time_since_last_brew = 0;
    }

    //MODIFIES: this
    //EFFECTS: subtracts one cup from cups remaining
    //         an exception will be thrown when cups remaining <= 0 or time since last bew >= 60
    public void pourCoffee() throws NoCupsRemainingException, StaleCoffeeException
    {
        if (!areCupsRemaining()) {
            throw new NoCupsRemainingException();
        }
        if (getTimeSinceLastBrew() >= 60) {
            throw new StaleCoffeeException(getTimeSinceLastBrew());
        }
        m_cups_remaining -= 1;
    }

}
