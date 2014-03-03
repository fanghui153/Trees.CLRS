package Trees.CLRS;

/**
 * This class implement the AVLNode
 * Created by Fanghz on 3/1/14.
 * @version 1.0
 */
public class AVLNode {

    public int key;
    public AVLNode lc; // left child of the node
    public AVLNode rc; // right child of the node
    public int height; // the height of the node


    public AVLNode(int key, AVLNode lc, AVLNode rc) {
        this.key = key;
        this.lc = lc;
        this.rc = rc;
    }

    /**
     * The getKey() method returns the data in the node
     * @return the data value in the node
     */
    public int getKey() {
        return key;
    }

    /**
     * The getLc() method returns the left child of the AVLNode
     * @return the left child node
     */

    public AVLNode getLc() {
        return lc;
    }

    /**
     * The getRc() method returns the right child of the AVLNode
     * @return
     */
    public AVLNode getRc() {
        return rc;
    }


    /**
     * The setKey() method sets the data or key of the AVLNode
     * @param key the key should be set as the data of the node
     */
    public void setKey(int key) {
        this.key = key;

    }

    /**
     * This setLc() method sets the left child of the AVLNode
     * @param lc establishes a left child for this node
     */
    public void setLc(AVLNode lc) {
        this.lc = lc;
    }

    /**
     * This setRc() method sets the right child of the AVLNode
     * @param rc establishes a right child for this node
     */

    public void setRc(AVLNode rc) {
        this.rc = rc;
    }

    /**
     * This getHeight() method get the height of the AVLNode
     * @return the height of the node
     */
    public int getHeight() {return height; }

    /**
     * This setHeight() method set the height of the AVLNode
     * @param height the height should be set as the height of the node
     */
    public void setHeight(int height) {this.height = height; }

}
