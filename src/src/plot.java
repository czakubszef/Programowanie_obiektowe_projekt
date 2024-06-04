package src;

import java.awt.*;

public class plot {
    int zdrowie;
    Point koordynaty;
    public plot(Point koor){
        this.koordynaty=koor;
        this.zdrowie=5;
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
