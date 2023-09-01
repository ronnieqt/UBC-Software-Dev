package model;

import exceptions.BrokeException;
import exceptions.DidNotEat;

import java.util.List;

public class Manager {

    private final List<Animal> animalsToManage;
    private final Keeper keeper;

    public Manager(List<Animal> animals, Keeper keeper) {
        this.animalsToManage = animals;
        this.keeper = keeper;
    }

    public void manage() throws BrokeException {
        try {
            keeper.feed();
        }
        catch (DidNotEat e) {
            System.out.println("Get the doctor");
            throw new BrokeException();
        }
        finally {
            // will run regardless of whether an exception will be thrown or not
            System.out.println("Manager says regardless of what happens");
        }
    }


}