import java.util.Scanner;

public class CapsuleHotel {

    private static final int LINE_LENGTH = 35;

    public static void main(String[] args){
        boolean isHotelActive = true;
        Scanner sc = new Scanner(System.in);

        String[] mainMenuOptions = {"Check In","Check Out","View Guests","Exit"};

        while(isHotelActive){
            System.out.println("Hello Welcome to the Capsule Hotel!");
            printMenuHeader(LINE_LENGTH,"Main");
            printItems(LINE_LENGTH, mainMenuOptions);
            isHotelActive = false;
        }
    }

    private static void printDivider(int lineLength, char divider){
        for(int i = 0; i < lineLength; i++){
            System.out.print(divider);
        }
        System.out.println();
    }

    private static void printMenuHeader(int lineLength, String headerText){
        printDivider(lineLength,'=');
        System.out.printf("|%-"+(lineLength - 2)+"s|\n", headerText);
        printDivider(lineLength,'=');
    }

    private static void printItems(int lineLength, String[] items){
        for(int i = 0; i < items.length; i++){
            System.out.printf("|%-"+(lineLength - 2)+"s|\n", items[i]);
            printDivider(lineLength,'-');
        }
    }

    private static void mainMenu(){

    }

    private static void checkIn(){

    }

    private static void viewGuests(){

    }

    private static void exit(){
        System.out.println("Thank you for visiting the Capsule Hotel");
    }
}

