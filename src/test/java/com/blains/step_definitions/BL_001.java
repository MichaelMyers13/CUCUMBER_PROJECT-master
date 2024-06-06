package com.blains.step_definitions;

import com.blains.utilities.ConfigurationReader;
import com.blains.utilities.Driver;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BL_001 {


  @Given("{string} open the BL main page")
  public void user_open_the_bl_main_page(String user) {

    WebDriverManager.chromedriver().clearDriverCache().setup();
   Driver.getDriver().get(ConfigurationReader.getProperty("BL_MAIN_PAGE"));


  }
  @Then("User put invalid credential")
  public void user_put_invalid_credential() {

  }
  @Then("User has to see invalid login msg")
  public void user_has_to_see_invalid_login_msg() {

  }



}
