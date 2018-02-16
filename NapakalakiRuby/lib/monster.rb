# encoding: utf-8
require_relative 'prize'

module NapakalakiGame
  class Monster

    attr_reader :name, :combatLevel, :prize, :bc, :levelChangeAgainstCultistPlayer
    def initialize(n,c,p,b,l=0)
      @name = n
      @combatLevel = c
      @prize = p
      @bc = b
      @levelChangeAgainstCultistPlayer = l
    end

    def to_s
      "name: #{@name} 
       combatLevel: #{@combatLevel}
       prize: #{@prize}
       bc: #{@bc}
       levelChangeAgainstCultistPlayer: #{@levelChangeAgainstCultistPlayer}"
    end
    
    def getLevelsGained
      return @prize.level
    end
    
    def getTreasuresGained
      return @prize.treasures
    end
    
    def getCombatLevelAgainstCultistPlayer
      return @levelChangeAgainstCultistPlayer+@combatLevel
    end
  end
end
