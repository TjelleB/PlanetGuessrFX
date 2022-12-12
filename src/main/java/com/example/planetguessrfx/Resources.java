package com.example.planetguessrfx;
/*
Ersteller: Frederick
*/
import java.util.Random;
public class Resources extends Generator {
  // Häufigkeits-Konstanten
  private static final int VERY_FREQUENTLY = 1001;
  private static final int FREQUENTLY = 751;
  private static final int COMMON = 651;
  private static final int MODERATE = 501;
  private static final int RARE = 351;
  private static final int VERY_RARE = 301;
  private static final int EXTREMELY_RARE = 101;
  
  private final int[] amounts = new int[15]; // Ressources-Mengen-Array
  private final double[] values = new double[15]; //Ressources-Werte-Array
  private int type;
  
  public Resources() {
    this.generate();
  }
  
  @Override
  public void generate()
  {
    // Generierung des Ressources-Menge
    Random rdm1 = new Random();
    amounts[0] = rdm1.nextInt(FREQUENTLY);
    amounts[1] = rdm1.nextInt(MODERATE);
    amounts[2] = rdm1.nextInt(RARE);
    amounts[3] = rdm1.nextInt(VERY_RARE);
    amounts[4] = rdm1.nextInt(VERY_RARE);
    amounts[5] = rdm1.nextInt(COMMON);
    amounts[6] = rdm1.nextInt(COMMON);
    amounts[7] = rdm1.nextInt(EXTREMELY_RARE);
    amounts[8] = rdm1.nextInt(MODERATE);
    amounts[9] = rdm1.nextInt(MODERATE);
    amounts[10] = rdm1.nextInt(FREQUENTLY);
    amounts[11] = rdm1.nextInt(VERY_RARE);
    amounts[12] = rdm1.nextInt(VERY_FREQUENTLY);
    amounts[13] = rdm1.nextInt(MODERATE);
    amounts[14] = rdm1.nextInt(MODERATE);

    // Generierung der Ressources-Werte
    Random rdm2 = new Random();
    values[0] = (rdm2.nextInt(5-1)+1)/10.0;
    values[1] = rdm2.nextInt(87-71)+71;
    values[2] = rdm2.nextInt(710-640)+640;
    values[3] = rdm2.nextInt(5900-5640)+5640;
    values[4] = rdm2.nextInt(3460-2987)+2987;
    values[5] = (rdm2.nextInt(32-19)+19)/100.0;
    values[6] = (rdm2.nextInt(32-25)+25)/10.0;
    values[7] = rdm2.nextInt(35000-30000)+30000;
    values[8] = rdm2.nextInt(53-40)+40;
    values[9] = rdm2.nextInt(112-93)+93;
    values[10] = rdm2.nextInt(15-9)+9;
    values[11] = rdm2.nextInt(19000-13000)+13000;
    values[12] = 3;
    values[13] = rdm2.nextInt(41-32)+32;
    values[14] = rdm2.nextInt(64-50)+50;
    
    Random rdm3 = new Random();
    type = rdm3.nextInt(4);
  }

  public int getType() {
    return type;
  }
  public int getAmounts(int c) {
    return amounts[c];
  }
  public double getValues(int c) {
    return values[c];
  }
  public int getArrayLength() { //Wiedergabe der Array-Laenge
    return amounts.length;    //Welches Array egal, da sie gleichlang sein muessen
  }
 }
