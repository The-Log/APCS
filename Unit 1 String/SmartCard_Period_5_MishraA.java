//name: Ankur Mishra    date: 9/11

import java.text.DecimalFormat;

public class SmartCard_Period_5_MishraA

{
   public static void main(String[] args)
   {
      DecimalFormat format = new DecimalFormat("$0.00");
      Station downtown = new Station("Downtown", 1);
      Station center = new Station("Center City", 1);
      Station uptown = new Station("Uptown", 2);
      Station suburbia = new Station("Suburb", 4);
      Station red = new Station("Long Trip", 40);


      SmartCard jimmy = new SmartCard(20.00); //bought with $20.00
      jimmy.board(center);                        //boarded in zone 1
      jimmy.disembark(suburbia);					 //disembark in zone 4
      jimmy.disembark(uptown);					 //disembark without having boarded
      jimmy.board(center);                        //boarded in zone 1
      jimmy.disembark(red);					 //disembark without having boarded
      jimmy.board(red);
      jimmy.disembark(center);					 //disembark without having boarded

      //lots more test cases!
   }
}
class SmartCard {
   private double myMoney;
   private boolean ifBoarded;
   public Station myStation;
   private DecimalFormat format = new DecimalFormat("$0.00");
   public SmartCard(double m){
      myMoney = m;
   }
   public double moneyOnCard(){
      return myMoney;
   }
   public void addMoney(double m){
      myMoney = myMoney + m;
      System.out.println("Added " + format.format(m));
   }
   public void setMoney(double m){
      myMoney = m;
   }
   public void setBoarded(boolean b){
      ifBoarded = b;
   }
   public double cost(Station a, Station b){
      double moneyNeeded = 0.50; int difference;
      if(a.getZone() > b.getZone())
         difference = a.getZone() - b.getZone();
      else{
         difference = b.getZone() - a.getZone();
      }
      moneyNeeded = moneyNeeded + (0.75 * difference);
      //System.out.println(myMoney+ " " + moneyNeeded);
      if(myMoney > moneyNeeded)
         setMoney(myMoney - moneyNeeded);
      return moneyNeeded;
   }
   public void board(Station s){
      if(myMoney > 0.5) {
         myStation = s;
         ifBoarded = true;
         System.out.println("Boarded at: " + myStation.getName());
      }
      else{
         System.out.println( "Go see the station manager! You don't have enough money.");
      }
   }
   public void disembark(Station station){
      if (ifBoarded == true) {
         double cost = cost(myStation, station);
         if (myMoney < cost) {
            System.out.println("The trip costs " + format.format(cost) + ". You have " + format.format(myMoney) + ", which is " +
                    "not enough.");
            System.out.println( "Go see the station manager! \n");

         }
         else {
            System.out.println("From " + myStation.getName() + " to " + station.getName() + " costs " +
                    format.format(cost) + "\nBalance: " + format.format(myMoney)+ "\n");
            ifBoarded = false;
         }
      }
      else if(ifBoarded == false){
         System.out.println( "You haven't boarded! \n");
      }
      else{
         System.out.println( "Go see the station manager! \n");
      }
   }
}

class Station
{
   private String myName;
   private int myZone;
   public Station(String s, int i){
      myName = s;
      myZone = i;
   }
   public String getName(){
      return myName;
   }
   public int getZone(){
      return myZone;
   }
   public void setName(String s){
      myName = s;
   }
   public void setZone(int z){
      myZone = z;
   }
   public String toString(){
      return "The station's name is " + myName + " and number is " + myZone;

   }
}

 
