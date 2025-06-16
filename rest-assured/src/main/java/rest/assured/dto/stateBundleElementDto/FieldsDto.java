package rest.assured.dto.stateBundleElementDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldsDto {

    private ValueDto value;
    private String name;

    @JsonProperty("$type")
    private String type;
}
