package uz.nodirbek;

import javax.sound.midi.*;

import static javax.sound.midi.ShortMessage.*;

public class MusicApp {
    public static void main(String[] args) {
        MusicApp musicApp = new MusicApp();
        musicApp.play();
    }

    public void play(){
        try {
            //1
            Sequencer player = MidiSystem.getSequencer();
            player.open();

            //2
            Sequence seq = new Sequence(Sequence.PPQ,4);

            //3
            Track track = seq.createTrack();

            //4
            ShortMessage msg1 = new ShortMessage();
            msg1.setMessage(NOTE_ON,1,20,100);
            MidiEvent noteOn = new MidiEvent(msg1,1);
            track.add(noteOn);

            ShortMessage msg2 = new ShortMessage();
            msg2.setMessage(NOTE_OFF,1,20,100);
            MidiEvent noteOff = new MidiEvent(msg2,3);
            track.add(noteOff);

            //5
            player.setSequence(seq);
            player.start();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
