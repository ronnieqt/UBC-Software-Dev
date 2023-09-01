package ui;

import model.*;

public class FlyerDemo {

    // NOTE: this project goes with the Subclasses & Extends, Pt. 2 (Extending) Video

    public static void main(String[] args) {
        Bird birdie = new Bird();
        Airplane planie = new Airplane();
        Cafe cafePlane = new Airplane();
        Flyer flyerPlane = new Airplane();
        PrivatePlane pp = new PrivatePlane();

        pp.bring_warm_towels();

        birdie.fly();
        cafePlane.serveDrinks();

        FlyerDemo fd = new FlyerDemo();
        fd.lunchService(cafePlane);
        fd.firstPartOfFlight(planie);
        fd.luxury_takeoff(pp);
    }

    public void lunchService(Cafe c){
        c.serveDrinks();
        c.serveSnacks();
    }

    public void firstPartOfFlight(Airplane a){
        a.takeOff();
        a.serveDrinks();
    }

    public void luxury_takeoff(PrivatePlane pp)
    {
        pp.takeOff();
        pp.bring_warm_towels();
        pp.serveDrinks();
    }
}