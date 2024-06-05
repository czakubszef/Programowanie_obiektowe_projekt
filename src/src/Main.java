package src;

//import src.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    static int menu_number;
    public static void main(String[] args) {
        mapa mapa = new mapa();
        mapa.losuj_mape();
        int rozmiar = mapa.getRozmiar();
        for(int i=0; i<rozmiar; i++){
            for (int j=0; j<rozmiar; j++){
                System.out.print(mapa.map[i][j]);
                System.out.print(' ');
            }
            System.out.println();
        }
    }
    public static void main_menu(){
        System.out.println("Menu\n\n1. Losowy\n2. Kreator symulacji\n3. Wyjscie  Prosze wybrac odpowiednia opcje wpisujac odpowiadajacy jej numer: ");
        Scanner usr_input = new Scanner(System.in);
        while(true){
            menu_number = usr_input.nextInt();
            if(menu_number == 1){

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
    public int symulacja(mapa mapa){
        while(true){
            for(int i=0; i<=mapa.tab_cz.size(); i++){
                czlowiek czlowiek = mapa.tab_cz.get(i);
                //logika czlowieka + efekty returnow (jakies komunikaty czy cos) !!!!!!!!!!!!!!!!!!!!!!!!!!!!! NIE ZAPOMNIJ O EFEKCIE WODY
                if(czlowiek.tury_spowolnienia>0){
                    czlowiek.tury_spowolnienia--;
                    continue;
                }
                int p=czlowiek.logika_czlowieka(mapa);
                switch (p){
                    case 1:
                        //komunikat czlowiek zaatakowal zombie
                        break;
                    case 2:
                        //komunikat czlowiek ucieka przed zombie
                        break;
                    case 3:
                        //komunikat czlowiek poszedl po bron
                        break;
                    case 4:
                        //komunikat czlowiek wykonal losowy ruch
                        break;
                    case 5:
                        //komunikat czlowiek zabil zombie
                        break;
                    case 6:
                        //komunikat czlowiek zniszczyl plot
                        break;
                    case 7:
                        //komunikat czlowiek poszedl do zombie
                        break;
                    case 8:
                        //komunikat czlowiek zaatakowal plot
                        break;
                }
            }
            for(int i=0; i<=mapa.tab_z.size(); i++){
                //logika zombie + efekty returnow (jakies komunikaty czy cos)
                zombie zombie = mapa.tab_z.get(i);
                if(zombie.tury_spowolnienia>0){
                    zombie.tury_spowolnienia--;
                    continue;
                }
                int p = zombie.logika_zombie(mapa);
                switch (p){
                    case 1:
                        //komunikat zombie zaatakował człowieka
                        break;
                    case 2:
                        //komunikat zombie podszedł do człowieka
                        break;
                    case 3:
                        //komunikat zombie poszedl w losowym kierunku
                        break;
                    case 4:
                        //komunikat zombie atakuje plot
                        break;
                    case 5:
                        //komunikat zombie zabil czlowieka
                        break;
                    case 6:
                        //komunikat zombie zniszczyl plot
                        break;
                }
            }
            if(mapa.tab_z.isEmpty()){
                return 1;
            }
            else if(mapa.tab_cz.isEmpty()){
                return 0;
            }
        }
    }
    //jakas tam metoda do wyswietlania wszystkiego
    public void cli(mapa mapa){
        wyczysc_konsole();
        System.out.print('-');
        for(int i=0; i< mapa.rozmiar; i++){
            System.out.print("--");
        }
        System.out.print("\n|");
        for(int i=0; i<mapa.rozmiar; i++){
            for(int j=0; j<mapa.rozmiar; j++){
                System.out.print(mapa.map[i][j]+'|');
            }
            System.out.print("\n|");
        }
        System.out.print('-');
        for(int i=0; i< mapa.rozmiar; i++){
            System.out.print("--");
        }
        System.out.print("\n\nLiczba ludzi na mapie: " + mapa.tab_cz.size()+"\n\nLiczba zombie na mapie: " + mapa.tab_z.size() + "Proszę wcisnąć d aby udać się do dziennika zdarzeń lub ENTER aby przejść do nasępnej tury");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if ("d".equalsIgnoreCase(input)) {
            System.out.println('d');
        }
    }
    public void wyczysc_konsole(){
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }
}

