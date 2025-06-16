package rest.dto.stateBundleElementDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldsDto {

    private ValueDto value;
    private String name;

    @JsonProperty("$type")
    private String type;
}
