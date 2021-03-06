package Models;

import java.util.ArrayList;

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
    private ArrayList<Deadline> scadenze;

    public Vehicle(){
        this.name = "";
        this.icon = Icon.car;
        this.scadenze = new ArrayList<>();
    }

    public Vehicle(String name){
        this.name = name;
        this.icon = Icon.car;
        this.scadenze = new ArrayList<>();
    }

    public Vehicle(String name, Icon icon) {
        this.name = name;
        this.icon = icon;
        this.scadenze = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public void setScadenze(ArrayList<Deadline> scadenze) {
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

