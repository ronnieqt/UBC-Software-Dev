package test;

import exceptions.BeansAmountException;
import exceptions.NoEnoughBeansException;
import exceptions.TooManyBeansException;
import exceptions.NoEnoughWaterException;
import model.CoffeeMaker;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class TestCoffeeMaker {

    private CoffeeMaker m_coffee_maker;

    @Before
    public void setUp(){
        m_coffee_maker = new CoffeeMaker();
    }

    @Test
    public void test_constructor()
    {
        assertEquals(m_coffee_maker.getTimeSinceLastBrew(), 100);
        assertEquals(m_coffee_maker.getCupsRemaining(), 0);
    }

    @Test
    public void test_bew_should_pass()
    {
        try {
            m_coffee_maker.brew(2.4, 14.8);
            m_coffee_maker.brew(2.5, 15.0);
            m_coffee_maker.brew(2.6, 15.5);
        }
        catch (Exception e) {
            fail("Unexpected Error Caught: " + e.getMessage());
        }
    }

    @Test(expected = NoEnoughBeansException.class)
    public void test_brew_fail_no_enough_beans() throws BeansAmountException, NoEnoughWaterException
    {
        m_coffee_maker.brew(2.39, 15);
    }

    @Test(expected = TooManyBeansException.class)
    public void test_brew_fail_too_many_beans() throws BeansAmountException, NoEnoughWaterException
    {
        m_coffee_maker.brew(2.61, 15);
    }

    @Test(expected = NoEnoughWaterException.class)
    public void test_brew_no_enough_water() throws BeansAmountException, NoEnoughWaterException
    {
        m_coffee_maker.brew(2.5, 14.74);
    }

    @Test(expected = BeansAmountException.class)
    public void test_bew_fail_both() throws BeansAmountException, NoEnoughWaterException
    {
        m_coffee_maker.brew(2.39, 14.74);
    }

    @Test
    public void test_successful_path()
    {
        // brew coffee correctly
        try {
            m_coffee_maker.brew(2.5, 20);
        }
        catch (Exception e) {
            fail(e.getMessage());
        }
        // pour a few cups
        try {
            m_coffee_maker.pourCoffee();
            m_coffee_maker.pourCoffee();
            m_coffee_maker.pourCoffee();
        }
        catch (Exception e) {
            fail(e.getMessage());
        }
        // set the time since last brew
        try {
            m_coffee_maker.setTimeSinceLastBrew(10);
        }
        catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test_unsuccessful_path()
    {
        // putting in the wrong amount of water and the wrong amount of beans,
        try {
            m_coffee_maker.brew(2, 10);
            // call the assertion method `fail()` inside the try block,
            // on the line after a method is supposed to throw an exception
            fail("Error is not thrown for incorrect constructor");
        }
        catch (Exception e) {
            System.out.println("Correctly caught error: " + e.getMessage());
        }
        // then putting in the correct amounts,
        try {
            m_coffee_maker.brew(2.5, 20);
        }
        catch (Exception e) {
            fail(e.getMessage());
        }
        // pouring a few cups,
        try {
            m_coffee_maker.pourCoffee();
            m_coffee_maker.pourCoffee();
            m_coffee_maker.pourCoffee();
        }
        catch (Exception e) {
            fail(e.getMessage());
        }
        assertEquals(m_coffee_maker.getCupsRemaining(), 20 - 3);
        // setting the time since last brew to 59,
        try {
            m_coffee_maker.setTimeSinceLastBrew(59);
        }
        catch (Exception e) {
            fail(e.getMessage());
        }
        // pouring a few cups,
        try {
            m_coffee_maker.pourCoffee();
            m_coffee_maker.pourCoffee();
        }
        catch (Exception e) {
            fail(e.getMessage());
        }
        assertEquals(m_coffee_maker.getCupsRemaining(), 20 - 3 - 2);
        // setting the time since last brew > 60,
        try {
            m_coffee_maker.setTimeSinceLastBrew(80);
        }
        catch (Exception e) {
            fail(e.getMessage());
        }
        // and then attempting to pour coffee again
        try {
            m_coffee_maker.pourCoffee();
            fail("Error is not thrown when time since last brew > 60");
        }
        catch (Exception e) {
            System.out.println("Correctly caught error: " + e.getMessage());
        }
        // setting the time since last brew back into range,
        try {
            m_coffee_maker.setTimeSinceLastBrew(30);
        }
        catch (Exception e) {
            fail(e.getMessage());
        }
        try {
            for (int i = 0; i < 15; ++i) {
                m_coffee_maker.pourCoffee();
            }
        }
        catch (Exception e) {
            fail(e.getMessage());
        }
        try {
            m_coffee_maker.pourCoffee();
            fail("NoCupsRemainingException is not thrown");
        }
        catch (Exception e) {
            System.out.println("Correctly caught error: " + e.getMessage());
        }
    }
}
