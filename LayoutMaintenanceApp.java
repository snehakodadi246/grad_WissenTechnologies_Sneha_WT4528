import java.io.*;
import java.util.*;

class Owner {
    int id;
    String name;
    String phone;

    Owner(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + phone;
    }
}

class Site {
    int id;
    int length;
    int width;
    String type;      // Open / Villa / Apartment / Independent
    String status;    // Open / Occupied
    Integer ownerId;  // null if open

    Site(int id, int length, int width, String type, String status, Integer ownerId) {
        this.id = id;
        this.length = length;
        this.width = width;
        this.type = type;
        this.status = status;
        this.ownerId = ownerId;
    }

    int area() {
        return length * width;
    }

    @Override
    public String toString() {
        return id + "," + length + "," + width + "," + type + "," + status + "," + ownerId;
    }
}

class Maintenance {
    int siteId, month, year;
    double amount;
    String status; // Paid / Pending

    Maintenance(int siteId, int month, int year, double amount, String status) {
        this.siteId = siteId;
        this.month = month;
        this.year = year;
        this.amount = amount;
        this.status = status;
    }

    @Override
    public String toString() {
        return siteId + "," + month + "," + year + "," + amount + "," + status;
    }
}

public class LayoutMaintenanceApp {

    static List<Owner> owners = new ArrayList<>();
    static List<Site> sites = new ArrayList<>();
    static List<Maintenance> maintenances = new ArrayList<>();

    static Scanner sc = new Scanner(System.in);

    static double calculateMaintenance(Site s) {
        int area = s.area();
        return s.status.equalsIgnoreCase("Open") ? area * 6 : area * 9;
    }

    static Site getSiteById(int id) {
        for (Site s : sites)
            if (s.id == id) return s;
        return null;
    }

    static void seedSites() {
        int id = 1;
        for (int i = 0; i < 10; i++) sites.add(new Site(id++, 40, 60, "Open", "Open", null));
        for (int i = 0; i < 10; i++) sites.add(new Site(id++, 30, 50, "Open", "Open", null));
        for (int i = 0; i < 15; i++) sites.add(new Site(id++, 30, 40, "Open", "Open", null));
    }

    public static void main(String[] args) {

        seedSites();

        while (true) {
            System.out.println("""
                    
                    ---- Layout Maintenance Application ----
                    1. Add Owner
                    2. View Owners
                    3. Assign Site to Owner
                    4. Add Monthly Maintenance
                    5. View Pending Maintenance
                    6. Mark Maintenance Paid
                    7. Exit
                    """);

            int choice = sc.nextInt();

            switch (choice) {

                case 1 -> {
                    System.out.print("Owner ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Phone: ");
                    String phone = sc.nextLine();
                    owners.add(new Owner(id, name, phone));
                    System.out.println("Owner added.");
                }

                case 2 -> {
                    for (Owner o : owners) {
                        System.out.println(o.id + " | " + o.name + " | " + o.phone);
                    }
                }

                case 3 -> {
                    System.out.print("Site ID: ");
                    int siteId = sc.nextInt();
                    System.out.print("Owner ID: ");
                    int ownerId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Type (Villa/Apartment/Independent): ");
                    String type = sc.nextLine();

                    Site s = getSiteById(siteId);
                    if (s != null) {
                        s.ownerId = ownerId;
                        s.type = type;
                        s.status = "Occupied";
                        System.out.println("Site assigned.");
                    } else {
                        System.out.println("Site not found.");
                    }
                }

                case 4 -> {
                    System.out.print("Site ID: ");
                    int siteId = sc.nextInt();
                    System.out.print("Month: ");
                    int m = sc.nextInt();
                    System.out.print("Year: ");
                    int y = sc.nextInt();

                    Site s = getSiteById(siteId);
                    if (s != null) {
                        double amt = calculateMaintenance(s);
                        maintenances.add(new Maintenance(siteId, m, y, amt, "Pending"));
                        System.out.println("Maintenance added. Amount = " + amt);
                    }
                }

                case 5 -> {
                    for (Maintenance m : maintenances) {
                        if (m.status.equals("Pending")) {
                            System.out.println(m.siteId + " | " + m.month + "/" + m.year + " | " + m.amount);
                        }
                    }
                }

                case 6 -> {
                    System.out.print("Site ID: ");
                    int siteId = sc.nextInt();
                    System.out.print("Month: ");
                    int m = sc.nextInt();
                    System.out.print("Year: ");
                    int y = sc.nextInt();

                    for (Maintenance mt : maintenances) {
                        if (mt.siteId == siteId && mt.month == m && mt.year == y) {
                            mt.status = "Paid";
                            System.out.println("Marked as Paid.");
                        }
                    }
                }

                case 7 -> System.exit(0);
            }
        }
    }
}
