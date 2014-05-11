package mls;

import java.text.DecimalFormat;
import java.util.*;


/**
 * @author Simone Murzilli
 */

public class UtilBackup {

    /** Variabili statiche usate nel test Chi-Quadro **/
    public static double Z25 = -0.674; // accettabile
    public static double Z75 = 0.674;  // accettabile

    public static double Z10 = -1.282; //quasi sosteptto P5-P10
    public static double Z90 = 1.282;  //quasi sosteptto P90-95

    public static double Z1 = -2.326; // rigettato
    public static double Z99 = 2.326;  //rigettato


    public static List<Integer> generatoreCongruenteMoltiplicativo(int a, int x0, int m) {
        List<Integer> l = new ArrayList<Integer>();
        while (!l.contains(x0)) {
            l.add(x0);
            x0 = (a * x0) % m;
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

    public static boolean controllaSequenzaRn(List<Double> l) {
        for (Double v : l) {
            if( v < 0 || v >= 1)
                return false;
        }
        return true;
    }

    public static boolean controllaSequenza(List<Double> l, double min, double max) {
        for (Double v : l) {
            if( v <= min || v >= max)
                return false;
        }
        return true;
    }


    public static List<Integer> generaValoriCorollarioA(int b, int x0) {
        List<Integer> l = new ArrayList<Integer>();
        int size = (int) Math.pow(2, (b-3)) -1;
        if ( x0 == 1 % 16 || x0 == 3 % 16 || x0 == 9 % 16 || x0 == 11 % 16) {
            for (int v = 0; v <= size; v++) {
                l.add((8 * v) + 1);
                l.add((8 * v) + 3);
            }
        }
        if ( x0 == 5 % 16 || x0 == 7 % 16 || x0 == 13 % 16 || x0 == 15 % 16) {
            for (int v = 0; v <= size; v++) {
                l.add((8 * v) + 5);
                l.add((8 * v) + 7);
            }
        }
        return l;
    }

    public static List<Double> generaIntervallo(int a, int x0, int m, int min, int max) {
        List<Double> rn = generaRn(a, x0, m);
        List<Double> l = new ArrayList<Double>();
        for(Double v : rn)
            l.add(min + v * (max - min));
        return l;
    }

    public static List<Double> generaEsponenziale(int a, int x0, int m, double avg) {
        List<Double> rns = generaRn(a, x0, m);
        List<Double> l = new ArrayList<Double>();
        for(Double rn : rns)
            l.add(-avg * Math.log(rn));
        return l;
    }

    public static List<Double> generaKErlangiana(int a, int m, int k, double avg, int[] xos ) {
        List<List<Double>> exps = new ArrayList<List<Double>>();

        // generazione k sequenze Rn
        for(int i=0; i < k; i++)
            exps.add(UtilBackup.generaRn(a, xos[i], m));

        // generazione sequenza k-erlangiana
        List<Double> x_sum = new ArrayList<Double>();
        double avgk = -avg / k;
        for (int i = 0 ; i < exps.get(0).size() ; i++) {
            double sumlog = 0.0;
            for (int j = 0; j < k; j++) {
                sumlog += Math.log(exps.get(j).get(i));
            }
            x_sum.add((avgk) * sumlog);
        }
        return x_sum;
    }

    public static String print(int[] xos) {
        String s = "";
        for(int i = 0; i < xos.length; i++) {
            s += xos[i];
            if ( i < xos.length -1)
                s += ",";
        }
        return s;
    }

    public static String printEsponenziale(List<Double> l, int a, double avg, int x0, boolean serie, boolean media) {
        String s = "--Sequenza Esponenziale di media "+ avg +" [a=" + a + "]" + "[x0="+ x0 +"]";
        if ( serie)
            s += "\n" + l + "\n";
        if  (media )
            s += "Media: " + UtilBackup.calcolaMedia(l) + "\n";
        System.out.println(s);
        return s;
    }

    public static String printKErlangiana(List<Double> l, int a, int k, double avg, int[] xos, boolean serie, boolean media) {
        String s = "--Sequenza "+ k +"-Erlangiana di media "+ avg +" [a=" + a + "]" + "[k=" + k +"] [X0s="+ UtilBackup.print(xos) +"]";
        if ( serie)
            s += "\n" + l + "\n";
        if  (media )
            s += "Media: " + UtilBackup.calcolaMedia(l) + "\n";
        System.out.println(s);
        return s;
    }

    public static String printSequenzaUniforme(List<Double> l, int a, int x0, double min, double max, boolean serie, boolean controllo) {
        String s = "--Sequenza uniforme in ("+ min + "," + max +") [a=" + a + "]" + "[x0=" + x0 + "]";
        if ( serie)
            s += "\n" + l + "\n";
        if  (controllo ) {
            boolean c = UtilBackup.controllaSequenza(l, min, max);
            s += "La sequenza e' compresa tra (" + min + "," + max + ") [" + c + "]\n";
        }
        System.out.println(s);
        return s;
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0 ; i < matrix.length ; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] > 0)
                    System.out.print("X ");
                else
                    System.out.print("  ");
            }
            System.out.println("| ");
        }
    }

    public static String printRn(List<Double> l, int a, int x0, boolean serie, boolean controllo) {

        String s = "--Sequenza Rn [a=" + a + "]" + "[x0=" + x0 + "]";
        if ( serie)
            s += "\n" + l + "\n";
        if  (controllo ) {
            boolean c = UtilBackup.controllaSequenzaRn(l);
            s += "La sequenza e' compresa tra [0,1) [" + c + "]\n";
        }
        System.out.println(s);
        return s;
    }

    public static double calcolaMedia(List<Double> l) {
        double somma = 0.0;
        for (Double v : l) {
            somma += v;
        }
        return (somma / l.size());
    }

    public static double calcolaVarianza(List<Double> l, double media) {
        double sumsq = 0.0;
        for (Double v : l) {
            sumsq = sumsq + ((v-media) * (v-media));
        }
        return sumsq / l.size();
    }

    public static double calcolaStandardDeviation(double varianze) {
        return Math.sqrt(varianze);
    }

    public static double calcolaV(List<Double> l, double n, double ps) {
        double v = 0.0;
        double valoreArreso = n * ps;
        for (int i = 0 ; i < l.size() ; i++)
            v += Math.pow(l.get(i) - valoreArreso, 2) / valoreArreso;
        return v;
    }

    public static double calcolaVSeriale(int[][] matrix, double size, double ps) {
        List<Double> l = new ArrayList<Double>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                l.add(new Double(matrix[i][j]));
            }
        }
        return UtilBackup.calcolaV(l, size, ps);
    }

    public static boolean controllaV(double v, double df) {
        double z25 = UtilBackup.calcolaChiQuadro(df, UtilBackup.Z25);
        double z75 = UtilBackup.calcolaChiQuadro(df, UtilBackup.Z75);

        if ( v >= z25 && v <= z75 )  {
            System.out.println(z25 + " <= " + v + " <= " + z75 + " Accettabile");
            return true;
        }
        else if ( v < z25 ) {
           // System.out.println(v + " < " + z25 + " < " + z75 +" Rigetto");
        }
        else if ( v > z75 )  {
            //System.out.println(v + " > " + z75  + " > " + z25 + " Rigetto");
        };
        return false;
    }


    public static List<Double> calcolaFrequenze(List<Long> s) {
        LinkedHashMap<Long, Double> f = new LinkedHashMap<Long, Double>();
        for (Long v : s) {
            double c = 1.0;
            if ( f.containsKey(v))
                c += f.get(v);
            f.put(v, c);
        }

        List<Double> l = new ArrayList<Double>();
        for (Double v : f.values())
            l.add(v);
        return l;
    }



    public static double calcolaChiQuadro(double df, double za) {
        return (df * Math.pow(1.0 - (2.0 / (9.0 * df)) + (za * Math.sqrt(2.0 / (9.0 * df))), 3));
    }


    public static SortedMap<Double, Integer> numeroOsservazioni(List<Double> sequenza, double step, double min, double max) {
        SortedMap<Double, Integer> osservazioni = new TreeMap();
        double intervalMin = min + step;
        osservazioni.put(intervalMin, 0);
        for(double range=min; range < max; range+=step)
            osservazioni.put(range + step, 0);

        for(Double v : sequenza) {
            for(double range=min; range <= max; range+=step) {
                double interval = range + step;
                if ( v > range && v <= (interval) ) {
                    osservazioni.put(interval, osservazioni.get(interval) + 1);
                    break;
                }
                else if ( v == min) {
                    osservazioni.put(intervalMin, osservazioni.get(intervalMin) + 1);
                    break;
                }
            }
        }
        return osservazioni;
    }

    public static SortedMap<Double, Double> frequezaRelativa(SortedMap<Double, Integer> osservazioni, double size) {
        SortedMap<Double, Double> frequenzaRelativa = new TreeMap();
        for(Double key: osservazioni.keySet()) {
            frequenzaRelativa.put(key, osservazioni.get(key) / size);
        }
        return frequenzaRelativa;
    }

    public static SortedMap<Double, Double> densitaProbabilita(SortedMap<Double, Double> frequenzaRelativa, double intervalli, double min, double max) {
        SortedMap<Double, Double> densitaProbabilita = new TreeMap();
        double step = (max - min) / intervalli;
        for(Double key: frequenzaRelativa.keySet()) {
            densitaProbabilita.put(key, frequenzaRelativa.get(key) / step);
        }
        return densitaProbabilita;
    }


    public static List<Double> calcolaCumulata(SortedMap<Double, Double> frequenzaRelativa) {
        List<Double> cumulativa = new ArrayList<Double>();
        double sum = 0;
        for(Double key: frequenzaRelativa.keySet()) {
            sum += frequenzaRelativa.get(key);
            cumulativa.add(sum);
        }
        return cumulativa;
    }

    public static void calcolaStatistiche(List<Double> l, double intervalli) {
        double min = Collections.min(l);
        double max = Collections.max(l);
        SortedMap<Double, Integer> numeroOccorrenze = UtilBackup.numeroOsservazioni(l, intervalli, min, max);
        SortedMap<Double, Double> frequenzaRelativa = UtilBackup.frequezaRelativa(numeroOccorrenze, l.size());
        SortedMap<Double, Double> densitaProbabilita = UtilBackup.densitaProbabilita(frequenzaRelativa, intervalli, min, max);
        List<Double> cumulata = UtilBackup.calcolaCumulata(frequenzaRelativa);
        System.out.println("Occorrenze: " + numeroOccorrenze.values());
        System.out.println("Frequenza Relativa: " + frequenzaRelativa.values());
        System.out.println("Densita' di probabilita': " + densitaProbabilita.values());
        System.out.println("Cumulata: " + cumulata);
        //printHighcharts(numeroOccorrenze, min);
        //printHighcharts(frequezaRelativa, min);
        //printHighcharts(densitaProbabilita, min);
        //System.out.println("Cumulata: " + printHighchartsCumulata(cumulata));
        double media =  UtilBackup.calcolaMedia(l);
        double varianza =  UtilBackup.calcolaVarianza(l, media);
        System.out.println("Media: " + media);
        System.out.println("Varianza: " + varianza + "\n");
    }

    private static void printHighcharts(Map map, double min) {
        int count = 0;
        DecimalFormat df = new DecimalFormat("#0.00000");
        String s = "data: [";
        for(Object key: map.keySet()) {
            count++;
            s += df.format(map.get(key));
            if ( count < map.size()) {
                s += (",");
            }
        }
        s += "]";


        df = new DecimalFormat("#0");
        String c = "categories: [";
        count = 0;
        String prec = df.format(min);
        for(Object key: map.keySet()) {
            count++;
            //c += df.format(key);
            c += "\"" + prec + "-" + df.format(key) + "\"";

            prec = df.format(key);
            if ( count < map.size()) {
                c += ",";
            }
        }
        c += "]";
        System.out.println(c);
        System.out.println(s);
    }


    private static String printHighchartsCumulata(List<Double> l) {
        int count = 0;
        DecimalFormat df = new DecimalFormat("#0.00000");
        String s = "data: [";
        for(Double v : l) {
            count++;
            s += df.format(v);
            if ( count < l.size()) {
                s += (",");
            }
        }
        s += "]";
        return s;
    }

    public static List<List<Long>> creaSequenze(double d, int a, int x0, int m, int parti ) {
        List<Double> rns = UtilBackup.generaRn(a, x0, m);
        System.out.println("HERe");
        List<Long> zn = new ArrayList<Long>();
        for (Double rn : rns)
            zn.add((long) Math.floor(d * rn));

        List<List<Long>> sequenze = new ArrayList<List<Long>>();
        double dimensioneSequenza = zn.size() / parti;
        int index = 0;
        for(int i=0; i < parti; i++) {
            if ( i < parti - 1)
                sequenze.add(zn.subList(index, index + (int) dimensioneSequenza));
            else
                sequenze.add(zn.subList(index, zn.size()));
            index += dimensioneSequenza;
        }
        return sequenze;
    }
}
