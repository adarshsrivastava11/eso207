#include <iostream>
 
enum Color {RED, BLACK};
 
struct Node
{
    int data;
    bool color;
    Node *left, *right, *parent;
 
    // Constructor
    Node(int data)
    {
       this->data = data;
       left = right = parent = NULL;
    }
};
 
void visit(Node *x)
{
    if(x==NULL){ return;}
    
    visit(x->left);
    
    std::cout << x->data << " ";
    if(x->color) std::cout<<"B ";
    else std::cout<<"R ";
    if(x->parent != NULL)
       std::cout<<(x->parent)->data;
    else std::cout<<"NIL";
    std::cout<<"\n";
    
    visit(x->right);
}
 

Node* BSTInsert(Node *&root, Node *&pt)
{
    if(root == NULL)
        root = pt;
 
    else
    {
        if(pt->data < root->data)
        {
            root->left  = BSTInsert(root->left, pt);
            root->left->parent = root;
        }
        else if(pt->data > root->data)
            {
               root->right = BSTInsert(root->right, pt);
               root->right->parent = root;
            }
    }
 
    return root;
}
 
 
void rotateLeft(Node *&root, Node *&pt)
{
    Node *pt_right = pt->right;
 
    pt->right = pt_right->left;
 
    if(pt->right != NULL)
        pt->right->parent = pt;
 
    pt_right->parent = pt->parent;
 
    if(pt->parent == NULL)
        root = pt_right;
 
    else if(pt == pt->parent->left)
        pt->parent->left = pt_right;
 
    else
        pt->parent->right = pt_right;
 
    pt_right->left = pt;
    pt->parent = pt_right;
}
 
void rotateRight(Node *&root, Node *&pt)
{
    Node *pt_left = pt->left;
 
    pt->left = pt_left->right;
 
    if(pt->left != NULL)
        pt->left->parent = pt;
 
    pt_left->parent = pt->parent;
 
    if(pt->parent == NULL)
        root = pt_left;
 
    else if(pt == pt->parent->left)
        pt->parent->left = pt_left;
 
    else
        pt->parent->right = pt_left;
 
    pt_left->right = pt;
    pt->parent = pt_left;
}
 

void fixViolation(Node *&root, Node *&pt)
{
    Node *parent_pt = NULL;
    Node *grand_parent_pt = NULL;
 
    while ((pt != root) && (pt->color != BLACK) &&
           (pt->parent->color == RED))
    {
 
        parent_pt = pt->parent;
        grand_parent_pt = pt->parent->parent;
 
        if(parent_pt == grand_parent_pt->left)
        {
 
            Node *uncle_pt = grand_parent_pt->right;

            if(uncle_pt != NULL && uncle_pt->color == RED)
            {
                grand_parent_pt->color = RED;
                parent_pt->color = BLACK;
                uncle_pt->color = BLACK;
                pt = grand_parent_pt;
            }
 
            else
            {

                if(pt == parent_pt->right)
                {
                    rotateLeft(root, parent_pt);
                    pt = parent_pt;
                    parent_pt = pt->parent;
                }
 
                rotateRight(root, grand_parent_pt);
                std::swap(parent_pt->color, grand_parent_pt->color);
                pt = parent_pt;
            }
        }
 
        else
        {
            Node *uncle_pt = grand_parent_pt->left;

            if((uncle_pt != NULL) && (uncle_pt->color == RED))
            {
                grand_parent_pt->color = RED;
                parent_pt->color = BLACK;
                uncle_pt->color = BLACK;
                pt = grand_parent_pt;
            }
            else
            {

                if(pt == parent_pt->left)
                {
                    rotateRight(root, parent_pt);
                    pt = parent_pt;
                    parent_pt = pt->parent;
                }
 
                rotateLeft(root, grand_parent_pt);
                std::swap(parent_pt->color, grand_parent_pt->color);
                pt = parent_pt;
            }
        }
    }
 
    root->color = BLACK;
}
 

Node* insert(const int data, Node* root)
{
    Node *pt = new Node(data);
    root = BSTInsert(root, pt);
    fixViolation(root, pt);
    return root;
}
 

void prefixRoute(Node*root)     
{
    if(root==NULL)
        std::cout<<"empty tree";
    else visit(root);
}
 
int main()
{
    Node* root;
    root = NULL;
 
    int n,k,i;
    scanf("%d",&n);
    for(i=0;i<n;i++){
        scanf("%d",&k);
        root = insert(k,root);
    }
 
    prefixRoute(root);
 
    return 0;
}