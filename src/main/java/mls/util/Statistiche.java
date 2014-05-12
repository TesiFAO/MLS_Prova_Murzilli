package mls.util;

import java.text.DecimalFormat;
import java.util.*;

/*
 * @author Simone Murzilli
 */

public class Statistiche {

    /**
     * Calcola la media della sequenza
     *
     * @param l sequenza
     * @return media della sequenza
     */
    public static double calcolaMedia(double[] l) {
        double somma = 0.0;
        for (double v : l) {
            somma += v;
        }
        return somma / l.length;
    }

    public static double calcolaMedia(long[] l) {
        double somma = 0.0;
        for (long v : l) {
            somma += v;
        }
        return somma / l.length;
    }

    /**
     * Calcola la varianza della sequenza
     *
     * @param l sequenza
     * @param media media della sequenza
     * @return varianza della sequenza
     */
    public static double calcolaVarianza(double[] l, double media) {
        double sumsq = 0.0;
        for (double v : l) {
            sumsq += Math.pow(v-media, 2);
        }
        return sumsq / l.length;
    }

    public static double calcolaVarianza(long[] l, double media) {
        double sumsq = 0.0;
        for (long v : l) {
            sumsq += Math.pow(v-media, 2);
        }
        return sumsq / l.length;
    }

    public static double calcolaStandardDeviation(double varianza) {
        return Math.sqrt(varianza);
    }

    /**
     * Calcola le statistiche della sequenza
     *
     * @param l sequenza
     * @param intervalli intervalli
     * @param creaChart booleano per la creazione della chart tramite Highcharts
     */
    public static void calcolaStatistiche(double[] l, double intervalli, Boolean creaChart, String dfCategories, String dfData) {
        double[] minmax = getMinMax(l);
        double min = minmax[0];
        double max = minmax[1];
        double step = (max - min) / intervalli;
        SortedMap<Double, Integer> numeroOccorrenze = numeroOsservazioni(l, step, min, max);
        List<String> soglie = getSoglie(numeroOccorrenze, min, max, dfCategories);
        SortedMap<Double, Double> frequenzaRelativa = frequezaRelativa(numeroOccorrenze, l.length);
        SortedMap<Double, Double> densitaProbabilita = densitaProbabilita(frequenzaRelativa, step);
        SortedMap<Double, Double> cumulata = calcolaCumulata(frequenzaRelativa);
        double media =  calcolaMedia(l);
        double varianza =  calcolaVarianza(l, media);
        System.out.println("Soglie: " + soglie.toString());
        System.out.println("Numero Occorrenze: " + numeroOccorrenze.values());
        System.out.println("Frequenza Relativa: " + frequenzaRelativa.values());
        System.out.println("Densita' di probabilita': " + densitaProbabilita.values());
        System.out.println("Cumulata: " + cumulata.values());
        System.out.println("Media: " + media);
        System.out.println("Varianza: " + varianza + "\n");

        if ( creaChart != null && creaChart ) {
            Highcharts.printHighcharts(numeroOccorrenze, min, new Highcharts("Numero Occorrenze", "", "column", dfCategories, dfData));
            Highcharts.printHighcharts(frequenzaRelativa, min, new Highcharts("Requenza Relativa", "", "column", dfCategories, dfData));
            Highcharts.printHighcharts(densitaProbabilita, min, new Highcharts("Densità di Probabilità", "", "column", dfCategories, "#0.0000"));
            Highcharts.printHighcharts(cumulata, min, new Highcharts("Cumulata", "", "line", dfCategories, dfData));
        }
    }


    public static double[] getMinMax(double[] l) {
        double[] minmax = new double[2];
        minmax[0] = l[0];
        minmax[1] = l[0];
        for (int i = 1; i < l.length; i++) {
            minmax[0] = Math.min(minmax[0], l[i]);
            minmax[1] = Math.max(minmax[1], l[i]);
        }
        return minmax;
    }

