package com.epam.horserace.services;

import java.util.concurrent.TimeUnit;

public class EmulationService {

  private RaceService raceService;
  private final int screenWith = 60;

  public EmulationService(RaceService raceService) {
    this.raceService = raceService;
  }

  public void showInformationAboutRace(){
    writeInformationHeader();

  }

  public void emulateRace(){

    for(int k = 0; k < 10; k++) {

      for (int i = 0; i < 5; i++)
        System.out.println();

      for (int i = 0; i < 10; i++) {
        for (int j = 0; j < i; j++)
          System.out.print("*");

        System.out.println();
      }

      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private void writeLine(int length){
    for (int i = 0; i < length; i++) {
      System.out.print("_");
    }
  }

  private void leftStick(int empties){
    System.out.print("|");

    for(int i = 0; i < empties; i++){
      System.out.print(" ");
    }
  }

  private void rightStick(int empties){

    for(int i = 0; i < empties; i++){
      System.out.print(" ");
    }

    System.out.println("|");
  }

  private void writeInformationHeader(){
    writeLine(screenWith);
    System.out.println();

    leftStick(25);
    System.out.print("Information");
    rightStick(22);

    leftStick(0);
    writeLine(screenWith - 2);
    rightStick(0);
  }

}
