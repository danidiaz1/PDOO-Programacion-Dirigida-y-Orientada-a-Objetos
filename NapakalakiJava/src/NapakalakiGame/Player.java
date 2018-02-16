/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NapakalakiGame;

import GUI.Dice;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Daniel Díaz Pareja
 */
public class Player {
    private static int MAXLEVEL = 10;
    private String name;
    private int level;
    private boolean dead;
    private boolean canISteal;
    private ArrayList<Treasure> hiddenTreasures = new ArrayList();
    private ArrayList<Treasure> visibleTreasures = new ArrayList();
    protected Player enemy;
    private BadConsequence pendingBadConsequence;
    
    /** @brief Constructor con argumentos
     * @param name Nombre del jugador
     */
    public Player(String name)
    {
        this.name = name;
        level = 1;
        dead = true;
        canISteal = true;
        pendingBadConsequence = new NumericBadConsequence("",0,0,0);
        this.enemy = null;
    }
    
    /**
     * @brief Constructor de copia
     * @param p Jugador a copiar
     */
    public Player (Player p)
    {
        name = p.name;
        level = p.level;
        dead = p.dead;
        canISteal = p.canISteal;
        pendingBadConsequence = p.pendingBadConsequence;
        enemy = p.enemy;  
        visibleTreasures = p.visibleTreasures;
        hiddenTreasures = p.hiddenTreasures;
    }

    public Player getEnemy() {return enemy;}
    public BadConsequence getPendingBadConsequence() {return pendingBadConsequence;}
    public String getName(){return name;}
    public static int getMaxLevel(){return MAXLEVEL;}
    private void bringToLife(){dead = false;}
    
    /**
     * @return Devuelve el nivel de combate del jugador, que viene dado por su nivel más los
        bonus que le proporcionan los tesoros que tenga equipados, según las reglas del
        juego.
     */
    public int getCombatLevel(){
        int combat_level = level;
        if (visibleTreasures != null)
            for (Treasure t: visibleTreasures)
                combat_level += t.getBonus();
        
        return combat_level;
    }
    
    private void incrementLevels(int l)
    {
        level += l;
      
        if (level > 10)
            level = 10;
    }
    
    private void decrementLevels(int l)
    {
        level -= l;
        
        if(level < 1)
            level = 1;
    }
    
    private void setPendingBadConsequence(BadConsequence b)
    {
        pendingBadConsequence = b;
    }
    
    /**
     * @brief Esta operación es la encargada de aplicar el buen rollo del monstruo vencido al jugador,
        sumando los niveles correspondientes y pidiendo al CardDealer que le dé el número de
        tesoros indicado en el buen rollo del monstruo. Esos tesoros se añaden a sus tesoros
        ocultos.
     * @param m Monstruo del que se aplica el premio
     */
    private void applyPrize(Monster m)
    {
        int nLevels = m.getLevelsGained();
        incrementLevels(nLevels);
        
        int nTreasures = m.getTreasuresGained();
        
        if (nTreasures > 0)
        {
            CardDealer dealer = CardDealer.getInstance();
            
            for (int i = 1; i <= nTreasures; i++)
            {
                Treasure treasure =dealer.nextTreasure();
                hiddenTreasures.add(treasure);
            }
        }
    }
    
