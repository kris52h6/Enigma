package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // UI metoder ...
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
        // beder brugeren om plaintext
        String plainText = userTextInput();
        System.out.println("You wish to encrypt: " + plainText);

        // kalder numberEncrypt med plaintext
        numberEncrypt(plainText);

        // udskriver ciphertext modtaget fra ovenstående
        int[] cipherResult = numberEncrypt(plainText);
        System.out.print("The result of the encryption is: ");
        System.out.println(Arrays.toString(cipherResult));


    }

    private static int[] numberEncrypt(String plainText) {
        int[] list = textToListOfNumbers(plainText);
        return list;
    }

    public static void decryptNumberMenu() {
        // beder brugeren om ciphertext
        String cipherText = userTextInput();
        System.out.println("You wish to decode: " + cipherText);

        // kalder listOfNumbersToText med ciphertext
        numberDecrypt(cipherText);

        // udskriver decrypted text
        String plainTextResult = numberDecrypt(cipherText);
        System.out.println(plainTextResult);
    }

    private static String numberDecrypt(String cipherText) {
        int[] numbers = Arrays.stream(cipherText.split("," + " ")).mapToInt(Integer::parseInt).toArray();

        String listDecoded = listOfNumbersToText(numbers);
        return listDecoded;
    }


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



    // Caesar
    public static void decryptCaesarMenu() {
        // beder brugeren om ciphertext
        String cipherText = userTextInput();
        System.out.println("You wish to decode: " + cipherText);

        // beder brugeren om shift-værdi
        System.out.println("Please enter a shift value for the decoding");
        int shiftValue = getShiftValue();
        shiftValue = - shiftValue;

        // kalder caesarDecrypt med ciphertext og shift-værdi
        String decodeResult = caesarDecrypt(cipherText, shiftValue);

        // udskriver plaintext modtaget fra ovenstående
        System.out.println("The result of the encryption is: " + decodeResult);

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
        // beder brugeren om plaintext
        String plainText = userTextInput();
        System.out.println("You wish to encrypt: " + plainText);

        // beder brugeren om shift-værdi
        System.out.println("Please a shift value for the encryption");
        int shiftValue = getShiftValue();
        System.out.printf("You wish to shift it %d time(s)\n", shiftValue);

        // kalder caesarEncrypt med ciphertext og shift-værdi
        String cipherResult = caesarEncrypt(plainText, shiftValue);

        // udskriver ciphertext modtaget fra ovenstående
        System.out.println("The result of the encryption is: " + cipherResult);
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

    public static String caesarEncrypt(String plainText, int shiftValue) {
        // textToListOfNumbers
        int[] list = textToListOfNumbers(plainText);

        // shiftListOfNumbers
        shiftListOfNumbers(list, shiftValue);
        list = shiftListOfNumbers(list, shiftValue);

        // listOfNumbersToText
        return listOfNumbersToText(list);
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