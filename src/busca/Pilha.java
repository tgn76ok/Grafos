package busca;

import java.util.NoSuchElementException;

public interface Pilha<T> {
    public T top() throws NoSuchElementException;
    public T pop() throws NoSuchElementException;
    public void push(T element);
    public boolean isEmpty();
}
