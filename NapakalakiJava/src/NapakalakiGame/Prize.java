/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NapakalakiGame;

/** Premios obtenidos por el jugador al ganar al monstruo
 * @author Daniel Díaz Pareja
 */
public class Prize {
    private int treasures; // Tesoros obtenidos al ganar al monstruo.
    private int level; // Niveles obtenidos al ganar al monstruo.
    
    /** Constructor con dos argumentos
     * @param level Niveles obtenidos al ganar al monstruo
     * @param treasures Tesoros obtenidos al ganar al monstruo
     */
    public Prize(int level, int treasures){
        this.level = level;
        this.treasures = treasures;
    }
    
    // Métodos get
    public int getTreasures(){return treasures;}
    public int getLevel(){return level;}
    
    // Método toString
    @Override
    public String toString(){
        return "\ntreasures = " + Integer.toString(treasures) + 
                "\nlevels = " + Integer.toString(level);
    }
    
}
