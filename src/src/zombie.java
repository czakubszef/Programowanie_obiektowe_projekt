package src;

import java.awt.*;
import java.util.Random;

public class zombie extends src.postac {
    /*
    Logika zombie:
    1. Atakuj człowieka
    2. Idź do człowieka
    3. Idź w losowym kierunku (jeśli nie może to w inny)
    4. Jeśli nie ma innego wyboru to oznacza, że otaczają go płoty, więc zniszcz jeden.
     */
    public int logika_zombie(mapa mapa) {
        boolean idz_do_czlowieka = false;
        Point cel_ataku = new Point();
        Point cel_ruchu = new Point();

        for(int i = 0; i<=mapa.tab_cz.size(); i++){
            czlowiek czlowiek = mapa.tab_cz.get(i);
            if(this.koordynaty.distance(czlowiek.getKoordynaty())<=1.4){
                return 1;
            }
            if(this.koordynaty.distance(czlowiek.getKoordynaty())<=4){
                idz_do_czlowieka = true;
                cel_ruchu.setLocation(czlowiek.koordynaty);
            }
        }
        if(idz_do_czlowieka && czy_plot(cel_ruchu, mapa)){
            //zaatakuj plot
            return 4;
        }
        else if(idz_do_czlowieka){
            //idz do czlowieka
            return 2;
        }
        cel_ruchu.setLocation(losowy_ruch(this.koordynaty, mapa.rozmiar));
        if(czy_plot(cel_ruchu, mapa)){
            //zaatakuj plot
            return 4;
        }
        else{
            //idz do punktu
            return 3;
        }
    }
    private Point losowy_ruch(Point punkt, int rozmiar_mapy){
        Random rand = new Random();
        int x = rand.nextInt(3)-1;
        int y = rand.nextInt(3)-1;
        if(punkt.x+x>rozmiar_mapy || punkt.x+x<0){
            x=x*-1;
        }
        if(punkt.y+y>rozmiar_mapy || punkt.y+y<0){
            y=y*-1;
        }
        return new Point(punkt.x+x, punkt.y+y);
    }
    private Point jaki_ruch(Point start, Point cel){
        int kierunek_x = Integer.signum(cel.x - start.x);
        int kierunek_y = Integer.signum(cel.y - start.y);
        return new Point(start.x+kierunek_x, start.y+kierunek_y);
    }
    private boolean czy_plot(Point punkt, mapa mapa){
        for(int i=0; i<mapa.tab_pl.size(); i++){
            plot plot = mapa.tab_pl.get(i);
            if(plot.koordynaty.equals(punkt)){
                return true;
            }
        }
        return false;
    }
    public zombie(Point koordynaty) {
        super(koordynaty, 5, 1, 2);
    }
}
