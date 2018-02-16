/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NapakalakiGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author Daniel Díaz Pareja
 */
public class CardDealer {
    private static final CardDealer instance = new CardDealer();
    private ArrayList<Monster> unusedMonsters = new ArrayList();
    private ArrayList<Monster> usedMonsters = new ArrayList();
    private ArrayList<Treasure> unusedTreasures = new ArrayList();
    private ArrayList<Treasure> usedTreasures = new ArrayList();
    private ArrayList<Cultist> unusedCultists = new ArrayList();
    
    // El constructor privado asegura que no se puede instanciar
    // desde otras clases
    private CardDealer() 
    {
    
    }
    
    public static CardDealer getInstance(){return instance;}
    
    /** @brief Inicia el mazo de tesoros
     */
    private void initTreasureCardDeck()
    {
        // ¡Sí, mi amo!
        unusedTreasures.add(new Treasure("¡Si mi amo!", 4, TreasureKind.HELMET));

        // Botas de investigación
        unusedTreasures.add(new Treasure("Botas de investigacion", 3, TreasureKind.SHOES));

        // Capucha de Cthulhu
        unusedTreasures.add(new Treasure("Capucha de Cthulhu", 3, TreasureKind.HELMET));

        // A prueba de babas
        unusedTreasures.add(new Treasure("A prueba de babas", 2, TreasureKind.ARMOR));

        // Botas de lluvia ácida
        unusedTreasures.add(new Treasure("Botas de lluvia acida", 1, TreasureKind.BOTHHANDS));

        // Casco minero
        unusedTreasures.add(new Treasure("Casco minero", 2, TreasureKind.HELMET));

        // Ametralladora Thompson
        unusedTreasures.add(new Treasure("Ametralladora Thompson", 4, TreasureKind.BOTHHANDS));

        // Camiseta de la UGR
        unusedTreasures.add(new Treasure("Camiseta de la UGR", 1, TreasureKind.ARMOR));

        // Clavo de rail ferroviario
        unusedTreasures.add(new Treasure("Clavo de rail ferroviario", 3, TreasureKind.ONEHAND));

        // Cuchillo de sushi arcano
        unusedTreasures.add(new Treasure("Cuchillo de sushi arcano", 2, TreasureKind.ONEHAND));

        // Fez Alópodo
        unusedTreasures.add(new Treasure("Fez Alopodo", 3, TreasureKind.HELMET));

        // Hacha prehistórica
        unusedTreasures.add(new Treasure("Hacha prehistorica", 2, TreasureKind.ONEHAND));

        // El aparato del Pr. Tesla
        unusedTreasures.add(new Treasure("El aparato del Pr. Tesla", 4, TreasureKind.ARMOR));

        // Gaita
        unusedTreasures.add(new Treasure("Gaita", 4, TreasureKind.BOTHHANDS));

        // Insecticida
        unusedTreasures.add(new Treasure("Insecticida", 2, TreasureKind.ONEHAND));

        // Escopeta de 3 cañones
        unusedTreasures.add(new Treasure("Escopeta de 3 cañones", 4, TreasureKind.BOTHHANDS));

        // Garabato místico
        unusedTreasures.add(new Treasure("Garabato mistico", 2, TreasureKind.ONEHAND));

        // La rebeca metálica
        unusedTreasures.add(new Treasure("La rebeca metalica", 2, TreasureKind.ARMOR));

        // Lanzallamas
        unusedTreasures.add(new Treasure("Lanzallamas", 4, TreasureKind.BOTHHANDS));

        // Necro-comicón
        unusedTreasures.add(new Treasure("Necro comicon", 1, TreasureKind.ONEHAND));

        // Necronomicón
        unusedTreasures.add(new Treasure("Necronomicon", 5, TreasureKind.BOTHHANDS));

        // Linterna a 2 manos
        unusedTreasures.add(new Treasure("Linterna a 2 manos", 3, TreasureKind.BOTHHANDS));

        // Necro-gnomicón
        unusedTreasures.add(new Treasure("Necro-gnomicon", 2, TreasureKind.ONEHAND));

        // Necrotelecom
        unusedTreasures.add(new Treasure("Necrotelecom", 2, TreasureKind.HELMET));
        
        // Mazo de los antiguos
        unusedTreasures.add(new Treasure("Mazo de los antiguos", 3, TreasureKind.ONEHAND));

        // Necro-playboycón
        unusedTreasures.add(new Treasure("Necro playboycon", 3, TreasureKind.ONEHAND));

        // Porra preternatural
        unusedTreasures.add(new Treasure("Porra preternatural", 2, TreasureKind.ONEHAND));

        // Shogulador
        unusedTreasures.add(new Treasure("Shogulador", 1, TreasureKind.BOTHHANDS));
        
        // Tentáculo de pega
        unusedTreasures.add(new Treasure("Tentaculo de pega", 2, TreasureKind.HELMET));
        
        // Zapato deja-amigos
        unusedTreasures.add(new Treasure("Zapato deja-amigos", 1, TreasureKind.SHOES));
        
        shuffleTreasures();
    }
    
