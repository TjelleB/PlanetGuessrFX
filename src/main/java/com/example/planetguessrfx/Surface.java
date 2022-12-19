package com.example.planetguessrfx;
/*
Ersteller: Frederick
0 = Stein
1 = Wasser
2 = Gas
3 = Eis
*/
import java.util.Random;
public class Surface extends Generator {
  private int type;
  
  public Surface() {
    this.generate();
  }
  
  @Override
  public void generate() {
    Random rdm = new Random();
    type = rdm.nextInt(4);
  }

  //@Ivo >Checken, ob die generierten Werte logisch zusammenpassen | falls nicht, Werte ändern
  public void checkType(int atmos) {
    if (atmos == 0) {
      type = 0;
    }
  }
  public int getType()
  {
    return type;
  }
}
