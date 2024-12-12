package com.g5.restaurants.infrastructure.mappers;

import com.g5.reservation.model.CreateReservationDTO;
import com.g5.reservation.model.ReservationDTO;
import com.g5.reservation.model.UpdateReservationDTO;
import com.g5.restaurants.aplication.domain.base.BaseId;
import com.g5.restaurants.aplication.domain.reservation.Reservation;
import com.g5.restaurants.aplication.usecases.reservation.create.ReservationCreateUseCaseInput;
import com.g5.restaurants.aplication.usecases.reservation.create.ReservationCreateUseCaseOutput;
import com.g5.restaurants.aplication.usecases.reservation.retrive.get.ReservationGetByIdUseCaseOutput;
import com.g5.restaurants.aplication.usecases.reservation.retrive.list.ReservationListUseCaseOutput;
import com.g5.restaurants.aplication.usecases.reservation.update.ReservationUpdateUseCaseInput;
import com.g5.restaurants.aplication.usecases.reservation.update.ReservationUpdateUseCaseOutput;
import com.g5.restaurants.infrastructure.persistence.entities.ReservationEntity;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-12T04:50:24-0300",
    comments = "version: 1.6.0, compiler: javac, environment: Java 17.0.13 (Ubuntu)"
)
@Component
public class ReservationMapperImpl implements ReservationMapper {

    @Override
    public ReservationDTO toDTO(ReservationCreateUseCaseOutput output) {
        if ( output == null ) {
            return null;
        }

        ReservationDTO reservationDTO = new ReservationDTO();

        reservationDTO.setCustomerName( output.customerName() );
        reservationDTO.setCustomerContact( output.customerContact() );
        reservationDTO.setReservationDate( output.reservationDate() );
        reservationDTO.setNumberOfTables( output.numberOfTables() );
        reservationDTO.setStatus( output.status() );

        reservationDTO.setId( java.util.UUID.fromString(output.id().toString()) );
        reservationDTO.setRestaurantId( java.util.UUID.fromString(output.restaurantId().toString()) );

        return reservationDTO;
    }

    @Override
    public ReservationDTO toDTO(ReservationListUseCaseOutput output) {
        if ( output == null ) {
            return null;
        }

        ReservationDTO reservationDTO = new ReservationDTO();

        reservationDTO.setCustomerName( output.customerName() );
        reservationDTO.setCustomerContact( output.customerContact() );
        reservationDTO.setReservationDate( output.reservationDate() );
        reservationDTO.setNumberOfTables( output.numberOfTables() );
        reservationDTO.setStatus( output.status() );

        reservationDTO.setId( java.util.UUID.fromString(output.id().toString()) );
        reservationDTO.setRestaurantId( java.util.UUID.fromString(output.restaurantId().toString()) );

        return reservationDTO;
    }

    @Override
    public ReservationDTO toDTO(ReservationGetByIdUseCaseOutput output) {
        if ( output == null ) {
            return null;
        }

        ReservationDTO reservationDTO = new ReservationDTO();

        reservationDTO.setCustomerName( output.customerName() );
        reservationDTO.setCustomerContact( output.customerContact() );
        reservationDTO.setReservationDate( output.reservationDate() );
        reservationDTO.setNumberOfTables( output.numberOfTables() );
        reservationDTO.setStatus( output.status() );

        reservationDTO.setId( java.util.UUID.fromString(output.id().toString()) );
        reservationDTO.setRestaurantId( java.util.UUID.fromString(output.restaurantId().toString()) );

        return reservationDTO;
    }

    @Override
    public ReservationDTO toDTO(ReservationUpdateUseCaseOutput output) {
        if ( output == null ) {
            return null;
        }

        ReservationDTO reservationDTO = new ReservationDTO();

        reservationDTO.setCustomerName( output.customerName() );
        reservationDTO.setCustomerContact( output.customerContact() );
        reservationDTO.setReservationDate( output.reservationDate() );
        reservationDTO.setNumberOfTables( output.numberOfTables() );
        reservationDTO.setStatus( output.status() );

        reservationDTO.setId( java.util.UUID.fromString(output.id().toString()) );
        reservationDTO.setRestaurantId( java.util.UUID.fromString(output.restaurantId().toString()) );

        return reservationDTO;
    }

    @Override
    public ReservationCreateUseCaseInput fromDTO(CreateReservationDTO dto) {
        if ( dto == null ) {
            return null;
        }

        String restaurantId = null;
        String customerName = null;
        String customerContact = null;
        LocalDate reservationDate = null;
        Integer numberOfTables = null;

        if ( dto.getRestaurantId() != null ) {
            restaurantId = dto.getRestaurantId().toString();
        }
        customerName = dto.getCustomerName();
        customerContact = dto.getCustomerContact();
        reservationDate = dto.getReservationDate();
        numberOfTables = dto.getNumberOfTables();

        ReservationCreateUseCaseInput reservationCreateUseCaseInput = new ReservationCreateUseCaseInput( restaurantId, customerName, customerContact, reservationDate, numberOfTables );

        return reservationCreateUseCaseInput;
    }

    @Override
    public ReservationUpdateUseCaseInput fromDTO(String id, UpdateReservationDTO dto) {
        if ( id == null && dto == null ) {
            return null;
        }

        ReservationDTO.StatusEnum status = null;
        if ( dto != null ) {
            status = statusEnumToStatusEnum( dto.getStatus() );
        }
        String id1 = null;
        id1 = id;

        ReservationUpdateUseCaseInput reservationUpdateUseCaseInput = new ReservationUpdateUseCaseInput( id1, status );

        return reservationUpdateUseCaseInput;
    }

    @Override
    public Reservation toObject(ReservationEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Reservation reservation = new Reservation();

        reservation.setCustomerName( entity.getCustomerName() );
        reservation.setCustomerContact( entity.getCustomerContact() );
        reservation.setReservationDate( entity.getReservationDate() );
        reservation.setNumberOfTables( entity.getNumberOfTables() );
        if ( entity.getStatus() != null ) {
            reservation.setStatus( Enum.valueOf( ReservationDTO.StatusEnum.class, entity.getStatus() ) );
        }

        reservation.setId( new BaseId(entity.getId()) );
        reservation.setRestaurantId( new BaseId(entity.getRestaurantId()) );

        return reservation;
    }

    protected ReservationDTO.StatusEnum statusEnumToStatusEnum(UpdateReservationDTO.StatusEnum statusEnum) {
        if ( statusEnum == null ) {
            return null;
        }

        ReservationDTO.StatusEnum statusEnum1;

        switch ( statusEnum ) {
            case PENDING: statusEnum1 = ReservationDTO.StatusEnum.PENDING;
            break;
            case CONFIRMED: statusEnum1 = ReservationDTO.StatusEnum.CONFIRMED;
            break;
            case CANCELED: statusEnum1 = ReservationDTO.StatusEnum.CANCELED;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + statusEnum );
        }

        return statusEnum1;
    }
}
