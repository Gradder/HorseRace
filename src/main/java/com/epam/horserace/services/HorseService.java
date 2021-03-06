package com.epam.horserace.services;

import com.epam.horserace.domain.Breed;
import com.epam.horserace.domain.Horse;
import com.epam.horserace.domain.Rider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class HorseService {

  private List<Horse> horses;

  public HorseService(String[] names, String[] breeds, String[] riders) {
    Random random = new Random();

    horses = new ArrayList<Horse>();

    ArrayList<String> breedsList = new ArrayList<String>(Arrays.asList(breeds));
    Collections.shuffle(breedsList);

    ArrayList<String> ridersList = new ArrayList<String>(Arrays.asList(riders));
    Collections.shuffle(ridersList);

    ArrayList<String> namesList = new ArrayList<String>(Arrays.asList(names));
    Collections.shuffle(namesList);

    for (String name : namesList) {
      String breedName = breedsList.get(0);
      breedsList.remove(0);

      String riderName = ridersList.get(0);
      ridersList.remove(0);

      horses.add(new Horse(name, new Breed(breedName), new Rider(riderName)));
    }
  }

  public List<Horse> getHorses() {
    return horses;
  }

}
