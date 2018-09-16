package com.epam.horserace.domain;

public class Horse {

  private String name;
  private Breed breed;
  private Rider rider;

  public Horse(String name, Breed breed, Rider rider) {
    this.name = name;
    this.breed = breed;
    this.rider = rider;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Breed getBreed() {
    return breed;
  }

  public void setBreed(Breed breed) {
    this.breed = breed;
  }

  public Rider getRider() {
    return rider;
  }

  public void setRider(Rider rider) {
    this.rider = rider;
  }
}
