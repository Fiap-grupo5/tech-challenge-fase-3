package com.g5.restaurants.aplication.infrastructure.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Method;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.g5.restaurant.model.CreateRestaurantDTO;
import com.g5.restaurant.model.RestaurantDTO;
import com.g5.restaurant.model.UpdateRestaurantDTO;
import com.g5.restaurants.aplication.domain.base.BaseId;
import com.g5.restaurants.aplication.domain.restaurant.Restaurant;
import com.g5.restaurants.aplication.usecases.restaurant.create.RestaurantCreateUseCaseInput;
import com.g5.restaurants.aplication.usecases.restaurant.create.RestaurantCreateUseCaseOutput;
import com.g5.restaurants.aplication.usecases.restaurant.retrive.get.RestaurantGetByIdUseCaseOutput;
import com.g5.restaurants.aplication.usecases.restaurant.retrive.list.RestaurantListUseCaseOutput;
import com.g5.restaurants.aplication.usecases.restaurant.update.RestaurantUpdateUseCaseOutput;
import com.g5.restaurants.infrastructure.mappers.RestaurantMapperImpl;
import com.g5.restaurants.infrastructure.persistence.entities.RestaurantEntity;

class RestaurantMapperImplTest {

    private RestaurantMapperImpl mapper;

    @BeforeEach
    void setUp() {
        mapper = new RestaurantMapperImpl();
    }

    @Test
    void testToDTOWithCreateUseCaseOutput() {
        RestaurantCreateUseCaseOutput output = new RestaurantCreateUseCaseOutput(
                new BaseId(UUID.randomUUID().toString()), "Restaurant Name", 10,
                "Address", "City", "State", RestaurantDTO.TypeEnum.BRAZILIAN,
                LocalTime.of(9, 0), LocalTime.of(22, 0)
        );

        RestaurantDTO dto = mapper.toDTO(output);

        assertNotNull(dto);
        assertEquals(output.name(), dto.getName());
        assertEquals(output.numberOfTables(), dto.getNumberOfTables());
        assertEquals(DateTimeFormatter.ISO_LOCAL_TIME.format(output.openedAt()), dto.getOpenedAt());
        assertEquals(DateTimeFormatter.ISO_LOCAL_TIME.format(output.closedAt()), dto.getClosedAt());
    }

    @Test
    void testToDTOWithNullCreateUseCaseOutput() {
        assertNull(mapper.toDTO((RestaurantCreateUseCaseOutput) null));
    }

    @Test
    void testFromDTOCreateRestaurant() {
        CreateRestaurantDTO dto = new CreateRestaurantDTO();
        dto.setName("Test Restaurant");
        dto.setNumberOfTables(20);
        dto.setOpenedAt("10:00:00");
        dto.setClosedAt("23:00:00");

        RestaurantCreateUseCaseInput input = mapper.fromDTO(dto);

        assertNotNull(input);
        assertEquals(dto.getName(), input.name());
        assertEquals(LocalTime.parse(dto.getOpenedAt()), input.openedAt());
    }

    @Test
    void testTypeEnumToTypeEnum() throws Exception {
        Method method = RestaurantMapperImpl.class.getDeclaredMethod(
                "typeEnumToTypeEnum", CreateRestaurantDTO.TypeEnum.class);
        method.setAccessible(true);

        RestaurantDTO.TypeEnum result = (RestaurantDTO.TypeEnum) method.invoke(
                mapper, CreateRestaurantDTO.TypeEnum.BRAZILIAN);

        assertEquals(RestaurantDTO.TypeEnum.BRAZILIAN, result);
    }

    @Test
    void testTypeEnumToTypeEnum1() throws Exception {
        Method method = RestaurantMapperImpl.class.getDeclaredMethod(
                "typeEnumToTypeEnum1", UpdateRestaurantDTO.TypeEnum.class);
        method.setAccessible(true);

        RestaurantDTO.TypeEnum result = (RestaurantDTO.TypeEnum) method.invoke(
                mapper, UpdateRestaurantDTO.TypeEnum.JAPAN);

        assertEquals(RestaurantDTO.TypeEnum.JAPAN, result);
    }

    @Test
    void testToObjectWithEntity() {
        RestaurantEntity entity = new RestaurantEntity();
        entity.setId("123e4567-e89b-12d3-a456-426614174000");
        entity.setName("Test Restaurant");
        entity.setNumberOfTables(30);

        Restaurant restaurant = mapper.toObject(entity);

        assertNotNull(restaurant);
        assertEquals("Test Restaurant", restaurant.getName());
        assertEquals(30, restaurant.getNumberOfTables());
    }

