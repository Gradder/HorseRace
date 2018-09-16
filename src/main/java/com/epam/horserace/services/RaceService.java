package com.epam.horserace.services;

import com.epam.horserace.domain.Race;

public class RaceService {

  private HorseService horseService;

  public RaceService(HorseService horseService) {
    this.horseService = horseService;
  }

  public Race getRace(){
    return new Race(horseService.getHorses());
  }

}
