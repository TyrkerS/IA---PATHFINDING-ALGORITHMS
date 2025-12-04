import java.util.ArrayList;
import java.util.List;

public abstract class Search {
    private float[][] costMap;
    private Heuristic heuristic;

    public Search(float[][] costMap, Heuristic heuristic) {
        this.costMap = costMap;
        this.heuristic = heuristic;
    }

    abstract List<State> DoSearch(State initialState, State targetState);

    public List<State> EvaluateOperators(State currentState, State targetState) {
        List<State> successors = new ArrayList<>();
        List<int[]> directions = List.of(
            new int[]{-1, 0}, // Up
            new int[]{1, 0},  // Down
            new int[]{0, -1}, // Left
            new int[]{0, 1}   // Right
        );

        float[][] costMap = getCostMap();

        for (int[] dir : directions) {
            int newRow = currentState.getRow() + dir[0];
            int newCol = currentState.getCol() + dir[1];

            // Ensure new position is within bounds and accessible
            if (isValidPosition(newRow, newCol, costMap)) {
                float newCost = currentState.getCost() + costMap[newRow][newCol];
                float heuristicValue = getHeuristic().Evaluate(new State(newRow, newCol, newCost), targetState, costMap);
                State successor = new State(newRow, newCol, newCost, heuristicValue, currentState, dir);
                successors.add(successor);
            }
        }
        return successors;
    }

    // Check if the new position is valid (within bounds and accessible)
    private boolean isValidPosition(int row, int col, float[][] costMap) {
        return row >= 0 && row < costMap.length && col >= 0 && col < costMap[0].length && costMap[row][col] != -1;
    }
    
    // Getters and Setters
    public float[][] getCostMap() {
        return costMap;
    }

    public Heuristic getHeuristic() {
        return heuristic;
    }

    public void setCostMap(float[][] costMap) {
        this.costMap = costMap;
    }

    public void setHeuristic(Heuristic heuristic) {
        this.heuristic = heuristic;
    }

}