    /**
     * @brief Inicia el mazo de monstruos
     */
    private void initMonsterCardDeck()
    {
        // 3 Byakhees de bonanza
        BadConsequence bc = new SpecificBadConsequence("Pierdes tu armadura"
            + " visible y otra oculta", 0, 
            new ArrayList(Arrays.asList(TreasureKind.ARMOR)),
            new ArrayList(Arrays.asList(TreasureKind.ARMOR)));
        Prize premio = new Prize(2,1);
        unusedMonsters.add(new Monster("3 Byakhees de bonanza", 8, bc, premio));
        
        // Chibithulhu
        bc = new SpecificBadConsequence("Embobados con el lindo primigenio te descartas"
            + " de tu casco invisible", 0, 
            new ArrayList(Arrays.asList(TreasureKind.HELMET)),
            new ArrayList(Arrays.asList()));
        premio = new Prize(1,1);
        unusedMonsters.add(new Monster("Chibithulhu", 2, bc, premio));
        
        // El sopor de Dunwich
        bc = new SpecificBadConsequence("El primordial bostezo" +
            " contagioso. Pierdes el calzado visible", 0, 
            new ArrayList(Arrays.asList(TreasureKind.SHOES)),
            new ArrayList(Arrays.asList()));
        premio = new Prize(1,1);
        unusedMonsters.add(new Monster("El sopor de Dunwich", 2, bc, premio));
        
        // Angeles de la noche ibicenca
        bc = new SpecificBadConsequence("Te atrapan y te dejan caer en mitad del" +
            " vuelo. Descarta 1 mano visible y 1" +
            " mano oculta", 0, 
            new ArrayList(Arrays.asList(TreasureKind.ONEHAND)),
            new ArrayList(Arrays.asList(TreasureKind.ONEHAND)));
        premio = new Prize(4,1);
        unusedMonsters.add(new Monster("Angeles de la noche ibicenca", 14, bc, premio));
        
        // El gorrón en el umbral
        bc = new SpecificBadConsequence("Pierdes todos tus tesoros\n" +
            " visibles", 0, 
            new ArrayList(Arrays.asList(TreasureKind.ARMOR, TreasureKind.BOTHHANDS,
                TreasureKind.HELMET, TreasureKind.ONEHAND, TreasureKind.SHOES)),
            new ArrayList(Arrays.asList()));
        premio = new Prize(3,1);
        unusedMonsters.add(new Monster("El gorron en el umbral", 10, bc, premio));
        
        // H.P. Munchcraft
        bc = new SpecificBadConsequence("Pierdes la armadura visible", 0, 
            new ArrayList(Arrays.asList(TreasureKind.ARMOR)),
            new ArrayList(Arrays.asList()));
        premio = new Prize(2,1);
        unusedMonsters.add(new Monster("H.P. Munchcraft", 6, bc, premio));
        
        // Bichgooth
        bc = new SpecificBadConsequence("Sientes bichos bajo la" +
            " ropa. Descarta la armadura visible", 0, 
            new ArrayList(Arrays.asList(TreasureKind.ARMOR)),
            new ArrayList(Arrays.asList()));
        premio = new Prize(1,1);
        unusedMonsters.add(new Monster("Bichgooth", 2, bc, premio));
        
        // El rey de rosa
        bc = new NumericBadConsequence("Pierdes 5 niveles y 3" +
            " tesoros visibles", 5, 3, 0);
        premio = new Prize(4,2);
        unusedMonsters.add(new Monster("El rey de rosa", 13, bc, premio));
        
        // La que redacta en las tinieblas
        bc = new NumericBadConsequence("Toses los pulmones y" +
            " pierdes 2 niveles.", 2, 0, 0);
        premio = new Prize(1,1);
        unusedMonsters.add(new Monster("La que redacta en las tinieblas", 2, bc, premio));
        
        // Los Hondos
        bc = new DeathBadConsequence("Estos monstruos resultan" +
            " bastante superficiales y te aburren" +
            " mortalmente. Estas muerto");
        premio = new Prize(2,1);
        unusedMonsters.add(new Monster("Los Hondos", 8, bc, premio));
        
        // Semillas Cthulhu
        bc = new NumericBadConsequence("Pierdes 2 niveles y 2" +
            " tesoros ocultos.",2,0,2);
        premio = new Prize(2,1);
        unusedMonsters.add(new Monster("Semillas Cthulhu", 4, bc, premio));
        
        // Dameargo
        bc = new NumericBadConsequence("Te intentas escaquear." +
            " Pierdes una mano visible.",2,0,2);
        premio = new Prize(2,1);
        unusedMonsters.add(new Monster("Dameargo", 1, bc, premio));
        
        // Pollipolipo volante
        bc = new NumericBadConsequence("Da mucho asquito." +
            " Pierdes 3 niveles.",3,0,0);
        premio = new Prize(1,1);
        unusedMonsters.add(new Monster("Pollipolipo volante", 3, bc, premio));
        
        // Yskhtihyssg-Goth
        bc = new DeathBadConsequence("No le hace gracia que" +
            " pronuncien mal su nombre. Estas" +
            " muerto");
        premio = new Prize(3,1);
        unusedMonsters.add(new Monster("YskhtihyssgGoth", 12, bc, premio));
        
        // Familia feliz
        bc = new DeathBadConsequence("La familia te atrapa." +
            " Estas muerto.");
        premio = new Prize(4,1);
        unusedMonsters.add(new Monster("Familia feliz", 1, bc, premio));
        
        // Roboggoth
        bc = new SpecificBadConsequence("Pierdes un arma visible de 2 manos y 2 niveles.",2,
            new ArrayList(Arrays.asList(TreasureKind.BOTHHANDS)),
            new ArrayList(Arrays.asList()));
        premio = new Prize(2,1);
        unusedMonsters.add(new Monster("Roboggoth", 8, bc, premio));
        
        // El espia
        bc = new SpecificBadConsequence("Te asusta en la noche." +
            " Pierdes un casco visible.",0,
            new ArrayList(Arrays.asList(TreasureKind.HELMET)),
            new ArrayList(Arrays.asList()));
        premio = new Prize(1,1);
        unusedMonsters.add(new Monster("El espia", 5, bc, premio));
        
        // El Lenguas
        bc = new NumericBadConsequence("Menudo susto te llevas." +
            " Pierdes 2 niveles y 5 tesoros visibles.",2,5,0);
        premio = new Prize(1,1);
        unusedMonsters.add(new Monster("El Lenguas", 20, bc, premio));
        
        // Bicefalo
        bc = new SpecificBadConsequence("Te faltan manos para" +
            " tanta cabeza. Pierdes 3 niveles y tus" +
            " tesoros visibles de las manos.",2,
            new ArrayList(Arrays.asList(TreasureKind.ONEHAND,TreasureKind.BOTHHANDS)),
            new ArrayList(Arrays.asList()));
        premio = new Prize(1,1);
        unusedMonsters.add(new Monster("Bicefalo", 20, bc, premio));
        
        /*************************************************************************/
        // Monstruos con sectarios
        
        // El mal indecible impronunciable
        bc = new SpecificBadConsequence("Pierdes 1 mano visible",0,
            new ArrayList(Arrays.asList(TreasureKind.ONEHAND)),
            new ArrayList(Arrays.asList()));
        premio = new Prize(3,1);
        unusedMonsters.add(new Monster("El mal indecible impronunciable", 10, bc, premio, -2));

        // Testigos Oculares
        bc = new SpecificBadConsequence("Pierdes tus tesoros visibles. Jajaja.",0,
            new ArrayList(Arrays.asList(TreasureKind.ARMOR, TreasureKind.BOTHHANDS,
                TreasureKind.HELMET, TreasureKind.ONEHAND, TreasureKind.SHOES)),
            new ArrayList(Arrays.asList()));
        premio = new Prize(2,1);
        unusedMonsters.add(new Monster("Testigos oculares", 6, bc, premio, 2));
        
        // El gran cthulhu
        bc = new DeathBadConsequence("Hoy no es tu día de suerte. Mueres.");
        premio = new Prize(2,5);
        unusedMonsters.add(new Monster("El gran cthulhu", 20, bc, premio, 4));
        
        // Serpiente Político
        bc = new NumericBadConsequence("Tu gobierno te recorta 2 niveles",2,0,0);
        premio = new Prize(2,1);
        unusedMonsters.add(new Monster("Serpiente Politico", 8, bc, premio, -2));
        
        // Felpuggoth
        bc = new SpecificBadConsequence("Pierdes tu casco y tu" +
            " armadura visible. Pierdes tus manos ocultas.",0,
            new ArrayList(Arrays.asList(TreasureKind.ARMOR,TreasureKind.HELMET)),
            new ArrayList(Arrays.asList(TreasureKind.BOTHHANDS)));
        premio = new Prize(1,1);
        unusedMonsters.add(new Monster("Felpuggoth", 2, bc, premio, 5));
        
        // Shoggoth
        bc = new NumericBadConsequence("Pierdes 2 niveles",2,0,0);
        premio = new Prize(4,2);
        unusedMonsters.add(new Monster("Shoggoth", 16, bc, premio, -4));
        
        // Lolitagooth
        bc = new NumericBadConsequence("Pintalabios negro. Pierdes 2 niveles.",2,0,0);
        premio = new Prize(1,1);
        unusedMonsters.add(new Monster("Lolitagooth", 2, bc, premio, 3));
        
        shuffleMonsters();
    }
    
