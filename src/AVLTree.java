
public class AVLTree {
  Node root;

  int height(Node N) {
    if (N == null)
      return 0;

    return N.getHeight();
  }

  static AVLTree insert(AVLTree sourceTree, Node newNode) throws Exception {
    AVLTree newTree = new AVLTree();

    // make sure new Node has no default values other than data
    if (newNode == null) {
      throw new Exception("Node must be a valid object with data");
    }

    if (sourceTree.root == null) {
      newTree.root = newNode;

      return newTree;
    }

    // start comparison
    newTree.root = sourceTree.insertNode(sourceTree.root, newNode);
   
    return newTree;
  }

  @SuppressWarnings("rawtypes")
  static AVLTree delete(AVLTree sourceTree, Comparable value) throws Exception {
    AVLTree newTree = new AVLTree();


    if (sourceTree.root == null) {
      throw new Exception("Tree is empty");
    }

    newTree.root = sourceTree.deleteNode(sourceTree.root, value);
   
    return newTree;
  }
  
  @SuppressWarnings({ "unchecked", "rawtypes" })
  static boolean search(AVLTree sourceTree, Comparable value) {

    boolean exists = false;
    
    Node n = sourceTree.root;
    
    while ((n != null) && !exists)
    {
        if (n.getData().compareTo(value) < 0)
            n = n.getRightChild();
        else if (n.getData().compareTo(value) > 0)
          n = n.getLeftChild();
        else
        {
            exists = true;
            break;
        }
    }
    
    return exists;
  }
  
  @SuppressWarnings({ "rawtypes", "unchecked" })
  static int count(AVLTree sourceTree, Comparable startValue, Comparable endValue) {
    
    int itemExists = 0;
    
    if (sourceTree.root == null)
      return 0;
    
    Node n = sourceTree.root;
    itemExists += inOrderCount(n, startValue, endValue);

    if (n.getData().compareTo(startValue) >= 0 && n.getData().compareTo(endValue) <= 0)
      itemExists += 1;
    
    return itemExists;
  }  

  @SuppressWarnings({ "rawtypes", "unchecked" })
  static int inOrderCount(Node node, Comparable startValue, Comparable endValue) {
    int count =0;
    
    if (node != null) {
      count += inOrderCount(node.getLeftChild(), startValue, endValue);
      
      if (node.getData().compareTo(startValue) >= 0 && node.getData().compareTo(endValue) <= 0)
      {
        count += 1;
      }

      count += inOrderCount(node.getRightChild(), startValue, endValue);
    }
    
    return count;
  }
  
