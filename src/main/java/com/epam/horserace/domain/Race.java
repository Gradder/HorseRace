package com.epam.horserace.domain;

import java.util.ArrayList;
import java.util.List;

public class Race {

  private List<Horse> chosenHorses;
  private int trackLength;

  public Race(List<Horse> horses){

    chosenHorses = new ArrayList<Horse>();

    for(int i =0; i < 6; i++){
      chosenHorses.add(horses.get(i));
    }

    trackLength = 10 + (int)(Math.random()*10);
  }

  public List<Horse> getChosenHorses() {
    return chosenHorses;
  }

  public int getTrackLength() {
    return trackLength;
  }
}
