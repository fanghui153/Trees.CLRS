package SplayTree.CLRS;

/**
 * Created by Fanghz on 3/8/14.
 */
public class SplayNode {

    SplayNode left; // the left of the node
    SplayNode right; // the right of the node
    SplayNode parent; // the parent of the node
    int value; // the value of the node

    /**
     * This constructor set a new splay node.
     *
     * @param value the value of the node
     */
    public SplayNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    /**
     * The setValue method set the value of the node as the given value
     * @param value the value need to be set
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * The setLeft method set the left child of the given node
     * @param left the node need to be set as the left child
     */
    public void setLeft(SplayNode left) {
        this.left = left;
    }

    /**
     * The setRight method set the right child of the given node
     * @param right the node need to be set as the right child
     */
    public void setRight(SplayNode right) {
        this.right = right;
    }

    /**
     * The setParent method set the parent of the given node
     * @param parent the node need to be set as the parent of the given node
     */
    public void setParent(SplayNode parent) {
        this.parent = parent;
    }

    /**
     * The getValue method get the value of the given node
     * @return the value of the given node
     */
    public int getValue() {
        return value;
    }

    /**
     * The getLeft method get the left child of the given node
     * @return the left child of the given node
     */
    public SplayNode getLeft() {
        return left;
    }

    /**
     * The getRight method get the right child of the given node
     * @return the right child of the given node
     */
    public SplayNode getRight() {
        return right;
    }

    /**
     * The getParent method get the parent of the given node
     * @return the parent of the given node
     */
    public SplayNode getParent() {
        return parent;
    }
}
