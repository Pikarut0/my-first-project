package Services;

import java.util.Scanner;

public class IOOperations {
    private Scanner scanner;

    public IOOperations() {
        this.scanner = new Scanner(System.in);
    }

    public String readLine() {
        return scanner.nextLine();
    }

    public void print(String message) {
        System.out.println(message);
    }

    public int readInt() {
        while (!scanner.hasNextInt()) {
            print("Please enter a valid number:");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    public double readDouble() {
        while (!scanner.hasNextDouble()) {
            print("Please enter a valid number:");
            scanner.next();
        }
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }
}