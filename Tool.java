/**
 * A class that represents the tools which 
 * the player can use in the game.
 */
public class Tool {
    private String name;
    private int cost;
    private double expGain;

    /**
     * Constructor for this class
     * @param name
     * @param cost
     * @param expGain
     */
    public Tool(String name, int cost, double expGain) {
        this.name = name;
        this.cost = cost;
        this.expGain = expGain;
    }

    /** 
     * @return String
     */
    public String getName() {
        return name;
    }
    /** 
     * @return int
     */
    public int getCost() {
        return cost;
    }
    
    /** 
     * @return double
     */
    public double getExpGain() {
        return expGain;
    }
 
    /** 
     * @param expGain
     */
    public void setExpGain(double expGain) {
        this.expGain = expGain;
    }
}