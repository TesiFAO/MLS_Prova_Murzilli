package mls;

/*
 * @author Simone Murzilli
 */

public class MLS {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
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
       /* pratica1();
        pratica2();
        pratica3();
        pratica4();*/
        long endTime = System.currentTimeMillis();
        System.out.println("Tempo = " + (double) (endTime - startTime) / 1000 + "sec");
    }

    private static void pratica1() {
        int b = 9;
        new Pratica1(3, b, 1).generaSequenza();
        new Pratica1(11, b, 9).generaSequenza();
        new Pratica1(27, b, 15).generaSequenza();
        new Pratica1(11, b, 233).generaSequenza();
        new Pratica1(19, b, 427).generaSequenza();
    }

    private static void pratica2() {
        int a = 3;
        int b = 12;
        int x0 = 1;
        int min = 60;
        int max = 80;
        double avg = 30.0;
        int k = 3;

        new Pratica2(a, x0, b).generaRn();
        new Pratica2(a, x0, b, min, max).generaIntervallo();
        new Pratica2(a, x0, b, avg).generaEsponenziale();

        // diversi X0 da passare alla k-erlangiana
        long[] xos = new long[]{5,9,67};
        new Pratica2(a, b, avg, k, xos).generaKErlangiana();
    }

    private static void pratica3() {
        int a = 3;
        int b = 12;
        int m = (int) Math.pow(2, b);
        int x0 = 1;
        int min = 60;
        int max = 80;
        double avg = 30.0;
        int k = 3;

        new Pratica3(a, x0, b).generaRn();
        new Pratica3(a, x0, b, min, max).generaIntervallo();
        new Pratica3(a, x0, b, avg).generaEsponenziale();

        // diversi X0 da passare alla k-erlangiana
        long[] xos = new long[]{5,9,67};
        new Pratica3(a, b, avg, k, xos).generaKErlangiana();
    }

    private static void pratica4() {
        int b = 19;
        int d = 64;
        int prove = 3;
        long a = 10037;
        long x0 = 161;
        new Pratica4(a, x0, b, d, prove).applicaTest();
    }

}