    private void initCultistsCardDeck()
    {
        unusedCultists.add(new Cultist("Sectario",1));
        unusedCultists.add(new Cultist("Sectario",2));
        unusedCultists.add(new Cultist("Sectario",1));
        unusedCultists.add(new Cultist("Sectario",2));
        unusedCultists.add(new Cultist("Sectario",1));
        unusedCultists.add(new Cultist("Sectario",1));
        
        shuffleCultists();
    }
    
    /**
     * @brief Baraja el mazo de tesoros no utilizados
     */
    private void shuffleTreasures()
    {
        Collections.shuffle(unusedTreasures);
    }
    
    /**
     * @brief Baraja el mazo de monstruos no utilizados
     */
    private void shuffleMonsters()
    {
        Collections.shuffle(unusedMonsters);
    }
    
    /**
     * @brief Baraja el mazo de monstruos con sectario
     */
    private void shuffleCultists()
    {
        Collections.shuffle(unusedCultists);
    }
    
    /**
     * @brief Devuelve el siguiente tesoro que hay en el mazo de tesoros (unusedTreasures) y lo elimina
        de él. Si al iniciar el método el mazo unusedTreasures estuviese vacío, pasa el mazo de
        descartes (usedTreasures) al mazo de tesoros (unusedTreasures) y barájalo, dejando el
        mazo de descartes vacío.
     * @return Siguiente tesoro de unusedTreasures
     */
    public Treasure nextTreasure()
    {
        if (unusedTreasures.isEmpty()) {
            ArrayList <Treasure> usedTreasures_copy = new ArrayList<Treasure>(usedTreasures); 
            unusedTreasures = usedTreasures_copy;
            shuffleTreasures();
            
            usedTreasures.clear();
        }
        Treasure t = unusedTreasures.get(0);
        
        usedTreasures.add(t);
        
        unusedTreasures.remove(t);
        
        return t;
    }
    
