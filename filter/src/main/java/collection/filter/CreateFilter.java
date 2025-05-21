package collection.filter;

import java.util.Arrays;

public class CreateFilter {

    public <T extends Number> T[] filter(T[] coll, Filter<T> filter) {

        for (int i = 0; i < coll.length; i++) {
            T t = filter.apply(coll[i]);
            coll[i] = t;
        }
        System.out.println(Arrays.toString(coll));
        return coll;
    }
}
