package arezzo.modeles;

import javafx.scene.image.Image;
import org.json.JSONException;
import org.json.JSONObject;
import partition.Partition;
//import gson;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private ArrayList<String> listeNotes;
    private JSONObject json;

    public Arezzo() {
        super();
        try {
            synthe = MidiSystem.getSynthesizer();
        } catch (MidiUnavailableException e) {
            throw new RuntimeException(e);
        }
        try {
            synthe.open();
        } catch (MidiUnavailableException e) {
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
        listeNotes = new ArrayList<>();
        json = new JSONObject();
    }


    public Boolean isMelodieVide() {
        if (notes.length() == 0) {
            return true;
        }
        return false;
    }

    public void modifVolume(double newVol) {
        this.volume = newVol;
        this.parti.setVolume(volume);
    }

    public void modifTempo(int newTempo) {
        this.tempo = newTempo;
        this.parti.setTempo(tempo);
    }

    public void modifInstrument(String newInstru) {
        this.instrument = newInstru;
        parti.setInstrument(instrument);
        this.notifierObservateur();
    }

    public Image getImage() {
        return parti.getImage();
    }

    public Image getImagePlay() {
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

    public void setMelodie(String note) {
        String noteAdd;
        /////On gère le nombre de note/////
        if (note.equals("chut")) {
            for (double i = nbNotes; i < 4; i++) {
                this.notes.append("z1");            //A faire en fonction de la forme de note séléctionnée (ronde mon zbi et tt)
            }
            this.notes.append("|");
            this.nbNotes = 0;
            parti.setMelodie(notes.toString());
        } else {
            if (assezDePlace(this.getForme())) {
                if (this.getOctave().equals("Grave")) {
                    noteAdd = note + ",";
                } else if (this.getOctave().equals("Aigu")) {
                    noteAdd = note.toLowerCase();
                } else {   //Note en medium
                    noteAdd = note;
                }
                //Puis on ajoute la durée corespondante//
                if (this.getForme().equals("Croche")) {
                    noteAdd = noteAdd + "/";
                } else if (this.getForme().equals("Blanche")) {
                    noteAdd = noteAdd + "2";
                } else if (this.getForme().equals("Ronde")) {
                    noteAdd = noteAdd + "4";
                } //Si forme == noire, on ne change rien
                this.notes.append(noteAdd);
                this.listeNotes.add(noteAdd);
                if (this.nbNotes == 4) {
                    this.notes.append("|");
                    this.nbNotes = 0;
                }
                System.out.println(notes.toString());
                parti.setMelodie(notes.toString());
                parti.play(noteAdd);
            } else {
                System.out.println("Pas assez de place pour le note choisie : ajout de |");
                this.notes.append("|");
                this.nbNotes = 0;
            }
        }
        System.out.println("Votre oeuvre : " + notes);
        System.out.println("En liste s'il vous plaît !" + listeNotes.toString());
        this.notifierObservateur();
        System.out.println("");
    }

    /**
     * Augmente nbNotes en fonction de la durée de la note voulue et regarde si on peut la posée
     *
     * @param forme
     * @return true si assez de place dans la partition pour ajouter la note souhaitée
     */
    public boolean assezDePlace(String forme) {
        if (forme.equals("Noire")) {    // + 1
            this.nbNotes++;
            if (this.nbNotes > 4) {
                return false;
            }
        }
        switch (forme) {
            case "Croche":    // + 0.5
                this.nbNotes = nbNotes + 0.5;
                return !(this.nbNotes > 4);
            case "Blanche":  // + 2
                this.nbNotes += 2;
                return !(this.nbNotes > 4);
            case "Ronde":    // + 4
                this.nbNotes += 4;
                return !(this.nbNotes > 4);
        }
        return true;
    }

    public void jouerTout() {
        parti.play();
    }

    public void changerOctave(String newOctave) {
        this.octave = newOctave;
    }

    public void changerForme(String newForme) {
        this.forme = newForme;
    }

    public String getForme() {
        return forme;
    }

    public String getOctave() {
        return octave;
    }

    public ArrayList<String> getListeNotes() {
        return listeNotes;
    }

    public void ajouterObs(Observateur obs) {
        this.listeObservateur.add(obs);
    }

    public String getNom() {
        return nom;
    }

    public void changerNom(String newNom) {
        this.nom = newNom;
        this.notifierObservateur();
    }

    public void supprimerNotes(Object o) {
    }

    public void chargerDonnees(File file) throws JSONException {
        String lecture;
        try{
            FileReader fileReader = new FileReader(file.getAbsoluteFile());
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            lecture = bufferedReader.readLine();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.importerDonneesChargees(lecture);
    }
    public void importerDonneesChargees(String fichier) throws JSONException {
        System.out.println(fichier);
        json = new JSONObject(fichier);
        notes = new StringBuilder();
        listeNotes.clear();
        nbNotes = 0;
        String newTitre = (String) json.get("Titre");
        String arrayString = String.valueOf(json.get("ArrayNotes"));
        arrayString = arrayString.replace("[","").replace("]","").replace("\"","");
        String[] str = arrayString.split(",");
        List<String> arrayConvert = new ArrayList<String>();
        arrayConvert = Arrays.asList(str);
        for (String not: arrayConvert
             ) {
            this.setMelodie(not);
        }
        nom = newTitre;
        this.notifierObservateur();
    }

    public void sauvegarder() throws JSONException {
        json.put("ArrayNotes", listeNotes.toString());
        json.put("Titre", nom);
    }

    public JSONObject getJson() {
        return json;
    }

    public void cleanNotes() {
        this.notes = new StringBuilder();
        this.listeNotes = new ArrayList<>();
        nbNotes = 0;
        this.notifierObservateur();
    }

    public void notifierObservateur() {
        for (Observateur obs : listeObservateur
        ) {
            obs.reagir();
        }
    }
}

