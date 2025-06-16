package rest.assured.dto.errorDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponseDto {

    private String error;
    private String error_description;
    private String error_developer_message;
    private List<ErrorChildDto> error_children;

    @Override
    public String toString() {
        return "ErrorResponseDto{" +
               "error='" + error + '\'' +
               ", error_description='" + error_description + '\'' +
               ", error_children=" + error_children +
               '}';
    }
}
