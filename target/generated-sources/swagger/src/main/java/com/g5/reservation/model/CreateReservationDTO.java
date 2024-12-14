package com.g5.reservation.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * CreateReservationDTO
 */
@Validated
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-12-14T10:04:32.755161419-03:00[America/Sao_Paulo]")


public class CreateReservationDTO   {
  @JsonProperty("restaurantId")
  private UUID restaurantId = null;

  @JsonProperty("customerName")
  private String customerName = null;

  @JsonProperty("customerContact")
  private String customerContact = null;

  @JsonProperty("reservationDate")
  private LocalDate reservationDate = null;

  @JsonProperty("numberOfTables")
  private Integer numberOfTables = null;

  /**
   * Reservation Status
   */
  public enum StatusEnum {
    PENDING("PENDING"),
    
    CONFIRMED("CONFIRMED"),
    
    CANCELED("CANCELED");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("status")
  private StatusEnum status = null;

  public CreateReservationDTO restaurantId(UUID restaurantId) {
    this.restaurantId = restaurantId;
    return this;
  }

  /**
   * Restaurant ID
   * @return restaurantId
   **/
  @Schema(example = "01ec2160-587e-4551-bc4a-3b65484058f8", required = true, description = "Restaurant ID")
      @NotNull

    @Valid
    public UUID getRestaurantId() {
    return restaurantId;
  }

  public void setRestaurantId(UUID restaurantId) {
    this.restaurantId = restaurantId;
  }

  public CreateReservationDTO customerName(String customerName) {
    this.customerName = customerName;
    return this;
  }

  /**
   * Name of Customer
   * @return customerName
   **/
  @Schema(example = "Gabriel Silva", required = true, description = "Name of Customer")
      @NotNull

  @Size(min=2)   public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public CreateReservationDTO customerContact(String customerContact) {
    this.customerContact = customerContact;
    return this;
  }

  /**
   * Contact of Customer
   * @return customerContact
   **/
  @Schema(required = true, description = "Contact of Customer")
      @NotNull

    public String getCustomerContact() {
    return customerContact;
  }

  public void setCustomerContact(String customerContact) {
    this.customerContact = customerContact;
  }

  public CreateReservationDTO reservationDate(LocalDate reservationDate) {
    this.reservationDate = reservationDate;
    return this;
  }

  /**
   * Get reservationDate
   * @return reservationDate
   **/
  @Schema(example = "Sun Dec 31 21:00:00 BRT 2023", required = true, description = "")
      @NotNull

    @Valid
    public LocalDate getReservationDate() {
    return reservationDate;
  }

  public void setReservationDate(LocalDate reservationDate) {
    this.reservationDate = reservationDate;
  }

  public CreateReservationDTO numberOfTables(Integer numberOfTables) {
    this.numberOfTables = numberOfTables;
    return this;
  }

  /**
   * Number of tables in reservation
   * minimum: 1
   * @return numberOfTables
   **/
  @Schema(example = "1", required = true, description = "Number of tables in reservation")
      @NotNull

  @Min(1)  public Integer getNumberOfTables() {
    return numberOfTables;
  }

  public void setNumberOfTables(Integer numberOfTables) {
    this.numberOfTables = numberOfTables;
  }

  public CreateReservationDTO status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Reservation Status
   * @return status
   **/
  @Schema(example = "PENDING", description = "Reservation Status")
      @NotNull

    public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateReservationDTO createReservation = (CreateReservationDTO) o;
    return Objects.equals(this.restaurantId, createReservation.restaurantId) &&
        Objects.equals(this.customerName, createReservation.customerName) &&
        Objects.equals(this.customerContact, createReservation.customerContact) &&
        Objects.equals(this.reservationDate, createReservation.reservationDate) &&
        Objects.equals(this.numberOfTables, createReservation.numberOfTables) &&
        Objects.equals(this.status, createReservation.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(restaurantId, customerName, customerContact, reservationDate, numberOfTables, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateReservationDTO {\n");
    
    sb.append("    restaurantId: ").append(toIndentedString(restaurantId)).append("\n");
    sb.append("    customerName: ").append(toIndentedString(customerName)).append("\n");
    sb.append("    customerContact: ").append(toIndentedString(customerContact)).append("\n");
    sb.append("    reservationDate: ").append(toIndentedString(reservationDate)).append("\n");
    sb.append("    numberOfTables: ").append(toIndentedString(numberOfTables)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