    /**
     * @brief Devuelve el siguiente monstruo que hay en el mazo de monstruos (unusedMonsters) y lo elimina
        de él. Si al iniciar el método el mazo unusedMonsters estuviese vacío, pasa el mazo de
        descartes (usedMonsters) al mazo de tesoros (unusedMonsters) y barájalo, dejando el
        mazo de descartes vacío.
     * @return Siguiente monstruo de unusedMonsters
     */
    public Monster nextMonster()
    {
        if (unusedMonsters.isEmpty()) {
            ArrayList<Monster> usedMonsters_copy = new ArrayList<Monster>(usedMonsters);
            unusedMonsters = usedMonsters_copy;
            shuffleMonsters();
            
            usedMonsters.clear();
        }
        
        Monster m = unusedMonsters.get(0);
        
        usedMonsters.add(m);
        
        unusedMonsters.remove(m);
        
        return m; 
    }
    
    /**
     * @brief Busca y devuelve el siguiente monstruo con sectario del mazo de cartas de sectario
     * @return Siguiente monstruo con sectario del mazo de cartas de sectario
     */
    public Cultist nextCultist()
    {
        Cultist c = unusedCultists.get(0);
        
        unusedCultists.remove(c);
        
        return c; 
    }
    
    public void giveTreasureBack(Treasure t){usedTreasures.add(t);}
    public void giveMonsterBack(Monster m){usedMonsters.add(m);}
    
    /**
     * @brief Crea y baraja todos los mazos
     */
    public void initCards()
    {
        initTreasureCardDeck();
        initMonsterCardDeck();
        initCultistsCardDeck();
    }
}
