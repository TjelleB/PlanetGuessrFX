package com.example.planetguessrfx;
/*
Ersteller: Frederick
*/
public abstract class Generator {
  private int planetID;
  
  public Generator() {
    this.setPlanetID(planetID);
  }

  // Abstrakte Methode, kein Algorithmus vorzugeben
  protected abstract void generate();

  public void setPlanetID(int planetIDNew) {
    planetID = planetIDNew;
  }
}
