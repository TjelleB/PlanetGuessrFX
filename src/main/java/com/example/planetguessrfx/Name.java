/*package com.example.planetguessrfx;*/
import java.util.Random;
public class Name extends Generator {
  
  // start attributes
  private String[] greek_letters = {"Alpha", "Beta", "Gamma", "Delta"};
  private String[] numbers = {"I", "II", "III", "IV", "V"};
  private String name;
  // end attributes
  
  
  
  public Name()
  {
    this.setGreek_letters(greek_letters);
    this.setName(name);
    this.setNumbers(numbers);
  }
  // start methods
  
  @Override
  public void generate()
  {
    Random rdm = new Random(); 
    int index = rdm.nextInt(greek_letters.length); 
    name = (greek_letters[index]);
    
    Random rdm2 = new Random();
    int index2 = rdm2.nextInt(numbers.length);
    name += (numbers[index2]);
  }
  
  

  public String getName() {
    return name;
  }

  public void setName(String nameNew) {
    name = nameNew;
  }

  public String[] getNumbers() {
    return numbers;
  }

  public void setNumbers(String[] numbersNew) {
    numbers = numbersNew;
  }

  public String[] getGreek_letters() {
    return greek_letters;
  }

  public void setGreek_letters(String[] greek_lettersNew) {
    greek_letters = greek_lettersNew;
  }

  // end methods
}
