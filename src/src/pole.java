package src;

import java.awt.*;

public class pole {
    Point koordynaty;
    public pole(Point koor){
        this.koordynaty=new Point();
        this.koordynaty.setLocation(koor);
    }
    public Point getKoordynaty() {
        return koordynaty;
    }

    public void setKoordynaty(Point koordynaty) {
        this.koordynaty = koordynaty;
    }
}
