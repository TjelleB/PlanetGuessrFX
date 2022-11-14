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
  
  public boolean getHabitable()
  {
    return habitable;
  }
}