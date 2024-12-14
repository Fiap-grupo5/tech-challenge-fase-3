package com.g5.restaurants.infrastructure.mappers;

import com.g5.restaurant.model.CreateRestaurantDTO;
import com.g5.restaurant.model.RestaurantDTO;
import com.g5.restaurant.model.UpdateRestaurantDTO;
import com.g5.restaurants.aplication.domain.base.BaseId;
import com.g5.restaurants.aplication.domain.restaurant.Restaurant;
import com.g5.restaurants.aplication.usecases.restaurant.create.RestaurantCreateUseCaseInput;
import com.g5.restaurants.aplication.usecases.restaurant.create.RestaurantCreateUseCaseOutput;
import com.g5.restaurants.aplication.usecases.restaurant.retrive.get.RestaurantGetByIdUseCaseOutput;
import com.g5.restaurants.aplication.usecases.restaurant.retrive.list.RestaurantListUseCaseOutput;
import com.g5.restaurants.aplication.usecases.restaurant.update.RestaurantUpdateUseCaseInput;
import com.g5.restaurants.aplication.usecases.restaurant.update.RestaurantUpdateUseCaseOutput;
import com.g5.restaurants.infrastructure.persistence.entities.RestaurantEntity;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-14T12:13:29-0300",
    comments = "version: 1.6.0, compiler: Eclipse JDT (IDE) 3.40.0.z20241112-1021, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class RestaurantMapperImpl implements RestaurantMapper {

    @Override
    public RestaurantDTO toDTO(RestaurantCreateUseCaseOutput output) {
        if ( output == null ) {
            return null;
        }

        RestaurantDTO restaurantDTO = new RestaurantDTO();

        restaurantDTO.setName( output.name() );
        restaurantDTO.setNumberOfTables( output.numberOfTables() );
        restaurantDTO.setAddress( output.address() );
        restaurantDTO.setCity( output.city() );
        restaurantDTO.setState( output.state() );
        restaurantDTO.setType( output.type() );
        if ( output.openedAt() != null ) {
            restaurantDTO.setOpenedAt( DateTimeFormatter.ISO_LOCAL_TIME.format( output.openedAt() ) );
        }
        if ( output.closedAt() != null ) {
            restaurantDTO.setClosedAt( DateTimeFormatter.ISO_LOCAL_TIME.format( output.closedAt() ) );
        }

        restaurantDTO.setId( java.util.UUID.fromString(output.id().toString()) );

        return restaurantDTO;
    }

    @Override
    public RestaurantDTO toDTO(RestaurantGetByIdUseCaseOutput output) {
        if ( output == null ) {
            return null;
        }

        RestaurantDTO restaurantDTO = new RestaurantDTO();

        restaurantDTO.setName( output.name() );
        restaurantDTO.setNumberOfTables( output.numberOfTables() );
        restaurantDTO.setAddress( output.address() );
        restaurantDTO.setCity( output.city() );
        restaurantDTO.setState( output.state() );
        restaurantDTO.setType( output.type() );
        if ( output.openedAt() != null ) {
            restaurantDTO.setOpenedAt( DateTimeFormatter.ISO_LOCAL_TIME.format( output.openedAt() ) );
        }
        if ( output.closedAt() != null ) {
            restaurantDTO.setClosedAt( DateTimeFormatter.ISO_LOCAL_TIME.format( output.closedAt() ) );
        }

        restaurantDTO.setId( java.util.UUID.fromString(output.id().toString()) );

        return restaurantDTO;
    }

    @Override
    public RestaurantDTO toDTO(RestaurantListUseCaseOutput output) {
        if ( output == null ) {
            return null;
        }

        RestaurantDTO restaurantDTO = new RestaurantDTO();

        restaurantDTO.setName( output.name() );
        restaurantDTO.setNumberOfTables( output.numberOfTables() );
        restaurantDTO.setAddress( output.address() );
        restaurantDTO.setCity( output.city() );
        restaurantDTO.setState( output.state() );
        restaurantDTO.setType( output.type() );
        if ( output.openedAt() != null ) {
            restaurantDTO.setOpenedAt( DateTimeFormatter.ISO_LOCAL_TIME.format( output.openedAt() ) );
        }
        if ( output.closedAt() != null ) {
            restaurantDTO.setClosedAt( DateTimeFormatter.ISO_LOCAL_TIME.format( output.closedAt() ) );
        }

        restaurantDTO.setId( java.util.UUID.fromString(output.id().toString()) );

        return restaurantDTO;
    }

    @Override
    public RestaurantDTO toDTO(RestaurantUpdateUseCaseOutput output) {
        if ( output == null ) {
            return null;
        }

        RestaurantDTO restaurantDTO = new RestaurantDTO();

        restaurantDTO.setName( output.name() );
        restaurantDTO.setNumberOfTables( output.numberOfTables() );
        restaurantDTO.setAddress( output.address() );
        restaurantDTO.setCity( output.city() );
        restaurantDTO.setState( output.state() );
        restaurantDTO.setType( output.type() );
        if ( output.openedAt() != null ) {
            restaurantDTO.setOpenedAt( DateTimeFormatter.ISO_LOCAL_TIME.format( output.openedAt() ) );
        }
        if ( output.closedAt() != null ) {
            restaurantDTO.setClosedAt( DateTimeFormatter.ISO_LOCAL_TIME.format( output.closedAt() ) );
        }

        restaurantDTO.setId( java.util.UUID.fromString(output.id().toString()) );

        return restaurantDTO;
    }

    @Override
    public RestaurantCreateUseCaseInput fromDTO(CreateRestaurantDTO dto) {
        if ( dto == null ) {
            return null;
        }

        String name = null;
        Integer numberOfTables = null;
        String address = null;
        String city = null;
        String state = null;
        RestaurantDTO.TypeEnum type = null;
        LocalTime openedAt = null;
        LocalTime closedAt = null;

        name = dto.getName();
        numberOfTables = dto.getNumberOfTables();
        address = dto.getAddress();
        city = dto.getCity();
        state = dto.getState();
        type = typeEnumToTypeEnum( dto.getType() );
        if ( dto.getOpenedAt() != null ) {
            openedAt = LocalTime.parse( dto.getOpenedAt() );
        }
        if ( dto.getClosedAt() != null ) {
            closedAt = LocalTime.parse( dto.getClosedAt() );
        }

        RestaurantCreateUseCaseInput restaurantCreateUseCaseInput = new RestaurantCreateUseCaseInput( name, numberOfTables, address, city, state, type, openedAt, closedAt );

        return restaurantCreateUseCaseInput;
    }

    @Override
    public RestaurantUpdateUseCaseInput fromDTO(String id, UpdateRestaurantDTO dto) {
        if ( id == null && dto == null ) {
            return null;
        }

        String name = null;
        Integer numberOfTables = null;
        String address = null;
        String city = null;
        String state = null;
        RestaurantDTO.TypeEnum type = null;
        LocalTime openedAt = null;
        LocalTime closedAt = null;
        if ( dto != null ) {
            name = dto.getName();
            numberOfTables = dto.getNumberOfTables();
            address = dto.getAddress();
            city = dto.getCity();
            state = dto.getState();
            type = typeEnumToTypeEnum1( dto.getType() );
            if ( dto.getOpenedAt() != null ) {
                openedAt = LocalTime.parse( dto.getOpenedAt() );
            }
            if ( dto.getClosedAt() != null ) {
                closedAt = LocalTime.parse( dto.getClosedAt() );
            }
        }
        String id1 = null;
        id1 = id;

        RestaurantUpdateUseCaseInput restaurantUpdateUseCaseInput = new RestaurantUpdateUseCaseInput( id1, name, numberOfTables, address, city, state, type, openedAt, closedAt );

        return restaurantUpdateUseCaseInput;
    }

    @Override
    public Restaurant toObject(RestaurantEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Restaurant restaurant = new Restaurant();

        restaurant.setAddress( entity.getAddress() );
        restaurant.setCity( entity.getCity() );
        restaurant.setClosedAt( entity.getClosedAt() );
        restaurant.setName( entity.getName() );
        restaurant.setNumberOfTables( entity.getNumberOfTables() );
        restaurant.setOpenedAt( entity.getOpenedAt() );
        restaurant.setState( entity.getState() );
        if ( entity.getType() != null ) {
            restaurant.setType( Enum.valueOf( RestaurantDTO.TypeEnum.class, entity.getType() ) );
        }

        restaurant.setId( new BaseId(entity.getId()) );

        return restaurant;
    }

    protected RestaurantDTO.TypeEnum typeEnumToTypeEnum(CreateRestaurantDTO.TypeEnum typeEnum) {
        if ( typeEnum == null ) {
            return null;
        }

        RestaurantDTO.TypeEnum typeEnum1;

        switch ( typeEnum ) {
            case BRAZILIAN: typeEnum1 = RestaurantDTO.TypeEnum.BRAZILIAN;
            break;
            case JAPAN: typeEnum1 = RestaurantDTO.TypeEnum.JAPAN;
            break;
            case ARABIC: typeEnum1 = RestaurantDTO.TypeEnum.ARABIC;
            break;
            case ITALIAN: typeEnum1 = RestaurantDTO.TypeEnum.ITALIAN;
            break;
            case MEXICAN: typeEnum1 = RestaurantDTO.TypeEnum.MEXICAN;
            break;
            case CHINESE: typeEnum1 = RestaurantDTO.TypeEnum.CHINESE;
            break;
            case VEGAN: typeEnum1 = RestaurantDTO.TypeEnum.VEGAN;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + typeEnum );
        }

        return typeEnum1;
    }

    protected RestaurantDTO.TypeEnum typeEnumToTypeEnum1(UpdateRestaurantDTO.TypeEnum typeEnum) {
        if ( typeEnum == null ) {
            return null;
        }

        RestaurantDTO.TypeEnum typeEnum1;

        switch ( typeEnum ) {
            case BRAZILIAN: typeEnum1 = RestaurantDTO.TypeEnum.BRAZILIAN;
            break;
            case JAPAN: typeEnum1 = RestaurantDTO.TypeEnum.JAPAN;
            break;
            case ARABIC: typeEnum1 = RestaurantDTO.TypeEnum.ARABIC;
            break;
            case ITALIAN: typeEnum1 = RestaurantDTO.TypeEnum.ITALIAN;
            break;
            case MEXICAN: typeEnum1 = RestaurantDTO.TypeEnum.MEXICAN;
            break;
            case CHINESE: typeEnum1 = RestaurantDTO.TypeEnum.CHINESE;
            break;
            case VEGAN: typeEnum1 = RestaurantDTO.TypeEnum.VEGAN;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + typeEnum );
        }

        return typeEnum1;
    }
}
