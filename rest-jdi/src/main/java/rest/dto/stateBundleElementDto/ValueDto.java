package rest.dto.stateBundleElementDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValueDto {

    private String name;
    private String id;

    @JsonProperty("$type")
    private String type;

    @Override
    public String toString() {
        return "ObjValueDto{" +
               "name='" + name + '\'' +
               ", id='" + id + '\'' +
               ", type='" + type + '\'' +
               '}';
    }
}
