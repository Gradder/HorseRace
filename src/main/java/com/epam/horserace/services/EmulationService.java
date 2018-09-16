package com.epam.horserace.services;

import com.epam.horserace.domain.Horse;
import com.epam.horserace.domain.Race;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class EmulationService {

  private RaceService raceService;
  private Race race;
  private List<Horse> horsesInRace;
  private Writer writer;
  private final int screenWith = 60;
  private final int nameColWidth = 19;
  private final int breedColWidth = 15;
  private final int riderColWidth = 21;

  public EmulationService(RaceService raceService) {
    this.raceService = raceService;
    race = raceService.getRace();
    horsesInRace = race.getChosenHorses();
    writer = new Writer();
  }

  public void showInformationAboutRace(){
    writer.writeInformationHeader();
    writer.writeTableHeader();
    for (Horse horse: horsesInRace) {
      writer.writeTableEntry(horse.getName(), horse.getBreed().getName(), horse.getRider().getName());
    }
    writer.writeTableLine();
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

  private class Writer{

    private void writeLine(int length){
      for (int i = 0; i < length; i++) {
        System.out.print("_");
      }
    }

    private void writeTableLine(){
      leftStick(0);
      writeLine(screenWith - 2);
      rightStick(0);
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

    private void emptyLines(int empties){
      for(int i = 0; i < empties; i++) {
        System.out.print(" ");
      }
    }

    private void writeInformationHeader(){
      writeLine(screenWith);
      System.out.println();

      leftStick(24);
      System.out.print("Information");
      rightStick(23);

      writeTableLine();
    }

    private void writeTableHeader(){
      leftStick(5);
      System.out.print("Horse name");
      emptyLines(4);

      leftStick(5);
      System.out.print("Breed");
      emptyLines(5);

      leftStick(5);
      System.out.print("Rider's name");
      rightStick(5);

      writeTableLine();
    }

    private void writeTableEntry(String horsename, String breed, String rider){
      leftStick(0);
      writeColumn(horsename, nameColWidth);
      writeColumn(breed, breedColWidth);
      writeColumn(rider, riderColWidth);
      System.out.println();
    }

    private void writeColumn(String entry, int colWidth) {
      int colSpace;

      colSpace = (colWidth - entry.length())/2;

      emptyLines(colSpace);
      System.out.print(entry);
      if(!isSymmetry(entry)){
        colSpace++;
      }
      emptyLines(colSpace);
      if (colWidth == riderColWidth) {
        emptyLines(1);
      }
      leftStick(0);
    }

    private boolean isSymmetry(String word){
      return (nameColWidth - word.length()) % 2 == 0;
    }
  }

}

