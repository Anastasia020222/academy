package string.builder.sb;

public interface ICustomStringBuilder {

    CustomStringBuilder append(String str);

    CustomStringBuilder delete(int start, int end);

    CustomStringBuilder deleteCharAt(int index);

}
