import java.util.LinkedList;

import static java.lang.System.out;

public class SimpleAccount implements BankAccount {
    private final LinkedList<String> transactionalHistory = new LinkedList<>();
    private final DateTime dateTime;
    private int balance = 0;

    public SimpleAccount(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void deposit(int amount) {
        balance += amount;
        transactionalHistory.add(getCurrentTime() + " | " + amount + " | " + balance);
    }

    public void withdraw(int amount) {
        deposit(-amount);
    }

    public void printStatement() {
        out.println("DATE | AMOUNT | BALANCE");

        transactionalHistory
                .descendingIterator()
                .forEachRemaining(out::println);
    }

    private String getCurrentTime() {
        return dateTime.getTimeString();
    }
}
