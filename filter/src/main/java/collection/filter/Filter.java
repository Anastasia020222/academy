package collection.filter;

public class Filter<T extends Number> implements IFilter<T> {

    @Override
    public T apply(T o) {
        Number i = 0;
        if (o.getClass() == Integer.class) {
            i = o.intValue() * 2;
        }
        if (o.getClass() == Double.class) {
            i = o.doubleValue() * 2;
        }
        if (o.getClass() == Long.class) {
            i = o.longValue() * 2;
        }
        return (T) i;
    }
}
