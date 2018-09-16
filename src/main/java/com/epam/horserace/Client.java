package com.epam.horserace;

import com.epam.horserace.domain.Horse;
import com.epam.horserace.services.HorseService;
import java.util.List;

public class Client {

  private HorseService horseService;

  public Client(HorseService horseService) {
    this.horseService = horseService;
  }

  void startHorseRace() {

    List<Horse> horses = horseService.getHorses();

    for (Horse horse : horses) {
      System.out.println(
          horse.getName() + " " + horse.getBreed().getName() + " " + horse.getRider().getName());
    }
  }

}
