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
public class Cultist {
    private String name;
    private int gainedLevels;

    /** @brief Constructor por defecto
     * @param n Nombre
     * @param l Niveles ganados
     */
    public Cultist(String n, int l)
    {
        name = n;
        gainedLevels = l;
    }
    
    public int getGainedLevels(){return gainedLevels;}
    
    @Override
    public String toString()
    {
       return "name: " + name +
               "\ngainedLevels: " + gainedLevels;
    }
}
