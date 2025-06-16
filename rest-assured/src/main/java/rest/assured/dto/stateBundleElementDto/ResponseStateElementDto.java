package rest.assured.dto.stateBundleElementDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseStateElementDto {

    private List<FieldsDto> fields;

    @JsonProperty("$type")
    private String type;

    @Override
    public String toString() {
        return "IssuesDto{" +
               "fields=" + fields +
               ", type='" + type + '\'' +
               '}';
    }
}
