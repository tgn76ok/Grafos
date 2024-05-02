package busca;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class PilhaArrayList<T> implements Pilha<T> {
    private final List<T> elementos;

    public PilhaArrayList() {
        this.elementos = new ArrayList<>();
    }

    @Override
    public T top() throws NoSuchElementException {
        if (isEmpty()) throw new NoSuchElementException();
        return elementos.get(elementos.size() - 1);
    }

    @Override
    public T pop() throws NoSuchElementException {
        if (isEmpty()) throw new NoSuchElementException();
        return elementos.remove(elementos.size() - 1);
    }

    @Override
    public void push(T element) {
        elementos.add(element);
    }

    @Override
    public boolean isEmpty() {
        return elementos.isEmpty();
    }
}
