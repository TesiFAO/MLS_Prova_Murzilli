package mls.utils;

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

    // usato in Teoria2
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

    // usato in Teoria2
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
        double[] soglie = calcolaSoglie(l, intervalli, step, min, max);
        int[] occorrenze = calcolaOccorrenze(l, intervalli, step, min, max);
        double[] frequenzaRelativa = frequenzaRelativa(occorrenze, l.length);
        double[] densitaProbabilita = densitaProbabilita(frequenzaRelativa, step);
        double[] cumulata = calcolaCumulata(frequenzaRelativa);
        double media =  calcolaMedia(l);
        double varianza =  calcolaVarianza(l, media);
        List<String> soglieChart = getSoglie(soglie, min, max, dfCategories);
        System.out.println("Soglie: "+ soglieChart.toString());
        System.out.println("Numero Occorrenze: [" + Util.sequenceToString(occorrenze) + "]");
        System.out.println("Frequenza Relativa: [" + Util.sequenceToString(frequenzaRelativa) + "]");
        System.out.println("Densita' di probabilita': [" + Util.sequenceToString(densitaProbabilita) + "]");
        System.out.println("Cumulata: [" + Util.sequenceToString(cumulata) + "]");
        System.out.println("Media: " + media);
        System.out.println("Varianza: " + varianza + "\n");

        if ( creaChart != null && creaChart ) {
            Highcharts.printHighcharts(occorrenze, soglieChart, new Highcharts("Numero Occorrenze", "", "column", dfCategories, dfData));
            Highcharts.printHighcharts(frequenzaRelativa, soglieChart, new Highcharts("Requenza Relativa", "", "column", dfCategories, dfData));
            Highcharts.printHighcharts(densitaProbabilita, soglieChart, new Highcharts("Densità di Probabilità", "", "column", dfCategories, "#0.0000"));
            Highcharts.printHighcharts(cumulata, soglieChart, new Highcharts("Cumulata", "", "line", dfCategories, dfData));
        }
    }

    /**
     *
     *
     * @param l sequenza
     * @param intervalli numero intervalli
     * @param step step = (max-min) / intervalli
     * @param min minimo
     * @param max massimo
     * @return Sequenza con i valori delle soglie
     */
    public static double[] calcolaSoglie(double[] l, double intervalli, double step, double min, double max) {
        double[] soglie = new double[(int) intervalli];
        int index = 0;
        for(double range=min; range < max; range+=step) {
            soglie[index] = range + step;
            index++;
        }
        return soglie;
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
     * Calcola il numero di osservazioni nei vari intervalli della sequenza
     * @param l sequenza
     * @param step step dato da (max-min)/intervalli
     * @param min minimo
     * @param max massimo
     * @return lista con le occorrenze
     */
    public static int[] calcolaOccorrenze(double[] l, double intervalli, double step, double min, double max) {
        int[] osservazioni = new int[(int) intervalli];
        // per ogni valore della sequenza viene incremeantato il relativo intervallo di appartenenza
        for(double v : l) {
            int index = 0;
            for(double range=min; range <= max; range+=step) {
                double interval = range + step;
                if ( v > range && v <= (interval) ) {
                    osservazioni[index] = osservazioni[index] + 1;
                    break;
                }
                else if ( v == min) {
                    osservazioni[0] = osservazioni[0] + 1;
                    break;
                }
                index++;
            }
        }
        return osservazioni;
    }

    /**
     * Calcola la frequenza relativa in base alle osservazioni
     * @param osservazioni osservazioni rilevate
     * @param size dimensione della sequenza
     * @return lista con le frequenze relative
     */
    public static double[] frequenzaRelativa(int[] osservazioni, double size) {
        double[] frequenzaRelativa = new double[osservazioni.length];
        for(int i=0; i < osservazioni.length; i++) {
            frequenzaRelativa[i] = osservazioni[i]/size;
        }
        return frequenzaRelativa;
    }

    /**
     * Calcola la densità di probabilità dalla frequenza relativa
     * @param frequenzaRelativa frequenza relativa della sequenza
     * @param step step
     * @return lista con le frequenze relative
     */
    public static double[] densitaProbabilita(double[] frequenzaRelativa, double step) {
        double[] densitaProbabilita = new double[frequenzaRelativa.length];
        for(int i=0; i < frequenzaRelativa.length; i++) {
            densitaProbabilita[i] = frequenzaRelativa[i]/step;
        }
        return densitaProbabilita;
    }


    /**
     * Calcola Cumulata
     * @param frequenzaRelativa frequenza relativa della sequenza
     * @return lista con le frequenze relative
     */
    public static double[] calcolaCumulata(double[] frequenzaRelativa) {
        double[] cumulata = new double[frequenzaRelativa.length];
        double sum = 0.0;
        for(int i=0; i < frequenzaRelativa.length; i++) {
            sum += frequenzaRelativa[i];
            cumulata[i] = sum;
        }
        return cumulata;
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
     * @param l
     * @param min
     * @param decimalFormat
     * @return
     */
    private static List<String> getSoglie(double[] l, double min, double max, String decimalFormat) {
        DecimalFormat df = new DecimalFormat(decimalFormat);
        List<String> soglie = new ArrayList<String>();
        String prec = df.format(min);
        for(int i=0; i < l.length; i++) {
            if ( i < l.length)
                soglie.add(prec + "-" + df.format(l[i]));
            else
                soglie.add(prec + "-" + df.format(max));
            prec = df.format(l[i]);
        }
        return soglie;
    }

}
