# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
module NapakalakiGame
  class Cultist
    def initialize(n,l)
      @name = n
      @gainedLevels = l
    end
    
    def getGainedLevels
      return @gainedLevels
    end
    
    def to_s
      "name: #{@name}
      gainedLevels: #{@gainedLevels}
      " 
    end
  end
end