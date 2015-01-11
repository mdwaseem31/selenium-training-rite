package com.Day6_TestNG_Demo;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class JetBlueFFNewReservationTest {

  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("Executing beforeMethod function of NewReservation Test");
  }
  
  @Test
  public void NewReservation() {
	  if(TestReadyForExecution()) {
		  System.out.println("Executing the code of NewReservation Test....");  
	  } else
		  System.out.println("NO PERMISSION TO RUN THIS TEST");
  }

  public boolean TestReadyForExecution(){
	  int intPermission = 0;
	  
	  //We can have some business logic here
	  if(intPermission == 1){
		  return true;
	  } else {
		  return false;
	  }
  }
  
  @AfterMethod
  public void afterMethod() {
	  System.out.println("Executing afterMethod function of NewReservation Test");
  }

}
