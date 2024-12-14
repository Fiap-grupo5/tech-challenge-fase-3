package com.g5.review.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * UpdateReviewDTO
 */
@Validated
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-12-14T12:13:02.936758130-03:00[America/Sao_Paulo]")


public class UpdateReviewDTO   {
  @JsonProperty("reviewerName")
  private String reviewerName = null;

  @JsonProperty("rating")
  private Integer rating = null;

  @JsonProperty("comments")
  private String comments = null;

  public UpdateReviewDTO reviewerName(String reviewerName) {
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

  public UpdateReviewDTO rating(Integer rating) {
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

  public UpdateReviewDTO comments(String comments) {
    this.comments = comments;
    return this;
  }

  /**
   * Comments of review
   * @return comments
   **/
  @Schema(required = true, description = "Comments of review")
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
    UpdateReviewDTO updateReview = (UpdateReviewDTO) o;
    return Objects.equals(this.reviewerName, updateReview.reviewerName) &&
        Objects.equals(this.rating, updateReview.rating) &&
        Objects.equals(this.comments, updateReview.comments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(reviewerName, rating, comments);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateReviewDTO {\n");
    
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
