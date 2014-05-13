package mls;

import mls.utils.*;

/*
 * @author Simone Murzilli
 */
public class Pratica3 {

    public static double[] generaRn(int a , int b, int x0) {
        double[] l = new GeneratoreRn(a, b, x0).generaSequenza();
        Util.printRn(l, a, x0, b, false, false);
        Statistiche.calcolaStatistiche(l, 10.0, false, "#0.0", "#0.0000");
        return l;
    }

    public static double[] generaIntervallo(int a, int b, int x0, double min, double max) {
        double[] l = new GeneratoreUniforme(a, b, x0, min, max).generaSequenza();
        Util.printSequenzaUniforme(l, a, x0, b, min, max, false, false);
        Statistiche.calcolaStatistiche(l, 10.0, false, "#0.0", "#0.0000");
        return l;
    }

    public static double[] generaEsponenziale(int a, int b, int x0, double avg) {
        double[] l = new GeneratoreEsponenziale(a,b, x0, avg).generaSequenza();
        Util.printEsponenziale(l, a, b, avg, x0, false, false);
        Statistiche.calcolaStatistiche(l, 25.0, false, "#0", "#0.0000");
        return l;
    }

    public static double[] generaKErlangiana(int a, int b, long[] x0s, double avg, int k) {
        double[] l = new GeneratoreKErlangiana(a, b, x0s, avg, k).generaSequenza();
        Util.printKErlangiana(l, a, b, k, avg, x0s, false, false);
        Statistiche.calcolaStatistiche(l, 20.0, false, "#0", "#0.0000");
        return l;
    }
}
