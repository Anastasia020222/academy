package rest.assured.dto.taskDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTaskDto {

    String summary;
    String idReadable;
    String description;
    String id;

    @JsonProperty("$type")
    private String type;

    @Override
    public String toString() {
        return "\n Task:" +
               " summary: " + summary +
               ", idReadable: " + idReadable +
               ", description: " + description +
               ", id: " + id +
               ", $type: " + type;
    }
}
