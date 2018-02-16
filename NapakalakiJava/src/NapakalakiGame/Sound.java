/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NapakalakiGame;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Dani
 */
public class Sound {
    
    Clip sonido;
    String pathFile;
    
    public Sound(String file_path)
    {
        pathFile = file_path;
        Load();
    }
    
    /**
     * @brief Reproduce el sonido una vez o en bucle
     * @param loop true para reproducir en bucle, false para reproducir una vez
     */
    public void Play(boolean loop){
        
        if (loop)  
            sonido.loop(Clip.LOOP_CONTINUOUSLY);
        else
        {
            sonido.start();
            Load();
        }
    }
    
    /**
     * @brief Carga el sonido para ser reproducido
     */
    private void Load()
    {
        try {
                sonido = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(this.getClass().getResource(pathFile));
                //File file = new File(pathFile);
                sonido.open(inputStream);
                
            } catch (LineUnavailableException ex) {
                Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
            }   catch (IOException ex) {
                Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
            } 
    }
    
    /**
     * @brief Detiene la ejecución del sonido
     */
    public void Stop()
    {
        sonido.stop();
    }
    
}
