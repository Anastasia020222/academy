package string.builder.memento;

public class Memento implements IMemento { //хранить внутреннее состояние

    private final char[] mass;
    private final int count;

    public Memento(char[] mass, int count) {
        this.mass = mass.clone();
        this.count = count;
    }

    @Override
    public char[] getState() {
        return mass.clone();
    }

    @Override
    public int getCount() {
        return count;
    }
}
