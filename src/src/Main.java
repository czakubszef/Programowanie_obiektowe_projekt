package src;

//import src.*;

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
    public void symulacja(mapa mapa){
        while(true){
            for(int i=0; i<=mapa.tab_cz.size(); i++){
                czlowiek czlowiek = mapa.tab_cz.get(i);
                czlowiek.logika_czlowieka();

            }
            for(int i=0; i<=mapa.tab_z.size(); i++){

            }
        }
    }
}
