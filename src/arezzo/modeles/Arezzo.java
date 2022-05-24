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
    private String forme;
    private String octave;

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
        forme = "noire";
        octave = "medium";
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
        String noteAdd;
        /////On gère le nombre de note/////
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
                this.nbNotes = 1;
            }
            /////On quelle note on ajoute/////
                //En premier on regarde l'octave//
            if(this.getOctave().equals("Grave")){
                noteAdd = note + ",";
            } else if (this.getOctave().equals("Aigu")) {
                noteAdd = note.toLowerCase();
            }
            else{   //Note en medium
                noteAdd = note;
            }
                //Puis on ajoute la durée corespondante//
            if(this.getForme().equals("Croche")){
                noteAdd = noteAdd + "/";
            } else if (this.getForme().equals("Blanche")) {
                noteAdd = noteAdd + "2";
            } else if (this.getForme().equals("Ronde")) {
                noteAdd = noteAdd + "4";
            } //Si forme == noire, on ne change rien
            System.out.println(this.getOctave() + " " +this.getForme());
            this.notes.append(noteAdd);
            parti.setMelodie(noteAdd);
            parti.play(noteAdd);
        }
        System.out.println("Voila ta mélo pour l'instant bg (stringBuilder) " + notes);
    }

    public void changerOctave(String newOctave){
        this.octave = newOctave;
    }
    public void changerForme(String newForme){
        this.forme = newForme;
    }

    public String getForme() {
        return forme;
    }

    public String getOctave() {
        return octave;
    }
}
