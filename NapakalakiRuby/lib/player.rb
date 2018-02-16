#encoding: utf-8

# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'combat_result'
require_relative 'card_dealer'
require_relative 'player'
require_relative 'dice'
require_relative 'bad_consequence'
require_relative 'numeric_bad_consequence'
require_relative 'treasure_kind'

module NapakalakiGame
  class Player
    
    attr_reader :dead, :name, :level, :hiddenTreasures, :visibleTreasures, :enemy, :canISteal, :pendingBadConsequence
    attr_writer :pendingBadConsequence, :enemy

    @@Maxlevel = 10
    def initialize(name)
      @dead = true
      @name = name
      @level = 1
      @hiddenTreasures = Array.new
      @visibleTreasures = Array.new
      @pendingBadConsequence = NumericBadConsequence.new("Todavia sin bc", 0, 0, 0) 
      @enemy = nil
      @canISteal = true
    end
    
    def getVisibleTreasures
      return @visibleTreasures
    end
    
    def getHiddenTreasures
      return @hiddenTreasures
    end
    
    def to_s
      return "name: #{@name}
      level: #{@level}
      combatLevel: #{getCombatLevel}
      dead: #{@dead}
      canISteal: #{@canISteal}
      pendingBC #{@pendingBadConsequence}
      "
    end
    
    def self.Maxlevel    #Definición del consultor de la variable
      @@Maxlevel
    end
    
    def bringToLife
      @dead = false
    end
    
    def getCombatLevel
      lvl = @level
      @visibleTreasures.each { |t|
        lvl += t.bonus
      }

      return lvl
    end
    
    def incrementLevels(l)
      @level = @level + l
      
      if @level > 10 then
        @level = 10
      end
    end
    
    def decrementLevels(l)
      @level = @level - l
      
      if @level < 1 then
        @level = 1
      end
    end
    
=begin
    @brief Esta operación es la encargada de aplicar el buen rollo del monstruo vencido al jugador,
      sumando los niveles correspondientes y pidiendo al CardDealer que le dé el número de
      tesoros indicado en el buen rollo del monstruo. Esos tesoros se añaden a sus tesoros
      ocultos.
    @param m Monstruo del que se aplica el premio
=end
    def applyPrize(m)
      nLevels = m.getLevelsGained
      incrementLevels(nLevels)
      
      nTreasures = m.getTreasuresGained
      
      if (nTreasures > 0)
        dealer = CardDealer.instance
        
        for i in 1..nTreasures
          treasure = dealer.nextTreasure
          @hiddenTreasures << treasure
        end
      end
    end
    
=begin
    @brief Cuando el jugador ha perdido el combate, hay que considerar el mal rollo que le impone el
      monstruo con el que combatió. Para ello, decrementa sus niveles según indica el mal rollo y
      guarda una copia de un objeto badConsequence ajustada a los tesoros que puede perder.
      Es decir, un objeto mal rollo que refleje el mal rollo indicado por el monstruo pero
      eliminando las condiciones que el jugador no pueda cumplir según los tesoros de que
      disponga (por ejemplo si el mal rollo del monstruo implica perder 2 tesoros visibles y el
      jugador sólo tiene 1, entonces el mal rollo pendiente será de sólo 1 tesoro visible). La
      operación encargada de hacer esto es adjustToFitTreasureList de la clase badConsequence.
      Éste es el mal rollo pendiente (pendingbadConsequence) que el jugador almacenará y que
      deberá cumplir descartándose de esos tesoros antes de que pueda pasar al siguiente turno.
    @param m Monstruo del que aplicar el mal rollo