    /**
     * @brief Cuando el jugador ha perdido el combate, hay que considerar el mal rollo que le impone el
        monstruo con el que combatió. Para ello, decrementa sus niveles según indica el mal rollo y
        guarda una copia de un objeto badConsequence ajustada a los tesoros que puede perder.
        Es decir, un objeto mal rollo que refleje el mal rollo indicado por el monstruo pero
        eliminando las condiciones que el jugador no pueda cumplir según los tesoros de que
        disponga (por ejemplo si el mal rollo del monstruo implica perder 2 tesoros visibles y el
        jugador sólo tiene 1, entonces el mal rollo pendiente será de sólo 1 tesoro visible). La
        operación encargada de hacer esto es adjustToFitTreasureList de la clase badConsequence.
        Éste es el mal rollo pendiente (pendingbadConsequence) que el jugador almacenará y que
        deberá cumplir descartándose de esos tesoros antes de que pueda pasar al siguiente turno.
     * @param m Monstruo del que aplicar el mal rollo
     */
    private void applyBadConsequence(Monster m)
    {
        BadConsequence badConsequence = m.getBadConsequence();
        int nLevels = badConsequence.getLevels();
        
        decrementLevels(nLevels);
        
        BadConsequence pendingBad = badConsequence.adjustToFitTreasureLists(visibleTreasures, hiddenTreasures);
        setPendingBadConsequence(pendingBad);
    }
    
    /**
     * @brief Comprueba si el tesoro t se puede pasar de oculto a visible según las reglas del juego.
     * @param T Tesoro oculto a pasar a visible
     * @return true si se puede pasar a visible, false en caso contrario
     */
    private boolean canMakeTreasureVisible(Treasure T)
    {
        boolean resultado = false;
        
        // Comprobamos que el jugador tenga menos de 5 tesoros
        // (el jugador puede tener 5 tesoros visibles como máximo:
        // 1 armor, 1 shoes, 2 onehand, 1 head)
        if (visibleTreasures.size() < 5)
        {
            TreasureKind type = T.getType();
            
            // En caso de ser un tesoro de una mano...
            if (type == TreasureKind.ONEHAND)
            {
                // En el caso de que no se tenga un tesoro visible de dos manos...
                if (!HaveTreasureOf(visibleTreasures,TreasureKind.BOTHHANDS))
                {
                    // Comprobamos que no se tengan dos tesoros visibles de 1 mano,
                    // es decir, que se tenga una mano libre

                    int num_tesoros_one_hand = 0;
                    for (Treasure t:visibleTreasures)
                    {
                        if (t.getType() == TreasureKind.ONEHAND)
                            num_tesoros_one_hand++;
                    }
                    
                    resultado = num_tesoros_one_hand < 2;
                    
                }
            }
            // En el caso de que sea un tesoro de dos manos, comprobamos que no se tenga ninguno
            // de una mano o dos.
            else if (type == TreasureKind.BOTHHANDS) 
                resultado = !HaveTreasureOf(visibleTreasures, TreasureKind.ONEHAND) &&
                        !HaveTreasureOf(visibleTreasures, TreasureKind.BOTHHANDS);
            
            // En cualquier otro caso, comprobamos que no se tenga un tesoro visible
            // del tipo escogido
            else          
               resultado = !HaveTreasureOf(visibleTreasures,type);
        }
        
        return resultado;
    }
    
    /** 
     * @brief Comprueba si el jugador tiene un tesoro del tipo especificado
     * @param treasures Vector de tesoros a comprobar
     * @param type tipo a buscar
     * @return true si se tiene el tesoro del tipo especificado, false en caso contrario
     */
    private boolean HaveTreasureOf(ArrayList<Treasure> treasures, TreasureKind type)
    {
        boolean resultado = false;
        boolean sigue = true;
        int i = 0;
        
        while (i < treasures.size() && sigue)
        {
            if (treasures.get(i).getType() == type)
            {
                resultado = true;
                sigue = false;
            }
            i++;
        }
        
        return resultado;
    }
    
    private int howManyVisibleTreasures(TreasureKind tKind)
    {
        int contador = 0;
        for (Treasure t : visibleTreasures)
            if (t.getType() == tKind) 
                contador++;
        
        return contador;
    }
    
    private void dieIfNoTreasures()
    {
        if (hiddenTreasures.isEmpty() && visibleTreasures.isEmpty())
            dead = true;
    }
    
    public boolean isDead(){return dead;}
    public ArrayList<Treasure> getHiddenTreasures(){return hiddenTreasures;}
    public ArrayList<Treasure> getVisibleTreasures(){return visibleTreasures;}
    
