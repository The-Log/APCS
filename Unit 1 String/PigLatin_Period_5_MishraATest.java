import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class PigLatin_Period_5_MishraATest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** A test that always fails. **/
   @Test public void defaultTest() {
      Assert.assertEquals("Default test added by jGRASP. Delete "
            + "this test once you have added your own.", 0, 1);
   }
   
   /** Supposed to Not Fail **/

  @Test public void word1() 
  {
      Assert.assertEquals("Igpay piglatinized is: ","Igpay",PigLatin_Period_5_MishraA.pig("Pig"));
  }
  @Test public void word2() 
  {
      Assert.assertEquals("igpay piglatinized is: ","igpay",PigLatin_Period_5_MishraA.pig("pig"));
  }   
  @Test public void word3() 
  {
      Assert.assertEquals("\"Pig!\" piglatinized is: ","\"Igpay!\"",PigLatin_Period_5_MishraA.pig("\"Pig!\""));
  }   
  @Test public void word4() 
  {
      Assert.assertEquals("Rhyme piglatinized is: ","Ymerhay",PigLatin_Period_5_MishraA.pig("Rhyme"));
  }
  @Test public void word5() 
  {
      Assert.assertEquals("yes piglatinized is: ","esyay",PigLatin_Period_5_MishraA.pig("yes"));
  }
  @Test public void word6() 
  {
      Assert.assertEquals("Thomas piglatinized is: ","Omasthay",PigLatin_Period_5_MishraA.pig("Thomas"));
  } 
  @Test public void word7() 
  {
      Assert.assertEquals("Thomas piglatinized is: ","Omasthay",PigLatin_Period_5_MishraA.pig("Thomas"));
  }
  @Test public void word8() 
  {
      Assert.assertEquals("Thomas! piglatinized is: ","Omasthay!",PigLatin_Period_5_MishraA.pig("Thomas!"));
  }
  @Test public void word9() 
  {
      Assert.assertEquals("Adam piglatinized is: ","Adamway",PigLatin_Period_5_MishraA.pig("Adam"));
  }
  @Test public void word10() 
  {
      Assert.assertEquals("Adam! piglatinized is: ","Adamway!",PigLatin_Period_5_MishraA.pig("Adam!"));
  }
  @Test public void word11() 
  {
      Assert.assertEquals("\"Adam\" piglatinized is: ","\"Adamway\"",PigLatin_Period_5_MishraA.pig("\"Adam\""));
  }
  @Test public void word12() 
  {
      Assert.assertEquals("\"adam\" piglatinized is: ","\"adamway\"",PigLatin_Period_5_MishraA.pig("\"adam\""));
  }
  @Test public void word13() 
  {
      Assert.assertEquals("\"Quilt\" piglatinized is: ","\"Iltquay\"",PigLatin_Period_5_MishraA.pig("\"Quilt\""));
  }
  
  @Test public void word14() 
  {
      Assert.assertEquals("\"Squeeze\" piglatinized is: ","\"Eezesquay\"",PigLatin_Period_5_MishraA.pig("\"Squeeze\""));
  }
  @Test public void word15() 
  {
      Assert.assertEquals("bfdg piglatinized is: ","INVALID",PigLatin_Period_5_MishraA.pig("bfdg"));
  }   
}
