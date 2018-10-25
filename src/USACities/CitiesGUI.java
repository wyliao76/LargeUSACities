package USACities;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * CitiesGUI.java
 A class representing the GUI used in a maintaining a City database.
 * <pre>
    Project: CitiesGUI Database
    Platform: jdk 1.8.0_65; NetBeans IDE 8.1; Windows 10

 </pre>
 *
 * @author:	Wei-Yu.Liao@go.shoreline.edu
 * @version: 	%1% %2%
 * @see:     	javax.swing
 * @see:        java.awt.Toolkit
 * @see         java.util.ArrayList
 */

public class CitiesGUI extends JFrame
{  
    // class instance ArrayList of City objects
    private ArrayList <City> cities = new ArrayList ();
    // external file name of City
    private final String fileName = "src/USACities/Citystats.txt";
    //private final String fileNameXML = "src/USACities/Cities.xml";
    DecimalFormat number = new DecimalFormat("#,##0");        
    
    //Javadocs??    
    /** Creates new form CitiesGUI */
    /** Default constructor
 Creates new form Cities form centered, with Add button as default
 The City are read from an external text file, Citystats.txt, into
 an ArrayList of City type
     */
    public CitiesGUI()
    {
        initComponents();
        this.getRootPane().setDefaultButton(addJButton); //set buttonAdd as default
        this.setIconImage(Toolkit.getDefaultToolkit().
                getImage("src/USACities/buckinghamfountain_small.jpg"));
        //centers the form at start.
        setLocationRelativeTo(null);
        
        // Read form an external file Citystats.txt and create an
        // ArrayList of City type, City        
        readFromFile(fileName);
        
        // Show the city names in the JList
        //displayCities();
        //showCityData(citiesJList.getSelectedIndex());
    }
    
    
    /**
     * Method: readFromFile
 Reads City from a text file that is comma delimited and
 creates an instance of the City class with the data read.
     * Then the newly created city is added to the City database.
 Uses an object from the ReadFile class to read record.
     * @parem file: String
     * @return void
 pre-condition: a valid file name, Citystats.txt is expected
 for input with comma separated text fields (but no spaces) for
 city name, population, median, local, and degree
 post-condition: a new City is created with the read fields
 and added to the ArrayList City
     * @see ReadFile
     * @see Member
     */
    public void readFromFile(String file)            
    {
      cities.clear();
      try{
         FileReader inputFile = new FileReader(fileName);
         BufferedReader input = new BufferedReader(inputFile);
         String Line = input.readLine();
         while (Line != null){
             City metropolis = new City();
             StringTokenizer token = new StringTokenizer(Line, ",");
             while (token.hasMoreElements())
             {
                 metropolis.setName(token.nextToken());
                 metropolis.setPopulation(Float.parseFloat(token.nextToken()));
                 metropolis.setMedianIncome(Float.parseFloat(token.nextToken()));
                 metropolis.setPercentLocal(Float.parseFloat(token.nextToken()));
                 metropolis.setAdvancedDegrees(Float.parseFloat(token.nextToken()));
             }
             cities.add(metropolis);
             Line = input.readLine();
         }
      }
      catch (FileNotFoundException exp)
      {
          exp.printStackTrace();
      }
      catch (IOException exp)
      {
          exp.printStackTrace();
      }
    }
    
