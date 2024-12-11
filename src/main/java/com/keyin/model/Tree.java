package com.keyin.model;

import jakarta.persistence.*;

public class Tree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private TreeNode root;

    public Tree() {}
    public Tree(TreeNode root) {this.root = root;}

    public void insert(int value) {
        root = insertRec(root, value);
    }

    private TreeNode insertRec(TreeNode root, int value) {
        if (root == null) {
            root = new TreeNode(value);
            return root;
        }
        if (value < root.getValue()) {
            root.setLeft(insertRec(root.getLeft(), value));
        } else if (value > root.getValue()) {
            root.setRight(insertRec(root.getRight(), value));
        }
        return root;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public TreeNode getRoot() {return root;}
    public void setRoot(TreeNode root) {this.root = root;}
}
