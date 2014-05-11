package mls;

import java.text.DecimalFormat;
import java.util.*;

/*
 * @author Simone Murzilli
 */
public class Util {

    /** Variabili statiche usate nel test Chi-Quadro **/
    public static double Z25 = -0.674; // accettabile
    public static double Z75 = 0.674;  // accettabile
    public static double  Z5 = -1.645;
    public static double Z95 = 1.645;
    public static double Z10 = -1.282; //quasi sosteptto P5-P10
    public static double Z90 = 1.282;  //quasi sosteptto P90-95
    public static double Z1 = -2.326; // rigettato
    public static double Z99 = 2.326;  //rigettato


    public static List<Long> generaValoriCorollarioA(int b, int x0) {
        List<Long> l = new ArrayList<Long>();
        int size = (int) Math.pow(2, (b-3)) -1;
        if ( x0 == 1 % 16 || x0 == 3 % 16 || x0 == 9 % 16 || x0 == 11 % 16) {
            for (int v = 0; v <= size; v++) {
                l.add((long) (8 * v) + 1);
                l.add((long) (8 * v) + 3);
            }
        }
        if ( x0 == 5 % 16 || x0 == 7 % 16 || x0 == 13 % 16 || x0 == 15 % 16) {
            for (int v = 0; v <= size; v++) {
                l.add((long) (8 * v) + 5);
                l.add((long) (8 * v) + 7);
            }
        }
        return l;
    }

    public static String printRn(List<Double> l, long a, long x0, long b, boolean serie, boolean controllo) {
        String s = "--Sequenza Rn dato [a=" + a + "]" + "[x0=" + x0 + "]"+ "[b=" + b + "]";
        if ( serie)
            s += "\n" + l;
        if  (controllo ) {
            boolean c = Util.controllaSequenzaRn(l);
            s += "\nLa sequenza e' compresa tra [0,1) [" + c + "]\n";
        }
        System.out.println(s);
        return s;
    }

    public static String printSequenzaUniforme(List<Double> l, long a, long x0, long b, double min, double max, boolean serie, boolean controllo) {
        String s = "--Sequenza uniforme in ("+ min + "," + max +") dato [a=" + a + "]" + "[x0=" + x0 + "]" + "[b="+ b +"]";
        if ( serie)
            s += "\n" + l;
        if  (controllo ) {
            boolean c = Util.controllaSequenza(l, min, max);
            s += "\nLa sequenza e' compresa tra (" + min + "," + max + ") [" + c + "]\n";
        }
        System.out.println(s);
        return s;
    }

    public static String printEsponenziale(List<Double> l, long a, long b, double avg, long x0, boolean serie, boolean media) {
        String s = "--Sequenza Esponenziale di media "+ avg +" dato [a=" + a + "]" + "[x0="+ x0 +"]" + "[b="+ b +"]";
        if ( serie)
            s += "\n" + l;
        if  (media )
            s += "\nMedia: " + Util.calcolaMedia(l) + "\n";
        System.out.println(s);
        return s;
    }

    public static String printKErlangiana(List<Double> l, long a, long b, int k, double avg, long[] xos, boolean serie, boolean media) {
        String s = "--Sequenza "+ k +"-Erlangiana di media "+ avg +" dato [a=" + a + "]" + "[b=" + b +"]" + "[k=" + k +"][X0s="+ Util.print(xos) +"]";
        if ( serie)
            s += "\n" + l;
        if  (media )
            s += "\nMedia: " + Util.calcolaMedia(l) + "\n";
        System.out.println(s);
        return s;
    }

    public static String print(long[] xos) {
        String s = "";
        for(int i = 0; i < xos.length; i++) {
            s += xos[i];
            if ( i < xos.length -1)
                s += ",";
        }
        return s;
    }

    public static boolean controllaSequenza(List<Double> l, double min, double max) {
        for (Double v : l) {
            if( v <= min || v >= max)
                return false;
        }
        return true;
    }

