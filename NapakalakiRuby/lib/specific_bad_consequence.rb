# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module NapakalakiGame
  class SpecificBadConsequence < BadConsequence
    attr_reader :specificHiddenTreasures, :specificVisibleTreasures
    def initialize(text, levels, someSpecificVisibleTreasures = Array.new, someSpecificHiddenTreasures = Array.new)
      super(text,levels)
      @specificVisibleTreasures=someSpecificVisibleTreasures
      @specificHiddenTreasures=someSpecificHiddenTreasures
    end

    public_class_method :new

    def isEmpty
      return (@specificVisibleTreasures.empty? &&
            @specificHiddenTreasures.empty?)
    end

    def adjustToFitTreasureLists(v,h)

        new_bc = self # Si no hay que hacer nada, la nueva bc es ella misma

        if (!@specificVisibleTreasures.empty? || !@specificHiddenTreasures.empty?)
          specificVisibleTreasures_copy = @specificVisibleTreasures
          specificHiddenTreasures_copy = @specificHiddenTreasures
          new_specificVisibleTreasures = Array.new
          new_specificHiddenTreasures = Array.new

          if (!@specificVisibleTreasures.empty?)
            v.each { |t|  
              tipo = t.type

              specificVisibleTreasures_copy.each { |tipo2|

                if (tipo == tipo2)
                  new_specificVisibleTreasures << tipo
                  specificVisibleTreasures_copy.delete(tipo)
                  break
                end
              }
            }
          end

          if (!@specificHiddenTreasures.empty?)
            h.each { |t|  
              tipo = t.type

              specificHiddenTreasures_copy.each { |tipo2|

                if (tipo == tipo2)
                  new_specificHiddenTreasures << tipo
                  specificHiddenTreasures_copy.delete(tipo)
                  break
                end
              }
            }
          end

          new_bc = SpecificBadConsequence.new(@text, @levels,
                    new_specificVisibleTreasures,
                    new_specificHiddenTreasures)

        end

        return new_bc

      end

    def substractVisibleTreasure(t)
        if(!@specificVisibleTreasures.empty?)
          @specificVisibleTreasures.delete(t.type)
        end
      end

      def substractHiddenTreasure(t)
        if(!@specificHiddenTreasures.empty?)
          @specificHiddenTreasures.delete(t.type)
        end
      end

      def to_s
        return "text: #{@text} 
        levels: #{@levels}
        specificVisibleTreasures: #{@specificVisibleTreasures} 
        specificHiddenTreasures: #{@specificHiddenTreasures}
        "
      end
  end
end
