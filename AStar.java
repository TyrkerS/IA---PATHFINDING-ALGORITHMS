import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AStar extends Search {
    public AStar(float[][] costMap, Heuristic heuristic) {
        super(costMap, heuristic);
    }

    @Override
    List<State> DoSearch(State startState, State goalState) {
        List<State> openSet = new ArrayList<>();
        List<State> closedSet = new ArrayList<>();
        boolean goalReached = false;

        openSet.add(startState);

        // While the open set is not empty and the goal hasn't been found
        while (!openSet.isEmpty() && !goalReached) {
            // Get the first state from the open set
            State current = openSet.remove(0);

            // Check if the current state is the goal state
            if (current.equals(goalState)) {
                goalReached = true;
                return State.reconstructPath(current, closedSet.size());
            } else {
                // Generate successors for the current state
                List<State> successors = EvaluateOperators(current, goalState);

                for (State successor : successors) {
                    // Add the successor to the open set if it hasn't been evaluated yet
                    if (!closedSet.contains(successor)) {
                        if (!openSet.contains(successor)) {
                            openSet.add(successor);
                        } else {
                            // Update the cost if the new path is better
                            int index = openSet.indexOf(successor);
                            if (successor.getCost() < openSet.get(index).getCost()) {
                                openSet.get(index).setCost(successor.getCost());
                                openSet.get(index).setParent(successor.getParent());
                            }
                        }
                    }
                }
                // Sort the open set by f(n) = g(n) + h(n)
                openSet.sort(Comparator.comparing(state -> state.getHeuristic() + state.getCost()));
                closedSet.add(current);
            }
        }
        return null; // Return null if the goal was not reached
    }
}
