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
  // Generierung des Atmosphären-Typs
  public void generate() {
    Random rdm = new Random();
    atmosphere_type = rdm.nextInt(4);
  }

  // Generierung des Wetter-Typs
  public void generateW() {
    Random rdm = new Random();
    weather_type = rdm.nextInt(8);
  }
  public int getWeatherType()
  {
    return weather_type;
  }
  
  // Checken, ob die generierten Werte logisch zusammenpassen | falls nicht, Werte ändern
  public void checkWeatherType(int surf) {
    // Falls keine Atmosphäre, kosmische Strahlung
    if(atmosphere_type == 0) {
      weather_type = 4;
    }
    
    if (!(atmosphere_type == 0 || atmosphere_type == 1)) {
      // Solange kosmische Strahlung, neue Generation
      while(weather_type == 4) {
        this.generateW();
      }
    }
    switch (surf) {
      case 0:
        // Falls Stein-Oberfläche, keine Probleme
        break;
      case 1:
        // Falls Wasser-Oberfläche, keine Vertrocknung möglich
        if(weather_type == 7) {
          while(weather_type == 7) {
            this.generateW();
          }
        }
        break;
      case 2:
        // Falls Gasriese, kein Schneesturm und keine Vertrocknung möglich
        if(weather_type == 5 || weather_type == 7) {
          while(weather_type == 5 || weather_type == 7) {
            this.generateW();
          }
        }
        break;
      case 3:
        // Falls Eisplanet, keine extreme Hitze möglich
        if(weather_type == 2) {
          while(weather_type == 2) {
            this.generateW();
          }
        }
        break;
    }
  }
  public int getAtmosphereType()
  {
    return atmosphere_type;
  }
}
