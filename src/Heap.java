public class Heap implements MyHeap {
	
	private Node[] heapArr;
	private int maxSize; // size of array
	private int currSize; // number of nodes in array
	Node root;
	
	public Heap(int max) {
		heapArr = new Node[max];
		maxSize = max;
		currSize = 0;
		root = null;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Node makeHeap(Comparable value) {
		
		return null;
	}
	
	@Override
	public boolean isEmpty() {
		if (currSize == 0) {
			return true;
		} else
			return false;
	}
	
	@Override
	public boolean deleteMin() {
		@SuppressWarnings("unused")
		Node keyItem = heapArr[0];
		
		try {
			return delete(0);
		} catch (Exception ex) {
			return false;
		}
		
	}
	
	@SuppressWarnings("unused")
	public boolean delete(int position) throws Exception {
		if (isEmpty())
			throw new Exception("List is empty.");
		
		Node item = heapArr[position];
		
		heapArr[position] = heapArr[currSize - 1];
		
		currSize--;
		resizeHeap(position);
		
		return true;
	}
	
	@SuppressWarnings("unchecked")
	private void resizeHeap(int position) {
		int child;
		Node tmp = heapArr[position];
		while (kthChild(position, 1) < currSize) {
			child = minChild(position);
			if (heapArr[child].getData().compareTo(tmp.getData()) < 0)
				heapArr[position] = heapArr[child];
			else
				break;
			position = child;
		}
		heapArr[position] = tmp;
	}
	
	/** Function to get smallest child **/
	@SuppressWarnings("unchecked")
	private int minChild(int ind) {
		int bestChild = kthChild(ind, 1);
		int k = 2;
		int pos = kthChild(ind, k);
		while ((k <= 2) && (pos < currSize)) {
			if (heapArr[pos].getData().compareTo(heapArr[bestChild].getData()) < 0)
				bestChild = pos;
			pos = kthChild(ind, k++);
		}
		return bestChild;
	}
	
	private int kthChild(int i, int k) {
		return 2 * i + k;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean decreaseKey(Node key, Comparable updateValue) {
		
		return false;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean delete(Node del) {
		int position = -1;
		
		for (int i = 0; i < currSize; i++)
		{
			if (heapArr[i].getData().compareTo(del.getData()) == 0)
			{
				position = i;
			}
		}
		
		if (position >=0)
		{
			try {
				return delete(position);
			} catch (Exception ex) {
				return false;
			}
		}
		
		System.out.print("Item does not exist on heap.");

		return false;
	}
	
	@Override
	public boolean union(MyHeap heap) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Comparable findMin() {
		return (Comparable) heapArr[1];
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean insert(Comparable value) {
		if (currSize == maxSize) // if heap is full
			return false;
		
		heapArr[currSize] = new Node(value);
		currSize++;
		// adjust heap
		sortHeap(currSize - 1);
		
		return true;
	}
	
	public void printHeap() {
		System.out.print("\nHeap = ");
		for (int i = 0; i < currSize; i++)
			System.out.print(heapArr[i].getData() + " ");
		System.out.println();
	}
	
	@SuppressWarnings("unchecked")
	private void sortHeap(int location) {
		Node tmp = heapArr[location];
		while (location > 0 && tmp.getData().compareTo((heapArr[parent(location)]).getData()) < 0) {
			heapArr[location] = heapArr[parent(location)];
			location = parent(location);
		}
		heapArr[location] = tmp;
	}
	
	private int parent(int i) {
		return (i - 1) / 2;
	}
}
