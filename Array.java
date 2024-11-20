import java.util.Random;

public class Array {
    Player p1 = new Player();
    Random random = new Random();

    private final int N=4,M=5;
    private final char[][] finalArray = new char[N][M];//Τελικός πίνακας
    private final char[][] charArray = new char[N/2][M];//Ν/2 περιέχει τα γράμματα
    private final char[][] stars = new char[N][M];//Πίνακας με κρυμμένους χαρακτήρες
    int i=10;

    public Array(){
        createArray();
        fillArray(); fillStars();
        print(finalArray);
        while(p1.begin());// Αναμονή να είναι έτοιμος ο παίκτης
        while(i>0){System.out.println(); i--;} //Κρύψιμο που πίνακα με τους χαρακτήρες
        print(stars);
        appear();
    }

    //Συνάρτηση που τοποθετεί τα ζεύγη στον τελικό πίνακα
    private void fillArray(){
        for(int i=0;i<charArray.length;i++){
            for(int j=0;j<charArray[i].length;j++){
                char c = charArray[i][j];
                int times=2;//Πέρασμα του γράμματος 2 φορές μόνο
                
                while(times>0){
                    int num1 = random.nextInt(N);//Τυχαίες θέσεις για τον τελικό πίνακα
                    int num2 = random.nextInt(M);
                    if(!Character.isLetter(finalArray[num1][num2]) && times>0){
                        finalArray[num1][num2]=charArray[i][j];
                        times--;
                    }
                }
            }
        }         
    }
    
    //Δημιουργεί μοναδικά γράμματα για τον πίνακα charArray
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

    //Ελέγχει αν το γράμμα που δίνουμε υπάρχει ήδη στον πίνακα
    private boolean checkChar(char [][] arr,char c){
        for(int i=0;i<arr.length;i++) {
            for(int j=0;j<arr[i].length;j++) {
                if (arr[i][j] == c) return false;
            }
        }
        return true;
    }

    /*
     * Απο εδώ και κάτω υπάρχουν οι λειτουργίες και
     * για τον πίνακα με τους κρυμμένους χαρακτήρες
     */

    private void fillStars(){
        for(int i=0;i<stars.length;i++)
            for(int j=0;j<stars[i].length;j++)
               stars[i][j]='*';
    }

    //Έλεγχος αν ο χρήστης έχει εισάγει σωστές θέσεις ζεύγους
    private boolean checkPos(int x1,int y1,int x2,int y2){
        if(x1==x2 && y1==y2) return false;
        if(x1>N || x2>N || y1>M||y2>M) return false;

        if(finalArray[x1][y1] == finalArray[x2][y2])
            return true;
        else
            return false;
    }

    //Συνάρτηση αλλαγής χαρακτήρων του πίνακα stars
    private void appear(){
        int pairs=0;//Μέτρηση ζευγών, στα 10 σταματάει το παιχνίδι

        while(p1.getTries()>0 && pairs!=10) {
            p1.givePos();//Πέρασμα συντεταγμένων του χρήστη
            if(checkPos(p1.getX1(), p1.getY1(), p1.getX2(), p1.getY2())) {
                stars[p1.getX1()][p1.getY1()] = stars[p1.getX2()][p1.getY2()] //Αλλάζει το * σε χαρακτήρα
                 =  finalArray[p1.getX1()][p1.getY1()];

                p1.setRounds();//Αυξάνει τον γύρο κάθε φορά
                print(stars);
                pairs++;//Μόλις ο χρήστης βρει ένα ζευγάρι, αυτό αυξάνεται
            }else{
                System.out.println("Wrong positions! Try again...");
                p1.setTries();
            }
        }

        if(pairs==10) System.out.println("You won!");
        endgame();
    }

    //Τυπώνει τον δοσμένο πίνακα
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
