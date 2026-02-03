package LLD.questions.ParkingLot;

public class Slot {
    private VehicleType allowedType;
    private int slotNo;
    private int floorNo;
    private boolean isOccupied;

    public Slot(int floorNo, int slotNo, VehicleType vehicleType) {
        this.floorNo = floorNo;
        this.allowedType = vehicleType;

    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    @Override
    public String toString() {
        return "Slot{" +
                "vehicle=" + allowedType +
                ", slotNo=" + slotNo +
                ", floorNo=" + floorNo +
                ", isOccupied=" + isOccupied +
                '}';
    }

    public VehicleType getAllowedType() {
        return allowedType;
    }

    public void setAllowedType(VehicleType allowedType) {
        this.allowedType = allowedType;
    }

    public int getSlotNo() {
        return slotNo;
    }

    public void setSlotNo(int slotNo) {
        this.slotNo = slotNo;
    }

    public int getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(int floorNo) {
        this.floorNo = floorNo;
    }
}
