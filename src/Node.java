public class Node {
    private Node left_child;
    private Node right_child;
    private Node parent;
    @SuppressWarnings("rawtypes")
    private Comparable data;
    
    private int height;
    
    public Node(){
        left_child = null;
        right_child = null;
        data = null;
        parent = null;
        height = 1;
    }

    @SuppressWarnings("rawtypes")
    public Node(Comparable data) {
        this.data = data;
        left_child = null;
        right_child = null;
        parent = null;
        height = 1;
    }
    
    @SuppressWarnings("rawtypes")
    public Node(Comparable data, Node left_child, Node right_child, Node parent) {
        this.data = data;
        this.left_child = left_child;
        this.right_child = right_child;
        this.parent = parent;
    }
    
    public Node getLeftChild() {
        return left_child;
    }

    public Node getRightChild() {
        return right_child;
    }

    @SuppressWarnings("rawtypes")
    public Comparable getData() {
        return data;
    }
    
    public int getHeight() {
        return height;
    }
    
    public Node getParent() {
        return parent;
    }

    public void setLeftChild(Node left_child) {
        this.left_child = left_child;
    }

    public void setRightChild(Node right_child) {
        this.right_child = right_child;
    }

    @SuppressWarnings("rawtypes")
    public void setData(Comparable data) {
        this.data = data;
    }
    
    public void setParent(Node parent) {
        this.parent = parent;
    }
    
    public void setHeight(int ht) {
      this.height = ht;
  }
}