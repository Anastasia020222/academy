package cucumber.selenide.api.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
