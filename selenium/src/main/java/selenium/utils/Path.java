package selenium.utils;

public enum Path {

    ISSUES("/issues");

    private final String path;

    Path(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
