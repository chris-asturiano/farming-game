/**
 * A class that that represents each of the plots
 * in the whole farm, and it also contains its current
 * states.
 */
public class Plot {
    private boolean plowState = false; //Initialized at False 
    private boolean rocked; //initialized based on the read
    private boolean occupied; //if rocked is True, occupied is also True, not initialized
    private Crop cropHeld; //if there is a Crop, set occupied to True as well 
    
    /**
     * Constructor for this class
     * @param plowState
     * @param rocked
     * @param occupied
     * @param cropHeld
     */
    public Plot(boolean plowState, boolean rocked, boolean occupied, Crop cropHeld) {
        this.plowState = plowState;
        this.rocked = rocked;
        this.occupied = occupied;
        this.cropHeld = cropHeld;
    }

    /** 
     * @return boolean
     */
    public boolean getPlowState() {
        return plowState;
    }
    
    /** 
     * @param plowState
     */
    public void setPlowState(boolean plowState) {
        this.plowState = plowState;
    }
    
    /** 
     * @return boolean
     */
    public boolean getRocked() {
        return rocked;
    }
    
    /** 
     * @param rocked
     */
    public void setRocked(boolean rocked) {
        this.rocked = rocked;
    }
    
    /** 
     * @return boolean
     */
    public boolean getOccupied() {
        return occupied;
    }
    
    /** 
     * @param occupied
     */
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
    
    /** 
     * @return Crop
     */
    public Crop getCropHeld() {
        return cropHeld;
    }
    
    /** 
     * @param cropHeld
     */
    public void setCropHeld(Crop cropHeld) {
        this.cropHeld = cropHeld;
    }
} 