    /**
     * Method: displayCities()
 Displays City in JList sorted by level = 0 using selection sort
 algorithm or last name = 1 using the insertion sort algorithm.
     * @parem void
     * @return void
 pre-condition: Uses the ArrayList City.
 post-condition: City ArrayList is sorted and displayed either by
 level or last name.
     * @see #selectionSort
     * @see #insetionSort
     */
    private void displayCities()
    {
      int location = citiesJList.getSelectedIndex();
      String [] citiesNames = new String [cities.size()];
      if(popJRadioButtonMenuItem.isSelected()) {
            // sort by population using selection sort. Display city name with population
            selectionSort(cities);
            
            for (int i = 0; i < cities.size(); i++) {
                citiesNames[i] = cities.get(i).getName() + ", " + cities.get(i).getPopulation() + " mil";
            }
        }
        else //sort by name using insertion sort. Display city name only
        {
            insertionSort(cities);
            
            for (int i = 0; i < cities.size(); i++) {
                citiesNames[i] = cities.get(i).getName();
            }
        }
        
        citiesJList.setListData(citiesNames);
        if (location < 0) //if nothing is selected
            citiesJList.setSelectedIndex(0); //select first city
        else
            citiesJList.setSelectedIndex(location);  
    }

    /**
     * Method: insertionSort
 Sorts ArrayList City in ascending order by name. Uses the insertion
     * sort algorithm which inserts city at correct position and shuffles
     * everyone else below that position.
     * @parem ArrayList: cities
     * @return void
 pre-condition: ArrayList City filled-in with City objects.
 post-condition: City ArrayList is sorted by name.
     */
    public static void insertionSort(ArrayList <City> cities)
    {
	int i, j ;
        for ( i = 0; i<cities.size(); i++){
            City temp = cities.get(i);
            j = i- 1;
            while ( j >= 0 && cities.get(j).getName().compareToIgnoreCase(temp.getName())>0){
                cities.set(j+1, cities.get(j));
                j--;
            }
            cities.set(j + 1, temp);
        }
    }

    /**
     * Method: selectionSort
 Sorts ArrayList City in descending order by population. Calls
 findsMaximum to find city with maximum population in each pass
 and swap to exchange City when necessary.
     * @parem ArrayList: cities
     * @return void
 pre-condition: ArrayList City filled-in with City objects.
 post-condition: City ArrayList is sorted by population.
     */
    public void selectionSort(ArrayList < City > cities)
    {
       for( int i= 0 ; i < cities.size(); i++){
        int max = findMaximum(cities, i);
        swap(cities, i , max);
    }
    }  

    /**
     * Method: findMaximum
     * Called by selectionSort to find the index of the member with the maximum
     * population from a given index to the end of the ArrayList
     * @parem ArrayList: cities
     * @parem  int i: index from which to search for the max >= 0
     * @return int: position or index  where maximum is located
     * pre-condition: ArrayList members filled-in with members objects, int i >= 0.
     * post-condition: members ArrayList is sorted by level.
     */
    public int findMaximum(ArrayList < City > cities, int i)
    {
      int j , max = i ;
      for ( j = i +1 ; j < cities.size(); j++){
          if ( cities.get(j).getPopulation()> cities.get(max).getPopulation())
              max = j;
      }
      return max;
    }

    /**
     * Method: swap
     * Called by selectionSort to find the index of the member with the maximum
     * level from a given index to the end of the ArrayList
     * @parem ArrayList: members
     * @parem  int i: index of element to be swapped >= 0
     * @parem  int j: index of element to be swapped >= 0
     * @return void
     * pre-condition: ArrayList members filled-in with members objects, int i, j >= 0.
     * post-condition: members ArrayList with two members swapped.
     */
    public void swap(ArrayList < City > cities, int i, int j)
    {
       City temp = cities.get(i); 
       cities.set(i, cities.get(j)); 
       cities.set(j , temp); 
    }

    // Binary search for city 

