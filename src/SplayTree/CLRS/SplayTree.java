package SplayTree.CLRS;

/**
 * Splay Tree is a self-adjusting search tree
 * Created by Fanghz on 3/8/14.
 */
public class SplayTree {

    SplayNode root; // the root node of the SplayTree

    /**
     * Construct a new Splay Tree.
     *
     */
    public SplayTree() {
        root = null;
    }

    /**
     * The insert method performs a insertion into the splay tree.
     * First splay the tree, set the last visited node as the root.
     * Then split the splay tree as two trees, set the new node as the root.
     *
     * @param value the value of the new node
     * @throws Exception
     */
    public void insert(int value) throws Exception {
        SplayNode temp = new SplayNode(value);
        if(root == null) {
            root = new SplayNode(value);
        } else {
            splay(value);
            if(root.getValue() == value) {
                throw new Exception("duplicate value!");
            } else if (root.getValue() > value) {
                //split the splay tree with right subtree including root, and set the new node as root

                temp.setLeft(root.getLeft());
                temp.setRight(root);
                root.setLeft(null);
                root = temp;
            } else {
                // split the splay tree with left subtree including root, and set the new node as root
                temp.setLeft(root);
                temp.setRight(root.getRight());
                root.setRight(null);
                root = temp;
            }
        }
    }

    /**
     * The find method look for the node with the given value.
     * @param value the given value
     * @return true if found, false otherwise.
     * @throws Exception
     */
    public boolean find(int value) throws Exception {
        if(root == null) throw new Exception("Tree is Empty.");
        splay(value);
        if(root.getValue() == value) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Find the max node in the splay tree.
     * @param node
     * @return the node contains the max value of the tree
     * @throws Exception
     */
    public SplayNode findMax(SplayNode node) throws Exception {
        if(node== null) throw new Exception("Tree is Empty");
        node = splay(node, Integer.MAX_VALUE);
        return node;
    }

    /**
     * Find the max value in the splay tree
     * @return the max value in the splay tree
     * @throws Exception
     */
    public int findMax() throws Exception {
        root = findMax(this.root);
        return root.getValue();
    }

    /**
     * Find the min node in the splay tree.
     * @param node
     * @return the node contains the min value of the tree
     * @throws Exception
     */
    public SplayNode findMin(SplayNode node) throws Exception {
        if(node== null) throw new Exception("Tree is Empty");
        node = splay(node, Integer.MIN_VALUE);
        return node;
    }

    /**
     * Find the min value in the splay tree
     * @return the min value in the splay tree
     * @throws Exception
     */
    public int findMin() throws Exception {
        root = findMin(this.root);
        return root.getValue();
    }

    /**
     * Delete the minimum value in the splay tree.
     * After finding the minimum value of the splay tree, the minimum value is on the root of the splay tree, and the root does not have a left child/
     *
     * @return the minimum value in the splay tree
     * @throws Exception
     */
    public int deleteMin() throws Exception {
        int min = findMin();
        root = root.getRight();
        return min;
    }

    /**
     * Delete the max value in the splay tree.
     * After finding the max value of the splay tree, the max value is on the root of the splay tree, and the root does not have a right child/
     * @return the max value in the splay tree
     * @throws Exception
     */
    public int deleteMax() throws Exception {
        int max = findMax();
        root = root.getLeft();
        return max;
    }

    /**
     * Delete the given value from the splay tree.
     * Splay the value, if the value is not equal to the root value, then the value does not exist in the tree.
     * If the left subtree of the splay tree is null, then the x is the minimum node in the tree, delete the root, set the right child as the new root.
     * If the left subtree of the splay tree is not null, then find max node in the left subtree, set the max value as the new root, delete the root.
     * @param value
     * @throws Exception
     */
    public void remove(int value) throws Exception {
        if(root == null) throw new Exception("Tree is Empty");
        splay(value);
        if(value != root.getValue()) {
            throw new Exception ("value not found");
        }
        if(root.getLeft() == null) {
            // root is the min node
            root = root.getRight();
        } else {
            SplayNode leftSubTreeRoot = findMax(root.getLeft());
            leftSubTreeRoot.setRight(root.getRight());
            root = leftSubTreeRoot;
        }
    }

    /**
     * Zig-Zig
     * @param grandParent
     * @param parent
     */
    private void rotateLeftChild(SplayNode grandParent, SplayNode parent) {
        grandParent.setLeft(parent.getRight());
        parent.setRight(grandParent);
        //split the parent with the middle tree
        parent.setLeft(null);
    }

    /**
     * Zag-Zag
     * @param grandParent
     * @param parent
     */
    private void rotateRightChild(SplayNode grandParent, SplayNode parent) {
        grandParent.setRight(parent.getLeft());
        parent.setLeft(grandParent);
        //split the parent with the middle tree
        parent.setRight(null);
    }

    /**
     * Splay the tree
     * @param value
     */
    public void splay(int value) {
        root = splay(root, value);
    }

    private SplayNode splay(SplayNode node, int value) {
        if(node == null) {
            return null;
        }
        SplayNode pseudoNode = new SplayNode(Integer.MAX_VALUE);
        SplayNode leftMax = pseudoNode;
        SplayNode rightMin = pseudoNode;
        SplayNode t = node;
        while(true) {
            if(value == t.getValue()) {
                break;
            } else if(value < t.getValue()) {
                SplayNode parent = t.getLeft();
                if(parent == null) {
                    break;
                } else {
                    if(value < parent.getValue()) {
                        if(parent.getLeft() == null){
                            // zig
                            t.setLeft(null);
                            rightMin.setLeft(t);
                            rightMin = t;
                            t = parent;
                        } else {
                            // zig-zig
                            SplayNode temp = parent.getLeft();
                            rotateLeftChild(t, parent);
                            rightMin.setLeft(parent);
                            rightMin = parent;
                            //update the middle tree's root
                            t = temp;
                        }
                    } else {
                        //ie. value > t.left.value
                        //zig-zag
                        t.setLeft(null);
                        rightMin.setLeft(t);
                        rightMin = t;
                        t = parent;
                    }
                }
            } else {
                // ie value > t.getValue()
                SplayNode parent = t.getRight();
                if(parent == null) {
                    break;
                } else {
                    if(value > parent.getValue()) {
                        if(parent.getRight() == null) {
                            //zag
                            t.setRight(null);
                            leftMax.setRight(t);
                            leftMax = t;
                            t = parent;
                        } else {
                            //zag-zag
                            SplayNode temp = parent.getRight();
                            rotateRightChild(t, parent);
                            // update left tree and its max node
                            leftMax.setRight(parent);
                            leftMax = parent;
                            //update the middle tree's root
                            t = temp;
                        }
                    } else {
                        //ie x <= t.right.value
                        //zag or zag-zig
                        t.setRight(null);
                        leftMax.setRight(t);
                        leftMax = t;
                        t = parent;
                    }
                }
            }
        }
        leftMax.setRight(t.getLeft());
        rightMin.setLeft(t.getRight());

        t.setLeft(pseudoNode.getRight());
        t.setRight(pseudoNode.getLeft());
        return t;
    }
}
