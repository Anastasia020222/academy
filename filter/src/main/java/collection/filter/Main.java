package collection.filter;

public class Main {

    public static void main(String[] args) {
        CreateFilter createFilter = new CreateFilter();
        Filter<Integer> f = new Filter();

        Integer[] masI = new Integer[]{3, 4, 5};
        //Long[] masD = new Long[]{4L, 41L};

        createFilter.filter(masI, f);
    }
}