public class RedBlackTree {
    Node root = NIL;
    int size;

    public static final Node NIL;
    public static final boolean RED = true;
    public static final boolean BLACK = false;

    static {
        NIL = new Node(0);
        NIL.left = null;
        NIL.right = null;
    }
    RedBlackTree(){
        size = 0;
    }

    Node search(Node parent, int data){
        if (parent == NIL){

            return null;
        }
        if (parent.data == data){

            return parent;
        }
        else if (parent.data < data){
            return search(parent.right, data);
        }
        return search(parent.left, data);
    }

    boolean contains(Node parent, int data){
        if (parent == NIL){

            return false;
        }
        if (parent.data == data){

            return true;
        }
        else if (parent.data < data){
            return contains(parent.right, data);
        }
        return contains(parent.left, data);
    }

    Node getRoot(){
        return this.root;
    }

    boolean isEmpty(){
        if(this.size == 0){
            return true;
        }
        return false;
    }

    void clear(){
        this.root = NIL;
    }

    Node leftRotate(Node x) {
        boolean isRoot = (x == this.root);
        Node y = x.right;
        Node z = y.left;
        y.left = x;
        x.right = z;
        x.parent = y;
        if(z!= null) z.parent = x;
        if (isRoot)
            this.root = y;
        return y;
    }

    Node leftRightRotate(Node x){
        x.left = leftRotate(x.left);
        x.left.parent = x;
        x = rightRotate(x);
        return x;
    }

    Node rightRotate(Node x) {
        boolean isRoot = (x == this.root);
        Node y = x.left;
        Node z = y.right;
        y.right = x;
        x.left = z;
        x.parent = y;
        if(z != null) z.parent = x;
        if (isRoot)
            this.root = y;
        return y;
    }

    Node rightLeftRotate(Node x){
        x.right = rightRotate(x.right);
        x.right.parent = x;
        x = leftRotate(x);
        return x;
    }

    boolean leftleft, rightright, leftright, rightleft = false;

    Node insert(Node x, int data){
        boolean redConflict = false;

        if(x == NIL){
            this.size++;
            x = new Node(data);
            if (this.size==1) {
                this.root = x;
                this.root.color = BLACK;
            }
            System.out.println("\nWelcome to our tree," + data + " :)");
            return x;
        }

        if (data < x.data){
            x.left = insert(x.left, data);
            x.left.parent = x;
            if(x != this.root){
                if(x.color == RED && x.left.color == RED)
                    redConflict = true;
            }
        }
        else if (data > x.data){
            x.right = insert(x.right, data);
            x.right.parent = x;
            if(x != this.root){
                if(x.color == RED && x.right.color == RED)
                    redConflict = true;
            }
        }
        else {
            System.out.println("\nNumber already exists.");
            return x;
        }

        if(this.rightright)
        {
            x = leftRotate(x);
            x.color = BLACK;
            x.left.color = RED;
            this.rightright = false;
        }
        else if(this.leftleft)
        {
            x = rightRotate(x);
            x.color = BLACK;
            x.right.color = RED;
            this.leftleft  = false;
        }
        else if(this.rightleft)
        {
            x = rightLeftRotate(x);
            x.color = BLACK;
            x.left.color = RED;

            this.rightleft = false;
        }
        else if(this.leftright)  // for left and then right.
        {
            x = leftRightRotate(x);
            x.color = BLACK;
            x.right.color = RED;
            this.leftright = false;
        }

        if(redConflict){
            if(x.parent.right == x){
                if(x.parent.left.color == BLACK || x.parent.left == NIL){
                    if(x.left.color == RED && x.left != NIL){
                        this.rightleft = true;
                    }
                    else if(x.right.color == RED && x.right != NIL){
                        this.rightright = true;
                    }
                }
                else{
                    x.parent.left.color = BLACK;
                    x.color = BLACK;
                    if(x.parent != this.root){
                        x.parent.color = RED;
                    }
                }
            }
            else{
                if(x.parent.right.color == BLACK || x.parent.right == NIL ){
                    if(x.left.color == RED && x.left != NIL){
                        this.leftleft = true;
                    }
                    else if(x.right.color == RED && x.right != NIL){
                        this.leftright = true;
                    }
                }
                else{
                    x.parent.right.color = BLACK;
                    x.color = BLACK;
                    if(x.parent != this.root){
                        x.parent.color = RED;
                    }
                }

            }
            redConflict = false;
        }

        return x;
    }

    void print(Node x){

        if(x == NIL){
            return;
        }

        System.out.println(x.data);
        if(x.color == RED) System.out.println("Red Node");
        else System.out.println("Black Node");
        System.out.print("Left child of " + x.data + ": ");
        print(x.left);
        System.out.print("Right child of " + x.data + ": ");
        print(x.right);
    }

    
}



