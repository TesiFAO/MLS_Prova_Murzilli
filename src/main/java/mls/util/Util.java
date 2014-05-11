package mls.util;

import java.util.*;

/*
 * @author Simone Murzilli
 */
public class Util {


    /**
     * Genera una lista di valori dal corollario
     *
     * @param b
     * @param x0
     * @return lista di valori dal corollario a seconda del seme x0 e b
     */
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

    public static String stampaSequenza(List<Long> l, int a, int b, int x0) {
        return "--La sequenza creata dato [a=" + a + "]" + "[x0=" + x0 + "]" + "[b=" + b + "] \n"  + l;
    }

    public static List<List<Long>> creaSequenze(double d, long a, long x0, long b, int parti ) {
        List<Long> zn = new GeneratoreRn(a, b, x0).generaSequenzaZn(d);
        List<List<Long>> sequenze = new ArrayList<List<Long>>();
        double dimensioneSequenza = zn.size() / parti;
        int index = 0;
        // vengono create le sotto-sequenze dalla sequenza zn
        for(int i=0; i < parti; i++) {
            if ( i < parti - 1)
                sequenze.add(zn.subList(index, index + (int) dimensioneSequenza));
            else
                sequenze.add(zn.subList(index, zn.size()));
            index += dimensioneSequenza;
        }
        return sequenze;
    }

    /**
     * Utility di stampa
     *
     * @param l
     * @param a
     * @param x0
     * @param b
     * @param serie
     * @param controllo
     * @return
     */
    public static String printRn(List<Double> l, long a, long x0, long b, boolean serie, boolean controllo) {
        String s = "--Sequenza Rn dato [a=" + a + "]" + "[x0=" + x0 + "]"+ "[b=" + b + "]";
        if ( serie)
            s += "\n" + l;
        if  (controllo ) {
            boolean c = controllaSequenzaRn(l);
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
            boolean c = controllaSequenza(l, min, max);
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

    /**
     * controlla se la sequenza è min tra max e min
     *
     * @param l sequenza da controllare
     * @param min minimo
     * @param max massimo
     * @return booleano di controllo
     */
    public static boolean controllaSequenza(List<Double> l, double min, double max) {
        for (Double v : l) {
            if( v <= min || v >= max)
                return false;
        }
        return true;
    }

    /**
     * controlla se la sequenza è tra [0,1)
     *
     * @param l sequenza da controllare
     * @return booleano di controllo
     */
    public static boolean controllaSequenzaRn(List<Double> l) {
        for (Double v : l) {
            if( v < 0 || v >= 1)
                return false;
        }
        return true;
    }

}
