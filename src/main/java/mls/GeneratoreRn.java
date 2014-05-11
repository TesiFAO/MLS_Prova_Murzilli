package mls;

import java.util.ArrayList;
import java.util.List;

/*
 * @author Simone Murzilli
 */

public class GeneratoreRn {

    private GeneratoreCongruenteMolt g;

    public GeneratoreRn(long a, long b, long x0) {
       this.setG(new GeneratoreCongruenteMolt(a, b, x0));
    }

    public double getNext() {
        double v = (double) g.getX() / g.getM();
        g.getNext();
        return v;
    }

    public List<Double> generaSequenza() {
        int size = (int) Math.pow(2, g.getB()- 2);
        List<Double> l = new ArrayList<Double>(size);
        double v = getNext();
        while( l.size() < size) {
            l.add(v);
            v = getNext();
        }
        return l;
    }

    public List<Long> generaSequenzaZn(double d) {
        int size = (int) Math.pow(2, g.getB()- 2);
        List<Long> l = new ArrayList<Long>(size);
        double v = getNext() * d;
        while( l.size() < size) {
            l.add((long) Math.floor(v));
            v = getNext()* d;
        }
        return l;
    }


    public static void main(String args[]) {
        GeneratoreRn g = new GeneratoreRn(3, 12, 3);
        System.out.println(g.generaSequenza());
    }


    public GeneratoreCongruenteMolt getG() {
        return g;
    }

    public void setG(GeneratoreCongruenteMolt g) {
        this.g = g;
    }

}
