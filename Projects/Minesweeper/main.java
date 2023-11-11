import java.util.Arrays;
import java.util.Scanner;
class Main {
  public static void main(String[] args) {
    Scanner kbReader = new Scanner(System.in);
    Blocks field[][] = new Blocks[16][16];
    String type = "";
    int x=0, bombspots[]=new int[40];
    boolean win=false, loose=false, go=false;
    for(int i = 0; i < bombspots.length;i++){
      int k=(int)((Math.random()*255));
      while(index.findIndex(bombspots,k)!=-1)
        k=(int)((Math.random()*255));
      bombspots[i]=k;
    }//creates bombs
    Arrays.sort(bombspots);
    System.out.print("   01  03  05  07  09  11  13  15");
    for(int i = 0; i < bombspots.length;i++)
      field[bombspots[i]/16][bombspots[i]%16]= new Blocks(true);
    //Places bombs
    for(int i = 0; i < field.length; i++) {
      for(int j = 0; j < field[i].length; j++) {
        if(field[i][j]==null){
          field[i][j] = new Blocks();
        }
      }
    }//makes board
    for(int i = 0; i < field.length; i++) {
      System.out.print("\n ");
      for(int j = 0; j < field[i].length; j++) {
        field[i][j].setBombsAround(Blocks.getAdjacent(field, i, j));
        System.out.print(field[i][j]+"");
      }
      System.out.print(" "+i);
    }//prints board
    //part 1 
    while(win==false&&loose==false){
      System.out.println("\nTo flag type F to reveal a spot type S:");
      type = kbReader.next();
      while(!type.toLowerCase().equals("s")&&!type.toLowerCase().equals("f")){
        System.out.println("invalid type please try again.");
        System.out.println("\nTo flag type F to reveal a spot type S:");
        type = kbReader.next();
      }
      if(type.toLowerCase().equals("s")){
        System.out.println("\nPlease enter the X coordinate of the spot");
        int xC = kbReader.nextInt();
        System.out.println("\nPlease enter the Y coordinate of the spot");
        int yC = kbReader.nextInt();
        System.out.println("("+xC+", "+yC+")");
        while(!(xC<=15&&xC>=0&&yC<=15&&yC>=0)){
          System.out.println("\nLooks like the coordinate you chose is not valid. Please try again.");
          System.out.println("\nPlease enter the X coordinate of the spot");
          xC = kbReader.nextInt();
          System.out.println("\nPlease enter the Y coordinate of the spot");
          yC = kbReader.nextInt();
          System.out.println("("+xC+", "+yC+")");
        }
        Blocks.expand(field,xC,yC);
        if(field[yC][xC].getBomb()==true)
          loose=true;
      } else if(type.toLowerCase().equals("f")){
        System.out.println("\nPlease enter the X coordinate of the flag");
        int xC = kbReader.nextInt();
        System.out.println("\nPlease enter the Y coordinate of the flag");
        int yC = kbReader.nextInt();
        System.out.println("("+xC+", "+yC+")");
        while(!(xC<=15&&xC>=0&&yC<=15&&yC>=0)){
          System.out.println("\nLooks like the coordinate you chose is not valid. Please try again.");
          System.out.println("\nPlease enter the X coordinate of the flag");
          xC = kbReader.nextInt();
          System.out.println("\nPlease enter the Y coordinate of the flag");
          yC = kbReader.nextInt();
          System.out.println("("+xC+", "+yC+")");
        }
        field[yC][xC].setFlag(true);
      }
      System.out.print("\033[H\033[2J");
      System.out.print("   01  03  05  07  09  11  13  15");
      for(int i = 0; i < field.length; i++) {
        System.out.print("\n  ");
        for(int j = 0; j < field[i].length; j++) {
          System.out.print(field[i][j]+"");
        }
        System.out.print(" "+i);
      }
    }
  }
}