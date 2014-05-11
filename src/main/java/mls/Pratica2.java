package mls;

import mls.util.*;

import java.util.List;

/*
 * @author Simone Murzilli
 */
public class Pratica2 {

    private long a;
    private long x0;
    private long b;
    private long m;
    private double min;
    private double max;
    private double avg;
    private int k;
    private long[] xos;

    public Pratica2(int a, int x0, int b) {
        this.setA(a);
        this.setX0(x0);
        this.setB(b);
        this.setM((long) Math.pow(2, b));
    }
    public Pratica2(int a, int x0, int b, int min, int max) {
        this.setA(a);
        this.setX0(x0);
        this.setB(b);
        this.setM((long) Math.pow(2, b));
        this.setMin(min);
        this.setMax(max);
    }
    public Pratica2(int a, int b, double avg, int k, long[] xos) {
        this.setA(a);
        this.setB(b);
        this.setM((long) Math.pow(2, b));
        this.setAvg(avg);
        this.setK(k);
        this.setXos(xos);
    }
    public Pratica2(int a, int x0, int b, double avg) {
        this.setA(a);
        this.setX0(x0);
        this.setB(b);
        this.setM((long) Math.pow(2, b));
        this.setAvg(avg);
    }

    public double[] generaRn() {
        double[] l = new GeneratoreRn(this.getA(), this.getB(), this.getX0()).generaSequenza();
        Util.printRn(l, this.getA(), this.getX0(), this.getB(), true, true);
        return l;
    }

    public double[] generaIntervallo() {
        double[] l = new GeneratoreUniforme(this.getA(), this.getB(), this.getX0(), this.getMin(), this.getMax()).generaSequenza();
        Util.printSequenzaUniforme(l, this.getA(), this.getX0(), this.getB(), this.getMin(), this.getMax(), true, true);
        return l;
    }

    public double[] generaEsponenziale() {
        double[] l = new GeneratoreEsponenziale(this.getA(), this.getB(), this.getX0(), this.getAvg()).generaSequenza();
        Util.printEsponenziale(l, this.getA(), this.getB(), this.getAvg(), this.getX0(), true, true);
        return l;
    }

    public double[] generaKErlangiana() {
        double[] l = new GeneratoreKErlangiana(this.getA(), this.getB(), this.getXos(), this.getAvg(), this.getK()).generaSequenza();
        Util.printKErlangiana(l, this.getA(), this.getB(), this.getK(), this.getAvg(), this.getXos(), true, true);
        return l;
    }

    public long getA() {
        return a;
    }

    public void setA(long a) {
        this.a = a;
    }

    public long getX0() {
        return x0;
    }

    public void setX0(long x0) {
        this.x0 = x0;
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

    public void setMax(long max) {
        this.max = max;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public long[] getXos() {
        return xos;
    }

    public void setXos(long[] xos) {
        this.xos = xos;
    }
}
