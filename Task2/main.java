package Task2;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

abstract class Player {
    private int input;

    public void setInput(int input) {
        this.input = input;
    }

    public int getInput() {
        return input;
    }

    public void player(int num) {
        setInput(num);
    }

    public void print() {
        System.out.println(input);
    }

    public String toStringArr(int arr[]) {
        return Arrays.toString(arr);
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

    public int arrayToInt(int arrays[]) {
        StringBuilder strNum = new StringBuilder();

        for (int num : arrays)
        {
            strNum.append(num);
        }
        int finalInt = Integer.parseInt(strNum.toString());
        return finalInt;
    }

    public int[] secretCode(int input) {
        int[] num = new int[4];
        for (int i = 0; i < 4; i++) {
            num[i] = input % 10;
            input /= 10;
        }
        return num;
    }
}

class HumanPlayer extends Player {

}

class ComputerPlayer extends Player {
    public void testPrint() {
        System.out.println(arrayToInt(comGenerated()));
    }

    public int[] comGenerated() {             //recursive method that generates non-repeating array of numbers
        Random rand = new Random();
        int[] num = new int[4];
        for (int i = 0; i < 4; i++) {
            num[i] = rand.nextInt(9)+1;
        }
        if (isUnique(num)) {
            return num;
        } else {
            return comGenerated();//
        }
    }
}

class Main {
    public static void main(String[] args) {
        Game.game();
    }
}

class Game {
    public static void game() {
        ComputerPlayer computerPlayer = new ComputerPlayer();
        HumanPlayer humanPlayer = new HumanPlayer();

        int secretCode = computerPlayer.arrayToInt(computerPlayer.comGenerated());
        System.out.println(secretCode);
        Scanner sc = new Scanner(System.in);

        for (int i = 7; i > 0; i--) {
            int n = inputGate();
            System.out.println("You Guessed: " + n);
            int[] inputArr = inputArr(n);

            int bulls = bullCounter(inputArr, computerPlayer.secretCode(secretCode));
            int cows = cowCounter(inputArr, computerPlayer.secretCode(secretCode)) - bulls;
            if (bulls != 4) {
                String out = bulls + " Bulls " + cows + " Cows |" + "Try again " + i + " attempts left";
                System.out.println(out);
            }
            if (bulls == 4) {
                String out = "You Won";
                System.out.println(out);
            }
            if (cows == 0 && bulls == 0) {
                String out = bulls + " bulls " + cows + " cows | " + "Try again" + i + " attempts left";
                System.out.println(out);
            }
        }
    }

    public static int inputGate() {
        int n = input();
        if (!inputValidation(n)) {
            System.out.println("Please Enter A valid input");
            inputGate();
        }
        return n;
    }

    public static int input() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        return n;
    }

    public static int[] inputArr(int input) {
        int[] inputArr = new int[4];
        for (int i = 0; i < 4; i++) {
            inputArr[i] = input % 10;
            input /= 10;
        }
        return inputArr;
    }

    //inputValidation
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