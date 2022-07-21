package com.pms.service;

import com.pms.comm.CommService;
import com.pms.comm.ncp.service.SmsService;
import com.pms.domain.Place;
import com.pms.domain.Room;
import com.pms.domain.booking.Booking;
import com.pms.domain.booking.BookingState;
import com.pms.domain.user.User;
import com.pms.dto.booking.ReturnBookingDTO;
import com.pms.repository.BookingRepository;
import com.pms.repository.PlaceRepository;
import com.pms.repository.RoomRepository;
import com.pms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingService extends CommService {

    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;

    public Booking insertOrUpdateBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking getBookingByBookingId(String bookingId) {
        return bookingRepository.findByBookingId(bookingId)
                .orElseThrow(() -> new NullPointerException("NOT EXIST BOOKING DATA"));
    }

    public List<List<ReturnBookingDTO>> getReturnBookingData(LocalDate startDt, LocalDate endDt) {
        List<List<ReturnBookingDTO>> finalList = new ArrayList<>();
        Period period = Period.between(startDt, endDt);

        List<Booking> bookingList = bookingRepository.findByRegistDtBetween(startDt, endDt);
        List<ReturnBookingDTO> returnDtoList = new ArrayList<>();
        if (bookingList.isEmpty())
            return finalList;

        bookingList.forEach((booking) -> {
            Place place = placeRepository.findByPlaceId(booking.getPlaceId())
                    .orElseThrow(() -> new NullPointerException("NOT FOUND PLACE"));
            Room room = roomRepository.findByRoomId(booking.getRoomId())
                    .orElseThrow(() -> new NullPointerException("NOT FOUND ROOM"));
            User user = userRepository.findByUserId(booking.getUserId())
                    .orElseThrow(() -> new NullPointerException("NOT FOUND USER"));

            returnDtoList.add(ReturnBookingDTO.builder()
                    .bookingId(booking.getBookingId())
                    .reservedName(booking.getReservedName())
                    .userPhoneNo(user.getPhoneNo())
                    .placeName(place.getName())
                    .roomName(room.getName())
                    .startDt(booking.getStartDt())
                    .endDt(booking.getEndDt())
                    .bookingState(booking.getBookingState())
                    .registDt(booking.getRegistDt())
                    .build());
        });

        for (int i = 0; i < period.getDays() + 1; i++) {
            LocalDate date = startDt.plusDays(i);
            List<ReturnBookingDTO> dateList = new ArrayList<>();
            returnDtoList.forEach(dto -> {
                if (dto.getRegistDt().isEqual(date))
                    dateList.add(dto);
            });

            finalList.add(dateList);
        }

        return finalList;
    }

//    //TODO: 카톡 알림으로 변경
//    public void sendBookingMsg(int userId) {
//        User user = userService.getUserByUserId(userId);
//        // TODO: 사용자에게 예약대기문자 발송 [ 내용 어떤 거 들어갈지 생각 필요 ]
////        smsService.sendSMS(user.getPhoneNo(), "예약완료 될 때까지 기다려주세요.");
//
//        // TODO: 운영진에게 예약요청문자 발송 [ 내용 어떤 거 들어갈지 생각 필요 ]
////        smsService.sendSMS(adminPhoneNo, "예약요청이 들어왔습니다.");
//    }
}
