import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

/**
 * A class that represents the player which contains 
 * its attributes and the functions that it can perform.
 */
public class PlayerSys {
    private String name; //inputted by the user
    private double exp;
    private int level;
    private double objectCoins;  
    private int farmerType = 0;
    private String[] farmerTypeName = {"Farmer", "Registered", "Distinguished", "Legendary"};


    /**
     * Constructor for this class
     * @param name
     * @param exp
     * @param level
     * @param objectCoins
     * @param farmerType
     */
    public PlayerSys(String name, double exp, int level, double objectCoins, int farmerType) {
        this.name = name;
        this.exp = exp;
        this.level = level;
        this.objectCoins = objectCoins;
        this.farmerType = farmerType;
    }

    /**
     * Sets the bonus for the crops based on the farmer type
     * @param arrayPlot : array of plots
     * @param cropList : ArrayList of crops
     * @param seedCostReduction : the amount that will reduce the cost of the seeds
     * @param waterBonusIncrease : the amount that will increase the water bonus
     * @param fertilizerBonusIncrease : the amount that will increase the fertilizer bonus
     */
    public void setBonus(Plot arrayPlot[][], ArrayList<Crop> cropList, int seedCostReduction, int waterBonusIncrease, int fertilizerBonusIncrease) {
        //FOR ALL EXISTING CROPS ON THE FARM
        for (int i = 0; i < arrayPlot.length; i++) {
            for (int j = 0; j < arrayPlot[0].length; j++) {
                //Update Water Bonus limit
                //Update fertilizer bonus limit 
                if (arrayPlot[i][j].getCropHeld() != null) {
                    arrayPlot[i][j].getCropHeld().setWaterBonus(arrayPlot[i][j].getCropHeld().getWaterBonus() + waterBonusIncrease);
                    arrayPlot[i][j].getCropHeld().setFertilizerBonus(arrayPlot[i][j].getCropHeld().getFertilizerBonus() + fertilizerBonusIncrease);
                }
            }
        }
        //FOR FUTURE CROPS
        for (int i = 0; i < cropList.size(); i++) {
            //Update Seed Cost
            //Update Water Bonus Limit
            //Update Fertilizer Bonus Limit
            cropList.get(i).setSeedCost(cropList.get(i).getSeedCost() + seedCostReduction);
            cropList.get(i).setWaterBonus(cropList.get(i).getWaterBonus() + waterBonusIncrease);
            cropList.get(i).setFertilizerBonus(cropList.get(i).getFertilizerBonus() + fertilizerBonusIncrease);
        }
    }

    /**
     * Method that registers the farmer
     * @param frame : the JFrame to be used to display a message dialog
     * @param arrayPlot : array of plots
     * @param cropList : ArrayList of crops
     */
    public void Register(JFrame frame, Plot arrayPlot[][], ArrayList<Crop> cropList) {
        //Registered Farmer
        if (farmerType == 0) {
            if (level >= 5 && objectCoins >= 200) {
                farmerType = 1;
                objectCoins -= 200;
                setBonus(arrayPlot, cropList, -1, 0, 0);
                JOptionPane.showMessageDialog(frame, "You are now a Registered Farmer!");
            }
            else {
                JOptionPane.showMessageDialog(frame, "You do not meet the requirements to be a Registered Farmer. (Level 5 and 200 ObjectCoins)");
            }
        }
        //Distinguished Farmer
        else if (farmerType == 1) {
            if (level >= 10 && objectCoins >= 300) {
                farmerType = 2;
                objectCoins -= 300;
                setBonus(arrayPlot, cropList, -1, 1, 0);
                JOptionPane.showMessageDialog(frame, "You are now a Distinguished Farmer!");
            }
            else {
                JOptionPane.showMessageDialog(frame, "You do not meet the requirements to be a Distinguished Farmer. (Level 10 and 300 ObjectCoins)");

            }
        }

        //Legendary Farmer
        else if (farmerType == 2) {
            if (level >= 15 && objectCoins >= 400) {
                farmerType = 3;
                objectCoins -= 400;
                setBonus(arrayPlot, cropList, -1, 1, 1);
                JOptionPane.showMessageDialog(frame, "You are now a Legendary Farmer!");

            }
            else {
                JOptionPane.showMessageDialog(frame, "You do not meet the requirements to be a Legendary Farmer. (Level 15 and 400 ObjectCoins)");
            }
        }
        //If farmer is already a Legendary Farmer, they cant register anymore
        else if (farmerType >= 3) {
            JOptionPane.showMessageDialog(frame, "You can't register anymore, you are a LEGENDARY FARMER!!");
        }
    }

