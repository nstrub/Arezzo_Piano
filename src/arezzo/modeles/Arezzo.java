package arezzo.modeles;

import partition.Partition;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public class Arezzo {
    private Partition parti;
    private Synthesizer synthe;
    private StringBuilder notes;

    public Arezzo(){
        super();
        try{
            synthe = MidiSystem.getSynthesizer();
        }catch (MidiUnavailableException e){
            throw new RuntimeException(e);
        }
        try{
            synthe.open();
        }catch (MidiUnavailableException e){
            throw new RuntimeException(e);
        }
        parti = new Partition(synthe);
        notes = new StringBuilder();
    }

    public Partition getPartition(){
        return parti;
    }

    public void setMelodie(String note){
        parti.setMelodie(note);
        parti.play(note);
    }
}
