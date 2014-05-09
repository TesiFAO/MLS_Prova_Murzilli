package mls;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vortex on 5/9/14.
 */
public class GenInteriUniforme {
    private long a;
    private long b;
    private long m;
    private long x;

    public GenInteriUniforme(long a, long m, long x0) {
      this.setA(a);
      this.setM(m);
      this.setX(x0);
    }

    public long getNext() {
        x = (a * x) % m;
        return x;
    }

    public List<Long> generaSequenza() {
        List<Long> s = new ArrayList<Long>((int) Math.pow(2, this.getB()-2));
        while (!s.contains(x)) {
            s.add(x);
            x = (a * x) % m;
        }
        return s;
    }


    public long getA() {
        return a;
    }

    public void setA(long a) {
        this.a = a;
    }

    public long getB() {
        return b;
    }

    public void setB(long b) {
        this.b = b;
    }

    public long getM() {
        return m;
    }

    public void setM(long m) {
        this.m = m;
    }

    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }
}
