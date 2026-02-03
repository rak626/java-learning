package LLD.questions.ParkingLot;

public class ParkingLotRunner {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(3, 2, 3, 5);
        System.out.println(parkingLot.checkAvailability());
        Ticket ticket = parkingLot.parkVehicle(new Car());
        System.out.println(ticket);
        System.out.println(parkingLot.checkAvailability());
        Ticket ticket1 = parkingLot.parkVehicle(new Bike());
        System.out.println(ticket1);
        System.out.println(parkingLot.checkAvailability());

        for (int i = 0; i < 5; i++) {
            parkingLot.parkVehicle(new Bike());
        }
        System.out.println(parkingLot.checkAvailability());

        System.out.println(parkingLot.unparkVehicle(ticket1.getTicketNo()));
        System.out.println(parkingLot.checkAvailability());

        parkingLot.print();
    }
}
