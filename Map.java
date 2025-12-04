public class Map {
    private char[][] charMap;
    private float[][] costMap;

    public Map(char[][] charMap){
        this.charMap = charMap;
        costMap = generateCostMap();
    }
    
    public char[][] getCharMap(){
        return charMap;
    }

    public float[][] getCostMap(){
        return costMap;
    }

    private float[][] generateCostMap(){
        float[][] costMap = new float[charMap.length][charMap[0].length];        
        
        float cost = -1;
        for(int row=0; row < charMap.length; row++){
            for(int col=0; col < charMap[0].length; col++){
                switch(charMap[row][col]){
                    case 'M': cost= -1.0f; // "Mountain" ("Muntanya")
                    break;
                    case 'N': cost= 5.0f; //  "Empty" ("Buit")
                    break;
                    case 'A': cost= 4.0f; //  "Village" ("Aldea")
                    break;
                    case 'P': cost= 2.0f; //  "Town" ("Poble")
                    break;
                    case 'C': cost= 0.5f; //  "City" ("Ciutat")
                    break;
                }
                costMap[row][col] = cost;
            }
        }

        return costMap;
    }

    public String toString(){
        String text = "";

        for (char[] row : charMap) {
            for (char cell : row){
                text+=cell+" ";
            }
            text+="\n";
        }

        // Remove last enter
        text = text.substring(0, text.length()-1);

        return text;
    }
}
