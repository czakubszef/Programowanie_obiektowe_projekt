package src;

import java.awt.*;

public class woda extends src.pole {
    int tury_spowolnienia;

    public int getTury_spowolnienia() {
        return tury_spowolnienia;
    }

    public void setTury_spowolnienia(int tury_spowolnienia) {
        this.tury_spowolnienia = tury_spowolnienia;
    }

    public woda(Point koor){
        super(koor);
        this.tury_spowolnienia=1;
    }
    public woda(Point koor, int ts){
        super(koor);
        this.tury_spowolnienia=ts;
    }
}
