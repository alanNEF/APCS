class CheckerPiece{
  private boolean isKing;
  private String team;
  CheckerPiece(){
    isKing = false;
    team="black";
  }
  CheckerPiece(String f){
    isKing = false;
    team=f.toLowerCase();
  }
  public void setKing(){
    isKing = true;
  }
  public boolean isKing(){
    return isKing;
  }
  public String getTeam(){
    return team;
  }
  public boolean checkMoves(int x, int y, int a, int b){
    if(isKing==false){
      if(team.equals("black")){
        if((a==x+1||a==x-1) && (b==y+1)){
         return true;
        }
        return false;
      } else{
        if((a==x+1||a==x-1) && (b==y-1)){
         return true;
        }
        return false;
      }
    } else{
      if((x==a+1||x==a-1) && (y==b+1||y==b-1)){
        return true;
      }
      return false;
      
    }
  }
  public void makeKing(){
    isKing=true;
  }
  public String toString(){
    if(isKing==false){
      if(team.equals("black"))
        return "\u26C0";
      else
        return "\u26C2";
    } else{
      if(team.equals("black"))
        return "\u26C1";
      else
        return "\u26C3";
    }
  }
}
