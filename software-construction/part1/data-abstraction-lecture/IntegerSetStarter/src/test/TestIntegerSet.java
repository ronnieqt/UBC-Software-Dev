package test;

import model.IntegerSet;
import model.LowVolumeIntegerSet;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class TestIntegerSet {
    IntegerSet m_int_set;

    @Before
    public abstract void init();

    // TODO: test insert not there: insert a number that is not in the set
    // TODO: outcome: the number would now be in the set once
    @Test
    public void test_insert_not_there() {
        // check that the number is not already in the set
        check_set_empty_does_not_contain(3);
        // insert the number into the set
        m_int_set.insert(3);
        // check that the number is in the set once
        check_set_contains_once(3);
    }

    // TODO: test insert already there: insert a number that's already in the set
    // TODO: outcome: the number would now be in the set once
    @Test
    public void test_insert_already_there() {
        int num = 3;
        // check that the number is not already in the set
        check_set_empty_does_not_contain(num);
        // insert the number into the set
        m_int_set.insert(num);
        // check that the number is in the set once
        check_set_contains_once(num);
        // insert the number into the set again
        m_int_set.insert(num);
        // check that the number is in the set once (still)
        check_set_contains_once(num);
    }

    @Test
    public void test_insert_high_volume() {
        for (int i = 0; i < 5000; ++i) {
            m_int_set.insert(i);
            assertTrue(m_int_set.contains(i));
            assertEquals(m_int_set.size(), i + 1);
        }
    }

    private void check_set_contains_once(int num) {
        assertEquals(m_int_set.size(), 1);
        assertTrue(m_int_set.contains(num));
    }

    private void check_set_empty_does_not_contain(int num) {
        assertEquals(m_int_set.size(), 0);
        assertFalse(m_int_set.contains(num));
    }
}
