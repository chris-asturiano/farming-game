/**
 * A class which contains all the crops and its stats in the game
 */
public class Crop {
    private String name;
    private String type;
    private boolean harvestability = false; //all plants start at false
    private boolean withered = false; //all plants aren't withered upon planting
    private int seedCost; //will be set
    private int daysTillHarvest; 
    private int totalTimesWatered = 0; //starts at 0
    private int totalTimesFertilized = 0; //starts at 0
    private int waterNeeds;
    private int fertilizerNeeds;
    private int producedYield; //will be set to a random int value
    private double expYield;
    private int baseSellingPricePerPiece;
    private int waterBonus;
    private int fertilizerBonus;
    private int produceMin;
    private int produceMax;
    private int harvestTime;

    /**
     * Constructor for crops
     * @param name
     * @param type
     * @param harvestability
     * @param withered
     * @param seedCost
     * @param daysTillHarvest
     * @param totalTimesWatered
     * @param totalTimesFertilized
     * @param waterNeeds
     * @param fertilizerNeeds
     * @param producedYield
     * @param expYield
     * @param baseSellingPricePerPiece
     * @param waterBonus
     * @param fertilizerBonus
     * @param produceMin
     * @param produceMax
     * @param harvestTime
     */
	public Crop(String name, String type, boolean harvestability, boolean withered, int seedCost, int daysTillHarvest,
            int totalTimesWatered, int totalTimesFertilized, int waterNeeds, int fertilizerNeeds, int producedYield, double expYield,
            int baseSellingPricePerPiece, int waterBonus, int fertilizerBonus, int produceMin, int produceMax, int harvestTime) {
        this.name = name;
        this.type = type;
        this.harvestability = harvestability;
        this.withered = withered;
        this.seedCost = seedCost;
        this.daysTillHarvest = daysTillHarvest;
        this.totalTimesWatered = totalTimesWatered;
        this.totalTimesFertilized = totalTimesFertilized;
        this.waterNeeds = waterNeeds;
        this.fertilizerNeeds = fertilizerNeeds;
        this.producedYield = producedYield;
        this.expYield = expYield;
        this.baseSellingPricePerPiece = baseSellingPricePerPiece;
        this.waterBonus = waterBonus;
        this.fertilizerBonus = fertilizerBonus;
        this.produceMin = produceMin;
        this.produceMax = produceMax;
        this.harvestTime = harvestTime;
    }

