package mls;

import mls.util.Statistiche;
import mls.util.TestChiQuadro;
import mls.util.Util;

import java.util.ArrayList;
import java.util.List;

/*
 * @author Simone Murzilli
 */
public class Pratica4 {

    private long a;
    private long x0;
    private int b;
    private double d;
    private int parti;

    public Pratica4(long a, long x0, int b, double d, int parti) {
        this.setA(a);
        this.setX0(x0);
        this.setD(d);
        this.setParti(parti);
        this.setB(b);
    }


    public void applicaTest() {
        List<List<Long>> sequenze = Util.creaSequenze(this.getD(), this.getA(), this.getX0(), this.getB(), this.getParti());
        int successiUniformita = testUniformita(sequenze);
        int successiSeriale = testSeriale(sequenze);
        //if ( su >= 0 && ss >= 1)
        //    System.out.println("\nDAJE SEMPRE!!!!!!! " + su + " | "  + ss + " | "  + this.getA() + " | " + this.getX0());

        /*if ( su >= 2 )
            System.out.println(su  + " | "  + ss + " | "  + this.getA() + " | " + this.getX0());

        if ( ss >= 3 )
            System.out.println(su  + " | "  + ss + " | "  + this.getA() + " | " + this.getX0());*/

        if( successiUniformita >= 2 && successiSeriale >= 3)
            System.out.println("\nDAJE SEMPRE!!!!!!! " + successiUniformita + " | "  + successiSeriale + " | "  + this.getA() + " | " + this.getX0());

    }

    /**
     * @param sequenze le sequenze da analizzare
     * @return il numero delle sotto-sequenze Accettabili
     */
    public Integer testUniformita(List<List<Long>> sequenze) {

        // stampa parametri
        stampaParametri("--Il Test di Uniformita'");

        int accettabili = 0;
        for (List<Long> sottoSequenza : sequenze ) {

            // calcolo delle frequenze della sottosequenza
            List<Double> l = Statistiche.calcolaFrequenze(sottoSequenza);

            // calcolo di V
            double v =  TestChiQuadro.calcolaV(l, sottoSequenza.size(), 1 / this.getD());

            // classifica V con d-1 gradi di liberta'
            if( TestChiQuadro.classificaV(v, this.getD() - 1)) {
                accettabili++;
            }
        }

        System.out.println("Risulta Accettabile " + accettabili + " volte su " +  sequenze.size() + "\n");
        return accettabili;
    }


    /**
     * @param sequenze le sequenze da analizzare
     * @return il numero delle sotto-sequenze Accettabili
     */
    public Integer testSeriale(List<List<Long>> sequenze) {

        // stampa parametri
        stampaParametri("--Il Test Seriale");

        int dd = (int) Math.pow(this.getD(), 2);
        int accettabili = 0;
        for (List<Long> sequenza : sequenze ) {

            // sequenza (Z0, Z1)
            System.out.print("Sequenza (Z0, Z1) ");
            double v1 = TestChiQuadro.calcolaVSeriale(sequenza, 0, (int) this.getD());
            if (  TestChiQuadro.classificaV(v1, dd - 1) )  {
                accettabili++;
            }

            // sequenza (Z1, Z2)
            System.out.print("Sequenza (Z1, Z2) ");
            double v2 = TestChiQuadro.calcolaVSeriale(sequenza, 1, (int) this.getD());
            if ( TestChiQuadro.classificaV(v2, dd - 1) ) {
                accettabili++;
            }
        }
        System.out.println("Risulta Accettabile " + accettabili + " volte su " +  sequenze.size() * 2 + "\n");
        return accettabili;
    }

    private void stampaParametri(String name) {
        System.out.println(name + " dato [a=" + this.getA() + "]" + "[x0=" + this.getX0() + "]" + "[b=" + this.getB() + "]"+ "[d=" + this.getD() + "]"+ "[parti=" + this.getParti()+ "]");
    }


    public static void main(String args[]) {
        int b = 19;
        //int a = 8933;
        //int x0 = 1;
        int d = 64;
        int parti = 3;
/*        for (int x0=3; x0 < 9 ; x0+=2) {
            for (int a=301; a < 9000; a+=2) {
                new Pratica4(a, x0, b, d, prove).applicaTest();
            }
        }*/

        /*for (int x0=3; x0 < 9 ; x0+=2) {
            for (int a=9001; a < 11001; a+=2) {
                new Pratica4(a, x0, b, d, prove).applicaTest();
            }
        }*/

        /*for (int x0=3; x0 < 9 ; x0+=2) {
            for (int a=10037; a < 11000; a+=2) {
                new Pratica4(a, x0, b, d, prove).applicaTest();
            }
        }*/

        /*int a = 10037;
        for (int x0=3; x0 < 1111 ; x0+=2) {
            new Pratica4(a, x0, b, d, prove).applicaTest();
        }*/

        // working
        long a = 10037;
        long x0 = 3;
        new Pratica4(a, x0, 19, d, parti).applicaTest();

    }

    public long getA() {
        return a;
    }

    public void setA(long a) {
        this.a = a;
    }

    public long getX0() {
        return x0;
    }

    public void setX0(long x0) {
        this.x0 = x0;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public int getParti() {
        return parti;
    }

    public void setParti(int parti) {
        this.parti = parti;
    }
}
