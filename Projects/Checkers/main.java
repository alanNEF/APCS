import java.util.Scanner;
class Main {  
  public static void main(String[] args) {
    int xpiece=0,ypiece=0,xMove=0,yMove=0,count=0,blackCount=0, whiteCount=0;
    boolean go=false, win=false, extra=false;
    String pieceCoordinates,moveCoordinates, winner="null", lastTeam="null";
    Scanner kbReader = new Scanner(System.in);
    CheckerPiece black = new CheckerPiece();
    CheckerPiece blackKing = new CheckerPiece();
    blackKing.setKing();
    CheckerPiece white = new CheckerPiece("white");
    CheckerPiece whiteKing = new CheckerPiece("white");
    whiteKing.setKing();
    CheckerPiece board[][]= new CheckerPiece[8][8];
    for(int i = 0; i < board.length; i++) {
      for(int j = 0; j < board[i].length; j++) {
        if(i<3){
          if(i%2==0 && j%2!=0)
            board[i][j]=black;
          if(i%2!=0 && j%2==0)
            board[i][j]=black;
        } if(i>4){
          if(i%2==0 && j%2!=0)
            board[i][j]=white;
          if(i%2!=0 && j%2==0)
            board[i][j]=white;
        }
      }
    }
    System.out.print("X   0   1   2   3   4   5   6   7");
    for(int i = 0; i < board.length; i++) {
      System.out.println("\n");
      System.out.print(7-i+":  ");
      for(int j = 0; j < board[i].length; j++) {
        if(board[i][j]==null)
          System.out.print("-   ");
        else{
          System.out.print(board[i][j]+"   ");
        }
      }
    }
    System.out.println("\n\nY");
    while(win==false){
      while(go==false){
        System.out.println("\nPlease enter the coordinates of the piece you would like to move(Please use (x,y) format)");
        pieceCoordinates = kbReader.nextLine();
        xpiece=Character.getNumericValue(pieceCoordinates.charAt(1));
        ypiece=7-(Character.getNumericValue(pieceCoordinates.charAt(3)));
        if(board[ypiece][xpiece]!=null&&(!board[ypiece][xpiece].getTeam().equals(lastTeam)||extra==true)){
          go=true;
          lastTeam=board[ypiece][xpiece].getTeam();
        } else
          System.out.println("\nLooks like the coordinates you selected do not contain a piece you can move please try again.");
      }
      go=false;
      while(go==false){
        System.out.println("Please enter the coordinates you would like the piece to move to(Please use (x,y) format)");
        if(extra==true){
          go=true;
          extra=false;
        } else{
          moveCoordinates = kbReader.nextLine();
          xMove=Character.getNumericValue(moveCoordinates.charAt(1));
          yMove=7-(Character.getNumericValue(moveCoordinates.charAt(3)));
          if(board[ypiece][xpiece].checkMoves(xpiece, ypiece, xMove, yMove)==true)
            go=true;
          else
            System.out.println("\nLooks like the piece you have selected can't move there please try again!");
        }
      }
      int moveY=(yMove-ypiece);
      int moveX=(xMove-xpiece);
        if(board[yMove][xMove]==null || !(board[yMove][xMove].getTeam().equals(board[ypiece][xpiece].getTeam()))){
          if(board[yMove][xMove]!=null&&board[yMove+(yMove-ypiece)][xMove+(xMove-xpiece)]==null){
            if((yMove+(2*(moveY))<=7&&xMove+(2*(moveX))<=7)){
              board[yMove+(yMove-ypiece)][xMove+(xMove-xpiece)]=board[ypiece][xpiece];
              board[ypiece][xpiece]=null;
              board[yMove][xMove]=null;
              ypiece=yMove+(moveY);
              xpiece=xMove+(moveX);
              yMove+=2*(moveY);
              xMove+=2*(moveX);
              count++;
              if(board[yMove][xMove]!=null&&!(board[yMove][xMove].getTeam().equals(board[ypiece][xpiece].getTeam()))){
                if((yMove<=6&&xMove<=6)&&(yMove>=1&&xMove>=1)){
                  if(board[yMove][xMove]!=null&&board[yMove+moveY][xMove+moveX]==null&&(xMove+moveX<=7&&xMove+moveX>=0)&&(yMove+moveY<=7&&yMove+moveY>=0)){
                    extra = true;
                  }
                }
              }
               System.out.println(xMove+" and "+ yMove);
            }
            if(board[ypiece][xpiece].getTeam().equals("black")&&ypiece==7){
              board[ypiece][xpiece]=blackKing;
            } if(board[ypiece][xpiece].getTeam().equals("white")&&ypiece==0){
              board[ypiece][xpiece]=whiteKing;
            }
          } else if(count==0&&board[yMove][xMove]==null){
            board[yMove][xMove]=board[ypiece][xpiece];
            board[ypiece][xpiece]=null;
            go=true;
            if(board[yMove][xMove].getTeam().equals("black")&&yMove==7){
              board[yMove][xMove]=blackKing;
            } if(board[yMove][xMove].getTeam().equals("white")&&yMove==0){
              board[yMove][xMove]=whiteKing;
            }
          } 
        } else{
          System.out.println("Looks like you can't move the piece there please try again!\n");
          System.out.println("Please enter the coordinates you would like the piece to move to(Please use (x,y) format)");
          moveCoordinates = kbReader.nextLine();
          xMove=Character.getNumericValue(moveCoordinates.charAt(1));
          yMove=7-(Character.getNumericValue(moveCoordinates.charAt(3)));
        }
      go=false;
      for(int i = 0; i < board.length; i++) {
        for(int j = 0; j < board[i].length; j++) {
          if(board[i][j]!=null){
            if(board[i][j].getTeam().equals("black"))
              blackCount++;
            else
              whiteCount++;
          }
        }
      }
      if(whiteCount==0){
        win=true;
        winner="Black";
      } if(blackCount==0){
        win=true;
        winner="White";
      }
      //System.out.print("\033[H\033[2J");
      System.out.println(extra);
      System.out.println("Piece Moved! \n"); 
      System.out.print("X   0   1   2   3   4   5   6   7");
      for(int i = 0; i < board.length; i++) {
        System.out.println("\n");
        System.out.print(7-i+":  ");
        for(int j = 0; j < board[i].length; j++) {
          if(board[i][j]==null)
            System.out.print("-   ");
          else{
            System.out.print(board[i][j]+"   ");
          }
        }
      }
      System.out.println("\n\nY");
    }
    System.out.println(winner+" has won!\n\n\n Click Run To Play Again!");
  }
}