    /** showCityData
     * This method is called from within the constructor to
     * display the data for the selected city.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void showCityData(int index)
    {
        nameJTextField.setText(cities.get(index).getName());
        popJTextField.setText(String.valueOf(cities.get(index).getPopulation()));
        medianJTextField.setText("$" + number.format(cities.get(index).getMedianIncome()));
        percentJTextField.setText(number.format(cities.get(index).getPercentLocal()) + "%");
        degreeJTextField.setText(number.format(cities.get(index).getAdvancedDegrees()) + "%");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menubuttonGroup = new javax.swing.ButtonGroup();
        titleJPanel = new javax.swing.JPanel();
        logoJLabel = new javax.swing.JLabel();
        titleJLabel = new javax.swing.JLabel();
        listJPanel = new javax.swing.JPanel();
        llistJScrollPane = new javax.swing.JScrollPane();
        citiesJList = new javax.swing.JList();
        displayJPanel = new javax.swing.JPanel();
        nameJLabel = new javax.swing.JLabel();
        nameJTextField = new javax.swing.JTextField();
        popJLabel = new javax.swing.JLabel();
        popJTextField = new javax.swing.JTextField();
        medianJLabel = new javax.swing.JLabel();
        medianJTextField = new javax.swing.JTextField();
        percentJLabel = new javax.swing.JLabel();
        percentJTextField = new javax.swing.JTextField();
        degreetJLabel = new javax.swing.JLabel();
        degreeJTextField = new javax.swing.JTextField();
        controlPanel = new javax.swing.JPanel();
        addJButton = new javax.swing.JButton();
        editJButton = new javax.swing.JButton();
        deleteJButton = new javax.swing.JButton();
        printJButton = new javax.swing.JButton();
        exitJButton = new javax.swing.JButton();
        citiesJMenuBar = new javax.swing.JMenuBar();
        fileJMenu = new javax.swing.JMenu();
        clearJMenuItem = new javax.swing.JMenuItem();
        printJMenuItem = new javax.swing.JMenuItem();
        fileJSeparator = new javax.swing.JPopupMenu.Separator();
        exitJMenuItem = new javax.swing.JMenuItem();
        sortJMenu = new javax.swing.JMenu();
        nameJRadioButtonMenuItem = new javax.swing.JRadioButtonMenuItem();
        popJRadioButtonMenuItem = new javax.swing.JRadioButtonMenuItem();
        actionJMenu = new javax.swing.JMenu();
        addJMenuItem = new javax.swing.JMenuItem();
        deleteJMenuItem = new javax.swing.JMenuItem();
        editJMenuItem = new javax.swing.JMenuItem();
        searchJMenuItem = new javax.swing.JMenuItem();
        helpJMenu = new javax.swing.JMenu();
        aboutJMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Large USA Cities Statistics");

        logoJLabel.setFont(new java.awt.Font("Tahoma", 2, 24)); // NOI18N
        logoJLabel.setForeground(new java.awt.Color(51, 0, 0));
        logoJLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/USACities/buckinghamfountain.jpg"))); // NOI18N
        logoJLabel.setToolTipText("");

        titleJLabel.setFont(new java.awt.Font("Tempus Sans ITC", 2, 24)); // NOI18N
        titleJLabel.setForeground(new java.awt.Color(51, 0, 0));
        titleJLabel.setText("Large Cities in USA");

        javax.swing.GroupLayout titleJPanelLayout = new javax.swing.GroupLayout(titleJPanel);
        titleJPanel.setLayout(titleJPanelLayout);
        titleJPanelLayout.setHorizontalGroup(
            titleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titleJPanelLayout.createSequentialGroup()
                .addContainerGap(70, Short.MAX_VALUE)
                .addComponent(logoJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(titleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        titleJPanelLayout.setVerticalGroup(
            titleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titleJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(titleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(titleJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(logoJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(titleJPanel, java.awt.BorderLayout.NORTH);

        citiesJList.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        citiesJList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                citiesJListValueChanged(evt);
            }
        });
        llistJScrollPane.setViewportView(citiesJList);

        javax.swing.GroupLayout listJPanelLayout = new javax.swing.GroupLayout(listJPanel);
        listJPanel.setLayout(listJPanelLayout);
        listJPanelLayout.setHorizontalGroup(
            listJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, listJPanelLayout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(llistJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        listJPanelLayout.setVerticalGroup(
            listJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(llistJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
        );

        getContentPane().add(listJPanel, java.awt.BorderLayout.LINE_START);

        displayJPanel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        displayJPanel.setLayout(new java.awt.GridLayout(5, 2, 5, 5));

        nameJLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nameJLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        nameJLabel.setText("Name of city: ");
        displayJPanel.add(nameJLabel);

        nameJTextField.setEditable(false);
        nameJTextField.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        displayJPanel.add(nameJTextField);

        popJLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        popJLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        popJLabel.setText("Population (in millions): ");
        displayJPanel.add(popJLabel);

        popJTextField.setEditable(false);
        popJTextField.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        popJTextField.setToolTipText("Press Enter to update");
        displayJPanel.add(popJTextField);

        medianJLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        medianJLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        medianJLabel.setText("Median income (per household): ");
        displayJPanel.add(medianJLabel);

        medianJTextField.setEditable(false);
        medianJTextField.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        medianJTextField.setToolTipText("Enter with no $ or commas and press Enter to update");
        displayJPanel.add(medianJTextField);

        percentJLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        percentJLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        percentJLabel.setText("Percent native to state: ");
        displayJPanel.add(percentJLabel);

        percentJTextField.setEditable(false);
        percentJTextField.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        percentJTextField.setToolTipText("Enter without % sign and pres Enter to update");
        displayJPanel.add(percentJTextField);

        degreetJLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        degreetJLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        degreetJLabel.setText("Percent advanced degrees: ");
        displayJPanel.add(degreetJLabel);

        degreeJTextField.setEditable(false);
        degreeJTextField.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        degreeJTextField.setToolTipText("Enter without % sign and press Enter to update");
        displayJPanel.add(degreeJTextField);

        getContentPane().add(displayJPanel, java.awt.BorderLayout.CENTER);

        controlPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        controlPanel.setMinimumSize(new java.awt.Dimension(299, 45));
        controlPanel.setLayout(new java.awt.GridLayout(1, 5, 5, 5));

        addJButton.setBackground(new java.awt.Color(204, 255, 204));
        addJButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        addJButton.setMnemonic('A');
        addJButton.setText("Add");
        addJButton.setToolTipText("Add new city");
        addJButton.setMinimumSize(new java.awt.Dimension(57, 45));
        addJButton.setPreferredSize(new java.awt.Dimension(57, 35));
        addJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addJButtonActionPerformed(evt);
            }
        });
        controlPanel.add(addJButton);

        editJButton.setBackground(new java.awt.Color(204, 255, 204));
        editJButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        editJButton.setMnemonic('E');
        editJButton.setText("Edit");
        editJButton.setToolTipText("Edit city. Press Enter in any of the JTextFields to confirm changes...");
        editJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editJButtonActionPerformed(evt);
            }
        });
        controlPanel.add(editJButton);

        deleteJButton.setBackground(new java.awt.Color(204, 255, 204));
        deleteJButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        deleteJButton.setMnemonic('D');
        deleteJButton.setText("Delete");
        deleteJButton.setToolTipText("Delete city");
        deleteJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteJButtonActionPerformed(evt);
            }
        });
        controlPanel.add(deleteJButton);

        printJButton.setBackground(new java.awt.Color(204, 255, 204));
        printJButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        printJButton.setMnemonic('P');
        printJButton.setText("Print");
        printJButton.setToolTipText("Print individual city data");
        printJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printJButtonActionPerformed(evt);
            }
        });
        controlPanel.add(printJButton);

        exitJButton.setBackground(new java.awt.Color(204, 255, 204));
        exitJButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        exitJButton.setMnemonic('x');
        exitJButton.setText("Exit");
        exitJButton.setToolTipText("Exit application");
        exitJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitJButtonActionPerformed(evt);
            }
        });
        controlPanel.add(exitJButton);

        getContentPane().add(controlPanel, java.awt.BorderLayout.SOUTH);

        fileJMenu.setMnemonic('F');
        fileJMenu.setText("File");

        clearJMenuItem.setMnemonic('C');
        clearJMenuItem.setText("Clear");
        clearJMenuItem.setToolTipText("Clear form");
        clearJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(clearJMenuItem);

        printJMenuItem.setMnemonic('P');
        printJMenuItem.setText("Print");
        printJMenuItem.setToolTipText("Print form");
        printJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(printJMenuItem);
        fileJMenu.add(fileJSeparator);

        exitJMenuItem.setMnemonic('x');
        exitJMenuItem.setText("Exit");
        exitJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(exitJMenuItem);

        citiesJMenuBar.add(fileJMenu);

        sortJMenu.setMnemonic('S');
        sortJMenu.setText("Sort");

        menubuttonGroup.add(nameJRadioButtonMenuItem);
        nameJRadioButtonMenuItem.setMnemonic('n');
        nameJRadioButtonMenuItem.setSelected(true);
        nameJRadioButtonMenuItem.setText("By Name");
        nameJRadioButtonMenuItem.setToolTipText("Sort by name and display only name");
        nameJRadioButtonMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameJRadioButtonMenuItemActionPerformed(evt);
            }
        });
        sortJMenu.add(nameJRadioButtonMenuItem);

        menubuttonGroup.add(popJRadioButtonMenuItem);
        popJRadioButtonMenuItem.setMnemonic('B');
        popJRadioButtonMenuItem.setText("By Population");
        popJRadioButtonMenuItem.setToolTipText("Sort by populatoin a nd display name and population");
        popJRadioButtonMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popJRadioButtonMenuItemActionPerformed(evt);
            }
        });
        sortJMenu.add(popJRadioButtonMenuItem);

        citiesJMenuBar.add(sortJMenu);

        actionJMenu.setMnemonic('t');
        actionJMenu.setText("Action");

        addJMenuItem.setMnemonic('A');
        addJMenuItem.setText("Add New City");
        addJMenuItem.setToolTipText("Add new city");
        addJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addJMenuItemActionPerformed(evt);
            }
        });
        actionJMenu.add(addJMenuItem);

        deleteJMenuItem.setMnemonic('D');
        deleteJMenuItem.setText("Delete City");
        deleteJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteJMenuItemActionPerformed(evt);
            }
        });
        actionJMenu.add(deleteJMenuItem);

        editJMenuItem.setMnemonic('E');
        editJMenuItem.setText("Edit City");
        editJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editJMenuItemActionPerformed(evt);
            }
        });
        actionJMenu.add(editJMenuItem);

        searchJMenuItem.setMnemonic('r');
        searchJMenuItem.setText("Search City");
        searchJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchJMenuItemActionPerformed(evt);
            }
        });
        actionJMenu.add(searchJMenuItem);

        citiesJMenuBar.add(actionJMenu);

        helpJMenu.setMnemonic('H');
        helpJMenu.setText("Help");

        aboutJMenuItem.setMnemonic('A');
        aboutJMenuItem.setText("About");
        aboutJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutJMenuItemActionPerformed(evt);
            }
        });
        helpJMenu.add(aboutJMenuItem);

        citiesJMenuBar.add(helpJMenu);

        setJMenuBar(citiesJMenuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clearAll()
    {
        //Clear and set JTextFields visible
        citiesJList.setEnabled(true);
        nameJTextField.setText("");
        nameJTextField.setEditable(true);
        popJTextField.setText("");
        popJTextField.setEditable(true);
        medianJTextField.setText("");
        medianJTextField.setEditable(true);
        percentJTextField.setText("");
        percentJTextField.setEditable(true);
        degreeJTextField.setText("");
        degreeJTextField.setEditable(true);
        nameJTextField.requestFocus();
       
    }
    //Event handler for Adding a new city
   //missing Javadocs
    private void addJButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_addJButtonActionPerformed
    {//GEN-HEADEREND:event_addJButtonActionPerformed
        // Add new city
         try
        {            
            // Create and display a new AddDialog
            AddCity addCity = new AddCity(this, true);
            addCity.setVisible(true);

            // The modal dialog takes focus, upon regaining focus:
            City newCity = addCity.getCity();
       //     System.out.println(newCity);
            if (newCity != null) //&& !cityExists(newCity)) 
            {
                // Add the new city to the database
                cities.add(newCity);              
                displayCities();                  //refresh GUI
                searchCity(newCity.getName());    //highlight added city
                
                //save new city to file
               saveCities();                                                
            }
            else
            {
                JOptionPane.showMessageDialog(null, "City not Added", 
                    "City is null or laready exists", JOptionPane.WARNING_MESSAGE);                
                citiesJList.setVisible(true);
                citiesJList.setSelectedIndex(0);                
            }

        }
        catch (NullPointerException nullex)
        {
            JOptionPane.showMessageDialog(null, "City not Added", "Input Error",
                  JOptionPane.WARNING_MESSAGE);            
            citiesJList.setVisible(true);
            citiesJList.setSelectedIndex(0);
       }           
    }//GEN-LAST:event_addJButtonActionPerformed
    
     //save city to file--needs Javadocs
    private void saveCities()
    {       
         try{
             writeToFile(fileName);
         }
         catch(IOException exp)
         {
             exp.printStackTrace();
         }
    }
    
      /**
     * Method: writeToFile
     * Write cities to a text file that is comma delimited.
     * @parem file: String
     * @return void
     * pre-condition: a valid file name, Citystats.txt is expected
     * post-condition: a new text file is created with the current cities
     * in the database
     * @see WriteFile
     * @see City
     */
    
