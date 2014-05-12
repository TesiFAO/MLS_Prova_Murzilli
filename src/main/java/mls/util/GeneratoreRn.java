package mls.util;

/*
 * @author Simone Murzilli
 */

public class GeneratoreRn {

    private GeneratoreCM g;

    /**
     * Generatore Rn
     *
     * @param a
     * @param b
     * @param x0
     */
    public GeneratoreRn(long a, long b, long x0) {
       this.setG(new GeneratoreCM(a, b, x0));
    }

    // genera il prossimo valore usando il GeneratoreCM dividendolo per il modulo
    public double getNext() {
        double v = (double) g.getX() / g.getM();
        g.getNext(); // crea nuovo x0 in GeneratoreCM
        return v;
    }

    // genera la sequenza in base ai parametri del generatore
    public double[] generaSequenza() {
        int size = (int) Math.pow(2, g.getB()- 2);
        double[] l = new double[size];
        for(int i=0; i < l.length; i++) {
            l[i] = getNext();
        }
        return l;
    }

    // genera la sequenza Zn in base ai parametri del generatore e dimensione della sequenza
    public long[] generaSequenzaZn(double d, int size) {
        long[] l = new long[size];
        for(int i=0; i < l.length; i++) {
            l[i] = (long) Math.floor(getNext() * d);
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