=end
    def applyBadConsequence(m)
      badConsequence = m.bc
      nLevels = badConsequence.levels
      
      decrementLevels(nLevels)
      
      pendingBad = badConsequence.adjustToFitTreasureLists(@visibleTreasures, @hiddenTreasures)
      @pendingBadConsequence = pendingBad
    end
    
    def canMakeTreasureVisible(t)
      resultado = false
      
      # Comprobamos que el jugador tenga menos de 5 tesoros
      # (el jugador puede tener 5 tesoros visibles como máximo:
      # 1 armor, 1 shoes, 2 onehand, 1 helmet)
      
      if (@visibleTreasures.size < 5)
        type = t.type
        
        # En caso de ser un tesoro de una mano
        if (type == TreasureKind::ONEHAND)
          
          # En caso de no tener un tesoro visible de una mano...
          if (!HaveTreasureOf(@visibleTreasures,TreasureKind::BOTHHANDS))
            
            # Comprobamos que no se tengan dos tesoros visibles de una mano
            
            num_tesoros_one_hand=0
            @visibleTreasures.each { |t|  
              
              if (t.type == TreasureKind::ONEHAND)
                num_tesoros_one_hand = num_tesoros_one_hand + 1
              end
            }
            
            resultado = num_tesoros_one_hand < 2
          end
        ## En el caso de que sea un tesoro de dos manos, comprobamos que no se tenga ninguno
        ## de una mano o de dos
        elsif (type == TreasureKind::BOTHHANDS)
          resultado = !HaveTreasureOf(@visibleTreasures, TreasureKind::ONEHAND) &&
            !HaveTreasureOf(@visibleTreasures, TreasureKind::BOTHHANDS)
            
          # En cualquier otro caso, comprobamos que no se tenga un tesoro visible
          # del tipo escogido
        else 
          resultado = !HaveTreasureOf(@visibleTreasures,type)
        end
      end
      return resultado
    end
    
    ## 
    # @brief Comprueba si el jugador tiene un tesoro del tipo especificado
    # @param treasures Vector de tesoros a comprobar
    # @param type tipo a buscar
    # @return true si se tiene el tesoro del tipo especificado, false en caso contrario
    
    def HaveTreasureOf(treasures,type)
      resultado = false;
      sigue = true;
      i = 0;
      
      while (i < treasures.size && sigue)
        if (treasures.at(i).type == type)
          resultado = true
          sigue = false
        end
        i+=1;
      end
      
      return resultado
    end
    
    def howManyVisibleTreasures(tKind)
      contador = 0
      
      @visibleTreasures.each { |t|
        if t.type == tKind then
          contador+=1
        end
      } 
    end
    
    def dieIfNoTreasures()
      if (@hiddenTreasures.empty? && @visibleTreasures.empty?)
        @dead=true
      end
    end
    
    def shouldConvert()
      resultado = false
      
      num = Dice.instance.next_number
      
      if (num == 1)
        resultado = true
      end
      
      return resultado
    end
    
    public
=begin
     @brief Devuelve el nivel del combate normal del monstruo
     @param m Monstruo a pedir el nivel
     @return Nivel del monstruo contra jugador normal
=end
    def getOponentLevel(m)
      return m.combatLevel
    end
    
=begin
    @brief Realiza un combate entre el jugador y el monstruo m
    @param m Monstruo a combatir
    @return Resultado del combate con el monstruo
=end
    def combat(m)
      myLevel = getCombatLevel
      monsterLevel = getOponentLevel(m)
      combatResult = nil
      
      if (myLevel > monsterLevel)
        applyPrize(m)
        
        if (@level >= @@Maxlevel)
          combatResult = CombatResult::WINGAME
        else
          combatResult = CombatResult::WIN
        end
      else
        applyBadConsequence(m)
        combatResult = CombatResult::LOSE
        
        if (shouldConvert)
          combatResult = CombatResult::LOSEANDCONVERT
        end
      end
      
      return combatResult
    end
    
=begin
    @brief Pasa un tesoro a visible según las reglas del juego
    @param t Tesoro a pasar a visible
=end
    def makeTreasureVisible(t)
      canI = canMakeTreasureVisible(t)
      
      if (canI)
        @visibleTreasures << t
        @hiddenTreasures.delete(t)
      end
    end
    
=begin
    @brief Elimina un tesoro visible del conjunto de tesoros visibles del jugador
    @param t Tesoro a eliminar    
