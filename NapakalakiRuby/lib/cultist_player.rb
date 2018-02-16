# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative 'player'
require_relative 'cultist'

module NapakalakiGame
  class CultistPlayer < Player
    @@totalCultistPlayers = 0
    
    def initialize(p,c)
      super(p.name)
      copyAll(p)
      @myCultistCard = c
      @@totalCultistPlayers += 1
    end
    
    def copyAll(p)
      @dead = p.dead
      @name = p.name
      @level = p.level
      @hiddenTreasures = p.hiddenTreasures
      @visibleTreasures = p.visibleTreasures
      @pendingBadConsequence = p.pendingBadConsequence
      @enemy = p.enemy
      @canISteal = p.canISteal
    end
    
    def self.getTotalCultistPlayers
      @@totalCultistPlayers
    end
    
    def getCombatLevel
      nivel_superior = super
      nivel_carta = @myCultistCard.getGainedLevels
      
      return (nivel_superior + 0.2*nivel_superior + nivel_carta*@@totalCultistPlayers).round
    end
    
    def canYouGiveMeATreasure
      return (!getVisibleTreasures.empty?);
    end
    
    def giveMeATreasure
      aleatorio = rand(@visibleTreasures.size)
      tesoro = @visibleTreasures.at(aleatorio)
      nuevo_tesoro = Treasure.new(tesoro.name, tesoro.bonus, tesoro.type)
      @visibleTreasures.delete(tesoro)
      
      return nuevo_tesoro
    end
    
    def shouldConvert
      return false
    end
    
    def getOponentLevel(m)
      return m.getCombatLevelAgainstCultistPlayer
    end
    
    def to_s
      return  "name: #{@name}
      level: #{@level}
      combatLevel: #{getCombatLevel}
      dead: #{@dead}
      canISteal: #{@canISteal}
      pendingBC #{@pendingBadConsequence}
      Soy un jugador cultist.
      Carta: #{@myCultistCard}"
    end
  end
end