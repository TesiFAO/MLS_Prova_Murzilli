package mls;

/*
 * @author Simone Murzilli
 */

public class MLS {

    public static void main(String[] args) {
        //long startTime = System.currentTimeMillis();
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if (arg.equals("pratica1"))
                pratica1();
            else if (arg.equals("pratica2"))
                pratica2();
            else if (arg.equals("pratica3"))
                pratica3();
            else if (arg.equals("pratica4"))
                pratica4();
        }
        pratica3();
        //long endTime = System.currentTimeMillis();
        //System.out.println("Tempo = " + (double) (endTime - startTime) / 1000 + "sec");
    }

    private static void pratica1() {
        System.out.println("********************** PRATICA-1 ********************** ");
        int b = 9;
        Pratica1.generaSequenza(3, b, 3);
        Pratica1.generaSequenza(27, b, 23);
    }

    private static void pratica2() {
        System.out.println("********************** PRATICA-2 ********************** ");
        int a = 3;
        int b = 12;
        int x0 = 1;
        int min = 60;
        int max = 80;
        double avg = 30.0;
        int k = 3;

        Pratica2.generaRn(a, b, x0);
        Pratica2.generaIntervallo(a, b, x0, min, max);
        Pratica2.generaEsponenziale(a, b, x0, avg);

        // diversi X0 da passare alla k-erlangiana
        long[] xos = new long[]{5,9,67};
        Pratica2.generaKErlangiana(a, b, xos, avg, k);

    }

    private static void pratica3() {
        System.out.println("********************** PRATICA-3 ********************** ");
        int a = 3;
        int b = 12;
        int m = (int) Math.pow(2, b);
        int x0 = 1;
        int min = 60;
        int max = 80;
        double avg = 30.0;
        int k = 3;

        Pratica3.generaRn(a, b, x0);
        Pratica3.generaIntervallo(a, b, x0, min, max);
        Pratica3.generaEsponenziale(a, b, x0, avg);

        // diversi X0 da passare alla k-erlangiana
        long[] xos = new long[]{5,9,67};
        Pratica3.generaKErlangiana(a, b, xos, avg, k);
    }

    private static void pratica4() {
        System.out.println("********************** PRATICA-4 ********************** ");
        int b = 19;
        int d = 64;
        int prove = 3;
        long a = 10037;
        long x0 = 213;
        Pratica4.applicaTest(a, b, x0, d, prove);
    }
}
