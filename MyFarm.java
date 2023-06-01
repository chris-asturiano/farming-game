import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

/**
 * A class which represents the farm which the player plays on.
 */
public class MyFarm implements ActionListener{
    private PlayerSys player1;
    private Plot[][] arrayPlot;
    private ArrayList<Crop> shopList; 
    private ArrayList<Tool> toolList;
    private Plot selectedTile;
    private int DayCount;
    private int plotRows;
    private int plotColumns; 

    private int selectedRow;
    private int selectedColumn;


    //GUI Declarations
    //Intro
    private JFrame frame;
    private JLabel intro;
    private JButton introButton;
    private JPanel introPanel;
    private JPanel introPic;
    private JTextField nameIn;
    private Font myFont = new Font("Arial", Font.BOLD, 20);
    private Font sidePanelFont = new Font("Comic Sans MS", Font.PLAIN, 17);
    private Font sidePanelFontBld = new Font("Comic Sans MS", Font.BOLD, 17);

    //Actual Game
    private JButton[][] tileButtons = new JButton[10][5];
    private JPanel farmPanel = new JPanel();

    //SidePanel stuffs
    private JPanel sidePanel;
    private JLabel greetLbl;
    private JLabel dayLbl;
    private JLabel farmerTypeLbl;
    private JLabel levelLbl;
    private JLabel expLbl;
    private JLabel objectCoinsLbl;

    //SidePanel2
    private JPanel sidePanel2;
    private JLabel cropnameLbl;
    private JLabel wateredLbl;  
    private JLabel fertilizedLbl;
    private JLabel dthLbl;

    private JLabel selectedTileLbl;
    private JButton updateDayBtn;

    //Register Button
    private JButton registerBtn;

    //Tool Buttons
    private JButton plow;
    private JButton wtrCan;
    private JButton fertilizer;
    private JButton pickaxe;
    private JButton shovel;
    private JButton harvest;

    //Shop
    private JFrame shop;
    private JButton[] cropButtons = new JButton[8];
    private JButton openShop;

    //ImageIcons
    ImageIcon plowedTileIcon = new ImageIcon("Pictures\\tile_plowed.png");
    ImageIcon rockedIcon = new ImageIcon("Pictures\\tile_rocked.png");
    ImageIcon unplowedIcon = new ImageIcon("Pictures\\tile_unplowed.png");

