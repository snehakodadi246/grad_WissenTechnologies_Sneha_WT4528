import java.util.*;
import java.util.stream.*;

class Employee {
    String name;
    int age;
    String gender;
    double salary;
    String designation;
    String department;

    Employee(String name, int age, String gender,
             double salary, String designation, String department) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.designation = designation;
        this.department = department;
    }
}

public class Main {

    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee("Ravi", 45, "Male", 90000, "Manager", "IT"));
        employees.add(new Employee("Sneha", 28, "Female", 50000, "Developer", "IT"));
        employees.add(new Employee("Kiran", 35, "Male", 70000, "Tester", "QA"));
        employees.add(new Employee("Anita", 42, "Female", 85000, "Manager", "HR"));
        employees.add(new Employee("Rahul", 30, "Male", 60000, "Developer", "IT"));
        employees.add(new Employee("Pooja", 26, "Female", 45000, "Tester", "QA"));
        employees.add(new Employee("Suresh", 50, "Male", 95000, "Architect", "IT"));

        // 1. Highest salary
        Employee highest = employees.stream()
                .max(Comparator.comparingDouble(e -> e.salary))
                .get();
        System.out.println("Highest Salary: " + highest.name);

        // 2. Male & Female count
        long male = employees.stream().filter(e -> e.gender.equals("Male")).count();
        long female = employees.stream().filter(e -> e.gender.equals("Female")).count();
        System.out.println("Male: " + male + ", Female: " + female);

        // 3. Department wise expense
        System.out.println("Department Expense:");
        employees.stream()
                .collect(Collectors.groupingBy(
                        e -> e.department,
                        Collectors.summingDouble(e -> e.salary)
                ))
                .forEach((k, v) -> System.out.println(k + " : " + v));

        // 4. Top 5 senior employees
        System.out.println("Top 5 Seniors:");
        employees.stream()
                .sorted(Comparator.comparingInt((Employee e) -> e.age).reversed())
                .limit(5)
                .forEach(e -> System.out.println(e.name + " - " + e.age));

        // 5. Managers
        System.out.println("Managers:");
        employees.stream()
                .filter(e -> e.designation.equalsIgnoreCase("Manager"))
                .forEach(e -> System.out.println(e.name));

        // 6. Salary hike except managers
        System.out.println("Salary after hike:");
        for (Employee e : employees) {
            if (!e.designation.equalsIgnoreCase("Manager")) {
                e.salary *= 1.2;
            }
            System.out.println(e.name + " : " + e.salary);
        }

        // 7. Total employees
        System.out.println("Total Employees: " + employees.size());
    }
}
