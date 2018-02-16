/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NapakalakiGame;

/**
 *
 * @author danidiaz
 */
public class DeathBadConsequence extends NumericBadConsequence {

    
    /** Cartas que te hacen morir
     * @param text Texto de la consecuencia
     * @param death Posibilidad de morir. Valores true o false.
     */
    public DeathBadConsequence(String text) // DBC
    {
        super(text,Player.getMaxLevel(),MAXTREASURES,MAXTREASURES);
    }
    
            
}
