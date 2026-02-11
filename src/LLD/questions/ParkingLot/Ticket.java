package LLD.questions.ParkingLot;

import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket {
    private final String ticketNo;
    private final Slot slot;
    private final LocalDateTime parkedTime;

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

    public LocalDateTime getParkedTime() {
        return parkedTime;
    }

    public String getTicketNo() {
        return ticketNo;
    }
}
