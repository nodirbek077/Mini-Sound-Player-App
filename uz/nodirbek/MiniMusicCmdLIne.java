package uz.nodirbek;

import javax.sound.midi.*;
import static javax.sound.midi.Sequence.*;

import static javax.sound.midi.ShortMessage.*;

public class MiniMusicCmdLIne {
    public static void main(String[] args) {
        MiniMusicCmdLIne mini = new MiniMusicCmdLIne();
        if (args.length < 2){
            System.out.println("Don't forget the instrument and note args");
        }else {
            int instrument = Integer.parseInt(args[0]);
            int note = Integer.parseInt(args[1]);
            mini.play(instrument,note);
        }
    }

    public void play(int instrument, int note){
        try {
            Sequencer  player = MidiSystem.getSequencer();
            player.open();
            Sequence sequence = new Sequence(PPQ, 4);
            Track track = sequence.createTrack();

            ShortMessage msg1 = new ShortMessage();
            msg1.setMessage(PROGRAM_CHANGE,1, instrument, 0);
            MidiEvent changeInstrument = new MidiEvent(msg1,1);
            track.add(changeInstrument);

            ShortMessage msg2 = new ShortMessage();
            msg2.setMessage(NOTE_ON,1, note, 100);
            MidiEvent noteOn = new MidiEvent(msg2,1);
            track.add(noteOn);

            ShortMessage msg3 = new ShortMessage();
            msg3.setMessage(NOTE_OFF,1, note, 0);
            MidiEvent noteOff = new MidiEvent(msg3,16);
            track.add(noteOff);

            player.setSequence(sequence);
            player.start();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
