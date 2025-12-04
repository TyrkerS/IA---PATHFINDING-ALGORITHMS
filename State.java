import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class State {

    private int row;
    private int col;
    private float cost;
    private float heuristic;
    private State parent;
    private int[] operators;
    private int stateVisit;

    // Constructor for initial state
    public State(int row, int col, float cost) {
        this.row = row;
        this.col = col;
        this.cost = cost;
        this.heuristic = 0;
        this.parent = null;
        this.operators = null;
        this.stateVisit = 0;
    }

    // Constructor for successor state
    public State(int row, int col, float cost, float heuristic, State parent, int[] operators) {
        this.row = row;
        this.col = col;
        this.cost = cost;
        this.heuristic = heuristic;
        this.parent = parent;
        this.operators = operators;
        this.stateVisit = 0;
    }

    // Reconstruct the path from the current state to the start state
    public static List<State> reconstructPath(State goalState, int visitedNodesCount) {
        List<State> path = new ArrayList<>();
        State current = goalState;
        goalState.setStateVisit(visitedNodesCount + 1);
        while (current != null) {
            path.add(0, current);
            current = current.getParent();
        }
        return path;
    }

    // Equals method to compare states
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        State state = (State) obj;
        return row == state.row && col == state.col;
    }

    // Getter methods
    public int getRow() { 
        return row; 
    }

    public int getCol() { 
        return col; 
    }

    public float getCost() { 
        return cost; 
    }

    public float getHeuristic() { 
        return heuristic; 
    }

    public State getParent() { 
        return parent; 
    }

    public int[] getOperators() { 
        return operators; 
    }

    public int getStateVisit() { 
        return stateVisit; 
    }

    // Setter methods
    public void setRow(int row) { 
        this.row = row; 
    }

    public void setCol(int col) { 
        this.col = col; 
    }

    public void setCost(float cost) { 
        this.cost = cost;
    }

    public void setHeuristic(float heuristic) { 
        this.heuristic = heuristic; 
    }

    public void setParent(State parent) { 
        this.parent = parent; 
    }

    public void setOperators(int[] operators) { 
        this.operators = operators; 
    }

    public void setStateVisit(int stateVisit) {
        this.stateVisit = stateVisit;
    }
    
    // Hash code method for hashing states
    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
