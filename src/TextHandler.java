import java.util.Scanner;

class TextHandler {
    private Scanner reader = new Scanner(System.in);
    void displayMenu() {
        System.out.println("Choose one of the following options:");
        System.out.println("1: Show a list of all nodes and their connected cities.");
        System.out.println("2: Initiate the program.");      
    }

    // userinput, returns int
    int userInt() {
        System.out.println("Your value: ");
        return reader.nextInt();
    }

    // userinput, returns double
    double userDouble() {
        System.out.println("Your value: ");
        return reader.nextDouble();
    }

    // userinput, returns String
    String userString() {
        System.out.println("Your value: ");
        return reader.nextLine();
    }

    int chooseCity() {
        System.out.println("1: Helsinki");
        System.out.println("2: Tammerfors");
        System.out.println("3: Åbo");
        System.out.println("4: Jyväskylä");
        System.out.println("5: Kuopio");
        System.out.println("6: Lahtis");
        return reader.nextInt();
    }
}
