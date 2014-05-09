package mls;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;

/**
 * Created by vortex on 5/9/14.
 */
public class Util {

    public static List<Long> generaValoriCorollarioA(int b, int x0) {
        List<Long> l = new ArrayList<Long>();
        int size = (int) Math.pow(2, (b-3)) -1;
        if ( x0 == 1 % 16 || x0 == 3 % 16 || x0 == 9 % 16 || x0 == 11 % 16) {
            for (int v = 0; v <= size; v++) {
                l.add((long) (8 * v) + 1);
                l.add((long)(8 * v) + 3);
            }
        }
        if ( x0 == 5 % 16 || x0 == 7 % 16 || x0 == 13 % 16 || x0 == 15 % 16) {
            for (int v = 0; v <= size; v++) {
                l.add((long)(8 * v) + 5);
                l.add((long) (8 * v) + 7);
            }
        }
        return l;
    }

    public static String printRn(List<Double> l, long a, long x0, boolean serie, boolean controllo) {
        String s = "--Sequenza Rn [a=" + a + "]" + "[x0=" + x0 + "]";
        if ( serie)
            s += "\n" + l + "\n";
        if  (controllo ) {
            boolean c = Util.controllaSequenzaRn(l);
            s += "La sequenza e' compresa tra [0,1) [" + c + "]\n";
        }
        System.out.println(s);
        return s;
    }

    public static String printSequenzaUniforme(List<Double> l, long a, long x0, double min, double max, boolean serie, boolean controllo) {
        String s = "--Sequenza uniforme in ("+ min + "," + max +") [a=" + a + "]" + "[x0=" + x0 + "]";
        if ( serie)
            s += "\n" + l + "\n";
        if  (controllo ) {
            boolean c = Util.controllaSequenza(l, min, max);
            s += "La sequenza e' compresa tra (" + min + "," + max + ") [" + c + "]\n";
        }
        System.out.println(s);
        return s;
    }

    public static String printEsponenziale(List<Double> l, long a, double avg, long x0, boolean serie, boolean media) {
        String s = "--Sequenza Esponenziale di media "+ avg +" [a=" + a + "]" + "[x0="+ x0 +"]";
        if ( serie)
            s += "\n" + l + "\n";
        if  (media )
            s += "Media: " + Util.calcolaMedia(l) + "\n";
        System.out.println(s);
        return s;
    }

    public static String printKErlangiana(List<Double> l, long a, int k, double avg, long[] xos, boolean serie, boolean media) {
        String s = "--Sequenza "+ k +"-Erlangiana di media "+ avg +" [a=" + a + "]" + "[k=" + k +"] [X0s="+ Util.print(xos) +"]";
        if ( serie)
            s += "\n" + l + "\n";
        if  (media )
            s += "Media: " + Util.calcolaMedia(l) + "\n";
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

    public static boolean controllaSequenza(List<Double> l, double min, double max) {
        for (Double v : l) {
            if( v <= min || v >= max)
                return false;
        }
        return true;
    }

    public static boolean controllaSequenzaRn(List<Double> l) {
        for (Double v : l) {
            if( v < 0 || v >= 1)
                return false;
        }
        return true;
    }

    public static double calcolaMedia(List<Double> l) {
        double somma = 0.0;
        for (Double v : l) {
            somma += v;
        }
        return (somma / l.size());
    }

    public static void calcolaStatistiche(List<Double> l, double intervalli) {
        double min = Collections.min(l);
        double max = Collections.max(l);
        SortedMap<Double, Integer> numeroOccorrenze = UtilBackup.numeroOsservazioni(l, intervalli, min, max);
        SortedMap<Double, Double> frequenzaRelativa = UtilBackup.frequezaRelativa(numeroOccorrenze, l.size());
        SortedMap<Double, Double> densitaProbabilita = UtilBackup.densitaProbabilita(frequenzaRelativa, intervalli, min, max);
        List<Double> cumulata = UtilBackup.calcolaCumulata(frequenzaRelativa);
        System.out.println("Occorrenze: " + numeroOccorrenze.values());
        System.out.println("Frequenza Relativa: " + frequenzaRelativa.values());
        System.out.println("Densita' di probabilita': " + densitaProbabilita.values());
        System.out.println("Cumulata: " + cumulata);
        //printHighcharts(numeroOccorrenze, min);
        //printHighcharts(frequezaRelativa, min);
        //printHighcharts(densitaProbabilita, min);
        //System.out.println("Cumulata: " + printHighchartsCumulata(cumulata));
        double media =  UtilBackup.calcolaMedia(l);
        double varianza =  UtilBackup.calcolaVarianza(l, media);
        System.out.println("Media: " + media);
        System.out.println("Varianza: " + varianza + "\n");
    }

}
