import java.util.Scanner;

public class CapsuleHotel {

    private static final int LINE_LENGTH = 35;
    private static String[] capsuleHotel;
    private static Scanner sc;

    public static void main(String[] args){
        sc = new Scanner(System.in);
        boolean isHotelActive = true;
        int numberOfRooms = args.length > 0 ? Integer.valueOf(args[0]) : 100;
        capsuleHotel = new String[numberOfRooms]; 
 
        System.out.println("Hello Welcome to the Capsule Hotel!");
        while(isHotelActive){
            isHotelActive = mainMenu();
        }
        System.out.println("Thank you for visiting!");
    }

    //Menu methods
    private static boolean mainMenu(){
        String[] mainMenuOptions = {"Check In","Check Out","View Guests","Exit"};
        
        printMenuHeader("Main Menu");
        printItems(mainMenuOptions);
        userPrompt();

        int option = Integer.valueOf(sc.nextLine()); //Need validation to protect against bad input

        switch(option){
            case 1:
                checkIn();
                break;
            case 2:
                checkOut();
                break;
            case 3:
                viewGuests();
                break;
            case 4:
                boolean isHotelActive = exit();
                return isHotelActive;
            default:
                System.out.println("Invalid input: use numbers 1-4.");
        }

        return true; //Continues program loop
    }

    private static void checkIn(){
        String guestName;
        int capsuleNumber = -1;

        //Getting the guests name
        printMenuHeader("Check In");
        System.out.println("Please Enter Name");
        userPrompt();
        guestName = sc.nextLine();

        //Assigning to a capsule
        System.out.println("Enter the capsule #(1-"+capsuleHotel.length+")");
        userPrompt();
        do{
            capsuleNumber = Integer.valueOf(sc.nextLine());
        }while(!isVaccant(capsuleNumber-1));
        
        //assign the value
        capsuleHotel[capsuleNumber-1] = guestName;
    }

    private static void checkOut(){
        int capsuleNumber;
        String guestName;
        
        if(allVaccancies()){
            System.out.println("There are no guests in the hotel.");
            return;
        }

        //Get capsule #
        printMenuHeader("Check Out");
        System.out.println("Enter the capsule #(1-"+capsuleHotel.length+")");        
        
        do{
            capsuleNumber = Integer.valueOf(sc.nextLine());
        }while(capsuleNumber < 0 || capsuleNumber > capsuleHotel.length || isVaccant(capsuleNumber - 1));

        System.out.printf("%s has left the building.\n", capsuleHotel[capsuleNumber - 1]);
        capsuleHotel[capsuleNumber - 1] = null;
    }

    private static void viewGuests(){
        int capsuleNumber;      //1 indexed
        int[] bounds;

        System.out.println("What capsule would you like to view?");
        userPrompt();

        do{
            capsuleNumber = Integer.valueOf(sc.nextLine());
        }while(capsuleNumber < 1 || capsuleNumber > capsuleHotel.length );
        
        //Has a lower and upper bound at [0] and [1] respectively
        bounds = calculateBounds(capsuleNumber - 1, 10);

        for(int i = bounds[0]; i <= bounds[1]; i++){
            System.out.printf("%d)  %s\n",(i+1),(capsuleHotel[i] != null ? capsuleHotel[i] : "[empty]"));
        }

        printDivider('*');
        System.out.println("Press 'Enter' to return to main menu...");
        userPrompt();
        sc.nextLine();
    }

    private static boolean exit(){
        System.out.println("Are you sure you want to exit? [y/n]");
        String answer = "";
        while(!isYesNo(answer)){        //continues loop until yes or no answer is given
            userPrompt();
            answer = sc.nextLine().trim();
        }
        return answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("no");
    }

    //Other Methods
    private static void printMenuHeader(String headerText){
        printDivider('=');
        System.out.printf("|%-"+(LINE_LENGTH - 2)+"s|\n", headerText);
        printDivider('=');
    }

    private static void printItems(String[] items){
        for(int i = 0; i < items.length; i++){
            System.out.printf("|%d) %-"+(LINE_LENGTH - 5)+"s|\n", (i + 1), items[i]);
            printDivider('-');
        }
    }

    private static void printDivider(char divider){
        for(int i = 0; i < LINE_LENGTH; i++){
            System.out.print(divider);
        }
        System.out.println();
    }

    private static void userPrompt(){
        System.out.print("?: ");
    }

    private static int[] calculateBounds(int index, int maxResults){
        int[] bounds = new int[2];
        int bottomPadding = maxResults % 2 == 0 ? maxResults/2 : (maxResults + 1)/2;
        int topPadding = maxResults % 2 == 0 ? maxResults/2 : (maxResults -1)/2;

        //If bottom padding goes out of bounds move extra padding to bottom
        if(index - bottomPadding < 0){
            topPadding += -(index - bottomPadding);
        }
        
        //If top padding goes out of bounds move extra padding to bottom
        if(index + topPadding > capsuleHotel.length - 1){
            bottomPadding += ((index + topPadding) - capsuleHotel.length);
        }

        //Basic Implementation
        bounds[0] = index - bottomPadding > 0 ? index - bottomPadding : 0;
        bounds[1] = index + topPadding  < capsuleHotel.length ? index + topPadding : capsuleHotel.length - 1; 

        return bounds;
    }

    private static boolean isYesNo(String answer){
        return answer != null && (
            answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n") ||
            answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("no")
        );
    }

    private static boolean isValidRoom(int capsuleIndex){
        return 0 <= capsuleIndex && 
            capsuleIndex < capsuleHotel.length;
    }

    private static boolean isVaccant(int capsuleIndex){
        return  isValidRoom(capsuleIndex) && capsuleHotel[capsuleIndex] == null;
    }

    private static boolean isOccupied(int capsuleIndex){
        return isValidRoom(capsuleIndex) && capsuleHotel[capsuleIndex] != null;
    }

    private static boolean allVaccancies(){
        boolean areAllVaccant = true;
        for(int roomIdx=0; roomIdx < capsuleHotel.length; roomIdx++){
            if(!isVaccant(roomIdx)){
                return !areAllVaccant;
            }
        }

        return areAllVaccant;
    }

    private static boolean allOccupied(){
        boolean areAllOccupied = true;
        for(int roomIdx=0; roomIdx < capsuleHotel.length; roomIdx++){
            if(!isOccupied(roomIdx)){
                return !areAllOccupied;
            }
        }

        return areAllOccupied;
    }
}

