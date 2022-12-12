package com.example.planetguessrfx;
/*
Ersteller: Frederick
0 = Zwerg
1 = Riese
2 = Neutronenstern
3 = Doppelstern
*/

import java.util.Random;

public class Star extends Generator {

  // start attributes
  private int type;
  // end attributes
  
  public Star() {
    this.generate();
  }
  
  
  @Override
  // Generator
  public void generate() {
    Random rdm = new Random();
    type = rdm.nextInt(4);
  }
  
  // Get
  public int getType()
  {
    return type;
  }
}
