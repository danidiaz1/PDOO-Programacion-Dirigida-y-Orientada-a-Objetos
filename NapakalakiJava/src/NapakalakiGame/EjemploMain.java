
package NapakalakiGame;

import GUI.Dice;
import GUI.NapakalakiView;
import GUI.PlayerNamesCapture;
import java.util.ArrayList;

public class EjemploMain {

    public static void main(String[] args) {
      /* Antiguo main
      Napakalaki game = Napakalaki.getInstance();
      GameTester test = GameTester.getInstance();
      
      // Poner el numero de jugadores con el que se quiera probar
      test.play(game, 2); 
      */
      Napakalaki game = Napakalaki.getInstance();
      
      NapakalakiView napakalakiView = new NapakalakiView();
      Dice.createInstance(napakalakiView);

      ArrayList<String> names;
      PlayerNamesCapture namesCapture = new PlayerNamesCapture(napakalakiView,true);
      names = namesCapture.getNames();
      
      game.initGame(names);
      napakalakiView.setNapakalaki(game);
      napakalakiView.setVisible(true);
    }
}
