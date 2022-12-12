package com.example.planetguessrfx;
/*
@Ivo Härtel
 */
public class Math {
    //Attribute
    protected long value;
    private final Surface surf = new Surface();
    private final Star star = new Star();
    private final Atmosphere atmos = new Atmosphere();
    protected final Resources res = new Resources();
    private final Habitability hab = new Habitability();
    protected final Name name = new Name();
    private String bh1;
    private String bh2;
    private String h11;
    private String h12;
    private String h2;

    //Methoden
    public void calcFinalVal() {
        this.generate(); //Alle Random-Wert werden neu generiert
        int baseval = 50000000; //Basiswert des Planeten
        double m_baseval = baseval * this.calcFinalValueMultiplier(); //Basiswert * kompletter berechneter Multiplikator
        value = (long) (m_baseval + this.getResourcesVal()); //Endwert = angepasster Planetenwert + kompletter Ressourcenwert des Planeten
    }
    public double calcFinalValueMultiplier() {
        //Aufrufen der einzelnen Funktionen zum Berechnen der Multiplikatoren
        double m_atmosphere = this.getM_Atmosphere();
        double m_surface = this.getM_Surface();
        double m_star = this.getM_Star();
        double m_resources = this.getM_Resources();
        double m_habitability = this.getM_Habitability();
        double m_weather = this.getM_Weather();
        //Berechnen und Zurueckgeben vom finalen Multiplikator
        double m_final;
        if (this.atmos.getAtmosphereType() != 0) { //Berechnung mit Multiplikatoren, die nur bei einer vorhandenen Atmosphäre logisch sind
            m_final = m_surface * m_star
                    * m_atmosphere * m_resources
                    * m_habitability * m_weather;
        } else {
            m_final = m_star
                    * m_atmosphere * m_resources;
        }
        return m_final;
    }

    //Generiere jeden Random-Wert neu
    public void generate() {
        surf.generate();
        atmos.generate();
        atmos.generateW();
        star.generate();
        res.generate();
        hab.generate();
    }


    public double getM_Surface() {
        surf.checkType(atmos.getAtmosphereType()); //Checkt, ob der Oberflächentyp mit der Atmosphäre kompatibel ist
        switch (surf.getType()) { //Bestimmung des Strings für die Tipps
            case 0 -> bh1 = "Stein";
            case 1 -> bh1 = "Wasser";
            case 2 -> bh1 = "Gas";
            case 3 -> bh1 = "Eis";
        }
        double[] m_surface_array = {1, 0.9, 0.7, 0.6};  //Alle möglichen Multiplikatoren
        return m_surface_array[surf.getType()];         //Rueckgabe vom Multiplikator, Bestimmung via Abfrage vom generierten Wert
    }
    public double getM_Star() {
        switch (star.getType()) { //Bestimmung des Strings für die Tipps
            case 0 -> bh2 = "Zwerg";
            case 1 -> bh2 = "Riese";
            case 2 -> bh2 = "Neutron";
            case 3 -> bh2 = "Doppel";
        }
        double[] m_star_array = {1, 0.9, 0.3, 0.9};     //Alle möglichen Multiplikatoren
        return m_star_array[star.getType()];            //Rueckgabe vom Multiplikator, Bestimmung via Abfrage vom generierten Wert
    }
    public double getM_Atmosphere() {
        switch (atmos.getAtmosphereType()) { //Bestimmung des Strings für die Tipps
            case 0 -> h11 = "Keine";
            case 1 -> h11 = "Schwach";
            case 2 -> h11 = "Normal";
            case 3 -> h11 = "Stark";
        }
        double[] m_atmosphere_array = {0.2, 0.6, 1, 1.5};   //Alle möglichen Multiplikatoren
        return m_atmosphere_array[atmos.getAtmosphereType()]; //Rueckgabe vom Multiplikator, Bestimmung via Abfrage vom generierten Wert
    }
    public double getM_Weather() {
        atmos.checkWeatherType(surf.getType()); //Checkt, ob das Wetter mit der Atmosphäre kompatibel ist
        switch (atmos.getWeatherType()) { //Bestimmung des Strings für die Tipps
            case 0 -> h12 = "Moderat";
            case 1 -> h12 = "Stürmisch";
            case 2 -> h12 = "Extreme Hitze";
            case 3 -> h12 = "Chemischer Regen";
            case 4 -> h12 = "Kosmische Strahlung";
            case 5 -> h12 = "Schneesturm";
            case 6 -> h12 = "Permafrost";
            case 7 -> h12 = "Vertrocknet";
        }
        double[] m_weather_array = {1.1, 0.9, 0.65, 0.4, 0.3, 0.8, 0.7, 0.5};   //Alle möglichen Multiplikatoren
        return m_weather_array[atmos.getWeatherType()]; //Rueckgabe vom Multiplikator, Bestimmung via Abfrage vom generierten Wert
    }
    public double getM_Habitability() {
        hab.checkHab(atmos.getAtmosphereType(), surf.getType()); //Checkt, ob die Bewohnbarkeit mit Oberfläche und Atmosphäre kompatibel ist
        if(hab.getHabitable()) {
            h2 = "Ja"; //String für Tipp
            return 1; //Multiplikator
        } else {
            h2 = "Nein"; //String für Tipp
            return 0.6; //Multiplikator
        }
    }
    public double getM_Resources() {
        double[] m_resources_array = {0.3, 0.7, 1, 1.4};  //Alle möglichen Multiplikatoren
        return m_resources_array[res.getType()];    //Rueckgabe vom Multiplikator, Bestimmung via Abfrage vom generierten Wert
    }

