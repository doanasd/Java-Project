package view;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class sound {

    public static void playSound(String soundFilePath) {
        try {
            // Đọc file âm thanh và tạo một Clip
            File soundFile = new File(soundFilePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            // Phát âm thanh
            clip.start();

        } catch (Exception ex) {
            System.out.println("Error playing sound: " + ex.getMessage());
        }
    }
}

