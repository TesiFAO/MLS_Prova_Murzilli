package mls.utils;

/*
 * @author Simone Murzilli
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
     * @param l Frequenze
     * @param n Dimensione vettore
     * @param ps Probabilita' teorica
     * @return valore V
     */
    public static double calcolaV(double[] l, double n, double ps) {
        double v = 0.0;
        double nps = n * ps;
        for (int i = 0 ; i < l.length ; i++) {
            v += Math.pow(l[i] - nps, 2) / nps;
        }
        return v;
    }


    /**
     * Calcola frequenze test seriale tornando un array d^2
     * @param l sequenza da analizzare
     * @param serie 0 se (Z0, Z1), 1 se (Z1, Z2) (indice di partenza)
     * @param d valore d
     * @return calcola frequenze test seriale tornando un array d^2
     */
    public static double[] calcolaFrequenzeSeriale(long[] l, int serie, int d) {
        // calcola frequenze usando un array d^2
        double s[] = new double[(int) Math.pow(d,2)];
        for (int i = serie; i < l.length - 1; i+=2) {
            s[(int)l[i]*d + (int)l[i+1]] = s[(int)l[i]*d + (int)l[i+1]] + 1;
        }
        return s;
    }

    /**
     * Calcola l’alpha-percentile
     * @param df gradi di libertà
     * @param za z-alfa
     * @return valore dell’alpha percentile
     */
    public static double calcolaAlphaPercentile(double df, double za) {
        return (df * Math.pow(1.0 - (2.0 / (9.0 * df)) + (za * Math.sqrt(2.0 / (9.0 * df))), 3));
    }

    /**
     * Controlla se il valore V è Accetabile
     * @param v valore V
     * @param df gradi di libertà
     * @return true se è Accetabile, false negli altri casi
     */
    public static boolean classificaV(double v, double df) {
        double p1 =  calcolaAlphaPercentile(df, Z1);
        double p5 =  calcolaAlphaPercentile(df, Z5);
        double p10 = calcolaAlphaPercentile(df, Z10);
        double p25 = calcolaAlphaPercentile(df, Z25);
        double p75 = calcolaAlphaPercentile(df, Z75);
        double p90 = calcolaAlphaPercentile(df, Z90);
        double p95 = calcolaAlphaPercentile(df, Z95);
        double p99 = calcolaAlphaPercentile(df, Z99);

        System.out.print("V=[" + v + "] ");
        if ( v >= p25 && v <= p75 )  { System.out.println(p25 + " <= " + v + " <= " + p75 + " Accettabile"); return true; }
        else if ( (v >= p10 && v <= p25) ) { System.out.println(p10 + " <= " + v + " <= " + p25 + " Accettabile/Quasi Sospetto"); }
        else if ( (v >= p75 && v <= p90) ) { System.out.println(p75 + " <= " + v + " <= " + p90 + " Accettabile/Quasi Sospetto"); }
        else if ( (v >= p5 && v <= p10) ) { System.out.println(p5 + " <= " + v + " <= " + p10 + " Quasi Sospetto"); }
        else if ( (v >= p90 && v <= p95) ) { System.out.println(p90 + " <= " + v + " <= " + p95 + " Quasi Sospetto"); }
        else if ( (v >= p1 && v <= p5) ) { System.out.println(p1 + " <= " + v + " <= " + p5 + " Sospetto"); }
        else if ( (v >= p95 && v <= p99) ) { System.out.println(p95 + " <= " + v + " <= " + p99 + " Sospetto"); }
        else if ( v > p99 )  { System.out.println(v + " > " + p99  + " Rigetto"); }
        else if ( v < p1 )  { System.out.println(v + " < " + p1  + " Rigetto"); }
        else System.out.println(v + " Rigetto");
        return false;
    }
}
