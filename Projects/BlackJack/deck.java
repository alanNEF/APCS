import java.util.*;

class Deck {
  private ArrayList<Cards> deck = new ArrayList<Cards>();
  private boolean isPlayerDeck;

  Deck(){
    for(int k = 0; k < 4; k++){
      for(int i = 2; i <= 10; i++){
        if(i == 10){
          deck.add(new Cards(k,"10"));
          deck.add(new Cards(k,"J"));
          deck.add(new Cards(k,"Q"));
          deck.add(new Cards(k,"K"));
          deck.add(new Cards(k,"A"));
        } else{
          deck.add(new Cards(k,String.valueOf(i)));
        }
      }
    }
    isPlayerDeck=false;
  }
  
  Deck(boolean player){
    isPlayerDeck=player;
  }
  
  void shuffleDeck() {
    Collections.shuffle(deck);
  }

  void clearDeck() {
    deck.clear();
  }

  Cards takeCard() {
    Cards card = deck.get(0);
    deck.remove(0);
    return card;
  }

  void removeCard(int k) {
    deck.remove(k);
  }

  Cards getCard(int i) {
    return deck.get(i);
  }

  int getNumber() {
    return deck.size();
  }

  void addCard(Cards x) {
    deck.add(x);
  }

  int sumOfDeck(){
    int sum = 0;
    int numOfAces = 0;
    for(int i = 0; i < deck.size(); i++){
      sum += deck.get(i).getValue();
      if(deck.get(i).isAce()){
        numOfAces++;
      }
    }
    while (sum > 21 && numOfAces > 0) {
      sum -= 10; 
      numOfAces--;
    }
    return sum;
  }
  boolean isBlackJack(){
    if(deck.get(0).getValue() + deck.get(1).getValue() == 21){
      return true;
    } return false;
  }
  
  Deck split(){
    if(deck.get(0).getValue() == deck.get(1).getValue()){
      Deck playerDeck2 = new Deck(true);
      playerDeck2.addCard(takeCard());
      return playerDeck2;
    }
    return null;
  }

  void hit(Deck main){
    deck.add(main.takeCard());
  }
  
  boolean checkPossible(String type) {
    if(type.equals("hit") || type.equals("stay")){
      return true; 
    }
    else if(type.equals("split")){
      if(deck.get(0).getValue() == deck.get(1).getValue())
        return true;
      return false;
      
    } else if(type.equals("double down")){
      if(deck.get(0).getValue() + deck.get(1).getValue() == 11)
        return true;
      return false;
    } else {
      return false;
    }
  }

  void runDealer(Deck mainDeck){
    while(sumOfDeck()<17){
      hit(mainDeck);
    }
  }

  public String toString(){
    String yourDeck = "";
    for(int i = 0; i < deck.size(); i++){
      yourDeck += "\n" + (i+1) + ")" +" "+ deck.get(i);
    }
    return yourDeck;
  }
}
