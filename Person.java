public class Person {
    private String namePer;
    private double balance;
    public Person(String namePer, double balance) {
        this.namePer = namePer;
        this.balance = balance;
    }
    public String getName() {
        return namePer;
    }
    public double getBalance() {
        return balance;
    }
    public boolean purchase(Item item, int quantity) {
        double price = item.getPrice();
        if (quantity * price <= balance) {
            balance = balance - quantity * price;
            return true;
        } else {
            return false;
        }
    }
    public void deposit(double amount) {
        if (amount > 0) {
            balance = balance + amount;
        }
    }
}
