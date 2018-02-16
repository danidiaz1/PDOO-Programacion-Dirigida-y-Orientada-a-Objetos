/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NapakalakiGame;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Daniel Díaz Pareja
 */
public class Napakalaki {
    private static final Napakalaki instance = new Napakalaki();
    private Player currentPlayer;
    private ArrayList<Player> players = new ArrayList();
    private CardDealer dealer = CardDealer.getInstance();
    private Monster currentMonster;
    
    
    // El constructor privado asegura que no se puede instanciar
    // desde otras clases
    private Napakalaki() 
    {
        
    }
    
    public static Napakalaki getInstance() 
    {
        return instance;
    }
    
    private void initPlayers(ArrayList<String> names)
    {
        for (String s:names)
            players.add(new Player(s));
    }
    
    private Player nextPlayer(){
        
        int siguiente_indice;
        
        int num_jugadores = players.size();
        
        // Primera jugada
        if (currentPlayer == null)
        {
            Random random = new Random();
            siguiente_indice = random.nextInt(num_jugadores);
        }
        else
        {
            int indice_jugador_actual = players.indexOf(currentPlayer);
            
            if (indice_jugador_actual == num_jugadores - 1)
                siguiente_indice = 0;
            else
                siguiente_indice = indice_jugador_actual + 1;
        }
        
        Player siguiente_jugador = players.get(siguiente_indice);
        
        return siguiente_jugador;
    }
    /**
     * @brief Método que comprueba si el jugador activo (currentPlayer) cumple con las reglas del juego
        para poder terminar su turno. Devuelve false si el jugador activo no puede pasar de turno y
        true en caso contrario, para ello usa el método de Player validState() donde se realizan las
        comprobaciones pertinentes.
        Si currentplayer es null, devuelve true.
     * @return 
     */
    private boolean nextTurnAllowed()
    {
        boolean permitido = true;
        
        if (currentPlayer != null)
            permitido = currentPlayer.validState();
        
        return permitido;
    }
    /**
     * @brief Se asigna un enemigo a cada jugador. Esta asignación se hace de forma aleatoria teniendo
        en cuenta que un jugador no puede ser enemigo de sí mismo.
     */
    private void setEnemies()
    {
        Random random = new Random();
        for (Player p:players)
        {
            int indice_enemigo = random.nextInt(players.size());

            // Un jugador no puede ser enemigo de si mismo
            while (indice_enemigo == players.indexOf(p))
                indice_enemigo = random.nextInt(players.size());
            
            p.setEnemy(players.get(indice_enemigo));
        }
    }
    /**
     * @brief Operación responsabilidad de la única instancia de Napakalaki, la cual pasa el control al
        jugador actual (currentPlayer) para que lleve a cabo el combate con el monstruo que le ha
        tocado (currentMonster). El método de la clase Player con esa responsabilidad es
        combat(currentMonster:Monster): CombatResult, cuyo comportamiento general (también
        reflejado en el diagrama y responsabilidad de Player) es: si el nivel de combate del jugador
        supera al del monstruo, se aplica el buen rollo y se puede ganar el combate o el juego, en
        otro caso, el jugador pierde el combate y se aplica el mal rollo correspondiente.
     * @return
     * @todo El nuevo jugador cultist también tiene que actualizarse como enemigo
     *      de los jugadores que corresponda
     */
    public CombatResult developCombat()
    {
        Monster m = currentMonster;
        CombatResult combatResult = currentPlayer.combat(m);
        dealer.giveMonsterBack(m);
        
        if (combatResult == CombatResult.LOSEANDCONVERT)
        {
            Cultist c = dealer.nextCultist();
            // Nuevo jugador cultist
            Player newCultistPlayer = new CultistPlayer(currentPlayer,c);
            
            // Actualizamos el nuevo jugador a los enemigos de los demás
            for (Player p: players)
                if (p.getEnemy() == currentPlayer)
                    p.setEnemy(newCultistPlayer);
            
            // Indice donde insertar el nuevo jugador
            int indiceCurrentPlayer = players.indexOf(currentPlayer);
            
            // Insertamos el nuevo jugador en el indice
            players.set(indiceCurrentPlayer, newCultistPlayer);

            // El jugador actual también se actualiza al nuevo
            currentPlayer = newCultistPlayer;
        }
        
        return combatResult;
    }
    
