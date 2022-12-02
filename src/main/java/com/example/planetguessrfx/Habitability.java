package com.example.planetguessrfx;
/*
Ersteller: Frederick
*/
import java.util.Random;
public class Habitability extends Generator {
  private Boolean habitable;
  
  public Habitability() {
    this.generate();
  }
  
  @Override
  public void generate() {
    Random rdm = new Random();
    habitable = rdm.nextBoolean();
  }

  //checkHab() = @Ivo
  public void checkHab(int atmos, int surf) {
    if(atmos == 0) {
      habitable = false;
    }
    if(surf == 2) {
      habitable = false;
    }
  }
  public boolean getHabitable()
  {
    return habitable;
  }
}