import java.util.HashMap;
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

    String chooseCity(HashMap<String, Node> nodeHashMap) {
        for (Node node : nodeHashMap.values()){
            System.out.println(node.getName());
        }
        return reader.next();
    }
}
