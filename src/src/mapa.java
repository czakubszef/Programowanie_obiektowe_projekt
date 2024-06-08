package src;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

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
    public void stworz_mape(){
        int input;
        Point koor = new Point();
        int zdrowie;
        int dmg;
        int tury_spow;
        int dmg_br;
        int wytrz;
        int zasieg;
        int czy_uzb;
        for(int i=0; i<this.rozmiar; i++){
            for(int j=0; j<this.rozmiar; j++){
                wyczysc_konsole();
                koor.setLocation(j, i);
                System.out.println("Kreator mapy do symulacji\nAktualny wyglad mapy:");
                for(int k=0; k<this.rozmiar; k++) {
                    for (int l = 0; l < this.rozmiar; l++) {
                        System.out.print('|');
                        System.out.print(this.map[k][l]);
                        System.out.print('|');
                    }
                    System.out.print("\n");
                    for (int l = 0; l < this.rozmiar; l++) {
                        System.out.print("---");
                    }
                    System.out.print("\n");
                }
                System.out.println("1. Czlowiek\n2. Zombie\n3. Plot\n4. Bron lezaca na trawie\n5. Trawa\n6. Woda\nProsze wpisac nr odpowiadający obiektowi jaki chce się postawić na polu x = " + j + "y = " + i + ": ");
                Scanner scanner = new Scanner(System.in);
                input = scanner.nextInt();
                switch (input){
                    case 1:
                       wyczysc_konsole();
                       System.out.print("Prosze wpisac odpowiednie statystyki dla tego czlowieka:\nZdrowie: ");
                       zdrowie = scanner.nextInt();
                       System.out.print("Podstawowe obrazenia: ");
                       dmg = scanner.nextInt();
                       System.out.print("Czy ma na start bron (1 jesli tak, 0 jesli nie : ");
                       czy_uzb = scanner.nextInt();
                       if(czy_uzb==1){
                           System.out.print("Prosze podac statystyki broni trzymanej przez czlowieka:\nObrazenia: ");
                           dmg_br = scanner.nextInt();
                           System.out.print("Wytrzymalosc: ");
                           wytrz = scanner.nextInt();
                           System.out.print("Zasieg: ");
                           zasieg = scanner.nextInt();
                           bron bron = new bron(koor ,false, dmg_br, wytrz, zasieg);
                           czlowiek czlowiek = new czlowiek(koor, zdrowie, dmg, czy_uzb, bron);
                           trawa trawa = new trawa(koor, 1);
                           this.tab_t.add(trawa);
                           this.tab_cz.add(czlowiek);
                           this.tab_br.add(bron);
                       }
                       else{
                           bron bron = new bron(koor, false);
                           czlowiek czlowiek = new czlowiek(koor, zdrowie, dmg, 0, bron);
                           trawa trawa = new trawa(koor, 1);
                           this.tab_t.add(trawa);
                           this.tab_cz.add(czlowiek);
                       }
                       this.map[i][j] = 'c';
                       break;
                    case 2:
                        wyczysc_konsole();
                        System.out.print("Prosze wpisac odpowiednie statystyki dla tego zombie:\nZdrowie: ");
                        zdrowie = scanner.nextInt();
                        System.out.print("Podstawowe obrazenia: ");
                        dmg = scanner.nextInt();
                        zombie zombie = new zombie(koor,zdrowie,dmg);
                        trawa trawa1 = new trawa(koor, 2);
                        this.map[i][j]='z';
                        this.tab_z.add(zombie);
                        this.tab_t.add(trawa1);
                        break;
                    case 3:
                        wyczysc_konsole();
                        System.out.print("Prosze wpisac odpowiednie statystyki dla tego plotu:\n Wytrzymalosc: ");
                        zdrowie = scanner.nextInt();
                        plot plot = new plot(koor, zdrowie);
                        trawa traw2a = new trawa(koor, 4);
                        this.map[i][j] = 'p';
                        this.tab_t.add(traw2a);
                        this.tab_pl.add(plot);
                        break;
                    case 4:
                        wyczysc_konsole();
                        System.out.print("Prosze wpisac odpowiednie statystyki dla tej broni:\nObrazenia: ");
                        dmg_br = scanner.nextInt();
                        System.out.print("Wytrzymalosc: ");
                        wytrz = scanner.nextInt();
                        System.out.print("Zasieg: ");
                        zasieg = scanner.nextInt();
                        bron bron = new bron(koor ,true, dmg_br, wytrz, zasieg);
                        trawa trawa = new trawa(koor, 3);
                        this.map[i][j] = 'b';
                        this.tab_t.add(trawa);
                        this.tab_br.add(bron);
                        break;
                    case 5:
                        wyczysc_konsole();
                        trawa trawa2 = new trawa(koor, 0);
                        this.map[i][j] = ' ';
                        this.tab_t.add(trawa2);
                        break;
                    case 6:
                        wyczysc_konsole();
                        System.out.print("Prosze wpisac odpowiednie statystyki dla tego pola wody:\nTury spowolnienia: ");
                        tury_spow = scanner.nextInt();
                        woda woda = new woda(koor, tury_spow);
                        this.map[i][j] = 'w';
                        this.tab_w.add(woda);
                        break;
                }
            }
        }
        wyczysc_konsole();
        System.out.print("Zakonczono tworzenie mapy, prosze wcisnac ENTER aby przejsc do symulacji");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
    private void wyczysc_konsole(){
        for (int i = 0; i < 100; i++) {
            System.out.println();
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
