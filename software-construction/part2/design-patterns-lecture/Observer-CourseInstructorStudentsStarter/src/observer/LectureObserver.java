package observer;

import model.LectureModule;

public interface LectureObserver {
    public void on_update(LectureModule lecture_module);
}
