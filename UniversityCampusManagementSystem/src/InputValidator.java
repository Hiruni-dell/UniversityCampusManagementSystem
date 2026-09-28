import java.util.Scanner;

public class InputValidator {
    public static String readNonEmpty(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) return value;
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    public static double readMarks(Scanner scanner) {
        while (true) {
            System.out.print("Enter marks (0-100): ");
            String input = scanner.nextLine().trim();

            try {
                double marks = Double.parseDouble(input);
                if (marks >= 0 && marks <= 100) return marks;
                System.out.println("Invalid marks. Enter a value from 0 to 100.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter numeric marks.");
            }
        }
    }

    public static int readMenuChoice(Scanner scanner, int min, int max) {
        while (true) {
            System.out.print("Enter your choice (" + min + "-" + max + "): ");
            String input = scanner.nextLine().trim();

            try {
                int choice = Integer.parseInt(input);
                if (choice >= min && choice <= max) return choice;
                System.out.println("Choice out of range.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number.");
            }
        }
    }
}
