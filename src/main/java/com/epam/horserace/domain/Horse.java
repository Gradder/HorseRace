package com.epam.horserace.domain;

public class Horse {

  private String name;
  private Breed breed;
  private Rider rider;
  private double position;
  private double speed;
  public Horse(String name, Breed breed, Rider rider) {
    this.name = name;
    this.breed = breed;
    this.rider = rider;
    this.position = 0;
    this.speed = 0.5 + Math.random()*2;
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

  public int getPosition() {
    return (int)Math.ceil(position);
  }

  public void changePosition() {
    this.position+= speed;
  }
}
