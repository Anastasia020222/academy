package rest.assured.services;

import org.junit.jupiter.api.Assertions;
import org.opentest4j.AssertionFailedError;
import rest.assured.dto.taskDto.RequestDtoTask;
import rest.assured.dto.taskDto.ResponseTaskDto;
import rest.assured.dto.commentDto.CommentsDto;
import rest.assured.dto.commentDto.ResponseCommentsDto;
import rest.assured.dto.stateBundleElementDto.FieldsDto;
import rest.assured.dto.stateBundleElementDto.ResponseStateElementDto;
import rest.assured.dto.tagsDto.ResponseTagsDto;
import rest.assured.dto.tagsDto.TagsDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;

public class RequestTaskChecks {

    public void checkCreateTask(List<ResponseTaskDto> responseListTaskDto, RequestDtoTask requestTaskDto, ResponseTaskDto responseTaskDto) {
        try {
            for (ResponseTaskDto l : responseListTaskDto) {
                if (responseTaskDto.getId().equals(l.getId())) {
                    try {
                        assertAll("Check add task response",
                                () -> Assertions.assertEquals(requestTaskDto.getSummary(), l.getSummary(), "Неверное название задачи"),
                                () -> Assertions.assertEquals(requestTaskDto.getDescription(), l.getDescription(), "Неверное описание у задачи")
                        );
                    } catch (AssertionFailedError e) {
                        throw new AssertionFailedError(e.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Задача " + responseTaskDto.getId() + " не была добавлена. " + e);
        }
    }

    public void checkStatusTask(ResponseStateElementDto responseStateElementDto) {
        for (FieldsDto f : responseStateElementDto.getFields()) {
            Assertions.assertEquals(f.getValue().getName(), "In Progress", "Статус задачи не изменился");
        }
    }

    public void checkAddCommentTask(CommentsDto addComment, ResponseCommentsDto responseCommentsDto) {
        try {
            for (CommentsDto getCom : responseCommentsDto.getComments()) {
                if (getCom.getId().equals(addComment.getId())) {
                    Assertions.assertEquals(addComment.getText(), getCom.getText(), "Текст комментария не верный");
                    break;
                }
            }
        } catch (AssertionFailedError e) {
            throw new AssertionFailedError("Комментарий с id " + addComment.getId() + " не был добавлен" + e);
        }
    }

    public void checkTagsInTask(TagsDto addTags, List<ResponseTagsDto> getTags) {
        try {
            for (ResponseTagsDto getTag : getTags) {
                if (addTags.getId().equals("getTag.getId()")) {
                    Assertions.assertEquals(getTag.getName(), addTags.getName(), "Tag имеет не верные данные");
                    break;
                }
            }
        } catch (AssertionFailedError e) {
            throw new AssertionFailedError("Tag с id " + addTags.getId() + " не был добавлен" + e);
        }
    }
}
