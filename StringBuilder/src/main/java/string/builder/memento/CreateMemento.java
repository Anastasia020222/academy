package string.builder.memento;

import string.builder.sb.CustomStringBuilder;

import java.util.Stack;

public class CreateMemento { //хранение истории состояний, отмена изменений

    private final Stack<Memento> history;
    CustomStringBuilder customStringBuilder;

    public CreateMemento(CustomStringBuilder getCustomStringBuilder) {
        this.customStringBuilder = getCustomStringBuilder;
        history = new Stack<>();
    }

    public void backup() {
        history.push(customStringBuilder.save());
    }

    public void undo() {
        if (!history.isEmpty()) {
            customStringBuilder.restore(history.pop());
        } else {
            System.out.println("Нечего откатывать.");
        }
    }
}
