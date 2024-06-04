package src;

import java.awt.*;

public class trawa extends src.pole {
    /*
    0 - nic
    1 - czlowiek
    2 - zombie
    3 - bron
    4 - plot
     */
    int co_lezy;
    public trawa(Point koor, int lezy){
        super(koor);
        this.co_lezy=lezy;
    }

    public int getCo_lezy() {
        return co_lezy;
    }

    public void setCo_lezy(int co_lezy) {
        this.co_lezy = co_lezy;
    }
}
