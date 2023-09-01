package model;

import java.util.ArrayList;

public class LowVolumeIntegerSet implements IntegerSet {
    private ArrayList<Integer> m_data;

    public LowVolumeIntegerSet()
    {
        m_data = new ArrayList<Integer>();
    }

    // MODIFIES: this
    // EFFECTS: inserts integer into set unless it's already there, in which case do nothing.
    @Override
    public void insert(Integer num)
    {
        if (!m_data.contains(num)) {
            m_data.add(num);
        }
    }

    // MODIFIES: this
    // EFFECTS: if the integer is in the integer set, then remove it from the integer set.
    //          otherwise, do nothing.
    @Override
    public void remove(Integer num)
    {}

    // EFFECTS: check whether `num` is contained in the set
    @Override
    public boolean contains(Integer num)
    {
        return m_data.contains(num);
    }

    // EFFECTS: get the size of the integer set
    @Override
    public int size()
    {
        return m_data.size();
    }
}
