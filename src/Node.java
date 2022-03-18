class Node {
    int data;
    int bh;
    boolean color;

    Node left,right,parent;

    public Node(int data) {
        this.data = data;
        this.bh = 0;
        this.color = true;
        this.right = RedBlackTree.NIL;
        this.left = RedBlackTree.NIL;
    }

}
