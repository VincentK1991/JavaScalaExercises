package main.java;
import java.util.Scanner;

public class Primes {
    public static void main(String[] args){
        int nValues = 50;
        boolean isPrime = true;
        for(int i = 2; i <= nValues; i++){
            isPrime = true;
            for (int j = 2; j < i; j++){
                if (i % j == 0){
                    isPrime = false;
                    break;
                }
            }
            if (isPrime){
                System.out.println(i);
            }
        }
        System.out.println("please enter the integer you want to consider > ");
        Scanner keyboard = new Scanner(System.in);
        int inputTemp = Integer.valueOf(keyboard.nextLine());
        System.out.println("following are the primes: ");
        for(int i = 2; i <= inputTemp; i++){
            if(isPrime(i)){
                System.out.println(i);
            }
        }
    }

    public static boolean isPrime(int item){
        int divisor = (int)Math.floor(Math.sqrt(item));
        for (int i = 2; i <= divisor; i++){
            if (item % i != 0){
                continue;
            }
            else {
                return false;
            }
        }
        return true;
    }
}