    @Test
    void testToDTOWithNullInputs() {
        // RestaurantCreateUseCaseOutput null
        assertNull(mapper.toDTO((RestaurantCreateUseCaseOutput) null));
        assertNull(mapper.toDTO((RestaurantGetByIdUseCaseOutput) null));
        assertNull(mapper.toDTO((RestaurantListUseCaseOutput) null));
        assertNull(mapper.toDTO((RestaurantUpdateUseCaseOutput) null));
    }

    @Test
    void testFromDTOWithNullInputs() {
        // CreateRestaurantDTO null
        assertNull(mapper.fromDTO((CreateRestaurantDTO) null));
        
        // UpdateRestaurantDTO null and id null
        assertNull(mapper.fromDTO(null, null));
    }

    @Test
    void testToDTOWithNullOpenedAndClosedTimes() {
        RestaurantCreateUseCaseOutput output = new RestaurantCreateUseCaseOutput(
                new BaseId(UUID.randomUUID().toString()), 
                "Restaurant Name", 
                10, 
                "Address", 
                "City", 
                "State", 
                RestaurantDTO.TypeEnum.BRAZILIAN, 
                null, // openedAt null
                null  // closedAt null
        );
    
        RestaurantDTO dto = mapper.toDTO(output);
    
        assertNotNull(dto);
        assertNull(dto.getOpenedAt());
        assertNull(dto.getClosedAt());
    }

    @Test
    void testTypeEnumToTypeEnumWithAllCases() throws Exception {
        Method method = RestaurantMapperImpl.class.getDeclaredMethod("typeEnumToTypeEnum", CreateRestaurantDTO.TypeEnum.class);
        method.setAccessible(true); // Permite acessar métodos protegidos ou privados
    
        assertEquals(RestaurantDTO.TypeEnum.BRAZILIAN, method.invoke(mapper, CreateRestaurantDTO.TypeEnum.BRAZILIAN));
        assertEquals(RestaurantDTO.TypeEnum.JAPAN, method.invoke(mapper, CreateRestaurantDTO.TypeEnum.JAPAN));
        assertEquals(RestaurantDTO.TypeEnum.ARABIC, method.invoke(mapper, CreateRestaurantDTO.TypeEnum.ARABIC));
        assertEquals(RestaurantDTO.TypeEnum.ITALIAN, method.invoke(mapper, CreateRestaurantDTO.TypeEnum.ITALIAN));
        assertEquals(RestaurantDTO.TypeEnum.MEXICAN, method.invoke(mapper, CreateRestaurantDTO.TypeEnum.MEXICAN));
        assertEquals(RestaurantDTO.TypeEnum.CHINESE, method.invoke(mapper, CreateRestaurantDTO.TypeEnum.CHINESE));
        assertEquals(RestaurantDTO.TypeEnum.VEGAN, method.invoke(mapper, CreateRestaurantDTO.TypeEnum.VEGAN));
    }
    
    @Test
    void testTypeEnumToTypeEnumWithNull() throws Exception {
        Method method = RestaurantMapperImpl.class.getDeclaredMethod("typeEnumToTypeEnum", CreateRestaurantDTO.TypeEnum.class);
        method.setAccessible(true); // Permite acessar métodos protegidos ou privados
    
        assertNull(method.invoke(mapper, (Object) null));
    }
    
