package com.keyin.tests;

import com.keyin.model.TreeNode;
import com.keyin.service.TreeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BstApplicationTests {

    @Autowired
    private TreeService binarySearchTreeService;

    @Test
    public void testTreeSerialization() {
        TreeNode root = null;
        root = binarySearchTreeService.insert(root, 10);
        root = binarySearchTreeService.insert(root, 6);
        root = binarySearchTreeService.insert(root, 15);

        String jsonTree = binarySearchTreeService.serializeTree(root);

        assertNotNull(jsonTree, "Serialized tree JSON should not be null.");
        assertTrue(jsonTree.contains("\"value\":10"));
        assertTrue(jsonTree.contains("\"left\":{\"value\":6"));
        assertTrue(jsonTree.contains("\"right\":{\"value\":15"));
    }

    @Test
    public void testDuplicateValueInsertion() {
        TreeNode root = null;
        root = binarySearchTreeService.insert(root, 10);
        root = binarySearchTreeService.insert(root, 6);
        root = binarySearchTreeService.insert(root, 15);
        root = binarySearchTreeService.insert(root, 10); // Duplicate value

        assertNotNull(root);
        assertEquals(10, root.getValue());
        assertEquals(6, root.getLeft().getValue());
        assertEquals(15, root.getRight().getValue());
        assertNull(root.getLeft().getLeft(), "Duplicates should not be inserted.");
    }

    @Test
    public void testNullRootHandling() {
        TreeNode root = null;
        root = binarySearchTreeService.insert(root, 5);

        assertNotNull(root, "Root should not be null after inserting the first value.");
        assertEquals(5, root.getValue());
    }
}