    public static boolean controllaSequenzaRn(List<Double> l) {
        for (Double v : l) {
            if( v < 0 || v >= 1)
                return false;
        }
        return true;
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

    public static void calcolaStatistiche(List<Double> l, double intervalli, Boolean creaChart) {
        double min = Collections.min(l);
        double max = Collections.max(l);
        double step = (max - min) / intervalli;
        SortedMap<Double, Integer> numeroOccorrenze = Util.numeroOsservazioni(l, step, min, max);
        SortedMap<Double, Double> frequenzaRelativa = Util.frequezaRelativa(numeroOccorrenze, l.size());
        SortedMap<Double, Double> densitaProbabilita = Util.densitaProbabilita(frequenzaRelativa, step);
        SortedMap<Double, Double> cumulata = Util.calcolaCumulata(frequenzaRelativa);
        double media =  Util.calcolaMedia(l);
        double varianza =  Util.calcolaVarianza(l, media);
        System.out.println("Occorrenze: " + numeroOccorrenze.values());
        System.out.println("Frequenza Relativa: " + frequenzaRelativa.values());
        System.out.println("Densita' di probabilita': " + densitaProbabilita.values());
        System.out.println("Cumulata: " + cumulata.values());
        System.out.println("Media: " + media);
        System.out.println("Varianza: " + varianza + "\n");

        if ( creaChart != null && creaChart ) {
            printHighcharts(numeroOccorrenze, min, new Highchart("Numero Occorrenze", "", "column",  "#0.0", "#0"));
            printHighcharts(frequenzaRelativa, min, new Highchart("Requenza Relativa", "", "column",  "#0.0", "#0.000"));
            printHighcharts(densitaProbabilita, min, new Highchart("Densità di Probabilità", "", "column",  "#0.0", "#0.000"));
            printHighcharts(cumulata, min, new Highchart("Cumulata", "", "line",  "#0.0", "#0.000"));
        }
    }

    public static SortedMap<Double, Integer> numeroOsservazioni(List<Double> sequenza, double step, double min, double max) {
        SortedMap<Double, Integer> osservazioni = new TreeMap();
        double intervalMin = min + step;

        // genera intervalli con valore 0
        osservazioni.put(intervalMin, 0);
        for(double range=min; range < max; range+=step)
            osservazioni.put(range + step, 0);

        // per ogni valore della sequenza viene incremeantato il relativo intervallo di appartenenza
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

    public static SortedMap<Double, Double> densitaProbabilita(SortedMap<Double, Double> frequenzaRelativa, double step) {
        SortedMap<Double, Double> densitaProbabilita = new TreeMap();
        for(Double key: frequenzaRelativa.keySet()) {
            densitaProbabilita.put(key, frequenzaRelativa.get(key) / step);
        }
        return densitaProbabilita;
    }

    public static SortedMap<Double, Double> calcolaCumulata(SortedMap<Double, Double> frequenzaRelativa) {
        SortedMap<Double, Double> cumulativa = new TreeMap();
        double sum = 0;
        for(Double key: frequenzaRelativa.keySet()) {
            sum += frequenzaRelativa.get(key);
            cumulativa.put(key,sum);
        }
        return cumulativa;
    }

    public static List<List<Long>> creaSequenze(double d, long a, long x0, long b, int parti ) {
        List<Long> zn = new GeneratoreRn(a, b, x0).generaSequenzaZn(d);
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

    public static double calcolaV(List<Double> l, double n, double ps) {
        double v = 0.0;
        double valoreArreso = n * ps;
        for (int i = 0 ; i < l.size() ; i++)
            v += Math.pow(l.get(i) - valoreArreso, 2) / valoreArreso;
        return v;
    }

    public static boolean controllaV(double v, double df) {
        double p1 = UtilBackup.calcolaChiQuadro(df, Util.Z1);
        double p5 = UtilBackup.calcolaChiQuadro(df, Util.Z5);
        double p10 = UtilBackup.calcolaChiQuadro(df, Util.Z10);
        double p25 = UtilBackup.calcolaChiQuadro(df, Util.Z25);
        double p75 = UtilBackup.calcolaChiQuadro(df, Util.Z75);
        double p90 = UtilBackup.calcolaChiQuadro(df, Util.Z90);
        double p95 = UtilBackup.calcolaChiQuadro(df, Util.Z95);
        double p99 = UtilBackup.calcolaChiQuadro(df, Util.Z99);

   /*     System.out.println(p1);
        System.out.println(p5);
        System.out.println(p10);
        System.out.println(p25);
        System.out.println(p75);
        System.out.println(p90);
        System.out.println(p95);
        System.out.println(p99);*/

        System.out.print("V=[" + v + "] ");
        if ( v >= p25 && v <= p75 )  {
            System.out.println(p25 + " <= " + v + " <= " + p75 + " Accettabile");
            return true;
        }
        else if ( (v >= p1 && v <= p5) ) {
            System.out.println(p1 + " <= " + v + " <= " + p5 + " Sospetto");
        }
        else if ( (v >= p95 && v <= p99) ) {
            System.out.println(p95 + " <= " + v + " <= " + p99 + " Sospetto");
        }
        else if ( (v >= p5 && v <= p10) ) {
            System.out.println(p5 + " <= " + v + " <= " + p10 + " Quasi Sospetto");
        }
        else if ( (v >= p90 && v <= p95) ) {
            System.out.println(p90 + " <= " + v + " <= " + p95 + " Quasi Sospetto");
        }
        else if ( v > p99 )  {
            System.out.println(v + " > " + p99  + " Rigetto");
        }
        else if ( v < p1 )  {
            System.out.println(v + " < " + p1  + " Rigetto");
        }
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

    private static void printHighcharts(Map map, double min, Highchart highchart) {
        int count = 0;
        DecimalFormat df = new DecimalFormat(highchart.getDecimalFormatData());
        String data = "[";
        for(Object key: map.keySet()) {
            count++;
            data += df.format(map.get(key));
            if ( count < map.size()) {
                data += (",");
            }
        }
        data += "]";

        df = new DecimalFormat(highchart.getDecimalFormatCategories());
        String categories = "[";
        count = 0;
        String prec = df.format(min);
        for(Object key: map.keySet()) {
            count++;
            categories += "\"" + prec + "-" + df.format(key) + "\"";
            prec = df.format(key);
            if ( count < map.size()) {
                categories += ",";
            }
        }
        categories += "]";

        highchart.setData(data);
        highchart.setCategories(categories);
        System.out.println("Highchart: " + highchart.getChart());
    }

}
