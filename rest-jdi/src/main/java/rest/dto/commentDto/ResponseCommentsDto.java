package rest.dto.commentDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import rest.dto.stateBundleElementDto.FieldsDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
