package mls;

import mls.utils.*;

/*
 * @author Simone Murzilli
 */
public class Pratica2 {

    public static double[] generaRn(int a , int b, int x0) {
        double[] l = new GeneratoreRn(a, b, x0).generaSequenza();
        Util.printRn(l, a, x0, b, true, true);
        return l;
    }

    public static double[] generaIntervallo(int a, int b, int x0, double min, double max) {
        double[] l = new GeneratoreUniforme(a, b, x0, min, max).generaSequenza();
        Util.printSequenzaUniforme(l, a, x0, b, min, max, true, true);
        return l;
    }

    public static double[] generaEsponenziale(int a, int b, int x0, double avg) {
        double[] l = new GeneratoreEsponenziale(a,b, x0, avg).generaSequenza();
        Util.printEsponenziale(l, a, b, avg, x0, true, false);
        return l;
    }

    public static double[] generaKErlangiana(int a, int b, long[] x0s, double avg, int k) {
        double[] l = new GeneratoreKErlangiana(a, b, x0s, avg, k).generaSequenza();
        System.out.println();
        Util.printKErlangiana(l, a, b, k, avg, x0s, true, false);
        return l;
    }
}
