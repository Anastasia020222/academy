package string.builder.sb;

import string.builder.memento.IMemento;
import string.builder.memento.Memento;

public class CustomStringBuilder implements ICustomStringBuilder { //основной объект Originator

    private char[] value;
    private int count; //количество задействованных символов в массиве

    int DEFAULT_SIZE = 16;

    public CustomStringBuilder() {
        value = new char[DEFAULT_SIZE];
        count = 0;
    }

    public CustomStringBuilder(int size) {
        value = new char[size];
        count = 0;
    }

    @Override
    public CustomStringBuilder append(String str) {
        checkingSize(str.length());
        for (int i = 0; i < str.length(); i++) {
            value[count++] = str.charAt(i);
        }
        return this;
    }

    @Override
    public CustomStringBuilder delete(int start, int end) {
        if (end > count) {
            throw new IndexOutOfBoundsException("Индекс превышает длину строки: " + end);
        }
        for (int i = 0; i < count - end; i++) {
            value[start + i] = value[end + i];
        }
        count = count - (end - start);
        return this;
    }

    @Override
    public CustomStringBuilder deleteCharAt(int index) {
        if (index > count) {
            throw new IndexOutOfBoundsException("Индекс превышает длину строки: " + index);
        }
        for (int i = index; i < count - 1; i++) {
            value[i] = value[i + 1];
        }
        count--;
        return this;
    }

    public void checkingSize(int sizeStr) {
        int capacity = sizeStr + count;
        if (capacity >= value.length) {
            int newCapacity = value.length * 2 + 2;
            char[] newBuffer = new char[newCapacity];
            if (count != 0) {
                for (int i = 0; i < value.length - 1; i++) {
                    newBuffer[i] = value[i];
                }
            }
            value = newBuffer;
        }
    }

    public Memento save() {
        return new Memento(value, count);
    }

    public void restore(IMemento iMemento) {
        this.value = iMemento.getState();
        this.count = iMemento.getCount();
    }

    public int length() {
        return count;
    }

    @Override
    public String toString() {
        return new String(value, 0, count);
    }
}
