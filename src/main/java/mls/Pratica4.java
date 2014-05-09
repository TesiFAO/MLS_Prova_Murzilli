package mls;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vortex on 5/9/14.
 */
public class Pratica4 {

    private int a;
    private int x0;
    private int m;
    private int b;
    private double d;
    private int prove;

    public Pratica4(int a, int x0, int b, double d, int prove) {
        this.setA(a);
        this.setX0(x0);
        this.setM((int) Math.pow(2.0, b));
        this.setD(d);
        this.setProve(prove);
        this.setB(b);
    }

    public void applicaTest() {
        List<List<Long>> sequenze = UtilBackup.creaSequenze(this.getD(), this.getA(), this.getX0(), this.getM(), this.getProve());
        testUniformita(sequenze);
        testSeriale(sequenze);
    }

    public void testUniformita(List<List<Long>> sequenze) {
        System.out.println("--Il Test di Uniformita' dato [a=" + this.getA() + "]" + "[x0=" + this.getX0() + "]" + "[b=" + this.getB() + "]"+ "[d=" + this.getD() + "]"+ "[prove=" + this.getProve()+ "]");

        int successi = 0;
        for (List<Long> sottoSequenza : sequenze ) {

            // calcolo delle frequenze della sottosequenza
            List<Double> l = UtilBackup.calcolaFrequenze(sottoSequenza);

            // calcolo di V
            double v = UtilBackup.calcolaV(l, sottoSequenza.size(), 1 / this.getD());

            // controllo V con d-1 gradi di liberta'
            if( UtilBackup.controllaV(v, this.getD() - 1)) {
                successi++;
            }
        }

        System.out.println("Risulta Accettabile " + successi + " volte su " +  sequenze.size() + "\n");
    }



    public void testSeriale(List<List<Long>> sequenze) {
        System.out.println("--Il Test Seriale dato [a=" + this.getA() + "]" + "[x0=" + this.getX0() + "]" + "[b=" + this.getB() + "]"+ "[d=" + this.getD() + "]"+ "[prove=" + this.getProve()+ "]");

        // generazione sequenze
        int dd = (int) Math.pow(this.getD(), 2);

        int successi = 0;
        for (List<Long> sequenza : sequenze ) {

            // sequenza (Z0, Z1)
            System.out.print("Sequenza (Z0, Z1) ");
            double v1 = calcolaVSeriale(sequenza, 0, (int) this.getD());
            if ( UtilBackup.controllaV(v1, dd - 1) )  {
                successi++;
            }

            // sequenza (Z1, Z2)
            System.out.print("Sequenza (Z1, Z2) ");
            double v2 = calcolaVSeriale(sequenza, 1, (int) this.getD());
            if ( UtilBackup.controllaV(v2, dd - 1) ) {
                successi++;
            }
        }
        System.out.println("Risulta Accettabile " + successi + " volte su " +  sequenze.size() * 2 + "\n");
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

        return UtilBackup.calcolaV(l, (double) sequenza.size() / 2, (double) 1 / Math.pow(d, 2));
    }


    public static void main(String args[]) {
        int b = 19;
        int a = 79;
        int x0 = 1;
        int d = 64;
        int prove = 3;
        Pratica4 u1 = new Pratica4(a, x0, b, d, prove);
        u1.applicaTest();

    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getX0() {
        return x0;
    }

    public void setX0(int x0) {
        this.x0 = x0;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
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

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}