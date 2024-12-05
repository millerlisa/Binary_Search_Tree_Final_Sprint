package com.keyin.tests;
import com.keyin.model.TreeNode;
import com.keyin.service.BinarySearchTreeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BstApplicationTests {
    @Autowired
    private BinarySearchTreeService binarySearchTreeService;

    @Test
    public void testTreeInsertion() {
        TreeNode root = null;
        root = binarySearchTreeService.insert(root, 5);
        root = binarySearchTreeService.insert(root, 3);
        root = binarySearchTreeService.insert(root, 7);
        assertNotNull(root);
        assertEquals(5, root.getValue());
        assertEquals(3, root.getLeft().getValue());
        assertEquals(7, root.getRight().getValue());
    }

    @Test
    public void testTreeRetrieval() {
        TreeNode root = new TreeNode(5);
        binarySearchTreeService.saveTree(root);
        List<TreeNode> trees = binarySearchTreeService.getAllTrees();
        assertFalse(trees.isEmpty());
    }

    @Test
    public void testTreeBalancing() {
        // Implement balancing test if applicable
    }
}