    /**
     * Calcola il numero di osservazioni della sequenza
     *
     * @param l sequenza
     * @param step step della sequenza
     * @param min minimo
     * @param max massimo
     * @return Map contenente soglie e numero delle occorrenze
     */
    public static SortedMap<Double, Integer> numeroOsservazioni(double[] l, double step, double min, double max) {
        SortedMap<Double, Integer> osservazioni = new TreeMap();
        double intervalMin = min + step;

        // genera intervalli con valore 0
        osservazioni.put(intervalMin, 0);
        for(double range=min; range < max; range+=step)
            osservazioni.put(range + step, 0);

        // per ogni valore della sequenza viene incremeantato il relativo intervallo di appartenenza
        for(double v : l) {
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

    /**
     * Calcola la frequenza relativa in base alle osservazioni
     *
     * @param osservazioni osservazioni rilevate
     * @param size dimensione della sequenza
     * @return Map contenente soglie e valore delle frequenze relative
     */
    public static SortedMap<Double, Double> frequezaRelativa(SortedMap<Double, Integer> osservazioni, double size) {
        SortedMap<Double, Double> frequenzaRelativa = new TreeMap();
        for(Double key: osservazioni.keySet()) {
            frequenzaRelativa.put(key, osservazioni.get(key) / size);
        }
        return frequenzaRelativa;
    }

    /**
     * Calcola la densità di probabilità dalla frequenza relativa
     *
     * @param frequenzaRelativa frequenza relativa della sequenza
     * @param step intervallo della sequenza
     * @return Map contenente soglie e valore della densità di probabilità
     */
    public static SortedMap<Double, Double> densitaProbabilita(SortedMap<Double, Double> frequenzaRelativa, double step) {
        SortedMap<Double, Double> densitaProbabilita = new TreeMap();
        for(Double key: frequenzaRelativa.keySet()) {
            densitaProbabilita.put(key, frequenzaRelativa.get(key) / step);
        }
        return densitaProbabilita;
    }

    /**
     * Calcola la cumulata
     *
     * @param frequenzaRelativa frequenza relativa della sequenza
     * @return Map contenente soglie e valore della cumulata
     */
    public static SortedMap<Double, Double> calcolaCumulata(SortedMap<Double, Double> frequenzaRelativa) {
        SortedMap<Double, Double> cumulativa = new TreeMap();
        double sum = 0.0;
        for(Double key: frequenzaRelativa.keySet()) {
            sum += frequenzaRelativa.get(key);
            cumulativa.put(key,sum);
        }
        return cumulativa;
    }

    /**
     * Calcola le frequenze all'interno di una sequenza
     *
     * @param l sequenza
     * @return Lista con il numero delle occorrenze della sequenza
     */
    public static double[] calcolaFrequenze(long[] l) {
        LinkedHashMap<Long, Double> f = new LinkedHashMap<Long, Double>();
        for (long v : l) {
            double c = 1.0;
            if ( f.containsKey(v))
                c += f.get(v);
            f.put(v, c);
        }

        double[] s = new double[f.size()];
        int index=0;
        for (double v : f.values()) {
            s[index] = v;
            index++;
        }
        return s;
    }

    /**
     * Restituisce le soglie
     *
     * @param map
     * @param min
     * @param decimalFormat
     * @return
     */
    private static List<String> getSoglie(Map map, double min, double max, String decimalFormat) {
        DecimalFormat df = new DecimalFormat(decimalFormat);
        List<String> soglie = new ArrayList<String>();
        int count = 0;
        String prec = df.format(min);
        for(Object key: map.keySet()) {
            String soglia = "";
            if ( count < map.size())
                soglie.add(prec + "-" + df.format(key));
            else
                soglie.add(prec + "-" + df.format(max));
            prec = df.format(key);
        }
        return soglie;
    }

    private static String print(Map map, double min, double max, String decimalFormat) {
        DecimalFormat df = new DecimalFormat(decimalFormat);
        String categories = "[";
        int count = 0;
        String prec = df.format(min);
        for(Object key: map.keySet()) {
            if ( count < map.size())
                categories += "\"" + prec + "-" + df.format(key) + "\"";
            else
                categories += "\"" + prec + "-" + df.format(max) + "\"";
            prec = df.format(key);
            count++;
            if ( count < map.size()) {
                categories += ",";
            }
        }
        categories += "]";
        return categories;
    }
}
