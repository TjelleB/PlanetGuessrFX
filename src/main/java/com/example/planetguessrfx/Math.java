package com.example.planetguessrfx;
public class Math {
    //>Attribute
    protected long value;
    private final Surface surf = new Surface();
    private final Star star = new Star();
    private final Atmosphere atmos = new Atmosphere();
    protected final Resources res = new Resources();
    private final Habitability hab = new Habitability();
    protected final Name name = new Name();
    private String baseHint1;
    private String baseHint2;
    private String hint1Part1;
    private String hint1Part2;
    private String hint2;

    //>Methoden
    //@Ivo >Berechnet den finalen Wert eines Planeten
    public void calculateValue() {
        this.generate(); //Alle Random-Wert werden neu generiert
        int basevalue = 50000000;
        double multipliedBasevalue = basevalue * this.calculateMultiplier(); //Basiswert * kompletter berechneter Multiplikator
        value = (long) (multipliedBasevalue + this.getResourcesValue()); //Endwert = angepasster Planetenwert + kompletter Ressourcenwert des Planeten
    }

    //@Ivo >Berechnet Multiplikator
    public double calculateMultiplier() {
        //>Berechnen und Zurueckgeben vom finalen Multiplikator
        if (this.atmos.getAtmosphereType() != 0) { //>Berechnung mit Multiplikatoren, die nur bei einer Atmosphäre vorhanden sind
            return this.getSurfaceMultiplier() * this.getStarMultiplier()
                    * this.getAtmosphereMultiplier() * this.getResourceMultiplier()
                    * this.getHabitableMultiplier() * this.getWeatherMultiplier();
        } else {
            return this.getStarMultiplier()
                    * this.getAtmosphereMultiplier() * this.getResourceMultiplier();
        }
    }

    //@Ivo >Generiere jeden Random-Wert neu
    public void generate() {
        surf.generate();
        atmos.generate();
        atmos.generateW();
        star.generate();
        res.generate();
        hab.generate();
    }

    //@Ivo
    public double getSurfaceMultiplier() {
        surf.checkType(atmos.getAtmosphereType()); //Checkt, ob der Oberflächentyp mit der Atmosphäre kompatibel ist
        switch (surf.getType()) { //Bestimmung des Strings für die Tipps
            case 0 -> baseHint1 = "Stein";
            case 1 -> baseHint1 = "Wasser";
            case 2 -> baseHint1 = "Gas";
            case 3 -> baseHint1 = "Eis";
        }
        double[] m_surface_array = {1, 0.9, 0.7, 0.6};  //Alle möglichen Multiplikatoren
        return m_surface_array[surf.getType()];         //Rueckgabe vom Multiplikator, Bestimmung via Abfrage vom generierten Wert
    }

    //@Ivo
    public double getStarMultiplier() {
        switch (star.getType()) { //Bestimmung des Strings für die Tipps
            case 0 -> baseHint2 = "Zwerg";
            case 1 -> baseHint2 = "Riese";
            case 2 -> baseHint2 = "Neutron";
            case 3 -> baseHint2 = "Doppel";
        }
        double[] m_star_array = {1, 0.9, 0.3, 0.9};     //Alle möglichen Multiplikatoren
        return m_star_array[star.getType()];            //Rueckgabe vom Multiplikator, Bestimmung via Abfrage vom generierten Wert
    }

    //@Ivo
    public double getAtmosphereMultiplier() {
        switch (atmos.getAtmosphereType()) { //Bestimmung des Strings für die Tipps
            case 0 -> hint1Part1 = "Keine";
            case 1 -> hint1Part1 = "Schwach";
            case 2 -> hint1Part1 = "Normal";
            case 3 -> hint1Part1 = "Stark";
        }
        double[] m_atmosphere_array = {0.2, 0.6, 1, 1.5};   //Alle möglichen Multiplikatoren
        return m_atmosphere_array[atmos.getAtmosphereType()]; //Rueckgabe vom Multiplikator, Bestimmung via Abfrage vom generierten Wert
    }

