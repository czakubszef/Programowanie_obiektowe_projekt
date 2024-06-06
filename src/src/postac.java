package src;

import java.awt.*;
import java.util.Random;

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
    public Point losowy_ruch(Point punkt, int rozmiar_mapy){
        Random rand = new Random();
        int x = rand.nextInt(3)-1;
        int y = rand.nextInt(3)-1;
        if(punkt.x+x>=rozmiar_mapy || punkt.x+x<0){
            x=x*-1;
        }
        if(punkt.y+y>=rozmiar_mapy || punkt.y+y<0){
            y=y*-1;
        }
        return new Point(punkt.x+x, punkt.y+y);
    }
    public Point jaki_ruch(Point start, Point cel){
        int kierunek_x = Integer.signum(cel.x - start.x);
        int kierunek_y = Integer.signum(cel.y - start.y);
        return new Point(start.x+kierunek_x, start.y+kierunek_y);
    }
    public boolean czy_plot(Point punkt, mapa mapa){
        for(int i=0; i<mapa.tab_pl.size(); i++){
            plot plot = mapa.tab_pl.get(i);
            if(plot.koordynaty.equals(punkt)){
                return true;
            }
        }
        return false;
    }








    public postac(Point koor, int zdrow, int dmg) {
        this.zdrowie = zdrow;
        this.podstawowe_obrazenia = dmg;
        this.koordynaty = new Point();
        this.koordynaty.setLocation(koor);
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

    public int getTury_spowolnienia() {
        return tury_spowolnienia;
    }

    public void setTury_spowolnienia(int tury_spowolnienia) {
        this.tury_spowolnienia = tury_spowolnienia;
    }
}
