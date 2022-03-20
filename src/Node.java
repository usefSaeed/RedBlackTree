class Node {
    int data;
    boolean color;
    Node left,right,parent;

    public Node(int data) {
        this.data = data;
        this.color = true;
        this.right = RedBlackTree.NIL;
        this.left = RedBlackTree.NIL;
    }

    public int countNils(){
        if (this.left==RedBlackTree.NIL && this.right==RedBlackTree.NIL)
            return 2;
        if (this.left==RedBlackTree.NIL || this.right==RedBlackTree.NIL)
            return 1;
        return 0;
    }

    public Node getSibling(){
        if (this.parent.left==this)
            return this.parent.right;
        return  this.parent.left;
    }

    public boolean isLeft(){
        return this.parent.left==this;
    }

}
