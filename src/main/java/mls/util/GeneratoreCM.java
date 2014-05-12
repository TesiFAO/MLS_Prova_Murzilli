package mls.util;

/*
 * @author Simone Murzilli
 */


public class GeneratoreCM {
    private long a;
    private long b;
    private long m;
    private long x;

    /**
     * Generatore Congruente oltiplicativo
     *
     * @param a
     * @param b
     * @param x0
     */
    public GeneratoreCM(long a, long b, long x0) {
      this.setA(a);
      this.setB(b);
      this.setM((long) Math.pow(2, b));
      this.setX(x0);
    }

    // genera il prossimo valore della sequenza
    public long getNext() {
        return x = (a * x) % m;
    }

    // genera la sequenza in base ai parametri del generatore
    public long[] generaSequenza() {
        long[] l = new long[(int) Math.pow(2, this.getB() - 2)];
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

    public void setB(long b) {
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
