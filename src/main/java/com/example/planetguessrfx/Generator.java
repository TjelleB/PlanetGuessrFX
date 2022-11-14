package com.example.planetguessrfx;
/*
Ersteller: Frederick
*/

public abstract class Generator {
  
  // start attributes
  private int planetID;
  // end attributes
  
  public Generator()
  {
    this.setPlanetID(planetID);
  }
  // start methods
  
  protected abstract void generate();
  
  
  public int getPlanetID() {
    return planetID;
  }

  public void setPlanetID(int planetIDNew) {
    planetID = planetIDNew;
  }

  // end methods
}