    /**
     * Gets the bonus earnings based on the farmer type
     * @return the bonus earnings
     */
    public int getBonusEarnings() {
        if (farmerType == 0) //Default Farmer
            return 0; 

        if (farmerType == 1) //Registered Farmer
            return 1;

        if (farmerType == 2) //Distinguished Farmer
            return 2;

        if (farmerType == 3) //Legendary Farmer
            return 4;

        return -1;
    }

    /** 
     * @return String
     */
    public String getName() {
        return name;
    }
    
    /** 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /** 
     * @return double
     */
    public double getExp() {
        return exp;
    }
    
    /** 
     * @param exp
     */
    public void setExp(double exp) {
        this.exp = exp;
    }
    
    /** 
     * @return int
     */
    public int getLevel() {
        return level;
    }
    
    /** 
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
    }
    
    /** 
     * @return double
     */
    public double getObjectCoins() {
        return objectCoins;
    }
    
    /** 
     * @param objectCoins
     */
    public void setObjectCoins(double objectCoins) {
        this.objectCoins = objectCoins;
    }
    
    /** 
     * @return int
     */
    public int getFarmerType() {
        return farmerType;
    }
    
    /** 
     * @param farmerType
     */
    public void setFarmerType(int farmerType) {
        this.farmerType = farmerType;
    } 
    
  
    /** 
     * Creates all the crops that the player can plant
     * @param shopList : ArrayList of type Crop that contains all the crops to be used 
     *                   by the player
     */
    public void initializeCrops(ArrayList<Crop> shopList) {
        Crop turnip = new Crop("Turnip", "Root", false, false, 5, 2, 0, 0, 1, 0, 0, 5.0, 6, 2, 1, 1, 2, 2);
        Crop carrot = new Crop("Carrot", "Root", false, false, 10, 3, 0, 0, 1, 0, 0, 7.5, 9, 2, 1, 1, 2, 3);
        Crop potato = new Crop("Potato", "Root", false, false, 20, 5, 0, 0, 3, 1, 0, 12.5, 3, 4, 2, 1, 10, 5);
        Crop roseFlower = new Crop("Rose", "Flower", false, false, 5, 1, 0, 0, 1, 0, 0, 2.5, 5, 2, 1, 1, 1, 1);
        Crop turnipsFlower = new Crop("Turnips", "Flower", false, false, 10, 2, 0, 0, 2, 0, 0, 5.0, 9, 3, 1, 1, 1, 2);
        Crop sunFlower = new Crop("Sunflower", "Flower", false, false, 20, 3, 0, 0, 2, 1, 0, 7.5, 19, 3, 2, 1, 1, 3);
        Crop mangoTree = new Crop("Mango", "Fruit tree", false, false, 100, 10, 0, 0, 7, 4, 0, 25.0, 8, 7, 4, 5, 15, 10);
        Crop appleTree = new Crop("Apple", "Fruit tree", false, false , 200, 10, 0, 0, 7, 5, 0, 25.0, 5, 7, 5, 10, 15, 10);

        //add the objects to the list
        shopList.add(turnip);
        shopList.add(carrot);
        shopList.add(potato);
        shopList.add(roseFlower);
        shopList.add(turnipsFlower);
        shopList.add(sunFlower);
        shopList.add(mangoTree);
        shopList.add(appleTree);        
    }
    
