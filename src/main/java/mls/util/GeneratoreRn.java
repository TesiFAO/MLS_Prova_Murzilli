package mls.util;

/*
 * @author Simone Murzilli
 */

public class GeneratoreRn {

    private GeneratoreCM g;

    /**
     * @param a  Parametro a del generatore congruente moltiplicativo
     * @param b  Parametro b potenza di m = 2^b
     * @param x0 Seme X0
     */
    public GeneratoreRn(long a, int b, long x0) {
       this.setG(new GeneratoreCM(a, b, x0));
    }

    /**
     * Restituisce valore generato tra [0,1)
     * @return Valore generato tra [0,1)
     */
    public double getNext() {
        double v = (double) g.getX() / g.getM();
        g.getNext(); // genera nuovo x
        return v;
    }

    /**
     * Restituisce una sequenza lunga 2^b-2 tra [0,1)
     * @return Restituisce una sequenza lunga 2^b-2 tra [0,1)
     */
    public double[] generaSequenza() {
        double[] l = new double[(int) Math.pow(2, g.getB()-2)];
        for(int i=0; i < l.length; i++) {
            l[i] = getNext();
        }
        return l;
    }

    /**
     * Restituisce una sequenza Zn lunga size
     * @return Restituisce una sequenza Zn lunga size
     */
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
