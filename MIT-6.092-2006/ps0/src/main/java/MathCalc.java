package main.java;

import java.util.Scanner;

public class MathCalc {
    public static void main(String[] args){
        double radius = 0.0;
        double circleArea = 100.0;
        int feet = 0;
        int inches = 0;
        radius = Math.sqrt(circleArea/Math.PI);
        feet = (int)Math.floor(radius);
        inches = (int)Math.round(12.0*(radius - feet));
        System.out.println("the radius of a circle with area" + circleArea +
                " square feet is \n" + feet + " feet " + inches + " inches");
        System.out.println("please enter the volume of a planet in miles");
        Scanner keyboard = new Scanner(System.in);
        int inputTemp = Integer.valueOf(keyboard.nextLine());
        int vol = volumeFromDiameter(inputTemp);
        System.out.println("the volume of earth is "+ vol + " cubic miles");
    }

    public static int volumeFromDiameter(int diameter){
        int vol = 0;
        int radius = diameter / 2;
        vol = (int) ((int)(4/3)*Math.PI*Math.pow(radius,3));
        return vol;
    }

}
