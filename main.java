import java.util.Scanner;
import java.util.Arrays;
import java.util.*;
class main {

    public static void main(String[] args) {
        temp.test();
    }
}
class temp{
    public static void test() {
        computer c1 = new computer();
        player p1 = new player() {
        };

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while (!inputValidation(n)) {
            System.out.println("Please Enter A valid input");;
            n = sc.nextInt();
        }
        c1.testPrint();
        p1.player(n);
        p1.print();
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
}
class player {
    private int input;
    public void setinput(int input) {
         this.input = input;
    }
    public int getinput() {
        return input;
    }
    public void player(int num) {
        setinput(num);
    }
    public void print() {
        System.out.println(input);
    }
    public String toStringArr(int arr[]) {
        return Arrays.toString(arr);
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
    public int[] secretCode(int input) {
        int[] num = new int[4];
        for (int i = 0; i < 4; i++) {
            num[i] = input % 10;
            input /= 10;
        }
        return num;
    }
}
class computer extends player {
    public void testPrint() {
        System.out.println(arraytoInt(comGenerated()));
    }
    public int[] comGenerated() {             //recursive method that generates non-repeating array of numbers
        Random rand = new Random();
        int[] num = new int[4];
        for (int i = 0; i < 4; i++) {
            num[i] = rand.nextInt(10)+1%10;
        }
        if (isUnique(num)) {
            return num;
        } else {
            return comGenerated();
        }
    }
}
