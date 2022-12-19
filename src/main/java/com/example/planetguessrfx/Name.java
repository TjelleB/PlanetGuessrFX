package com.example.planetguessrfx;
import java.util.Random;
public class Name extends Generator {
  private final String[] greek_letters = {"","", "", "Alpha", "Beta", "Gamma", "Delta"};
  private final String[] name = {"Spe", "Arion", "Arkas", "Orbitar", "Taphao Thong",
          "Taphao Kaew", "Dimidium", "Galileo", "Brahe",
          "Lipperhey", "Janssen", "Harriot", "Ægir"};
  private final String[] letter = {"", "", "", "a", "b", "c"};
  private final String[] numbers = {"","", "", "I", "II", "III", "IV", "V"};
  private String finalName;
  @Override
  public void generate() {
    // Auswahl des griechischen Buchstabens
    Random rdm = new Random(); 
    int index = rdm.nextInt(greek_letters.length); 
    finalName = (greek_letters[index]) + " ";
    // Auswahl des Namens
    index = rdm.nextInt(name.length);
    finalName += (name[index]);
    // Auswahl des Buchstabens
    index = rdm.nextInt(letter.length);
    if(index >=3){finalName +=  "-";}
    finalName += (letter[index]);
    // Auswahl der Nummer
    index = rdm.nextInt(numbers.length);
    if(index >=3){finalName +=  " ";}
    finalName +=(numbers[index]);
  }
  public String getName() {
    this.generate();
    return finalName;
  }
}
