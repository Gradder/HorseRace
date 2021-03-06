package com.epam.horserace.services;

import com.epam.horserace.domain.Horse;
import com.epam.horserace.domain.Race;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class EmulationService {

  private RaceService raceService;
  private Race race;
  private List<Horse> horsesInRace;
  private Horse leadingHorse;
  private Writer writer;
  private final int screenWith = 60;
  private final int nameColWidth = 19;
  private final int breedColWidth = 15;
  private final int riderColWidth = 21;
  private int maxHorseNameLength = 0;
  private Integer money = 0;
  private Horse chosenHorse;

  public EmulationService(RaceService raceService) {
    this.raceService = raceService;
    race = raceService.getRace();
    horsesInRace = race.getChosenHorses();
    writer = new Writer();
    leadingHorse = horsesInRace.get(0);
  }

  public void showInformationAboutRace() {
    writer.writeInformationHeader();
    writer.writeTableHeader();
    for (Horse horse : horsesInRace) {
      writer
          .writeTableEntry(horse.getName(), horse.getBreed().getName(), horse.getRider().getName());
      if (maxHorseNameLength < horse.getName().length()) {
        maxHorseNameLength = horse.getName().length();
      }
    }
    writer.writeTableLine();
  }

  public void makeABet() {
    Horse horse = null;

    writer.writeBetHeader();
    while (horse == null){
      String search = offerMakeABet();
      horse = findHorse(search);
      if(horse == null) {
        writer.writeTryAgain();
      }
      writer.writeEmptyRow();
    }
    writer.leftStick(0);
    writer.writeLine(screenWith - 2);
    writer.rightStick(0);

    chosenHorse = horse;
    chosenHorse(horse);
  }

  private void chosenHorse(Horse horse){
    writer.leftStick(1);
    writer.writeColumn("This horse is yours", screenWith-2);
    System.out.println();
    writer.writeTableLine();

    writer.leftStick(0);
    writer.writeColumn(horse.getName(), nameColWidth);
    writer.writeColumn(horse.getBreed().getName(), breedColWidth);
    writer.writeColumn(horse.getRider().getName(), riderColWidth);
    System.out.println();
    writer.leftStick(0);
    writer.writeLine(screenWith - 2);
    writer.rightStick(0);

    betWelcome();
  }

  private void betWelcome(){
    writer.leftStick(1);
    writer.writeColumn("Make a bet", screenWith-3);
    System.out.println();
    writer.writeTableLine();

    writer.leftStick(1);
    System.out.print("Your bet: ");
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String bet = "";

    try {
      bet = reader.readLine();
      money = Integer.valueOf(bet);
    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println();
    System.out.println("Race starts in:");

    for (int i = 3; i > 0; i--) {
      System.out.println(i);
      try {
        TimeUnit.MILLISECONDS.sleep(800);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

  }

  public void emulateRace() {
    writer.writeLine(screenWith + 5);
    while (true) {

      try {
        TimeUnit.MILLISECONDS.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      for (Horse horse : horsesInRace) {

        if (leadingHorse.getPosition() < horse.getPosition()) {
          leadingHorse = horse;
        }

        writer.raceTrackEntry(horse.getName(), horse.getPosition());
        horse.changePosition();
      }

      System.out.println();
      writer.writeLine(screenWith + 5);

      if (leadingHorse.getPosition() >= race.getTrackLength()) {
        announceWinner(leadingHorse);
        return;
      }
    }

  }

  private void announceWinner(Horse horse) {
    writer.writeAnnouncementHeader();
    writer.leftStick(0);
    writer.writeColumn(horse.getName(), nameColWidth);
    writer.writeColumn(horse.getBreed().getName(), breedColWidth);
    writer.writeColumn(horse.getRider().getName(), riderColWidth);
    System.out.println();
    writer.leftStick(0);
    writer.writeLine(screenWith - 2);
    writer.rightStick(0);

    writer.leftStick(1);
    if(chosenHorse == horse){
      writer.writeColumn("You win! Your gain: " + money*3, screenWith-3);
    }else{
      writer.writeColumn("You lose! Your gain: -" + money, screenWith-3);
    }

    System.out.println();
    writer.leftStick(0);
    writer.writeLine(screenWith - 2);
    writer.rightStick(0);

  }

  private String offerMakeABet() {
    writer.leftStick(1);
    System.out.print("Enter horse's name, rider's name or breed to make a bet: ");
    writer.rightStick(0);
    writer.leftStick(1);
    System.out.print("Search: ");
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String search = "";
    try {
      search = reader.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return search;
  }

  private Horse findHorse(String search) {

    for (Horse horse : horsesInRace) {
      if (horse.getName().equalsIgnoreCase(search) || horse.getRider().getName().equalsIgnoreCase(search)
          || horse.getBreed().getName().equalsIgnoreCase(search)) {
          return horse;
      }
    }
    return null;
  }

  private class Writer {

    private void writeLine(int length) {
      for (int i = 0; i < length; i++) {
        System.out.print("_");
      }
    }

    private void writeTableLine() {
      leftStick(0);
      writeLine(screenWith - 2);
      rightStick(0);
    }

    private void leftStick(int empties) {
      System.out.print("|");

      for (int i = 0; i < empties; i++) {
        System.out.print(" ");
      }
    }

    private void rightStick(int empties) {

      for (int i = 0; i < empties; i++) {
        System.out.print(" ");
      }

      System.out.println("|");
    }

    private void emptyLines(int empties) {
      for (int i = 0; i < empties; i++) {
        System.out.print(" ");
      }
    }

    private void writeInformationHeader() {
      writeLine(screenWith);
      System.out.println();

      leftStick(24);
      System.out.print("Information");
      rightStick(23);

      writeTableLine();
    }

    private void writeTryAgain(){
      leftStick(1);
      System.out.print("Horse wasn't found. Try again!");
      rightStick(27);
    }

    private void writeEmptyRow(){
      writer.leftStick(1);
      writer.rightStick(57);
    }

    private void writeAnnouncementHeader() {
      System.out.println();
      writeLine(screenWith);
      System.out.println();

      leftStick(15);
      System.out.print("Congratulations for winner!");
      rightStick(16);

      writeTableLine();
    }

    private void writeBetHeader() {
      System.out.println();
      writeLine(screenWith);
      System.out.println();

      leftStick(24);
      System.out.print("Make a bet");
      rightStick(24);

      writeTableLine();
    }

    private void writeChosenHorseHeader() {
      System.out.println();
      writeLine(screenWith);
      System.out.println();



      leftStick(24);
      System.out.print("This horse is yours");
      rightStick(24);

      writeTableLine();
    }

    private void writeTableHeader() {
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

    private void writeTableEntry(String horsename, String breed, String rider) {
      leftStick(0);
      writeColumn(horsename, nameColWidth);
      writeColumn(breed, breedColWidth);
      writeColumn(rider, riderColWidth);
      System.out.println();
    }

    private void writeColumn(String entry, int colWidth) {
      int colSpace;

      colSpace = (colWidth - entry.length()) / 2;

      emptyLines(colSpace);
      System.out.print(entry);
      if (!isSymmetry(entry)) {
        colSpace++;
      }
      emptyLines(colSpace);
      if (colWidth == riderColWidth) {
        emptyLines(1);
      }
      leftStick(0);
    }

    private boolean isSymmetry(String word) {
      return (nameColWidth - word.length()) % 2 == 0;
    }

    public void raceTrackEntry(String name, int position) {

      System.out.println();
      writeColumn(name, nameColWidth);

      for (int i = 0; i < race.getTrackLength(); i++) {
        if (i == position) {
          System.out.print("*");
        } else {
          System.out.print("_");
        }
      }

      leftStick(0);

    }
  }

}

