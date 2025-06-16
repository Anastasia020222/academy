package rest.common;

public class Path {
    public static String NEGATIVE_REQUEST_STATUS_TASK = "src/main/resources/NegativeRequestStatusTask.json";
    public static String REQUEST_COMMENTS_TASK = "src/main/resources/RequestCommentsTask.json";
    public static String REQUEST_STATUS_TASK = "src/main/resources/RequestStatusTask.json";
    public static String REQUEST_TAGS = "src/main/resources/RequestTags.json";
    public static String NEGATIVE_NAME_TASK = "src/main/resources/NegativeNameTask.json";
    public static String REQUEST_DELETE_TAG = "src/main/resources/RequestDeleteTags.json";
    public static String REQUEST_ADD_TAD = "{\n" +
                                           "  \"$type\": \"Tag\",\n" +
                                           "  \"name\": \"new_tag_api\",\n" +
                                           "  \"id\": \"%s\"\n" +
                                           "}";
}