    /**
     * @brief Realiza un combate entre el jugador y el monstruo m
     * @param m Monstruo a combatir
     * @return Resultado del combate con el monstruo
     */
    public CombatResult combat(Monster m)
    {
        int myLevel = getCombatLevel();
        int monsterLevel = getOponentLevel(m);
        CombatResult combatResult;
        
        if (myLevel > monsterLevel)
        {
            applyPrize(m);
        
            if (this.level >= MAXLEVEL)
                combatResult = CombatResult.WINGAME;
            else
                combatResult = CombatResult.WIN;
        }
        else
        {
            applyBadConsequence(m);
            combatResult = CombatResult.LOSE;
            
            if (shouldConvert())
                combatResult = CombatResult.LOSEANDCONVERT;
            
        }

        return combatResult;
    }
    /**
     * @brief Pasa un tesoro a visible según las reglas del juego
     * @param t Tesoro a pasar a visible
     */
    public void makeTreasureVisible(Treasure t)
    {
        boolean canI = canMakeTreasureVisible(t);
        
        if (canI)
        {
            visibleTreasures.add(t);
            hiddenTreasures.remove(t);
        } 
    }
    
    /**
     * @brief Elimina un tesoro visible del conjunto de tesoros visibles del jugador
     * @param t Tesoro a eliminar
     */
    public void discardVisibleTreasure(Treasure t)
    {
        visibleTreasures.remove(t);
        
        if (pendingBadConsequence != null && !pendingBadConsequence.isEmpty())
            pendingBadConsequence.substractVisibleTreasure(t);
        
        this.dieIfNoTreasures();
    }
    
    /**
     * @brief Elimina un tesoro oculto del conjunto de tesoros ocultos del jugador
     * @param t Tesoro a eliminar
     */
    public void discardHiddenTreasure(Treasure t)
    {
        hiddenTreasures.remove(t);
        
        if (pendingBadConsequence != null && !pendingBadConsequence.isEmpty())
            pendingBadConsequence.substractHiddenTreasure(t);
        
        this.dieIfNoTreasures();
    }
    
    /**
     * @return true cuando el jugador no tiene ningún mal rollo que cumplir y no tiene
        más de 4 tesoros ocultos, y false en caso contrario. 
     */
    public boolean validState()
    {
        return (pendingBadConsequence.isEmpty() && hiddenTreasures.size() <=4);
    }
    
    /**
     * @brief Cuando un jugador está en su primer turno o se ha quedado sin tesoros, hay que
        proporcionarle nuevos tesoros para que pueda seguir jugando. El número de tesoros que se
        les proporciona viene dado por el valor que saque al tirar el dado:
        Si (dado == 1) roba un tesoro.
        Si (1 < dado < 6) roba dos tesoros.
        Si (dado == 6) roba tres tesoros.
     */
    public void initTreasures()
    {
        CardDealer dealer = CardDealer.getInstance();
        Dice dice = Dice.getInstance();
        
        bringToLife();
        
        Treasure treasure = dealer.nextTreasure();
        hiddenTreasures.add(treasure);
 
        int number = dice.nextNumber("Turno de " + name + ". Tira el dado para saber cuantos tesoros obtienes.",
                "1: un tesoro.   2,3,4,5: 2 tesoros.   6: 3 tesoros.");
        if (number > 1)
        {
            treasure = dealer.nextTreasure();
            hiddenTreasures.add(treasure);
        }
        
        if (number == 6)
        {
            treasure = dealer.nextTreasure();
            hiddenTreasures.add(treasure);
        }
    }
    
    /**
     * @brief Devuelve los niveles del jugador
     * @return Niveles del jugador
     */
    public int getLevels()
    {
        return level;
    }
    
