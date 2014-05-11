package mls.util;

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
    public double[] generaSequenza() {
        int size = (int) Math.pow(2, g.getB()- 2);
        double[] l = new double[size];
        double r = getNext();
        for(int i=0; i < l.length; i++) {
            l[i] = r;
            r = getNext();
        }
        return l;
    }

    // genera la sequenza Zn in base ai parametri del generatore
    public long[] generaSequenzaZn(double d) {
        int size = (int) Math.pow(2, g.getB()- 2);
        long[] l = new long[size];
        double r = getNext() * d;
        for(int i=0; i < l.length; i++) {
            l[i] = (long) Math.floor(r);
            r = getNext() * d;
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
