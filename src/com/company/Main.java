package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // UI Methods ...
        System.out.println("Welcome to the Enigma machine.");
        System.out.println("Choose which type of cipher  you want.");

        System.out.println("1. Number cipher");
        System.out.println("2. Caesar cipher");
        System.out.println("3. Viginère cipher");
        System.out.println("0. Terminate program");

        System.out.println("Choose by entering a number between 0-3");
        int cipherType = scanner.nextInt();

        // Exits program if input is 0
        if (cipherType == 0) {
            System.exit(0);
        }

        System.out.println("Do you wish to encode or decode? Type e/d");
        char encodeOrDecode = scanner.next().charAt(0);

        // Number cipher encode or decode
        if (cipherType == 1 && encodeOrDecode == 'e') {
            System.out.println("Number cipher encode");
            encryptNumberMenu();
        } else if (cipherType == 1 && encodeOrDecode == 'd') {
            System.out.println("Number cipher decode");
        decryptNumberMenu();
        }

        // Caesar cipher encode or decode
        if (cipherType == 2 && encodeOrDecode == 'e') {
            System.out.println("Caesar cipher encode");
            encryptCaesarMenu();
        } else if (cipherType == 2 && encodeOrDecode == 'd') {
            System.out.println("Caesar cipher decode");
            decryptCaesarMenu();
        }

        // Viginère cipher encode or decode
        if (cipherType == 3 && encodeOrDecode == 'e') {
            System.out.println("Viginère cipher encode");
/*
            viginereCipherEncode();
*/
        } else if (cipherType == 3 && encodeOrDecode == 'd') {
            System.out.println("Viginère cipher decode");
/*
            viginereCipherDecode();
*/
        }
    }

    // NumberCipher
    public static void encryptNumberMenu() {
        // Asks user for text input
        String plainText = userTextInput();
        System.out.println("You wish to encrypt: " + plainText);

        // Calls numberEncrypt with the text input from the user
        numberEncrypt(plainText);

        // Prints cipher result
        int[] cipherResult = numberEncrypt(plainText);
        System.out.print("The result of the encryption is: ");
        System.out.println(Arrays.toString(cipherResult));


    }

    private static int[] numberEncrypt(String plainText) {
        int[] list = textToListOfNumbers(plainText);
        return list;
    }

    public static void decryptNumberMenu() {
        // Asks user for text input
        String cipherText = userTextInput();
        System.out.println("You wish to decode: " + cipherText);

        // Calls numberDecrypt with the input from user
        numberDecrypt(cipherText);

        // Prints the result of the decoding
        String plainTextResult = numberDecrypt(cipherText);
        System.out.println(plainTextResult);
    }

    private static String numberDecrypt(String cipherText) {
        int[] numbers = Arrays.stream(cipherText.split("," + " ")).mapToInt(Integer::parseInt).toArray();

        String listDecoded = listOfNumbersToText(numbers);
        return listDecoded;
    }


    // Caesar
    public static void decryptCaesarMenu() {
        // Asks for user text input
        String cipherText = userTextInput();
        System.out.println("You wish to decode: " + cipherText);

        // Asks for a shift value from the user
        System.out.println("Please enter a shift value for the decoding");
        int shiftValue = getShiftValue();
        shiftValue = - shiftValue;

        // Calls caesarDecrypt with the text input & shift input
        String decodeResult = caesarDecrypt(cipherText, shiftValue);

        // Prints the result from the decoding
        System.out.print("The result of the encryption is: ");
        System.out.println(decodeResult);

    }

    public static String caesarDecrypt(String cipherText, int shiftValue) {
        // textToListOfNumbers
        int[] list = textToListOfNumbers(cipherText);

        // shiftListOfNumbers
        shiftListOfNumbers(list , shiftValue);
        list = shiftListOfNumbers(list, shiftValue);

        // listOfNumbersToText
        return listOfNumbersToText(list);
    }

    public static void encryptCaesarMenu() {
        // Ask user for text input
        String plainText = userTextInput();
        System.out.println("You wish to encrypt: " + plainText);

        // Asks user for shift value
        System.out.println("Please a shift value for the encryption");
        int shiftValue = getShiftValue();
        System.out.printf("You wish to shift it %d time(s)\n", shiftValue);

        // Calls caesarEncrypt with text input and shift value
        String cipherResult = caesarEncrypt(plainText, shiftValue);

        // Prints the result
        System.out.println("The result of the encryption is: " + cipherResult);
    }

    public static String caesarEncrypt(String plainText, int shiftValue) {
        // textToListOfNumbers
        int[] list = textToListOfNumbers(plainText);

        // shiftListOfNumbers
        shiftListOfNumbers(list, shiftValue);
        list = shiftListOfNumbers(list, shiftValue);

        // listOfNumbersToText
        return listOfNumbersToText(list);
    }


    // Methods for encoding / decoding

    public static String listOfNumbersToText( int[] numbers ) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];
            char letter = numberToLetter(number);
            text.append(letter);
        }
        return text.toString();
    }

    public static char numberToLetter( int number ) {
        String alphabet = " ABCDEFGHIJKLMNOPQRSTUVWXYZÆØÅ";
        return alphabet.charAt(number);
    }

    public static int[] textToListOfNumbers( String text ) {
        int[] list = new int [text.length()];

        for (int i = 0; i < text.length(); i++) {
            char letter = text.charAt(i);
            int number = letterToNumber(letter);
            list[i] = number;
        }
        return list;
    }

    public static int letterToNumber( char letter ) {
        String alphabet = " ABCDEFGHIJKLMNOPQRSTUVWXYZÆØÅ";
        return alphabet.indexOf(letter);
    }

    public static int[] shiftListOfNumbers(int[] numbers, int shiftValue) {
        // kald shiftNumber for hvert tal i numbers
        int[] list = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            int numb = numbers[i];
            int number = shiftNumber(numb, shiftValue);
            list[i] = number;
        }
        return list;
        // - men ikke mellemrum!
    }

    public static int shiftNumber(int number, int shiftValue ) {
        String alphabet = " ABCDEFGHIJKLMNOPQRSTUVWXYZÆØÅ";
        int shiftedNumber = number + shiftValue;
        int restOfShiftedNumber = alphabet.length() - number;
        if (shiftedNumber > alphabet.length() -1 && number < shiftValue) {
            shiftedNumber = alphabet.length() - restOfShiftedNumber - 1;
            if (shiftedNumber == 0) {
                shiftedNumber++;
            }
        }
        else if (shiftedNumber > alphabet.length() -1 && number > shiftValue) {
            shiftedNumber = restOfShiftedNumber;
        }
        return shiftedNumber;
    }

    public static String userTextInput() {
        System.out.println("Please enter plain text to be encrypted");
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine().toUpperCase();
        return text;
    }

    private static int getShiftValue() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}