package com.g5.restaurant.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * RestaurantDTO
 */
@Validated
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-12-14T12:13:05.732254311-03:00[America/Sao_Paulo]")


public class RestaurantDTO   {
  @JsonProperty("id")
  private UUID id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("numberOfTables")
  private Integer numberOfTables = null;

  @JsonProperty("address")
  private String address = null;

  @JsonProperty("city")
  private String city = null;

  @JsonProperty("state")
  private String state = null;

  /**
   * Type of restaurant
   */
  public enum TypeEnum {
    BRAZILIAN("BRAZILIAN"),
    
    JAPAN("JAPAN"),
    
    ARABIC("ARABIC"),
    
    ITALIAN("ITALIAN"),
    
    MEXICAN("MEXICAN"),
    
    CHINESE("CHINESE"),
    
    VEGAN("VEGAN");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TypeEnum fromValue(String text) {
      for (TypeEnum b : TypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("type")
  private TypeEnum type = null;

  @JsonProperty("openedAt")
  private String openedAt = null;

  @JsonProperty("closedAt")
  private String closedAt = null;

  public RestaurantDTO id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Restaurant Id.
   * @return id
   **/
  @Schema(example = "01ec2160-587e-4551-bc4a-3b65484058f8", description = "Restaurant Id.")
      @NotNull

    @Valid
    public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public RestaurantDTO name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of restaurant.
   * @return name
   **/
  @Schema(example = "Tia Nicole", description = "Name of restaurant.")
      @NotNull

    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public RestaurantDTO numberOfTables(Integer numberOfTables) {
    this.numberOfTables = numberOfTables;
    return this;
  }

  /**
   * Number of tables in restaurant
   * minimum: 1
   * @return numberOfTables
   **/
  @Schema(example = "10", description = "Number of tables in restaurant")
      @NotNull

  @Min(1)  public Integer getNumberOfTables() {
    return numberOfTables;
  }

  public void setNumberOfTables(Integer numberOfTables) {
    this.numberOfTables = numberOfTables;
  }

  public RestaurantDTO address(String address) {
    this.address = address;
    return this;
  }

  /**
   * Address of restaurant.
   * @return address
   **/
  @Schema(example = "Rua das Clarisas, 100", description = "Address of restaurant.")
      @NotNull

    public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public RestaurantDTO city(String city) {
    this.city = city;
    return this;
  }

  /**
   * City of restaurant.
   * @return city
   **/
  @Schema(example = "Belo Horizonte", description = "City of restaurant.")
      @NotNull

    public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public RestaurantDTO state(String state) {
    this.state = state;
    return this;
  }

  /**
   * State of restaurant.
   * @return state
   **/
  @Schema(example = "MG", description = "State of restaurant.")
      @NotNull

    public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public RestaurantDTO type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * Type of restaurant
   * @return type
   **/
  @Schema(example = "BRAZILIAN", description = "Type of restaurant")
      @NotNull

    public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public RestaurantDTO openedAt(String openedAt) {
    this.openedAt = openedAt;
    return this;
  }

  /**
   * Started at restaurant.
   * @return openedAt
   **/
  @Schema(example = "10:00:00", description = "Started at restaurant.")
      @NotNull

    public String getOpenedAt() {
    return openedAt;
  }

  public void setOpenedAt(String openedAt) {
    this.openedAt = openedAt;
  }

  public RestaurantDTO closedAt(String closedAt) {
    this.closedAt = closedAt;
    return this;
  }

  /**
   * Closed at restaurant.
   * @return closedAt
   **/
  @Schema(example = "10:00:00", description = "Closed at restaurant.")
      @NotNull

    public String getClosedAt() {
    return closedAt;
  }

  public void setClosedAt(String closedAt) {
    this.closedAt = closedAt;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RestaurantDTO restaurant = (RestaurantDTO) o;
    return Objects.equals(this.id, restaurant.id) &&
        Objects.equals(this.name, restaurant.name) &&
        Objects.equals(this.numberOfTables, restaurant.numberOfTables) &&
        Objects.equals(this.address, restaurant.address) &&
        Objects.equals(this.city, restaurant.city) &&
        Objects.equals(this.state, restaurant.state) &&
        Objects.equals(this.type, restaurant.type) &&
        Objects.equals(this.openedAt, restaurant.openedAt) &&
        Objects.equals(this.closedAt, restaurant.closedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, numberOfTables, address, city, state, type, openedAt, closedAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RestaurantDTO {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    numberOfTables: ").append(toIndentedString(numberOfTables)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    openedAt: ").append(toIndentedString(openedAt)).append("\n");
    sb.append("    closedAt: ").append(toIndentedString(closedAt)).append("\n");
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
