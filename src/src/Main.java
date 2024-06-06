package src;

//import src.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int menu_number;
    public static void main(String[] args) {
        Main main = new Main();
        main.main_menu();
    }
    private void main_menu(){
        Scanner usr_input = new Scanner(System.in);
        while(true){
            wyczysc_konsole();
            System.out.println("Menu\n\n1. Losowy\n2. Kreator symulacji\n3. Wyjscie\nProsze wybrac odpowiednia opcje wpisujac odpowiadajacy jej numer: ");
            menu_number = usr_input.nextInt();
            if(menu_number == 1){
                mapa mapa= new mapa();
                mapa.losuj_mape();
                this.symulacja(mapa);
            }
            else if(menu_number == 2){

            }
            else if(menu_number == 3){
                return;
            }
            else {
                System.out.println("Podany zly numer, prosze sprobowac ponownie\n");
            }
        }
    }
    //zwraca 1 jesli wygrali ludzie, 0 jesli wygrali zombie
    private void symulacja(mapa mapa){
        int liczba_tur=0;
        int start_c = mapa.tab_cz.size();
        int start_z = mapa.tab_z.size();
        int start_pl = mapa.tab_pl.size();
        List<Integer> cz_d = new ArrayList<>();
        List<Integer> z_d = new ArrayList<>();
        while(true){
            liczba_tur++;
            cli(mapa);
            cz_d.clear();
            z_d.clear();
            for(int i=0; i<mapa.tab_cz.size(); i++){
                czlowiek czlowiek = mapa.tab_cz.get(i);
                //logika czlowieka + efekty returnow (jakies komunikaty czy cos) !!!!!!!!!!!!!!!!!!!!!!!!!!!!! NIE ZAPOMNIJ O EFEKCIE WODY
                if(czlowiek.tury_spowolnienia>0){
                    czlowiek.tury_spowolnienia--;
                    continue;
                }
                int p=czlowiek.logika_czlowieka(mapa);
                cz_d.add(p);
            }
            for(int i=0; i<mapa.tab_z.size(); i++){
                //logika zombie + efekty returnow (jakies komunikaty czy cos)
                zombie zombie = mapa.tab_z.get(i);
                if(zombie.tury_spowolnienia>0){
                    zombie.tury_spowolnienia--;
                    continue;
                }
                int p = zombie.logika_zombie(mapa);
                z_d.add(p);
            }
            if(mapa.tab_z.isEmpty()){
                cli(mapa);
                return;
            }
            else if(mapa.tab_cz.isEmpty()){
                cli(mapa);
                return;
            }
        }
    }
    //jakas tam metoda do wyswietlania wszystkiego
    private void cli(mapa mapa){
        wyczysc_konsole();
        System.out.print('-');
        for(int i=0; i< mapa.rozmiar; i++){
            System.out.print("--");
        }
        System.out.print("\n");
        for(int i=0; i<mapa.rozmiar; i++) {
            for (int j = 0; j < mapa.rozmiar; j++) {
                System.out.print('|');
                System.out.print(mapa.map[i][j]);
                System.out.print('|');
            }
            System.out.print("\n");
            System.out.print('-');
            for (int j = 0; j < mapa.rozmiar; j++) {
                System.out.print("--");
            }
            System.out.print("\n");
        }
        System.out.print("Legenda:\n' ' - trawa\nw - woda\nb - bron\nz - zombie\nc - czlowiek\np - plot\no - zombie w wodzie\nl - czlowiek w wodzie\nm - zombie i bron\n - czlowiek i bron\ne - pare zombie na jednym polu\nk - pare ludzi na jednym polu");
        System.out.print("\n\nLiczba ludzi na mapie: " + mapa.tab_cz.size()+"\n\nLiczba zombie na mapie: " + mapa.tab_z.size() + "\n\nProszę wcisnąć d aby udać się do dziennika zdarzeń lub ENTER aby przejść do nasępnej tury");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if ("d".equalsIgnoreCase(input)) {
            System.out.println('d');
        }
    }
    private void wyczysc_konsole(){
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }
    private void dziennik_zdarzen(List<Integer> cz_d, List<Integer> z_d){
        wyczysc_konsole();
        System.out.println("Ruchy ludzi:");
        for(int i=0; i<cz_d.size(); i++){
            switch (cz_d.get(i)){
                case 1:
                    System.out.println("Czlowiek zaatakowal zombie");
                    break;
                case 2:
                    System.out.println("Czlowiek ucieka przed zombie");
                    break;
                case 3:
                    System.out.println("Czlowiek ucieka przed zombie");
                    break;
                case 4:
                    System.out.println("Czlowiek wykonal losowy ruch");
                    break;
                case 5:
                    System.out.println("Czlowiek zabil zombie");
                    break;
                case 6:
                    System.out.println("Czlowiek zniszczyl plot");
                    break;
                case 7:
                    System.out.println("Czlowiek poszedl do zombie");
                    break;
                case 8:
                    System.out.println("Czlowiek zaatakowal plot");
                    break;
            }
        }
        System.out.println("Ruchy zombie:");
        for(int i=0; i<z_d.size(); i++){
            switch (z_d.get(i)){
                case 1:
                    System.out.println("Zombie zaatakował człowieka");
                    break;
                case 2:
                    System.out.println("Zombie podszedł do człowieka");
                    break;
                case 3:
                    System.out.println("Zombie poszedl w losowym kierunku");
                    break;
                case 4:
                    System.out.println("Zombie atakuje plot");
                    break;
                case 5:
                    System.out.println("Zombie zabil czlowieka");
                    break;
                case 6:
                    System.out.println("Zombie zniszczyl plot");
                    break;
            }
        }
        System.out.println("\n\nProsze wcisnac ENTER aby przejsc do nastepnej tury");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
    private void koncowa_plansza(int w, mapa mapa, int lt, int sc, int sz, int sp){
        wyczysc_konsole();
        if(w == 0){
            System.out.println("Symulacja zakonczyla sie sukcesem zombie");

        }
        else {
            System.out.println("Symulacja zakonczyla sie sukcesem ludzi");
        }
        System.out.print("\n\nKoncowy wyglad mapy:\n-");
        for(int i=0; i< mapa.rozmiar; i++){
            System.out.print("--");
        }
        System.out.print("\n");
        for(int i=0; i<mapa.rozmiar; i++) {
            for (int j = 0; j < mapa.rozmiar; j++) {
                System.out.print('|');
                System.out.print(mapa.map[i][j]);
                System.out.print('|');
            }
            System.out.print("\n");
            System.out.print('-');
            for (int j = 0; j < mapa.rozmiar; j++) {
                System.out.print("--");
            }
            System.out.print("\n");
        }
        System.out.println("\nStatystyki:\nIle tur trwala symulacja: "+lt+"\nLiczba zombie na koniec symulacji: " + mapa.tab_z.size() + "\nLiczba zombie na poczatku symulacji: " + sz + "\nLiczba ludzi na koniec symulacji: " + mapa.tab_cz.size() + "\nLiczba ludzi na poczatku symulacji: "+ sc);
        System.out.println("Liczba plotkow na koncu symulacji: " + mapa.tab_pl.size() + "\nLiczba plotkow na poczatku symulacji: " + sp + "\nLiczba pol trawy na mapie: "+ mapa.tab_t.size() + "\nLiczba pol wody na mapie: "+ mapa.tab_w.size());
        System.out.println("\n\nProsze wcisnac ENTER aby przejsc do menu i zakonczyc proces symulacji");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}

