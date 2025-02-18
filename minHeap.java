import java.util.ArrayList;

// Generic MinHeap implementation
class MinHeap<T extends Comparable<T>> {
    private final ArrayList<T> heap;

    public MinHeap() {
        this.heap = new ArrayList<>();
    }

    // Get parent index of a node
    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    // Get left child index of a node
    private int getLeftChildIndex(int index) {
        return (2 * index) + 1;
    }

    // Get right child index of a node
    private int getRightChildIndex(int index) {
        return (2 * index) + 2;
    }

    // Swap two elements in the heap
    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    // Move an element down to maintain heap property (heapify down)
    private void heapifyDown(int index) {
        int smallest = index;
        int left = getLeftChildIndex(index);
        int right = getRightChildIndex(index);

        // Find the smallest element among parent, left, and right child
        if (left < heap.size() && heap.get(left).compareTo(heap.get(smallest)) < 0) {
            smallest = left;
        }
        if (right < heap.size() && heap.get(right).compareTo(heap.get(smallest)) < 0) {
            smallest = right;
        }

        // Swap and continue heapifying if needed
        if (smallest != index) {
            swap(index, smallest);
            heapifyDown(smallest);
        }
    }

    // Move an element up to maintain heap property (heapify up)
    private void heapifyUp(int index) {
        int parent = getParentIndex(index);
        if (index > 0 && heap.get(index).compareTo(heap.get(parent)) < 0) {
            swap(index, parent);
            heapifyUp(parent);
        }
    }

    // Insert a new element into the heap
    public void insert(T value) {
        heap.add(value);   // Add at the end
        heapifyUp(heap.size() - 1); // Adjust position to maintain heap
    }

    // Remove and return the root (smallest element)
    public T pop() {
        if (heap.isEmpty()) return null;

        T root = heap.get(0);  // Store root element
        T lastItem = heap.remove(heap.size() - 1); // Remove last element

        // If heap is not empty, move last item to root and heapify down
        if (!heap.isEmpty()) {
            heap.set(0, lastItem);
            heapifyDown(0);
        }
        return root;
    }

    // Build a MinHeap from an existing array
    public void buildMinHeap(ArrayList<T> arr) {
        heap.clear();
        heap.addAll(arr);
        // Heapify all non-leaf nodes
        for (int i = getParentIndex(heap.size() - 1); i >= 0; i--) {
            heapifyDown(i);
        }
    }

    // Display heap as a list
    public void displayHeap() {
        System.out.println(heap);
    }

    // Get heap size
    public int getSize() {
        return heap.size();
    }
}

// Custom class representing a Person with age-based comparison
class Person implements Comparable<Person> {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Compare based on age (MinHeap prioritizes youngest person)
    @Override
    public int compareTo(Person other) {
        return Integer.compare(this.age, other.age);
    }

    @Override
    public String toString() {
        return name + "(" + age + ")";
    }
}

// Main class to demonstrate MinHeap functionality
public class Main {
    public static void main(String[] args) {
        // Integer MinHeap
        MinHeap<Integer> intHeap = new MinHeap<>();
        System.out.println("Adding Integer elements:");
        intHeap.insert(10);
        intHeap.insert(20);
        intHeap.insert(5);
        intHeap.insert(15);
        intHeap.insert(1);
        intHeap.displayHeap();

        System.out.println("Removing the minimum element from Integer heap:");
        System.out.println("Removed element: " + intHeap.pop());
        intHeap.displayHeap();

        // Building MinHeap from an integer array
        ArrayList<Integer> intArr = new ArrayList<>();
        intArr.add(40);
        intArr.add(25);
        intArr.add(30);
        intArr.add(35);
        intArr.add(50);

        System.out.println("Building Integer heap from array:");
        intHeap.buildMinHeap(intArr);
        intHeap.displayHeap();

        // Float MinHeap
        MinHeap<Float> floatHeap = new MinHeap<>();
        System.out.println("\nAdding Float elements:");
        floatHeap.insert(10.5f);
        floatHeap.insert(20.1f);
        floatHeap.insert(5.2f);
        floatHeap.insert(15.6f);
        floatHeap.insert(1.3f);
        floatHeap.displayHeap();

        System.out.println("Removing the minimum element from Float heap:");
        System.out.println("Removed element: " + floatHeap.pop());
        floatHeap.displayHeap();

        // Building MinHeap from a float array
        ArrayList<Float> floatArr = new ArrayList<>();
        floatArr.add(40.7f);
        floatArr.add(25.9f);
        floatArr.add(30.4f);
        floatArr.add(35.2f);
        floatArr.add(50.8f);

        System.out.println("Building Float heap from array:");
        floatHeap.buildMinHeap(floatArr);
        floatHeap.displayHeap();

        // MinHeap using a custom class (Person)
        System.out.println("\n=== Custom Class MinHeap Example ===");
        MinHeap<Person> personHeap = new MinHeap<>();
        personHeap.insert(new Person("Parin", 30));
        personHeap.insert(new Person("Krushang", 25));
        personHeap.insert(new Person("Rahil", 35));
        personHeap.insert(new Person("Poojan", 20));
        personHeap.insert(new Person("Malav", 40));

        personHeap.displayHeap();
        System.out.println("Popping root: " + personHeap.pop());
        personHeap.displayHeap();
    }
}

