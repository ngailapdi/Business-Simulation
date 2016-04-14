public class Business {
    private Item[] inventory = new Item[10];
    private String nameBusiness, m = "";
    private double totalSales = 0.0;
    private int totalTrans = 0, d = 0;
    private Item item;
    private int ans;
    public Business(String[][] input) {
        this.nameBusiness = input[0][0];
        for (int i = 1; i < input.length; i++) {
            item = new Item(input[i][0], Integer.parseInt(input[i][2]),
                           Double.parseDouble(input[i][1]));
            addItem(item);
        }
    }
    public String getNameBusiness() {
        return nameBusiness;
    }
    public String getBestSellingItem() {
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null) {
                if (inventory[i].numAttempts() > d) {
                    d = inventory[i].numAttempts();
                    m = inventory[i].toString();
                }
            }
        }
        return m;
    }
    public void sell(Person p, Item item, int quantity) {
        if (p.purchase(item, quantity)
            && item.getQuantity() >= quantity) {
            p.getBalance();
            item.setQuantity(item.getQuantity() - quantity);
            totalSales = totalSales + quantity * item.getPrice();
            totalTrans++;
        }
        item.increaseAttemptsBy(quantity);
    }
    public boolean addItem(Item item) {
        outerloop:
        for (Item element: inventory) {
            if (item.equals(element)) {
                ans = 1;
                break outerloop;
            } else {
                ans = 0;
            }
        }
        if (ans == 0) {
            if (inventory[inventory.length - 1] != null) {
                Item[] temp = new Item[inventory.length * 2];
                for (int i = 0; i < inventory.length; i++) {
                    temp[i] = inventory[i];
                }
                inventory = temp;
                inventory[countNum(inventory)] = item;
            } else {
                inventory[countNum(inventory)] = item;
            }
            return true;
        } else {
            return false;
        }
    }
    public int countNum(Item[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                count = count + 1;
            }
        }
        return count;
    }
    public int totalQuantity() {
        int total = 0;
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null) {
                total = total + inventory[i].getQuantity();
            }
        }
        return total;
    }
    public String getReport(int days, double execTime) {
        String str1 = String.format("Simulation report: %1$s", nameBusiness);
        String str2 = String.format("Execution time: %1$5.2fms", execTime);
        String str3 = String.format("%s", getEqualSigns());
        String str4 = String.format("Days of simulation:          %1$s", days);
        String str5 = String.format("Total Transactions:"
            + "          %1$s", totalTrans);
        String str6 = String.format("Total Revenue:"
            + "               $%4.2f", totalSales);
        String str7 = String.format("Number of Items in stock:"
            + "    %1$s", totalQuantity());
        String str8 = String.format("Best selling Item:"
            + "           \"%1$s\"", getBestSellingItem());
        String str9 = String.format("%s", getEqualSigns());
        String str = String.format("%s%n%s%n%s%n%s%n%s%n"
            + "%s%n%s%n%s%n%s%n", str1, str2, str3, str4, str5,
                                               str6, str7, str8, str9);
        return str;
    }
    public Item[] getInventory() {
        return inventory;
    }
    public static String getEqualSigns() {
        String s = "";
        for (int i = 1; i <= 41; i++) {
            s = s + "=";
        }
        return s;
    }
}