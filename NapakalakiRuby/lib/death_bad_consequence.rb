# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module NapakalakiGame
  class DeathBadConsequence < NumericBadConsequence
    def initialize(text)
      super(text,Player.Maxlevel,@@Maxtreasures,@@Maxtreasures)
    end

    public_class_method :new
    
  end
end