    /** 
     * Creates all the tools that will be inside the toolList, 
     * allowing the player to gain access to them.
     * @param toolList : ArrayList of type Tool that contains all the tools to be
     *                   used by the player
     */
    public void initializeTools(ArrayList<Tool> toolList){
        Tool Plow = new Tool("Plow", 0, 0.5);
        Tool WateringCan = new Tool("Watering Can", 0, 0.5);
        Tool Fertilizer = new Tool("Fertilizer",10, 4);
        Tool Pickaxe = new Tool("Pickaxe",50, 15);
        Tool Shovel = new Tool("Shovel",7, 2);

        //add the objects to the list
        toolList.add(Plow);
        toolList.add(WateringCan);
        toolList.add(Fertilizer);
        toolList.add(Pickaxe);
        toolList.add(Shovel);
    }

    

    
    /** 
     * Determines the attributes of the specific tile, then if applicable, plant the crop in that tile.
     * @param frame : the frame 
     * @param plotTile : The tile that is selected by the player and will be checked.
     * @param crop : The crop that the player wishes to plant.
     */
    public void Plant(JFrame frame, Plot plotTile, Crop crop, Plot[][] arrayPlot, int r, int c) { //consider the TYPE
        Crop cropCpy = new Crop(crop);
        //If player has enough objectCoins
        if (objectCoins >= crop.getSeedCost()) {
            //Tile isn't occupied by another crop, and is plowed.
            if (plotTile.getOccupied() == false && plotTile.getPlowState() == true) {
                //If its a fruit tree, check if its on the edge and check if tiles beside it are occupied
                if (crop.getType().equals("Fruit tree")) {
                    
                    //Check if on edge
                    if (r == 0 || r == 9 || c == 0 || c == 4)
                        JOptionPane.showMessageDialog(frame, "You can't plant a Fruit tree on the edge of the farm!");
                    
                    //Check beside tiles
                    else if (arrayPlot[r-1][c].getOccupied() == true || arrayPlot[r+1][c].getOccupied() == true || 
                             arrayPlot[r][c-1].getOccupied() == true || arrayPlot[r][c+1].getOccupied() == true || 
                             arrayPlot[r-1][c-1].getOccupied() == true || arrayPlot[r-1][c+1].getOccupied() == true || 
                             arrayPlot[r+1][c-1].getOccupied() == true || arrayPlot[r+1][c+1].getOccupied() == true) 
                        JOptionPane.showMessageDialog(frame, "You can't plant a Fruit tree beside an occupied plot!");

                    //Plant tree
                    else {
                        plotTile.setCropHeld(cropCpy);
                        //update wallet based on cost
                        int cropCost = crop.getSeedCost();
                        objectCoins = objectCoins - cropCost;
                        

                        JOptionPane.showMessageDialog(frame, "Successfully planted a " + crop.getName() + " tree!" + "\nYou have used " + cropCost + " coins!" + "\nYour remaining balance is: " + objectCoins +".");
                        plotTile.setOccupied(true);
                    }
                }
                
                //Normal crops
                else {
                    //you can now plant
                    plotTile.setCropHeld(cropCpy);

                    //update wallet based on cost
                    int cropCost = crop.getSeedCost();
                    objectCoins = objectCoins - cropCost;
                    

                    JOptionPane.showMessageDialog(frame, "Successfully planted a " + crop.getName() + "!" + "\nYou have used " + cropCost + " coins!" + "\nYour remaining balance is: " + objectCoins +".");
                    plotTile.setOccupied(true);
                }   
            }
            //Tile is rocked, unplowed, or occupied by another crop.
            else {
                if (plotTile.getRocked() == true) //rocked
                    JOptionPane.showMessageDialog(frame, "Tile is rocked!");

                
                
                else if (plotTile.getPlowState() == false) //unplowed
                    JOptionPane.showMessageDialog(frame, "Tile is not plowed!");

                else //occupied by another crop
                    JOptionPane.showMessageDialog(frame, "Tile is occupied by another crop!");
                }
        }
        
        else {
            JOptionPane.showMessageDialog(frame, "You do not have enough objectCoins to purchase that crop!");
        }
    }

    
    /** 
     * First checks for the plot's condition, as well as the crop's condition
     * before harvesting, if able, the function generates a random amount
     * of crop yield and sells it before resetting the values of the crop to default. 
     * @param plotTile : The type Plot tile that contains the crop which will be harvested
     */
    public void Harvest(JFrame frame, Plot plotTile) {
        Random random = new Random();
        if (plotTile.getCropHeld() != null) {
            //The crop has withered, or crop isn't harvestable yet
            if (plotTile.getCropHeld().getWithered() == true || plotTile.getCropHeld().getHarvestability() == false) {
                JOptionPane.showMessageDialog(frame, "You cannot harvest that crop!");
            }

            //The crop is harvestable
            else if (plotTile.getCropHeld().getHarvestability() == true) {
                int nHarvest = random.nextInt(plotTile.getCropHeld().getProduceMax() - plotTile.getCropHeld().getProduceMin() + 1) + plotTile.getCropHeld().getProduceMin();

                
                //remove the crop, set back to unplowoed
                plotTile.setOccupied(false);
                plotTile.setPlowState(false);
                
                plotTile.getCropHeld().setProducedYield(nHarvest); //amount acquired

                //use the TotalHarvestPrice function
                plotTile.getCropHeld().totalHarvestPrice(plotTile.getCropHeld(), plotTile.getCropHeld().getTotalTimesWatered(), plotTile.getCropHeld().getTotalTimesFertilized(), getBonusEarnings()); //farmerBonus is set to 0 for MCO1
                
                //Give the exp and objectcoins to the player
                setExp(getExp() + plotTile.getCropHeld().getExpYield()); 
                setLevel((int)getExp()/100);
                setObjectCoins(getObjectCoins() + (nHarvest * plotTile.getCropHeld().getBaseSellingPricePerPiece()));

                 if (plotTile.getCropHeld().getProducedYield() == 1)
                    JOptionPane.showMessageDialog(frame, "The harvest has yielded a total of " + plotTile.getCropHeld().getProducedYield() + " " + plotTile.getCropHeld().getName() + "!"
                                                  + "\nYou have received " + (nHarvest * plotTile.getCropHeld().getBaseSellingPricePerPiece()) + " Objectcoins and " + plotTile.getCropHeld().getExpYield() + " EXP!");
                else
                    JOptionPane.showMessageDialog(frame, "The harvest has yielded a total of " + plotTile.getCropHeld().getProducedYield() + " " + plotTile.getCropHeld().getName() + "s!"
                    + "\nYou have received " + (nHarvest * plotTile.getCropHeld().getBaseSellingPricePerPiece()) + " Objectcoins and " + plotTile.getCropHeld().getExpYield() + " EXP!");
                
                //reset days till harvest, harvestability, water needs, and fertilizer needs
                plotTile.getCropHeld().setDaysTillHarvest(plotTile.getCropHeld().getHarvestTime());
                plotTile.getCropHeld().setHarvestability(false);
                plotTile.getCropHeld().setTotalTimesWatered(0);
                plotTile.getCropHeld().setTotalTimesFertilized(0);
                plotTile.getCropHeld().setWithered(false);
                plotTile.setCropHeld(null); //remove the crop
            }
        }
        else {
            JOptionPane.showMessageDialog(frame, "There is no crop in that tile!");
        }
    }
    
