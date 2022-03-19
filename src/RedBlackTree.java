public class RedBlackTree {
    Node root,x;

    public static final Node NIL;
    public static final boolean RED = true;
    public static final boolean BLACK = false;



    static {
        NIL = new Node(0);
        NIL.left = null;
        NIL.right = null;
        NIL.color = BLACK;
    }

    Node search(Node current, int data){
        if (current == NIL){
            System.out.println("NO");
            return null;
        }
        if (current.data == data){
            System.out.println("YES");
            return current;
        }
        else if (current.data < data){
            return search(current.right, data);
        }
        return search(current.left, data);
    }


    public Node delete(Node current,int data){
        if (current == NIL){
            System.out.println("Duh. You cannot delete a word that already doesn't exist");
            return null;
        }
        if (current.data == data){
            this.deletingAction(current);
        }else if (current.data < data){
            return delete(current.right, data);
        }else {
            return delete(current.left, data);
        }
        if (x.color==RED) {
            x.color = BLACK;
        }else {
            Node w = x.getSibling();
            Node y = w.left;
            Node z = w.right;
            if (this.getOutOfCases1and2(w,y,z))
                return null;
            if (x.isLeft()){
                if (y.color==RED && z.color==BLACK)
                    this.dCase3Left(w,y,z);
                if (y.color==BLACK && z.color==RED)
                    this.dCase4Left(w,y,z);
            }else{
                if (y.color==RED && z.color==BLACK)
                    this.dCase3Right(w,y,z);
                if (y.color==BLACK && z.color==RED)
                    this.dCase4Right(w,y,z);
            }
        }
        return null;
    }

    public void deletingAction(Node current){
        int nils = current.countNils();
        if (nils==2){
            x = current.right;
            if (current==this.root)
                this.root = null;
            current = x;
        }else if (nils==1){
            x = current.right==NIL ? current.left : current.right;
            current = x;
        }else{
            Node replaceTemp = getMinNode(current);
            x = replaceTemp.right;
            if (current.color==RED && replaceTemp.color==BLACK)
                replaceTemp.color=RED;
            current = replaceTemp;
        }
    }

    Node getMinNode(Node x){
        Node temp = x;
        while (temp.left!=NIL)
            temp = temp.left;
        return temp;
    }

    boolean dCase1(Node w){
        x.color=BLACK;
        x.parent.color=RED;
        x.parent = rotateAccordingly(x.parent);
        w = x.isLeft() ? x.parent.right : x.parent.right;
        return false;
    }

    boolean dCase2(Node w,Node y,Node z){
        w.color=RED;
        x = x.parent;
        if (x.color==RED){
            x.color = BLACK;
            return true;
        }
        return false;

    }

    boolean dCase3Left(Node w,Node y,Node z){
        y.color = BLACK;
        w.color = RED;
        rightRotate(w);
        w = x.parent.right;
        return false;
    }

    boolean dCase3Right(Node w,Node y,Node z){
        z.color = BLACK;
        w.color = RED;
        leftRotate(w);
        w = x.parent.left;
        return false;
    }

    boolean dCase4Left(Node w,Node y,Node z){
        w.color = x.parent.color;
        x.parent.color = z.color =  BLACK;
        leftRotate(z);
        return true;
    }

    boolean dCase4Right(Node w,Node y,Node z){
        w.color = x.parent.color;
        x.parent.color = y.color =  BLACK;
        rightRotate(z);
        return true;
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

    Node rotateAccordingly(Node current){
        if (current.isLeft())
            current = leftRotate(current);
        else
            current = rightRotate(current);
        return  current;
    }

    boolean getOutOfCases1and2(Node w,Node y,Node z) {
        boolean done = false;
        boolean enteredCase2 = false;
        if (w.color==RED)
            this.dCase1(w);
        if (w.color==BLACK && y.color==BLACK && z.color==BLACK) {
            enteredCase2 = true;
            done = this.dCase2(w,y,z);
        }
        if (done)
            return true;
        if (enteredCase2)
            return false;
        return getOutOfCases1and2(w,y,z);
    }

}
