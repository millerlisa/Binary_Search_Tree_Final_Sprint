package com.keyin.model;

import jakarta.persistence.*;

public class TreeNode {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    private int value;

    @OneToOne(cascade = CascadeType.ALL)
    private TreeNode left;

    @OneToOne(cascade = CascadeType.ALL)
    private TreeNode right;

    public TreeNode(int value) {
        this.value = value;
    }

//    public Long getId() {return id;}
//    public void setId(Long id) {this.id = id;}

    public int getValue() {return value;}
    public void setValue(int value) {this.value = value;}

    public TreeNode getLeft() {return left;}
    public void setLeft(TreeNode left) {this.left = left;}

    public TreeNode getRight() {return right;}
    public void setRight(TreeNode right) {this.right = right;}


}