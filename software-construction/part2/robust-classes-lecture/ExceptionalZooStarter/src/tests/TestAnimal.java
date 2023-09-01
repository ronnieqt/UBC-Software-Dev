package tests;

import exceptions.AllergyException;
import exceptions.NotHungry;
import model.Animal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestAnimal
{
    @Test
    public void test_eat_is_hungry_not_allergic()
    {
        Animal a = new Animal(true, false);
        int eaten_times = 0;
        try {
            eaten_times = a.eat();
        }
        catch (AllergyException e) {
            fail("Got allergic when shouldn's have");
        }
        catch (NotHungry e) {
            fail("Got not hungry when we shouldn't have");
        }
        assertEquals(eaten_times, 1);
    }

    @Test
    public void test_eat_not_hungry_not_allergic()
    {
        Animal a = new Animal(false, false);
        int eaten_times = 0;
        try {
            eaten_times = a.eat();
        }
        catch (AllergyException e) {
            fail("Got allergic when shouldn't have");
        }
        catch (NotHungry e) {
            System.out.println("Caught the right exception");
        }
        assertEquals(eaten_times, 0);
    }
}