=end
    def discardVisibleTreasure(t)
      @visibleTreasures.delete(t)
        
      if (@pendingBadConsequence != nil && !@pendingBadConsequence.isEmpty)
            @pendingBadConsequence.substractVisibleTreasure(t)
      end
      
      dieIfNoTreasures
    end
    
=begin
    @brief Elimina un tesoro oculto del conjunto de tesoros ocultos del jugador
    @param t Tesoro a eliminar    
=end
    def discardHiddenTreasure(t)
      @hiddenTreasures.delete(t)
        
      if (@pendingBadConsequence != nil && !@pendingBadConsequence.isEmpty)
            @pendingBadConsequence.substractHiddenTreasure(t)
      end
      
      dieIfNoTreasures
    end
    
    def validState
      return (@pendingBadConsequence.isEmpty && @hiddenTreasures.size <=4)
    end
    
=begin
      @brief Cuando un jugador está en su primer turno o se ha quedado sin tesoros, hay que
        proporcionarle nuevos tesoros para que pueda seguir jugando. El número de tesoros que se
        les proporciona viene dado por el valor que saque al tirar el dado:
        Si (dado == 1) roba un tesoro.
        Si (1 < dado < 6) roba dos tesoros.
        Si (dado == 6) roba tres tesoros.
=end
    def initTreasures
      dealer = CardDealer.instance
      dice = Dice.instance
      
      bringToLife
      
      treasure = dealer.nextTreasure
      @hiddenTreasures << treasure
      
      number = dice.next_number
      
      if (number > 1)
        treasure = dealer.nextTreasure
        @hiddenTreasures << treasure
      end
      
      if (number == 6)
        treasure = dealer.nextTreasure
        @hiddenTreasures << treasure
      end
    end
    
=begin
   @brief Cuando el jugador decide robar un tesoro a su enemigo, este método comprueba que
      puede hacerlo (sólo se puede robar un tesoro durante la partida) y que su enemigo tiene
      tesoros ocultos para ser robados (canYouGiveMeATreasure()), en el caso que así sea éste le
      proporciona un tesoro que se almacenará con sus ocultos. El jugador no puede volver a
      robar otro tesoro durante la partida. En el caso que no se haya podido robar el tesoro por
      algún motivo se devuelve null.
    @return Tesoro robado
=end
    def stealTreasure
      canI = canISteal
      treasure = nil
      
      if (canI)
        canYou = @enemy.canYouGiveMeATreasure();
        
        if (canYou)
          treasure = @enemy.giveMeATreasure();
          @hiddenTreasures << treasure;
          haveStolen
        end
      end
      
      return treasure
    end
    
=begin
    @brief El jugador se descarta de todos sus tesoros ocultos y visibles. Para cada tesoro que se
      descarta se hace uso de la operación discardVisibleTreasure(t:Treasure) o
      discardHiddenTreasure(t:Treasure) según corresponda, de esa forma se verifica si se cumple
      con algún mal rollo pendiente.
=end
    def discardAllTreasures
      visibleTreasures_copia = Array.new(@visibleTreasures)
      hiddenTreasures_copia = Array.new(@hiddenTreasures)
      
      visibleTreasures_copia.each { |t| discardVisibleTreasure(t)}
      hiddenTreasures_copia.each { |t| discardHiddenTreasure(t)}
    end

    # @brief Devuelve un tesoro elegido al azar de entre los tesoros ocultos del jugador
    # @return Tesoro oculto aleatorio del jugador
    def giveMeATreasure
      aleatorio = rand(@hiddenTreasures.size)
      tesoro = @hiddenTreasures.at(aleatorio)
      nuevo_tesoro = Treasure.new(tesoro.name, tesoro.bonus, tesoro.type)
      @hiddenTreasures.delete(tesoro)
      
      return nuevo_tesoro
      
      return nuevo_tesoro
    end
    
    def canYouGiveMeATreasure
      return (!@hiddenTreasures.empty?)
    end
    
    def haveStolen
      @canISteal = false;
    end
  end
  
end