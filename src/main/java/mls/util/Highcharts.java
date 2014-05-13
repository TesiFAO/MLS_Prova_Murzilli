package mls.util;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

/*
 * @author Simone Murzilli
 */
public class Highcharts {

   private String title;
   private String subtitle;
   private String type;
   private String categories;
   private String data;
   private String decimalFormatData;
   private String decimalFormatCategories;

   public Highcharts(String title, String subtitle, String type, String decimalFormatCategories, String decimalFormatData) {
       this.setTitle(title);
       this.setType(type);
       this.setSubtitle(subtitle);
       this.setData(data);
       this.setCategories(categories);
       this.setDecimalFormatCategories(decimalFormatCategories);
       this.setDecimalFormatData(decimalFormatData);
   }

   private static String s = "$(function () { $('#container').highcharts({ chart: { type: 'scatter', zoomType: 'xy' }, title: { text: '$_TITLE', }, subtitle: { text: '$_SUBTITLE' }, xAxis: { title: { enabled: false }, labels: { rotation: -45 }, startOnTick: false, endOnTick: true, showLastLabel: true, categories: $_CATEGORIES }, legend: { enabled: false }, yAxis: { title: { text: '' }, }, credits: { enabled: false }, plotOptions: { column: { dataLabels: { enabled: true, rotation: -90, y: -20, x:4 }, pointPadding: 0.2, borderWidth: 0 }, line: { dataLabels: { enabled: true, rotation: -90, y: -20, x:4 }, pointPadding: 0.2, borderWidth: 0 }, scatter: { marker: { states: { hover: { enabled: false, lineColor: 'rgb(100,100,100)' } } }, states: { hover: { marker: { enabled: false } } }, tooltip: { headerFormat: '<b>{series.name}</b><br>', pointFormat: '{point.x}, {point.y}' } } }, series: [ { name: '', color: 'rgba(119, 152, 191, .8)', type: '$_TYPE', data: $_DATA } ] }); }); ";

   public String getChart() {
       String r = s.replace("$_TITLE", title);
       r = r.replace("$_SUBTITLE", subtitle);
       r = r.replace("$_TYPE", type);
       r = r.replace("$_CATEGORIES", categories.toString());
       r = r.replace("$_DATA", data);
       return r;
   }

    public static void printHighcharts(double[] l, List<String> soglie, Highcharts highchart) {
        DecimalFormat df = new DecimalFormat(highchart.getDecimalFormatData());
        String data = "[";
        for(int i=0; i < l.length; i++) {
            data += df.format(l[i]);
            if ( i < l.length ) {
                data += (",");
            }
        }
        data += "]";

        String categories = "[" + soglie.toString() + "]";

        data += "]";

        highchart.setData(data);
        highchart.setCategories(categories);
        System.out.println("Highcharts: " + highchart.getChart());
    }

    public static void printHighcharts(int[] l, List<String> soglie, Highcharts highchart) {
        DecimalFormat df = new DecimalFormat(highchart.getDecimalFormatData());
        String data = "[";
        for(int i=0; i < l.length; i++) {
            data += df.format(l[i]);
            if ( i < l.length ) {
                data += (",");
            }
        }
        data += "]";

        String categories = "[" + soglie.toString() + "]";

        data += "]";

        highchart.setData(data);
        highchart.setCategories(categories);
        System.out.println("Highcharts: " + highchart.getChart());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public static String getS() {
        return s;
    }

    public static void setS(String s) {
        Highcharts.s = s;
    }

    public String getDecimalFormatData() {
        return decimalFormatData;
    }

    public void setDecimalFormatData(String decimalFormatData) {
        this.decimalFormatData = decimalFormatData;
    }

    public String getDecimalFormatCategories() {
        return decimalFormatCategories;
    }

    public void setDecimalFormatCategories(String decimalFormatCategories) {
        this.decimalFormatCategories = decimalFormatCategories;
    }
}