    /**
     * Constructor for the class 
     */
    public MyFarm() {
        player1 = new PlayerSys(null, 0, 0, 100, 0);
        arrayPlot = new Plot[10][5];
        shopList = new ArrayList<Crop>();
        toolList = new ArrayList<Tool>();
        selectedTile = new Plot(false, false, false, null);
        DayCount = 1; //starts at Day 1
        plotRows = 10; //editable value 
        plotColumns = 5; //editable value

        //Initialize Farm and Rocks via file input
        try {
            BufferedReader reader = new BufferedReader(new FileReader("rockscatter.txt"));
            int rocked;
            //Initializes the plot
            for (int i = 0; i < plotRows; i++) {
                for (int j = 0; j < plotColumns; j++) {
                    rocked = Integer.parseInt(reader.readLine());
                    Plot tempPlot;
                    //Read file and determine the rocks
                    if (rocked == 1) {
                        tempPlot = new Plot(false, true, true, null);
                    }
                    else {
                        tempPlot = new Plot(false, false, false, null);
                    }

                    arrayPlot[i][j] = tempPlot; //initialize values
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        player1.initializeCrops(shopList);
        player1.initializeTools(toolList);


        //GUI Initializations
        //MAIN frame
        frame = new JFrame("DLSU Farming Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(820, 800);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(0,154,23));
        
        //Intro Stuff
        introPic = new JPanel();
        introPic.setSize(229,163);
        introPic.setLocation((frame.getWidth() / 2) - (introPic.getWidth()/2), (frame.getHeight() / 2) - (introPic.getHeight()/2)-135);
        introPic.setBackground(new Color(0,154,23));
       
        introPanel = new JPanel();  
        introPanel.setLayout(new GridLayout(4, 1, 0, 1));
        introPanel.setSize(450, 100);
        int x = (frame.getWidth() / 2) - (introPanel.getWidth()/2);
        int y = (frame.getHeight() / 2) - (introPanel.getHeight()/2);
        introPanel.setLocation(x, y);
        introPanel.setOpaque(false);

        JLabel pic = new JLabel();
        pic.setIcon(new ImageIcon("Farm.png")); //Farm Icon, source: https://www.cleanpng.com/png-organic-farming-portable-network-graphics-agricult-7268807/
        pic.setOpaque(false);
        pic.setBackground(new Color(0,154,23));

        intro = new JLabel("Welcome to DLSU Farming Simulator!"); 
        intro.setFont(myFont);
        intro.setHorizontalAlignment(JLabel.CENTER);

        JLabel nameQues = new JLabel("What would you like your name to be?");
        nameQues.setFont(myFont);
        nameQues.setHorizontalAlignment(JLabel.CENTER);

        introButton = new JButton("Accept!");
        introButton.addActionListener(this);

        nameIn = new JTextField();
        nameIn.setEditable(true);
 
        introPanel.add(intro);
        introPanel.add(nameQues);
        introPanel.add(nameIn);
        introPanel.add(introButton);
        introPic.add(pic);
        pic.setLocation(100,200);

        //TILES
        farmPanel.setBounds(5, 5, 500, 750);
        farmPanel.setLayout(new GridLayout(10,5,27,10));
        farmPanel.setBackground(new Color(0,154,23));

        //Initialization
        for (int i = 0; i < plotRows; i++) {
            for (int j = 0; j < plotColumns; j++) {
                tileButtons[i][j] = new JButton();
                tileButtons[i][j].addActionListener(this);
                tileButtons[i][j].setFont(myFont);
                tileButtons[i][j].setFocusable(false);
                tileButtons[i][j].setBackground(new Color(124, 252, 0));
                tileButtons[i][j].setIcon(unplowedIcon);
                if (arrayPlot[i][j].getRocked() == true)
                    tileButtons[i][j].setIcon(rockedIcon);
                farmPanel.add(tileButtons[i][j]);
            }
        }

        //Side panel
        sidePanel = new JPanel();
        int sidePanelWidth = 270;
        sidePanel.setBounds(525, 5, sidePanelWidth, 145);
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBackground(new Color(211, 211, 211));

        //Side Panel texts
        greetLbl = new JLabel();
        greetLbl.setFont(sidePanelFont);
        greetLbl.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        dayLbl = new JLabel();
        dayLbl.setFont(sidePanelFont);
        dayLbl.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        
        farmerTypeLbl = new JLabel();
        farmerTypeLbl.setFont(sidePanelFont);
        farmerTypeLbl.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        levelLbl = new JLabel();
        levelLbl.setFont(sidePanelFont);
        levelLbl.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        expLbl = new JLabel();
        expLbl.setFont(sidePanelFont);
        expLbl.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        objectCoinsLbl = new JLabel();
        objectCoinsLbl.setFont(sidePanelFont);
        objectCoinsLbl.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        //Update day button
        updateDayBtn = new JButton();
        updateDayBtn.addActionListener(this);
        updateDayBtn.setFont(sidePanelFont);
        updateDayBtn.setText("Update Day");
        updateDayBtn.setBounds(525, 155, sidePanelWidth, 25);
        updateDayBtn.setAlignmentX(JButton.CENTER_ALIGNMENT);

        updateDayBtn.setVisible(false);

        //Register Button
        registerBtn = new JButton();
        registerBtn.addActionListener(this);
        registerBtn.setFont(sidePanelFont);
        registerBtn.setText("Register");
        registerBtn.setBounds(525, 185, sidePanelWidth, 25);
        registerBtn.setAlignmentX(JButton.CENTER_ALIGNMENT);
        registerBtn.setVisible(false);
        

        //Tool Buttons
        selectedTileLbl = new JLabel();
        selectedTileLbl.setFont(sidePanelFont);
        selectedTileLbl.setBounds(525, 215, sidePanelWidth, 25);
        selectedTileLbl.setAlignmentX(JButton.CENTER_ALIGNMENT);
        selectedTileLbl.setVisible(false);

        Font toolFont = new Font("Comic Sans MS", Font.PLAIN, 21);

        plow = new JButton();
        plow.addActionListener(this);
        plow.setFont(toolFont);
        plow.setText("Plow (0)");
        plow.setBounds(525, 255, sidePanelWidth, 30);
        plow.setAlignmentX(JButton.CENTER_ALIGNMENT);
        plow.setBackground(new Color(159, 112, 65));
        plow.setBorder(new LineBorder(Color.BLACK));
        plow.setVisible(false);

        wtrCan = new JButton();
        wtrCan.addActionListener(this);
        wtrCan.setFont(toolFont);
        wtrCan.setText("Watering Can (0)");
        wtrCan.setBounds(525, 290, sidePanelWidth, 30);
        wtrCan.setAlignmentX(JButton.CENTER_ALIGNMENT);
        wtrCan.setBackground(new Color(159, 112, 65));
        wtrCan.setBorder(new LineBorder(Color.BLACK));
        wtrCan.setVisible(false);
        
        fertilizer = new JButton();
        fertilizer.addActionListener(this);
        fertilizer.setFont(toolFont);
        fertilizer.setText("Fertilizer (10)");
        fertilizer.setBounds(525, 325, sidePanelWidth, 30);
        fertilizer.setAlignmentX(JButton.CENTER_ALIGNMENT);
        fertilizer.setBackground(new Color(159, 112, 65));
        fertilizer.setBorder(new LineBorder(Color.BLACK));
        fertilizer.setVisible(false);

        pickaxe = new JButton();
        pickaxe.addActionListener(this);
        pickaxe.setFont(toolFont);
        pickaxe.setText("Pickaxe (50)");
        pickaxe.setBounds(525, 360, sidePanelWidth, 30);
        pickaxe.setAlignmentX(JButton.CENTER_ALIGNMENT);
        pickaxe.setBackground(new Color(159, 112, 65));
        pickaxe.setBorder(new LineBorder(Color.BLACK));
        pickaxe.setVisible(false);

        shovel = new JButton();
        shovel.addActionListener(this);
        shovel.setFont(toolFont);
        shovel.setText("Shovel (7)");
        shovel.setBounds(525, 395, sidePanelWidth, 30);
        shovel.setAlignmentX(JButton.CENTER_ALIGNMENT);
        shovel.setBackground(new Color(159, 112, 65));
        shovel.setBorder(new LineBorder(Color.BLACK));
        shovel.setVisible(false);

        sidePanel.add(greetLbl);
        sidePanel.add(dayLbl);
        sidePanel.add(farmerTypeLbl);
        sidePanel.add(levelLbl);
        sidePanel.add(expLbl);
        sidePanel.add(objectCoinsLbl);
    
        frame.add(introPanel);
        frame.add(introPic);
        frame.add(farmPanel);
        frame.add(sidePanel);
        frame.add(updateDayBtn);
        frame.add(registerBtn);

        frame.setVisible(true);
        farmPanel.setVisible(false);
        sidePanel.setVisible(false);
        introPanel.setVisible(true);

        //Crop shop
        openShop = new JButton();
        openShop.addActionListener(this);
        openShop.setFont(toolFont);
        openShop.setText("Plant a Crop");
        openShop.setBounds(525, 430, sidePanelWidth, 30);
        openShop.setAlignmentX(JButton.CENTER_ALIGNMENT);
        openShop.setBackground(new Color(159, 112, 65));
        openShop.setBorder(new LineBorder(Color.BLACK));
        openShop.setVisible(false);

        //Harvest
        harvest = new JButton();
        harvest.addActionListener(this);
        harvest.setFont(toolFont);
        harvest.setText("Harvest");
        harvest.setBounds(525, 465, sidePanelWidth, 30);
        harvest.setAlignmentX(JButton.CENTER_ALIGNMENT);
        harvest.setBackground(new Color(159, 112, 65));
        harvest.setBorder(new LineBorder(Color.BLACK));
        harvest.setVisible(false);

        //SidePanel2
        sidePanel2 = new JPanel();
        sidePanel2.setBounds(525, 510, sidePanelWidth, 100);
        sidePanel2.setLayout(new BoxLayout(sidePanel2, BoxLayout.Y_AXIS));
        sidePanel2.setBackground(new Color(159, 112, 65));

        cropnameLbl = new JLabel();
        cropnameLbl.setFont(sidePanelFontBld);
        cropnameLbl.setAlignmentX(JLabel.LEFT_ALIGNMENT);


        wateredLbl = new JLabel();
        wateredLbl.setFont(sidePanelFont);
        wateredLbl.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        
        fertilizedLbl = new JLabel();
        fertilizedLbl.setFont(sidePanelFont);
        fertilizedLbl.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        dthLbl = new JLabel();
        dthLbl.setFont(sidePanelFont);
        dthLbl.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        
        
        sidePanel2.add(cropnameLbl);
        sidePanel2.add(wateredLbl);
        sidePanel2.add(fertilizedLbl);
        sidePanel2.add(dthLbl);
        sidePanel2.setVisible(false);

        frame.add(selectedTileLbl);
        frame.add(plow);
        frame.add(wtrCan);
        frame.add(fertilizer);
        frame.add(pickaxe);
        frame.add(shovel);
        frame.add(openShop);
        frame.add(harvest);
        frame.add(sidePanel2);

        //Shop frame
        shop = new JFrame("Shop");
        shop.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        shop.setSize(300, 400);
        shop.setLayout(null);
        shop.setResizable(false);
    
        //Initialize the crop buttons for the shop
        for (int i = 0; i < shopList.size(); i++) {
            cropButtons[i] = new JButton();
            cropButtons[i].addActionListener(this);
            cropButtons[i].setFont(sidePanelFont);
            cropButtons[i].setText(shopList.get(i).getName());
            cropButtons[i].setSize(130, 43);
            cropButtons[i].setLocation((shop.getWidth()/2) - (cropButtons[i].getWidth()/2), i*45);
            cropButtons[i].setAlignmentX(JButton.CENTER_ALIGNMENT);
            cropButtons[i].setVisible(true);
            shop.add(cropButtons[i]);
        }
        //Set colors of the buttons
        cropButtons[0].setBackground(new Color(201, 104, 119));
        cropButtons[1].setBackground(new Color(237, 145, 33));
        cropButtons[2].setBackground(new Color(183, 146, 104));
        cropButtons[3].setBackground(new Color(194, 30, 86));
        cropButtons[4].setBackground(new Color(243, 243, 10));
        cropButtons[5].setBackground(new Color(246, 169, 15));
        cropButtons[6].setBackground(new Color(237, 145, 33));
        cropButtons[7].setBackground(new Color(199, 55, 47));


        shop.setVisible(false);      
    }

    /**
     * Reinitializes the class to be used when restarting the game
     */
    private void init() {
        player1 = new PlayerSys(null, 0, 0, 100, 0);
        arrayPlot = new Plot[10][5];
        shopList = new ArrayList<Crop>();
        toolList = new ArrayList<Tool>();
        selectedTile = new Plot(false, false, false, null);
        DayCount = 1; //starts at Day 1
        plotRows = 10; //editable value 
        plotColumns = 5; //editable value

        try {
            BufferedReader reader = new BufferedReader(new FileReader("rockscatter.txt"));
            int rocked;
            //Initializes the plot
            for (int i = 0; i < plotRows; i++) {
                for (int j = 0; j < plotColumns; j++) {
                    rocked = Integer.parseInt(reader.readLine());
                    Plot tempPlot;
                    if (rocked == 1) {
                        tempPlot = new Plot(false, true, true, null);
                    }
                    else {
                        tempPlot = new Plot(false, false, false, null);
                    }

                    arrayPlot[i][j] = tempPlot; //initialize values
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        player1.initializeCrops(shopList);
        player1.initializeTools(toolList);
    }


    /**
     * Checks if the game has ended
     */
    public void checkEnd() {
        if (player1.gameEnd(arrayPlot, plotRows, plotColumns, shopList) == true) {
            if (endGameVal() == JOptionPane.YES_OPTION)
                restartGame();
            else 
                frame.dispose();
        }
    }
    /** 
     * Displays a few lines of text when the game ends, also asks the player to
     * continue or not
     * @return int
     */
    public int endGameVal() {
        int finaldecision;
        finaldecision = JOptionPane.showConfirmDialog(frame, "Game Over!\nThank you for playing DLSU Farming Simulator!\nWould you like to play again?", 
                                                      null, JOptionPane.YES_NO_OPTION);
        
        return finaldecision;
    }


    /**
     * Updates the side panels
     */
    private void updateSidePanel() {
        //SidePanel1
        greetLbl.setText(" Hello " + player1.getName() + "!");
        dayLbl.setText(" Day: " + DayCount);
        farmerTypeLbl .setText(" Farmer Type: " + player1.getFarmerTypeName()[player1.getFarmerType()]);
        levelLbl.setText(" Level: " + String.valueOf(player1.getLevel()));
        expLbl.setText(" Experience: " + String.valueOf(player1.getExp()));
        objectCoinsLbl.setText(" ObjectCoins: " + String.valueOf(player1.getObjectCoins()));

        //SidePanel2
        if (selectedTile.getCropHeld() != null) {
            cropnameLbl.setText(" " + selectedTile.getCropHeld().getName());
            wateredLbl.setText(" Watered: " + selectedTile.getCropHeld().getTotalTimesWatered() + "/" + selectedTile.getCropHeld().getWaterNeeds() + " (Bonus Limit: " + selectedTile.getCropHeld().getWaterBonus() + ")");
            fertilizedLbl.setText(" Fertilized: " + selectedTile.getCropHeld().getTotalTimesFertilized() + "/" + selectedTile.getCropHeld().getFertilizerNeeds() + " (Bonus Limit: " + selectedTile.getCropHeld().getFertilizerBonus() + ")");
            dthLbl.setText(" Days Till Harvest: " + selectedTile.getCropHeld().getDaysTillHarvest());
        }
    }

    
    /** 
     * Shows the tools
     * @param b : the boolean value that determines if the tools should be displayed or not
     * @param plot : the plot to be evaluated
     */
    private void showTools(Boolean b, Plot plot) {
        if (b == true) {
            selectedTileLbl.setText("Selected Tile: [" + (selectedRow + 1) + "] [" + (selectedColumn + 1) + "]");
            selectedTileLbl.setVisible(true);
            plow.setVisible(true);
            wtrCan.setVisible(true);
            fertilizer.setVisible(true);
            pickaxe.setVisible(true);
            shovel.setVisible(true);
            openShop.setVisible(true);
            harvest.setVisible(true);
            
            //Only display sidePanel2 if there is a crop
            if (plot.getCropHeld() != null) {
                sidePanel2.setVisible(true);
            }
            else 
                sidePanel2.setVisible(false);

        }
        else {
            selectedTileLbl.setVisible(false);
            plow.setVisible(false);
            wtrCan.setVisible(false);
            fertilizer.setVisible(false);
            pickaxe.setVisible(false);
            shovel.setVisible(false);
            openShop.setVisible(false);
            harvest.setVisible(false);
        }
    }

    /**
     * Restarts the game
     */
    public void restartGame() {
        //Reinitalize
        init();
        
        //Set the visibilities
        sidePanel.setVisible(false);
        sidePanel2.setVisible(false);
        farmPanel.setVisible(false); 
        updateDayBtn.setVisible(false);
        registerBtn.setVisible(false);
        showTools(false, arrayPlot[0][0]);
        introPanel.setVisible(true);
        introPic.setVisible(true);
        
        //Update side panel
        updateSidePanel();
        
        //Reupdate Crops
        for (int i = 0; i < plotRows; i++) {
            for (int j = 0; j < plotColumns; j++) {
                updateIcons(arrayPlot[i][j], i, j);
            }
        } 
    }

    
    /** 
     * Returns a string that contains the information of each crop in the shop
     * @param crop : the crop to show the information of
     * @return String : the string that will be generated
     */
    public String showShopInfo(Crop crop) {
        String stats;
        //Only flowers produce 1 crop
        if (crop.getType().equals("Flower")){
            stats = "Crop Information:\nName: " + crop.getName() + "\nType: " + crop.getType() + "\nHarvest Time: " 
                            + crop.getHarvestTime() + " Days\nWater Needs: " + crop.getWaterNeeds() 
                            + "\nFertilizer Needs: " + crop.getFertilizerNeeds() + "\nProducts Produced: " 
                            + crop.getProduceMin() +  "\nBase Selling Price per Piece: " + crop.getBaseSellingPricePerPiece() 
                            + "\nExperience Yield: " + crop.getExpYield() + "\nCOST: " + crop.getSeedCost()
                            + "\nWould you like to plant this crop?";
        }
        else  {
            stats = "Crop Information:\nName: " + crop.getName() + "\nType: " + crop.getType() + "\nHarvest Time: " 
                            + crop.getHarvestTime() + " Days\nWater Needs: " + crop.getWaterNeeds() 
                            + "\nFertilizer Needs: " + crop.getFertilizerNeeds() + "\nProducts Produced: " 
                            + crop.getProduceMin() + "-" + crop.getProduceMax() + "\nBase Selling Price per Piece: " + crop.getBaseSellingPricePerPiece() 
                            + "\nExperience Yield: " + crop.getExpYield() + "\nCOST: " + crop.getSeedCost()
                            + "\nWould you like to plant this crop?";
        }
            return stats;
    }

    
    /** 
     * Updates the images of the tiles in the farm
     * @param plot : the plot to be updated
     * @param selectedRow : the plot's row
     * @param selectedColumn : the plot's column
     */
    public void updateIcons(Plot plot, int selectedRow, int selectedColumn) {
        //Set the icons based on the tile status
        if (plot.getOccupied() == true) {
            if (plot.getRocked() == true) {
                tileButtons[selectedRow][selectedColumn].setIcon(new ImageIcon("Pictures\\tile_rocked.png"));
            }
            else if (plot.getCropHeld() != null) {
                if (plot.getCropHeld().getHarvestability() == false) {
                    if (plot.getCropHeld().getWithered() == false)
                        tileButtons[selectedRow][selectedColumn].setIcon(new ImageIcon("Pictures\\" + plot.getCropHeld().getName() + "Growing.png"));
                    
                        else
                            tileButtons[selectedRow][selectedColumn].setIcon(new ImageIcon("Pictures\\" + plot.getCropHeld().getName() + "Withered.png"));
                }
                else 
                    tileButtons[selectedRow][selectedColumn].setIcon(new ImageIcon("Pictures\\" + plot.getCropHeld().getName() + "Harvestable.png"));
            }
        }
        else {
            if (plot.getPlowState() == true)
                tileButtons[selectedRow][selectedColumn].setIcon(plowedTileIcon);

            else
                tileButtons[selectedRow][selectedColumn].setIcon(unplowedIcon);
        }
    }
    

    
    /** 
     * Sets the actions for the buttons
     * @param e : the ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Tile buttons
        for (int i = 0; i < plotRows; i++) {
            for (int j = 0; j < plotColumns; j++) {
                if (e.getSource() == tileButtons[i][j]) {
                    //display the side buttons panel
                    selectedRow = i;
                    selectedColumn = j;
                    selectedTile = arrayPlot[i][j];
                    updateSidePanel();
                    showTools(true, arrayPlot[i][j]);
                }
            }
        }

        //Hide the intro panel, then open the farm when player clicks on intro button
        if (e.getSource() == introButton) {
            player1.setName(nameIn.getText());
            introPanel.setVisible(false);
            introPic.setVisible(false);
            updateSidePanel();
            sidePanel.setVisible(true);
            farmPanel.setVisible(true); 
            updateDayBtn.setVisible(true);
            registerBtn.setVisible(true);
        }
        //Update farm
        if (e.getSource() == updateDayBtn) {
            DayCount = player1.updateFarm(DayCount, plotRows, plotColumns, arrayPlot);
            updateSidePanel();
            for (int i = 0; i < plotRows; i++) {
                for (int j = 0; j < plotColumns; j++) {
                    updateIcons(arrayPlot[i][j], i, j);
                }
            }
            JOptionPane.showMessageDialog(frame, "You have updated the farm!\nIt is now Day " + DayCount);

            //Checks for game end since Update Day can wither crops
            checkEnd();
        }

        //Register
        if (e.getSource() == registerBtn) {
            player1.Register(frame, arrayPlot, shopList);
            updateSidePanel();
        }

        //Plow
        if (e.getSource() == plow) {
            player1.useTool(toolList.get(0), selectedTile, frame);
            updateSidePanel();
            updateIcons(selectedTile, selectedRow, selectedColumn);
        }

        //Watering Can
        if (e.getSource() == wtrCan) {
            player1.useTool(toolList.get(1), selectedTile, frame);
            updateSidePanel();
        }

        //Fertilizer
        if (e.getSource() == fertilizer) {
            player1.useTool(toolList.get(2), selectedTile, frame);
            updateSidePanel();
        }

        //Pickaxe
        if (e.getSource() == pickaxe) {
            player1.useTool(toolList.get(3), selectedTile, frame);
            updateSidePanel();
            updateIcons(selectedTile, selectedRow, selectedColumn);
            checkEnd();
        }

        //Shovel
        if (e.getSource() == shovel) {
            player1.useTool(toolList.get(4), selectedTile, frame);
            updateSidePanel();
            updateIcons(selectedTile, selectedRow, selectedColumn);
            showTools(true, selectedTile);
            checkEnd();
        }

        //Open Shop
        if (e.getSource() == openShop) {
            if (arrayPlot[selectedRow][selectedColumn].getRocked() == true)
                JOptionPane.showMessageDialog(frame, "Tile has a rock!");

            else if (arrayPlot[selectedRow][selectedColumn].getPlowState() == false)
                JOptionPane.showMessageDialog(frame, "Tile isn't plowed!");

            else if (arrayPlot[selectedRow][selectedColumn].getOccupied() == true)
                JOptionPane.showMessageDialog(frame, "Tile is occupied!");

            else 
                shop.setVisible(true);
        }

        //Harvest
        if (e.getSource() == harvest) {
            selectedTile = arrayPlot[selectedRow][selectedColumn];
            player1.Harvest(frame, selectedTile);
            updateSidePanel();
            updateIcons(selectedTile, selectedRow, selectedColumn);
            showTools(true, selectedTile);
        }

        //Shop Buttons
        for (int i = 0; i < shopList.size(); i++) {
            if (e.getSource() == cropButtons[i]) {
                selectedTile = arrayPlot[selectedRow][selectedColumn];
                int ans = JOptionPane.showConfirmDialog(frame, showShopInfo(shopList.get(i)));
                if (ans == 0){
                    player1.Plant(shop, selectedTile, shopList.get(i), arrayPlot, selectedRow, selectedColumn);
                    updateSidePanel();
                    showTools(true, selectedTile);
                    shop.setVisible(false);
                    shop.dispose();
                }
                updateIcons(selectedTile, selectedRow, selectedColumn);
            }
        }
    }
}