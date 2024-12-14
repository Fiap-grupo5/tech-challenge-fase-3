package com.g5.review.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * CreateReviewDTO
 */
@Validated
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-12-14T12:13:02.936758130-03:00[America/Sao_Paulo]")


public class CreateReviewDTO   {
  @JsonProperty("restaurantId")
  private UUID restaurantId = null;

  @JsonProperty("reviewerName")
  private String reviewerName = null;

  @JsonProperty("rating")
  private Integer rating = null;

  @JsonProperty("comments")
  private String comments = null;

  public CreateReviewDTO restaurantId(UUID restaurantId) {
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

  public CreateReviewDTO reviewerName(String reviewerName) {
    this.reviewerName = reviewerName;
    return this;
  }

  /**
   * Name of reviewer
   * @return reviewerName
   **/
  @Schema(required = true, description = "Name of reviewer")
      @NotNull

  @Size(min=2)   public String getReviewerName() {
    return reviewerName;
  }

  public void setReviewerName(String reviewerName) {
    this.reviewerName = reviewerName;
  }

  public CreateReviewDTO rating(Integer rating) {
    this.rating = rating;
    return this;
  }

  /**
   * Ratting of review
   * minimum: 0
   * maximum: 100
   * @return rating
   **/
  @Schema(required = true, description = "Ratting of review")
      @NotNull

  @Min(0) @Max(100)   public Integer getRating() {
    return rating;
  }

  public void setRating(Integer rating) {
    this.rating = rating;
  }

  public CreateReviewDTO comments(String comments) {
    this.comments = comments;
    return this;
  }

  /**
   * Comments of review
   * @return comments
   **/
  @Schema(description = "Comments of review")
      @NotNull

    public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateReviewDTO createReview = (CreateReviewDTO) o;
    return Objects.equals(this.restaurantId, createReview.restaurantId) &&
        Objects.equals(this.reviewerName, createReview.reviewerName) &&
        Objects.equals(this.rating, createReview.rating) &&
        Objects.equals(this.comments, createReview.comments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(restaurantId, reviewerName, rating, comments);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateReviewDTO {\n");
    
    sb.append("    restaurantId: ").append(toIndentedString(restaurantId)).append("\n");
    sb.append("    reviewerName: ").append(toIndentedString(reviewerName)).append("\n");
    sb.append("    rating: ").append(toIndentedString(rating)).append("\n");
    sb.append("    comments: ").append(toIndentedString(comments)).append("\n");
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
