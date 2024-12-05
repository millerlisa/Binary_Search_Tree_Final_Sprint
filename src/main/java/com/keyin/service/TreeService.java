package com.keyin.service;

import com.keyin.model.TreeNode;
import com.keyin.repository.TreeNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BinarySearchTreeService {
    @Autowired
    private TreeNodeRepository treeNodeRepository;

    public TreeNode insert(TreeNode root, int value) {
        if (root == null) {
            return new TreeNode(value);
        }
        if (value < root.getValue()) {
            root.setLeft(insert(root.getLeft(), value));
        } else if (value > root.getValue()) {
            root.setRight(insert(root.getRight(), value));
        }
        return root;
    }

    public TreeNode saveTree(TreeNode root) {
        return treeNodeRepository.save(root);
    }

    public List<TreeNode> getAllTrees() {
        return treeNodeRepository.findAll();
    }
}