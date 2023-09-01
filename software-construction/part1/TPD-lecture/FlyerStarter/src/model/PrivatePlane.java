package model;

public class PrivatePlane extends Airplane
{
    @Override
    public void serveSnacks() {
        super.serveSnacks();
        System.out.println("Serving advanced snacks!");
    }

    public void bring_warm_towels()
    {
        System.out.println("Here is your warm towel");
    }
}
