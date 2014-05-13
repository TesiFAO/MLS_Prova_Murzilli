package mls;

import mls.util.Statistiche;
import mls.util.TestChiQuadro;
import mls.util.Util;

/*
 * @author Simone Murzilli
 */
public class Pratica4 {

    public static void applicaTest(long a, int b, long x0, double d, int parti) {
        long[][] sequenze = Util.creaSequenze(d, a, x0, b, parti);
        stampaParametri("--Il Test di Uniformita'", a, b, x0, d, parti);
        testUniformita(sequenze, d);
        stampaParametri("--Il Test Seriale", a, b, x0, d, parti);
        testSeriale(sequenze, d);
    }

    /**
     * @param sequenze Sequenze da analizzare
     * @param d parametro d sequenza Zn
     * @return il numero delle sotto-sequenze Accettabili
     */
    public static int testUniformita(long[][] sequenze, double d) {
        int accettabili = 0;
        for (long[] sottoSequenza : sequenze ) {

            // calcolo delle frequenze della sottosequenza
           double[] l = Statistiche.calcolaFrequenze(sottoSequenza);

            // calcolo di V
            double v =  TestChiQuadro.calcolaV(l, sottoSequenza.length, 1 / d);

            // classifica V con d-1 gradi di liberta'
            if( TestChiQuadro.classificaV(v, d - 1)) {
                accettabili++;
            }
        }
        System.out.println("Risulta Accettabile " + accettabili + " volte su " + sequenze.length + "\n");
        return accettabili;
    }


    /**
    * @param sequenze Sequenze da analizzare
    * @param d parametro d sequenza Zn
    * @return il numero delle sotto-sequenze Accettabili
    */
    public static int testSeriale(long[][] sequenze, double d) {
        int accettabili = 0;
        for (long[] sequenza : sequenze ) {
            // sequenza (Z0, Z1)
            System.out.print("Sequenza (Z0, Z1) ");
            accettabili += testSequenzaSeriale(sequenza, 0, (int) d);
            System.out.print("Sequenza (Z1, Z2) ");
            accettabili += testSequenzaSeriale(sequenza, 1, (int) d);
        }
        System.out.println("Risulta Accettabile " + accettabili + " volte su " +  sequenze.length * 2 + "\n");
        return accettabili;
    }

    /**
     * Ritorna 1 se la sequenza e' accettabile e 0 se non lo e'
     * @param sequenza sequenza da analizzare
     * @param index 0 se (Z0, Z1), 1 se (Z1, Z2) (indice di partenza)
     * @param d valore d
     * @return Ritorna 1 se la sequenza e' accettabile e 0 se non lo e'
     */
    private static int testSequenzaSeriale(long[] sequenza, int index, double d) {
        double s[] = TestChiQuadro.calcolaFrequenzeSeriale(sequenza, index, (int) d);
        double v = TestChiQuadro.calcolaV(s,(double) sequenza.length / 2, (double) 1 / Math.pow(d, 2));
        if ( TestChiQuadro.classificaV(v, Math.pow(d, 2)-1)) {
           return 1;
        }
        return 0;
    }

    private static void stampaParametri(String name,long a, int b, long x0, double d, int parti ) {
        System.out.println(name + " dato [a=" + a + "]" + "[x0=" + x0 + "]" + "[b=" + b + "]"+ "[d=" + d + "]"+ "[parti=" + parti+ "]");
    }
}