    /** 
     * Allows the player to use a tool that they wish, and does the necessary 
     * checking whether if they are allowed to use that tool or not.
     * @param toolType : the Tool that the player wishes to use.
     * @param plotTile : the Tile that the player wishes to use the tool on.
     */
    public void useTool(Tool toolType, Plot plotTile, JFrame frame) {
        //Plow
        if (toolType.getName().equals("Plow")) {
            //then plow
            if (plotTile.getRocked() == true) {
                JOptionPane.showMessageDialog(frame, "The tile is rocked! You may not plow it yet!");
            }
            else if (plotTile.getPlowState() == true) {
                JOptionPane.showMessageDialog(frame, "The tile is already plowed!");
            }
            else {
                JOptionPane.showMessageDialog(frame, "You have successfully plowed the tile! \nYou have gained " + toolType.getExpGain() + " EXP!");
                plotTile.setPlowState(true);
                //cost and exp gain after use
                setObjectCoins(getObjectCoins() - toolType.getCost());
                setExp(getExp() + toolType.getExpGain());
                setLevel((int)getExp()/100);
            }
        }
        //Watering Can
        else if (toolType.getName().equals("Watering Can")) {
            if (plotTile.getPlowState() == true) {
                if (plotTile.getOccupied() == true) {
                    if (plotTile.getCropHeld().getWithered() == false){
                        plotTile.getCropHeld().setTotalTimesWatered(plotTile.getCropHeld().getTotalTimesWatered() + 1);
                        setExp(getExp() + toolType.getExpGain());
                        setLevel((int)getExp()/100);
                        
                        JOptionPane.showMessageDialog(frame, "You have watered the plot! \nYou have gained " + toolType.getExpGain() + " EXP!");
                    }
                    else
                        JOptionPane.showMessageDialog(frame, "You can't water the plot, the crop has withered :(");

                }
                else {
                    JOptionPane.showMessageDialog(frame, "The tile isn't occupied by a crop!");
                }
            }
            else {
                JOptionPane.showMessageDialog(frame, "The tile isn't plowed!");
            }
        }
        //Fertilizer
        else if (toolType.getName().equals("Fertilizer")) {
            //checks if the player has enough balance
            if (getObjectCoins() >= toolType.getCost()) {
                if (plotTile.getPlowState() == true) {
                    if (plotTile.getOccupied() == true) {
                        if (plotTile.getCropHeld().getWithered() == false){
                            plotTile.getCropHeld().setTotalTimesFertilized(plotTile.getCropHeld().getTotalTimesFertilized() + 1);
                            setObjectCoins(getObjectCoins() - toolType.getCost());
                            setExp(getExp() + toolType.getExpGain());
                            setLevel((int)getExp()/100);

                            JOptionPane.showMessageDialog(frame, "You have fertilized the crop for " + toolType.getCost() + " ObjectCoins! \nYou have gained " + toolType.getExpGain() + " EXP!");
                        }
                        else 
                            JOptionPane.showMessageDialog(frame, "You can't fertilize the plot, the crop has withered :(");

                    }
                    else {
                        JOptionPane.showMessageDialog(frame, "The tile isn't occupied by a crop!");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(frame, "The tile isn't plowed!");
                }
            }
            else {
                JOptionPane.showMessageDialog(frame, "Insufficient balance!");
            }
        }
        //Pickaxe
        else if (toolType.getName().equals("Pickaxe")) {
            if (getObjectCoins() >= toolType.getCost()) {
                //then destroy rock
                if (plotTile.getRocked() == true) {
                    plotTile.setRocked(false);
                    plotTile.setOccupied(false);
                    setObjectCoins(getObjectCoins() - toolType.getCost());
                    setExp(getExp() + toolType.getExpGain());
                    setLevel((int)getExp()/100);
                    JOptionPane.showMessageDialog(frame, "You have successfully removed the rocks for " + toolType.getCost() + " ObjectCoins! \nYou have gained " + toolType.getExpGain() + " EXP!");

                }
                else {
                    JOptionPane.showMessageDialog(frame, "Tile isn't rocked!");

                }
            }
            else {
                JOptionPane.showMessageDialog(frame, "Insufficient balance!");
            }
        }
        //Shovel
        else if (toolType.getName().equals("Shovel")) {
            if (getObjectCoins() >= toolType.getCost()) {
                if (plotTile.getPlowState() == true) {
                    if (plotTile.getOccupied() == true) {
                        //remove the crop inside and set to unplowed
                        plotTile.setPlowState(false);
                        plotTile.setOccupied(false);
                        setObjectCoins(getObjectCoins() - toolType.getCost());
                        setExp(getExp() + toolType.getExpGain());
                        setLevel((int)getExp()/100);
                        
                        JOptionPane.showMessageDialog(frame, "Crop has been removed and tile has been unplowed for " + toolType.getCost() + " ObjectCoins! \nYou have gained " + toolType.getExpGain() + " EXP!");

                        //reset days till harvest, harvestability, water needs, harvestability, and fertilizer needs
                        plotTile.getCropHeld().setDaysTillHarvest(plotTile.getCropHeld().getHarvestTime());
                        plotTile.getCropHeld().setHarvestability(false);
                        plotTile.getCropHeld().setTotalTimesWatered(0);
                        plotTile.getCropHeld().setTotalTimesFertilized(0);
                        plotTile.getCropHeld().setWithered(false);
                        plotTile.setCropHeld(null); //remove the crop
                    }
                    else {
                        plotTile.setPlowState(false);
                        setObjectCoins(getObjectCoins() - toolType.getCost());
                        setExp(getExp() + toolType.getExpGain());
                        setLevel((int)getExp()/100);

                        JOptionPane.showMessageDialog(frame, "Plowed tile has been unplowed for " + toolType.getCost() + " ObjectCoins! \nYou have gained " + toolType.getExpGain() + " EXP!");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(frame, "Tile isn't plowed!");
                }
            }
            else {
                JOptionPane.showMessageDialog(frame, "Insufficient balance!");
            }
        }
    }
    
    /** 
     * Advances the entire play area by 1 day, updating multiple attributes of the
     * Plots, a central game mechanic that allows for the harvest and selling cycle
     * @param DayCount : The amount of days that have passed in total
     * @param plotRows : The amount of rows the Plot contains
     * @param plotColumns : The amount of columns the Plot contains
     * @param arrayPlot[][] : The plot itself
     * @return int : returns the number of days passed in total
     */
    public int updateFarm (int DayCount, int plotRows, int plotColumns,  Plot arrayPlot[][]) {
        DayCount += 1;
        //Does a For loop to go through all the plots
        for (int i = 0; i < plotRows; i++) {
            for (int j = 0; j < plotColumns; j++) { 
                if (arrayPlot[i][j].getOccupied()) {
                    if (arrayPlot[i][j].getRocked() == true) {
                    }
                    else {
                        //update harvest time, lower by 1 day
                        int tempVar1 = arrayPlot[i][j].getCropHeld().getDaysTillHarvest() - 1;
                        arrayPlot[i][j].getCropHeld().setDaysTillHarvest(tempVar1);
                       
                        //set the caps for water
                        if (arrayPlot[i][j].getCropHeld().getTotalTimesWatered() > arrayPlot[i][j].getCropHeld().getWaterBonus()) {
                            arrayPlot[i][j].getCropHeld().setTotalTimesWatered(arrayPlot[i][j].getCropHeld().getWaterBonus());
                        }
                        
                        //set the caps for fertilizier
                        if (arrayPlot[i][j].getCropHeld().getTotalTimesFertilized() > arrayPlot[i][j].getCropHeld().getFertilizerBonus()) {
                            arrayPlot[i][j].getCropHeld().setTotalTimesFertilized(arrayPlot[i][j].getCropHeld().getFertilizerBonus());
                        }
                        
                        //check if withered or harvestable
                        if (arrayPlot[i][j].getCropHeld().getDaysTillHarvest() < 0 || 
                            ((arrayPlot[i][j].getCropHeld().getTotalTimesWatered() < arrayPlot[i][j].getCropHeld().getWaterNeeds()) && 
                             arrayPlot[i][j].getCropHeld().getDaysTillHarvest() == 0)) {
                            //Set the status
                            arrayPlot[i][j].getCropHeld().setWithered(true);
                            arrayPlot[i][j].getCropHeld().setHarvestability(false);
                        }
                        
                        //crop is on its harvest day
                        else {
                            if (arrayPlot[i][j].getCropHeld().getDaysTillHarvest() == 0 &&
                                arrayPlot[i][j].getCropHeld().getTotalTimesWatered() >= arrayPlot[i][j].getCropHeld().getWaterNeeds() ) {
                                //becomes harvestable
                                arrayPlot[i][j].getCropHeld().setHarvestability(true);
                            }
                        }
                        
                    }
                }
            }
        }
        return DayCount;
    }
    
    /** 
     * The function that checks for certain conditions that satisfy a game end.
     * @param arrayPlot[][] : The Plot itself
     * @param plotRows : The number of rows in the plot
     * @param plotColumns : The number of columns in the plot
     * @param shopList : The shop that contains all the seeds that the player spends
     *                   Objectcoins on.
     * @return boolean : returns a true if any of the conditions are met,
     *                   returns a false if any of the conditions are not met.
     */
    public boolean gameEnd(Plot arrayPlot[][], int plotRows, int plotColumns, ArrayList <Crop> shopList) {
        int decider = 0;

        for (int i = 0; i < plotRows; i++) {
            for (int j = 0; j < plotColumns; j++) {
            //Check if any are harvestsable or non-withered crops and check if ObjectCOins is less than the cheapest seed
                if (arrayPlot[i][j].getCropHeld() != null) {
                    if (arrayPlot[i][j].getCropHeld().getHarvestability() == true || arrayPlot[i][j].getCropHeld().getWithered() == false) {
                        decider += 1; //add one if found one
                    }
                } 
            }
        }

        //If there are no non-withered crops, and player can't afford anymore seeds, end the game
        if (decider == 0 && (getObjectCoins() < shopList.get(0).getSeedCost())) 
            return true;

        return false;
    }

    /**
     * @return array of farmerType names
     */
    public String[] getFarmerTypeName() {
        return farmerTypeName;
    }

    /**
     * @param farmerTypeName
     */
    public void setFarmerTypeName(String[] farmerTypeName) {
        this.farmerTypeName = farmerTypeName;
    }
}