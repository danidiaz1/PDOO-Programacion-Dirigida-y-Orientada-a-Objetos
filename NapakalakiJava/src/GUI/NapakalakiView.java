/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import NapakalakiGame.CombatResult;
import NapakalakiGame.Napakalaki;
import NapakalakiGame.Player;
import NapakalakiGame.Sound;
import javax.swing.JLabel;

/**
 *
 * @author Daniel Diaz Pareja
 */
public class NapakalakiView extends javax.swing.JFrame {

    private Napakalaki NapakalakiModel;
    private static Sound buttonSelectOK = new Sound("/GUI/sound/jButtonSelect.wav");
    private static Sound buttonSelectCancel = new Sound("/GUI/sound/jButtonCancel.wav");
    private Sound combat;
    private static Sound mainTheme = new Sound("/GUI/sound/mainTheme.wav");
    
    /**
     * Creates new form NapakalakiView
     */
    public NapakalakiView() {
        
        getContentPane().setBackground(new java.awt.Color(153, 204, 255));
        initComponents();
        jButtonCombat.setEnabled(false);
        jButtonNextTurn.setEnabled(false);
        playerView.getButtonSteal().setEnabled(false);
        playerView.getButtonDiscardAllTreasures().setEnabled(false);
    }
    
    /**
     * @brief Método set para el atributo NapakalakiModel
     * @param n Parámetro que será el nuevo NapakalakiModel
     */
    public void setNapakalaki(Napakalaki n){
        NapakalakiModel = n;
        Player currentPlayer = NapakalakiModel.getCurrentPlayer();
        
        playerView.setPlayer(currentPlayer);
        playerView.setNapakalaki(NapakalakiModel);

        repaint();
    }
    
    
    public void disableAllButtons()
    {
        jButtonCombat.setEnabled(false);
        jButtonNextTurn.setEnabled(false);
        jButtonMeetTheMonster.setEnabled(false);
        playerView.getButtonDiscardAllTreasures().setEnabled(false);
        playerView.getButtonDiscardTreasure().setEnabled(false);
        playerView.getButtonSteal().setEnabled(false);
        playerView.getButtonMakeTreasureVisible().setEnabled(false);
    }
    
    public static Sound getSoundButtonSelect()
    {
        return buttonSelectOK;
    }
    
    public static Sound getSoundButtonSelectCancel()
    {
        return buttonSelectCancel;
    }
    
