package src;

import java.awt.*;
import java.util.Random;

public class bron {
    int obrazenia;
    int wytrzymalosc;
    int zasieg;
    boolean czy_na_ziemii;
    Point koordynaty;
    public bron(Point koor, boolean czy_ziemia){
        Random rand = new Random();
        int random = rand.nextInt(5);
        this.obrazenia=random;
        random = rand.nextInt();
        this.wytrzymalosc=5;
        this.zasieg=random;
        this.czy_na_ziemii = czy_ziemia;
    }

    public int getObrazenia() {
        return obrazenia;
    }

    public void setObrazenia(int obrazenia) {
        this.obrazenia = obrazenia;
    }

    public int getWytrzymalosc() {
        return wytrzymalosc;
    }

    public void setWytrzymalosc(int wytrzymalosc) {
        this.wytrzymalosc = wytrzymalosc;
    }

    public int getZasieg() {
        return zasieg;
    }

    public void setZasieg(int zasieg) {
        this.zasieg = zasieg;
    }

    public boolean isCzy_na_ziemii() {
        return czy_na_ziemii;
    }

    public void setCzy_na_ziemii(boolean czy_na_ziemii) {
        this.czy_na_ziemii = czy_na_ziemii;
    }
    public Point getKoordynaty() {
        return koordynaty;
    }

    public void setKoordynaty(Point koordynaty) {
        this.koordynaty = koordynaty;
    }
}
