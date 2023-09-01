package test;

import model.LowVolumeIntegerSet;

public class TestLowVolumeIntegerSet extends TestIntegerSet {

    @Override
    public void init() {
        m_int_set = new LowVolumeIntegerSet();
    }
}
