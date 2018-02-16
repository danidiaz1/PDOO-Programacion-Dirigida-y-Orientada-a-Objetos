# encoding: utf-8

module NapakalakiGame
  class BadConsequence

    attr_reader :text, :levels
    @@Maxtreasures = 10
    
    def initialize(aText, someLevels = 0)
      @text="Mal rollo: #{aText}"
      @levels=someLevels
    end
    
    private_class_method :new
    
    def self.Maxtreasures   #Definición del consultor de la variable
      @@MAXTREASURES
    end
    
    # Indicamos que new tiene visibilidad privada.
    private_class_method :new

    # @brief Comprueba si el objeto está vacío
    # @return true si el objeto está vacío, false en caso contrario
    def isEmpty()
      raise NotImplementedError.new("Clase abstracta, método no implementado")
    end

=begin
    @brief Actualiza el mal rollo para que el tesoro visible t no forme parte del mismo. Es posible que
          esta actualización no implique cambio alguno, que lleve a eliminar un tipo específico de
          tesoro visible, o a reducir el número de tesoros visibles pendientes.
    @param t Tesoro a eliminar
=end
    def substractVisibleTreasure(t)
      raise NotImplementedError.new("Clase abstracta, método no implementado")
    end

=begin
    @brief Actualiza el mal rollo para que el tesoro oculto t no forme parte del mismo. Es posible que
          esta actualización no implique cambio alguno, que lleve a eliminar un tipo específico de
          tesoro oculto, o a reducir el número de tesoros ocultos pendientes.
    @param t Tesoro a eliminar
=end 
    def substractHiddenTreasure(t)
      raise NotImplementedError.new("Clase abstracta, método no implementado")
    end
=begin
    @brief Recibe como parámetros los tesoros visibles y ocultos de los que dispone el jugador y
      devuelve un nuevo objeto mal rollo que se ajusta a las posibilidades del jugador. Los
      atributos de BadConsequence que debemos tener en cuenta para ajustar el el mal rollo que
      debe cumplir el jugador son nVisibleTreasures, nHiddenTreasures, specificVisibleTreasures y
      specificHiddenTreasures.
    @param v Tesoros visibles del jugador
    @param h Tesoros ocultos del jugador
    @return Nuevo mal rollo que se ajusta a las posibilidades del jugador
=end
    def adjustToFitTreasureLists(v,h)
      raise NotImplementedError.new("Clase abstracta, método no implementado")
    end

    def to_s
      return "text: #{@text} 
      levels: #{@levels}
      "
    end
  end
end
