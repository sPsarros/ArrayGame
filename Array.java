import java.util.Random;

public class Array {
    Player p1 = new Player();
    Random random = new Random();

    private final int N=4,M=5;
    private final char[][] finalArray = new char[N][M];
    private final char[][] charArray = new char[N/2][M];//Contains single chars only
    private final char[][] stars = new char[N][M];//Array with hidden characters
    int i=10;

    public Array(){
        createArray();
        fillArray(); fillStars();
        print(finalArray);
        while(p1.begin());//Waits for user to type yes
        while(i>0){System.out.println(); i--;} //Creating enough space to hide the character array
        print(stars);
        appear();
    }

    //Transfers and creates the final array using the charArray
    private void fillArray(){
        for(int i=0;i<charArray.length;i++){
            for(int j=0;j<charArray[i].length;j++){
                char c = charArray[i][j];
                int times=2;//Every character must be a pair (exists two times)
                
                while(times>0){
                    int num1 = random.nextInt(N);//Random positions in the final array
                    int num2 = random.nextInt(M);
                    if(!Character.isLetter(finalArray[num1][num2]) && times>0){ //If there is still space in the array
                        finalArray[num1][num2]=charArray[i][j];
                        times--;
                    }
                }
            }
        }         
    }
    
    //In the charArray every char must exist only once
    private void createArray(){
        for(int i=0;i<charArray.length;i++){
            for(int j=0;j<charArray[i].length;j++){           
                while(true){    
                    char c = (char)(random.nextInt(25)+65);//A-Z
                    if(checkChar(charArray,c)){
                        charArray[i][j]=c;
                        break;
                    }
                }
            }
        }
    }

    //Checks if the given char is in the array
    private boolean checkChar(char [][] arr,char c){
        for(int i=0;i<arr.length;i++) {
            for(int j=0;j<arr[i].length;j++) {
                if (arr[i][j] == c) return false;
            }
        }
        return true;
    }

    /*
     * From now on there will be also functions which were
     * used to edit the array with hidden characters
     */

    private void fillStars(){
        for(int i=0;i<stars.length;i++)
            for(int j=0;j<stars[i].length;j++)
               stars[i][j]='*';
    }

    //Checking if player guessed the right positions for a pair
    private boolean checkPos(int x1,int y1,int x2,int y2){
        if(x1==x2 && y1==y2) return false; //Player cannot give the same position
        if(x1>N || x2>N || y1>M||y2>M) return false; //or a number greater than the allocated space of the array

        if(finalArray[x1][y1] == finalArray[x2][y2])
            return true;
        else
            return false;
    }

    //If the guess is right the stars transforms now to the visible characters
    private void appear(){
        int pairs=0;//If pairs==10, player have found all of them

        while(p1.getTries()>0 && pairs!=10) {
            p1.givePos();
            if(checkPos(p1.getX1(), p1.getY1(), p1.getX2(), p1.getY2())) {
                stars[p1.getX1()][p1.getY1()] = stars[p1.getX2()][p1.getY2()] // * -> character
                 =  finalArray[p1.getX1()][p1.getY1()];

                p1.setRounds();//Every time player guesses right, they go to the next round
                print(stars);
                pairs++;
            }else{
                System.out.println("Wrong positions! Try again...");
                p1.setTries();
            }
        }

        if(pairs==10) System.out.println("You won!");
        endgame();
    }

    //Prints the array
    private void print(char[][] arr){
        for(int i=0;i<arr.length;i++) {
            for(int j=0;j<arr[i].length;j++)
                System.out.print(arr[i][j] + " ");
            System.out.println();
        }
    }

    private void endgame(){
        print(finalArray);
        System.out.println("Rounds: "+ p1.getRounds());
    }

}
