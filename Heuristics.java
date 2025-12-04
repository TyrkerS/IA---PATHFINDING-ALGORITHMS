public class Heuristics {
    public static float Heuristic1(State currentState, State targetState, float[][] map){
        int dx = Math.abs(currentState.getRow() - targetState.getRow());
        int dy = Math.abs(currentState.getCol() - targetState.getCol());
        return (dx + dy);
    }

    public static float Heuristic2(State currentState, State targetState, float[][] map){
        int dx = Math.abs(currentState.getRow() - targetState.getRow());
        int dy = Math.abs(currentState.getCol() - targetState.getCol());
        float minCost = 0.5f;
        return (dx + dy) * minCost;
    }
        

    public static float Heuristic3(State currentState, State targetState, float[][] map){
        // Distance in rows and columns to the target state
        int dx = Math.abs(currentState.getRow() - targetState.getRow());
        int dy = Math.abs(currentState.getCol() - targetState.getCol());
    
        // Current state cost
        float currentCost = currentState.getCost();
        // Estimate the total cost considering the distance and costs of adjacent cells
        float totalCost = currentCost + (dx + dy);   
        // Consider the costs of adjacent cells
        float adjCellsCost = 0;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Directions: up, down, left, right
        for (int[] dir : directions) {
            int newRow = currentState.getRow() + dir[0];
            int newCol = currentState.getCol() + dir[1];
            if (newRow >= 0 && newRow < map.length && newCol >= 0 && newCol < map[0].length) {
                adjCellsCost += map[newRow][newCol];
            }
        }
        // Adjust the total cost considering the costs of adjacent cells
        totalCost += adjCellsCost;
        return totalCost;
    }   
}
