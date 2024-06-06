package src;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class mapa {
    int rozmiar;
    public char[][] map;
    public List<czlowiek> tab_cz = new ArrayList<>();
    public List<bron> tab_br = new ArrayList<>();
    public List<plot> tab_pl = new ArrayList<>();
    public List<zombie> tab_z = new ArrayList<>();
    public List<trawa> tab_t = new ArrayList<>();
    public List<woda> tab_w = new ArrayList<>();

    public mapa() {
        this.rozmiar = 10;
        map = new char[rozmiar][rozmiar];
    }

    public mapa(int x) {
        this.rozmiar = x;
        map = new char[x][x];
    }

    /*
    Oznaczenia pól i przedmiotów
    ' ' - trawa
    w - woda
    b - bron
    z - zombie
    c - czlowiek
    p - plot
    o - zombie w wodzie
    l - czlowiek w wodzieF
    m - zombie i bron
    i - czlowiek i bron
    e - pare zombie na jednym polu
    k - pare ludzi na jednym polu
     */
    public void losuj_mape() {
        Point koor = new Point();
        for (int i = 0; i < this.rozmiar; i++) {
            for (int j = 0; j < this.rozmiar; j++) {
                Random rand = new Random();
                int random_postac_pole_obiekt = rand.nextInt(10);
                int random_woda_trawa = rand.nextInt(10);
                int random_bron = rand.nextInt(10);
                int random_zombie_czlowiek = rand.nextInt(10);
                if (random_postac_pole_obiekt <= 8) {
                    if (random_woda_trawa <= 2) {
                        koor.setLocation(j, i);
                        woda woda = new woda(koor);
                        tab_w.add(woda);
                        map[i][j] = 'w';
                    } else {
                        koor.setLocation(j, i);
                        if (random_bron <= 1) {
                            bron bron = new bron(koor, true);
                            tab_br.add(bron);
                            map[i][j]='b';
                            trawa trawa = new trawa(koor, 3);
                            tab_t.add(trawa);
                        }
                        else{
                            map[i][j]=' ';
                            trawa trawa = new trawa(koor, 0);
                            tab_t.add(trawa);
                        };
                    }
                } else if (random_postac_pole_obiekt > 8 && random_postac_pole_obiekt <=9) {
                    koor.setLocation(j,i);
                    if(random_zombie_czlowiek <= 5){
                        zombie zombie = new zombie(koor);
                        tab_z.add(zombie);
                        map[i][j]='z';
                        trawa trawa = new trawa(koor,2);
                        tab_t.add(trawa);
                    }
                    else{
                        czlowiek czlowiek = new czlowiek(koor);
                        if(czlowiek.getCzy_uzbrojony()==1){
                            bron bron = new bron(koor, false);
                            czlowiek.bron = bron;
                            tab_br.add(bron);
                        }
                        tab_cz.add(czlowiek);
                        map[i][j] = 'c';
                        trawa trawa = new trawa(koor,1);
                        tab_t.add(trawa);
                    }
                }
                else{
                    koor.setLocation(j,i);
                    plot plot = new plot(koor);
                    tab_pl.add(plot);
                    map[i][j]='p';
                    trawa trawa = new trawa(koor, 4);
                    tab_t.add(trawa);
                }
            }
        }
    }
    public void stworz_mape(mapa mapa){
        int input;
        for(int i=0; i<getRozmiar(); i++){

        }
    }






    public int getRozmiar() {
        return rozmiar;
    }

    public void setRozmiar(int rozmiar) {
        this.rozmiar = rozmiar;
    }

    public char[][] getMap() {
        return map;
    }

    public void setMap(char[][] map) {
        this.map = map;
    }

    public List<czlowiek> getTab_cz() {
        return tab_cz;
    }

    public void setTab_cz(List<czlowiek> tab_cz) {
        this.tab_cz = tab_cz;
    }

    public List<bron> getTab_br() {
        return tab_br;
    }

    public void setTab_br(List<bron> tab_br) {
        this.tab_br = tab_br;
    }

    public List<plot> getTab_pl() {
        return tab_pl;
    }

    public void setTab_pl(List<plot> tab_pl) {
        this.tab_pl = tab_pl;
    }

    public List<zombie> getTab_z() {
        return tab_z;
    }

    public void setTab_z(List<zombie> tab_z) {
        this.tab_z = tab_z;
    }

    public List<trawa> getTab_t() {
        return tab_t;
    }

    public void setTab_t(List<trawa> tab_t) {
        this.tab_t = tab_t;
    }

    public List<woda> getTab_w() {
        return tab_w;
    }

    public void setTab_w(List<woda> tab_w) {
        this.tab_w = tab_w;
    }
}
