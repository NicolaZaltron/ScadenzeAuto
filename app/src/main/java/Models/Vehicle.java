package Models;

import java.util.ArrayList;
import java.util.Date;

import Models.Scadenza;

/**
 * Created by Nicola Zaltron on 8/14/2017.
 */

public class Vehicle {
    public enum Icon{
        car,
        pickup,
        van,
        troc,
        ape,
        bike,
        scooter,
        motorbike,
        boat
    }
    private String name;
    private Icon icon;

    private ArrayList<Scadenza> scadenze;

    public Vehicle(){
        this.name = "";
        this.icon = Icon.car;
    }

    public Vehicle(String name){
        this.name = name;
        this.icon = Icon.car;
        this.scadenze = null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public void setScadenze(ArrayList<Scadenza> scadenze) {
        this.scadenze = scadenze;
    }

    public String getName() {
        return name;
    }

    public Icon getIcon() {
        return icon;
    }

    public ArrayList getScadenze() {
        return scadenze;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

