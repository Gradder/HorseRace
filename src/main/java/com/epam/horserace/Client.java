package com.epam.horserace;

import com.epam.horserace.domain.Horse;
import com.epam.horserace.services.HorseService;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

}
