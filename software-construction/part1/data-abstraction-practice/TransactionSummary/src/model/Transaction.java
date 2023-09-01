package model;

public class Transaction
{
    private String m_name;
    private String m_date;
    private int m_amount;
    private TransactionType m_type;

    public Transaction(String name, String date, int amount, TransactionType type)
    {
        m_name = name;
        m_date = date;
        m_amount = amount;
        m_type = type;
    }

    // getters
    public String getName()
    {
        return m_name;
    }
    public String getDate()
    {
        return m_date;
    }
    public int getAmount()
    {
        return m_amount;
    }
    public TransactionType getType()
    {
        return m_type;
    }
}