    /**
     * @brief Cuando el jugador decide robar un tesoro a su enemigo, este método comprueba que
        puede hacerlo (sólo se puede robar un tesoro durante la partida) y que su enemigo tiene
        tesoros para ser robados (canYouGiveMeATreasure()), en el caso que así sea éste le
        proporciona un tesoro que se almacenará con sus ocultos. El jugador no puede volver a
        robar otro tesoro durante la partida. En el caso que no se haya podido robar el tesoro por
        algún motivo se devuelve null.
     * @return Tesoro robado
     */
    public Treasure stealTreasure()
    {
        boolean canI = canISteal();
        Treasure treasure = null;
        
        if (canI)
        {
            boolean canYou = enemy.canYouGiveMeATreasure();
            
            if (canYou)
            {
                treasure = enemy.giveMeATreasure();
                hiddenTreasures.add(treasure);
                haveStolen();
            }
        }
        
        return treasure;
    }
    /**
     * @brief Asigna valor al atributo que referencia al enemigo del jugador.
     * @param enemy enemigo del jugador
     */
    public void setEnemy(Player enemy){this.enemy = enemy;}
    
    /**
     * @brief Devuelve un tesoro elegido al azar de entre los tesoros ocultos del jugador
     * @return Tesoro oculto aleatorio del jugador
     */
    protected Treasure giveMeATreasure()
    {
        int aleatorio = new Random().nextInt(hiddenTreasures.size());
        Treasure tesoro = hiddenTreasures.get(aleatorio);
        Treasure nuevo_tesoro = new Treasure(tesoro.getName(),tesoro.getBonus(),tesoro.getType());
        hiddenTreasures.remove(tesoro);
        
        return (nuevo_tesoro);
    }
    
    /**
     * @brief Nos dice si el jugador puede robar o no en caso de que haya
     * robado ya alguna vez
     * @return true si puede robar, false si no puede
     */
    public boolean canISteal()
    {
        return canISteal;
    }
    
    protected boolean canYouGiveMeATreasure()
    {
        return (!hiddenTreasures.isEmpty());
    }
    
    private void haveStolen()
    {
        canISteal = false;
    }
    
    /**
     * @brief El jugador se descarta de todos sus tesoros ocultos y visibles. Para cada tesoro que se
        descarta se hace uso de la operación discardVisibleTreasure(t:Treasure) o
        discardHiddenTreasure(t:Treasure) según corresponda, de esa forma se verifica si se cumple
        con algún mal rollo pendiente.
     */
    public void discardAllTreasures()
    {
        ArrayList<Treasure> visibleTreasures_copia = new ArrayList(visibleTreasures);
        ArrayList<Treasure> hiddenTreasures_copia = new ArrayList(hiddenTreasures);
        
        for(Treasure t:visibleTreasures_copia)
            discardVisibleTreasure(t);
        
        for(Treasure t:hiddenTreasures_copia)
            discardHiddenTreasure(t);
    }
    
    /**
     * @brief Devuelve el nivel del combate normal del monstruo
     * @param m Monstruo a pedir el nivel
     * @return Nivel del monstruo contra jugador normal
     */
    protected int getOponentLevel(Monster m)
    {
        return m.getCombatLevel();
    }
    
    /**
     * @brief ¿Puede convertirse el jugador en cultist? Esto se hace
     * si el jugador pierde un combate, tira un dado y le sale un 1.
     * @return true si se puede convertir, false si no
     */
    protected boolean shouldConvert()
    {
        boolean resultado = false;
        
        int num = Dice.getInstance().nextNumber("Pierdes el combate. Tira el dado para saber si te","conviertes en jugador sectario (obteniendo un 1)");
        if (num == 1)
            resultado = true;
        
        return resultado;
    }
    
    @Override
    public String toString(){
        return "\nname = " + name +
        "\nlevel = " + level +
        "\nCombatLevel= " + Integer.toString(getCombatLevel()) +
        "\ndead = "+ dead +
        "\ncanISteal = "+ canISteal +
        "\npendingBC = " + pendingBadConsequence;// +
        //"\nenemy = "+ enemy;
    }
}