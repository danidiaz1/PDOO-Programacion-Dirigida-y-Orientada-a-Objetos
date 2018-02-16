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
public class SpecificBadConsequence extends BadConsequence {
    
    private ArrayList<TreasureKind> specificHiddenTreasures = new ArrayList();
    private ArrayList<TreasureKind> specificVisibleTreasures = new ArrayList();
    
    /** Cartas que te hacen perder niveles y objetos especificos (visibles 
     * y/o ocultos)
     * @param text Texto de la consecuencia
     * @param levels Niveles que pierdes
     * @param tVisible Array con los objetos visibles que pierdes
     * @param tHidden Array con los objetos ocultos que pierdes
     */
    public SpecificBadConsequence(String text, int levels, 
            ArrayList<TreasureKind>  tVisible,
            ArrayList<TreasureKind>  tHidden)
    {
        super(text, levels);
        
        
        // La asignacion entre objetos se hace por referencia automáticamente
        // (direcciones de memoria). Por eso, podría modificarse desde fuera
        // de la clase. Así que utilizamos el constructor de copia:
        this.specificVisibleTreasures = new ArrayList(tVisible);
        this.specificHiddenTreasures = new ArrayList(tHidden);
    }
    public ArrayList<TreasureKind> getSpecificVisibleTreasures(){return specificVisibleTreasures;}
    public ArrayList<TreasureKind> getSpecificHiddenTreasures(){return specificHiddenTreasures;}

    @Override
    public boolean isEmpty() {
        return (specificVisibleTreasures.isEmpty() && specificHiddenTreasures.isEmpty());
    }

    @Override
    public SpecificBadConsequence adjustToFitTreasureLists(ArrayList<Treasure> v, ArrayList<Treasure> h) {
        SpecificBadConsequence new_bc = this; // Si no hay que hacer nada, la nueva bc es ella misma
        
        if (!specificVisibleTreasures.isEmpty() || !specificHiddenTreasures.isEmpty())
        {
            /* Comprobamos que los tipos de tesoro del jugador y el BadConsequence estén
            bien ajustados. Por cada tipo de cada tesoro del jugador, comprobaremos que
            también existe ese tipo en la bc. Si es así, añadiremos a un nuevo vector
            (que formará la nueva bc) dicho tipo, y lo quitaremos de una copia
            de la bc original para evitar duplicados. */
            
            ArrayList<TreasureKind> specificVisibleTreasures_copy = 
                    new ArrayList<TreasureKind>(specificVisibleTreasures);
            ArrayList<TreasureKind> specificHiddenTreasures_copy = 
                    new ArrayList<TreasureKind>(specificHiddenTreasures);
            ArrayList<TreasureKind> new_specificVisibleTreasures = new ArrayList();
            ArrayList<TreasureKind> new_specificHiddenTreasures = new ArrayList();
            
            if (!specificVisibleTreasures.isEmpty())
            {
                for (Treasure t:v)
                {
                    TreasureKind tipo = t.getType();

                    for (TreasureKind tipo2:specificVisibleTreasures_copy)
                    {
                        if (tipo == tipo2)
                        {
                            new_specificVisibleTreasures.add(tipo);
                            specificVisibleTreasures_copy.remove(tipo);
                            break;
                        }
                    }
                }
            }
            
            if (!specificHiddenTreasures.isEmpty())
            {
                for (Treasure t:h)
                {
                    TreasureKind tipo = t.getType();

                    for (TreasureKind tipo2:specificHiddenTreasures_copy)
                    {
                        if (tipo == tipo2)
                        {
                            new_specificHiddenTreasures.add(tipo);
                            specificHiddenTreasures_copy.remove(tipo);
                            break;
                        }
                    }
                }
            }
            // Si el jugador no puede perder tantos niveles como indica la BC,
            // hay que ajustarlos
            int new_levels = getLevels();
            
            
            new_bc = new SpecificBadConsequence(getText(),new_levels,
                    new_specificVisibleTreasures,
                    new_specificHiddenTreasures);
        } // fin if (!specificVisibleTreasures.isEmpty() || !specificHiddenTreasures.isEmpty())

        return new_bc;
    }

    @Override
    public void substractVisibleTreasure(Treasure t) {
        if(!specificVisibleTreasures.isEmpty())
            specificVisibleTreasures.remove(t.getType());
    }

    @Override
    public void substractHiddenTreasure(Treasure t) {
        if(!specificHiddenTreasures.isEmpty())
            specificHiddenTreasures.remove(t.getType());
    }
    
    @Override
    public String toString(){
        return super.toString();
    }
}
