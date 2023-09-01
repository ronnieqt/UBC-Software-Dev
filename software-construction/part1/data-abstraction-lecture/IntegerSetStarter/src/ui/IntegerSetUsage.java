package ui;

import model.ChattyIntegerSet;
import model.IntegerSet;
import model.LowVolumeIntegerSet;

public class IntegerSetUsage
{
    public static void main(String[] args)
    {
        IntegerSet my_set = new LowVolumeIntegerSet();
        my_set.insert(3);
        System.out.println("Does the set contain number 3: " + my_set.contains(3));
        System.out.println("           Size of the set is: " + my_set.size());
        my_set.remove(3);

        ChattyIntegerSet cis = new ChattyIntegerSet();
        cis.insert(10);
    }
}
