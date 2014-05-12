package mls.util;

/*
 * @author Simone Murzilli
 */

public class GeneratoreEsponenziale {
    private GeneratoreRn grn;
    private double avg;

    /**
     * @param a   Parametro a del generatore congruente moltiplicativo
     * @param b   Parametro b potenza di m = 2^b
     * @param x0  Seme X0
     * @param avg media
     */
    public GeneratoreEsponenziale(long a, int b, long x0, double avg) {
        this.setGrn(new GeneratoreRn(a, b, x0));
        this.setAvg(avg);
    }

    public double getNext() {
        return (-avg * Math.log(grn.getNext()));
    }

    /**
     * Restituisce una sequenza esponenziale lung 2^b-2
     * @return Restituisce una sequenza esponenziale lung 2^b-2
     */
    public double[] generaSequenza() {
        double[] l = new double[(int) Math.pow(2, grn.getG().getB()- 2)];
        for(int i=0; i < l.length; i++) {
            l[i] = getNext();
        }
        return l;
    }

    public GeneratoreRn getGrn() {
        return grn;
    }
    public void setGrn(GeneratoreRn grn) {
        this.grn = grn;
    }
    public double getAvg() {
        return avg;
    }
    public void setAvg(double avg) {
        this.avg = avg;
    }
}
