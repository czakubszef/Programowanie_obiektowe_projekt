package src;

import java.awt.*;
import java.util.Random;
public class czlowiek extends src.postac {
    /*
    0 - brak broni
    1 - bron
     */
    int czy_uzbrojony;
    boolean czy_zarazony;
    int tury_do_zarazenia;
    bron bron;
    public int logika_czlowieka(){
        return 1;
    }
    public czlowiek(Point koor){
        super(koor, 10, 2);
        Random rand = new Random();
        int random = rand.nextInt(10);
        if(random <=2){
            this.czy_uzbrojony = 1;
        }
        else{
            this.czy_uzbrojony=0;
        }
        random = rand.nextInt(5);
        this.czy_uzbrojony = random+1;
        this.czy_zarazony=false;
        this.tury_do_zarazenia=3;
    }


    public int getCzy_uzbrojony() {
        return czy_uzbrojony;
    }

    public void setCzy_uzbrojony(int bron) {
        this.czy_uzbrojony = bron;
    }

    public boolean isCzy_zarazony() {
        return czy_zarazony;
    }

    public void setCzy_zarazony(boolean czy_zarazony) {
        this.czy_zarazony = czy_zarazony;
    }

    public int getTury_do_zarazenia() {
        return tury_do_zarazenia;
    }

    public void setTury_do_zarazenia(int tury_do_zarazenia) {
        this.tury_do_zarazenia = tury_do_zarazenia;
    }

    public src.bron getBron() {
        return bron;
    }

    public void setBron(src.bron bron) {
        this.bron = bron;
    }
}
