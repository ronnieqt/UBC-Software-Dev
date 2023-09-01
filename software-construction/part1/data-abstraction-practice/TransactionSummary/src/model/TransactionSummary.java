package model;

import java.util.ArrayList;
import java.util.List;

public class TransactionSummary
{
    private String m_name;
    private List<Transaction> m_transactions;

    public TransactionSummary(String name)
    {
        m_name = name;
        m_transactions = new ArrayList<Transaction>();
    }

    // getters
    public String getName()
    {
        return m_name;
    }
    public List<Transaction> getTransactions()
    {
        return m_transactions;
    }
    public int getNumTransactions()
    {
        return m_transactions.size();
    }

    // REQUIRES: t is not already within transactions
    // MODIFIES: this
    // EFFECTS: adds the given transaction t to the list of transactions
    public void addTransaction(Transaction t)
    {
        m_transactions.add(t);
    }

    // REQUIRES: transactions is non-empty
    // EFFECTS: computes the average amount of money spent on a transaction
    public double averageTransactionCost()
    {
        int n = 0;
        double sum = 0.0;
        for (Transaction t : m_transactions) {
            n += 1;
            sum += t.getAmount();
        }
        return sum / n;
    }

    // REQUIRES: transactions is non-empty
    // EFFECTS:  returns the average cost of all transactions of type specificType in this TransactionSummary
    public double specificTypeAverage(TransactionType specificType)
    {
        int n = 0;
        double sum = 0.0;
        for (Transaction t : m_transactions) {
            if (t.getType().equals(specificType)) {
                n += 1;
                sum += t.getAmount();
            }
        }
        return (n > 0) ? sum/n : -1.0;
    }

    // REQUIRES: transactions is non-empty
    // EFFECTS: returns the largest transaction (in terms of cost) in this TransactionSummary
    public Transaction largestTransaction()
    {
        Transaction t_largest = null;
        for (Transaction t : m_transactions) {
            if (t_largest == null || t.getAmount() > t_largest.getAmount()) {
                t_largest = t;
            }
        }
        return t_largest;
    }

    // EFFECTS: returns true if the given transaction is contained within the list of transactions
    public boolean contains(Transaction t)
    {
        boolean is_in = false;
        for (Transaction transaction : m_transactions) {
            if (transaction.equals(t)) {
                is_in = true;
                break;
            }
        }
        return is_in;
    }
}
