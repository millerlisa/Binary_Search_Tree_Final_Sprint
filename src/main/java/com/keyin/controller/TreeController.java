package com.keyin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.keyin.model.Tree;
import com.keyin.model.TreeEntity;
import com.keyin.model.TreeNode;
import com.keyin.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TreeController {

    @Autowired
    private TreeService treeService;

    @GetMapping("/enter-numbers")
    public String enterNumbers() {
        return "enter-numbers";
    }

//    @GetMapping("/visualize-tree/{id}")
//    public String visualizeSpecificTree(@PathVariable Long id, Model model) {
//        // Fetch the tree from the database by ID
//        TreeEntity treeEntity = treeService.getTreeById(id);
//
//        // Pretty print the JSON structure
//        String prettyJsonTree;
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            Object json = objectMapper.readValue(treeEntity.getTreeStructure(), Object.class);
//            prettyJsonTree = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException("Failed to format JSON", e);
//        }
//
//        // Add the pretty JSON and original structure to the model
//        model.addAttribute("jsonTree", prettyJsonTree);
//        return "visualize-tree";
//    }

//    @GetMapping("/visualize-tree")
//    public String visualizeTree(
//            @RequestParam(value = "id", required = false) Long id,
//            @RequestParam(value = "input", required = false) String input,
//            Model model) {
//
//        String jsonTree;
//
//        if (id != null) {
//            // Scenario 1: Load the tree from the database using the ID
//            TreeEntity treeEntity = treeService.getTreeById(id);
//            jsonTree = treeEntity.getTreeStructure();
//        } else if (input != null) {
//            // Scenario 2: Create a new tree from user input
//            List<Integer> numbers = Arrays.stream(input.split(","))
//                    .map(String::trim)
//                    .map(Integer::parseInt)
//                    .collect(Collectors.toList());
//
//            TreeNode root = null;
//            for (int number : numbers) {
//                root = treeService.insert(root, number);
//            }
//            jsonTree = treeService.serializeTree(root);
//
//            // Optionally save the new tree
//            treeService.saveTree(numbers, new Tree(root));
//        } else {
//            throw new RuntimeException("No valid input or tree ID provided.");
//        }
//
//        model.addAttribute("jsonTree", jsonTree);
//        return "visualize-tree";
//    }

    @GetMapping("/visualize-tree")
    public String visualizeTree(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "input", required = false) String input,
            Model model) {

        String jsonTree;

        if (id != null) {
            // Fetch the tree by ID
            TreeEntity treeEntity = treeService.getTreeById(id);
            jsonTree = treeEntity.getTreeStructure();
        } else if (input != null) {
            // Create a tree from user input
            List<Integer> numbers = Arrays.stream(input.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            TreeNode root = null;
            for (int number : numbers) {
                root = treeService.insert(root, number);
            }

            // Serialize the tree structure with pretty print
            ObjectMapper prettyMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            try {
                jsonTree = prettyMapper.writeValueAsString(root);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Error serializing tree", e);
            }

            // Optionally save the tree to the database
            treeService.saveTree(numbers, new Tree(root));
        } else {
            throw new RuntimeException("No input or tree ID provided.");
        }

        // Add JSON to the model
        model.addAttribute("jsonTree", jsonTree);

        return "visualize-tree";
    }






    @PostMapping("/process-numbers")
    public String processNumbers(@RequestParam("numbers") String numbers, Model model) {
        List<Integer> numList = Arrays.stream(numbers.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        Tree bst = new Tree();
        numList.forEach(bst::insert);

        // Save the tree and get JSON representation
        String jsonTree = treeService.saveTree(numList, bst);

        // Pretty print JSON
        String prettyJsonTree;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Object json = objectMapper.readValue(jsonTree, Object.class);
            prettyJsonTree = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to format JSON", e);
        }

        model.addAttribute("jsonTree", prettyJsonTree);
        model.addAttribute("treeRoot", bst.getRoot());
        return "visualize-tree";
    }



    @GetMapping("/previous-trees")
    public String previousTrees(Model model) {
        // Retrieve all stored trees as TreeEntities
        List<TreeEntity> treeEntities = treeService.getAllTreeEntities();
        model.addAttribute("trees", treeEntities);
        return "previous-trees";
    }
}
