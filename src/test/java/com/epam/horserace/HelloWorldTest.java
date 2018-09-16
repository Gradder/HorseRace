package com.epam.horserace;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/Beans.xml"})
public class HelloWorldTest {

  @Test
  public void clientPaysMoneyForRaceToWinOrNotButBetterToWin() {

  }
}
