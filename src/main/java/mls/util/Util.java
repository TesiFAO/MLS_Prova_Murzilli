package mls.util;

import mls.UtilBackup;

import java.text.DecimalFormat;
import java.util.*;

/*
 * @author Simone Murzilli
 */
public class Util {

    /** Variabili statiche usate nel test Chi-Quadro **/
    public static double Z25 = -0.674; // accettabile
    public static double Z75 = 0.674;  // accettabile
    public static double  Z5 = -1.645;
    public static double Z95 = 1.645;
    public static double Z10 = -1.282; //quasi sosteptto P5-P10
    public static double Z90 = 1.282;  //quasi sosteptto P90-95
    public static double Z1 = -2.326; // rigettato
    public static double Z99 = 2.326;  //rigettato

    public static List<Long> generaValoriCorollarioA(int b, int x0) {
        List<Long> l = new ArrayList<Long>();
        int size = (int) Math.pow(2, (b-3)) -1;
        if ( x0 == 1 % 16 || x0 == 3 % 16 || x0 == 9 % 16 || x0 == 11 % 16) {
            for (int v = 0; v <= size; v++) {
                l.add((long) (8 * v) + 1);
                l.add((long) (8 * v) + 3);
            }
        }
        if ( x0 == 5 % 16 || x0 == 7 % 16 || x0 == 13 % 16 || x0 == 15 % 16) {
            for (int v = 0; v <= size; v++) {
                l.add((long) (8 * v) + 5);
                l.add((long) (8 * v) + 7);
            }
        }
        return l;
    }

    public static String printRn(List<Double> l, long a, long x0, long b, boolean serie, boolean controllo) {
        String s = "--Sequenza Rn dato [a=" + a + "]" + "[x0=" + x0 + "]"+ "[b=" + b + "]";
        if ( serie)
            s += "\n" + l;
        if  (controllo ) {
            boolean c = Statistiche.controllaSequenzaRn(l);
            s += "\nLa sequenza e' compresa tra [0,1) [" + c + "]\n";
        }
        System.out.println(s);
        return s;
    }

    public static String printSequenzaUniforme(List<Double> l, long a, long x0, long b, double min, double max, boolean serie, boolean controllo) {
        String s = "--Sequenza uniforme in ("+ min + "," + max +") dato [a=" + a + "]" + "[x0=" + x0 + "]" + "[b="+ b +"]";
        if ( serie)
            s += "\n" + l;
        if  (controllo ) {
            boolean c = Statistiche.controllaSequenza(l, min, max);
            s += "\nLa sequenza e' compresa tra (" + min + "," + max + ") [" + c + "]\n";
        }
        System.out.println(s);
        return s;
    }

    public static String printEsponenziale(List<Double> l, long a, long b, double avg, long x0, boolean serie, boolean media) {
        String s = "--Sequenza Esponenziale di media "+ avg +" dato [a=" + a + "]" + "[x0="+ x0 +"]" + "[b="+ b +"]";
        if ( serie)
            s += "\n" + l;
        if  (media )
            s += "\nMedia: " + Statistiche.calcolaMedia(l) + "\n";
        System.out.println(s);
        return s;
    }

    public static String printKErlangiana(List<Double> l, long a, long b, int k, double avg, long[] xos, boolean serie, boolean media) {
        String s = "--Sequenza "+ k +"-Erlangiana di media "+ avg +" dato [a=" + a + "]" + "[b=" + b +"]" + "[k=" + k +"][X0s="+ Util.print(xos) +"]";
        if ( serie)
            s += "\n" + l;
        if  (media )
            s += "\nMedia: " + Statistiche.calcolaMedia(l) + "\n";
        System.out.println(s);
        return s;
    }

    public static String print(long[] xos) {
        String s = "";
        for(int i = 0; i < xos.length; i++) {
            s += xos[i];
            if ( i < xos.length -1)
                s += ",";
        }
        return s;
    }




}
