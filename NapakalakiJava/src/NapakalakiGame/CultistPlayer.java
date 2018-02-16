/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NapakalakiGame;

import java.util.Random;

/**
 *
 * @author danidiaz
 */
public class CultistPlayer extends Player
{
    private static int totalCultistPlayers = 0;
    private Cultist myCultistCard;
    
    /**
     * @brief Constructor
     */
    public CultistPlayer(Player p, Cultist c)
    {
        super(p);
        myCultistCard = c;
        totalCultistPlayers++;
    }
    
    /**
     * @brief Consultor de cuantos jugadores cultist hay.
     * @return 
     */
    public static int getTotalCultistPlayers()
    {
        return totalCultistPlayers;
    }
    
    /**
     * @brief Redefinicion del metodo getCombatLevel
     * @return 
     */
    @Override
    public int getCombatLevel()
    {
        int nivel_superior = super.getCombatLevel();
        int nivel_carta = myCultistCard.getGainedLevels();
        
        return (int) (nivel_superior + 0.2*nivel_superior + 
                nivel_carta*totalCultistPlayers);
    }
    
    /**
     * @brief Redefinición del método de la clase superior.
     *      Se hace lo mismo, pero con los tesoros visibles.
     * @return 
     */
    @Override
    protected boolean canYouGiveMeATreasure()
    {
        return (!getVisibleTreasures().isEmpty());
    }
    
    /**
     * @brief Redefinición del método de la clase superior.
     *      Se hace lo mismo, pero con los tesoros visibles.
     * @return 
     */
    @Override
    protected Treasure giveMeATreasure()
    {
        int aleatorio = new Random().nextInt(getVisibleTreasures().size());
        Treasure tesoro = getVisibleTreasures().get(aleatorio);
        Treasure nuevo_tesoro = new Treasure(tesoro.getName(),tesoro.getBonus(),tesoro.getType());
        getVisibleTreasures().remove(tesoro);

        return (nuevo_tesoro);
    }
    
    /**
     * @brief El jugador no puede convertirse en cultist porque ya lo es.
     * @return Siempre falso.
     */
    @Override
    protected boolean shouldConvert()
    {
        return false;
    }
    
    /**
     * @brief Devuelve el nivel del combate contra cultist player del monstruo
     * @param m Monstruo a pedir el nivel
     * @return Nivel del monstruo contra jugador cultist
     */
    @Override
    protected int getOponentLevel(Monster m)
    {
        return m.getCombatLevelAgainstCultistPlayer();
    }
    
    @Override
    public String toString()
    {
        return super.toString() + "\nSoy cultist player" +
                "\nCarta: " + myCultistCard.toString();
    }
}
