package unimoove.api.users;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.*;

/**
 * CarBrandChangeRequest
 */
@Validated
public class CarBrandChangeRequest {
	@JsonProperty("newCarBrand")
	private String newCarBrand = null;

	public CarBrandChangeRequest newCarBrand(String newCarBrand) {
		this.newCarBrand = newCarBrand;
		return this;
	}

	/**
	 * Get newCarBrand
	 * 
	 * @return newCarBrand
	 **/
	@ApiModelProperty(example = "Ford", required = true, value = "")
	@NotNull

	public String getNewCarBrand() {
		return newCarBrand;
	}

	public void setNewCarBrand(String newCarBrand) {
		this.newCarBrand = newCarBrand;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		CarBrandChangeRequest carBrandChangeRequest = (CarBrandChangeRequest) o;
		return Objects.equals(this.newCarBrand, carBrandChangeRequest.newCarBrand);
	}

	@Override
	public int hashCode() {
		return Objects.hash(newCarBrand);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class CarBrandChangeRequest {\n");

		sb.append("    newCarBrand: ").append(toIndentedString(newCarBrand)).append("\n");
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