    public void writeToFile(String file) throws IOException {
        PrintWriter output = new PrintWriter(file);
        for ( int i =0; i<cities.size(); i++){
           City temp = cities.get(i);
           String Line = temp.getName()+ " , "+ temp.getPopulation()+ " , "+
                   temp.getMedianIncome()+ " , "+temp.getPercentLocal()+ " , "+
                   temp.getAdvancedDegrees();
           output.println(Line);
        }
        output.close();
    }
    //Javadocs??
//    private boolean cityExists(City metropolis)
 //   {
        
 //   }

    // Needs Javadocs
    // Search for a city by name and highlight if found
    private void searchCity(String cityName){
      if (cityName != null && (cityName.length()>0)){
          nameJRadioButtonMenuItem.setSelected(true);
          displayCities();
          String [] citiesNmaeArray = new String [cities.size()];
          for ( int i = 0; i < citiesNmaeArray.length; i++){
              citiesNmaeArray[i] = cities.get(i).getName();
          }
          int index = linearSearch(citiesNmaeArray, cityName);
          if ( index<0){
              JOptionPane.showMessageDialog(null, cityName + " not found ", " Search result", JOptionPane.INFORMATION_MESSAGE);
          }
          else citiesJList.setSelectedIndex(index);
      }
  }
    public int linearSearch(String [] array, String key) {
    for ( int i = 0; i < array.length; i++){
        if (array[i].toLowerCase().contains(key.toLowerCase()))
            return i;
    }
    return -1;
}
    
