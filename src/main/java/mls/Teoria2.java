package mls;

import mls.util.GeneratoreCM;
import mls.util.Statistiche;

import java.util.Arrays;

/*
 * @author Simone Murzilli
 */
public class Teoria2 {

    private int a;
    private int b;
    private int x0;

    public Teoria2(int a, int b, int x0) {
        this.setA(a);
        this.setB(b);
        this.setX0(x0);
    }

    public long[] generaSequenza() {

        long[] l = new GeneratoreCM(this.getA(), this.getB(), this.getX0()).generaSequenza();
        long[] l1 = Arrays.copyOfRange(l, 1, l.length);

        double media = Statistiche.calcolaMedia(l);
        double media1 = Statistiche.calcolaMedia(l1);

        double cov = covarianza(l, l1, media, media1 );

        double sd =  Statistiche.calcolaStandardDeviation(Statistiche.calcolaVarianza(l, media));
        double sd1 = Statistiche.calcolaStandardDeviation(Statistiche.calcolaVarianza(l1, media1));

        double sds = sd * sd1;
        double corr = cov / sds;
        System.out.println("Media: " + media);
        System.out.println("Media1: " + media1);
        System.out.println("Covarianza: " + cov);
        System.out.println("Correlazione: " + corr);
        System.out.println();
        return l;
    }

    private double covarianza(long[] l , long[] l1, double media, double media1) {
        double cov = 0.0;
        for(int i=0; i < l.length -1; i++) {
            cov += (l[i] - media) * (l1[i] - media1);
        }
        return cov / (l.length -1);
    }

    public static void main(String[] args) {
        int b = 5;
        new Teoria2(3, b, 1).generaSequenza();
        new Teoria2(11, b, 1).generaSequenza();
        new Teoria2(19, b, 1).generaSequenza();
        new Teoria2(27, b, 1).generaSequenza();
    }

    public int getA() { return a; }
    public void setA(int a) { this.a = a; }
    public int getB() { return b; }
    public void setB(int b) { this.b = b; }
    public int getX0() { return x0; }
    public void setX0(int x0) { this.x0 = x0; }
}
