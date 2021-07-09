import java.util.Scanner;

public class CapsuleHotel {
    public static void main(String[] args){
        boolean isHotelActive = true;
        Scanner sc = new Scanner(System.in);


        while(isHotelActive){
            System.out.println("Type something");

            String foo = sc.nextLine();

            System.out.printf("\"%s\" is what you typed", foo);
            isHotelActive = false;
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

