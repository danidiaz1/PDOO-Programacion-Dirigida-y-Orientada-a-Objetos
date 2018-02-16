# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative 'bad_consequence'

module NapakalakiGame
  class NumericBadConsequence < BadConsequence
    attr_reader :nVisibleTreasures, :nHiddenTreasures
    def initialize(text, levels, someVisibleTreasures, someHiddenTreasures)
      super(text,levels)
      @nVisibleTreasures=someVisibleTreasures
      @nHiddenTreasures=someHiddenTreasures
    end
    public_class_method :new

    def isEmpty
      return (@nHiddenTreasures == 0 && @nVisibleTreasures == 0)
    end

    def adjustToFitTreasureLists(v,h)

        new_bc = self # Si no hay que hacer nada, la nueva bc es ella misma

        if (@nVisibleTreasures > 0 || @nHiddenTreasures > 0)

          new_nVisibleTreasures = 0
          new_nHiddenTreasures = 0

          if (v.size < @nVisibleTreasures)
            new_nVisibleTreasures = v.size
          end

          if (h.size < @nHiddenTreasures)
            new_nHiddenTreasures = h.size
          end

          new_bc = NumericBadConsequence.new(@text,@levels,
                  new_nVisibleTreasures, new_nHiddenTreasures)
        end

        return new_bc

      end


      def substractVisibleTreasure(t)
        if (@nVisibleTreasures > 0)
            @nVisibleTreasures = @nVisibleTreasures - 1
        end
      end


      def substractHiddenTreasure(t)
        if (@nHiddenTreasures > 0)
            @nHiddenTreasures = @nHiddenTreasures - 1
        end
      end

      def to_s
        return "text: #{@text} 
        levels: #{@levels}
        nVisibleTreasures: #{@nVisibleTreasures}
        nHiddenTreasures: #{@nHiddenTreasures}
        "
      end
  end
end