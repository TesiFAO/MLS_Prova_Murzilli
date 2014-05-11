package mls.util;

import java.util.*;

/**
 * Created by vortex on 11/05/14.
 */
public class Statistiche {

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
        SortedMap<Double, Integer> numeroOccorrenze = numeroOsservazioni(l, step, min, max);
        SortedMap<Double, Double> frequenzaRelativa = frequezaRelativa(numeroOccorrenze, l.size());
        SortedMap<Double, Double> densitaProbabilita = densitaProbabilita(frequenzaRelativa, step);
        SortedMap<Double, Double> cumulata = calcolaCumulata(frequenzaRelativa);
        double media =  calcolaMedia(l);
        double varianza =  calcolaVarianza(l, media);
        System.out.println("Occorrenze: " + numeroOccorrenze.values());
        System.out.println("Frequenza Relativa: " + frequenzaRelativa.values());
        System.out.println("Densita' di probabilita': " + densitaProbabilita.values());
        System.out.println("Cumulata: " + cumulata.values());
        System.out.println("Media: " + media);
        System.out.println("Varianza: " + varianza + "\n");

        if ( creaChart != null && creaChart ) {
            Highchart.printHighcharts(numeroOccorrenze, min, new Highchart("Numero Occorrenze", "", "column",  "#0.0", "#0"));
            Highchart.printHighcharts(frequenzaRelativa, min, new Highchart("Requenza Relativa", "", "column",  "#0.0", "#0.000"));
            Highchart.printHighcharts(densitaProbabilita, min, new Highchart("Densità di Probabilità", "", "column",  "#0.0", "#0.000"));
            Highchart.printHighcharts(cumulata, min, new Highchart("Cumulata", "", "line",  "#0.0", "#0.000"));
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
}
