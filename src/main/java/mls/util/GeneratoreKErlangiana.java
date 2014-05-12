package mls.util;

import java.util.ArrayList;
import java.util.List;

/*
 * @author Simone Murzilli
 */

public class GeneratoreKErlangiana {
    private List<GeneratoreRn> grns;
    private double avg;
    private double k;
    private long[] xos;
    private long b;

    /**
     *
     * Generatore K-Erl
     *
     * @param a
     * @param b
     * @param x0s
     * @param avg
     * @param k
     */
    public GeneratoreKErlangiana(long a, long b, long[] x0s, double avg, double k) {
        this.setGrns(new ArrayList<GeneratoreRn>((int) k));
        for(int i=0; i < k; i++) {
            addGn(new GeneratoreRn(a, b, x0s[i]));
        }
        this.setAvg(avg);
        this.setK(k);
        this.setXos(x0s);
        this.setB(b);
    }

    // genera la sequenza in base ai parametri del generatore
    public double[] generaSequenza() {
        double[] l = new double[(int) Math.pow(2, b-2)];
        double avgk = -avg / k;
        for(int i=0; i < l.length; i++) {
            double sumlog = 0.0;
            for (int j = 0; j < k; j++) {
                sumlog += Math.log(getGrns().get(j).getNext());
            }
            l[i] = (avgk) * sumlog;
        }
        return l;
    }

    // aggiunge k-esimo generatore
    private void addGn(GeneratoreRn grn) {
       this.getGrns().add(grn);
    }

    public List<GeneratoreRn> getGrns() {
        return grns;
    }

    public void setGrns(List<GeneratoreRn> grns) {
        this.grns = grns;
    }

    public long[] getXos() {
        return xos;
    }

    public void setXos(long[] xos) {
        this.xos = xos;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public double getK() {
        return k;
    }

    public void setK(double k) {
        this.k = k;
    }

    public long getB() {
        return b;
    }

    public void setB(long b) {
        this.b = b;
    }
}
