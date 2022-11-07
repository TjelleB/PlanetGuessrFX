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

    //Methoden
    public void calcFinalVal() {
        int resourceval = this.calcResourcesVal();
        int baseval = 50000000;
        double m_baseval = baseval * this.calcFinalValueMultiplier();
        value = (long) (m_baseval + resourceval);
    }
    public double calcFinalValueMultiplier() {
        //Berechnen der einzelnen Multiplikatoren
        double m_surface = this.calcM_Surface();
        double m_star = this.calcM_Star();
        double m_atmosphere = this.calcM_Atmosphere();
        double m_resources = this.calcM_Resources();
        double m_habitability = this.calcM_Habitability();
        double m_weather = this.calcM_Weather();
        //Berechnen und zurueckgeben vom finalen Multiplikator
        double m_final;
        m_final = m_surface * m_star
                * m_atmosphere * m_resources
                * m_habitability * m_weather;
        return m_final;
    }
    public double calcM_Surface() {
        double[] m_surface_array = {1, 0.9, 0.7, 0.6};  //Alle möglichen Multiplikatoren
        surf.generate();                                //Wert wird generiert
        return m_surface_array[surf.getType()];         //Rueckgabe vom Multiplikator, Bestimmung via Abfrage vom generierten Wert
    }
    public double calcM_Star() {
        double[] m_star_array = {1, 0.9, 0.3, 0.9};     //Alle möglichen Multiplikatoren
        star.generate();                                //Wert wird generiert
        return m_star_array[star.getType()];            //Rueckgabe vom Multiplikator, Bestimmung via Abfrage vom generierten Wert
    }
    public double calcM_Atmosphere() {
        double[] m_atmosphere_array = {0.2, 0.6, 1, 1.5};   //Alle möglichen Multiplikatoren
        atmos.generateAtmosphere();                         //Wert wird generiert
        return m_atmosphere_array[atmos.getAtmosphereType()]; //Rueckgabe vom Multiplikator, Bestimmung via Abfrage vom generierten Wert
    }
    public double calcM_Weather() {
        double[] m_weather_array = {1.1, 0.9, 0.65, 0.4, 0.3, 0.8, 0.7, 0.5};   //Alle möglichen Multiplikatoren
        atmos.generateWeather();                        //Wert wird generiert
        return m_weather_array[atmos.getWeatherType()]; //Rueckgabe vom Multiplikator, Bestimmung via Abfrage vom generierten Wert
    }
    public double calcM_Habitability() {
        hab.generate();
        if(hab.getHabitable()) {
            return 1;
        } else {
            return 0.6;
        }
    }
    public double calcM_Resources() {
        double[] m_resources_array = {0.3, 0.7, 1, 1.4};  //Alle möglichen Multiplikatoren
        res.generateType();                         //Wert wird generiert
        return m_resources_array[res.getType()];    //Rueckgabe vom Multiplikator, Bestimmung via Abfrage vom generierten Wert
    }
    public int calcResourcesVal() {
        res.generateResources();
        res.generateValues();
        int val = 0;
        for (int t = 0; t < res.getArrayLength(); t++) {
            val += res.getResources(t) * res.getValues(t);
        }
        return val;
    }
} // end of class Math