package LLD.questions.ParkingLot;

import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket {
    private String ticketNo;
    private Slot slot;
    private LocalDateTime parkedTime;

    public Ticket(Slot slot) {
        this.slot = slot;
        this.ticketNo = UUID.randomUUID().toString();
        this.parkedTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketNo='" + ticketNo + '\'' +
                ", slot=" + slot +
                ", parkedTime=" + parkedTime +
                '}';
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public LocalDateTime getParkedTime() {
        return parkedTime;
    }

    public void setParkedTime(LocalDateTime parkedTime) {
        this.parkedTime = parkedTime;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }
}
