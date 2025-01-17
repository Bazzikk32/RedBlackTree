package tree;

public class Tree {
    Node root;
    class Node{
        int value;
        Node left;
        Node right;
        Color color;
    }
    enum Color{
        BLACK,
        RED
    }
    private Node balans(Node node) {
        boolean flag = true;
        Node cur = node;
        do {
            flag = false;
            if (cur.right != null && cur.right.color == Color.RED && (cur.left == null || cur.left.color == Color.BLACK)) {
                cur = leftRotate(cur);
                flag = true;
            }
            if (cur.left != null && cur.left.color == Color.RED && cur.left.left != null && cur.left.left.color == Color.RED) {
                cur = rightRotate(cur);
                flag = true;
            }
            if (cur.left != null && cur.left.color == Color.RED && cur.right != null && cur.right.color == Color.RED) {
                swapCollor(cur);
                flag = true;
            }
        } while (flag);
        return cur;
    }
    private void swapCollor(Node node){
        node.color=(node.color==Color.RED ? Color.BLACK : Color.RED);
        node.left.color=Color.BLACK;
        node.right.color=Color.BLACK;
    }
    private Node leftRotate(Node node){
        Node temp=node.right;
        node.right=temp.left;
        temp.left=node;
        temp.color=node.color;
        node.color=Color.RED;
        return temp;
    }
    private Node rightRotate(Node node){
        Node temp=node.left;
        node.left=temp.right;
        temp.right=node;
        temp.color=node.color;
        node.color=Color.RED;
        return temp;
    }
    public void add(int value){
        if (root!=null){
            add(root,value);
            root = balans(root);
        }else {
            root = new Node();
            root.value=value;
        }
        root.color=Color.BLACK;
    }
    private void add(Node node,int value){
        if (node.value!=value){
            if (node.value<value){
                if (node.right==null){
                    node.right=new Node();
                    node.right.value=value;
                    node.right.color=Color.RED;
                }else {
                    add(node.right,value);
                    node.right=balans(node.right);
                }
            }else {
                if (node.left==null){
                    node.left=new Node();
                    node.left.value=value;
                    node.left.color=Color.RED;

                }else {
                    add(node.left,value);
                    node.left=balans(node.left);
                }
            }
        }
    }
    public Node find(int value){
        return find(root,value);
    }
    private Node find(Node node,int value){
        if (node==null)return null;
        else {
            if (node.value==value)return node;
            else {
                if (value>node.value)return find(node.right,value);
                else return find(node.left,value);
            }
        }
    }
    public void print (){
        print(root, 0);
    }
    private void print(Node node, int deep){
        if (node==null)return;
        print(node.left,deep+4);
        for (int i = 0; i < deep; i++)
            System.out.print(" ");
            System.out.println("value: " + node.value + " {color " + node.color + "}");
            print(node.right,deep+4);
    }

}

