package model;

import java.util.Collection;
import java.util.HashSet;

public class HighVolumeIntegerSet implements IntegerSet {

    private Collection<Integer> m_data;

    public HighVolumeIntegerSet() {
        m_data = new HashSet<Integer>();
    }

    @Override
    public void insert(Integer num) {
        m_data.add(num);
    }

    @Override
    public void remove(Integer num) {
        m_data.remove(num);
    }

    @Override
    public boolean contains(Integer num) {
        return m_data.contains(num);
    }

    @Override
    public int size() {
        return m_data.size();
    }
}
