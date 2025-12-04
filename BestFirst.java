import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BestFirst extends Search {
    public BestFirst(float[][] costMap, Heuristic heuristic) {
        super(costMap, heuristic);
    }

    @Override
    List<State> DoSearch(State startState, State goalState) {
        List<State> openSet = new ArrayList<>();
        List<State> closedSet = new ArrayList<>();
        boolean goalReached = false;

        openSet.add(startState);

        // Continue searching while there are states to explore and the target hasn't been found.
        while (!openSet.isEmpty() && !goalReached) {
            // Take the first state from the open set
            State currentState = openSet.remove(0);

            // Check if the current state is the goal
            if (currentState.equals(goalState)) {
                goalReached = true;
                return State.reconstructPath(currentState, closedSet.size());
            } else {
                // Generate neighbors for the current state
                List<State> neighbors = EvaluateOperators(currentState, goalState);

                for (State neighbor : neighbors) {
                    // Add the neighbor to the open set if it hasn't been evaluated yet
                    if (!closedSet.contains(neighbor) && !openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }

                // Sort the open set by heuristic value (best first)
                openSet.sort(Comparator.comparing(State::getHeuristic));
                closedSet.add(currentState);
            }
        }
        return null; // If the goal was not reached, return null
    }
}
