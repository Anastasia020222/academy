package string.builder;

import string.builder.memento.CreateMemento;
import string.builder.sb.CustomStringBuilder;

public class Main {
    public static void main(String[] args) {
        CustomStringBuilder csb = new CustomStringBuilder();
        CreateMemento createMemento = new CreateMemento(csb);

        csb.append("Hello");
        System.out.println(csb);

        createMemento.backup();

        System.out.println("Изменение");
        csb.deleteCharAt(2);
        System.out.println(csb);

        System.out.println("Восстановление");
        createMemento.undo();
        System.out.println(csb);
    }
}