package tk.nicolazaltron.scadenzeauto;

import java.util.Date;

/**
 * Created by Nicola Zaltron on 8/14/2017.
 */

public class Vehicle {
    private String name;
    private Date revisione;
    private Date bollo;
    private Date assicurazione;

    public Vehicle(String name, Date revisione, Date bollo, Date assicurazione) {
        this.name = name;
        this.revisione = revisione;
        this.bollo = bollo;
        this.assicurazione = assicurazione;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRevisione(Date revisione) {
        this.revisione = revisione;
    }

    public void setBollo(Date bollo) {
        this.bollo = bollo;
    }

    public void setAssicurazione(Date assicurazione) {
        this.assicurazione = assicurazione;
    }

    public String getName() {
        return name;
    }

    public Date getRevisione() {
        return revisione;
    }

    public Date getBollo() {
        return bollo;
    }

    public Date getAssicurazione() {
        return assicurazione;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return name+","+revisione.toString()+","+bollo.toString()+","+assicurazione.toString();
    }
}