    @Test
    void testTypeEnumToTypeEnumWithException() throws Exception {
        Method method = RestaurantMapperImpl.class.getDeclaredMethod("typeEnumToTypeEnum", CreateRestaurantDTO.TypeEnum.class);
        method.setAccessible(true);
    
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            method.invoke(mapper, Enum.valueOf(CreateRestaurantDTO.TypeEnum.class, "UNKNOWN"));
        });
    
        assertTrue(exception.getMessage().contains("No enum constant com.g5.restaurant.model.CreateRestaurantDTO.TypeEnum.UNKNOWN"));
    }
    
    @Test
    void testToDTOWithGetByIdUseCaseOutput() {
        RestaurantGetByIdUseCaseOutput output = new RestaurantGetByIdUseCaseOutput(
                new BaseId(UUID.randomUUID().toString()), "Restaurant Name", 10,
                "Address", "City", "State", RestaurantDTO.TypeEnum.ITALIAN,
                LocalTime.of(9, 0), LocalTime.of(22, 0)
        );
    
        RestaurantDTO dto = mapper.toDTO(output);
    
        assertNotNull(dto);
        assertEquals(output.name(), dto.getName());
        assertEquals(output.numberOfTables(), dto.getNumberOfTables());
        assertEquals(DateTimeFormatter.ISO_LOCAL_TIME.format(output.openedAt()), dto.getOpenedAt());
        assertEquals(DateTimeFormatter.ISO_LOCAL_TIME.format(output.closedAt()), dto.getClosedAt());
    }

    @Test
    void testFromDTOCreateRestaurantWithPartialData() {
        CreateRestaurantDTO dto = new CreateRestaurantDTO();
        dto.setName("Test Restaurant");
        // Campos `numberOfTables`, `openedAt` e `closedAt` são nulos
    
        RestaurantCreateUseCaseInput input = mapper.fromDTO(dto);
    
        assertNotNull(input);
        assertEquals(dto.getName(), input.name());
        assertNull(input.numberOfTables());
        assertNull(input.openedAt());
        assertNull(input.closedAt());
    }
    
    @Test
    void testToObjectWithPartialEntity() {
        RestaurantEntity entity = new RestaurantEntity();
        entity.setId("123e4567-e89b-12d3-a456-426614174000"); // Valor de ID válido
        entity.setName("Test Restaurant");
        // Outros campos não são definidos
    
        Restaurant restaurant = mapper.toObject(entity);
    
        assertNotNull(restaurant);
        assertEquals("Test Restaurant", restaurant.getName());
        assertNull(restaurant.getNumberOfTables());
        assertNull(restaurant.getOpenedAt());
    }

    @Test
    void testToDTOWithNullOpenedAndClosedTimesGetById() {
        RestaurantGetByIdUseCaseOutput output = new RestaurantGetByIdUseCaseOutput(
                new BaseId(UUID.randomUUID().toString()),
                "Restaurant Name",
                10,
                "Address",
                "City",
                "State",
                RestaurantDTO.TypeEnum.BRAZILIAN,
                null, // openedAt null
                null  // closedAt null
        );
    
        RestaurantDTO dto = mapper.toDTO(output);
    
        assertNotNull(dto);
        assertNull(dto.getOpenedAt());
        assertNull(dto.getClosedAt());
    }

    @Test
    void testFromDTOWithNullIdAndDTO() {
        assertNull(mapper.fromDTO(null, null));
    }

    @Test
    void testToObjectWithValidEntity() {
        RestaurantEntity entity = new RestaurantEntity();
        entity.setId("123e4567-e89b-12d3-a456-426614174000");
        entity.setName("Test Restaurant");
        entity.setNumberOfTables(30);
        entity.setAddress("123 Main St");
        entity.setCity("Test City");
        entity.setState("Test State");
        entity.setType("BRAZILIAN");
        entity.setOpenedAt(LocalTime.of(10, 0));
        entity.setClosedAt(LocalTime.of(22, 0));
    
        Restaurant restaurant = mapper.toObject(entity);
    
        assertNotNull(restaurant);
        assertEquals(entity.getId(), restaurant.getId().toString());
        assertEquals(entity.getName(), restaurant.getName());
        assertEquals(entity.getNumberOfTables(), restaurant.getNumberOfTables());
        assertEquals(entity.getAddress(), restaurant.getAddress());
        assertEquals(entity.getCity(), restaurant.getCity());
        assertEquals(entity.getState(), restaurant.getState());
        assertEquals(RestaurantDTO.TypeEnum.BRAZILIAN, restaurant.getType());
        assertEquals(entity.getOpenedAt(), restaurant.getOpenedAt());
        assertEquals(entity.getClosedAt(), restaurant.getClosedAt());
    }

    @Test
    void testFromDTOWithNullTimes() {
        CreateRestaurantDTO dto = new CreateRestaurantDTO();
        dto.setOpenedAt(null);
        dto.setClosedAt(null);
    
        RestaurantCreateUseCaseInput input = mapper.fromDTO(dto);
    
        assertNotNull(input);
        assertNull(input.openedAt());
        assertNull(input.closedAt());
    }
    
    @Test
    void testToObjectWithInvalidEnum() {
        RestaurantEntity entity = new RestaurantEntity();
        entity.setType("INVALID");
    
        assertThrows(IllegalArgumentException.class, () -> mapper.toObject(entity));
    }
    
    
}
