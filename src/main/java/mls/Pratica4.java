package mls;

import java.util.ArrayList;
import java.util.List;

/*
 * @author Simone Murzilli
 */
public class Pratica4 {

    private long a;
    private long x0;
    private long m;
    private long b;
    private double d;
    private int prove;

    public Pratica4(long a, long x0, long b, double d, int prove) {
        this.setA(a);
        this.setX0(x0);
        this.setM((int) Math.pow(2.0, b));
        this.setD(d);
        this.setProve(prove);
        this.setB(b);
    }

    public void applicaTest() {
        List<List<Long>> sequenze = Util.creaSequenze(this.getD(), this.getA(), this.getX0(), this.getB(), this.getProve());
        int su = testUniformita(sequenze);
        int ss = testSeriale(sequenze);
        //if ( su >= 0 && ss >= 1)
        //    System.out.println("\nDAJE SEMPRE!!!!!!! " + su + " | "  + ss + " | "  + this.getA() + " | " + this.getX0());

        /*if ( su >= 2 )
            System.out.println(su  + " | "  + ss + " | "  + this.getA() + " | " + this.getX0());

        if ( ss >= 3 )
            System.out.println(su  + " | "  + ss + " | "  + this.getA() + " | " + this.getX0());*/

        if( su >= 2 && ss >= 3)
            System.out.println("\nDAJE SEMPRE!!!!!!! " + su + " | "  + ss + " | "  + this.getA() + " | " + this.getX0());

    }

    /**
     * @param sequenze le sequenze da analizzare in input
     * @return il numero delle sotto-sequenze Accettabili
     */
    public Integer testUniformita(List<List<Long>> sequenze) {
        System.out.println("--Il Test di Uniformita' dato [a=" + this.getA() + "]" + "[x0=" + this.getX0() + "]" + "[b=" + this.getB() + "]"+ "[d=" + this.getD() + "]"+ "[prove=" + this.getProve()+ "]");

        int successi = 0;
        for (List<Long> sottoSequenza : sequenze ) {

            // calcolo delle frequenze della sottosequenza
            List<Double> l = Util.calcolaFrequenze(sottoSequenza);

            // calcolo di V
            double v = Util.calcolaV(l, sottoSequenza.size(), 1 / this.getD());

            // controllo V con d-1 gradi di liberta'
            if( Util.controllaV(v, this.getD() - 1)) {
                successi++;
            }
        }

        System.out.println("Risulta Accettabile " + successi + " volte su " +  sequenze.size() + "\n");
        return successi;
    }



    public Integer testSeriale(List<List<Long>> sequenze) {
        System.out.println("--Il Test Seriale dato [a=" + this.getA() + "]" + "[x0=" + this.getX0() + "]" + "[b=" + this.getB() + "]"+ "[d=" + this.getD() + "]"+ "[prove=" + this.getProve()+ "]");

        // generazione sequenze
        int dd = (int) Math.pow(this.getD(), 2);

        int successi = 0;
        for (List<Long> sequenza : sequenze ) {

            // sequenza (Z0, Z1)
            //System.out.print("Sequenza (Z0, Z1) ");
            double v1 = calcolaVSeriale(sequenza, 0, (int) this.getD());
            if ( Util.controllaV(v1, dd - 1) )  {
                successi++;
            }

            // sequenza (Z1, Z2)
            //System.out.print("Sequenza (Z1, Z2) ");
            double v2 = calcolaVSeriale(sequenza, 1, (int) this.getD());
            if ( Util.controllaV(v2, dd - 1) ) {
                successi++;
            }
        }
        System.out.println("Risulta Accettabile " + successi + " volte su " +  sequenze.size() * 2 + "\n");
        return successi;
    }

    private double calcolaVSeriale(List<Long> sequenza, int serie, int d) {
        int[][] matrix = new int[d][d];

        // calcolo matrice delle frequenze
        for (int i = serie; i < sequenza.size() - 1; i+=2) {
            matrix[sequenza.get(i).intValue()][sequenza.get(i + 1).intValue()] = matrix[sequenza.get(i).intValue()][sequenza.get(i + 1).intValue()] + 1;
        }

        // calcolo array dalla matrice delle frequenze da passare al metodo per il calcolo di V
        List<Double> l = new ArrayList<Double>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                l.add(new Double(matrix[i][j]));
            }
        }

        return Util.calcolaV(l, (double) sequenza.size() / 2, (double) 1 / Math.pow(d, 2));
    }


    public static void main(String args[]) {
        int b = 19;
        //int a = 8933;
        //int x0 = 1;
        int d = 64;
        int prove = 3;
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

        /*long a = 10037;
        for (int x0=3; x0 < 1111 ; x0+=2) {
            new Pratica4(a, x0, b, d, prove).applicaTest();
        }*/

        // working
        long a = 10037;
        long x0 = 161;
        new Pratica4(a, x0, b, d, prove).applicaTest();


       b = 19;
         a = 29;
         x0 = 365;
        d = 64;
        prove = 3;
        new Pratica4(a, x0, b, d, prove).applicaTest();
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

    public long getM() {
        return m;
    }

    public void setM(long m) {
        this.m = m;
    }

    public long getB() {
        return b;
    }

    public void setB(long b) {
        this.b = b;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public int getProve() {
        return prove;
    }

    public void setProve(int prove) {
        this.prove = prove;
    }
}
