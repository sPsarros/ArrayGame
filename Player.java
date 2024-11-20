import java.util.Scanner; 

public class Player {
    private int tries = 4; //Tries of player
    private int rounds = 0; //Rounds of the game
    int x1,y1,x2,y2; //Positions given by player

    Scanner input = new Scanner(System.in);

    //Checks if user is ready to start
    protected boolean begin(){
        System.out.print("Ready to start (yes/no)? ");
        String start = input.nextLine();
        return start.equals("no");
    }

    //Getting positions of a possible pair
    protected void givePos(){
        System.out.print("Give a guess for a letter (x,y): ");
        x1 = input.nextInt();
        y1 = input.nextInt();
        System.out.print("Now guess where the pair of that letter is (x,y): ");
        x2 = input.nextInt();
        y2 = input.nextInt();
    }

    //Must have functions to be an encapsulation class
    public int getTries(){return tries;}
    public void setTries(){tries--;}
    public int getRounds(){return rounds;}
    public void setRounds(){rounds ++;}
    public int getX1(){return x1;}
    public int getY1() {return y1;}
    public int getX2(){return x2;}
    public int getY2() {return y2;}
}
