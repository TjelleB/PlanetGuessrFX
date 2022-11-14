package com.example.planetguessrfx;
/*
Ersteller: Frederick
Wetter:
0 = Moderat
1 = Stürmisch
2 = Extreme Hitze
3 = Chemischer Regen
4 = Kosmische Strahlung
5 = Schneesturm
6 = Permafrost
7 = Vertrocknet

Atmosphäre:
0 = Keine
1 = Schwach
2 = Mittel
3 = Stark
*/
import java.util.Random;
public class Atmosphere extends Generator {
  private int weather_type;
  private int atmosphere_type;
  
  public Atmosphere() {
    this.generate();
  }
  
  @Override
  public void generate() {
    Random rdm1 = new Random();
    atmosphere_type = rdm1.nextInt(4);
    Random rdm2 = new Random();
    weather_type = rdm2.nextInt(8);
  }
  
  public int getWeatherType()
  {
    return weather_type;
  }
  
  public int getAtmosphereType()
  {
    return atmosphere_type;
  }
}