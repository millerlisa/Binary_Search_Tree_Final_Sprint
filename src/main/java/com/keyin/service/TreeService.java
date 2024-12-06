//package com.keyin.service;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.keyin.model.Tree;
//import com.keyin.model.TreeEntity;
//import com.keyin.model.TreeNode;
//import com.keyin.repository.TreeRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class TreeService {
//    @Autowired
//    private TreeRepository treeRepository;
//
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    // Save tree to the database
//    public String saveTree(List<Integer> numList, Tree bst) {
//        // Serialize the tree structure to JSON
//        String treeJson;
//        try {
//            treeJson = objectMapper.writeValueAsString(bst.getRoot());
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException("Failed to serialize tree structure", e);
//        }
//
//        // Create a TreeEntity to store in the database
//        TreeEntity treeEntity = new TreeEntity();
//        treeEntity.setInputNumbers(numList.toString());
//        treeEntity.setTreeStructure(treeJson);
//
//        // Save the entity to the database
//        treeRepository.save(treeEntity);
//
//        // Return the JSON representation of the tree
//        return treeJson;
//    }
//
//    // Retrieve all stored trees as TreeEntities
//    public List<TreeEntity> getAllTreeEntities() {
//        return treeRepository.findAll();
//    }
//
//    // Retrieve all stored trees and deserialize them into TreeNode structures
//    public List<TreeNode> getAllTrees() {
//        return treeRepository.findAll().stream()
//                .map(treeEntity -> {
//                    try {
//                        // Deserialize treeStructure (JSON) back into TreeNode
//                        return objectMapper.readValue(treeEntity.getTreeStructure(), TreeNode.class);
//                    } catch (JsonProcessingException e) {
//                        throw new RuntimeException("Failed to deserialize tree structure", e);
//                    }
//                })
//                .collect(Collectors.toList());
//    }
//
//    public TreeEntity getTreeById(Long id) {
//        return treeRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Tree not found with ID: " + id));
//    }
//
//    public TreeNode insert(TreeNode root, int value) {
//        // If the root is null, create a new TreeNode with the given value
//        if (root == null) {
//            return new TreeNode(value);
//        }
//        // Traverse the left subtree if the value is less than the current node's value
//        if (value < root.getValue()) {
//            root.setLeft(insert(root.getLeft(), value));
//        }
//        // Traverse the right subtree if the value is greater than the current node's value
//        else if (value > root.getValue()) {
//            root.setRight(insert(root.getRight(), value));
//        }
//        // If the value is equal, do nothing (optional: you can enforce unique values in the BST)
//        return root;
//    }
//
//    public String serializeTree(TreeNode root) {
//        if (root == null) {return "null"; // Return "null" for an empty tree
//        }
//        // Recursively build the JSON-like string representation
//        return "{"
//                + "\"value\":" + root.getValue() + ","
//                + "\"left\":" + serializeTree(root.getLeft()) + ","
//                + "\"right\":" + serializeTree(root.getRight())
//                + "}";
//    }
//
//}
//
package com.keyin.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.model.Tree;
import com.keyin.model.TreeEntity;
import com.keyin.model.TreeNode;
import com.keyin.repository.TreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TreeService {

    @Autowired
    private TreeRepository treeRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    // Save the tree to the database
    public String saveTree(List<Integer> numList, Tree bst) {
        // Serialize the tree structure to JSON
        String treeJson;
        try {
            treeJson = objectMapper.writeValueAsString(bst.getRoot());
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize tree structure", e);
        }

        // Create a TreeEntity to store in the database
        TreeEntity treeEntity = new TreeEntity();
        treeEntity.setInputNumbers(numList.toString());
        treeEntity.setTreeStructure(treeJson);

        // Save the entity to the database
        treeRepository.save(treeEntity);

        // Return the JSON representation of the tree
        return treeJson;
    }

    // Retrieve all stored TreeEntities
    public List<TreeEntity> getAllTreeEntities() {
        return treeRepository.findAll();
    }

    // Retrieve all stored trees and deserialize them into TreeNode structures
    public List<TreeNode> getAllTrees() {
        return treeRepository.findAll().stream()
                .map(treeEntity -> {
                    try {
                        // Deserialize treeStructure (JSON) back into TreeNode
                        return objectMapper.readValue(treeEntity.getTreeStructure(), TreeNode.class);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException("Failed to deserialize tree structure", e);
                    }
                })
                .collect(Collectors.toList());
    }

    // Retrieve a specific TreeEntity by ID
    public TreeEntity getTreeById(Long id) {
        return treeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tree not found with ID: " + id));
    }

    // Insert a value into the binary search tree
    public TreeNode insert(TreeNode root, int value) {
        // If the root is null, create a new TreeNode with the given value
        if (root == null) {
            return new TreeNode(value);
        }
        // Traverse the left subtree if the value is less than the current node's value
        if (value < root.getValue()) {
            root.setLeft(insert(root.getLeft(), value));
        }
        // Traverse the right subtree if the value is greater than the current node's value
        else if (value > root.getValue()) {
            root.setRight(insert(root.getRight(), value));
        }
        // If the value is equal, do nothing (optional: you can enforce unique values in the BST)
        return root;
    }

    // Serialize a TreeNode to a JSON-like string representation
    public String serializeTree(TreeNode root) {
        if (root == null) {
            return "null"; // Return "null" for an empty tree
        }
        // Recursively build the JSON-like string representation
        return "{"
                + "\"value\":" + root.getValue() + ","
                + "\"left\":" + serializeTree(root.getLeft()) + ","
                + "\"right\":" + serializeTree(root.getRight())
                + "}";
    }
}
