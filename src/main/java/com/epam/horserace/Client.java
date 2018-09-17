package com.epam.horserace;

import com.epam.horserace.services.EmulationService;

public class Client {

  private EmulationService emulationService;

  public Client(EmulationService emulationService) {
    this.emulationService = emulationService;
  }

  void startHorseRace() {
  emulationService.showInformationAboutRace();
  emulationService.makeABet();
  emulationService.emulateRace();
  }

}
