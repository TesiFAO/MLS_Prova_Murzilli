package mls;

import java.util.List;

/**
 * Created by vortex on 5/6/14.
 */
public class Pratica3Backup {

    private int a;
    private int x0;
    private int m;
    private int min;
    private int max;
    private double avg;
    private int k;
    private int[] xos;

    public Pratica3Backup(int a, int x0, int m) {
        this.setA(a);
        this.setX0(x0);
        this.setM(m);
    }

    public Pratica3Backup(int a, int x0, int m, int min, int max) {
        this.setA(a);
        this.setX0(x0);
        this.setM(m);
        this.setMin(min);
        this.setMax(max);
    }

    public Pratica3Backup(int a, int m, double avg, int k, int[] xos) {
        this.setA(a);
        this.setM(m);
        this.setAvg(avg);
        this.setK(k);
        this.setXos(xos);
    }

    public Pratica3Backup(int a, int x0, int m, double avg) {
        this.setA(a);
        this.setX0(x0);
        this.setM(m);
        this.setAvg(avg);
    }

    public List<Double> generaRn() {
        List<Double> l = UtilBackup.generaRn(this.getA(), this.getX0(), this.getM());
        UtilBackup.printRn(l, this.getA(), this.getX0(), false, false);
        UtilBackup.calcolaStatistiche(l, 10.0);
        return l;
    }

    public List<Double> generaIntervallo() {
        List<Double> l = UtilBackup.generaIntervallo(this.getA(), this.getX0(), this.getM(), this.getMin(), this.getMax());
        UtilBackup.printSequenzaUniforme(l, this.getA(), this.getX0(), this.getMin(), this.getMax(), false, false);
        UtilBackup.calcolaStatistiche(l, 10.0);
        return l;
    }

    public List<Double> generaEsponenziale() {
        List<Double> l = UtilBackup.generaEsponenziale(this.getA(), this.getX0(), this.getM(), this.getAvg());
        UtilBackup.printEsponenziale(l, this.getA(), this.getAvg(), this.getX0(), false, false);
        UtilBackup.calcolaStatistiche(l, 25.0);
        return l;
    }

    public List<Double> generaKErlangiana() {
        List<Double> l = UtilBackup.generaKErlangiana(this.getA(), this.getM(), this.getK(), this.getAvg(), this.getXos());
        UtilBackup.printKErlangiana(l, this.getA(), this.getK(), this.getAvg(), this.getXos(), false, false);
        UtilBackup.calcolaStatistiche(l, 20.0);
        return l;
    }


    public static void main(String args[]) {
        int a = 3;
        int b = 12;
        int m = (int) Math.pow(2, b);
        int x0 = 1;
        int min = 60;
        int max = 80;
        double avg = 30.0;
        int k = 3;

        new Pratica3Backup(a, x0, m, min, max).generaRn();
        //System.out.println();

        List<Double> range = new Pratica3Backup(a, x0, m, min, max).generaIntervallo();
        //System.out.println();

        List<Double> sequenceExp = new Pratica3Backup(a, x0, m, avg).generaEsponenziale();
        //System.out.println();

        int[] xos = new int[]{5,9,67};
        List<Double> erl = new Pratica3Backup(a, m, avg, k, xos).generaKErlangiana();
        //System.out.println();
    }


    /**
     *
     * Rn
     * occ:http://jsfiddle.net/v0rtex83/AFn5k/2/
     * fr: http://jsfiddle.net/v0rtex83/x2pZ2/3/
     * dp: http://jsfiddle.net/v0rtex83/kaka2/4/
     * cum: http://jsfiddle.net/v0rtex83/zw4GK/2/
     *
     * Range
     * http://jsfiddle.net/v0rtex83/R5XCq/4/
     * http://jsfiddle.net/v0rtex83/QfXMd/3/
     * http://jsfiddle.net/v0rtex83/8JbGU/2/
     * http://jsfiddle.net/v0rtex83/9sZhv/2/
     *
     *
     * Esponenziale:
     * http://jsfiddle.net/v0rtex83/2ZNEK/1/
     * http://jsfiddle.net/v0rtex83/Z4dJV/1/
     * http://jsfiddle.net/v0rtex83/29XSC/1/
     * http://jsfiddle.net/v0rtex83/kEB6W/1/
     *
     * Erlangiana:
     * http://jsfiddle.net/v0rtex83/a2hAe/2/
     * http://jsfiddle.net/v0rtex83/8dAfc/1s/
     * http://jsfiddle.net/v0rtex83/WDEqw/1/
     * http://jsfiddle.net/v0rtex83/H33NW/1/
     *
     */

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getX0() {
        return x0;
    }

    public void setX0(int x0) {
        this.x0 = x0;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public int[] getXos() {
        return xos;
    }

    public void setXos(int[] xos) {
        this.xos = xos;
    }


}
