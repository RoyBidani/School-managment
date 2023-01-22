package com.company;

import java.util.Scanner;

public class ScannerGetter {
    public static String scanString(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    public static int scanInt(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    public static double scanDouble(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextDouble();
    }
}
