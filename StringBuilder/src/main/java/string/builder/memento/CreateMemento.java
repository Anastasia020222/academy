package string.builder.memento;

import string.builder.sb.CustomStringBuilder;

import java.util.Stack;

public class CreateMemento { //хранение истории состояний

    private final Stack<IMemento> history;
    CustomStringBuilder customStringBuilder;

    public CreateMemento(CustomStringBuilder getCustomStringBuilder) {
        this.customStringBuilder = getCustomStringBuilder;
        history = new Stack<>();
    }

    public void backup() {
        history.push(customStringBuilder.save());
    }

    public void undo() {
        if (history.isEmpty()) {
            return;
        }
        customStringBuilder.restore(history.pop());
    }
}
