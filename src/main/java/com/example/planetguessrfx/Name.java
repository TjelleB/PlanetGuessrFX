package com.example.planetguessrfx;
import java.util.Random;
public class Name extends Generator {
  private final String[] greek_letters = {"","", "", "Alpha", "Beta", "Gamma", "Delta"};
  private final String[] numbers = {"","", "", "I", "II", "III", "IV", "V"};
  private final String[] letter = {"", "", "", "a", "b", "c"};
  private final String[] name = {"Spe", "Arion", "Arkas", "Orbitar", "Taphao Thong", "Taphao Kaew", "Dimidium", "Galileo", "Brahe", "Lipperhey", "Janssen", "Harriot", "Ægir"};
  private String finalName;
  @Override
  public void generate() {
    Random rdm = new Random(); 
    int index = rdm.nextInt(greek_letters.length); 
    finalName = (greek_letters[index]) + " ";
    index = rdm.nextInt(name.length);
    finalName += (name[index]);
    index = rdm.nextInt(letter.length);
    if(index >=3){finalName +=  "-";}
    finalName += (letter[index]);
    index = rdm.nextInt(numbers.length);
    finalName +=(numbers[index]);
  }
  public String getName() {
    this.generate();
    return finalName;
  }
}