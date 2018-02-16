/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NapakalakiGame;

import java.util.ArrayList;

/** 
 * @brief Consecuencias de que el jugador pierda contra un monstruo
 * @author Daniel Díaz Pareja
 */
public abstract class BadConsequence {
    
    // Constantes de clase
    static final int MAXTREASURES = 10;
    
    // Datos miembro
    private String text;
    private int levels;
    
    // Constructor
    protected BadConsequence(String text, int levels)
    {
        this.text = text;
        this.levels = levels;
    }
    
    // Métodos get
    public String getText(){return text;}
    public int getLevels(){return levels;}
    public static int getMaxTreasureS(){return MAXTREASURES;}
    
    // Métodos set
    public void setText(String cad){text=cad;}
   
    /**
     * @brief Comprueba si el objeto está vacío
     * @return true si el objeto está vacio, false en caso contrario
     */
    public abstract boolean isEmpty();
    
    
    /**
     * @brief Recibe como parámetros los tesoros visibles y ocultos de los que dispone el jugador y
        devuelve un nuevo objeto mal rollo que se ajusta a las posibilidades del jugador. Los
        atributos de BadConsequence que debemos tener en cuenta para ajustar el el mal rollo que
        debe cumplir el jugador son nVisibleTreasures, nHiddenTreasures, specificVisibleTreasures y
        specificHiddenTreasures.
     * @param v Tesoros visibles del jugador
     * @param h Tesoros ocultos del jugador
     * @return Nuevo mal rollo que se ajusta a las posibilidades del jugador
     */
    public abstract BadConsequence adjustToFitTreasureLists(ArrayList<Treasure> v, ArrayList<Treasure> h);

    /**
     * @brief Actualiza el mal rollo para que el tesoro visible t no forme parte del mismo. Es posible que
        esta actualización no implique cambio alguno, que lleve a eliminar un tipo específico de
        tesoro visible, o a reducir el número de tesoros visibles pendientes.
     * @param t Tesoro a eliminar
     */
    public abstract void substractVisibleTreasure(Treasure t);
    
    /**
     * @brief Actualiza el mal rollo para que el tesoro oculto t no forme parte del mismo. Es posible que
        esta actualización no implique cambio alguno, que lleve a eliminar un tipo específico de
        tesoro oculto, o a reducir el número de tesoros ocultos pendientes.
     * @param t Tesoro a eliminar
     */
    public abstract void substractHiddenTreasure(Treasure t);
    
    // Método toString
    @Override
    public String toString(){
        return text;             
    }
}