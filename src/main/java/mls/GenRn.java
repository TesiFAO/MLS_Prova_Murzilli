package mls;

/**
 * Created by vortex on 5/9/14.
 */
public class GenRn {

    private GenInteriUniforme g;
    private double r;

    public GenRn(GenInteriUniforme gen) {
        g = gen;
    }

    public double getNext() {
        r = (double) g.getX() / g.getM();
        g.getNext();
        return r;
    }



    public GenInteriUniforme getG() {
        return g;
    }

    public void setG(GenInteriUniforme g) {
        this.g = g;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }
}
