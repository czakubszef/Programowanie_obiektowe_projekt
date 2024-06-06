package src;

import java.awt.*;

public class plot {
    int zdrowie;
    Point koordynaty;
    public plot(Point koor){
        this.koordynaty=new Point();
        this.koordynaty.setLocation(koor);
        this.zdrowie=5;
    }
    public plot(Point koor, int zdr){
        this.koordynaty=koor;
        this.zdrowie=zdr;
    }

    public int getZdrowie() {
        return zdrowie;
    }

    public void setZdrowie(int zdrowie) {
        this.zdrowie = zdrowie;
    }

    public Point getKoordynaty() {
        return koordynaty;
    }

    public void setKoordynaty(Point koordynaty) {
        this.koordynaty = koordynaty;
    }
}
