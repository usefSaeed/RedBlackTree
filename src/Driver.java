import java.util.*;

public class Driver {

    public static void main(String args[]){
        RedBlackTree RBT = new RedBlackTree();

        RBT.insert(RBT.root, 40);
        RBT.print(RBT.root);
        RBT.insert(RBT.root, 55);
        RBT.print(RBT.root);
        RBT.insert(RBT.root, 65);
        RBT.print(RBT.root);
        RBT.insert(RBT.root, 60);
        RBT.print(RBT.root);
        RBT.insert(RBT.root, 75);
        RBT.print(RBT.root);
        RBT.insert(RBT.root, 57);
        RBT.print(RBT.root);
        RBT.print(RBT.root);
        RBT.delete(RBT.root, 40);
        RBT.print(RBT.root);
        RBT.delete(RBT.root, 65);
        RBT.print(RBT.root);
        RBT.delete(RBT.root, 75);
        RBT.print(RBT.root);
        RBT.delete(RBT.root, 55);
        RBT.print(RBT.root);
//        RBT.insert(RBT.root, 7);
//        RBT.insert(RBT.root, 8);
//        RBT.insert(RBT.root, 9);
//        RBT.insert(RBT.root, 10);

    }
}
