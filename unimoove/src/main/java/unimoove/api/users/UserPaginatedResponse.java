package unimoove.api.users;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;

import unimoove.api.users.UserResponse;
import unimoove.api.utils.PaginationInfo;

import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * UserPaginatedResponse
 */
@Validated

public class UserPaginatedResponse   {
  @JsonProperty("pages")
  @Valid
  private List<UserResponse> pages = new ArrayList<>();

  @JsonProperty("paginationInfo")
  private PaginationInfo paginationInfo = null;

  public UserPaginatedResponse pages(List<UserResponse> pages) {
    this.pages = pages;
    return this;
  }

  public UserPaginatedResponse addPagesItem(UserResponse pagesItem) {
    this.pages.add(pagesItem);
    return this;
  }

  /**
   * Get pages
   * @return pages
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull
    @Valid
    public List<UserResponse> getPages() {
    return pages;
  }

  public void setPages(List<UserResponse> pages) {
    this.pages = pages;
  }

  public UserPaginatedResponse paginationInfo(PaginationInfo paginationInfo) {
    this.paginationInfo = paginationInfo;
    return this;
  }

  /**
   * Get paginationInfo
   * @return paginationInfo
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public PaginationInfo getPaginationInfo() {
    return paginationInfo;
  }

  public void setPaginationInfo(PaginationInfo paginationInfo) {
    this.paginationInfo = paginationInfo;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserPaginatedResponse userPaginatedResponse = (UserPaginatedResponse) o;
    return Objects.equals(this.pages, userPaginatedResponse.pages) &&
        Objects.equals(this.paginationInfo, userPaginatedResponse.paginationInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pages, paginationInfo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserPaginatedResponse {\n");
    
    sb.append("    pages: ").append(toIndentedString(pages)).append("\n");
    sb.append("    paginationInfo: ").append(toIndentedString(paginationInfo)).append("\n");
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
