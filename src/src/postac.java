package src;

import java.awt.*;

public class postac {
    public int zdrowie;
    public int podstawowe_obrazenia;
    public Point koordynaty;
    public int tury_spowolnienia;

    public int atakuj_plot(Point koor, mapa mapa){
        plot cel = null;
        for(int i=0; i<mapa.tab_pl.size(); i++){
            cel = mapa.tab_pl.get(i);
            if(cel.koordynaty.equals(koor)){
                break;
            }
        }
        cel.zdrowie = cel.zdrowie-this.podstawowe_obrazenia;
        if(cel.zdrowie<=0){
            mapa.map[cel.koordynaty.y][cel.koordynaty.x] = ' ';
            mapa.tab_pl.remove(cel);
            return 2;
        }
        return 1;
    }

    public postac(Point koor, int zdrow, int dmg) {
        this.zdrowie = zdrow;
        this.podstawowe_obrazenia = dmg;
        this.koordynaty = koor;
        this.tury_spowolnienia=0;
    }

    public int getZdrowie() {
        return zdrowie;
    }

    public void setZdrowie(int zdrowie) {
        this.zdrowie = zdrowie;
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
