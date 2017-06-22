import java.util.Scanner;


/* Class Node */
class RedBlackNode
{
    RedBlackNode left, right, parent1;
    int element;
    int color;

    /* Constructor */
    public RedBlackNode(int theElement)
    {
        this( theElement, null, null );
    }
    /* Constructor */
    public RedBlackNode(int theElement, RedBlackNode lt, RedBlackNode rt)
    {
        left = lt;
        right = rt;
        element = theElement;
        color = 1;
    }
}

/* Class RBTree */
class RBTree
{
    private RedBlackNode current;
    private RedBlackNode parent;
    private RedBlackNode grand;
    private RedBlackNode great;
    private RedBlackNode header;
    private static RedBlackNode nullNode;
    /* static initializer for nullNode */
    static
    {
        nullNode = new RedBlackNode(0);
        nullNode.left = nullNode;
        nullNode.right = nullNode;
    }
    static final int BLACK = 1;
    static final int RED   = 0;



    public RBTree(int negInf)
    {
        header = new RedBlackNode(negInf);
        header.left = nullNode;
        header.right = nullNode;
    }
    /* Function to check if tree is empty */
    public boolean isEmpty()
    {
        return header.right == nullNode;
    }
    /* Make the tree logically empty */
    public void makeEmpty()
    {
        header.right = nullNode;
    }
    /* Function to insert item */
    public void insert(int item )
    {
        current = parent = grand = header;
        nullNode.element = item;
        while (current.element != item)
        {
            great = grand;
            grand = parent;
            parent = current;
            current = item < current.element ? current.left : current.right;
            // Check if two red children and fix if so
            if (current.left.color == RED && current.right.color == RED)
                handleReorient( item );
        }
        // Insertion fails if already present
        if (current != nullNode)
            return;
        current = new RedBlackNode(item, nullNode, nullNode);
        // Attach to parent
        if (item < parent.element) {
            parent.left = current;
            current.parent1 = parent;
        }
        else {
            parent.right = current;
            current.parent1 = parent;
        }
        handleReorient( item );
    }
    private void handleReorient(int item)
    {
        // Do the color flip
        current.color = RED;
        current.left.color = BLACK;
        current.right.color = BLACK;

        if (parent.color == RED)
        {
            // Have to rotate
            grand.color = RED;
            if (item < grand.element != item < parent.element)
                parent = rotate( item, grand );  // Start dbl rotate
            current = rotate(item, great );
            current.color = BLACK;
        }
        // Make root black
        header.right.color = BLACK;
    }
    private RedBlackNode rotate(int item, RedBlackNode parent)
    {
        if(item < parent.element)
            return parent.left = item < parent.left.element ? rotateWithLeftChild(parent.left) : rotateWithRightChild(parent.left) ;
        else
            return parent.right = item < parent.right.element ? rotateWithLeftChild(parent.right) : rotateWithRightChild(parent.right);
    }
    /* Rotate binary tree node with left child */
    private RedBlackNode rotateWithLeftChild(RedBlackNode k2)
    {
        RedBlackNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        return k1;
    }
    /* Rotate binary tree node with right child */
    private RedBlackNode rotateWithRightChild(RedBlackNode k1)
    {
        RedBlackNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        return k2;
    }
    /* Functions to count number of nodes */
    public int countNodes()
    {
        return countNodes(header.right);
    }
    private int countNodes(RedBlackNode r)
    {
        if (r == nullNode)
            return 0;
        else
        {
            int l = 1;
            l += countNodes(r.left);
            l += countNodes(r.right);
            return l;
        }
    }
    /* Functions to search for an element */
    public boolean search(int val)
    {
        return search(header.right, val);
    }
    private boolean search(RedBlackNode r, int val)
    {
        boolean found = false;
        while ((r != nullNode) && !found)
        {
            int rval = r.element;
            if (val < rval)
                r = r.left;
            else if (val > rval)
                r = r.right;
            else
            {
                found = true;
                break;
            }
            found = search(r, val);
        }
        return found;
    }

    /* Function for preorder traversal */
    public void preorder()
    {
        preorder(header.right);
    }
    private void preorder(RedBlackNode r)
    {
        if (r != nullNode)
        {
            char c = 'B';
            if (r.color == 0)
                c = 'R';
            System.out.print(r.element +""+c+" "+parent.element);
            preorder(r.left);
            preorder(r.right);
        }
    }

}

/* Class RedBlackTreeTest */
public class Assignment2
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        /* Creating object of RedBlack Tree */
        RBTree rbt = new RBTree(Integer.MIN_VALUE);
        System.out.println("Red Black Tree Test\n");
        char ch;
        /*  Perform tree operations  */
        do
        {
            System.out.println("\nRed Black Tree Operations\n");
            System.out.println("1. insert ");
            System.out.println("2. search");
            System.out.println("3. count nodes");
            System.out.println("4. check empty");
            System.out.println("5. clear tree");

            int choice = scan.nextInt();
            switch (choice)
            {
                case 1 :
                    System.out.println("Enter integer element to insert");
                    rbt.insert( scan.nextInt() );
                    break;
                case 2 :
                    System.out.println("Enter integer element to search");
                    System.out.println("Search result : "+ rbt.search( scan.nextInt() ));
                    break;
                case 3 :
                    System.out.println("Nodes = "+ rbt.countNodes());
                    break;
                case 4 :
                    System.out.println("Empty status = "+ rbt.isEmpty());
                    break;
                case 5 :
                    System.out.println("\nTree Cleared");
                    rbt.makeEmpty();
                    break;
                default :
                    System.out.println("Wrong Entry \n ");
                    break;
            }

            System.out.print("\nPre order : ");
            rbt.preorder();


            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);
        } while (ch == 'Y'|| ch == 'y');
    }
}