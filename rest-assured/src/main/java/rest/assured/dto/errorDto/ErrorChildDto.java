package rest.assured.dto.errorDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorChildDto {

    private String error;
    private String error_description;
    private String error_developer_message;
    private String error_field;

    @Override
    public String toString() {
        return "ErrorChildDto{" +
               "error='" + error + '\'' +
               ", error_description='" + error_description + '\'' +
               ", error_developer_message='" + error_developer_message + '\'' +
               ", error_field='" + error_field + '\'' +
               '}';
    }
}
