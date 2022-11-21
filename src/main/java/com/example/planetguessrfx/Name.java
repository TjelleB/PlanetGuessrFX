/*package com.example.planetguessrfx;*/
import java.util.Random;
public class Name extends Generator {
  
  private String[] names = {"Leon", "Kamela", "Marcel", "Hillary", "Edward", "Lisa", "Michael", "Marie", "Alexander", "Laura", "Felix", "Clara", "Lucas", "Charlotte", "Richard", "Elisabeth", "Julian", "Leonie", "Anton", "Emma", "Jeremy", "Johanna", "Joseph", "Leni", "Adam", "Anna", "Bryan", "Lea", "Marc", "Olivia", "Jeff", "Sophia", "Ethan", "Emily", "Gregor", "Ellie", "Manuel", "Grace", "Henry", "Mia", "Sebastian", "Alina", "Jackson", "Isabella", "Jakob", "Ada", "Achim", "Karolin", "Alfred", "Ursula", "Bernd", "Valentina", "Norbert", "Vanessa", "Robert", "Theresa", "Thomas", "Gilberta", "Uwe", "Magret"};
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
