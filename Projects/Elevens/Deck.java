import java.util.*;
class Deck{
  private ArrayList<Cards> deck = new ArrayList<Cards>();
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
  }
  void shuffleDeck(){
    Collections.shuffle(deck);
  }
  void clearDeck(){
    deck.clear();
  }
  Cards takeCard(){
    Cards card = deck.get(0);
    deck.remove(0);
    return card;
  }
  void removeCard(int k){
    deck.remove(k);
  }
  Cards getCard(int i){
    return deck.get(i);
  }
  int getNumber(){
    return deck.size();
  }
  void addCard(Cards x){
    deck.add(x);
  }
  static boolean sum11(Cards a, Cards b){
    if(a.getValue()+b.getValue()==11)
      return true;
    return false;
  }
  void replaceCard(int cardIndexes[], Deck secondaryDeck){
    if(secondaryDeck.getNumber()<cardIndexes.length){
      int x = cardIndexes.length-secondaryDeck.getNumber();
      for(int i = 0; i < secondaryDeck.getNumber(); i++){
        deck.remove(cardIndexes[i]-1);
        deck.add(cardIndexes[i]-1,secondaryDeck.takeCard());
      }
      for(int i = 0; i < x; i++){
        deck.remove(cardIndexes[i]-1);
      }
    } else{
      for(int i = 0; i < cardIndexes.length; i++){
        deck.remove(cardIndexes[i]-1);
        deck.add(cardIndexes[i]-1,secondaryDeck.takeCard());
      }
    }
  }
  boolean checkPossible(){
    for(int i = 0; i < deck.size(); i++){
      boolean faceCards[] = new boolean[2];
      for(int k = 0; k < deck.size(); k++){
        if(deck.get(i).getKind().equals("J")){
          if(deck.get(k).getKind().equals("Q")){faceCards[0] = true;}
          if(deck.get(k).getKind().equals("K")){faceCards[1] = true;}
        }
        else if(deck.get(k).getValue()==11-deck.get(i).getValue()){return true;}
      }
      if(faceCards[0]&&faceCards[1]){return true;}
    }
    return false;
  }
  public String toString(){
    String yourDeck = "";
    for(int i = 0; i < deck.size(); i++){
      yourDeck += "\n" + (i+1) + ")" + deck.get(i);
    }
    return yourDeck;
  } 
}
