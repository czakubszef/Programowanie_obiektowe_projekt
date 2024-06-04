package src;

import java.awt.*;

public class postac {
    public int zdrowie;
    public int ruch_na_ture;
    public int podstawowe_obrazenia;
    public Point koordynaty;

    public void atak() {
    }

    public void ruch() {

    }

    public postac(Point koor, int zdrow, int ruch, int dmg) {
        this.zdrowie = zdrow;
        this.ruch_na_ture = ruch;
        this.podstawowe_obrazenia = dmg;
        this.koordynaty = koor;
    }

    public int getZdrowie() {
        return zdrowie;
    }

    public void setZdrowie(int zdrowie) {
        this.zdrowie = zdrowie;
    }

    public int getRuch_na_ture() {
        return ruch_na_ture;
    }

    public void setRuch_na_ture(int ruch_na_ture) {
        this.ruch_na_ture = ruch_na_ture;
    }

    public int getPodstawowe_obrazenia() {
        return podstawowe_obrazenia;
    }

    public void setPodstawowe_obrazenia(int podstawowe_obrazenia) {
        this.podstawowe_obrazenia = podstawowe_obrazenia;
    }

    public Point getKoordynaty() {
        return koordynaty;
    }

    public void setKoordynaty(Point koordynaty) {
        this.koordynaty = koordynaty;
    }
}
