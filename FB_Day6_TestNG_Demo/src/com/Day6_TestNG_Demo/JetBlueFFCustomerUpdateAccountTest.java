package com.Day6_TestNG_Demo;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class JetBlueFFCustomerUpdateAccountTest {

  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("Executing beforeMethod function of CustomerUpdateAccount Test....");
  }
  
  @Test
  public void CustomerUpdateAccount() {
	  System.out.println("Executing the code of CustomerUpdateAccount Test....");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("Executing afterMethod function of CustomerUpdateAccount Test....");
  }

}
