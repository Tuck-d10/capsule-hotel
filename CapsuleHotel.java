import java.util.Scanner;

public class CapsuleHotel {

    private static final int LINE_LENGTH = 35;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        boolean isHotelActive = true;

        System.out.println("Hello Welcome to the Capsule Hotel!");
        while(isHotelActive){
            isHotelActive = mainMenu(sc);
        }
        System.out.println("Thank you for visiting!");
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
            System.out.printf("|%d) %-"+(lineLength - 5)+"s|\n", (i + 1), items[i]);
            printDivider(lineLength,'-');
        }
    }

    private static boolean mainMenu(Scanner sc){
        String[] mainMenuOptions = {"Check In","Check Out","View Guests","Exit"};
        
        printMenuHeader(LINE_LENGTH,"Main Menu");
        printItems(LINE_LENGTH, mainMenuOptions);
        userPrompt();

        int option = sc.nextInt(); //Need validation to protect against bad input

        switch(option){
            case 1:
                checkIn(sc);
                break;
            case 2:
                checkOut(sc);
                break;
            case 3:
                viewGuests(sc);
                break;
            case 4:
                boolean isHotelActive = exit(sc);
                return isHotelActive;
            default:
                System.out.println("Invalid input: use numbers 1-4.");
        }

        return true;
    }

    private static void checkIn(Scanner sc){
        System.out.println("check in");
    }

    
    private static void checkOut(Scanner sc){
        System.out.println("check out");
    }

    private static void viewGuests(Scanner sc){
        System.out.println("view guests");
    }

    private static boolean exit(Scanner sc){
        System.out.println("Are you sure you want to exit? [y/n]");
        String answer = "";
        while(!isYesNo(answer)){        //continues loop until yes or no answer is given
            userPrompt();
            answer = sc.nextLine().trim();
        }
        return answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("no");
    }

    private static void userPrompt(){
        System.out.print("?: ");
    }

    private static boolean isYesNo(String answer){
        return answer != null && (
            answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n") ||
            answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("no")
        );
    }
}

