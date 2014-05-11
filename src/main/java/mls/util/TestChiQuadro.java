package mls.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vortex on 11/05/14.
 */
public class TestChiQuadro {

    /** Variabili statiche usate nel test Chi-Quadro **/
    public static double Z25 = -0.674;
    public static double Z75 = 0.674;
    public static double  Z5 = -1.645;
    public static double Z95 = 1.645;
    public static double Z10 = -1.282;
    public static double Z90 = 1.282;
    public static double Z1 = -2.326;
    public static double Z99 = 2.326;

    /**
     * Calcola il valore V della sequenza
     *
     * @param l
     * @param n
     * @param ps
     * @return valore V
     */
    public static double calcolaV(List<Double> l, double n, double ps) {
        double v = 0.0;
        double nps = n * ps;
        for (int i = 0 ; i < l.size() ; i++)
            v += Math.pow(l.get(i) - nps, 2) / nps;
        return v;
    }

    /**
     * Calcola V per il test Seriale
     *
     * @param l sequenza da analizzare
     * @param serie 0 se (Z0, Z1), 1 se (Z1, Z2) (indice di partenza)
     * @param d valore d
     * @return valore V
     */
    public static double calcolaVSeriale(List<Long> l, int serie, int d) {
        int[][] matrix = new int[d][d];

        // calcolo matrice delle frequenze
        for (int i = serie; i < l.size() - 1; i+=2) {
            matrix[l.get(i).intValue()][l.get(i + 1).intValue()] = matrix[l.get(i).intValue()][l.get(i + 1).intValue()] + 1;
        }

        // calcolo array d^2 dalla matrice delle frequenze da passare al metodo per il calcolo di V
        List<Double> s = new ArrayList<Double>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                s.add((double)(matrix[i][j]));
            }
        }

        return TestChiQuadro.calcolaV(s, (double) l.size() / 2, (double) 1 / Math.pow(d, 2));
    }

    /**
     * Calcola il Percentile
     *
     * @param df gradi di libertà
     * @param za z-alfa
     * @return valore del percentile
     */
    public static double calcolaChiQuadro(double df, double za) {
        return (df * Math.pow(1.0 - (2.0 / (9.0 * df)) + (za * Math.sqrt(2.0 / (9.0 * df))), 3));
    }

    /**
     * Controlla se il valore V è Accetabile
     *
     * @param v valore V
     * @param df gradi di libertà
     * @return true se è Accetabile, false negli altri casi
     */
    public static boolean classificaV(double v, double df) {
        double p1 =  calcolaChiQuadro(df, Z1);
        double p5 =  calcolaChiQuadro(df, Z5);
        double p10 = calcolaChiQuadro(df, Z10);
        double p25 = calcolaChiQuadro(df, Z25);
        double p75 = calcolaChiQuadro(df, Z75);
        double p90 = calcolaChiQuadro(df, Z90);
        double p95 = calcolaChiQuadro(df, Z95);
        double p99 = calcolaChiQuadro(df, Z99);

        System.out.print("V=[" + v + "] ");
        if ( v >= p25 && v <= p75 )  { System.out.println(p25 + " <= " + v + " <= " + p75 + " Accettabile"); return true; }
        else if ( (v >= p1 && v <= p5) ) { System.out.println(p1 + " <= " + v + " <= " + p5 + " Sospetto"); }
        else if ( (v >= p95 && v <= p99) ) { System.out.println(p95 + " <= " + v + " <= " + p99 + " Sospetto"); }
        else if ( (v >= p5 && v <= p10) ) { System.out.println(p5 + " <= " + v + " <= " + p10 + " Quasi Sospetto"); }
        else if ( (v >= p90 && v <= p95) ) { System.out.println(p90 + " <= " + v + " <= " + p95 + " Quasi Sospetto"); }
        else if ( v > p99 )  { System.out.println(v + " > " + p99  + " Rigetto"); }
        else if ( v < p1 )  { System.out.println(v + " < " + p1  + " Rigetto"); }
        return false;
    }
}
