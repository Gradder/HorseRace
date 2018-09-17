package com.epam.horserace;

import com.epam.horserace.services.EmulationService;
import com.epam.horserace.services.HorseService;
import com.epam.horserace.services.RaceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"classpath:horses.properties"})
public class AppConfig {

  @Value("${horses.property.name}")
  private String[] names;

  @Value("${horses.property.rider}")
  private String[] riders;

  @Value("${horses.property.breed}")
  private String[] breeds;

  @Bean(name = "horseService")
  public HorseService getHorseService() {
    return new HorseService(names, breeds, riders);
  }

  @Bean(name = "client")
  public Client getClient() {
    return new Client(getEmulationService());
  }

  @Bean(name = "emulationService")
  public EmulationService getEmulationService() {
    return new EmulationService(getRaceService());
  }

  @Bean(name = "raceService")
  public RaceService getRaceService() {
    return new RaceService(getHorseService());
  }
}
