package test;

import org.junit.Test;

import model.HighVolumeIntegerSet;

import static org.junit.Assert.*;

public class TestHighVolumeIntegerSet extends TestIntegerSet {

    @Override
    public void init() {
        m_int_set = new HighVolumeIntegerSet();
    }

    @Test
    @Override
    public void test_insert_high_volume() {
        for (int i = 0; i < 50000; ++i) {
            m_int_set.insert(i);
            assertTrue(m_int_set.contains(i));
            assertEquals(m_int_set.size(), i + 1);
        }
    }
}
