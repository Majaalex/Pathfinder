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

    String chooseCity() {
        System.out.println("Helsinki");
        System.out.println("Karjaa");
        System.out.println("Turku");
        System.out.println("Tampere");
        System.out.println("Pori");
        System.out.println("Seinäjoki");
        System.out.println("Vaasa");
        System.out.println("Ylivieska");
        System.out.println("Oulu");
        System.out.println("Rovaniemi");
        System.out.println("Kajaani");
        System.out.println("Kuopio");
        System.out.println("Joensuu");
        System.out.println("Parikkala");
        System.out.println("Jyväskylä");
        System.out.println("Lahtis");
        System.out.println("Kouvola");
        return reader.next();
    }
}
