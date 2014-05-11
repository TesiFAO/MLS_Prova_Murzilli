package mls;

import mls.util.GeneratoreCM;
import mls.util.Util;

import java.util.Arrays;


/*
 * @author Simone Murzilli
 */
public class Pratica1 {

    private int a;
    private int b;
    private int x0;

    public Pratica1(int a, int b, int x0) {
        this.setA(a);
        this.setB(b);
        this.setX0(x0);
    }

    public long[] generaSequenza() {

        // generazione valori del corollario
        long[] corollarioA1 = Util.generaValoriCorollarioA(this.getB(), 1);
        long[] corollarioA2 = Util.generaValoriCorollarioA(this.getB(), 15);

        // generazione sequenza
        long[] l = new GeneratoreCM(this.getA(), this.getB(), this.getX0()).generaSequenza();

        // stampa della sequenza generata
        Util.stampaSequenza(l, this.getA(), this.getB(), this.getX0());

        // ordinamento della sequenza
        Arrays.sort(l);

        // controllo se la sequenza ordinata appartiene al corollario
        controlloSequenza(l, corollarioA1, corollarioA2);

        return l;
    }

    private void controlloSequenza(long[] l, long[] corollarioA1, long[] corollarioA2) {
        if (Arrays.equals(l, corollarioA1))
            System.out.println("E' contenuta nella lista A1 creata dal corollario\n");
        else if (Arrays.equals(l, corollarioA2))
            System.out.println("E' contenuta nella lista A2 creata dal corollario\n");
        else
            System.out.println("Non e' contenuta in una delle liste create dal corollario\n");
    }

    public int getA() { return a; }
    public void setA(int a) { this.a = a; }
    public int getB() { return b; }
    public void setB(int b) { this.b = b; }
    public int getX0() { return x0; }
    public void setX0(int x0) { this.x0 = x0; }
}
