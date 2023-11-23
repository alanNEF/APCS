import java.util.Scanner;
class Main {
  public static void main(String[] args) {
    int wins = 0, loose = 0, cash=200;
    while(cash>0 && cash<400){
      double earned = 0;
      Scanner kbReader = new Scanner(System.in);
      Deck mainDeck = new Deck(), playerDeck = new Deck(true), dealerDeck = new Deck(false), playerDeck2 = new Deck(true);
      boolean split = false, dd = false, stay = false, stay2 = false, win = false, tie = false, once = false;
      mainDeck.shuffleDeck();
      for(int i = 0; i < 2; i++){
        playerDeck.addCard(mainDeck.takeCard());
        dealerDeck.addCard(mainDeck.takeCard());
      }
      System.out.print("Ammount: "+ cash + "\nWins: "+ wins + "   Losses/pushes: "+ loose +"\nHere are your cards: " + playerDeck + "\nDealer's first card: " + dealerDeck.getCard(0) + "\nType one of the following actions you would like to make HIT, STAY, SPLIT, and DOUBLE DOWN:");
      String action = kbReader.nextLine().toLowerCase();
      while(!playerDeck.checkPossible(action)){
        if(!playerDeck.checkPossible(action)){
          System.out.println("Looks like that action is invalid for your current hand.\nType one of the following actions you would like to make HIT, STAY, SPLIT, and DOUBLE DOWN:");
          action = kbReader.nextLine().toLowerCase();
        } 
      }
      if(action.equals("hit")){playerDeck.hit(mainDeck);} 
      else if(action.equals("split")){
        playerDeck2 = playerDeck.split();
        split = true;
        if(playerDeck.getCard(0).getKind().equals("A")){
          once = true;
        }
      } else if(action.equals("double down")){
        dd = true;
        playerDeck.hit(mainDeck);
      }
      else if(action.equals("stay")){stay=true;}
      while(stay == false && playerDeck.sumOfDeck() <= 21 && !split && !dd){
        System.out.print("\nHere are your cards: " + playerDeck + "\nDealer's first card: " + dealerDeck.getCard(0) + "\nWould you like to hit or stay?");
        action = kbReader.nextLine().toLowerCase();
        if(action.equals("hit")){playerDeck.hit(mainDeck);}
        else if(action.equals("stay")){stay = true;}
        else{
          System.out.println("Looks like that action is invalid for your current hand.\nType one of the following actions you would like to make HIT, STAY, SPLIT, and DOUBLE DOWN:");
          action = kbReader.nextLine().toLowerCase();
        }
      }
      if(!once){
        while(split && !(stay && stay2) && !(playerDeck.sumOfDeck() > 21 && playerDeck2.sumOfDeck() > 21)){
          System.out.println("First Hand: " + playerDeck + "\nSecond Hand: " + playerDeck2 + "\nDealer's first card: " + dealerDeck.getCard(0));
          if(!stay && playerDeck.sumOfDeck()<=21){
            System.out.println("Would you like to hit or stay your first hand: ");
            action = kbReader.nextLine().toLowerCase();
            while(!(action.equals("hit") || action.equals("stay"))){
              System.out.println("Looks like that action is invalid for your current hand.\nType one of the following actions you would you like to hit or stay your first hand: ");
              action = kbReader.nextLine().toLowerCase();
            }
            if(action.equals("hit")){playerDeck.hit(mainDeck);}
            else if(action.equals("stay")){stay = true;}
          } if(!stay2 && playerDeck2.sumOfDeck()<=21){
            System.out.println("Would you like to hit or stay your second hand: ");
            action = kbReader.nextLine().toLowerCase();
            while(!(action.equals("hit") || action.equals("stay"))){
              System.out.println("Looks like that action is invalid for your current hand.\nType one of the following actions you would you like to hit or stay your first hand: ");
              action = kbReader.nextLine().toLowerCase();
            }
            if(action.equals("hit")){playerDeck2.hit(mainDeck);}
            else if(action.equals("stay")){stay2 = true;}
          }
          if(playerDeck.sumOfDeck() > 21 ){stay = true;}
          if(playerDeck2.sumOfDeck() > 21 ){stay2 = true;}
        }
      } else if(once){
        System.out.println("First Hand: " + playerDeck + "\nSecond Hand: " + playerDeck2 + "\nDealer's first card: " + dealerDeck.getCard(0));
        System.out.println("Would you like to hit or stay your first hand: ");
        action = kbReader.nextLine().toLowerCase();
        while(!(action.equals("hit") || action.equals("stay"))){
          System.out.println("Looks like that action is invalid for your current hand.\nType one of the following actions you would you like to hit or stay your first hand: ");
          action = kbReader.nextLine().toLowerCase();
        }
        if(action.equals("hit")){playerDeck.hit(mainDeck);}
        System.out.println("Would you like to hit or stay your second hand: ");
        action = kbReader.nextLine().toLowerCase();
        while(!(action.equals("hit") || action.equals("stay"))){
          System.out.println("Looks like that action is invalid for your current hand.\nType one of the following actions you would you like to hit or stay your first hand: ");
          action = kbReader.nextLine().toLowerCase();
        }
        if(action.equals("hit")){playerDeck2.hit(mainDeck);}
      }
      dealerDeck.runDealer(mainDeck);
      if(playerDeck.sumOfDeck() > 21 && !split){
        loose++;
        win = false;
      } else if(playerDeck.sumOfDeck() > 21 && playerDeck2.sumOfDeck() > 21 && !(dealerDeck.sumOfDeck() > 21)){
        loose++;
        win = false;
      } else if(dealerDeck.sumOfDeck() > 21 && !(playerDeck.sumOfDeck() > 21 && playerDeck2.sumOfDeck() > 21)){
        win = true;
        wins++;
      } else if(dealerDeck.sumOfDeck() < playerDeck.sumOfDeck() || dealerDeck.sumOfDeck() < playerDeck2.sumOfDeck()){
        win = true;
        wins++;
      } else if(dealerDeck.sumOfDeck() > playerDeck.sumOfDeck() && dealerDeck.sumOfDeck() > playerDeck2.sumOfDeck()){
        loose++;
        win = false;
      }
      else{tie = true;} 
      if(tie){
        loose++;
        System.out.println("Your Hand(s)" + playerDeck);
        if(split){System.out.println(playerDeck2);}
        System.out.println("Dealer's Hand: " + dealerDeck + "\nIts a push! Click enter to continue");
        action = kbReader.nextLine().toLowerCase();
      } else if(win){
        System.out.println("Your Hand(s)" + playerDeck);
        if(split){System.out.println(playerDeck2);}
        earned+=10;
        if(playerDeck.isBlackJack() && playerDeck.sumOfDeck() == 21){earned*=1.5;}
        else if(dd){earned*=2;} 
        cash+=(int)Math.round(earned);
        System.out.println("Dealer's Hand: " + dealerDeck + "\nYou won! You earned " + (int)Math.round(earned) + "! Click enter to continue");
        action = kbReader.nextLine().toLowerCase();
      } else{
        System.out.println("Your Hand(s)" + playerDeck);
        if(split){System.out.println(playerDeck2);}
        System.out.println("Dealer's Hand: " + dealerDeck + "\nYou lost! You lost $10! Click enter to continue");
        action = kbReader.nextLine().toLowerCase();
        cash-=10;
      }
    }
    System.out.println("You had " + wins +" wins and " + loose + " looses \n making your win percentage " + ((wins/(wins+loose))*100) + "% ");
  }
}
