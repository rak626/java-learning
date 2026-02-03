package LLD.questions.ParkingLot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private List<Floor> floors;
    private int floorCount;
    private Map<String, Ticket> tickets;

    public ParkingLot(int floorCount, int bikeCnt, int carCnt, int truckCnt) {
        this.floorCount = floorCount;
        floors = new ArrayList<>();
        for (int i = 0; i < floorCount; i++) {
            floors.add(new Floor(carCnt, bikeCnt, truckCnt, i));
        }
        tickets = new HashMap<>();
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        for (var floor : floors) {
            Slot slot = floor.availableSlotNo(vehicle);
            if (slot != null) {
                slot.setOccupied(true);
                floors.get(slot.getFloorNo()).updateFreeSlot(vehicle.getVehicleType(), true);
                Ticket ticket = createTicket(slot);
                tickets.put(ticket.getTicketNo(), ticket);
                return ticket;
            }
        }
        return null;
    }

    public boolean unparkVehicle(String ticketNo) {
        if (!tickets.containsKey(ticketNo)) return false;
        Ticket ticket = tickets.get(ticketNo);
        int floorNo = ticket.getSlot().getFloorNo();
        int slotNo = ticket.getSlot().getSlotNo();
        Floor floor = floors.get(floorNo);
        floor.freeSlot(slotNo);
        floor.updateFreeSlot(ticket.getSlot().getAllowedType(), false);
        tickets.remove(ticket.getTicketNo());
        return true;
    }

    public String checkAvailability() {
        int carCnt = 0, bikeCnt = 0, truckCnt = 0;
        for (var floor : floors) {
            bikeCnt += floor.getFreeBikeSlot();
            carCnt += floor.getFreeCarSlot();
            truckCnt += floor.getFreeTruckSlot();
        }
        return String.format("Free Slots ---> Bike : %s, Car : %s, Truck : %s ", bikeCnt, carCnt, truckCnt);
    }

    private Ticket createTicket(Slot slot) {
        return new Ticket(slot);
    }

    public void print() {
        for (var floor : floors) {
            System.out.println("=============== Floor " + (floor.getFloorNo() + 1) + " ================");
            System.out.println();
            floor.print();
            System.out.println();
        }
    }


}
