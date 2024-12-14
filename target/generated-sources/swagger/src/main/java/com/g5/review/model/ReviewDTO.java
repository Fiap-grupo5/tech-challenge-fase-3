package com.g5.review.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * ReviewDTO
 */
@Validated
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-12-13T00:34:18.274509132-03:00[America/Sao_Paulo]")


public class ReviewDTO   {
  @JsonProperty("id")
  private UUID id = null;

  @JsonProperty("restaurantId")
  private UUID restaurantId = null;

  @JsonProperty("reviewerName")
  private String reviewerName = null;

  @JsonProperty("rating")
  private Integer rating = null;

  @JsonProperty("comments")
  private String comments = null;

  @JsonProperty("createdAt")
  private OffsetDateTime createdAt = null;

  @JsonProperty("updatedAt")
  private OffsetDateTime updatedAt = null;

  public ReviewDTO id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Review ID
   * @return id
   **/
  @Schema(example = "01ec2160-587e-4551-bc4a-3b65484058f8", description = "Review ID")
      @NotNull

    @Valid
    public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public ReviewDTO restaurantId(UUID restaurantId) {
    this.restaurantId = restaurantId;
    return this;
  }

  /**
   * Restaurant ID
   * @return restaurantId
   **/
  @Schema(example = "01ec2160-587e-4551-bc4a-3b65484058f8", description = "Restaurant ID")
      @NotNull

    @Valid
    public UUID getRestaurantId() {
    return restaurantId;
  }

  public void setRestaurantId(UUID restaurantId) {
    this.restaurantId = restaurantId;
  }

  public ReviewDTO reviewerName(String reviewerName) {
    this.reviewerName = reviewerName;
    return this;
  }

  /**
   * Name of reviewer
   * @return reviewerName
   **/
  @Schema(description = "Name of reviewer")
      @NotNull

  @Size(min=2)   public String getReviewerName() {
    return reviewerName;
  }

  public void setReviewerName(String reviewerName) {
    this.reviewerName = reviewerName;
  }

  public ReviewDTO rating(Integer rating) {
    this.rating = rating;
    return this;
  }

  /**
   * Ratting of review
   * minimum: 0
   * maximum: 100
   * @return rating
   **/
  @Schema(description = "Ratting of review")
      @NotNull

  @Min(0) @Max(100)   public Integer getRating() {
    return rating;
  }

  public void setRating(Integer rating) {
    this.rating = rating;
  }

  public ReviewDTO comments(String comments) {
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

  public ReviewDTO createdAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * Get createdAt
   * @return createdAt
   **/
  @Schema(description = "")
      @NotNull

    @Valid
    public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public ReviewDTO updatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
    return this;
  }

  /**
   * Get updatedAt
   * @return updatedAt
   **/
  @Schema(description = "")
      @NotNull

    @Valid
    public OffsetDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReviewDTO review = (ReviewDTO) o;
    return Objects.equals(this.id, review.id) &&
        Objects.equals(this.restaurantId, review.restaurantId) &&
        Objects.equals(this.reviewerName, review.reviewerName) &&
        Objects.equals(this.rating, review.rating) &&
        Objects.equals(this.comments, review.comments) &&
        Objects.equals(this.createdAt, review.createdAt) &&
        Objects.equals(this.updatedAt, review.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, restaurantId, reviewerName, rating, comments, createdAt, updatedAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReviewDTO {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    restaurantId: ").append(toIndentedString(restaurantId)).append("\n");
    sb.append("    reviewerName: ").append(toIndentedString(reviewerName)).append("\n");
    sb.append("    rating: ").append(toIndentedString(rating)).append("\n");
    sb.append("    comments: ").append(toIndentedString(comments)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n");
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
