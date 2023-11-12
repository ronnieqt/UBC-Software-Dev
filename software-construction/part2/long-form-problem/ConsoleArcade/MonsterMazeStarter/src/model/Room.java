package model;

import java.util.ArrayList;
import java.util.List;

public class Room extends Choice
{
    private List<Choice> choices;
    private int id;

    public Room(int id)
    {
        super("Enter Room " + id + ".");
        this.id = id;
        this.choices = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds m to next possible monsters
    public void addMonster(Monster m)
    {
        choices.add(m);
    }

    //MODIFIES: this
    //EFFECTS: adds t to next possible treasures
    public void addTreasure(Treasure t)
    {
        choices.add(t);
    }

    //MODIFIES: this
    //EFFECTS: adds r to next possible rooms
    public void addRoom(Room r)
    {
        choices.add(r);
    }

    //getters for gameplay
    public Choice getChoice(int i)
    {
        return choices.get(i);
    }

    public int getChoiceRange()
    {
        return choices.size();
    }

    @Override
    public void printOutcome()
    {
        System.out.println("You are now in Room " + id + ".\n");
        System.out.println("You have the following options: ");

        int counter = 0;

        for (Choice c : choices) {
            System.out.print("\tOption " + counter + ": ");
            c.printOptionMessage();
            ++counter;
        }
    }
}
