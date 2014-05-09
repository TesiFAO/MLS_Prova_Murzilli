package mls;

/*
 * @author Simone Murzilli
 */

public class MLS {

    public static void main(String[] args) {
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
    }

    private static void pratica1() {
        int b = 9;
        new Pratica1Backup(3, b, 1).generaSequenza();
        new Pratica1Backup(11, b, 9).generaSequenza();
        new Pratica1Backup(27, b, 15).generaSequenza();
        new Pratica1Backup(11, b, 233).generaSequenza();
        new Pratica1Backup(19, b, 427).generaSequenza();
    }

    private static void pratica2() {
        int a = 3;
        int b = 12;
        int m = (int) Math.pow(2, b);
        int x0 = 1;
        int min = 60;
        int max = 80;
        double avg = 30.0;
        int k = 3;

        new Pratica2Backup(a, x0, m).generaRn();
        new Pratica2Backup(a, x0, m, min, max).generaIntervallo();
        new Pratica2Backup(a, x0, m, avg).generaEsponenziale();

        // diversi X0 da passare alla k-erlangiana
        int[] xos = new int[]{5,9,67};
        new Pratica2Backup(a, m, avg, k, xos).generaKErlangiana();
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

        new Pratica3Backup(a, x0, m, min, max).generaRn();
        new Pratica3Backup(a, x0, m, min, max).generaIntervallo();
        new Pratica3Backup(a, x0, m, avg).generaEsponenziale();

        int[] xos = new int[]{5,9,67};
        new Pratica3Backup(a, m, avg, k, xos).generaKErlangiana();
    }

    private static void pratica4() {
        int b = 19;
        int a = 85;
        int x0 = 3;
        int d = 64;
        int prove = 3;
        Pratica4Backup u = new Pratica4Backup(a, x0, b, d, prove);
        u.testUniformita();

        b = 19;
        a = 3;
        x0 = 11;
        d = 64;
        prove = 3;
        Pratica4Backup s = new Pratica4Backup(a, x0, b, d, prove);
        s.testSeriale();
    }

}
