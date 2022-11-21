package com.example.planetguessrfx;/*package com.example.planetguessrfx;*/
import java.util.Random;
public class Name extends Generator {
  
  private String[] names = {"Leon", "Kamela", "Marcel", "Hillary", "Edward", "Lisa", "Michael", "Marie", "Alexander", "Laura", "Felix", "Clara", "Lucas", "Charlotte", "Richard", "Elisabeth", "Julian", "Leonie", "Anton", "Emma", "Jeremy", "Johanna", "Joseph", "Leni", "Adam", "Anna",};
  private String name;
  
  public Name()
  {
    this.setNames(names);
    this.setName(name);
  }
  // start methods
  
  @Override
  public void generate()
  {
    Random rdm = new Random(); 
    int index = rdm.nextInt(names.length); 
    name = (names[index]);
  }
  public String[] getNames() {
    return names;
  }

  public void setNames(String[] namesNew) {
    names = namesNew;
  }

  public String getName() {
    return name;
  }

  public void setName(String nameNew) {
    name = nameNew;
  }

  // end methods
}
