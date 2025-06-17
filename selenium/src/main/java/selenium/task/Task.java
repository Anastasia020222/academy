package selenium.task;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    private String id;
    private String title;

    @Override
    public String toString() {
        return "AbsTask{" +
               "id='" + id + '\'' +
               ", title='" + title + '\'' +
               '}';
    }
}
