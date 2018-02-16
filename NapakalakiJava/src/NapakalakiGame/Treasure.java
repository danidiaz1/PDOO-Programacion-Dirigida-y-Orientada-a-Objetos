/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NapakalakiGame;

/**
 *
 * @author Daniel Díaz Pareja
 */
public class Treasure {
    private String name;
    private int bonus;
    private TreasureKind type;
    
    /** @brief Constructor con argumentos
     * 
     * @param n Nombre del tesoro
     * @param b Bonus del tesoro
     * @param t Tipo de tesoro
     */
    public Treasure (String n, int b, TreasureKind t)
    {
        name = n;
        bonus = b;
        type = t;
    }
    
    // Métodos get
    public String getName() {return name;}
    public int getBonus() {return bonus;}
    public TreasureKind getType() {return type;}
    
    @Override
    public String toString(){
        return "\nname = " + name + 
               "\nbonus = " + bonus +
               "\ntype = "+ type; 
    }
    
}
