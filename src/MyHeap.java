
public interface MyHeap {
    @SuppressWarnings("rawtypes")
    public Node makeHeap(Comparable value);
    public boolean isEmpty();
    @SuppressWarnings("rawtypes")
    public boolean insert(Comparable value);
    public boolean deleteMin();
    @SuppressWarnings("rawtypes")
    public boolean decreaseKey(Node key, Comparable updateValue);
    public boolean delete(Node del);
    public boolean union(MyHeap heap);
    @SuppressWarnings("rawtypes")
    public Comparable findMin();
}