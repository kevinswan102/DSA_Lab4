public class Main {
	public static void main(String[] args) throws Exception {
		
		System.out.println("-------------------------------------");
		System.out.println("AVL TREE");
		System.out.println("-------------------------------------");
		
		System.out.println("");
		AVLTree tree = new AVLTree();
		
		tree = AVLTree.insert(tree, new Node(10));
		tree = AVLTree.insert(tree, new Node(20));
		tree = AVLTree.insert(tree, new Node(60));
		tree = AVLTree.insert(tree, new Node(40));
		tree = AVLTree.insert(tree, new Node(50));
		tree = AVLTree.insert(tree, new Node(30));
		tree = AVLTree.insert(tree, new Node(25));
		tree = AVLTree.insert(tree, new Node(55));
		
		System.out.println("Root - " + tree.root.getData());
		System.out.println("-------------------------------------");
		
		System.out.println("Printing in order");
		AVLTree.inOrder(tree);
		System.out.println("-------------------------------------");
		
		System.out.println("Search for item - 30");
		System.out.println(AVLTree.search(tree, 10));
		System.out.println("-------------------------------------");
		
		System.out.println("Search for item - 15");
		System.out.println(AVLTree.search(tree, 15));
		System.out.println("-------------------------------------");
		
		System.out.println("Delete item - 40 & print new list of items");
		AVLTree.delete(tree, 40);
		System.out.println("New Root - " + tree.root.getData());
		AVLTree.inOrder(tree);
		System.out.println("-------------------------------------");
		
		System.out.println("Delete item - 15 & print new list of items");
		AVLTree.delete(tree, 15);
		System.out.println("New Root - " + tree.root.getData());
		AVLTree.inOrder(tree);
		System.out.println("-------------------------------------");
		
		System.out.println("Delete item - 40 & print new list of items");
		AVLTree.delete(tree, 40);
		System.out.println("New Root - " + tree.root.getData());
		AVLTree.inOrder(tree);
		System.out.println("-------------------------------------");
		
		System.out.println("Count between 15 - 25");
		System.out.println(AVLTree.count(tree, 15, 25));
		System.out.println("-------------------------------------");
		
		System.out.println("Count between 0 - 5");
		System.out.println(AVLTree.count(tree, 0, 5));
		System.out.println("-------------------------------------");
		
		System.out.println("");
		System.out.println("-------------------------------------");
		System.out.println("Heap");
		System.out.println("-------------------------------------");
		
		System.out.println("");
		Heap heapObj = new Heap(10);
		
		heapObj.insert(10);
		heapObj.insert(20);
		heapObj.insert(60);
		heapObj.insert(40);
		heapObj.insert(50);
		heapObj.insert(25);
		heapObj.insert(35);
		
		System.out.println("Printing items on heap");
		heapObj.printHeap();
		System.out.println("-------------------------------------");
		
		System.out.println("Delete min");
		System.out.println(heapObj.deleteMin());
		System.out.println("Printing items on heap");
		heapObj.printHeap();
		System.out.println("-------------------------------------");
		
		System.out.println("Delete 35");
		System.out.println(heapObj.delete(new Node(35)));
		System.out.println("Printing items on heap");
		heapObj.printHeap();
		System.out.println("-------------------------------------");
		
		System.out.println("Delete 35");
		System.out.println(heapObj.delete(new Node(35)));
		System.out.println("Printing items on heap");
		heapObj.printHeap();
		System.out.println("-------------------------------------");
	}
	
}