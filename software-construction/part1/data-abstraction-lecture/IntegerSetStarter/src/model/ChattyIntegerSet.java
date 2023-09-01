package model;

public class ChattyIntegerSet extends HighVolumeIntegerSet
{
    @Override
    public void insert(Integer num)
    {
        System.out.println("Inserting " + num);
        super.insert(num);
    }
}
