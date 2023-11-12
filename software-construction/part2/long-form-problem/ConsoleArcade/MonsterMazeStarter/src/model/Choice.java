package model;

public abstract class Choice
{
    private String optionMessage;

    public Choice(String message)
    {
        this.optionMessage = message;
    }

    //EFFECTS: prints a message representing this possible next choice
    public void printOptionMessage()
    {
        System.out.println(optionMessage);
    }

    public abstract void printOutcome();
}
