package model;

public interface IntegerSet {
    // MODIFIES: this
    // EFFECTS: inserts integer into set unless it's already there, in which case do nothing.
    void insert(Integer num);

    // MODIFIES: this
    // EFFECTS: if the integer is in the integer set, then remove it from the integer set.
    //          otherwise, do nothing.
    void remove(Integer num);

    // EFFECTS: check whether `num` is contained in the set
    boolean contains(Integer num);

    // EFFECTS: get the size of the integer set
    int size();
}
