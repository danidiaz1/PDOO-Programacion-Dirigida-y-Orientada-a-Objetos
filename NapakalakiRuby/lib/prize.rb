# encoding: utf-8

module NapakalakiGame
  class Prize

    # Crean las variables de instancia con valor nil
    attr_reader :treasures, :level

    # El inicializador se invoca desde el constructor de ruby (new)
    def initialize(t,l)
      # Nos referimos a variables de instancia con @ (si no son locales)
      @treasures=t
      @level=l
    end

    def to_s
      "Tesoros ganados: #{@treasures} Niveles ganados: #{@level}"
    end
  end
end