    /**
     * Copy constructor
     * @param anotherCrop
     */
    public Crop (Crop anotherCrop) {
        this.name = anotherCrop.name;
        this.type = anotherCrop.type;
        this.harvestability = anotherCrop.harvestability;
        this.withered = anotherCrop.withered;
        this.seedCost = anotherCrop.seedCost;
        this.daysTillHarvest = anotherCrop.daysTillHarvest;
        this.totalTimesWatered = anotherCrop.totalTimesWatered;
        this.totalTimesFertilized = anotherCrop.totalTimesFertilized;
        this.waterNeeds = anotherCrop.waterNeeds;
        this.fertilizerNeeds = anotherCrop.fertilizerNeeds;
        this.producedYield = anotherCrop.producedYield;
        this.expYield = anotherCrop.expYield;
        this.baseSellingPricePerPiece = anotherCrop.baseSellingPricePerPiece;
        this.waterBonus = anotherCrop.waterBonus;
        this.fertilizerBonus = anotherCrop.fertilizerBonus;
        this.produceMin = anotherCrop.produceMin;
        this.produceMax = anotherCrop.produceMax;
        this.harvestTime = anotherCrop.harvestTime;
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
     * @return String
     */
    public String getType() {
        return type;
    }
    
    /** 
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }
    
    /** 
     * @return boolean
     */
    public boolean getHarvestability() {
        return harvestability;
    }
    
    /** 
     * @param harvestability
     */
    public void setHarvestability(boolean harvestability) {
        this.harvestability = harvestability;
    }
    
    /** 
     * @return boolean
     */
    public boolean getWithered() {
        return withered;
    }
    
    /** 
     * @param withered
     */
    public void setWithered(boolean withered) {
        this.withered = withered;
    }
    
    /** 
     * @return int
     */
    public int getSeedCost() {
        return seedCost;
    }
    
    /** 
     * @param seedCost
     */
    public void setSeedCost(int seedCost) {
        this.seedCost = seedCost;
    }
    
    /** 
     * @return int
     */
    public int getDaysTillHarvest() {
        return daysTillHarvest;
    }
    
    /** 
     * @param daysTillHarvest
     */
    public void setDaysTillHarvest(int daysTillHarvest) {
        this.daysTillHarvest = daysTillHarvest;
    }
    
    /** 
     * @return int
     */
    public int getTotalTimesWatered() {
        return totalTimesWatered;
    }
    
    /** 
     * @param totalTimesWatered
     */
    public void setTotalTimesWatered(int totalTimesWatered) {
        this.totalTimesWatered = totalTimesWatered;
    }
    
    /** 
     * @return int
     */
    public int getTotalTimesFertilized() {
        return totalTimesFertilized;
    }
    
    /** 
     * @param totalTimesFertilized
     */
    public void setTotalTimesFertilized(int totalTimesFertilized) {
        this.totalTimesFertilized = totalTimesFertilized;
    }
    
    /** 
     * @return int
     */
    public int getProducedYield() {
        return producedYield;
    }
    
    /** 
     * @param producedYield
     */
    public void setProducedYield(int producedYield) {
        this.producedYield = producedYield;
    }
    
    /** 
     * @return double
     */
    public double getExpYield() {
        return expYield;
    }
    
    /** 
     * @param expYield
     */
    public void setExpYield(double expYield) {
        this.expYield = expYield;
    }
    
    /** 
     * @return int
     */
    public int getBaseSellingPricePerPiece() {
        return baseSellingPricePerPiece;
    }
    
    /** 
     * @param baseSellingPricePerPiece
     */
    public void setBaseSellingPricePerPiece(int baseSellingPricePerPiece) {
        this.baseSellingPricePerPiece = baseSellingPricePerPiece;
    }
    
    /** 
     * @return int
     */
    public int getWaterNeeds() {
        return waterNeeds;
    }
    
    /** 
     * @param waterNeeds
     */
    public void setWaterNeeds(int waterNeeds) {
        this.waterNeeds = waterNeeds;
    }
    
    /** 
     * @return int
     */
    public int getFertilizerNeeds() {
        return fertilizerNeeds;
    }
    
    /** 
     * @param fertilizerNeeds
     */
    public void setFertilizerNeeds(int fertilizerNeeds) {
        this.fertilizerNeeds = fertilizerNeeds;
    }
    
    /** 
     * @return int
     */
    public int getWaterBonus() {
		return waterBonus;
	}
	
    /** 
     * @param waterBonus
     */
    public void setWaterBonus(int waterBonus) {
		this.waterBonus = waterBonus;
	}
	
    /** 
     * @return int
     */
    public int getFertilizerBonus() {
		return fertilizerBonus;
	}
	
    /** 
     * @param fertilizerBonus
     */
    public void setFertilizerBonus(int fertilizerBonus) {
		this.fertilizerBonus = fertilizerBonus;
	}
    
    /** 
     * @return int
     */
    public int getProduceMin() {
		return produceMin;
	}
	
    /** 
     * @param produceMin
     */
    public void setProduceMin(int produceMin) {
		this.produceMin = produceMin;
	}
	
    /** 
     * @return int
     */
    public int getProduceMax() {
		return produceMax;
	}
	
    /** 
     * @param produceMax
     */
    public void setProduceMax(int produceMax) {
		this.produceMax = produceMax;
	}
    
    
    /** 
     * @return int
     */
    public int getHarvestTime() {
        return harvestTime;
    }

    
    /** 
     * After generating yield after harvesting, this function calculates the total
     * Objectcoin profit for the player.
     * @param Yield : The randomly generated amount of yield from harvest
     * @param totalTimesWatered : The total amount of times the crop was watered
     * @param totalTimesFertilized : The total amount of times the crop was fertilized
     * @param FarmerBonus : The extra multiplier to the Harvest Price based on the player's Farmer type 
     * @return double
     */
    public double totalHarvestPrice(Crop Yield, int totalTimesWatered, int totalTimesFertilized, int FarmerBonus) {
        double HarvestTotal = Yield.producedYield * (Yield.baseSellingPricePerPiece + FarmerBonus);
        double WaterBonus = HarvestTotal * 0.2 * (totalTimesWatered - 1);
        double FertilizerBonus = HarvestTotal * 0.5 * totalTimesFertilized;
        double FinalHarvestPrice = HarvestTotal + WaterBonus + FertilizerBonus;
        
        //If flower, multiply by 1.1
        if (Yield.getType().equals("Flower"))
            FinalHarvestPrice = FinalHarvestPrice * 1.1;

        return FinalHarvestPrice;
    }
}