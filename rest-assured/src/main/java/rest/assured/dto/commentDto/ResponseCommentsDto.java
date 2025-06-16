package rest.assured.dto.commentDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import rest.assured.dto.stateBundleElementDto.FieldsDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCommentsDto {

    private List<CommentsDto> comments;
    private List<FieldsDto> fields;

    @JsonProperty("$type")
    private String type;
}
