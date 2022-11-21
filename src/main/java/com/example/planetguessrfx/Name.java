package com.example.planetguessrfx;
import java.util.Random;
public class Name extends Generator {
  private String[] greek_letters = {"Alpha", "Beta", "Gamma", "Delta"};
  private String[] numbers = {"I", "II", "III", "IV", "V"};
  private String name;
  @Override
  public void generate() {
    Random rdm = new Random(); 
    int index = rdm.nextInt(greek_letters.length); 
    name = (greek_letters[index]);
    index = rdm.nextInt(numbers.length);
    name +=  "-" + (numbers[index]);
  }
  public String getName() {
    return name;
  }
}