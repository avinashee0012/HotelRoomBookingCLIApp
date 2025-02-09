import java.util.*;

public class HotelRoomBookingCLIApp {

    // class fields
    HashMap<String, String> roomBookingEntries;
    String[][] hotelRooms;
    HashSet<String> hotelRoomsSet;
    Integer totalCoins;
    Integer activeBookings;

    // class constructor
    HotelRoomBookingCLIApp() {
        this.roomBookingEntries = new HashMap<>();
        this.hotelRooms = new String[10][26];
        this.hotelRoomsSet = new HashSet<>();
        this.totalCoins = 0;
        this.activeBookings = 0;
        this.buildRooms();
    }

    // helping method for constructor
    void buildRooms(){
        for(int i = 0; i < this.hotelRooms.length; i++){
            for(int j = 0; j < this.hotelRooms[i].length; j++){
                this.hotelRooms[i][j] = i + Character.toString(j+65);
                this.hotelRoomsSet.add(i + Character.toString(j+65));
            }
        }

    }

    // 1. Book a Room
    void bookRoom(String roomNo, String customerName){
        if(!this.hotelRoomsSet.contains(roomNo)) {
            System.out.println("Invalid Room Number");
        } else if(!this.roomBookingEntries.containsKey(roomNo)) {
            this.roomBookingEntries.put(roomNo, customerName);
            this.totalCoins++;
            this.activeBookings++;
            System.out.println("Booking Successfull: " + roomNo + " --> " + customerName);
        } else {
            System.out.println("This room is already booked. Try another room.");
        }
        
    }

    // 2. Checkout a Room
    void checkoutRoom(String roomID) {
        if(this.roomBookingEntries.containsKey(roomID)) {
            this.roomBookingEntries.remove(roomID);
            this.activeBookings--;
            System.out.println("Checkout Successfull: " + roomID);
        } else if (!this.hotelRoomsSet.contains(roomID)) {
            System.out.println("Invalid Room Number");
        } else {
            System.out.println("This room is vacant. Cross-check the roomID.");
        }
    }

    // 3. Print all Rooms
    void printRooms(){
        for(String[] sa : this.hotelRooms){
            for(String s : sa){
                if(this.roomBookingEntries.containsKey(s)) {
                    System.out.println(s + " (Booked) ");
                } else {
                    System.out.println(s + " (Vacant) ");
                }
            }
        }
    }

    // 4. Show Total Coins
    int getTotalCoins(){
        return this.totalCoins;
    }

    // 5. Show Active Bookings
    int getActiveBookings(){
        return this.activeBookings;
    }

    // 6. Show Booked Rooms with Customers
    void printBookedRooms(){
        for(String s : this.roomBookingEntries.keySet()){
            System.out.println(s + " -> " + this.roomBookingEntries.get(s));
        }
    }

    // 7. Check Room Availability
    void checkRoomAvailability(String roomID) {
        if(hotelRoomsSet.contains(roomID)) {
            if(this.roomBookingEntries.containsKey(roomID)) {
                System.out.println("Room is already booked");
            } else {
                System.out.println("Room is available.");
            }
        } else {
            System.out.println("Invalid Room Number");
        }
    }

    // 8. Get Customer Name by Room ID
    String getCustomerName(String roomID) {
        if(this.roomBookingEntries.containsKey(roomID)){
            return this.roomBookingEntries.get(roomID);
        }
        return "Room is vacant.";
    }

    // 9. Exit


    // show main menu
    void showMainMenu(){
        System.out.println("**************** Hotel Management System: ****************");
        System.out.println("1. Book a Room");
        System.out.println("2. Checkout a Room");
        System.out.println("3. Print all Rooms");
        System.out.println("4. Show Total Coins");
        System.out.println("5. Show Active Bookings");
        System.out.println("6. Show Booked Rooms with Customers");
        System.out.println("7. Check Room Availability");
        System.out.println("8. Get Customer Name by Room ID");
        System.out.println("9. Exit");
    }


    // MAIN METHOD
    public static void main(String args[]) {

        HotelRoomBookingCLIApp hotel = new HotelRoomBookingCLIApp();

        Scanner sc = new Scanner(System.in);
        int mainMenuInput = 0;
        
        while (mainMenuInput != 9) {
            hotel.showMainMenu();
            System.out.print("Choose an option: ");
            mainMenuInput = sc.nextInt();

            switch(mainMenuInput) {
                case 1 : 
                    System.out.println("****** Room Booking ******");
                    System.out.print("Enter roomNo: ");
                    String roomNo = sc.next();
                    System.out.print("Enter customerName: ");
                    String customerName = sc.next();
                    hotel.bookRoom(roomNo, customerName);
                    break;
                case 2 :
                    System.out.println("****** Room Checkout ******");
                    System.out.print("Enter roomNo: ");
                    roomNo = sc.next();
                    hotel.checkoutRoom(roomNo);
                    break; 
                case 3 :
                    System.out.println("****** Room Board ******");
                    hotel.printRooms();
                    break; 
                case 4 :
                    System.out.println(hotel.getTotalCoins());
                    break; 
                case 5 :
                    System.out.println(hotel.getActiveBookings());
                    break; 
                case 6 :
                    System.out.println("****** Booked Rooms ******");
                    hotel.printBookedRooms();
                    break; 
                case 7 :
                    System.out.println("****** Room Availability ******");
                    System.out.print("Enter roomNo: ");
                    roomNo = sc.next();
                    hotel.checkRoomAvailability(roomNo);
                    break; 
                case 8 :
                    System.out.println("****** Current Room Owner ******");
                    System.out.print("Enter roomNo: ");
                    roomNo = sc.next();
                    hotel.getCustomerName(roomNo);
                    break; 
                case 9 :
                    System.out.println("Exiting. Thank you!");
                    break; 
                default :
                    System.out.println("Invalid Input. ");
            }
        }

        sc.close();
    }
}


