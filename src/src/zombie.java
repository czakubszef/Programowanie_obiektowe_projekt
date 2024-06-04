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
    5. Zabito człowieka
    6. Zniszczono plot
     */
    public int logika_zombie(mapa mapa) {
        boolean idz_do_czlowieka = false;
        Point cel_ataku = new Point();
        Point cel_ruchu = new Point();

        for(int i = 0; i<=mapa.tab_cz.size(); i++){
            czlowiek czlowiek = mapa.tab_cz.get(i);
            if(this.koordynaty.distance(czlowiek.getKoordynaty())<=1.4){
                cel_ataku.setLocation(czlowiek.koordynaty);
                int p = atakuj_czlowieka(czlowiek, mapa);
                if(p == 1){
                    return 1;
                }
                else{
                    return 5;
                }
            }
            if(this.koordynaty.distance(czlowiek.getKoordynaty())<=5){
                idz_do_czlowieka = true;
                cel_ruchu.setLocation(czlowiek.koordynaty);
            }
        }
        if(idz_do_czlowieka && czy_plot(cel_ruchu, mapa)){
            int p = atakuj_plot(cel_ruchu, mapa);
            if(p==2){
                return 6;
            }
            return 4;
        }
        else if(idz_do_czlowieka){
            ruch(cel_ruchu,mapa);
            return 2;
        }
        cel_ruchu.setLocation(losowy_ruch(this.koordynaty, mapa.rozmiar));
        if(czy_plot(cel_ruchu, mapa)){
            int p = atakuj_plot(cel_ruchu, mapa);
            if(p==2){
                return 6;
            }
            return 4;
        }
        else{
            ruch(cel_ruchu,mapa);
            return 3;
        }
    }
    public void ruch(Point koor, mapa mapa){
        if(mapa.map[this.koordynaty.y][this.koordynaty.x] == 'o'){
            mapa.map[this.koordynaty.y][this.koordynaty.x] = 'w';
        }
        else if(mapa.map[this.koordynaty.y][this.koordynaty.x] == 'm'){
            mapa.map[this.koordynaty.y][this.koordynaty.x] = 'b';
        }
        else{
            mapa.map[this.koordynaty.y][this.koordynaty.x] = ' ';
        }
        if(mapa.map[koor.y][koor.x] == 'w'){
            woda wod = null;
            for(int i=0; i<mapa.tab_w.size(); i++){
                wod = mapa.tab_w.get(i);
                if(wod.koordynaty.equals(koor)){
                    break;
                }
            }
            this.tury_spowolnienia = wod.tury_spowolnienia;
            mapa.map[koor.y][koor.x] = 'o';
        }
        else if(mapa.map[koor.y][koor.x] == 'b'){
            mapa.map[koor.y][koor.x] = 'm';
        }
        else{
            mapa.map[koor.y][koor.x] = 'z';
        }
        this.koordynaty.equals(koor);
    }
    public int atakuj_czlowieka(czlowiek cel, mapa mapa){
        cel.zdrowie= cel.zdrowie-this.podstawowe_obrazenia;
        if(cel.zdrowie<=0){
            if(mapa.map[cel.koordynaty.y][cel.koordynaty.x]=='l'){
                mapa.map[cel.koordynaty.y][cel.koordynaty.x]='w';
            }
            else{
                mapa.map[cel.koordynaty.y][cel.koordynaty.x]=' ';
            }
            mapa.tab_cz.remove(cel);
            return 2;
        }
        return 1;
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
        super(koordynaty, 5, 2);
    }
}
