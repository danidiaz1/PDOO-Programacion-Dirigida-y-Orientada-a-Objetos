# encoding: utf-8

# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
module NapakalakiGame
  class Treasure
    attr_reader :name, :bonus, :type
    def initialize(n,b,t)
      @name=n
      @bonus=b
      @type=t
    end
    
    def to_s
      "name: #{@name}
       bonus: #{@bonus}
       type: #{@type}
      "
    end
  end
end
