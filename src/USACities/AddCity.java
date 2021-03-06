/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package USACities;

import java.awt.Toolkit;

/**
 *
 * @author user
 */
public class AddCity extends javax.swing.JDialog {

    private City newCity ;
    private boolean error = false;
    private String errorMessage ="";
    private final int MAX = 100;
    
    
    /**
     * Creates new form AddCity2
     */
    public AddCity(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.getRootPane().setDefaultButton(SaveJButton);
        this.setIconImage(Toolkit.getDefaultToolkit().
                getImage("src/USACities/buckinghamfountain.jpg"));
        setLocationRelativeTo(null);
        nameJTextField.requestFocus();
        newCity = null;
        this.setAlwaysOnTop(true);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleJPanel = new javax.swing.JPanel();
        logoJLabel = new javax.swing.JLabel();
        titleJLabel = new javax.swing.JLabel();
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
        SaveJPanel = new javax.swing.JPanel();
        SaveJButton = new javax.swing.JButton();
        cancelJButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        logoJLabel.setFont(new java.awt.Font("Tahoma", 2, 24)); // NOI18N
        logoJLabel.setForeground(new java.awt.Color(51, 0, 0));
        logoJLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/USACities/buckinghamfountain.jpg"))); // NOI18N
        logoJLabel.setToolTipText("");

        titleJLabel.setFont(new java.awt.Font("Tempus Sans ITC", 2, 24)); // NOI18N
        titleJLabel.setForeground(new java.awt.Color(51, 0, 0));
        titleJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleJLabel.setText("Add City");

        javax.swing.GroupLayout titleJPanelLayout = new javax.swing.GroupLayout(titleJPanel);
        titleJPanel.setLayout(titleJPanelLayout);
        titleJPanelLayout.setHorizontalGroup(
            titleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titleJPanelLayout.createSequentialGroup()
                .addContainerGap(80, Short.MAX_VALUE)
                .addComponent(logoJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(titleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        titleJPanelLayout.setVerticalGroup(
            titleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titleJPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(titleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(titleJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(logoJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)))
        );

        titleJLabel.getAccessibleContext().setAccessibleName("Add City");

        displayJPanel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        displayJPanel.setLayout(new java.awt.GridLayout(5, 2, 5, 5));

        nameJLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nameJLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        nameJLabel.setText("Name of city: ");
        displayJPanel.add(nameJLabel);

        nameJTextField.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        displayJPanel.add(nameJTextField);

        popJLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        popJLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        popJLabel.setText("Population (in millions): ");
        displayJPanel.add(popJLabel);

        popJTextField.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        popJTextField.setToolTipText("Press Enter to update");
        displayJPanel.add(popJTextField);

        medianJLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        medianJLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        medianJLabel.setText("Median income (per household): ");
        displayJPanel.add(medianJLabel);

        medianJTextField.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        medianJTextField.setToolTipText("Enter with no $ or commas and press Enter to update");
        displayJPanel.add(medianJTextField);

        percentJLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        percentJLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        percentJLabel.setText("Percent native to state: ");
        displayJPanel.add(percentJLabel);

        percentJTextField.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        percentJTextField.setToolTipText("Enter without % sign and pres Enter to update");
        displayJPanel.add(percentJTextField);

        degreetJLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        degreetJLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        degreetJLabel.setText("Percent advanced degrees: ");
        displayJPanel.add(degreetJLabel);

        degreeJTextField.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        degreeJTextField.setToolTipText("Enter without % sign and press Enter to update");
        displayJPanel.add(degreeJTextField);

        SaveJPanel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        SaveJPanel.setLayout(new java.awt.GridLayout(1, 0));

        SaveJButton.setBackground(java.awt.Color.green);
        SaveJButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        SaveJButton.setLabel("Save");
        SaveJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveJButtonActionPerformed(evt);
            }
        });
        SaveJPanel.add(SaveJButton);

        cancelJButton.setBackground(new java.awt.Color(51, 255, 0));
        cancelJButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cancelJButton.setText("Cancel");
        cancelJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelJButtonActionPerformed(evt);
            }
        });
        SaveJPanel.add(cancelJButton);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 685, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(titleJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(displayJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(35, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(95, Short.MAX_VALUE)
                    .addComponent(SaveJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(94, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 498, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(titleJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(243, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(259, Short.MAX_VALUE)
                    .addComponent(displayJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(68, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(436, Short.MAX_VALUE)
                    .addComponent(SaveJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SaveJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveJButtonActionPerformed

        String cityName = nameJTextField.getText();
        String pop = popJTextField.getText();
        float population = 0 ; 
        float med = Float.parseFloat(medianJTextField.getText());
        float local =Float.parseFloat(percentJTextField.getText()); 
        float degree = Float.parseFloat(degreeJTextField.getText());
        if ( ! Validation.isDouble(pop)){
            errorMessage += "Invalid city population\n";
            popJTextField.requestFocus();
            error = true;
        }
        else{
            population = Float.parseFloat(popJTextField.getText());
            error = false;
        }
        if(!error){
            newCity = new City(cityName, population, med, local, degree) ;
            this.dispose();
        }
    }//GEN-LAST:event_SaveJButtonActionPerformed

    private void cancelJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelJButtonActionPerformed
         this.dispose();
    }//GEN-LAST:event_cancelJButtonActionPerformed

    public City getCity(){
    return newCity;
}
    /**
     * @param args the command line arguments
     */
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SaveJButton;
    private javax.swing.JPanel SaveJPanel;
    private javax.swing.JButton cancelJButton;
    private javax.swing.JTextField degreeJTextField;
    private javax.swing.JLabel degreetJLabel;
    private javax.swing.JPanel displayJPanel;
    private javax.swing.JLabel logoJLabel;
    private javax.swing.JLabel medianJLabel;
    private javax.swing.JTextField medianJTextField;
    private javax.swing.JLabel nameJLabel;
    private javax.swing.JTextField nameJTextField;
    private javax.swing.JLabel percentJLabel;
    private javax.swing.JTextField percentJTextField;
    private javax.swing.JLabel popJLabel;
    private javax.swing.JTextField popJTextField;
    private javax.swing.JLabel titleJLabel;
    private javax.swing.JPanel titleJPanel;
    // End of variables declaration//GEN-END:variables
}
