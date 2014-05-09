package mls;

import java.util.Collections;
import java.util.List;


/**
 * Created by vortex on 5/9/14.
 */
public class Pratica1 {

    private int a;
    private int b;
    private int m;
    private int x0;

    public Pratica1(int a, int b, int x0) {
        this.setA(a);
        this.setB(b);
        this.setM((int) Math.pow(2, b));
        this.setX0(x0);
    }

    public List<Long> generaSequenza() {
        List<Long> l = new GeneratoreInteriUniforme(this.getA(), this.getB(), this.getX0()).generaSequenza();

        // generazione valori del corollario
        List<Long> corollarioA1 = Util.generaValoriCorollarioA(this.getB(), 1);
        List<Long> corollarioA2 = Util.generaValoriCorollarioA(this.getB(), 15);

        // stampa della sequenza generata
        System.out.println(stampaSequenza(l));
        System.out.println(l.size());

        // ordinamento della sequenza
        Collections.sort(l);

        // controllo se la sequenza ordinata appartiene al corollario
        if (l.equals(corollarioA1) || l.equals(corollarioA2))
            System.out.println("la sequenza e' contenuta nel corollario\n");
        else
            System.out.println("Error: la sequenza non e' contenuta nel corollario\n");

        return l;
    }


    private String stampaSequenza(List<Long> l) {
        return "[a=" + this.getA() + "]" + "[x0=" + this.getX0() + "]" + "[b=" + this.getB() + "] "  + l;
    }

    public static void main(String args[]) {
        int b = 9;
        new Pratica1(3, b, 1).generaSequenza();
        new Pratica1(11, b, 9).generaSequenza();
        new Pratica1(27, b, 15).generaSequenza();
        new Pratica1(11, b, 233).generaSequenza();
        new Pratica1(19, b, 427).generaSequenza();

    }

    public int getA() { return a; }
    public void setA(int a) { this.a = a; }
    public void setM(int m) { this.m = m; }
    public int getM() { return m; }
    public int getB() { return b; }
    public void setB(int b) { this.b = b; }
    public int getX0() { return x0; }
    public void setX0(int x0) { this.x0 = x0; }
}
