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
    l - czlowiek w wodzie
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
                        map[j][i] = 'w';
                    } else {
                        koor.setLocation(j, i);
                        if (random_bron <= 1) {
                            bron bron = new bron(koor, true);
                            tab_br.add(bron);
                            map[j][i]='b';
                            trawa trawa = new trawa(koor, 3);
                            tab_t.add(trawa);
                        }
                        else{
                            map[j][i]=' ';
                            trawa trawa = new trawa(koor, 0);
                            tab_t.add(trawa);
                        };
                    }
                } else if (random_postac_pole_obiekt > 8 && random_postac_pole_obiekt <=9) {
                    koor.setLocation(j,i);
                    if(random_zombie_czlowiek <= 5){
                        zombie zombie = new zombie(koor);
                        tab_z.add(zombie);
                        map[j][i]='z';
                        trawa trawa = new trawa(koor,2);
                        tab_t.add(trawa);
                    }
                    else{
                        czlowiek czlowiek = new czlowiek(koor);
                        if(czlowiek.getCzy_uzbrojony()==1){
                            bron bron = new bron(koor, false);
                            czlowiek.setBron(bron);
                            tab_br.add(bron);
                        }
                        tab_cz.add(czlowiek);
                        map[j][i] = 'c';
                        trawa trawa = new trawa(koor,1);
                        tab_t.add(trawa);
                    }
                }
                else{
                    koor.setLocation(j,i);
                    plot plot = new plot(koor);
                    tab_pl.add(plot);
                    map[j][i]='p';
                    trawa trawa = new trawa(koor, 4);
                    tab_t.add(trawa);
                }
            }
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
}
