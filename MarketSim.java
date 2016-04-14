
/**
 * This
 * @author Thomas Lilly
 * @version 1.0
 * @name Ngoc Anh Thai
 * Collaboration: "I worked on the homework assignment alone, using only course materials."
 */
import java.util.Random;
public class MarketSim {

    /* Please use this array to test your classes.
     * Feel free to add data to test resizing
     */
    private static String[][] businessData = {
        {"Amazon"},
        {"Fitbit ChargeHR", "129.89", "4"},
        {"Amazon Echo", "179.99" , "3"},
        {"Roses", "139.99", "10"},
        {"Lindt Chocolate", "30.40", "5"},
        {"Roses", "139.99", "10"},
        {"Jaybird X2", "128.50", "2"},
        {"Football", "28.44", "17"},
        {"Shawl", "12.99", "20"},
        {"Shawl", "12.99", "20"},
        {"CLRS", "66.32", "21"},
        {"USB Micro-USB to USB Cable", "4.96", "12"},
        {"Stationery", "9.89", "5"},
        {"Shawl", "12.99", "20"},
        {"Wand", "5.90", "30"}
    };
    private static int days, people, a = 0, b = 0;
    private static String strA;

    public static void main(String[] args) {
        //Write the simulation code here
        if (args.length == 0) {
            people = 5;
            days = 31;
            strA = String.format("Running simulation with %s "
            + "people over %s days...%n", people, days);
            System.out.println(strA);
        } else if (args.length == 2) {
            if (args[0].equals("-p")) {
                people = Integer.parseInt(args[1]);
                days = 31;
                strA = String.format("Running simulation with %s "
                + "people over %s days...%n", people, days);
                System.out.println(strA);
            } else if (args[0].equals("-d")) {
                days = Integer.parseInt(args[1]);
                people = 5;
                strA = String.format("Running simulation with %s "
                        + "people over %s days...%n", people, days);
                System.out.println(strA);
            }
        } else if (args.length == 4) {
            if (args[0].equals("-p") && args[2].equals("-d")) {
                people = Integer.parseInt(args[1]);
                days = Integer.parseInt(args[3]);
                strA = String.format("Running simulation with %s "
                + "people over %s days...%n", people, days);
                System.out.println(strA);
            } else if (args[0].equals("-d") && args[2].equals("-p")) {
                people = Integer.parseInt(args[3]);
                days = Integer.parseInt(args[1]);
                strA = String.format("Running simulation with %s "
                + "people over %s days...%n", people, days);
                System.out.println(strA);
            }
        } else {
            strA = "Usage: java MarketSim [-p <number "
                + "of people> -d <number of days>]";
            System.out.println(strA);
        }
        long a = System.nanoTime();
        Person[] perArr = new Person[people];
        Random gen = new Random();
        Business bus = new Business(businessData);
        for (int i = 1; i <= people; i++) {
            double bal = gen.nextInt(49999) + 50001;
            Person per = new Person("person " + genName(), bal);
            perArr[i - 1] = per;
        }
        int counting = bus.countNum(bus.getInventory());
        for (int i = 1; i <= days; i++) {
            for (int j = 1; j <= people; j++) {
                double chance = gen.nextDouble();
                if (chance > 0.25) {
                    int leg = bus.getInventory().length;
                    int selection = gen.nextInt(counting);
                    int q = gen.nextInt(5) + 1;
                    bus.sell(perArr[j - 1], bus.getInventory()[selection], q);
                }
            }
        }
        long b = System.nanoTime();
        if (!strA.equals("Usage: java MarketSim [-p <number "
            + "of people> -d <number of days>]")) {
            System.out.println(bus.getReport(days, (b - a) * Math.pow(10, -6)));
        }
    }
    private static int genName() {
        a++;
        return a;
    }
    private static int genName2() {
        b++;
        return b;
    }
}
