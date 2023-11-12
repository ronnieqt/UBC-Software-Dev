package observer;

import model.LectureModule;

import java.util.ArrayList;
import java.util.List;

public class Subject
{
    private List<LectureObserver> observers = new ArrayList<>();

    public void add_observer(LectureObserver lecture_observer)
    {
        if (!observers.contains(lecture_observer)) {
            observers.add(lecture_observer);
        }
    }

    public void nodify_observers(LectureModule lecture_module)
    {
        for (LectureObserver observer : observers) {
            observer.on_update(lecture_module);
        }
    }
}
