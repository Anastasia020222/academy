package rest.services;

import lombok.Data;
import lombok.NoArgsConstructor;
import rest.dto.taskDto.ResponseTaskDto;

import static com.epam.http.requests.ServiceInit.init;

@Data
@NoArgsConstructor
public class AbsServicesApi {

//    protected APIServiceTask apiServiceTask;
//    protected APIServiceTags apiServiceTags;
    protected ResponseTaskDto task;
//
//    public AbsServicesApi() {
//        this.apiServiceTask = init(APIServiceTask.class);
//        this.apiServiceTags = init(APIServiceTags.class);
//    }

    public AbsServicesApi(ResponseTaskDto task) {
        this.task = task;
    }
}
