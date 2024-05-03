package tr.edu.ku.Domain;

import java.io.Serializable;

public class Spell implements Serializable{
    String name;
    int duration; // in seconds
    int ID;

    public Spell(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }
}