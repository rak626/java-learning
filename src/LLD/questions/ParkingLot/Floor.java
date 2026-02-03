package LLD.questions.ParkingLot;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    private final int totalCarSlot;
    private final int totalBikeSlot;
    private final int totalTruckSlot;
    private final int floorNo;
    private List<Slot> slots;
    private int freeCarSlot;
    private int freeBikeSlot;
    private int freeTruckSlot;

    public Floor(int carCnt, int bikeCnt, int truckCnt, int floorNo) {
        this.totalCarSlot = carCnt;
        this.totalBikeSlot = bikeCnt;
        this.totalTruckSlot = truckCnt;
        this.freeCarSlot = carCnt;
        this.freeBikeSlot = bikeCnt;
        this.freeTruckSlot = truckCnt;
        this.floorNo = floorNo;

        slots = new ArrayList<>();
        int slotNo = 0;
        for (int i = 0; i < bikeCnt; i++) {
            slots.add(new Slot(floorNo, slotNo++, VehicleType.BIKE));
        }
        for (int i = 0; i < carCnt; i++) {
            slots.add(new Slot(floorNo, slotNo++, VehicleType.CAR));
        }
        for (int i = 0; i < truckCnt; i++) {
            slots.add(new Slot(floorNo, slotNo++, VehicleType.TRUCK));
        }
    }

    public Slot availableSlotNo(Vehicle vehicle) {
        return slots.stream()
                .filter(slot -> slot.getAllowedType() == vehicle.getVehicleType())
                .filter(slot -> !slot.isOccupied())
                .findFirst().orElse(null);
    }

    public void freeSlot(int slotNo) {
        slots.get(slotNo).setOccupied(false);
    }

    public int getTotalCarSlot() {
        return totalCarSlot;
    }

    public int getTotalBikeSlot() {
        return totalBikeSlot;
    }

    public int getTotalTruckSlot() {
        return totalTruckSlot;
    }

    public int getFreeCarSlot() {
        return freeCarSlot;
    }

    public void setFreeCarSlot(int freeCarSlot) {
        this.freeCarSlot = freeCarSlot;
    }

    public int getFreeBikeSlot() {
        return freeBikeSlot;
    }

    public void setFreeBikeSlot(int freeBikeSlot) {
        this.freeBikeSlot = freeBikeSlot;
    }

    public int getFreeTruckSlot() {
        return freeTruckSlot;
    }

    public void setFreeTruckSlot(int freeTruckSlot) {
        this.freeTruckSlot = freeTruckSlot;
    }

    public int getFloorNo() {
        return floorNo;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }

    public void updateFreeSlot(VehicleType vehicleType, boolean isParked) {
        if (isParked) {
            switch (vehicleType) {
                case CAR -> this.freeCarSlot--;
                case BIKE -> this.freeBikeSlot--;
                case TRUCK -> this.freeTruckSlot--;
            }
        } else {
            switch (vehicleType) {
                case CAR -> this.freeCarSlot++;
                case BIKE -> this.freeBikeSlot++;
                case TRUCK -> this.freeTruckSlot++;
            }
        }
    }

    public void print() {
        printEachType(0, totalBikeSlot);
        printEachType(totalBikeSlot, totalBikeSlot + totalCarSlot);
        printEachType(totalBikeSlot + totalCarSlot, totalBikeSlot + totalCarSlot + totalTruckSlot);
    }

    private void printEachType(int begin, int end) {
        for (int i = begin; i < end; i++) {
            Slot slot = slots.get(i);
            if (slot.isOccupied()) {
                System.out.print(" O ");
            } else {
                System.out.print(" V ");
            }
        }
        System.out.println();
    }
}
