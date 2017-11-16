
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BinaryTreeTest {
    @Test
    void subSet() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.add(10);
        tree.add(5);
        tree.add(7);
        tree.add(3);
        tree.add(1);
        tree.add(4);
        tree.add(8);
        tree.add(15);
        tree.add(20);
        SortedSet<Integer> sortedSet = new TreeSet<>();
        assertEquals(sortedSet, tree.subSet(0, 0));
        assertEquals(sortedSet, tree.subSet(20, 20));
        sortedSet.add(15);
        sortedSet.add(10);
        assertEquals(sortedSet, tree.subSet(10, 20));
        assertEquals(sortedSet, tree.subSet(9, 20));
        sortedSet.add(1);
        sortedSet.add(3);
        sortedSet.add(4);
        sortedSet.add(5);
        sortedSet.add(7);
        sortedSet.add(8);
        assertEquals(sortedSet, tree.subSet(1, 20));
        assertEquals(sortedSet, tree.subSet(0, 20));
        assertEquals(sortedSet, tree.subSet(-10, 20));
        sortedSet.remove(20);
        assertEquals(sortedSet, tree.subSet(1, 19));
        assertEquals(sortedSet, tree.subSet(0, 19));
        sortedSet.remove(15);
        assertEquals(sortedSet, tree.subSet(1, 15));
    }
    @Test
    void throwIllegal() throws IllegalArgumentException {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.add(10);
        tree.add(5);
        tree.add(7);
        tree.add(3);
        tree.add(1);
        tree.add(4);
        tree.add(8);
        tree.add(15);
        tree.add(20);
        SortedSet<Integer> sortedSet = tree.subSet(1,10);
        assertThrows(IllegalArgumentException.class,()-> sortedSet.add(15));
        assertThrows(IllegalArgumentException.class,()-> sortedSet.add(99));
        assertThrows(IllegalArgumentException.class,()-> sortedSet.add(9));
        assertThrows(IllegalArgumentException.class,()-> sortedSet.add(10));
        sortedSet.remove(4);
        assertEquals(false, tree.contains(4));
        System.out.println(sortedSet);
        System.out.println(tree);
    }



    @Test
    void remove() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        List<Integer> list = Collections.singletonList(1);
        tree.addAll(list);
        tree.remove(1);
        BinaryTree<Integer> treeTest = new BinaryTree<>();
        List<Integer> listTest = Collections.emptyList();
        treeTest.addAll(listTest);
        assertEquals(treeTest, tree);
        tree = new BinaryTree<>();
        list = Arrays.asList(1, 2, 0);
        tree.addAll(list);
        tree.remove(1);
        treeTest = new BinaryTree<>();
        listTest = Arrays.asList(2, 0);
        treeTest.addAll(listTest);
        assertEquals(treeTest, tree);
        tree = new BinaryTree<>();
        list = Arrays.asList(1, 2, 3, 4);
        tree.addAll(list);
        tree.remove(1);
        treeTest = new BinaryTree<>();
        listTest = Arrays.asList(2, 3, 4);
        treeTest.addAll(listTest);
        assertEquals(treeTest, tree);
        tree = new BinaryTree<>();
        list = Arrays.asList(1, 0, -1, -2);
        tree.addAll(list);
        tree.remove(1);
        treeTest = new BinaryTree<>();
        listTest = Arrays.asList(0, -1, -2);
        treeTest.addAll(listTest);
        assertEquals(treeTest, tree);
        tree = new BinaryTree<>();
        list = Arrays.asList(5, 4, 6, 3, 7);
        tree.addAll(list);
        tree.remove(7);
        treeTest = new BinaryTree<>();
        listTest = Arrays.asList(5, 4, 6, 3);
        treeTest.addAll(listTest);
        assertEquals(treeTest, tree);
        tree = new BinaryTree<>();
        list = Arrays.asList(1, 2, 3, 4);
        tree.addAll(list);
        tree.remove(2);
        treeTest = new BinaryTree<>();
        listTest = Arrays.asList(1, 3, 4);
        treeTest.addAll(listTest);
        assertEquals(treeTest, tree);
        tree = new BinaryTree<>();
        list = Arrays.asList(5, 4, 3, 2, 1);
        tree.addAll(list);
        tree.remove(4);
        treeTest = new BinaryTree<>();
        listTest = Arrays.asList(5, 3, 2, 1);
        treeTest.addAll(listTest);
        assertEquals(treeTest, tree);
        tree = new BinaryTree<>();
        list = Arrays.asList(5, 7, 3, 1, 2, 4, 9, 8, 6);
        tree.addAll(list);
        tree.remove(5);
        treeTest = new BinaryTree<>();
        listTest = Arrays.asList(6, 7, 9, 8, 3, 4, 1, 2);
        treeTest.addAll(listTest);
        assertEquals(treeTest, tree);
    }

}