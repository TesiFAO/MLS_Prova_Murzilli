package mls;

import mls.utils.GeneratoreCM;
import mls.utils.Util;

import java.util.Arrays;

/*
 * @author Simone Murzilli
 */
public class Pratica1 {

    public static long[] generaSequenza(int a, int b, int x0) {

        // generazione valori del corollario con valori x0=1 e x0=15
        long[] corollarioA1 = Util.generaValoriCorollarioA(b, 1);
        long[] corollarioA2 = Util.generaValoriCorollarioA(b, 15);

        // generazione sequenza
        long[] l = new GeneratoreCM(a, b, x0).generaSequenza();

        // stampa della sequenza generata
        Util.stampaSequenza(l, a, b, x0);

        // ordinamento della sequenza
        Arrays.sort(l);

        // controllo se la sequenza ordinata e’ uguale ad una di quelle generate dal corollario
        controlloSequenza(l, corollarioA1, corollarioA2);

        return l;
    }

    private static void controlloSequenza(long[] l, long[] corollarioA1, long[] corollarioA2) {
        System.out.println(" Sequenza Ordinata = [" + Util.sequenceToString(l)+"]");
        if (Arrays.equals(l, corollarioA1))
            System.out.println("E' contenuta nella lista A1 creata dal corollario\n" + "CorollarioA1 = [" + Util.sequenceToString(corollarioA1)+"]\n");
        else if (Arrays.equals(l, corollarioA2))
            System.out.println("E' contenuta nella lista A2 creata dal corollario\n" + "CorollarioA2 = [" + Util.sequenceToString(corollarioA2)+"]\n");
        else
            System.out.println("Non e' contenuta in una delle liste create dal corollario\n");
    }
}
