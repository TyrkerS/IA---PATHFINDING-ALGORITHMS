import java.util.List;
import java.util.Scanner;

public class Main {

    public static char[][] OriginalCharMap = {
        {'P','N','N','N','P','P','P','P','P','P'},
        {'P','N','N','N','M','M','P','P','N','P'},
        {'P','N','N','N','M','M','N','N','N','P'},
        {'P','A','A','A','A','A','A','N','N','N'},
        {'P','N','A','C','A','A','A','A','A','N'},
        {'P','A','A','C','M','C','C','A','A','A'},
        {'P','A','M','A','M','M','C','A','A','A'},
        {'A','A','M','A','M','C','C','P','M','P'},
        {'A','A','M','C','M','C','P','P','P','P'},
        {'A','A','C','C','M','C','C','C','C','C'},
    };
    public static Map OriginalMap = new Map(OriginalCharMap);

    public static char[][] CustomCharMap = {
        {'C','P','P','M','N'},
        {'M','C','C','A','A'},
        {'M','P','A','A','P'},
        {'A','A','A','P','A'},
        {'P','P','N','C','C'},
    };
    public static Map CustomMap = new Map(CustomCharMap);

    private static String toStringResult(List<State> states, long time) {
        if (states == null) {
            return "No solution found\n";
        }
        
        StringBuilder result = new StringBuilder();
        
        // Show path directions
        result.append("Path:\n[START ");
        for (int i = 1; i < states.size(); i++) {
            State prev = states.get(i - 1);
            State curr = states.get(i);
            int[] operators = { curr.getRow() - prev.getRow(), curr.getCol() - prev.getCol() };
    
            if (operators[0] == -1 && operators[1] == 0) {
                result.append("-> UP ");
            } else if (operators[0] == 1 && operators[1] == 0) {
                result.append("-> DOWN ");
            } else if (operators[0] == 0 && operators[1] == -1) {
                result.append("-> LEFT ");
            } else if (operators[0] == 0 && operators[1] == 1) {
                result.append("-> RIGHT ");
            }
        }
        result.append("]\n\n");
        
        // Show states and costs
        for (int i = 0; i < states.size(); i++) {
            State state = states.get(i);
            result.append("[(").append(state.getRow()).append(", ").append(state.getCol())
                  .append(") | Cost = ").append(state.getCost()).append("]");
            
            if (i < states.size() - 1) {
                result.append("  ->  ");
            }
            
            if ((i + 1) % 4 == 0) {
                result.append("\n");
            }
        }
        result.append("\n\n");
        
        // Show summary
        State lastState = states.get(states.size() - 1);
        result.append("Number of visited nodes: ").append(lastState.getStateVisit()).append("\n");
        result.append("Total of node solution: ").append(states.size()).append("\n");
        result.append("Time: ").append(time).append("ms\n");
        result.append("Total cost: ").append(lastState.getCost()).append("\n");

        // Check if the solution is optimal
        if(states.get(states.size() - 1).getCost() == 33.0 ){
            result.append("The solution is optimal\n");
        }else{
            if (states.get(states.size() - 1).getCost() == 22.5){
                result.append("The solution is optimal\n");
            } else {
                result.append("The solution is not optimal\n");
            }

        }
        return result.toString();
    }

    
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        //  Declare map and initial/target states
        Map[] maps = {OriginalMap, CustomMap};
        State[] initialStates = {new State(0, 0, 2), new State(0, 0, 2)};
        State[] targetStates = {new State(9, 9, 0), new State(4, 4, 0)};

        //  Declare heuristics
        Heuristic[] heuristics = new Heuristic[3];
        heuristics[0] = Heuristics::Heuristic1;
        heuristics[1] = Heuristics::Heuristic2;
        heuristics[2] = Heuristics::Heuristic3;

        while (true) {
            // Select search algorithm
            System.out.println("Select search algorithm:");
            System.out.println("1. Best First");
            System.out.println("2. A*");
            System.out.println("3. Exit");
            int searchChoice = scanner.nextInt();
            if (searchChoice == 3) {
                System.out.println("Exiting...");
                break;
            }
            searchChoice -= 1;

            // Select heuristic
            System.out.println("Select heuristic:");
            System.out.println("1. Heuristic 1");
            System.out.println("2. Heuristic 2");
            System.out.println("3. Heuristic 3");
            System.out.println("4. Exit");
            int heuristicChoice = scanner.nextInt();
            if (heuristicChoice == 4) {
                System.out.println("Exiting...");
                break;
            }
            heuristicChoice -= 1;

            // Select map
            System.out.println("Select map:");
            System.out.println("1. Original Map");
            System.out.println("2. Custom Map");
            System.out.println("3. Exit");
            int mapChoice = scanner.nextInt();
            if (mapChoice == 3) {
                System.out.println("Exiting...");
                break;
            }
            mapChoice -= 1;

            // Initialize search algorithm based on user choice
            Search search;
            if (searchChoice == 0) {
                search = new BestFirst(maps[mapChoice].getCostMap(), heuristics[heuristicChoice]);
            } else {
                search = new AStar(maps[mapChoice].getCostMap(), heuristics[heuristicChoice]);
            }

            // Run experiments
            long startTime = System.currentTimeMillis();
            List<State> result = search.DoSearch(initialStates[mapChoice], targetStates[mapChoice]);
            long endTime = System.currentTimeMillis();

            // Show results
            System.out.println("Algorithm: " + search.getClass().getSimpleName() + " / Heuristic: " + (heuristicChoice + 1) + " / Map: " + (mapChoice == 0 ? "OriginalMap" : "CustomMap"));
            System.out.println("Results:");
            System.out.println(toStringResult(result, endTime - startTime));
        }

        // Close the scanner
        scanner.close();
    }
}