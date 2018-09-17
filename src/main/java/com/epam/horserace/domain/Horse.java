package com.epam.horserace.domain;

public class Horse {

  private String name;
  private Breed breed;
  private Rider rider;
  private double speed;
  private double position;

  public Horse(String name, Breed breed, Rider rider) {
    this.name = name;
    this.breed = breed;
    this.rider = rider;
    this.speed = 0.5 + Math.random()*2;
    this.position = 0;
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

  public int getPosition() {
    return (int)Math.ceil(position);
  }

  public void changePosition() {
    this.position+=speed;
  }
}