  @SuppressWarnings("unchecked")
  Node insertNode(Node node, Node newNode) {

    if (node == null)
      return (new Node(newNode.getData()));

    // if current is greater than input then send to left
    if (node.getData().compareTo(newNode.getData()) > 0) {
      //if (node.getLeftChild() != null)
        node.setLeftChild(insertNode(node.getLeftChild(), newNode));
      //else
        //node.setLeftChild(newNode);
    } else {
      //if (node.getRightChild() != null)
        node.setRightChild(insertNode(node.getRightChild(), newNode));
      //else
        //node.setRightChild(newNode);
    }

    //System.out.println("Height - " + maxHeight(node));

    // adjust the height to new height
    node.setHeight(1 + maxHeight(node));

    // get the balance factor, then handle if it is unbalanced
    int balance = getBalance(node);
    //System.out.println("Balance - " + getBalance(node));

    // according to definition if difference of balance is > +/-1 then we need to
    // rotate
    if (balance > 1) {
      if (newNode.getData().compareTo(node.getLeftChild().getData()) < 0)
        return rightRotate(node);
      
      if (newNode.getData().compareTo(node.getLeftChild().getData()) > 0)
      {
        node.setLeftChild(leftRotate(node.getLeftChild()));
        return rightRotate(node);
      }
      
    } else if (balance < -1) {
      if (newNode.getData().compareTo(node.getRightChild().getData()) > 0)
        return leftRotate(node);
      
      if (newNode.getData().compareTo(node.getRightChild().getData()) < 0)
      {
        node.setRightChild(rightRotate(node.getRightChild()));
        return leftRotate(node);
      }
    }
    
    
    return node;
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  Node deleteNode(Node node, Comparable valueToDelete)
  {

	  if (node == null)
          return node;

      // If the value to be deleted is smaller than
      // the node value, then it lies in left subtree
      if (node.getData().compareTo(valueToDelete) > 0)
          node.setLeftChild(deleteNode(node.getLeftChild(), valueToDelete));

      // If the value to be deleted is greater than the
      // node value, then it lies in right subtree
      else if (node.getData().compareTo(valueToDelete) < 0)
        node.setRightChild(deleteNode(node.getRightChild(), valueToDelete));

      // to be deleted
      else
      {
          // node with only one child or no child
          if ((node.getLeftChild() == null) || (node.getRightChild() == null))
          {
              Node temp = null;
              if (temp == node.getLeftChild())
                  temp = node.getRightChild();
              else
                  temp = node.getLeftChild();

              if (temp == null)
              {
                  temp = node;
                  node = null;
              }
              else
                  node = temp;
          }
          else
          {

              // node with two children: Get the successor (smallest in the right subtree)
              Node temp = smallestNode(node.getRightChild());

              // Copy the inorder successor's data to this node
              node.setData(temp.getData());

              // Delete the successor
              node.setRightChild(deleteNode(node.getRightChild(), temp.getData()));
          }
      }

      // If the tree had only one node then return
      if (node == null)
          return node;

      //UPDATE HEIGHT OF THE CURRENT NODE
      node.setHeight(1 + maxHeight(node));

      //GET THE BALANCE FACTOR OF THIS NODE (to check whether
      //  this node became unbalanced)
      int balance = getBalance(node);

      // If this node becomes unbalanced, then there are 4 cases
      // Left Left Case
      if (balance > 1 && getBalance(node.getLeftChild()) >= 0)
          return rightRotate(node);

      // Left Right Case
      if (balance > 1 && getBalance(node.getLeftChild()) < 0)
      {
          node.setLeftChild(leftRotate(node.getLeftChild()));
          return rightRotate(node);
      }

      // Right Right Case
      if (balance < -1 && getBalance(node.getRightChild()) <= 0)
          return leftRotate(node);

      // Right Left Case
      if (balance < -1 && getBalance(node.getRightChild()) > 0)
      {
        node.setRightChild(rightRotate(node.getRightChild()));
          return leftRotate(node);
      }

      return node;
  }
  
  //left rotate subtree rooted with y
  Node rightRotate(Node node) {
    Node x = node.getLeftChild();
    Node x1 = x.getRightChild();

    // Perform rotation
    x.setRightChild(node);
    node.setLeftChild(x1);

    // Update heights
    node.setHeight(1 + maxHeight(node));
    x.setHeight(1 + maxHeight(x));

    // Return new root
    return x;
  }

  //left rotate subtree rooted with x
  Node leftRotate(Node node) {
    Node y = node.getRightChild();
    Node y1 = y.getLeftChild();

    // Perform rotation
    y.setLeftChild(node);
    node.setRightChild(y1);

    // Update heights
    y.setHeight(1 + maxHeight(y));
    node.setHeight(1 + maxHeight(node));

    // Return new root
    return y;
  }

  int maxHeight(Node x) {

    if (x.getLeftChild() != null && x.getRightChild() != null) {
      return (x.getLeftChild().getHeight() > x.getRightChild().getHeight() ? x.getLeftChild().getHeight()
          : x.getRightChild().getHeight());
    } else if (x.getLeftChild() != null && x.getRightChild() == null) {
      return x.getLeftChild().getHeight();
    } else if (x.getLeftChild() == null && x.getRightChild() != null) {
      return x.getRightChild().getHeight();
    }

    return 0;
  }

  int getBalance(Node x) {

    if (x.getLeftChild() != null && x.getRightChild() != null) {
      return (x.getLeftChild().getHeight() - x.getRightChild().getHeight());
    } else if (x.getLeftChild() != null && x.getRightChild() == null) {
      return x.getLeftChild().getHeight();
    } else if (x.getLeftChild() == null && x.getRightChild() != null) {
      return 0 - x.getRightChild().getHeight();
    }

    return 0;
  }
  
  Node smallestNode(Node node)
  {
      Node current = node;

      /* loop down to find the leftmost leaf */
      while (current.getLeftChild() != null)
         current = current.getLeftChild();

      return current;
  }

  static void inOrder(AVLTree tree) throws Exception {
    if (tree.root == null)
      throw new Exception("AVL Tree is empty");

    tree.inOrderTraverse(tree.root);
    System.out.print("\n");
  }

  void inOrderTraverse(Node node) {
    if (node != null) {
      inOrderTraverse(node.getLeftChild());
      System.out.print(node.getData() + " ");
      inOrderTraverse(node.getRightChild());
    }
  }
}
