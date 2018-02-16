/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NapakalakiGame;

import java.util.ArrayList;

/**
 *
 * @author danidiaz
 */
public class NumericBadConsequence extends BadConsequence {
    private int nVisibleTreasures;
    private int nHiddenTreasures;

   /** Cartas que te hacen perder niveles y objetos.
     * @param text Texto de la consecuencia
     * @param levels Niveles perdidos
     * @param nVisible Número de objetos visibles que pierdes
     * @param nHidden Número de objetos ocultos que pierdes
     */
    public NumericBadConsequence(String text, int levels, int nVisible, int nHidden) // NBC
    {
        super(text,levels);
        nVisibleTreasures = nVisible;
        nHiddenTreasures = nHidden;
    }
    
    public int getnVisibleTreasures(){return nVisibleTreasures;}
    public int getnHiddenTreasures(){return nHiddenTreasures;} 

    @Override
    public boolean isEmpty() {
        return (nHiddenTreasures == 0 && nVisibleTreasures == 0);
    }

    @Override
    public NumericBadConsequence adjustToFitTreasureLists(ArrayList<Treasure> v, ArrayList<Treasure> h) {
        NumericBadConsequence new_bc = this; // Si no hay que hacer nada, la nueva bc es ella misma
        
        if (nVisibleTreasures > 0 || nHiddenTreasures > 0)
        {
            // Si el número de tesoros del jugador es menor que
            // los tesoros que tenemos que perder según la bc,
            // actualizamos su valor
            int new_nVisibleTreasures = 0, new_nHiddenTreasures = 0;
            
            if (v.size() < nVisibleTreasures)
                new_nVisibleTreasures = v.size();

            if (h.size() < nHiddenTreasures)
                new_nHiddenTreasures = h.size();
            
            new_bc = new NumericBadConsequence(getText(), getLevels(),
                        new_nVisibleTreasures,
                        new_nHiddenTreasures
                        );
        }
        
        return new_bc;
    }

    @Override
    public void substractVisibleTreasure(Treasure t) {
        if (nVisibleTreasures > 0)
            nVisibleTreasures--;
    }

    @Override
    public void substractHiddenTreasure(Treasure t) {
        if (nHiddenTreasures > 0)
            nHiddenTreasures--;
    }
    
    @Override
    public String toString(){
        return super.toString();
    }
}
