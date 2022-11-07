package com.example.planetguessrfx;/*
Ersteller: Frederick
*/
import java.util.Random;
public class Resources extends Generator {
  private static final int VERY_FREQUENTLY = 1001;
  private static final int FREQUENTLY = 751;
  private static final int COMMON = 651;
  private static final int MODERATE = 501;
  private static final int RARE = 351;
  private static final int VERY_RARE = 301;
  private static final int EXTREMELY_RARE = 101;
  private int[] resources = new int[15];
  private int[] values = new int[15];
  private int type;
  public Resources() {
    this.generateResources();
    this.generateValues();
  }
  
  public void generateResources()
  {
    Random rdm = new Random(); // creating Random object
    resources[0] = rdm.nextInt(FREQUENTLY);
    resources[1] = rdm.nextInt(MODERATE);
    resources[2] = rdm.nextInt(RARE);
    resources[3] = rdm.nextInt(VERY_RARE);
    resources[4] = rdm.nextInt(VERY_RARE);
    resources[5] = rdm.nextInt(COMMON);
    resources[6] = rdm.nextInt(COMMON);
    resources[7] = rdm.nextInt(EXTREMELY_RARE);
    resources[8] = rdm.nextInt(MODERATE);
    resources[9] = rdm.nextInt(MODERATE);
    resources[10] = rdm.nextInt(FREQUENTLY);
    resources[11] = rdm.nextInt(VERY_RARE);
    resources[12] = rdm.nextInt(VERY_FREQUENTLY);
    resources[13] = rdm.nextInt(MODERATE);
    resources[14] = rdm.nextInt(MODERATE);
    }
  public void generateValues()
  {
    Random rdm = new Random(); // creating Random object
    values[0] = rdm.nextInt(375)+75;
    values[1] = rdm.nextInt(43500)+35500;
    values[2] = rdm.nextInt(248500)+224000;
    values[3] = rdm.nextInt(1770000)+1692000;
    values[4] = rdm.nextInt(1038000)+896100;
    values[5] = rdm.nextInt(208)+124;
    values[6] = rdm.nextInt(2080)+1625;
    values[7] = rdm.nextInt(3500000)+3000000;
    values[8] = rdm.nextInt(26500)+20000;
    values[9]= rdm.nextInt(56000)+46500;
    values[10] = rdm.nextInt(11250)+6750;
    values[11] = rdm.nextInt(5700000)+3900000;
    values[12] = 3000;
    values[13] = rdm.nextInt(20500)+16000;
    values[14] = rdm.nextInt(32000)+25000;
  }
  public void generateType() {
    Random rdm = new Random();
    type = rdm.nextInt(4);
  }
  public int getType() {
    return type;
  }
  public int getResources(int c) {
    return resources[c];
  }
  public int getValues(int c) {
    return values[c];
  }
  public int getArrayLength() { //Wiedergabe der Array-Laenge
    return resources.length;    //Welches Array egal, da sie gleichlang sein muessen
  }
 }