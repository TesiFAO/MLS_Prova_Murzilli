package mls.util;

import mls.UtilBackup;

import java.util.List;

/**
 * Created by vortex on 11/05/14.
 */
public class TestChiQuadro {

    /** Variabili statiche usate nel test Chi-Quadro **/
    public static double Z25 = -0.674; // accettabile
    public static double Z75 = 0.674;  // accettabile
    public static double  Z5 = -1.645;
    public static double Z95 = 1.645;
    public static double Z10 = -1.282; //quasi sosteptto P5-P10
    public static double Z90 = 1.282;  //quasi sosteptto P90-95
    public static double Z1 = -2.326; // rigettato
    public static double Z99 = 2.326;  //rigettato

    public static double calcolaV(List<Double> l, double n, double ps) {
        double v = 0.0;
        double valoreArreso = n * ps;
        for (int i = 0 ; i < l.size() ; i++)
            v += Math.pow(l.get(i) - valoreArreso, 2) / valoreArreso;
        return v;
    }

    public static double calcolaChiQuadro(double df, double za) {
        return (df * Math.pow(1.0 - (2.0 / (9.0 * df)) + (za * Math.sqrt(2.0 / (9.0 * df))), 3));
    }

    public static boolean controllaV(double v, double df) {
        double p1 =  calcolaChiQuadro(df, Util.Z1);
        double p5 =  calcolaChiQuadro(df, Util.Z5);
        double p10 = calcolaChiQuadro(df, Util.Z10);
        double p25 = calcolaChiQuadro(df, Util.Z25);
        double p75 = calcolaChiQuadro(df, Util.Z75);
        double p90 = calcolaChiQuadro(df, Util.Z90);
        double p95 = calcolaChiQuadro(df, Util.Z95);
        double p99 = calcolaChiQuadro(df, Util.Z99);

   /*     System.out.println(p1);
        System.out.println(p5);
        System.out.println(p10);
        System.out.println(p25);
        System.out.println(p75);
        System.out.println(p90);
        System.out.println(p95);
        System.out.println(p99);*/

        System.out.print("V=[" + v + "] ");
        if ( v >= p25 && v <= p75 )  {
            System.out.println(p25 + " <= " + v + " <= " + p75 + " Accettabile");
            return true;
        }
        else if ( (v >= p1 && v <= p5) ) {
            System.out.println(p1 + " <= " + v + " <= " + p5 + " Sospetto");
        }
        else if ( (v >= p95 && v <= p99) ) {
            System.out.println(p95 + " <= " + v + " <= " + p99 + " Sospetto");
        }
        else if ( (v >= p5 && v <= p10) ) {
            System.out.println(p5 + " <= " + v + " <= " + p10 + " Quasi Sospetto");
        }
        else if ( (v >= p90 && v <= p95) ) {
            System.out.println(p90 + " <= " + v + " <= " + p95 + " Quasi Sospetto");
        }
        else if ( v > p99 )  {
            System.out.println(v + " > " + p99  + " Rigetto");
        }
        else if ( v < p1 )  {
            System.out.println(v + " < " + p1  + " Rigetto");
        }
        return false;
    }
}
