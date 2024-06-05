package src;

import java.awt.*;
import java.util.Random;
public class czlowiek extends src.postac {
    /*
    0 - brak broni
    1 - bron
     */
    int czy_uzbrojony;
    bron bron;
    /*
    Logika czlowieka:
    1. Jesli masz bron atakuj zombie
    2. Jesli nie masz broni uciekaj przed zombie
    3. Jesli nie masz broni i nie ma zombie w okolicy i znajduje sie bron idz po bron
    4. Jesli masz bron i nie ma zombie  poruszaj sie w losowym kierunku (jesli napotkasz plot na drodze zniszcz, jesli napotkasz bron to sprawdz czy lepsza, jesli tak to wez)
    5. Zabito zombie
    6. Zniszczono plot
    7. jesli masz bron ale zombie nie w zasiegu idz do zombie
    8. atakuj plot
     */
    public int logika_czlowieka(mapa mapa){
        Point cel_ruchu = null;
        for(int i=0; i<=mapa.tab_z.size(); i++){
            zombie zom = mapa.tab_z.get(i);
            if(zom.koordynaty.distance(this.koordynaty)<=5){
                if(this.czy_uzbrojony==1){
                    if(zom.koordynaty.distance((this.koordynaty))<=this.bron.zasieg){
                        int p = atakuj_zombie(zom, mapa);
                        if(p==1){
                            return 5;
                        }
                        else{
                            return 1;
                        }
                    }
                    else{
                        cel_ruchu.setLocation(jaki_ruch(this.koordynaty, zom.koordynaty));
                        if(czy_plot(cel_ruchu,mapa)){
                            int p = atakuj_plot(cel_ruchu, mapa);
                            if(p==2){
                                return 6;
                            }
                            return 8;
                        }
                        else{
                            ruch(cel_ruchu,mapa);
                            return 7;

                        }
                    }
                }
                else{
                    cel_ruchu.setLocation(uciekaj_przed_zombie(zom.koordynaty, mapa.rozmiar));
                    if (czy_plot(cel_ruchu, mapa)){
                        int p = atakuj_plot(cel_ruchu, mapa);
                        if(p==2){
                            return 6;
                        }
                        return 8;
                    }
                    else{
                        ruch(cel_ruchu, mapa);
                        return 2;
                    }
                }
            }
        }
        for(int i=0; i<=mapa.tab_br.size(); i++){
            bron br = mapa.tab_br.get(i);
            if(this.czy_uzbrojony==0 && br.czy_na_ziemii && br.koordynaty.distance(this.koordynaty)<=5){
                cel_ruchu = jaki_ruch(this.koordynaty, br.koordynaty);
                if (czy_plot(cel_ruchu, mapa)){
                    int p = atakuj_plot(cel_ruchu, mapa);
                    if(p==2){
                        return 6;
                    }
                    return 8;
                }
                else{
                    ruch(cel_ruchu, mapa);
                    return 3;
                }
            }
        }
        cel_ruchu = losowy_ruch(this.koordynaty, mapa.rozmiar);
        if (czy_plot(cel_ruchu, mapa)){
            int p = atakuj_plot(cel_ruchu, mapa);
            if(p==2){
                return 6;
            }
            return 8;
        }
        else{
            ruch(cel_ruchu, mapa);
            return 4;
        }
    }
    public int atakuj_zombie(zombie zom, mapa mapa){
        zom.zdrowie=zom.zdrowie-this.bron.obrazenia;
        this.bron.wytrzymalosc--;
        if(this.bron.wytrzymalosc<=0){
            mapa.tab_br.remove(this.bron);
            this.bron=null;
        }
        if(zom.zdrowie<=0){
            if(mapa.map[zom.koordynaty.y][zom.koordynaty.x]=='o'){
                mapa.map[zom.koordynaty.y][zom.koordynaty.x]='w';
            }
            else if(mapa.map[zom.koordynaty.y][zom.koordynaty.x]=='m'){
                mapa.map[zom.koordynaty.y][zom.koordynaty.x]='b';
            }
            else{
                mapa.map[zom.koordynaty.y][zom.koordynaty.x]=' ';
            }
            mapa.tab_z.remove(zom);
            return 1;
        }
        return 0;
    }
    public void ruch(Point koor, mapa mapa){
        if(mapa.map[this.koordynaty.y][this.koordynaty.x] == 'l'){
            mapa.map[this.koordynaty.y][this.koordynaty.x] = 'w';
        }
        else if(mapa.map[this.koordynaty.y][this.koordynaty.x] == 'i'){
            mapa.map[this.koordynaty.y][this.koordynaty.x] = 'b';
        }
        else{
            mapa.map[this.koordynaty.y][this.koordynaty.x] = ' ';
        }
        if(mapa.map[koor.y][koor.x] == 'w'){
            woda wod = null;
            for(int i=0; i<=mapa.tab_w.size(); i++){
                wod = mapa.tab_w.get(i);
                if(wod.koordynaty.equals(koor)){
                    break;
                }
            }
            this.tury_spowolnienia = wod.tury_spowolnienia;
            mapa.map[koor.y][koor.x] = 'l';
        }
        else if(mapa.map[koor.y][koor.x] == 'b'){
            if(this.czy_uzbrojony==0){
                mapa.map[koor.y][koor.x] = 'c';
                wez_bron(mapa, koor);
            }
            else{
                mapa.map[koor.y][koor.x] = 'i';
                if(czy_lepsza_bron(koor, mapa)){
                    zamien_bron(mapa, koor);
                }
            }
        }
        else{
            mapa.map[koor.y][koor.x] = 'c';
        }
        this.koordynaty = koor;
        this.bron.koordynaty = koor;
    }
    private boolean czy_lepsza_bron(Point koor, mapa mapa){
        double br_1 = (this.bron.obrazenia+this.bron.zasieg+this.bron.wytrzymalosc)/3;
        bron br = null;
        double br_2 = 0;
        for(int i=0; i<=mapa.tab_br.size(); i++){
            br = mapa.tab_br.get(i);
            if(br.koordynaty.equals(koor) && br != this.bron){
                br_2 = (br.obrazenia + br.zasieg + br.wytrzymalosc)/3;
            }
        }
        if(br_2>=br_1){
            return true;
        }
        return false;
    }
    private void zamien_bron(mapa mapa, Point koor){
        for(int i=0; i<=mapa.tab_br.size(); i++){
            bron br = mapa.tab_br.get(i);
            if(br.koordynaty.equals(koor) && br != this.bron){
                this.bron.czy_na_ziemii=true;
                setBron(br);
                this.bron.czy_na_ziemii=false;
            }
        }
    }
    private void wez_bron(mapa mapa, Point koor){
        for(int i=0; i<=mapa.tab_br.size(); i++){
            bron br = mapa.tab_br.get(i);
            if(br.koordynaty.equals(koor)){
                setBron(br);
                this.bron.czy_na_ziemii=false;
            }
        }
    }
    private Point uciekaj_przed_zombie(Point zom, int rozmiar_mapy){
        int kierunek_x = Integer.signum(zom.x - this.koordynaty.x);
        int kierunek_y = Integer.signum(zom.y - this.koordynaty.y);
        if(this.koordynaty.x+kierunek_x*-1<0 || this.koordynaty.x+kierunek_x*-1>=rozmiar_mapy){
            if(this.koordynaty.y+kierunek_y*-1<0 || this.koordynaty.y+kierunek_y*-1>=rozmiar_mapy){
                return new Point(this.koordynaty.x+kierunek_x, this.koordynaty.y+kierunek_y);
            }
            else{
                return new Point(this.koordynaty.x+kierunek_x, this.koordynaty.y+kierunek_y*-1);
            }
        }
        else{
            if(this.koordynaty.y+kierunek_y*-1<0 || this.koordynaty.y+kierunek_y*-1>=rozmiar_mapy){
                return new Point(this.koordynaty.x+kierunek_x*-1, this.koordynaty.y+kierunek_y);
            }
            else {
                return new Point(this.koordynaty.x + kierunek_x * -1, this.koordynaty.y + kierunek_y * -1);
            }
        }
    }








    // Konstruktory, gettery i settery
    public czlowiek(Point koor){
        super(koor, 10, 2);
        Random rand = new Random();
        int random = rand.nextInt(10);
        if(random <=2){
            this.czy_uzbrojony = 1;
        }
        else{
            this.czy_uzbrojony=0;
        }
        random = rand.nextInt(5);
        this.czy_uzbrojony = random+1;
    }


    public int getCzy_uzbrojony() {
        return czy_uzbrojony;
    }

    public void setCzy_uzbrojony(int bron) {
        this.czy_uzbrojony = bron;
    }


    public src.bron getBron() {
        return bron;
    }

    public void setBron(src.bron bron) {
        this.bron = bron;
    }
}
