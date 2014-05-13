package mls.utils;

/*
 * @author Simone Murzilli
 */


public class GeneratoreCM {
    private long a;
    private int b;
    private long m;
    private long x;

    /**
     * @param a  Parametro a del generatore congruente moltiplicativo
     * @param b  Parametro b potenza di m = 2^b
     * @param x0 Seme X0
     */
    public GeneratoreCM(long a, int b, long x0) {
      this.setA(a);
      this.setB(b);
      this.setM((long) Math.pow(2, b));
      this.setX(x0);
    }

    /**
     * Restituisce valore generato tramite il GCM
     * @return Valore generato tramite il GCM
     */
    public long getNext() {
        return x = (a * x) % m;
    }

    /**
     * Restituisce una sequenza lunga 2^b-2
     * @return Restituisce una sequenza lunga 2^b-2
     */
    public long[] generaSequenza() {
        long[] l = new long[(int) Math.pow(2, this.getB()-2)];
        for(int i=0; i < l.length; i++) {
            l[i] = x;
            x = getNext();
        }
        return l;
    }

    public long getA() {
        return a;
    }

    public void setA(long a) {
        this.a = a;
    }

    public long getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public long getM() {
        return m;
    }

    public void setM(long m) {
        this.m = m;
    }

    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }
}
