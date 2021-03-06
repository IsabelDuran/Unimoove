package unimoove.api.places;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.*;

/**
 * PlaceCreationRequest
 */
@Validated

public class PlaceCreationRequest   {
  @JsonProperty("idPlace")
  private String idPlace = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("category")
  private Integer category = null;

  public PlaceCreationRequest idPlace(String idPlace) {
    this.idPlace = idPlace;
    return this;
  }

  /**
   * Get idPlace
   * @return idPlace
  **/
  @ApiModelProperty(example = "PR", required = true, value = "")
      @NotNull

    public String getIdPlace() {
    return idPlace;
  }

  public void setIdPlace(String idPlace) {
    this.idPlace = idPlace;
  }

  public PlaceCreationRequest name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(example = "Puerto Real", required = true, value = "")
      @NotNull

    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public PlaceCreationRequest category(Integer category) {
    this.category = category;
    return this;
  }

  /**
   * Get category
   * @return category
  **/
  @ApiModelProperty(example = "0", required = true, value = "")
      @NotNull

    public Integer getCategory() {
    return category;
  }

  public void setCategory(Integer category) {
    this.category = category;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PlaceCreationRequest placeCreationRequest = (PlaceCreationRequest) o;
    return Objects.equals(this.idPlace, placeCreationRequest.idPlace) &&
        Objects.equals(this.name, placeCreationRequest.name) &&
        Objects.equals(this.category, placeCreationRequest.category);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idPlace, name, category);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PlaceCreationRequest {\n");
    
    sb.append("    idPlace: ").append(toIndentedString(idPlace)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
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
