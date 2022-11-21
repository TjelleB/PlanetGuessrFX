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
    private final Resources res = new Resources();
    private final Habitability hab = new Habitability();
    protected final Name name = new Name();
    private String bh1;
    private String bh2;
    private String h11;
    private String h12;
    private String h2;
    private String h3;

    //Methoden
    public void calcFinalVal() {
        this.generate();
        int resourceval = this.getResourcesVal();
        int baseval = 50000000;
        double m_baseval = baseval * this.calcFinalValueMultiplier();
        value = (long) (m_baseval + resourceval);
    }
    public double calcFinalValueMultiplier() {
        //Berechnen der einzelnen Multiplikatoren
        double m_atmosphere = this.getM_Atmosphere();
        double m_surface = this.getM_Surface();
        double m_star = this.getM_Star();
        double m_resources = this.getM_Resources();
        double m_habitability = this.getM_Habitability();
        double m_weather = this.getM_Weather();
        //Berechnen und zurueckgeben vom finalen Multiplikator
        double m_final;
        if (this.surf.getType() == 0) {
            m_final = m_surface * m_star
                    * m_atmosphere * m_resources
                    * m_habitability * m_weather;
        } else {
            m_final = m_star
                    * m_atmosphere * m_resources;
        }
        return m_final;
    }
    public void generate() {
        surf.generate();
        atmos.generate();
        atmos.generateW();
        star.generate();
        res.generate();
        hab.generate();
    }
    public double getM_Surface() {
        surf.checkType(atmos.getAtmosphereType());
        switch (surf.getType()) {
            case 0 -> bh1 = "Stein";
            case 1 -> bh1 = "Wasser";
            case 2 -> bh1 = "Gas";
            case 3 -> bh1 = "Eis";
        }
        double[] m_surface_array = {1, 0.9, 0.7, 0.6};  //Alle möglichen Multiplikatoren
        return m_surface_array[surf.getType()];         //Rueckgabe vom Multiplikator, Bestimmung via Abfrage vom generierten Wert
    }
    public double getM_Star() {
        switch (star.getType()) {
            case 0 -> bh2 = "Zwerg";
            case 1 -> bh2 = "Riese";
            case 2 -> bh2 = "Neutron";
            case 3 -> bh2 = "Doppel";
        }
        double[] m_star_array = {1, 0.9, 0.3, 0.9};     //Alle möglichen Multiplikatoren
        return m_star_array[star.getType()];            //Rueckgabe vom Multiplikator, Bestimmung via Abfrage vom generierten Wert
    }
    public double getM_Atmosphere() {
        switch (atmos.getAtmosphereType()) {
            case 0 -> h11 = "Keine";
            case 1 -> h11 = "Schwach";
            case 2 -> h11 = "Normal";
            case 3 -> h11 = "Stark";
        }
        double[] m_atmosphere_array = {0.2, 0.6, 1, 1.5};   //Alle möglichen Multiplikatoren
        return m_atmosphere_array[atmos.getAtmosphereType()]; //Rueckgabe vom Multiplikator, Bestimmung via Abfrage vom generierten Wert
    }
    public double getM_Weather() {
        atmos.checkWeatherType(surf.getType());
        switch (atmos.getWeatherType()) {
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
        hab.checkHab(atmos.getAtmosphereType(), surf.getType());
        if(hab.getHabitable()) {
            h2 = "Ja";
            return 1;
        } else {
            h2 = "Nein";
            return 0.6;
        }
    }
    public double getM_Resources() {
        double[] m_resources_array = {0.3, 0.7, 1, 1.4};  //Alle möglichen Multiplikatoren
        return m_resources_array[res.getType()];    //Rueckgabe vom Multiplikator, Bestimmung via Abfrage vom generierten Wert
    }
    public int getResourcesVal() {
        int val = 0;
        for (int t = 0; t < res.getArrayLength(); t++) {
            val += res.getResources(t) * res.getValues(t);
        }
        return val;
    }
    public int detImg() {
        if (atmos.getAtmosphereType() == 0) {
            return 4;
        } else {
            return this.surf.getType();
        }
    }
    public int calcPts(long sc) {
        long[] lwrBorders = new long[10];
        long[] uprBorders = new long[10];
        double multi = 0;
        for (int i = 0; i < lwrBorders.length; i++) {
            multi += 0.01;
            lwrBorders[i] = (long) (value - value * multi);
            uprBorders[i] = (long) (value + value * multi);
        }
        double reduce = 0;
        if(sc < lwrBorders[lwrBorders.length-1] || sc > uprBorders[uprBorders.length-1]) {
            return 0;
        } else {
            for (int v = 0; v < uprBorders.length; v++) {
                reduce += 0.1;
                if (sc == value) {
                    return (int) (value / 10000);
                } else if (sc >= lwrBorders[v] && sc <= uprBorders[v]) {
                    return (int) ((value - (value * reduce)) / 10000);
                }
            }
        }
        return 0;
    }
    public String getBh1() {return bh1;}
    public String getBh2() {return bh2;}
    public String getH11() {return h11;}
    public String getH12() {return h12;}
    public String getH2() {return h2;}
} // end of class Math