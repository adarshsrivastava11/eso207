package com.company;

public class Assignment2 {
    public class RBTree{
        public Node root;

        public RBTree(){
            root.color = 'b';
            root = null;
        }
        public void insert(int n){
            Node node = new Node(n);
            if (this.root == null){
                root = node;
                node.parent = null;
            }
            else {
                Node temp = new Node();
                
            }

        }
    }
    public class Node{
       public int data;
       public char color;
       public Node left;
       public Node right;
       public Node parent;

        public Node(){
            this.data = 0;
            this.color = 'b';
            this.left = null;
            this.right = null;
            this.parent = null;
        }
        public Node(int data){
            this.data = data;
            this.color = 'b';
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }
}
