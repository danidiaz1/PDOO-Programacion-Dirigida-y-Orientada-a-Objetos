/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import NapakalakiGame.Monster;
import javax.swing.JLabel;

/**
 *
 * @author Dani
 */
public class MonsterView extends javax.swing.JPanel {
    Monster monsterModel;
    /**
     * Creates new form MonsterView
     */
    public MonsterView() {
        initComponents();
    }
    
    public void setMonster(Monster m){
        monsterModel = m;
        jLabelMonsterIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/images/monsters/"+m.getName()+".jpg")));
        repaint();
    }
    
    public JLabel getJLabelMonsterIcon()
    {
        return jLabelMonsterIcon;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelTitle = new javax.swing.JLabel();
        jLabelMonsterIcon = new javax.swing.JLabel();
        jLabelMonsterTitleIcon = new javax.swing.JLabel();

        setBackground(new java.awt.Color(153, 204, 255));

        jLabelTitle.setFont(new java.awt.Font("Algerian", 0, 18)); // NOI18N
        jLabelTitle.setText("Monstruo Actual");

        jLabelMonsterTitleIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/images/monsters/smorc.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelMonsterIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelMonsterTitleIcon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelTitle)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTitle, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelMonsterTitleIcon, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(jLabelMonsterIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelMonsterIcon;
    private javax.swing.JLabel jLabelMonsterTitleIcon;
    private javax.swing.JLabel jLabelTitle;
    // End of variables declaration//GEN-END:variables
}