    /** 
     * @brief Operación encargada de eliminar los tesoros visibles indicados de la lista de tesoros
        visibles del jugador. Al eliminar esos tesoros, si el jugador tiene un mal rollo pendiente, se
        indica a éste dicho descarte para su posible actualización. Finalmente, se invoca a
        dieIfNoTreasure() para comprobar si se ha quedado sin tesoros y en ese caso pasar a
        estado de muerto. Los tesoros descartados se devuelven al CardDealer.
     * @param treasures Tesoros visibles a eliminar
     */
    public void discardVisibleTreasures(ArrayList<Treasure> treasures)
    {
        for (Treasure t:treasures)
        {
            currentPlayer.discardVisibleTreasure(t);
            dealer.giveTreasureBack(t);
        }
    }
    
    /**
     * @brief Operacion similar a la anterior
     * @param treasures Tesoros ocultos a eliminar
     */
    public void discardHiddenTreasures(ArrayList<Treasure> treasures)
    {
        for (Treasure t:treasures)
        {
            currentPlayer.discardHiddenTreasure(t);
            dealer.giveTreasureBack(t);
        }
    }
    
    /**
     * @brief Operación en la que se pide al jugador actual que pase tesoros ocultos a visibles, siempre
        que pueda hacerlo según las reglas del juego, para ello desde Player se ejecuta el método:
        canMakeTreasureVisible(treasures:Treasure ):boolean
     * @param treasures Tesoros a pasar a visibles
     */
    public void makeTreasuresVisible(ArrayList<Treasure> treasures)
    {
        for (Treasure t:treasures)
            currentPlayer.makeTreasureVisible(t);
    }
    
    /**
     * @brief Se encarga de solicitar a CardDealer la inicialización de los mazos de cartas de Tesoros y
        de Monstruos, de inicializar los jugadores proporcionándoles un nombre, asignarle a cada
        jugador su enemigo y de iniciar el juego con la llamada a nextTurn() para pasar al siguiente
        turno (que en este caso será el primero).
     * @param names Jugadores
     */
    public void initGame(ArrayList<String> names)
    {
        initPlayers(names);
        setEnemies();
        dealer.initCards();
        nextTurn();
    }
    
    public Player getCurrentPlayer(){
        return currentPlayer;
    }
    
    public Monster getCurrentMonster()
    {
        return currentMonster;
    }
    /**
     * @brief Esta operación usa el método nextTurnAllowed(), donde se verifica si el jugador activo
        (currentPlayer) cumple con las reglas del juego para poder terminar su turno.
        En caso el caso que nextTurnAllowed() devuelva true, se le solicita a CardDealer el
        siguiente monstruo al que se enfrentará ese jugador (currentMonster) y se actualiza el
        jugador activo al siguiente jugador.
        * 
        En caso de que el nuevo jugador activo esté muerto, por el combate en su anterior turno o
        porque es el primer turno, se inicializan sus tesoros siguiendo las reglas del juego. La
        inicialización de los tesoros se encuentra recogida en el diagrama subordinado
        initTreasures.
     * @return Si se ha conseguido pasar de turno
     */
    public boolean nextTurn()
    { 
        boolean stateOK = this.nextTurnAllowed();
       
        if (stateOK)
        {
            currentMonster = dealer.nextMonster();
            currentPlayer = this.nextPlayer();
            currentPlayer.getPendingBadConsequence().setText("");
            boolean dead = currentPlayer.isDead();
            boolean no_tiene_tesoros = currentPlayer.getHiddenTreasures().isEmpty() &&
                    currentPlayer.getVisibleTreasures().isEmpty();
            
            if (dead || no_tiene_tesoros)
                currentPlayer.initTreasures();    
        }
            
        return stateOK;
    }
    
    /**
     * @brief Comprueba si el juego ha terminado
     * @param result Resultado a comparar
     * @return true si el parámetro result es WINGAME, false en caso contrario
     */
    public boolean endOfGame(CombatResult result)
    {
        return (result == CombatResult.WINGAME);
    }
    
    
}
