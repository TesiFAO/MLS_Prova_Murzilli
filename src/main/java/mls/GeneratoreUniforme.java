package mls;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vortex on 5/9/14.
 */
public class GeneratoreUniforme {
    private GeneratoreRn grn;
    private double min;
    private double max;


    public GeneratoreUniforme(long a, long b, long x0, double min, double max) {
        this.setGrn(new GeneratoreRn(a, b, x0));
        this.setMin(min);
        this.setMax(max);
    }

    public double getNext() {
        return min + ((max-min) * (grn.getNext()));
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
