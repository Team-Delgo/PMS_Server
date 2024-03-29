package com.pms.domain.booking;

import com.pms.domain.Place;
import com.pms.domain.Room;
import com.pms.dto.booking.BookingResDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    private String bookingId;
    private Integer userId;
    private String reservedName;
    private String reservedPhoneNo;
    private Integer roomId;
    private Integer placeId;
    private Integer couponId;
    private Integer point;
    private LocalDate startDt;
    private LocalDate endDt;
    private Integer finalPrice;
    @CreationTimestamp
    private LocalDate registDt;
    @Enumerated(EnumType.STRING)
    private BookingState bookingState;
    private String orderId; // toss OrderId
    private String paymentKey; // toss 취소할 때 사용.

    public Booking setBookingState(BookingState state) {
        this.bookingState = state;

        return this;
    }

    public BookingResDTO toResDTO(Place place, Room room){
        return BookingResDTO.builder()
                .bookingId(bookingId)
                .reservedName(reservedName)
                .userPhoneNo(reservedPhoneNo)
                .placeName(place.getName())
                .roomName(room.getName())
                .startDt(startDt)
                .endDt(endDt)
                .bookingState(bookingState)
                .registDt(registDt)
                .build();
    }
}
