package Task3;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

public class main {
    public static void main(String[] args) {
        game g = new game();
        g.turn();
    }

}
class game {

public static void turn(){
    computer com = new computer();
    human play = new human();
    int secretCode;
    System.out.println("Enter a 4 digit number");
    secretCode = play.input();
    System.out.println("Secret Code: " + secretCode);
    System.out.println("-----------------------------------------------------");
    int[] secretCodeArr = play.inputArr(secretCode);
    int h = 7;
    int c1 = 7;
        int turn = 0;
        while (turn < 14){
            int computerGuess = com.generatedInt();
            turn++;
            if (turn % 2 == 0){
                System.out.println("Computer's Turn");
                int[] computerGuessArr = com.inputArr(computerGuess);
                int bulls = bullCounter(secretCodeArr,computerGuessArr);
                int cows = cowCounter(secretCodeArr,computerGuessArr) - bulls;
                if (bulls !=4 ) {
                    System.out.println("Computer Guessed: " + computerGuess);
                    h--;
                    String out = bulls + " Bulls " + cows + " Cows |"+"Try again "+ h + " attempts left";
                    System.out.println(out);
                    System.out.println("-----------------------------------------------------");
                }

                 else if (cows == 0 && bulls ==0){
                    System.out.println("Computer Guessed: " + computerGuess);
                    h--;
                    String out = bulls + "bulls " + cows + " cows | " + "Try again" + h + " attempts left";
                    System.out.println(out);
                    System.out.println("-----------------------------------------------------");
                }
                else {
                    System.out.println("Computer Guessed: " + computerGuess);
                    String out = "Computer Won";
                    System.out.println(out);
                    System.out.println("-----------------------------------------------------");
                }
            }else{
                System.out.println("Player's Turn");
                int humanGuess = play.input();
                int[] humanGuessArr = play.inputArr(humanGuess);
                int bulls = bullCounter(secretCodeArr,humanGuessArr);
                int cows = cowCounter(secretCodeArr,humanGuessArr) - bulls;
                if(bulls == 4) {
                    System.out.println("Player Guessed: " + humanGuess);
                    String out = "You Won";
                    System.out.println(out);
                    System.out.println("-----------------------------------------------------");
                }
                else if (bulls == 0 && cows == 0) {
                    System.out.println("Player Guessed: " + humanGuess);
                    c1--;
                    String out = bulls + " bulls " + cows + " cows | " + "Try again " + c1 + " attempts left";
                    System.out.println(out);
                    System.out.println("-----------------------------------------------------");
                }
                else{
                    System.out.println("Player Guessed: " + humanGuess);
                    c1--;
                    String out = bulls + " Bulls " + cows + " Cows |"+"Try again "+ c1 + " attempts left";
                    System.out.println(out);
                    System.out.println("-----------------------------------------------------");
                }

            }
        }
        if (turn == 14){

            System.out.println("No one guessed correctly, Its a tie");
            System.out.println("-----------------------------------------------------");
        }

}

    public static int bullCounter(int[] inputArr, int[] secretCode) {
        int bulls = 0;
        for (int i = 0; i < 4; i++) {
            if (secretCode[i] == inputArr[i]) {
                bulls++;
            }
        }
        return bulls;
    }
    public static int cowCounter(int[] inputArr, int[] secretCode) {
        int cows = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (secretCode[i] == inputArr[j]) {
                    cows++;
                }
            }
        }
        return cows;
    }
}
class player {
    private int input;

    public String print(int arr[]) {                      //print method that prints arrays and converts it to format able string
        return  (Arrays.toString(arr));
    }
    public static int input() {
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        if (!inputValidation(input)) {
            System.out.println("Please Enter A valid input");
            input();
        }
        return input;
    }
    public static int[] inputArr(int input) {       //turns inputs into arrays
        int[] inputArr = new int[4];
        for (int i = 0; i < 4; i++) {
            inputArr[i] = input % 10;
            input /= 10;
        }
        return inputArr;
    }


    public boolean isUnique(int[] arr){                         // method to check if the numbers are unique
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    return false;
                }
            }
        }
        return true;
    }
    public int arraytoInt(int arrays[]) {
        StringBuilder strNum = new StringBuilder();

        for (int num : arrays)
        {
            strNum.append(num);
        }
        int finalInt = Integer.parseInt(strNum.toString());
        return finalInt;
    }
    public static boolean inputValidation(int input) {
        if (input < 1000 || input > 9999) {
            return false;
        }
        int[] num = new int[4];
        for (int i = 0; i < 4; i++) {
            num[i] = input % 10;
            input /= 10;
        }
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                if (num[i] == num[j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
class computer extends player{
    public int generatedInt() {             //recursive method that generates non-repeating array of numbers
        Random rand = new Random();
        int[] num = new int[4];
        for (int i = 0; i < 4; i++) {
            num[i] = rand.nextInt(9)+1;
        }
        if (isUnique(num)) {
            return arraytoInt(num);
        } else {
            return generatedInt();//
        }
    }
}
class human extends player{

}




