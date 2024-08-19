public class CircularQueue<T> {
    private int top = -1;
    private int base = 0;
    private T[] data;

    // Construtor
    public CircularQueue(int size) {
        data = (T[]) new Object[size];
    }

    // Adiciona um elemento na fila
    public void add(T value) {
        if (isFull()) {
            System.out.println("Fila cheia. Não é possível adicionar o elemento.");
            return;
        }
        // Se o top for -1 (fila vazia), mova para a primeira posição
        if (top == -1) {
            top = 0;
            base = 0;
        } else {
            top = move(top);
        }
        data[top] = value;
    }

    // Remove um elemento da fila
    public T remove() {
        if (isEmpty()) {
            System.out.println("Fila vazia. Não é possível remover elementos.");
            return null;
        }
        T value = data[base];
        data[base] = null;
        base = move(base);

        // Se a fila estiver vazia após a remoção, redefina os índices
        if (base == top + 1 || (top == data.length - 1 && base == 0)) {
            top = -1;
            base = 0;
        }
        return value;
    }

    // Limpa a fila
    public void clear() {
        top = -1;
        base = 0;
        data = (T[]) new Object[data.length];
    }

    // Verifica se a fila está cheia
    public boolean isFull() {
        return top != -1 && move(top) == base;
    }

    // Verifica se a fila está vazia
    public boolean isEmpty() {
        return top == -1;
    }

    // Move para a próxima posição na fila circular
    private int move(int position) {
        return (position + 1) % data.length;
    }

    // Método principal para testes
    public static void main(String[] args) {
        CircularQueue<Integer> queue = new CircularQueue<>(5);

        // Testando adicionar elementos
        queue.add(10);
        queue.add(20);
        queue.add(30);
        queue.add(40);
        queue.add(50);

        // Testando remoção
        System.out.println("Removido: " + queue.remove());
        System.out.println("Removido: " + queue.remove());

        // Testando adicionar novamente após remoção
        queue.add(60);
        queue.add(70);

        // Testando esvaziar a fila
        while (!queue.isEmpty()) {
            System.out.println("Removido: " + queue.remove());
        }
    }
}
