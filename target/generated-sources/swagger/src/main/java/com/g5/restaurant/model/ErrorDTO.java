package com.g5.restaurant.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * ErrorDTO
 */
@Validated
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-12-13T00:34:19.998662689-03:00[America/Sao_Paulo]")


public class ErrorDTO   {
  @JsonProperty("code")
  private String code = null;

  @JsonProperty("message")
  private String message = null;

  @JsonProperty("details")
  @Valid
  private List<String> details = null;

  public ErrorDTO code(String code) {
    this.code = code;
    return this;
  }

  /**
   * Code of error.
   * @return code
   **/
  @Schema(example = "NOT_FOUND", description = "Code of error.")
      @NotNull

    public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public ErrorDTO message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Message of error.
   * @return message
   **/
  @Schema(example = "Not Found Error.", description = "Message of error.")
      @NotNull

    public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public ErrorDTO details(List<String> details) {
    this.details = details;
    return this;
  }

  public ErrorDTO addDetailsItem(String detailsItem) {
    if (this.details == null) {
      this.details = new ArrayList<>();
    }
    this.details.add(detailsItem);
    return this;
  }

  /**
   * Details of Errors.
   * @return details
   **/
  @Schema(description = "Details of Errors.")
      @NotNull

    public List<String> getDetails() {
    return details;
  }

  public void setDetails(List<String> details) {
    this.details = details;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorDTO error = (ErrorDTO) o;
    return Objects.equals(this.code, error.code) &&
        Objects.equals(this.message, error.message) &&
        Objects.equals(this.details, error.details);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, message, details);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ErrorDTO {\n");
    
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    details: ").append(toIndentedString(details)).append("\n");
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
