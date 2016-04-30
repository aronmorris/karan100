import java.io.File;
import java.io.IOException;
import java.util.TimerTask;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class AlarmTask extends TimerTask implements LineListener {

	private String msg;
	boolean completed;
		
	public AlarmTask(String msg) {
		this.msg = msg;
	}
	public void run() {
		System.out.println(msg);
		playAlarm(msg);
	}
	
	//Audio playback courtesy of http://www.codejava.net/coding/how-to-play-back-audio-in-java-with-examples
	//Not going to reinvent the wheel on something fairly simple like this
	private void playAlarm(String alarmPath) {
	
		File audioFile = new File(alarmPath);
		
		try {
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
			
			AudioFormat format = audioStream.getFormat();
			
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			
			Clip audioClip = (Clip) AudioSystem.getLine(info);
			
			audioClip.addLineListener(this);
			
			audioClip.open(audioStream);
			
			audioClip.start();
			
			while (!completed){
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			audioClip.close();
			
		} catch(UnsupportedAudioFileException e) {
			System.out.println("File not supported.");
			e.printStackTrace();
		} catch(LineUnavailableException e) {
			System.out.println("Audio line unavailable.");
			e.printStackTrace();
		} catch(IOException e) {
			System.out.println("Error playing file.");
			e.printStackTrace();
		}
		
	}
	@Override
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();
         
        if (type == LineEvent.Type.START) {
            System.out.println("Playback started.");
             
        } else if (type == LineEvent.Type.STOP) {
            completed = true;
            System.out.println("Playback completed.");
        }
 
    }	
	
	
}