    //@Ivo
    public double getWeatherMultiplier() {
        atmos.checkWeatherType(surf.getType()); //Checkt, ob das Wetter mit der Atmosphäre kompatibel ist
        switch (atmos.getWeatherType()) { //Bestimmung des Strings für die Tipps
            case 0 -> hint1Part2 = "Moderat";
            case 1 -> hint1Part2 = "Stürmisch";
            case 2 -> hint1Part2 = "Extreme Hitze";
            case 3 -> hint1Part2 = "Chemischer Regen";
            case 4 -> hint1Part2 = "Kosmische Strahlung";
            case 5 -> hint1Part2 = "Schneesturm";
            case 6 -> hint1Part2 = "Permafrost";
            case 7 -> hint1Part2 = "Vertrocknet";
        }
        double[] m_weather_array = {1.1, 0.9, 0.65, 0.4, 0.3, 0.8, 0.7, 0.5};   //Alle möglichen Multiplikatoren
        return m_weather_array[atmos.getWeatherType()]; //Rueckgabe vom Multiplikator, Bestimmung via Abfrage vom generierten Wert
    }

    //@Ivo
    public double getHabitableMultiplier() {
        hab.checkHab(atmos.getAtmosphereType(), surf.getType()); //Checkt, ob die Bewohnbarkeit mit Oberfläche und Atmosphäre kompatibel ist
        if(hab.getHabitable()) {
            hint2 = "Ja"; //String für Tipp
            return 1; //Multiplikator
        } else {
            hint2 = "Nein"; //String für Tipp
            return 0.6; //Multiplikator
        }
    }

    //@Ivo
    public double getResourceMultiplier() {
        double[] m_resources_array = {0.3, 0.7, 1, 1.4};  //Alle möglichen Multiplikatoren
        return m_resources_array[res.getType()];    //Rueckgabe vom Multiplikator, Bestimmung via Abfrage vom generierten Wert
    }

    //@Ivo >Berechnet den totalen Wert aller Ressourcen des Planeten
    public int getResourcesValue() {
        int value = 0;
        for (int t = 0; t < res.getArrayLength(); t++) {
            value += res.getValues(t) * res.getAmounts(t); //Wert der Ressource * vorhandene Menge
        }
        return value;
    }

    //@Ivo >Bestimmt welches Gif geladen wird
    public int determineImage() {
        if (atmos.getAtmosphereType() == 0) {
            return 4;
        } else {
            return surf.getType();
        }
    }

    //@Ivo >Berechnen der Punkte mit dem Wert des Users und seinem reduzierten Tipp-Multiplikator
    public int calculateScore(double reducedHintMultiplier, long score) {
        //Arrays zum Speichern der Grenzwert für die Punkteberechnung
        long[] lwrBorders = new long[100];
        long[] uprBorders = new long[100];
        //Schritte in 1 % bis 100 %
        double multiplier = -0.01;
        for (int i = 0; i < lwrBorders.length; i++) {
            multiplier += 0.01;
            lwrBorders[i] = (long) (value - value * multiplier);
            uprBorders[i] = (long) (value + value * multiplier);
        }
        double reducedMultiplier = -0.01;
        //Wenn der Score außerhalb der 100 % liegt, wird 0 als Score zurückgegeben
        if(score < lwrBorders[lwrBorders.length-1] || score > uprBorders[uprBorders.length-1]) {
            return 0;
        } else {
            //Der Score wird mit jedem Grenzwert verglichen, jeder den er überschreitet, reduziert die Punkte um 2% (Max 50%)
            for (int v = 0; v < uprBorders.length; v++) {
                reducedMultiplier += 0.01;
                if (score == value) {
                    return (int) ((value / 10000) * reducedHintMultiplier);
                } else if (score >= lwrBorders[v] && score <= uprBorders[v]) {
                    return (int) (((value - (value * reducedMultiplier)) / 10000) * reducedHintMultiplier);
                }
            }
        }
        return 0;
    }
    //@Ivo >Tipp-Strings
    public String getBaseHint1() {return baseHint1;}
    public String getBaseHint2() {return baseHint2;}
    public String getHint1Part1() {return hint1Part1;}
    public String getHint1Part2() {return hint1Part2;}
    public String getHint2() {return hint2;}
} // end of class Math