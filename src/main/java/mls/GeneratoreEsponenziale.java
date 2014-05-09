package mls;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vortex on 5/9/14.
 */
public class GeneratoreEsponenziale {
    private GeneratoreRn grn;
    private double avg;

    public GeneratoreEsponenziale(long a, long b, long x0, double avg) {
        this.setGrn(new GeneratoreRn(a, b, x0));
        this.setAvg(avg);
    }

    public double getNext() {
        return (-avg * Math.log(grn.getNext()));
    }

    public List<Double> generaSequenza() {
        System.out.println(grn.getG());
        List<Double> l = new ArrayList<Double>((int) Math.pow(2, grn.getG().getB()- 2));
        double v = getNext();
        while (!l.contains(v)) {
            l.add(v);
            v = getNext();
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