    // Binary search for city
//    public static int binarySearch(String[] array, String key)
//    {
        
 //   }
   
    //Event handler for clearing the form    
    private void clearJMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_clearJMenuItemActionPerformed
    {//GEN-HEADEREND:event_clearJMenuItemActionPerformed
      clearAll();// Empty all fields and reset form by calling the method clearAll

}//GEN-LAST:event_clearJMenuItemActionPerformed
  
  
    //Event handler for printing the form   
    private void printJMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_printJMenuItemActionPerformed
    {//GEN-HEADEREND:event_printJMenuItemActionPerformed
        PrintUtilities.printComponent(this);// Print entire form
        
}//GEN-LAST:event_printJMenuItemActionPerformed

    private void exitJMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_exitJMenuItemActionPerformed
    {//GEN-HEADEREND:event_exitJMenuItemActionPerformed
        // Quit the application
        exitJButtonActionPerformed(evt);
}//GEN-LAST:event_exitJMenuItemActionPerformed

    //Event handler for displaying City sorted by name
    private void nameJRadioButtonMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_nameJRadioButtonMenuItemActionPerformed
    {//GEN-HEADEREND:event_nameJRadioButtonMenuItemActionPerformed
        // display City sorted by name
        displayCities();        
}//GEN-LAST:event_nameJRadioButtonMenuItemActionPerformed

