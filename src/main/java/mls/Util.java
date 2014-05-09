package mls;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vortex on 5/9/14.
 */
public class Util {

    public static List<Long> generaValoriCorollarioA(int b, int x0) {
        List<Long> l = new ArrayList<Long>();
        int size = (int) Math.pow(2, (b-3)) -1;
        if ( x0 == 1 % 16 || x0 == 3 % 16 || x0 == 9 % 16 || x0 == 11 % 16) {
            for (int v = 0; v <= size; v++) {
                l.add((long) (8 * v) + 1);
                l.add((long)(8 * v) + 3);
            }
        }
        if ( x0 == 5 % 16 || x0 == 7 % 16 || x0 == 13 % 16 || x0 == 15 % 16) {
            for (int v = 0; v <= size; v++) {
                l.add((long)(8 * v) + 5);
                l.add((long) (8 * v) + 7);
            }
        }
        return l;
    }

    public static List<Double> generaRn(int a, int x0, int m) {
        List<Integer> cm = generatoreCongruenteMoltiplicativo(a, x0, m);
        List<Double> l = new ArrayList<Double>();
        for (Integer v : cm)
            l.add((double) v / m);
        return l;
    }
}
