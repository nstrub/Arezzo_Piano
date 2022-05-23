package arezzo.modeles;

import javafx.scene.image.Image;
import partition.Melodie;
import partition.Partition;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public class Arezzo {
    private Partition parti;
    private Synthesizer synthe;
    private StringBuilder notes;
    private int nbNotes;

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
        nbNotes = 0;
    }

    public Partition getPartition(){
        return parti;
    }


    public Boolean isMelodieVide(){
        if(notes.length() == 0){
            return true;
        }
        return false;
    }

    public Image getImage(){
        return parti.getImage();
    }

    public void setMelodie(String note){
        if(note.equals("chut")){
            for(int i = nbNotes; i < 4; i++){
                this.notes.append("-");
            }
            this.notes.append("|");
            this.nbNotes = 0;
        }
        else{
            this.nbNotes ++;
            if(nbNotes == 5){
                this.notes.append("|");
                this.nbNotes = 0;
            }
            System.out.println(nbNotes);
            this.notes.append(note);
            parti.setMelodie(note);
            parti.play(note);
        }
        System.out.println("Voila ta mélo pour l'instant bg (stringBuilder) " + notes);
    }
}
