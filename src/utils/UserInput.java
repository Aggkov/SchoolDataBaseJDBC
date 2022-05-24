package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public final class UserInput {


    public static String readString(String question) {
        Scanner sc = new Scanner(System.in);
        System.out.print(question + ": ");

        String input = " ";
        boolean flag = false;
        while(!flag) {
            input = sc.nextLine();
            if (Pattern.matches("[A-Za-z]+", input)) {
                flag = true;
            }
            else {
                System.out.print("Only alphabetic characters and no spaces please: ");
            }
        }
        return input;
    }

    public static int readInt(String question) {
        int num = 0;
        Scanner sc = new Scanner(System.in);
        System.out.print(question + ": ");

        boolean flag = false;
        while(!flag) {
            if(!sc.hasNextInt()) {
                System.out.print("Only integers allowed: ");
                sc.next();
            }
            else {
                num = sc.nextInt();
                flag = true;
            }
        }
        return num;
    }

    public static int readInt(String question, int lower, int upper) {
        Scanner sc = new Scanner(System.in);
        System.out.print(question + "from " + lower + "to " + upper + ": ");

        int choice;
        boolean flag = false;
        while(!false) {
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                if (choice < lower || choice > upper) {
                    System.out.println("Error: Between " + lower + " and " + upper);
                }
                else
                    return choice;
            }
            else {
                sc.next();
                System.out.println("Error: Invalid input");
            }
        }
    }

    public static int readInt(String question, int[] availableChoices){

        Scanner sc = new Scanner(System.in);
        System.out.print(question + " : ");

        boolean flag = false;
        while (!flag) {
            if (!sc.hasNextInt()) {
                System.out.print("Only integers allowed: ");
                sc.next();
            } else {
                int num = sc.nextInt();
                for(int i : availableChoices ){
                    if (num == i) {
                        return num;
                    }
                }
                System.out.print("please try again (choose from available choices) : ");
            }
        }
        return -1;
    }

    public static int[] convertStringOptionsToInt(ArrayList<String> options) {

        int[] optionsToInt = new int[options.size()];
        for(int i = 0; i < optionsToInt.length; i++) {
            optionsToInt[i] = i + 1;
        }
        return optionsToInt;
    }

    public static String getOptionsNumbersAndNames(ArrayList<String> options) {
        String tmp = " ";

        for(int i = 0; i < options.size(); i++) {
            tmp += " [" + (i + 1) + "] " + options.get(i);
        }
        return tmp;
    }

    public static LocalDate readDate(String question) {
        Scanner sc = new Scanner(System.in);

        System.out.print(question + ": ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate formattedDate;
        while (true) {
            if (!sc.hasNextLine()) {
                System.out.println("Please insert date in valid format! (yyyy-MM-dd)");
            } else {
                try {
                    String Date = sc.nextLine();
                    formattedDate = LocalDate.parse(Date, formatter);
                    break;

                } catch (DateTimeParseException e) {
                    System.out.println("Please insert date in valid format! (yyyy-MM-dd)");
                }
            }
        }
        return formattedDate;
    }



}
