class Cards {
  private int suit;
  private String kind; 
  private String[] suits={"♠","♥","♣","♦"};
  Cards(){
    suit=-1;
    kind=null;
  }
  Cards(int a, String b){
    suit=a;
    kind=b.toUpperCase();
  }
  String getSuit(){
    return suits[suit];
  }
  String getKind(){
    return kind;
  }
  boolean isFaceCard(){
    if(kind.equals("K")||kind.equals("J")||kind.equals("Q")){
      return true;
    }
    return false;
  }
  boolean isAce(){
    if(kind.equals("A")){
      return true;
    }
    return false;
  }
  int getValue(){
    if(kind.equals("K")||kind.equals("J")||kind.equals("Q")){
      return 10;
    } else if(kind.equals("A")){
      return 11;
    } else{
      return Integer.parseInt(kind);
    }
  }
  // int getValue(Deck deck){
  //   if(kind.equals("K")||kind.equals("J")||kind.equals("Q")){
  //     return 10;
  //   } else if(isAce()){
      
  //   } else{
  //     return Integer.parseInt(kind);
  //   }
  // }
  public String toString(){
    return kind+" of "+suits[suit]+"  ";
  }
}