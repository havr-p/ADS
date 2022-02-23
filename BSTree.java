import java.util.*;

class BSTree {
    //node class that defines BST node
    class Node {
        long key;
        Node left, right;

        public Node(long data) {
            key = data;
            left = right = null;
        }
    }


    // BST root node
    private Node root;
    private double havg;
    private int nodeCount, totalSum, currentDepth, maxDepth;
    private boolean alreadyInTree;

    public BSTree() {
        root = null;
    }

    public void insert(long key) {
        currentDepth=0;
        this.root = insertNode(this.root, key);
        //System.out.println("Sum of depths is " + havg + '*' + nodeCount + "=" + havg*nodeCount);
        if (!alreadyInTree) {
            if (currentDepth > maxDepth) {
                maxDepth = currentDepth;
            }
            nodeCount++;
            totalSum+=currentDepth;
            havg = (double) totalSum/(double) nodeCount;
        }
    }

    public Node insertNode(Node root, long key) {
        alreadyInTree = false;
        // if the root is null, create a new node and return it
        if (root == null) {
            return new Node(key);
        }

        // if the given key is less than the root node,
        // recur for the left subtree
        if (key < root.key) {
            currentDepth++;
            root.left = insertNode(root.left, key);
        }

        // otherwise, recur for the right subtree
        else if (key > root.key) {
            // key > root.key
            currentDepth++;
            root.right = insertNode(root.right, key);
        }
        else {
            //ak je hodnota v bst, nic nerobime, vracame neupraveny root
            alreadyInTree = true;
            return root;
        }
        return root;
    }


    public void print() {
        System.out.printf("%d %d %.9f%n", nodeCount, maxDepth, havg);
    }


    public static void main(String[] args) {
        BSTree tree = new BSTree();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int a0 = in.nextInt();
        int k = in.nextInt();
        long q = (long) Math.pow(2, 31);
        long next = a0;
        //generate and insert pseudorandom values
        for (int i = 1; i <= n; i++) {
            next = (next * 1103515245 + 12345) % q;
            tree.insert(next / 4);
            if (i % k == 0) {
                tree.print();
            }
        }
        in.close();
    }
}