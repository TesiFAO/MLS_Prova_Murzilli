package mls;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vortex on 5/9/14.
 */
public class GeneratoreRn {

    private GeneratoreInteriUniforme g;

    public GeneratoreRn(long a, long b, long x0) {
       this.setG(new GeneratoreInteriUniforme(a, b, x0));
    }

    public double getNext() {
        double v = (double) g.getX() / g.getM();
        g.getNext();
        return v;
    }

    public List<Double> generaSequenza() {
        List<Double> l = new ArrayList<Double>((int) Math.pow(2, g.getB()- 2));
        double v = getNext();
        while (!l.contains(v)) {
            l.add(v);
            v = getNext();
        }
        return l;
    }


    public static void main(String args[]) {
        GeneratoreRn g = new GeneratoreRn(3, 12, 3);
        System.out.println(g.generaSequenza());
    }


    public GeneratoreInteriUniforme getG() {
        return g;
    }

    public void setG(GeneratoreInteriUniforme g) {
        this.g = g;
    }

}
