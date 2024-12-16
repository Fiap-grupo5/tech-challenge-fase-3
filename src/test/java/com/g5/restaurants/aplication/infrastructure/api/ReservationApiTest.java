package com.g5.restaurants.aplication.infrastructure.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.g5.reservation.api.ReservationApi;
import com.g5.reservation.model.CreateReservationDTO;
import com.g5.reservation.model.PaginateReservationDTO;
import com.g5.reservation.model.ReservationDTO;
import com.g5.reservation.model.UpdateReservationDTO;

import jakarta.servlet.http.HttpServletRequest;

class ReservationApiTest {

    private ReservationApi reservationApi;
    private ObjectMapper objectMapperMock;
    private HttpServletRequest requestMock;

    @BeforeEach
    void setUp() {
        reservationApi = Mockito.spy(ReservationApi.class);
        objectMapperMock = mock(ObjectMapper.class);
        requestMock = mock(HttpServletRequest.class);

        doReturn(Optional.of(objectMapperMock)).when(reservationApi).getObjectMapper();
        doReturn(Optional.of(requestMock)).when(reservationApi).getRequest();
    }

    @Test
    void testCreateReservation() throws Exception {
        // Arrange
        CreateReservationDTO createDto = new CreateReservationDTO();
        createDto.setCustomerName("John Doe");
        when(requestMock.getHeader("Accept")).thenReturn("application/json");
        when(objectMapperMock.readValue(anyString(), eq(ReservationDTO.class))).thenReturn(new ReservationDTO());

        // Act
        ResponseEntity<ReservationDTO> response = reservationApi.createReservation(createDto);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_IMPLEMENTED);
        verify(objectMapperMock, times(1)).readValue(anyString(), eq(ReservationDTO.class));
    }

    @Test
    void testDeleteReservation() {
        // Arrange
        String reservationId = "01ec2160-587e-4551-bc4a-3b65484058f8";

        when(requestMock.getHeader("Accept")).thenReturn(null);

        // Act
        ResponseEntity<Void> response = reservationApi.deleteReservation(reservationId);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_IMPLEMENTED);
        verify(requestMock, times(1)).getHeader("Accept");
    }

    @Test
    void testFindReservation() throws Exception {
        // Arrange
        when(requestMock.getHeader("Accept")).thenReturn("application/json");
        when(objectMapperMock.readValue(anyString(), eq(PaginateReservationDTO.class))).thenReturn(new PaginateReservationDTO());

        // Act
        ResponseEntity<PaginateReservationDTO> response = reservationApi.findReservation();

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_IMPLEMENTED);
        verify(objectMapperMock, times(1)).readValue(anyString(), eq(PaginateReservationDTO.class));
    }

    @Test
    void testFindReservationById() throws Exception {
        // Arrange
        String reservationId = "01ec2160-587e-4551-bc4a-3b65484058f8";
        when(requestMock.getHeader("Accept")).thenReturn("application/json");
        when(objectMapperMock.readValue(anyString(), eq(ReservationDTO.class))).thenReturn(new ReservationDTO());

        // Act
        ResponseEntity<ReservationDTO> response = reservationApi.findReservationById(reservationId);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_IMPLEMENTED);
        verify(objectMapperMock, times(1)).readValue(anyString(), eq(ReservationDTO.class));
    }

    @Test
    void testUpdateReservation() throws Exception {
        // Arrange
        String reservationId = "01ec2160-587e-4551-bc4a-3b65484058f8";
        UpdateReservationDTO updateDto = new UpdateReservationDTO();
        when(requestMock.getHeader("Accept")).thenReturn("application/json");
        when(objectMapperMock.readValue(anyString(), eq(ReservationDTO.class))).thenReturn(new ReservationDTO());

        // Act
        ResponseEntity<ReservationDTO> response = reservationApi.updateReservation(reservationId, updateDto);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_IMPLEMENTED);
        verify(objectMapperMock, times(1)).readValue(anyString(), eq(ReservationDTO.class));
    }

    @Test
    void testGetObjectMapperReturnsEmpty() {
        // Arrange
        doReturn(Optional.empty()).when(reservationApi).getObjectMapper();

        // Act
        Optional<ObjectMapper> objectMapper = reservationApi.getObjectMapper();

        // Assert
        assertThat(objectMapper).isEmpty();
    }

    @Test
    void testGetRequestReturnsEmpty() {
        // Arrange
        doReturn(Optional.empty()).when(reservationApi).getRequest();

        // Act
        Optional<HttpServletRequest> request = reservationApi.getRequest();

        // Assert
        assertThat(request).isEmpty();
    }

    @Test
    void testGetAcceptHeaderReturnsEmpty() {
        // Arrange
        doReturn(Optional.empty()).when(reservationApi).getRequest();

        // Act
        Optional<String> acceptHeader = reservationApi.getAcceptHeader();

        // Assert
        assertThat(acceptHeader).isEmpty();
    }

    @Test
    void testCreateReservationWithUnsupportedAcceptHeader() throws Exception {
        // Arrange
        CreateReservationDTO createDto = new CreateReservationDTO();
        when(requestMock.getHeader("Accept")).thenReturn("text/html");
        
        // Act
        ResponseEntity<ReservationDTO> response = reservationApi.createReservation(createDto);
    
        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_IMPLEMENTED);
        verify(requestMock, times(2)).getHeader("Accept"); // Alterado para aceitar duas chamadas
        verify(objectMapperMock, never()).readValue(anyString(), eq(ReservationDTO.class));
    }
    
    @Test
    void testFindReservationWithoutObjectMapperOrRequest() {
        // Arrange
        doReturn(Optional.empty()).when(reservationApi).getObjectMapper();
        doReturn(Optional.empty()).when(reservationApi).getRequest();
    
        // Act
        ResponseEntity<PaginateReservationDTO> response = reservationApi.findReservation();
    
        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_IMPLEMENTED);
        verify(reservationApi, times(1)).getObjectMapper(); // Já chamado no método findReservation
    }
    
    @Test
    void testDeleteReservationHandlesMissingAcceptHeader() {
        // Arrange
        String reservationId = "01ec2160-587e-4551-bc4a-3b65484058f8";
        doReturn(Optional.empty()).when(reservationApi).getRequest();
    
        // Act
        ResponseEntity<Void> response = reservationApi.deleteReservation(reservationId);
    
        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_IMPLEMENTED);
        verify(reservationApi, times(1)).getRequest();
    }
   
}
