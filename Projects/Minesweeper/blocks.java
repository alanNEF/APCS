class Blocks{
  private boolean isBomb,isVisible, isFlaged;
  int bombsAround;
  private String numbers[] = {"0️⃣ ","1️⃣ ","2️⃣ ","3️⃣ ","4️⃣ ","5️⃣ ","6️⃣ ","7️⃣ ","8️⃣ "};
  Blocks(){
    isBomb = false;
    isVisible=false;
    bombsAround=0;
    isFlaged=false;
 }
  Blocks(boolean a){
    isBomb = a;
    isVisible=false;
    bombsAround = 0;
    isFlaged=false;
  }
  public int getBombsAround(){
    return bombsAround;
  }
  public boolean getBomb(){
    return isBomb;
  }
  public boolean getVisibility(){
    return isVisible;
  }
  void setBombsAround(int a){
    bombsAround=a;
  }
  void makeVisible(){
    isVisible=true;
  }
  void setFlag(boolean a){
   isFlaged=a; 
  }
  boolean getFlag(){
    return isFlaged;
  }
  public static boolean isCoordinate(int i, int j) {
    if (i < 0 || j < 0 || i > 15 || j > 15) {
      return false;
    }
    return true;
  }
  public static void expand(Blocks[][] blocks, int i, int j){
    if(blocks[j][i].getBombsAround()==0 && blocks[j][i].getBomb()==false && blocks[j][i].getFlag()==false){
      if (isCoordinate(i - 1, j - 1) && blocks[j - 1][i - 1].getVisibility()==false) {
        expand(blocks, i - 1, j - 1);
        blocks[j][i].makeVisible();
      }
      if (isCoordinate(i - 1, j) && blocks[j][i - 1].getVisibility()==false) {
        blocks[j][i].makeVisible();
        expand(blocks, i - 1, j);
      }
      if (isCoordinate(i - 1, j + 1) && blocks[j + 1][i - 1].getVisibility()==false) {
        blocks[j][i].makeVisible();
        expand(blocks, i - 1, j + 1);
      }
      if (isCoordinate(i, j - 1) && blocks[j - 1][i].getVisibility()==false) {
        blocks[j][i].makeVisible();
        expand(blocks, i, j - 1);
      }
      if (isCoordinate(i, j + 1) && blocks[j + 1][i].getVisibility()==false) {
        blocks[j][i].makeVisible();
        expand(blocks, i, j + 1);
      }
      if (isCoordinate(i + 1, j - 1) && blocks[j - 1][i + 1].getVisibility()==false) {
        blocks[j][i].makeVisible();
        expand(blocks, i + 1, j - 1);
      }
      if (isCoordinate(i + 1, j) && blocks[j][i + 1].getVisibility()==false) {
        blocks[j][i].makeVisible();
        expand(blocks, i + 1, j);
      }
      if (isCoordinate(i + 1, j + 1) && blocks[j + 1][i + 1].getVisibility()==false) {
        blocks[j][i].makeVisible();
        expand(blocks, i + 1, j + 1);
      }
    } else 
      blocks[j][i].makeVisible();
  }
  public static int getAdjacent(Blocks[][] blocks, int i,int j){
    int z=0,t=0;
    Blocks bombspots[]=new Blocks[8];
    for (int x = (i > 0 ? -1 : 0); x <= (i < 15 ? 1 : 0);x++) {
      for (int y = (j > 0 ? -1 : 0);y <= (j < 15 ? 1 : 0); y++) {
        if ((x != 0 || y != 0)) {
          bombspots[t]=blocks[(i)+x][(j)+y];
          t++;
        }
      }
    }
    for(int p=0;p<8;p++){
      if(bombspots[p]!=null)
        if(bombspots[p].getBomb()==true)
          z++;
    } return z;
  } 
  public String toString(){
    if(isFlaged==true)
      return "🚩";
    if(isVisible==true){
      if(isBomb==false)
        return numbers[bombsAround];
      return "💣";
    }
    return "⬜";
  }
}