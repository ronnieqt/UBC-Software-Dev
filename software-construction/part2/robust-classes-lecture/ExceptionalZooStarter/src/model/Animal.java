package model;

import exceptions.AllergyException;
import exceptions.NotHungry;

public class Animal {

    private boolean m_is_hungry = true;
    private boolean m_is_allergic = true;
    private boolean hungry;
    private int eaten = 0;

    public Animal(boolean hungry, boolean allergic)
    {
        m_is_hungry = hungry;
        m_is_allergic = allergic;
    }

    // getters
    public boolean isM_is_hungry() { return hungry; }

    public int eat() throws NotHungry, AllergyException {
        int initial_eaten = eaten;
        if (!m_is_hungry) {
            System.out.println("Animal is not hungry");
            throw new NotHungry();
        }
        if (m_is_allergic) {
            System.out.println("Animal is allergic");
            throw new AllergyException();
        }
        m_is_hungry = false;
        eaten++;
        assert(!m_is_hungry && eaten > initial_eaten);
        return eaten;
    }


}