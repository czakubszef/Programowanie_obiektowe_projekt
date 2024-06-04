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
    /*
    Logika zombie:
    1. Jesli masz bron atakuj zombie
    2. Jesli nie masz broni uciekaj przed zombie
    3. Jesli nie masz broni i nie ma zombie w okolicy i znajduje sie bron idz po bron
    4. Jesli masz bron i nie ma zombie  poruszaj sie w losowym kierunku (jesli napotkasz plot na drodze zniszcz, jesli napotkasz bron to sprawdz czy lepsza, jesli tak to wez)
    5. Zabito człowieka
    6. Zniszczono plot
     */
    public int logika_czlowieka(mapa mapa){
        Point cel_ruchu = null;
        for(int i=0; i<mapa.tab_z.size(); i++){

        }
    }









    // Konstruktory, gettery i settery
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
