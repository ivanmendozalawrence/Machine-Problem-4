package Task4;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        game g = new game();
        g.turn();
    }

}
class game {
    player p = new player();
    public  void turn(){
        System.out.print("Please select a type of AI\nEasy AI: 1\nMedium AI: 2\n");
        int choice = p.input();
        switch (choice) {
            case 1 -> {
                System.out.println("Easy AI selected");
                easyAi easy = new easyAi();
                easy.start(easy.secretCode());
            }
            case 2 -> {
                System.out.println("Medium AI selected");
                midAi medium = new midAi();
                medium.start(medium.secretCode());
            }
        }
    }
}
class easyAi{
    computer com = new computer();
    human play = new human();

    public int secretCode(){
        int secretCode;
        System.out.println("Enter a 4 digit number");
        secretCode = play.input();
        System.out.println("Secret Code: " + secretCode);
        System.out.println("-----------------------------------------------------");
        return secretCode;
    }
    public void start(int secretCode){

        int[] secretCodeArr = play.inputArr(secretCode);
        int humanCtr = 7;
        int computerCtr = 7;
        int turn = 0;
        while (turn < 14){
            int computerGuess = com.generatedInt();
            turn++;
            if (turn % 2 == 0){
                System.out.println("Computer's Turn");
                int[] computerGuessArr = com.inputArr(computerGuess);
                int bulls = com.bullCounter(secretCodeArr,computerGuessArr);
                int cows = com.cowCounter(secretCodeArr,computerGuessArr) - bulls;
                if (bulls !=4 ) {
                    System.out.println("Computer Guessed: " + computerGuess);
                    humanCtr--;
                    String out = bulls + " Bulls " + cows + " Cows |"+"Try again "+ humanCtr + " attempts left";
                    System.out.println(out);
                    System.out.println("-----------------------------------------------------");
                }

                else if (cows == 0 && bulls ==0){
                    System.out.println("Computer Guessed: " + computerGuess);
                    humanCtr--;
                    String out = bulls + "bulls " + cows + " cows | " + "Try again" + humanCtr + " attempts left";
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
                int bulls = com.bullCounter(secretCodeArr,humanGuessArr);
                int cows = com.cowCounter(secretCodeArr,humanGuessArr) - bulls;
                if(bulls == 4) {
                    System.out.println("Player Guessed: " + humanGuess);
                    String out = "You Won";
                    System.out.println(out);
                    System.out.println("-----------------------------------------------------");
                }
                else if (bulls == 0 && cows == 0) {
                    System.out.println("Player Guessed: " + humanGuess);
                    computerCtr--;
                    String out = bulls + " bulls " + cows + " cows | " + "Try again " + computerCtr + " attempts left";
                    System.out.println(out);
                    System.out.println("-----------------------------------------------------");
                }
                else{
                    System.out.println("Player Guessed: " + humanGuess);
                    computerCtr--;
                    String out = bulls + " Bulls " + cows + " Cows |"+"Try again "+ computerCtr + " attempts left";
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
}
class midAi extends easyAi {
    public void start(int secretCode){
        int[][] aiGuesses = new int[8][4]; // 2D array to store the AI's guesses
        int[] secretCodeArr = play.inputArr(secretCode);
        int humanCtr = 7;
        int computerCtr = 7;
        int turn = 0;
        while (turn < 14 ) {
            int computerGuess = com.generatedInt();
            turn++;
            boolean repeatGuess = isRepeatGuess(computerGuess, aiGuesses);
            if (!repeatGuess) {
                // Store the guess in the array
                aiGuesses[7 - computerCtr] = com.inputArr(computerGuess);
                if (turn % 2 == 0) {
                    System.out.println("Computer's Turn");
                    int[] computerGuessArr = com.inputArr(computerGuess);
                    int bulls = com.bullCounter(secretCodeArr, computerGuessArr);
                    int cows = com.cowCounter(secretCodeArr, computerGuessArr) - bulls;
                    if (bulls != 4) {
                        System.out.println("Computer Guessed: " + computerGuess);
                        humanCtr--;
                        String out = bulls + " Bulls " + cows + " Cows |" + "Try again " + humanCtr + " attempts left";
                        System.out.println(out);
                        System.out.println("-----------------------------------------------------");
                    } else if (cows == 0 && bulls == 0) {
                        System.out.println("Computer Guessed: " + computerGuess);
                        humanCtr--;
                        String out = bulls + "bulls " + cows + " cows | " + "Try again " + humanCtr + " attempts left";
                        System.out.println(out);
                        System.out.println("-----------------------------------------------------");
                    } else {
                        System.out.println("Computer Guessed: " + computerGuess);
                        String out = "Computer Won";
                        System.out.println(out);
                        System.out.println("-----------------------------------------------------");
                    }
                } else {
                    System.out.println("Player's Turn");
                    int humanGuess = play.input();
                    int[] humanGuessArr = play.inputArr(humanGuess);
                    int bulls = com.bullCounter(secretCodeArr, humanGuessArr);
                    int cows = com.cowCounter(secretCodeArr, humanGuessArr) - bulls;
                    if(bulls == 4) {
                        System.out.println("Player Guessed: " + humanGuess);
                        String out = "You Won";
                        System.out.println(out);
                        System.out.println("-----------------------------------------------------");
                    }
                    else if (bulls == 0 && cows == 0) {
                        System.out.println("Player Guessed: " + humanGuess);
                        computerCtr--;
                        String out = bulls + " bulls " + cows + " cows | " + "Try again " + computerCtr + " attempts left";
                        System.out.println(out);
                        System.out.println("-----------------------------------------------------");
                    }
                    else{
                        System.out.println("Player Guessed: " + humanGuess);
                        computerCtr--;
                        String out = bulls + " Bulls " + cows + " Cows |"+"Try again "+ computerCtr + " attempts left";
                        System.out.println(out);
                        System.out.println("-----------------------------------------------------");
                    }

                }

            } else {
                // AI repeats a guess, skip this turn
                turn--;
            }
            if (humanCtr == 0 && computerCtr ==0){

                System.out.println("No one guessed correctly, Its a tie");
                System.out.println("-----------------------------------------------------");
            }
        }

    }

    private boolean isRepeatGuess(int computerGuess, int[][] aiGuesses) {
        for (int i = 0; i < aiGuesses.length; i++) {
            if (Arrays.equals(aiGuesses[i], com.inputArr(computerGuess))) {
                return true;
            }
        }
        return false;
    }
}




class player {
    private int input;

    public String print(int arr[]) {                      //print method that prints arrays and converts it to format able string
        return  (Arrays.toString(arr));
    }
    public int input() {
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        if(input==2||input==1){
            return input;
        }
        else{
            System.out.println("Invalid Input");
            return input();
        }
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
class computer extends player {
    public int generatedInt() {             //recursive method that generates non-repeating array of numbers
        Random rand = new Random();
        int[] num = new int[4];
        for (int i = 0; i < 4; i++) {
            num[i] = rand.nextInt(9)+1;
        }
        if (isUnique(num)) {
            return arraytoInt(num);
        } else {
            return generatedInt();
        }
    }





}
class human extends player {
   public int input() {
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        if (!inputValidation(input)) {
            System.out.println("Invalid Input, please try again");
            return input();
        }
        else {
            return input;
        }

    }

}