    //Event handler for displaying City sorted by population
    private void popJRadioButtonMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_popJRadioButtonMenuItemActionPerformed
    {//GEN-HEADEREND:event_popJRadioButtonMenuItemActionPerformed
        // display City sorted by population
        displayCities();        
}//GEN-LAST:event_popJRadioButtonMenuItemActionPerformed

    //Event handler for adding new city
    private void addJMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_addJMenuItemActionPerformed
    {//GEN-HEADEREND:event_addJMenuItemActionPerformed
        // call buttonAddActionPerformed
        addJButtonActionPerformed(evt);
}//GEN-LAST:event_addJMenuItemActionPerformed

    //Event handler for deleting the selected city
    private void deleteJMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_deleteJMenuItemActionPerformed
    {//GEN-HEADEREND:event_deleteJMenuItemActionPerformed
        // call buttonDeleteActionPerformed
        deleteJButtonActionPerformed(evt);
}//GEN-LAST:event_deleteJMenuItemActionPerformed

    //Event handler for editing the selected city
    private void editJMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editJMenuItemActionPerformed
    {//GEN-HEADEREND:event_editJMenuItemActionPerformed
        // call buttonEditActionPerformed
        editJButtonActionPerformed(evt);
}//GEN-LAST:event_editJMenuItemActionPerformed

