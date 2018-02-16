/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NapakalakiGame;

/** Clase para las cartas monstruo
 * @author Daniel Díaz Pareja
 */
public class Monster 
{
    // Datos miembro
    private String name;
    private int combatLevel;
    private Prize prize;
    private BadConsequence bc;
    private int levelChangeAgainstCultistPlayer;

    // Métodos get
    public String getName() {return name;}
    public int getCombatLevel() {return combatLevel;}
    public int getTreasuresGained() {return prize.getTreasures();}
    public BadConsequence getBadConsequence() {return bc;}
    public int getLevelsGained(){return prize.getLevel();};
    
    /** Constructor con 4 argumentos
     * @param name Nombre del monstruo
     * @param level Nivel del monstruo
     * @param bc Consecuencia de perder ante el monstruo
     * @param prize Premio al vencer al monstruo
     */
    public Monster(String name, int level, BadConsequence bc, Prize prize)
    {
        this.name = name;
        this.combatLevel = level;
        this.bc = bc;
        this.prize = prize;
        this.levelChangeAgainstCultistPlayer = 0;
    }
    
    /**
     * 
     * @param n Nombre del monstruo
     * @param l Nivel del monstruo
     * @param badConsequence Consecuencia de perder ante el monstruo
     * @param p Premio al vencer al monstruo
     * @param IC 
     */
    public Monster(String n, int l, BadConsequence badConsequence, Prize p, int IC)
    {
        this.name = n;
        this.combatLevel = l;
        this.bc = badConsequence;
        this.prize = p;
        this.levelChangeAgainstCultistPlayer = IC;
    }
    
    public int getCombatLevelAgainstCultistPlayer()
    {
        return (levelChangeAgainstCultistPlayer+combatLevel);
    }
    
    @Override
    public String toString()
    {
        return "\nname = " + name + 
                "\ncombatLevel = " + Integer.toString(combatLevel) +
                "\nprize = "+ prize.toString() +
                "\nbc = " + bc.toString() +
                "\nlevelChangeAgainstCultistPlayer = " + levelChangeAgainstCultistPlayer;           
    }
    
}
