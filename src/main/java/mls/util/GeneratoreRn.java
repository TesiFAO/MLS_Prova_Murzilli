package mls.util;

import java.util.ArrayList;
import java.util.List;

/*
 * @author Simone Murzilli
 */

public class GeneratoreRn {

    private GeneratoreCM g;

    public GeneratoreRn(long a, long b, long x0) {
       this.setG(new GeneratoreCM(a, b, x0));
    }

    // genera il prossimo valore dividendolo per il modulo
    public double getNext() {
        double v = (double) g.getX() / g.getM();
        g.getNext();
        return v;
    }

    // genera la sequenza in base ai parametri del generatore
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

    // genera la sequenza Zn in base ai parametri del generatore
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

    public GeneratoreCM getG() {
        return g;
    }

    public void setG(GeneratoreCM g) {
        this.g = g;
    }

}
