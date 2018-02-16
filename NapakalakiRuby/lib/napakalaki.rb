# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require "singleton"
require_relative "player"
require_relative "combat_result"
require_relative "card_dealer"
require_relative "cultist_player"

module NapakalakiGame
  class Napakalaki
    include Singleton
    attr_reader :currentPlayer, :currentMonster
    def initialize
      @currentMonster = nil
      @currentPlayer = nil
      @dealer = CardDealer.instance
      @players = Array.new
    end
    
    def getCurrentPlayer
      return @currentPlayer
    end
    
    def getCurrentMonster
      return @currentMonster
    end
    
    def initPlayers(names)
      names.each { |i|
        @players << Player.new(i)
      }
    end
     
    def nextPlayer
    
      siguiente_indice = 0
      num_jugadores = @players.size
      
      #Primera jugada
      if (@currentPlayer.nil?)
        siguiente_indice = rand(num_jugadores)
      else
        indice_jugador_actual = @players.index(@currentPlayer)
        
        if (indice_jugador_actual == (num_jugadores-1) )
            siguiente_indice = 0
        else
          siguiente_indice = indice_jugador_actual + 1
        end
      end

      siguiente_jugador = @players.at(siguiente_indice)
      
      return siguiente_jugador
    end

    def nextTurnIsAllowed
      permitido = true
      
      if (@currentPlayer != nil)
        permitido = @currentPlayer.validState()
      end
      
      return permitido
    end

    def setEnemies
      @players.each { |p|  
        
        indice_enemigo = rand(@players.size)
        
        while (indice_enemigo == @players.index(p))
          indice_enemigo = rand(@players.size)
        end
        
        enemigo = @players.at(indice_enemigo)
        p.enemy = enemigo
      }
    end
    
=begin
    @brief Operación responsabilidad de la única instancia de Napakalaki, la cual pasa el control al
        jugador actual (currentPlayer) para que lleve a cabo el combate con el monstruo que le ha
        tocado (currentMonster). El método de la clase Player con esa responsabilidad es
        combat(currentMonster:Monster): CombatResult, cuyo comportamiento general (también
        reflejado en el diagrama y responsabilidad de Player) es: si el nivel de combate del jugador
        supera al del monstruo, se aplica el buen rollo y se puede ganar el combate o el juego, en
        otro caso, el jugador pierde el combate y se aplica el mal rollo correspondiente.
    @return 
=end
    def developCombat
      m = @currentMonster
      combatResult = @currentPlayer.combat(m)
      @dealer.giveMonsterBack(m)
      
      if (combatResult == CombatResult::LOSEANDCONVERT)
        c = @dealer.nextCultist
        
        newCultistPlayer = CultistPlayer.new(@currentPlayer, c)
        
        @players.each { |p|  
          if (p.enemy == @currentPlayer)
            p.enemy = newCultistPlayer
          end
        }
        
        indiceCurrentPlayer = @players.index(@currentPlayer)
        
        @players.insert(indiceCurrentPlayer, newCultistPlayer)
        
        @currentPlayer = newCultistPlayer
      end
      
      return combatResult
    end
    
=begin
      @brief Operación encargada de eliminar los tesoros visibles indicados de la lista de tesoros
        visibles del jugador. Al eliminar esos tesoros, si el jugador tiene un mal rollo pendiente, se
        indica a éste dicho descarte para su posible actualización. Finalmente, se invoca a
        dieIfNoTreasure() para comprobar si se ha quedado sin tesoros y en ese caso pasar a
        estado de muerto. Los tesoros descartados se devuelven al CardDealer.
      @param treasures Tesoros visibles a eliminar
=end
    def discardVisibleTreasures(treasures)
      treasures.each { |t|  
          @currentPlayer.discardVisibleTreasure(t)
          @dealer.giveTreasureBack(t)
      }
    end

=begin
    @brief Operación similar a la anterior
    @param treasures Tesoros ocultos a eliminar
=end
    def discardHiddenTreasures(treasures)
      treasures.each { |t|  
          @currentPlayer.discardHiddenTreasure(t)
          @dealer.giveTreasureBack(t)
      }
    end

=begin
    @brief Operación en la que se pide al jugador actual que pase tesoros ocultos a visibles, siempre
      que pueda hacerlo según las reglas del juego, para ello desde Player se ejecuta el método:
      canMakeTreasureVisible(treasures:Treasure ):boolean
    @param treasures Tesoros a pasar a visibles
=end
    def makeTreasuresVisible(treasures)
      treasures.each { |t|
        @currentPlayer.makeTreasureVisible(t)
      }
    end
    
=begin
    @brief Se encarga de solicitar a CardDealer la inicialización de los mazos de cartas de Tesoros y
        de Monstruos, de inicializar los jugadores proporcionándoles un nombre, asignarle a cada
        jugador su enemigo y de iniciar el juego con la llamada a nextTurn() para pasar al siguiente
        turno (que en este caso será el primero).
    @param names Jugadores
=end
    def initGame(names)
      initPlayers(names)
      setEnemies
      @dealer.initCards
      nextTurn
      
    end

=begin
    @brief Esta operación usa el método nextTurnAllowed(), donde se verifica si el jugador activo
        (currentPlayer) cumple con las reglas del juego para poder terminar su turno.
        En caso el caso que nextTurnIsAllowed() devuelva true, se le solicita a CardDealer el
        siguiente monstruo al que se enfrentará ese jugador (currentMonster) y se actualiza el
        jugador activo al siguiente jugador.

        En caso de que el nuevo jugador activo esté muerto, por el combate en su anterior turno o
        porque es el primer turno, se inicializan sus tesoros siguiendo las reglas del juego. La
        inicialización de los tesoros se encuentra recogida en el diagrama subordinado
        initTreasures.
    @return Si se ha conseguido pasar de turno
     
=end
    def nextTurn
      stateOK = nextTurnIsAllowed
      
      if (stateOK)
        @currentMonster = @dealer.nextMonster
        @currentPlayer = nextPlayer
        dead = @currentPlayer.dead
        no_tiene_tesoros = @currentPlayer.getHiddenTreasures.empty? &&
          @currentPlayer.getVisibleTreasures.empty?
        
       if (dead || no_tiene_tesoros)
         @currentPlayer.initTreasures
       end
      end
      
      return stateOK
    end

    def endOfGame(result)
        return (result == WINGAME)
    end
  end
end
