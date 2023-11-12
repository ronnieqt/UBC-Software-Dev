package observer;

import java.util.Observable;
import java.util.Observer;

public class SongSaver implements Observer
{
    @Override
    public void update(Observable o, Object arg)
    {
        System.out.println("Playing shape: " + arg);
    }
}