    private void aboutJMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_aboutJMenuItemActionPerformed
    {//GEN-HEADEREND:event_aboutJMenuItemActionPerformed
       //Display About form
       About citiesAbout = new About() ;
       citiesAbout.setVisible(true);
}//GEN-LAST:event_aboutJMenuItemActionPerformed

    //Event handler for searching specified city
    private void searchJMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_searchJMenuItemActionPerformed
    {//GEN-HEADEREND:event_searchJMenuItemActionPerformed
        // Find specified city
        String cityName = JOptionPane.showInputDialog("Enter the name of the city");
        searchCity(cityName);

    }//GEN-LAST:event_searchJMenuItemActionPerformed

    private void exitJButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_exitJButtonActionPerformed
    {//GEN-HEADEREND:event_exitJButtonActionPerformed
        // End  program
        System.exit(0);
    }//GEN-LAST:event_exitJButtonActionPerformed

     /**
     * Method: citiesJListValueChanged,an event handler to populate
     * the text fields onto the form with the selected city from the JList.
     * @return void
     * @parem evt: ListSelectionEvent evt
     */
    private void citiesJListValueChanged(javax.swing.event.ListSelectionEvent evt)//GEN-FIRST:event_citiesJListValueChanged
    {//GEN-HEADEREND:event_citiesJListValueChanged
        int index = (citiesJList.getSelectedIndex());
        if (index == -1)
        {
            index = 0;
        }
        showCityData(index);
    }//GEN-LAST:event_citiesJListValueChanged

