package mls.utils;

/*
 * @author Simone Murzilli
 */
public class Util {

    /**
     * Genera una lista di valori dal corollario in base a = 3 (mod 8)
     * @param b  Parametro b potenza di m = 2^b
     * @param x0 Seme x0
     * @return Sequenza di valori dal corollario a seconda del seme x0 e b
     */
    public static long[] generaValoriCorollarioA(int b, int x0) {
        long[] l = new long[(int) Math.pow(2, b-2)];
        int size = (int) Math.pow(2, (b-3)) -1;
        if ( x0 == 1 % 16 || x0 == 3 % 16 || x0 == 9 % 16 || x0 == 11 % 16) {
            int index = 0;
            for (int v = 0; v <= size; v++) {
                l[index]   = (long) (8 * v) + 1;
                l[index+1] = (long) (8 * v) + 3;
                index+=2;
            }
        }
        if ( x0 == 5 % 16 || x0 == 7 % 16 || x0 == 13 % 16 || x0 == 15 % 16) {
            int index = 0;
            for (int v = 0; v <= size; v++) {
                l[index]   = (long) (8 * v) + 5;
                l[index+1] = (long) (8 * v) + 7;
                index+=2;
            }
        }
        return l;
    }

    public static void stampaSequenza(long[] l, int a, int b, int x0) {
        System.out.println("--La sequenza creata dato [a=" + a + "]" + "[x0=" + x0 + "]" + "[b=" + b + "] \n Sequenza = ["  + sequenceToString(l) + "]");
    }

    public static long[][] creaSequenze(double d, long a, long x0, int b, int parti ) {
        int dimensioneSequenze = (int) Math.pow(2, b - 2) / parti;
        int resto = (int) Math.pow(2, b - 2) % parti;
        long[][] sequenze = new long[parti][dimensioneSequenze];
        GeneratoreRn grn = new GeneratoreRn(a, b, x0);
        // genera n-sequenze Zn
        for(int i=0; i < parti; i++) {
            if ( i < parti - 1)
                sequenze[i] = grn.generaSequenzaZn(d, dimensioneSequenze);
            else
                sequenze[i] = grn.generaSequenzaZn(d, dimensioneSequenze + resto);
        }
        return sequenze;
    }

    /**
     * Utility di stampa
     * @param l
     * @param a
     * @param x0
     * @param b
     * @param serie
     * @param controllo
     * @return
     */
    public static String printRn(double[] l, long a, long x0, long b, boolean serie, boolean controllo) {
        String s = "--Sequenza <Rn> dato [a=" + a + "]" + "[x0=" + x0 + "]"+ "[b=" + b + "]";
        if ( serie)
            s += "\nSequenza = [" + sequenceToString(l) + "]";
        if  (controllo ) {
            boolean c = controllaSequenzaRn(l);
            s += "\nLa sequenza e' compresa tra [0,1) [" + c + "]\n";
        }
        System.out.println(s);
        return s;
    }

    public static String printSequenzaUniforme(double[] l, long a, long x0, long b, double min, double max, boolean serie, boolean controllo) {
        String s = "--Sequenza Uniforme in ("+ min + "," + max +") dato [a=" + a + "]" + "[x0=" + x0 + "]" + "[b="+ b +"]";
        if ( serie)
            s += "\nSequenza = [" + sequenceToString(l) + "]";
        if  (controllo ) {
            boolean c = controllaSequenza(l, min, max);
            s += "\nLa sequenza e' compresa tra (" + min + "," + max + ") [" + c + "]\n";
        }
        System.out.println(s);
        return s;
    }

    public static String printEsponenziale(double[] l, long a, long b, double avg, long x0, boolean serie, boolean media) {
        String s = "--Sequenza Esponenziale di media "+ avg +" dato [a=" + a + "]" + "[x0="+ x0 +"]" + "[b="+ b +"]";
        if ( serie)
            s += "\nSequenza = [" + sequenceToString(l) + "]";
        if  (media )
            s += "\nMedia: " + Statistiche.calcolaMedia(l) + "\n";
        System.out.println(s);
        return s;
    }

    public static String printKErlangiana(double[] l, long a, long b, int k, double avg, long[] xos, boolean serie, boolean media) {
        String s = "--Sequenza "+ k +"-Erlangiana di media "+ avg +" dato [a=" + a + "]" + "[b=" + b +"]" + "[k=" + k +"][X0s="+ print(xos) +"]";
        if ( serie)
            s += "\nSequenza = [" + sequenceToString(l) + "]";
        if  (media )
            s += "\nMedia: " + Statistiche.calcolaMedia(l) + "\n";
        System.out.println(s);
        return s;
    }

    public static String print(long[] xos) {
        String s = "[";
        for(int i = 0; i < xos.length; i++) {
            s += xos[i];
            if ( i < xos.length -1)
                s += ",";
        }
        s += "]";
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
    public static boolean controllaSequenza(double[] l, double min, double max) {
        for (double v : l) {
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
    public static boolean controllaSequenzaRn(double[] l) {
        for (double v : l) {
            if( v < 0 || v >= 1)
                return false;
        }
        return true;
    }

    public static String sequenceToString(double[] l) {
        String s = "";
        for(int i=0; i < l.length; i ++) {
            s += l[i];
            if ( i < l.length -1 )
                s += ",";
        }
        return s;
    }

    public static String sequenceToString(long[] l) {
        String s = "";
        for(int i=0; i < l.length; i++) {
            s += l[i];
            if ( i < l.length -1 )
                s += ",";
        }
        return s;
    }

    public static String sequenceToString(int[] l) {
        String s = "";
        for(int i=0; i < l.length; i++) {
            s += l[i];
            if ( i < l.length -1 )
                s += ",";
        }
        return s;
    }
}