    public static Sound getSoundMainTheme()
    {
        return mainTheme;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonMeetTheMonster = new javax.swing.JButton();
        jButtonCombat = new javax.swing.JButton();
        jButtonNextTurn = new javax.swing.JButton();
        jLabelCombatResultTitle = new javax.swing.JLabel();
        jLabelCombatResult = new javax.swing.JLabel();
        monsterView = new GUI.MonsterView();
        playerView = new GUI.PlayerView();
        jLabelWarning = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Napakalaki");
        setBackground(new java.awt.Color(153, 204, 255));
        setSize(new java.awt.Dimension(0, 0));

        jButtonMeetTheMonster.setBackground(new java.awt.Color(204, 255, 204));
        jButtonMeetTheMonster.setFont(new java.awt.Font("Bell MT", 1, 14)); // NOI18N
        jButtonMeetTheMonster.setText("Conoce al monstruo");
        jButtonMeetTheMonster.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 255, 51), 2, true));
        jButtonMeetTheMonster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMeetTheMonsterActionPerformed(evt);
            }
        });

        jButtonCombat.setBackground(new java.awt.Color(255, 153, 153));
        jButtonCombat.setFont(new java.awt.Font("Bell MT", 1, 14)); // NOI18N
        jButtonCombat.setText("¡Combate!");
        jButtonCombat.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 51, 51), 2, true));
        jButtonCombat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCombatActionPerformed(evt);
            }
        });

        jButtonNextTurn.setBackground(new java.awt.Color(255, 204, 102));
        jButtonNextTurn.setFont(new java.awt.Font("Bell MT", 1, 14)); // NOI18N
        jButtonNextTurn.setText("Pasar el turno");
        jButtonNextTurn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 0), 2, true));
        jButtonNextTurn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNextTurnActionPerformed(evt);
            }
        });

        jLabelCombatResultTitle.setFont(new java.awt.Font("Bell MT", 1, 18)); // NOI18N
        jLabelCombatResultTitle.setText("Resultado del combate");

        jLabelCombatResult.setFont(new java.awt.Font("Bell MT", 1, 18)); // NOI18N
        jLabelCombatResult.setForeground(new java.awt.Color(255, 51, 51));

        jLabelWarning.setFont(new java.awt.Font("Bell MT", 1, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(playerView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(monsterView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabelCombatResultTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButtonMeetTheMonster, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButtonNextTurn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButtonCombat, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelCombatResult, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 4, Short.MAX_VALUE))
                    .addComponent(jLabelWarning, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(playerView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(monsterView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelCombatResultTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelCombatResult, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonMeetTheMonster, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jButtonCombat, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jButtonNextTurn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabelWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCombatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCombatActionPerformed
        // TODO add your handling code here:
        buttonSelectOK.Play(false);
        combat.Stop();
        mainTheme.Play(true);
        CombatResult cr =NapakalakiModel.developCombat();
        
        playerView.setPlayer(NapakalakiModel.getCurrentPlayer());
        if (cr == CombatResult.WINGAME){
            Sound wingame = new Sound("/GUI/sound/wingame.wav");
            mainTheme.Stop();
            wingame.Play(false);
            disableAllButtons();
            jLabelWarning.setText("FIN DEL JUEGO.");
            jLabelCombatResult.setText("Ganas la partida");
        } else if (cr == CombatResult.WIN){
            jLabelCombatResult.setText("Ganas el combate");
            Sound win = new Sound("/GUI/sound/winsound.wav");
            win.Play(false);
            jButtonCombat.setEnabled(false);
            playerView.getButtonDiscardAllTreasures().setEnabled(true);
            playerView.getButtonDiscardTreasure().setEnabled(true);
            playerView.getButtonDiscardAllTreasures().setEnabled(true);
            
            if (NapakalakiModel.getCurrentPlayer().canISteal())
                playerView.getButtonSteal().setEnabled(true);

            playerView.getButtonMakeTreasureVisible().setEnabled(true);
            jButtonNextTurn.setEnabled(true);
        } else {
            jLabelCombatResult.setText("Pierdes el combate");
            jButtonCombat.setEnabled(false);
            playerView.getButtonDiscardAllTreasures().setEnabled(true);
            playerView.getButtonDiscardTreasure().setEnabled(true);
            playerView.getButtonDiscardAllTreasures().setEnabled(true);
            
            if (NapakalakiModel.getCurrentPlayer().canISteal())
                playerView.getButtonSteal().setEnabled(true);
            
            playerView.getButtonMakeTreasureVisible().setEnabled(true);
            jButtonNextTurn.setEnabled(true);
        }
        
        setNapakalaki(NapakalakiModel);
        
    }//GEN-LAST:event_jButtonCombatActionPerformed

    private void jButtonMeetTheMonsterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMeetTheMonsterActionPerformed
        // TODO add your handling code here:
        mainTheme.Stop();
        combat = new Sound("/GUI/sound/meetTheMonster.wav");
        combat.Play(true);
        monsterView.setMonster(NapakalakiModel.getCurrentMonster());
        jButtonCombat.setEnabled(true);
        jButtonMeetTheMonster.setEnabled(false);
        playerView.getButtonMakeTreasureVisible().setEnabled(false);
        playerView.getButtonDiscardTreasure().setEnabled(false);
        playerView.getButtonDiscardAllTreasures().setEnabled(false);
        
        buttonSelectOK.Play(false);
        setNapakalaki(NapakalakiModel);
    }//GEN-LAST:event_jButtonMeetTheMonsterActionPerformed

    private void jButtonNextTurnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNextTurnActionPerformed
        // TODO add your handling code here:
        
        if (!NapakalakiModel.getCurrentPlayer().getPendingBadConsequence().isEmpty())
        {
            buttonSelectCancel.Play(false);
            jLabelWarning.setText("No has cumplido el mal rollo");
        }  
        else
        {
            buttonSelectOK.Play(false);
            NapakalakiModel.nextTurn();
            playerView.setNapakalaki(NapakalakiModel);
            playerView.getButtonMakeTreasureVisible().setEnabled(true);
            playerView.getButtonSteal().setEnabled(false);
            jButtonMeetTheMonster.setEnabled(true);
            jButtonNextTurn.setEnabled(false);
            
            monsterView.getJLabelMonsterIcon().setIcon(null);
            monsterView.getJLabelMonsterIcon().revalidate();
            playerView.getPendingBCText().setText("");
            jLabelCombatResult.setText("");
            jLabelWarning.setText("");
            
            
            setNapakalaki(NapakalakiModel);
        }
            
    }//GEN-LAST:event_jButtonNextTurnActionPerformed


    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCombat;
    private javax.swing.JButton jButtonMeetTheMonster;
    private javax.swing.JButton jButtonNextTurn;
    private javax.swing.JLabel jLabelCombatResult;
    private javax.swing.JLabel jLabelCombatResultTitle;
    private javax.swing.JLabel jLabelWarning;
    private GUI.MonsterView monsterView;
    private GUI.PlayerView playerView;
    // End of variables declaration//GEN-END:variables


}


