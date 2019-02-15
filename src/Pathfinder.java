import java.util.ArrayList;
import java.util.Collections;

public class Pathfinder {

    public static void main(String[] args) {
		TextHandler menu = new TextHandler();
        ArrayList<Node> nodes = createGraph();
		menu.displayMenu();
		int choice = menu.userInt();

		switch(choice){
			case 1:
				printNodesAndNeighbours(nodes);
				break;
			case 2:
				runPathfinder(nodes);
				break;
		}
        
	
    }

    private static void runPathfinder(ArrayList<Node> nodes) {
        double tentativeG = 0;
        ArrayList<Node> openNodes = new ArrayList<>();
        ArrayList<Node> closedNodes = new ArrayList<>();
        TextHandler menu = new TextHandler();

        System.out.println("Choose a start node:");
        int startNode = menu.chooseCity();
        System.out.println("Choose an end node:");
        int endNode = menu.chooseCity();
        setStartEndNodes(startNode, endNode, nodes);

        openNodes.add(Node.startNode);
        //firstAttempt(openNodes, closedNodes);

        while (!openNodes.isEmpty()){
            Collections.sort(openNodes);
            if (openNodes.get(0) == Node.endNode){
                printBestPath();
                return;
            } else {
                Node.setCurrentNode(openNodes.get(0));
                openNodes.remove(0);
                closedNodes.add(Node.currentNode);
                for (Node neighbour : Node.currentNode.getNeighbours()){
                    if (!closedNodes.contains(neighbour)){
                        tentativeG = Node.currentNode.getTotalG() + Node.currentNode.calculateGTo(neighbour);
                        if (!openNodes.contains(neighbour)){
                            openNodes.add(neighbour);
                        } else if (tentativeG >= neighbour.getTotalG()){
                            neighbour.setPreviousNode(Node.currentNode);
                            neighbour.setTotalG(tentativeG);
                            neighbour.setTotalF(neighbour.getTotalG() + neighbour.calculateH());
                        }
                    }
                }
            }

        }

    }

    private static void printBestPath() {
        System.out.println(Node.endNode.getName());
        System.out.println(Node.endNode.getPreviousNode());

    }

    private static void firstAttempt(ArrayList<Node> openNodes, ArrayList<Node> closedNodes) {
        double previousF;
        double newF;
        System.out.println("startNode calc:");
        Node.startNode.calculateH();
        Node.startNode.calculateGTo(Node.startNode);
        previousF = Node.startNode.calculateH() + Node.startNode.calculateGTo(Node.startNode);
        Node.currentNode = Node.startNode;

        while (Node.currentNode != Node.endNode){

            for (Node neighbour : Node.currentNode.getNeighbours()){
                if(!closedNodes.contains(neighbour) && !openNodes.contains(neighbour)){
                    openNodes.add(neighbour);
                } // End first if
                newF = neighbour.calculateH() + neighbour.calculateGTo(Node.currentNode);

                if (newF < previousF){
                    previousF = newF;
                    neighbour.setPreviousNode(Node.currentNode);
                } // End second if

                closedNodes.add(Node.currentNode);
            }
            Node.setCurrentNode(openNodes.get(0));
        }
        System.out.println("Backtracked route:");
        while (Node.currentNode != Node.startNode){
            System.out.println(Node.currentNode.getName());
            Node.currentNode = Node.currentNode.getPreviousNode();
        }
    }

    private static void setStartEndNodes(int start, int end, ArrayList<Node> nodes) {
        switch (start){
            // HKI
            case 1:
                Node.startNode = nodes.get(0);
                break;
                // Tampere
            case 2:
                Node.startNode = nodes.get(1);
                break;
                // Turku
            case 3:
                Node.startNode = nodes.get(2);
                break;
                //Jyväskylä
            case 4:
                Node.startNode = nodes.get(3);
                break;
                //Kuopio
            case 5:
                Node.startNode = nodes.get(4);
                break;
                // Lahtis
            case 6:
                Node.startNode = nodes.get(5);
                break;
        }
        switch (end){
            // HKI
            case 1:
                Node.endNode = nodes.get(0);
                break;
                // Tampere
            case 2:
                Node.endNode = nodes.get(1);
                break;
                // Turku
            case 3:
                Node.endNode = nodes.get(2);
                break;
                //Jyväskylä
            case 4:
                Node.endNode = nodes.get(3);
                break;
                //Kuopio
            case 5:
                Node.endNode = nodes.get(4);
                break;
                // Lahtis
            case 6:
                Node.endNode = nodes.get(5);
                break;
        }
    }

    private static void printNodesAndNeighbours(ArrayList<Node> nodes) {
        for (Node node : nodes) {
            System.out.println(node.getName() + " is connected to ");
            for (Node neighbour : node.getNeighbours()){
                System.out.println("     " + neighbour.getName());
            }
            System.out.println();
        }
    }

    private static ArrayList<Node> createGraph()
    {
        //Skapar en nod för varje tågstation
        Node hki = new Node("Helsingfors", 60.1640504, 24.7600896);
        Node tpe = new Node("Tammerfors", 61.6277369, 23.5501169);
        Node tku = new Node("Abo", 60.4327477, 22.0853171);
        Node jyv = new Node("Jyväskylä", 62.1373432, 25.0954598);
        Node kpo = new Node("Kuopio", 62.9950487, 26.556762);
        Node lhi = new Node("Lahtis", 60.9948736, 25.5747703);

        //Förbindelser från Helsingfors tågstation
        hki.addNeighbour(tpe); //Tammerfors
        hki.addNeighbour(tku); //Åbo
        hki.addNeighbour(lhi); //Lahtis

        //Förbindelser från Tammerfors tågstation
        tpe.addNeighbour(hki); //Helsingfors
        tpe.addNeighbour(tku); //Åbo
        tpe.addNeighbour(jyv); //Jyväskylä
        tpe.addNeighbour(lhi); //Lahtis

        //Förbindelser från Åbo tågstation
        tku.addNeighbour(hki); //Helsingfors
        tku.addNeighbour(tpe); //Tammerfors

        //Förbindelser från Jyväskylä tågstation
        jyv.addNeighbour(tpe); //Tammerfors

        //Förbindelser från Kuopio tågstation
        kpo.addNeighbour(lhi); //Lahtis

        //Förbindelser från Lahtis tågstation
        lhi.addNeighbour(hki); //Helsingors
        lhi.addNeighbour(tpe); //Tammerfors
        lhi.addNeighbour(kpo); //Kuopio

        //Skapar en lista för grafen och sätter in alla noder
        ArrayList<Node> graph = new ArrayList<>();
        graph.add(hki);
        graph.add(tpe);
        graph.add(tku);
        graph.add(jyv);
        graph.add(kpo);
        graph.add(lhi);

        return graph;
    }

}