    //Event handler for editing new city
    private void editJButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editJButtonActionPerformed
    {//GEN-HEADEREND:event_editJButtonActionPerformed
        //Clear and set JTextFields visible--not a good a good implementation
        int index = citiesJList.getSelectedIndex();
        try
        {   System.out.println("city: " + cities.get(index));
            City cityToEdit = new City(cities.get(index));
            EditCity editedCity = new EditCity(cityToEdit); 
            editedCity.setVisible(true);
            
             if(editedCity.getCity() != null){
                cities.remove(index);
                cities.add(editedCity.getCity());
                saveCities();
                displayCities();
                
            }
        }
       catch (NullPointerException exp){
                    JOptionPane.showMessageDialog(null, "city was not edited"
                           , " Edit Error", JOptionPane.WARNING_MESSAGE);
                    citiesJList.setSelectedIndex(0);
                    } 
    }//GEN-LAST:event_editJButtonActionPerformed

    //Event handler for deleting the selected city
    private void deleteJButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_deleteJButtonActionPerformed
    {//GEN-HEADEREND:event_deleteJButtonActionPerformed
        // Delete selected city
        int result = JOptionPane.showConfirmDialog(null, 
                "Are you sure to delete city?", "Delete City",
                JOptionPane.YES_NO_CANCEL_OPTION, 
                JOptionPane.INFORMATION_MESSAGE);
       if ( result == JOptionPane.YES_OPTION){
           int index = citiesJList.getSelectedIndex();
           cities.remove(index);
           displayCities();
           saveCities();
       }
    }//GEN-LAST:event_deleteJButtonActionPerformed

    //Event handler for printing the form
    private void printJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printJButtonActionPerformed
       PrintUtilities.printComponent(this);
       
    }//GEN-LAST:event_printJButtonActionPerformed
      
    /**
    * @param args the command line arguments
    */
    public static void main(String args[])
    {      
        // show splash screen
        CitiesGUI usaCities = new CitiesGUI();
        //usaCities.setLocationRelativeTo(null);
        usaCities.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutJMenuItem;
    private javax.swing.JMenu actionJMenu;
    private javax.swing.JButton addJButton;
    private javax.swing.JMenuItem addJMenuItem;
    private javax.swing.JList citiesJList;
    private javax.swing.JMenuBar citiesJMenuBar;
    private javax.swing.JMenuItem clearJMenuItem;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JTextField degreeJTextField;
    private javax.swing.JLabel degreetJLabel;
    private javax.swing.JButton deleteJButton;
    private javax.swing.JMenuItem deleteJMenuItem;
    private javax.swing.JPanel displayJPanel;
    private javax.swing.JButton editJButton;
    private javax.swing.JMenuItem editJMenuItem;
    private javax.swing.JButton exitJButton;
    private javax.swing.JMenuItem exitJMenuItem;
    private javax.swing.JMenu fileJMenu;
    private javax.swing.JPopupMenu.Separator fileJSeparator;
    private javax.swing.JMenu helpJMenu;
    private javax.swing.JPanel listJPanel;
    private javax.swing.JScrollPane llistJScrollPane;
    private javax.swing.JLabel logoJLabel;
    private javax.swing.JLabel medianJLabel;
    private javax.swing.JTextField medianJTextField;
    private javax.swing.ButtonGroup menubuttonGroup;
    private javax.swing.JLabel nameJLabel;
    private javax.swing.JRadioButtonMenuItem nameJRadioButtonMenuItem;
    private javax.swing.JTextField nameJTextField;
    private javax.swing.JLabel percentJLabel;
    private javax.swing.JTextField percentJTextField;
    private javax.swing.JLabel popJLabel;
    private javax.swing.JRadioButtonMenuItem popJRadioButtonMenuItem;
    private javax.swing.JTextField popJTextField;
    private javax.swing.JButton printJButton;
    private javax.swing.JMenuItem printJMenuItem;
    private javax.swing.JMenuItem searchJMenuItem;
    private javax.swing.JMenu sortJMenu;
    private javax.swing.JLabel titleJLabel;
    private javax.swing.JPanel titleJPanel;
    // End of variables declaration//GEN-END:variables


}
