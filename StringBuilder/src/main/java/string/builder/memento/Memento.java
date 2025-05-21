package string.builder.memento;

import java.util.Arrays;

public class Memento implements IMemento {

    private final char[] state;
    private final int count;

    public Memento(char[] state, int count) {
        this.state = Arrays.copyOf(state, state.length);
        this.count = count;
    }

    @Override
    public char[] getState() {
        return Arrays.copyOf(state, state.length);
    }

    @Override
    public int getCount() {
        return count;
    }
}