    //Berechnet den totalen Wert aller Ressourcen des Planeten
    public int getResourcesVal() {
        int val = 0;
        for (int t = 0; t < res.getArrayLength(); t++) {
            val += res.getValues(t) * res.getAmounts(t); //Wert der Ressource * vorhandene Menge
        }
        return val;
    }

    //Bestimmt welches Gif geladen wird
    public int detImg() {
        if (atmos.getAtmosphereType() == 0) {
            return 4;
        } else {
            return surf.getType();
        }
    }

    //Berechnen der Punkte mit dem Wert des Users und seinem reduzierten Tipp-Multiplikator
    public int calcPts(double redM, long sc) {
        //Arrays zum Speichern der Grenzwert für die Punkteberechnung
        long[] lwrBorders = new long[100];
        long[] uprBorders = new long[100];
        //Schritte in 1% bis 25% (25% = Maximal erlaubte Abweichung zum tatsächlichen Wert)
        double multi = 0;
        for (int i = 0; i < lwrBorders.length; i++) {
            multi += 0.01;
            lwrBorders[i] = (long) (value - value * multi);
            uprBorders[i] = (long) (value + value * multi);
        }
        double reduce = -0.01;
        //Wenn der score außerhalb der 25 % liegt, wird 0 als Punkte zurückgegeben
        if(sc < lwrBorders[lwrBorders.length-1] || sc > uprBorders[uprBorders.length-1]) {
            return 0;
        } else {
            //Der score wird mit jedem Grenzwert verglichen, jeder den er überschreitet, reduziert die Punkte um 2% (Max 50%)
            for (int v = 0; v < uprBorders.length; v++) {
                reduce += 0.01;
                if (sc == value) {
                    return (int) ((value / 10000) * redM);
                } else if (sc >= lwrBorders[v] && sc <= uprBorders[v]) {
                    return (int) (((value - (value * reduce)) / 10000) * redM);
                }
            }
        }
        return 0;
    }
    //Tipp-Strings
    public String getBh1() {return bh1;}
    public String getBh2() {return bh2;}
    public String getH11() {return h11;}
    public String getH12() {return h12;}
    public String getH2() {return h2;}
} // end of class Math