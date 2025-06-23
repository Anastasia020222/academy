package cucumber.selenide.pages;

import cucumber.selenide.api.ApiRequest;
import cucumber.selenide.api.task.Task;

public abstract class AbsBasePage {

    protected Task task;
    private final ApiRequest apiRequest = new ApiRequest();

    public AbsBasePage() {
        this.task = apiRequest.getTask();
    }
}
