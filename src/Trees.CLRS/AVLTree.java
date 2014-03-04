package Trees.CLRS;

/**
 * An AVL tree (Adelson-Velskii and Landis' tree, named after the inventors) is a self-balancing binary search tree.
 * @wikipedia : http://en.wikipedia.org/wiki/AVL_tree
 *
 * Created by Fanghz on 3/1/14.
 * @version 1.0
 */
public class AVLTree {
    AVLNode root; // the root of the AVL tree
    private int size = 0; // the size of the AVL tree

    /**
     * This constructor creates an empty AVLTree
     */
    public AVLTree() {
        root = null;
    }

    /**
     *
     * This method returns the number of values inserted into the tree.
     * @return number of values inserted into the tree
     *
     */
    public int getSize() {
        return size;
    }

    /**
     * This method performs an inorder traversal of the tree. The inOrderTraversal(RedBlackNode) method is recursive and displays the content of the tree.
     * @param node
     */

    public void inOrderTraversal (AVLNode node) {
        if(node == null) {
            return;
        }
        inOrderTraversal(node.getLc());
        System.out.println("key = " + node.getKey());
        inOrderTraversal(node.getRc());
    }


    /**
     * The no argument inOrderTraversal() method calls the recursive inOrderTraversal(RedBlackNode) passing the root
     */
    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    /**
     * The insert() method places a node into the tree
     * Time Complexity: O(log n)
     *
     * @param Node the node to compare with the key
     * @param key the value to be inserted
     */

    public AVLNode insert(int key, AVLNode Node) {
        if(Node == null) {
             Node = new AVLNode(key,null,null);
        } else if(key < Node.getKey()) {
            Node.setLc(insert(key, Node.getLc()));
            if(height(Node.getLc()) - height(Node.getRc()) == 2) {
                if(key < Node.getLc().getKey()) {
                    Node = LLRotate(Node);
                } else {
                    Node = LRRotate(Node);
                }
            }
        } else {
            Node.setRc(insert(key, Node.getRc()));
            if(height(Node.getRc()) - height(Node.getLc()) == 2) {
                if(key > Node.getRc().getKey()) {
                    Node = RRRotate(Node);
                } else {
                    Node = RLRotate(Node);
                }
            }
        }
        Node.setHeight(1+Math.max(height(Node.getLc()), height(Node.getRc())));
        size++;
        return Node;
    }

    /**
     * THe insert() method insert a key into the tree
     * @param key the value of the node
     */
    public void insert(int key) {
        root = insert(key, root);
    }

    /**
     * The LLRotate() method performs a single right rotation.
     * LLRotate means the node was inserted into the left child of the left subtree
     * @param Node the node need to be fixed
     * @return newNode on the top after right rotation
     */

    private AVLNode LLRotate(AVLNode Node) {
        AVLNode newNode = Node.getLc();
        Node.setLc(newNode.getRc());
        newNode.setRc(Node);
        Node.setHeight(Math.max(height(Node.getLc()), height(Node.getRc())));
        newNode.setHeight((Math.max(height(newNode.getLc()), height(newNode.getRc()))));
        return newNode;
    }

    /**
     * THe LRRotate() method performs a double rotation, first performs a left rotation, and then performs a right rotation
     * LRRotate means the node was inserted into the right child of the left subtree
     * @param Node the node need to be fixed
     * @return new Node on the top after double rotation
     */

    private AVLNode LRRotate(AVLNode Node) {
        Node.setLc(RRRotate(Node.getLc()));
        return LLRotate(Node);
    }

    /**
     * The RLRotate() method performs a double rotation, first performs a right rotation, and then performs a left rotation
     * RLRotate measn the node was inserted into the left child of the right subtree
     * @param Node the node need to be fixed
     * @return the new node on the top after double rotation
     */
    private AVLNode RLRotate(AVLNode Node) {
        Node.setRc(LLRotate(Node.getRc()));
        return RRRotate(Node);
    }

    /**
     * The RRRotate() method performs a single rotation.
     * RRRotate means the node was inserted into the right child of the right subtree
     * @param Node the node need to be fixed
     * @return the new node on the top after left rotation
     */
    private AVLNode RRRotate(AVLNode Node) {
        AVLNode newNode = Node.getRc();
        Node.setRc(newNode.getLc());
        newNode.setLc(Node);
        Node.setHeight(Math.max(height(Node.getLc()), height(Node.getRc())));
        newNode.setHeight((Math.max(height(newNode.getLc()), height(newNode.getRc()))));
        return newNode;
    }

    /**
     *  This revursive routine that is used to compute the height of the AVLTree.
     *  It is called by the height() method.
     * @param node the node
     * @return the height of the node
     */
    public int height(AVLNode node) {
       // int height = 0;
        if(node == null) {
            return 0;
        } else {
            return node.getHeight();
        }
    }


    /**
     * The height() method passes the root of the tree to this method.
     * @return the height of the AVLTree
     */
    public int height() {
        return height(root);
    }

    /**
     * The delete() method delete a node from the AVL tree, the tree still need to keep balanced
     *
     * @param Node
     * @param key the key should be deleted in the tree
     */
    public AVLNode delete(int key, AVLNode Node) {
        if(Node == null) {
            return null;
        }

        if(Node.getKey() == key) {
            if(Node.getRc() == null) {
                Node = Node.getLc();
            } else {
                AVLNode head = Node.getRc();
                while(head.getLc() != null) {
                    head = head.getLc();
                }
                Node.setKey(head.getKey());
                Node.setRc(delete(Node.getKey(), Node.getRc()));
                Node.setHeight(Math.max(height(Node.getLc()), height(Node.getRc())) + 1);
            }
            return Node;
        } else if(Node.getKey() > key) {
            Node.setLc(delete(key, Node.getLc()));
        } else {
            Node.setRc(delete(key, Node.getRc()));
        }
        Node.setHeight(1+Math.max(height(Node.getLc()), height(Node.getRc())));

        if(Node.getLc() != null) {
            Node.setLc(rotate(Node.getLc()));
        }
        if(Node.getRc() != null) {
            Node.setRc(rotate(Node.getRc()));
        }

        return rotate(Node);
    }

    /**
     * The delete() method delete a key from the tree
     * @param key
     */
    public void delete(int key) {
        if(size==1) {
            root = null;
            return;
        }
        delete(key, root);
    }

    /**
     * The private rotate method performs rotation after the node is inserted into the tree
     *
     * @param Node
     * @return
     */
    private AVLNode rotate(AVLNode Node) {
        if(height(Node.getLc()) - height(Node.getRc()) == 2) {
            if(height(Node.getLc().getLc()) >= height(Node.getLc().getRc())) {
                Node = LLRotate(Node);
            } else {
                Node = LRRotate(Node);
            }
        }
        if(height(Node.getRc()) - height(Node.getLc())== 2) {
            if(height(Node.getRc().getRc()) >= height(Node.getRc().getLc())) {
                Node = RRRotate(Node);
            } else {
                Node = RLRotate(Node);
            }
        }
        return Node;
    }
}
