public class RedBlackTree {

    Node root = NIL;
    int size;
    public static final Node NIL;
    public static final boolean RED = true;
    public static final boolean BLACK = false;
    private static Node x,w;
    private int data;


    static {
        NIL = new Node(0);
        NIL.left = null;
        NIL.right = null;
        NIL.color = BLACK;
    }

    RedBlackTree(){
        size = 0;
    }

    Node search(Node parent, int data){
        if (parent == NIL){
            System.out.print("Sorry :( Couldn't find it.");
            return null;
        }
        if (parent.data == data){
            System.out.print("We found it!");
            return parent;
        }
        else if (parent.data < data){
            return search(parent.right, data);
        }
        return search(parent.left, data);
    }

    boolean contains(Node parent, int data){
        if (parent == NIL){
            System.out.print("Sorry :( Couldn't find it.");
            return false;
        }
        if (parent.data == data){
            System.out.print("We found it!");
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
        this.root.parent = null;
        System.out.println("Tree Cleared");
        this.size = 0;
    }

    Node leftRotate(Node x) {
        boolean isRoot = (x == this.root);
        Node y = x.right;
        Node z = y.left;
        y.left = x;
        y.parent = x.parent;
        x.right = z;
        x.parent = y;
        if(z!= null) z.parent = x;
        if (isRoot) {
            this.root = y;
            y.parent = null;
        }
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
        y.parent = x.parent;
        x.left = z;
        x.parent = y;
        if(z != null) z.parent = x;
        if (isRoot) {
            this.root = y;
            y.parent = null;
        }
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

    void print(Node current){
        if(current == NIL){
            System.out.print("\n");
            return;
        }
        System.out.print(current.data);
        if(current.color == RED) System.out.println(" (Red)");
        else System.out.println(" (Black)");
        System.out.print("Left child of " + current.data + ": ");
        print(current.left);
        System.out.print("Right child of " + current.data + ": ");
        print(current.right);
    }


    public Node delete(Node current,int data){
        int deletionJustDone = 0;
        if (current.data == data){
            current = this.deletingAction(current);
            deletionJustDone = 0;
            this.data = data;
        }else if (current.data < data){
            current.right = delete(current.right, data);
        }else {
            current.left = delete(current.left, data);
        }
        deletionJustDone++;
        if (deletionJustDone==2)
            current = this.repairDelete(current);
        this.root.color = BLACK;
        return current;
    }

    Node repairDelete(Node current){
        if (x.color==RED) {
            x.color = BLACK;
        }else {
            w = x.getSibling();
            if (this.getOutOfCases1and2())
                return current;
            Node y = w.left;
            Node z = w.right;
            if (x.isLeft()){
                if (y.color==RED && z.color==BLACK)
                    this.dCase3Left();
                y = w.left;
                z = w.right;
                if (y.color==BLACK && z.color==RED)
                    this.dCase4Left();
            }else{
                if (y.color==RED && z.color==BLACK)
                    this.dCase3Right();
                y = w.left;
                z = w.right;
                if (y.color==BLACK && z.color==RED)
                    this.dCase4Right();
            }
        }
        return current;
    }

    public Node deletingAction(Node current){
        int nils = current.countNils();
        if (nils==2){
            x = current.right;
            if (current==this.root)
                this.root = NIL;
            else
                x.parent = current.parent;
            current = x;
        }else if (nils==1){
            x = current.right==NIL ? current.left : current.right;
            if (current==this.root)
                this.root = x;
            else
                x.parent = current.parent;
            current = x;
        }else{
            Node replaceTemp = getMinNode(current.right);
            x = replaceTemp.right;
            if (current.color==RED && replaceTemp.color==BLACK)
                replaceTemp.color=RED;
            current.data = replaceTemp.data;
            current.right = delete(current.right,current.data);
        }
        return current;
    }

    Node getMinNode(Node x){
        Node temp = x;
        while (temp.left!=NIL)
            temp = temp.left;
        return temp;
    }

    boolean dCase1(){
        x.color=BLACK;
        x.parent.color=RED;
        if (x.isLeft()) {
            Node temp = leftRotate(x.parent);
            x.parent = temp.left;
            w = x.parent.right;
        }else{
            Node temp = rightRotate(x.parent);
            x.parent = temp.right;
            w = x.parent.left;
        }
        return false;
    }





    boolean dCase2(){
        w.color=RED;
        x = x.parent;
        if (x.color==RED){
            x.color = BLACK;
            return true;
        }
        return false;

    }

    boolean dCase3Left(){
        Node y = w.left;
        y.color = BLACK;
        w.color = RED;
        w = rightRotate(w);
        x = w.parent.left;
        x.parent = w.parent;
        x.parent.right = w;
        Node temp = w;
        while(temp.parent!=null) temp = temp.parent;
        root = temp;
        return false;
    }

    boolean dCase3Right(){
        Node z = w.right;
        z.color = BLACK;
        w.color = RED;
        w = leftRotate(w);
        x = w.parent.right;
        x.parent = w.parent;
        x.parent.left = w;
        Node temp = w;
        while(temp.parent!=null) temp = temp.parent;
        root = temp;
        return false;
    }

    boolean dCase4Left(){
        Node y = w.left;
        Node z = w.right;
        w.color = x.parent.color;
        x.parent.color = z.color =  BLACK;
        Node temp = leftRotate(x.parent);
        temp.parent = w.parent;
        w.parent.left = temp;
        Node traverse = temp;
        while(traverse.parent!=null) traverse = traverse.parent;
        root = traverse;
        return true;
    }

    boolean dCase4Right(){
        Node y = w.left;
        Node z = w.right;
        w.color = x.parent.color;
        x.parent.color = y.color =  BLACK;
        Node temp = rightRotate(x.parent);
        temp.parent = w.parent;
        w.parent.left = temp;
        Node traverse = temp;
        while(traverse.parent!=null) traverse = traverse.parent;
        root = traverse;
        return true;
    }



    boolean getOutOfCases1and2() {
        boolean done = false;
        boolean neverEnteredCase2 = true;
        Node y = w.left;
        Node z = w.right;
        if (w.color==RED) {
            this.dCase1();
            y = w.left;
            z = w.right;
        }
        if (w.color==BLACK && y.color==BLACK && z.color==BLACK) {
            neverEnteredCase2 = false;
            done = this.dCase2();
        }
        if (done)
            return true;
        if (neverEnteredCase2)
            return false;
        return getOutOfCases1and2();
    }

    
}
