package mls.util;

/*
 * @author Simone Murzilli
 */

public class GeneratoreUniforme {
    private GeneratoreRn grn;
    private double min;
    private double max;

    /**
     * @param a   Parametro a del generatore congruente moltiplicativo
     * @param b   Parametro b potenza di m = 2^b
     * @param x0  Seme X0
     * @param min minimo
     * @param max massimo
     */
    public GeneratoreUniforme(long a, int b, long x0, double min, double max) {
        this.setGrn(new GeneratoreRn(a, b, x0));
        this.setMin(min);
        this.setMax(max);
    }

    /**
     * Restituisce valore generato tra (min,max)
     * @return Valore generato tra (min,max)
     */
    public double getNext() {
        return min + (grn.getNext() * (max-min));
    }

    /**
     * Restituisce una sequenza lunga 2^b-2 tra (min,max)
     * @return Restituisce una sequenza lunga 2^b-2 tra (min,max)
     */
    public double[] generaSequenza() {
        double[] l = new double[(int) Math.pow(2, grn.getG().getB()-2)];
        for(int i=0; i < l.length; i++) {
            l[i] = getNext();
        }
        return l;
    }

    public GeneratoreRn getGrn(GeneratoreRn grn) {
        return grn;
    }

    public void setGrn(GeneratoreRn g) {
        this.grn = g;
    }

    public GeneratoreRn getGrn() {
        return grn;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }
}
