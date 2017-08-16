package Models;

import java.util.Date;

/**
 * Created by luisazurlo on 15/08/2017.
 */

public class Scadenza {
    private String kindOfScadenza;
    private Date dateScadenza;


    public Scadenza(String kindOfScadenza, Date dateScadenza) {
        this.kindOfScadenza = kindOfScadenza;
        this.dateScadenza = dateScadenza;
    }

    public Scadenza(String kindOfScadenza){
        this.kindOfScadenza = kindOfScadenza;
        this.dateScadenza = null;
    }

    public void setName(String kindOfScadenza) {
        this.kindOfScadenza = kindOfScadenza;
    }

    public void setScadenza(Date dateScadenza) {
        this.dateScadenza = dateScadenza;
    }


    public String getName() {
        return kindOfScadenza;
    }

    public Date getDateScadenza() {
        return dateScadenza;
    }

/*    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return name+","+revisione.toString()+","+bollo.toString()+","+assicurazione.toString();
    }*/
}

