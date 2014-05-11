package mls.util;

import java.util.ArrayList;
import java.util.List;

/*
 * @author Simone Murzilli
 */

public class GeneratoreEsponenziale {
    private GeneratoreRn grn;
    private double avg;

    public GeneratoreEsponenziale(long a, long b, long x0, double avg) {
        this.setGrn(new GeneratoreRn(a, b, x0));
        this.setAvg(avg);
    }

    // genera il valore successivo della sequenza
    public double getNext() {
        return (-avg * Math.log(grn.getNext()));
    }

    // genera la sequenza in base ai parametri del generatore
    public double[] generaSequenza() {
        int size = (int) Math.pow(2, grn.getG().getB()- 2);
        double[] l = new double[size];
        double r = getNext();
        for(int i=0; i < l.length; i++) {
            l[i] = r;
            r = getNext();
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
