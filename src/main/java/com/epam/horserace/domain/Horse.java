package com.epam.horserace.domain;

public class Horse {

  private String name;
  private Breed breed;
  private Rider rider;
  private double speed;

  public Horse(String name, Breed breed, Rider rider) {
    this.name = name;
    this.breed = breed;
    this.rider = rider;
    this.speed = Math.random();
  }

  public String getName() {
    return name;
  }

  public Breed getBreed() {
    return breed;
  }

  public Rider getRider() {
    return rider;
  }

  public double getSpeed() {
    return speed;
  }
}
