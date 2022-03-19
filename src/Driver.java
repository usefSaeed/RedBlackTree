import java.util.*;

public class Driver {

    public static void main(String args[]){
        RedBlackTree RBT = new RedBlackTree();

        for(int i = 1; i <= 38; i++){
            RBT.insert(RBT.root, i);
        }
//        RBT.insert(RBT.root, 1);
//        RBT.insert(RBT.root, 2);
//        RBT.insert(RBT.root, 3);
//        RBT.insert(RBT.root, 4);
//        RBT.insert(RBT.root, 5);
//        RBT.insert(RBT.root, 6);
//        RBT.insert(RBT.root, 7);
//        RBT.insert(RBT.root, 8);
//        RBT.insert(RBT.root, 9);
//        RBT.insert(RBT.root, 10);
//        Node hey = RBT.search(RBT.root, 80);

        RBT.print(RBT.root);
    }
}
