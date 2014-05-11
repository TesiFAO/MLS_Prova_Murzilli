package mls.util;

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
    public static double calcolaMedia(List<Double> l) {
        double somma = 0.0;
        for (Double v : l) {
            somma += v;
        }
        return (somma / l.size());
    }

    /**
     * Calcola la varianza della sequenza
     *
     * @param l sequenza
     * @param media media della sequenza
     * @return varianza della sequenza
     */
    public static double calcolaVarianza(List<Double> l, double media) {
        double sumsq = 0.0;
        for (Double v : l) {
            sumsq += Math.pow(v-media, 2);
        }
        return sumsq / l.size();
    }

    /**
     * Calcola le statistiche della sequenza
     *
     * @param l sequenza
     * @param intervalli intervalli
     * @param creaChart booleano per la creazione della chart tramite Highchart
     */
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
            Highchart.printHighchart(numeroOccorrenze, min, new Highchart("Numero Occorrenze", "", "column",  "#0.0", "#0"));
            Highchart.printHighchart(frequenzaRelativa, min, new Highchart("Requenza Relativa", "", "column",  "#0.0", "#0.000"));
            Highchart.printHighchart(densitaProbabilita, min, new Highchart("Densità di Probabilità", "", "column",  "#0.0", "#0.000"));
            Highchart.printHighchart(cumulata, min, new Highchart("Cumulata", "", "line",  "#0.0", "#0.000"));
        }
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
    public static SortedMap<Double, Integer> numeroOsservazioni(List<Double> l, double step, double min, double max) {
        SortedMap<Double, Integer> osservazioni = new TreeMap();
        double intervalMin = min + step;

        // genera intervalli con valore 0
        osservazioni.put(intervalMin, 0);
        for(double range=min; range < max; range+=step)
            osservazioni.put(range + step, 0);

        // per ogni valore della sequenza viene incremeantato il relativo intervallo di appartenenza
        for(Double v : l) {
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
    public static List<Double> calcolaFrequenze(List<Long> l) {
        LinkedHashMap<Long, Double> f = new LinkedHashMap<Long, Double>();
        for (Long v : l) {
            double c = 1.0;
            if ( f.containsKey(v))
                c += f.get(v);
            f.put(v, c);
        }

        List<Double> s = new ArrayList<Double>();
        for (Double v : f.values())
            s.add(v);
        return s;
    }
}
