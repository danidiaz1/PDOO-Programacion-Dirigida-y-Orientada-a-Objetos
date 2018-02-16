# encoding: utf-8

# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'monster'
require_relative 'treasure'
require_relative 'treasure_kind'
require_relative 'cultist'
require_relative 'numeric_bad_consequence'
require_relative 'specific_bad_consequence'
require_relative 'death_bad_consequence'
require 'singleton'

module NapakalakiGame
  
  class CardDealer
    include Singleton
    
    def initialize
      @unusedMonsters = Array.new
      @usedMonsters = Array.new
      @unusedTreasures = Array.new
      @usedTreasures = Array.new
      @unusedCultists = Array.new
    end
    
    private
    def initTreasureCardDeck
      @unusedTreasures << Treasure.new("¡Sí, mi amo!", 4,TreasureKind::HELMET)
      @unusedTreasures << Treasure.new("Botas de investigación",3,TreasureKind::SHOES)
      @unusedTreasures << Treasure.new("Capucha de Cthulhu",3,TreasureKind::HELMET)
      @unusedTreasures << Treasure.new("A prueba de babas",2,TreasureKind::ARMOR)
      @unusedTreasures << Treasure.new("Botas de lluvia ácida", 1, TreasureKind::BOTHHANDS)
      @unusedTreasures << Treasure.new("Casco minero", 2, TreasureKind::HELMET)
      @unusedTreasures << Treasure.new("Ametralladora Thompson", 4, TreasureKind::BOTHHANDS)
      @unusedTreasures << Treasure.new("Camiseta de la UGR", 1, TreasureKind::ARMOR)
      @unusedTreasures << Treasure.new("Clavo de rail ferroviario", 3, TreasureKind::ONEHAND)
      @unusedTreasures << Treasure.new("Cuchillo de sushi arcano", 2, TreasureKind::ONEHAND)
      @unusedTreasures << Treasure.new("Fez alópodo", 3, TreasureKind::HELMET)
      @unusedTreasures << Treasure.new("Hacha prehistórica", 2, TreasureKind::ONEHAND)
      @unusedTreasures << Treasure.new("El aparato del Pr. Tesla", 4, TreasureKind::ARMOR)
      @unusedTreasures << Treasure.new("Gaita", 4, TreasureKind::BOTHHANDS)
      @unusedTreasures << Treasure.new("Insecticida", 2, TreasureKind::ONEHAND)
      @unusedTreasures << Treasure.new("Escopeta de 3 cañones", 4, TreasureKind::BOTHHANDS)
      @unusedTreasures << Treasure.new("Garabato místico", 2, TreasureKind::ONEHAND)
      @unusedTreasures << Treasure.new("La rebeca metálica", 2, TreasureKind::ARMOR)
      @unusedTreasures << Treasure.new("Lanzallamas", 4, TreasureKind::BOTHHANDS)
      @unusedTreasures << Treasure.new("Necro-comicón", 1,  TreasureKind::ONEHAND)
      @unusedTreasures << Treasure.new("Necronomicón", 5, TreasureKind::BOTHHANDS)
      @unusedTreasures << Treasure.new("Linterna a 2 manos", 3, TreasureKind::BOTHHANDS)
      @unusedTreasures << Treasure.new("Necro-gnomicón", 2, TreasureKind::ONEHAND)
      @unusedTreasures << Treasure.new("Necrotelecom", 2, TreasureKind::HELMET)
      @unusedTreasures << Treasure.new("Mazo de los antiguos", 3, TreasureKind::ONEHAND)
      @unusedTreasures << Treasure.new("Necro-playboycón", 3, TreasureKind::ONEHAND)
      @unusedTreasures << Treasure.new("Porra preternatural", 2, TreasureKind::ONEHAND)
      @unusedTreasures << Treasure.new("Tentáculo de pega", 2, TreasureKind::HELMET)
      @unusedTreasures << Treasure.new("Zapato deja-amigos", 1, TreasureKind::SHOES)
      @unusedTreasures << Treasure.new("Shogulador", 1, TreasureKind::BOTHHANDS)
      @unusedTreasures << Treasure.new("Varita de atizamiento", 3, TreasureKind::ONEHAND)
      
      shuffleTreasures
    end
    
    def initMonsterCardDeck
      # 3 Byakhees de bonanza
      prize = Prize.new(2,1)
      badConsequence = SpecificBadConsequence.new('Pierdes tu armadura
        visible y otra oculta', 0, 
        [TreasureKind::ARMOR], [TreasureKind::ARMOR])
      @unusedMonsters << Monster.new('3 Byakhees de bonanza',8,prize,badConsequence)

      # Chibithulhu
      prize = Prize.new(1,1)
      badConsequence = SpecificBadConsequence.new('Embobados con el lindo
        primigenio te descartas de tu casco visible', 0, 
        [TreasureKind::HELMET], [])
      @unusedMonsters << Monster.new('Chibithulhu',2,prize,badConsequence)

      # El sopor de Dunwich
      prize = Prize.new(1,1)
      badConsequence = SpecificBadConsequence.new('El primordial bostezo
        contagioso. Pierdes el calzado visible', 0, 
        [TreasureKind::SHOES], [])
      @unusedMonsters << Monster.new('El sopor de Dunwich',2,prize,badConsequence)

      # Angeles de la noche ibicenca
      prize = Prize.new(4,1)
      badConsequence = SpecificBadConsequence.new('Te atrapan para llevarte
        de esta y te dejan caer en mitad del vuelo. Descarta 1 mano visible y 1
        mano oculta', 0, [TreasureKind::ONEHAND], [TreasureKind::ONEHAND])
      @unusedMonsters << Monster.new('Angeles de la noche ibicenca',14,prize,badConsequence)

      # El gorron en el umbral
      prize = Prize.new(3,1)
      badConsequence = SpecificBadConsequence.new('Pierdes todos tus 
        tesoros visibles', 0, [TreasureKind::ARMOR,TreasureKind::BOTHHANDS, TreasureKind::ONEHAND,
        TreasureKind::HELMET,TreasureKind::ONEHAND,TreasureKind::SHOES], [])
      @unusedMonsters << Monster.new('El gorron en el umbral',10,prize,badConsequence)

      # H.P. Munchcraft
      prize = Prize.new(2,1)
      badConsequence = SpecificBadConsequence.new('Pierdes la armadura
        visible', 0, [TreasureKind::ARMOR], [])
      @unusedMonsters << Monster.new('H.P. Munchcraft',6,prize,badConsequence)

      # Bichgooth
      prize = Prize.new(1,1)
      badConsequence = SpecificBadConsequence.new('Sientes bichos bajo la
        ropa. Descarta la armadura visible', 0, [TreasureKind::ARMOR], [])
      @unusedMonsters << Monster.new('Bichgooth',2,prize,badConsequence)

      # El rey de rosa
      prize = Prize.new(4,2)
      badConsequence = NumericBadConsequence.new('Pierdes
        5 niveles y 3 tesoros visibles',5, 3, 0)
      @unusedMonsters << Monster.new('El rey de rosa',13,prize,badConsequence)

      # La que redacta en las tinieblas
      prize = Prize.new(1,1)
      badConsequence = NumericBadConsequence.new('Toses los
        pulmones y pierdes 2 niveles',2,0,0)
      @unusedMonsters << Monster.new('La que redacta en las tinieblas',2,prize,badConsequence)

      # Los hondos
      prize = Prize.new(2,1)
      badConsequence = DeathBadConsequence.new('Estos monstruos resultan
        bastante superciales y te aburren mortalmente. Estas muerto')
      @unusedMonsters << Monster.new('Los hondos',8,prize,badConsequence)

      # Semillas Cthulhu
      prize = Prize.new(2,1)
      badConsequence = NumericBadConsequence.new('Pierdes 2 niveles y 2
        tesoros ocultos.',2,0,2)
      @unusedMonsters << Monster.new('Semillas Cthulhu',4,prize,badConsequence)

      # Dameargo
      prize = Prize.new(2,1)
      badConsequence = SpecificBadConsequence.new('Te intentas escaquear.
        Pierdes una mano visible.',0,[TreasureKind::ONEHAND],[])
      @unusedMonsters << Monster.new('Dameargo',1,prize,badConsequence)

      # Pollipolipo volante
      prize = Prize.new(1,1)
      badConsequence = NumericBadConsequence.new('Da mucho asquito.
      Pierdes 3 niveles.',3,0,0)
      @unusedMonsters << Monster.new('Pollipolipo volante',3,prize,badConsequence)

      # Yskhtihyssg-Goth
      prize = Prize.new(3,1)
      badConsequence = DeathBadConsequence.new('No le hace gracia que
        pronuncien mal su nombre. Estas muerto')
      @unusedMonsters << Monster.new('Yskhtihyssg-Goth',12,prize,badConsequence)

      # Familia feliz
      prize = Prize.new(4,1)
      badConsequence = DeathBadConsequence.new('La familia te atrapa.
        Estas muerto.')
      @unusedMonsters << Monster.new('Familia feliz',1,prize,badConsequence)

      # Roboggoth
      prize = Prize.new(2,1)
      badConsequence = SpecificBadConsequence.new('La quinta directiva
        primaria te obliga a perder 2 niveles y un tesoro 2 manos visible',2,
        [TreasureKind::BOTHHANDS],[])
      @unusedMonsters << Monster.new('Roboggoth',8,prize,badConsequence)

      # El espia
      prize = Prize.new(1,1)
      badConsequence = SpecificBadConsequence.new('Te asusta en la noche.
        Pierdes un casco visible.', 0, [TreasureKind::HELMET] ,[])
      @unusedMonsters << Monster.new('El espia',5,prize,badConsequence)

      # El Lenguas
      prize = Prize.new(1,1)
      badConsequence = NumericBadConsequence.new('Menudo susto te llevas.
        Pierdes 2 niveles y 5 tesoros visibles.',2,5,0)
      @unusedMonsters << Monster.new('El Lenguas',20,prize,badConsequence)

      # Bicefalo
      prize = Prize.new(1,1)
      badConsequence = SpecificBadConsequence.new('Te faltan manos para
        tanta cabeza. Pierdes 3 niveles y tus tesoros visibles de las manos.', 3, 
        [TreasureKind::BOTHHANDS], [])
      @unusedMonsters << Monster.new('Bicefalo',20,prize,badConsequence)
      
      ###############################################################
      # Cartas con sectarios
      
      # El mal indecible impronunciable
      prize = Prize.new(3,1)
      badConsequence = SpecificBadConsequence.new('Pierdes 1 mano visible', 0, 
        [TreasureKind::ONEHAND], [])
      @unusedMonsters << Monster.new('El mal indecible impronunciable',10,prize,badConsequence, -2)
      
      # Testigos Oculares
      prize = Prize.new(2,1)
      badConsequence = SpecificBadConsequence.new('Pierdes tus tesoros visibles. Jajaja.', 0, 
        [TreasureKind::ARMOR,TreasureKind::BOTHHANDS, TreasureKind::ONEHAND,
        TreasureKind::HELMET,TreasureKind::ONEHAND,TreasureKind::SHOES], [])
      @unusedMonsters << Monster.new('Testigos oculares',6,prize,badConsequence, 2)
      
      # El gran cthulhu
      prize = Prize.new(5,2)
      badConsequence = DeathBadConsequence.new('Hoy no es tu día de suerte. Mueres.')
      @unusedMonsters << Monster.new('El gran cthulhu',20,prize,badConsequence, 4)
      
      # Serpiente Político
      prize = Prize.new(1,2)
      badConsequence = NumericBadConsequence.new('Tu gobierno te recorta 2 niveles',2,0,0)
      @unusedMonsters << Monster.new('Serpiente Político',8,prize,badConsequence,-2)
      
      # Felpuggoth
      prize = Prize.new(1,1)
      badConsequence = SpecificBadConsequence.new('Pierdes tu casco y tu 
            armadura visible. Pierdes tus manos ocultas.', 0, 
        [TreasureKind::ARMOR, TreasureKind::HELMET],
        [TreasureKind::BOTHHANDS])
      @unusedMonsters << Monster.new('Felpuggoth',2,prize,badConsequence, 3)
      
      # Shoggoth
      prize = Prize.new(2,4)
      badConsequence = NumericBadConsequence.new('Pierdes 2 niveles',2,0,0)
      @unusedMonsters << Monster.new('Shoggoth',16,prize,badConsequence,-4)
      
      # Lolitagooth
      prize = Prize.new(1,11
      )
      badConsequence = NumericBadConsequence.new('Pintalabios negro. Pierdes 2 niveles.',2,0,0)
      @unusedMonsters << Monster.new('Lolitagooth',2,prize,badConsequence,3)
      
      shuffleMonsters
    end
    
    def initCultistsCardDeck
      @unusedCultists << Cultist.new("Sectario", 1)
      @unusedCultists << Cultist.new("Sectario", 2)
      @unusedCultists << Cultist.new("Sectario", 1)
      @unusedCultists << Cultist.new("Sectario", 2)
      @unusedCultists << Cultist.new("Sectario", 1)
      @unusedCultists << Cultist.new("Sectario", 1)
      
      shuffleCultists
    end
    
    
    def shuffleTreasures
      @unusedTreasures.shuffle!
    end
    
    def shuffleMonsters
      @unusedMonsters.shuffle!
    end
    
    def shuffleCultists
      @unusedCultists.shuffle!
    end
    
    public  
=begin
    @brief Devuelve el siguiente tesoro que hay en el mazo de tesoros (unusedTreasures) y lo elimina
    de él. Si al iniciar el método el mazo unusedTreasures estuviese vacío, pasa el mazo de
    descartes (usedTreasures) al mazo de tesoros (unusedTreasures) y barájalo, dejando el
    mazo de descartes vacío.
    @return Siguiente tesoro de unusedTreasures
=end
    def nextTreasure
      if (@unusedTreasures.empty?)
        usedTreasures_copy = ArrayList.new(@usedTreasures)
        @unusedTreasures = usedTreasures_copy
        shuffleTreasures
        @usedTreasures.clear
      end
      
      t = @unusedTreasures.at(0)
      @usedTreasures << t
      @unusedTreasures.delete(t)
      
      return t
    end
    
=begin
    Igual que el anterior pero con los mazos de monstruos.
=end
    def nextMonster
      if (@unusedMonsters.empty?)
        usedMonsters_copy = ArrayList.new(@usedMonsters)
        @unusedMonsters = usedMonsters_copy
        shuffleMonsters
        @usedMonsters.clear
      end
      
      m = @unusedMonsters.at(0)
      @usedMonsters << m
      @unusedMonsters.delete(m)
      
      return m
    end
    
    def nextCultist
      c = @unusedCultists.at(0)
      
      @unusedCultists.delete(0)
      
      return c
    end
    
    def giveTreasureBack(t)
      @usedTreasures << t
    end
    
    def giveMonsterBack(m)
      @usedMonsters << m
    end
    
    def initCards
      initTreasureCardDeck
      initMonsterCardDeck
      initCultistsCardDeck
    end
  end
end
