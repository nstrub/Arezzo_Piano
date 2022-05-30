package arezzo.modeles;

import arezzo.vues.Observateur;
import arezzo.vues.VuePartition;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonWriter;
import javafx.scene.image.Image;
import netscape.javascript.JSObject;
import partition.Melodie;
import partition.Partition;
//import gson;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Arezzo {
    private Partition parti;
    private Synthesizer synthe;
    private StringBuilder notes;
    private double nbNotes;
    private String forme;
    private String octave;
    private ArrayList<Observateur> listeObservateur;
    private double volume;
    private int tempo;
    private String instrument;
    private String nom;
    private Gson gson;

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
        forme = "Noire";
        octave = "Medium";
        listeObservateur = new ArrayList<>();
        parti.setPreferedMaxWidth(600);
        tempo = 180;
        parti.setTempo(tempo);
        instrument = "Piano";
        parti.setInstrument(instrument);
        nom = "Votre morcreau";
        gson = new Gson();
    }


    public Boolean isMelodieVide(){
        if(notes.length() == 0){
            return true;
        }
        return false;
    }

    public void modifVolume(double newVol){
        this.volume = newVol;
        this.parti.setVolume(volume);
    }

    public void modifTempo(int newTempo){
        this.tempo = newTempo;
        this.parti.setTempo(tempo);
    }

    public void modifInstrument(String newInstru){
        this.instrument = newInstru;
        parti.setInstrument(instrument);
        this.notifierObservateur();
    }

    public Image getImage(){
        return parti.getImage();
    }
    public Image getImagePlay(){
        switch (instrument) {
            case "Guitare":
                return new Image("images/bouton/playGuitare.png");
            case "Trompette":
                return new Image("images/bouton/playTrompette.png");
            case "Saxophone":
                return new Image("images/bouton/playSax.png");
        }
        //Par défaut, l'image select est le piano
        return new Image("images/bouton/playPiano.png");

    }

    public void setMelodie(String note){
        String noteAdd;
        /////On gère le nombre de note/////
        if(note.equals("chut")){
            for(double i = nbNotes; i < 4; i++){
                this.notes.append("z1");            //A faire en fonction de la forme de note séléctionnée (ronde mon zbi et tt)
            }
            this.notes.append("|");
            this.nbNotes = 0;
            parti.setMelodie(notes.toString());
        }
        else{
            if(assezDePlace(this.getForme())){
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
                this.notes.append(noteAdd);
                if(this.nbNotes == 4){
                    this.notes.append("|");
                    this.nbNotes = 0;
                }
                parti.setMelodie(notes.toString());
                parti.play(noteAdd);
            }
            else {
                System.out.println("Pas assez de place pour le note choisie : ajout de |");
                for(double i = nbNotes; i < 4; i++){
                    this.notes.append("-");
                }
                this.notes.append("|");
                this.nbNotes = 0;
            }
        }
        System.out.println("Votre oeuvre : " + notes);
        this.notifierObservateur();
    }

    /**
     * Augmente nbNotes en fonction de la durée de la note voulue et regarde si on peut la posée
     * @param forme
     * @return  true si assez de place dans la partition pour ajouter la note souhaitée
     */
    public boolean assezDePlace(String forme){
        if(forme.equals("Noire")){    // + 1
            this.nbNotes ++;
            if (this.nbNotes > 4) {
                return false;
            }
        }
        if(forme.equals("Croche")){   // + 0.5
            this.nbNotes = nbNotes + 0.5;
            if (this.nbNotes > 4) {
                return false;
            }
        } else if (forme.equals("Blanche")) { // + 2
            this.nbNotes += 2;
            if (this.nbNotes > 4) {
                return false;
            }
        } else if (forme.equals("Ronde")) {   // + 4
            this.nbNotes += 4;
            if (this.nbNotes > 4) {
                return false;
            }
        }
        return true;
    }

    public void jouerTout(){
        parti.play();
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

    public void ajouterObs(Observateur obs) {
        this.listeObservateur.add(obs);
    }

    public String getInstrument() {
        return instrument;
    }

    public String getNom(){
        return nom;
    }
    public void changerNom(String newNom){
        this.nom = newNom;
        this.notifierObservateur();
    }



    public void sauvegarder(){

    }

    public void ouvrir() {

    }

        public void cleanNotes(){
        this.notes = new StringBuilder();
        this.notifierObservateur();
    }

    public void notifierObservateur(){
        for (Observateur obs: listeObservateur
             ) {
            obs.reagir();
        }
    }



}
