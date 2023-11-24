import java.util.Scanner;
class Main {
  public static void main(String[] args) {
    Scanner kbReader = new Scanner(System.in);
    int cards=0;
    boolean win=false;
    Deck mainDeck = new Deck();
    mainDeck.shuffleDeck();
    Deck playerDeck = new Deck();
    playerDeck.clearDeck();
    for(int i = 0; i < 11; i++) {playerDeck.addCard(mainDeck.takeCard());}
    while(win==false&&playerDeck.checkPossible()==true){
      System.out.print("\033[H\033[2J");
      System.out.println("Number of cards collected: " + cards + " / 52");
      System.out.println(playerDeck);
      System.out.println("What cards would you like to select(Enter the number next to the card you want to select):");
      System.out.println("1st card:");
      int card1 = kbReader.nextInt();
      if(playerDeck.getCard(card1-1).getKind().equals("J")){
        System.out.println("2nd card:");
        int card2 = kbReader.nextInt();
        if(playerDeck.getCard(card2-1).getKind().equals("Q") || playerDeck.getCard(card2-1).getKind().equals("K")){
          System.out.println("3rd card:");
          int card3 = kbReader.nextInt();
          if(playerDeck.getCard(card2-1).getKind().equals("Q") ? playerDeck.getCard(card3-1).getKind().equals("K") : playerDeck.getCard(card3-1).getKind().equals("Q")){
            int x[] = {card1, card2, card3};
            playerDeck.replaceCard(x, mainDeck);
            cards+=3;
          }
        }
      } else if(playerDeck.getCard(card1-1).getValue()>0){
        System.out.println("2nd card:");
        int card2 = kbReader.nextInt();
        if(Deck.sum11(playerDeck.getCard(card1-1),playerDeck.getCard(card2-1))){
          int x[] = {card1, card2};
          playerDeck.replaceCard(x, mainDeck);
          cards+=2;
            if(cards>=52) {win=true;}
        } else {System.out.println("Sorry! Looks like the card you selected does not exist!");}
      } else {System.out.println("Sorry! Looks like the card you selected does not exist!");}
    }
    if(playerDeck.checkPossible()==false){System.out.println("Looks like there are no possible matches to make click run to play again!");}
    else{System.out.println("Congratulations, you won!");}
  }
}
