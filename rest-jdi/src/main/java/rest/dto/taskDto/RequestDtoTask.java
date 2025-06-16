package rest.dto.taskDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDtoTask {

    private ProjectDto project;
    private String summary;
    private String description;

    @Override
    public String toString() {
        return "RequestDtoTask{" +
               "project=" + project +
               ", summary='" + summary + '\'' +
               ", description='" + description + '\'' +
               '}';
    }
}
