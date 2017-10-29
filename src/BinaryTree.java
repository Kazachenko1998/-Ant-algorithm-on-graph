


import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

// Attention: comparable supported but comparator is not
@SuppressWarnings("WeakerAccess")
public class BinaryTree<T extends Comparable<T>> extends AbstractSet<T> implements SortedSet<T> {

    private static class Node<T> {
        T value;

        Node<T> left = null;

        Node<T> right = null;

        Node(T value) {
            this.value = value;
        }

    }

    private Node<T> root = null;

    private int size = 0;

    @Override
    public boolean add(T t) {
        Node<T> closest = find(t);
        int comparison = closest == null ? -1 : t.compareTo(closest.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<>(t);
        if (closest == null) {
            root = newNode;
        } else if (comparison < 0) {
            assert closest.left == null;
            closest.left = newNode;
        } else {
            assert closest.right == null;
            closest.right = newNode;
        }
        size++;
        return true;
    }

    public boolean checkInvariant() {
        return root == null || checkInvariant(root);
    }

    private boolean checkInvariant(Node<T> node) {
        Node<T> left = node.left;
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) return false;
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
    }

    private Node<T> findParent(T value) {
        if (root == null || (root.value == value)) return null;
        return findParent(value, root);
    }

    private Node<T> findParent(T value, Node<T> start) {
        if ((start.right != null && start.right.value == value) || (start.left != null && start.left.value == value))
            return start;
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        } else if (comparison < 0) {
            if (start.left == null) return start;
            return findParent(value, start.left);
        } else {
            if (start.right == null) return start;
            return findParent(value, start.right);
        }
    }

    private void delete(Node<T> node) {
        put(node, null);
    }

    private void put(Node<T> node, Node<T> newNode) {
        Node<T> parent = findParent(node.value);
        if (parent == null) {
            if (newNode == null) {
                root = null;
                return;
            }
            root.value = newNode.value;
            root.right = newNode.right;
            root.left = newNode.left;
            return;
        }
        if (parent.right == node) parent.right = newNode;
        if (parent.left == node) parent.left = newNode;
    }

    @Override
    public boolean remove(Object o) {
        @SuppressWarnings("unchecked")
        Node<T> elem = find((T) o);

        if (elem == null || elem.value != o) {
            return false;
        }
        // no children
        if (elem.left == null && elem.right == null) {
            delete(elem);
        }
        // one child (right)
        else if (elem.left == null) {
            put(elem, elem.right);
        }
        // one child (left)
        else if (elem.right == null) {
            put(elem, elem.left);
        }
        // two children
        else {
            Node<T> temp = findMin(elem.right); // find minimal value of right sub tree
            T value = temp.value;
            remove(temp.value);
            size++;
            elem.value = value;
        }
        size--;
        return true;
    }

    private Node<T> findMin(Node<T> root) {
        if (root == null) {
            return null; // or undefined.
        }
        if (root.left != null) {
            return findMin(root.left); // left tree is smaller
        }
        return root;
    }

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }

    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }

    private Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        } else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        } else {
            if (start.right == null) return start;
            return find(start.right, value);
        }
    }

    public class BinaryTreeIterator implements Iterator<T> {

        private Node<T> current = null;

        private BinaryTreeIterator() {
        }

        private Node<T> findNext() {
            return null;
        }

        @Override
        public boolean hasNext() {
            return findNext() != null;
        }

        @Override
        public T next() {
            current = findNext();
            if (current == null) throw new NoSuchElementException();
            return current.value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    @Override
    public int size() {
        return size;
    }


    @Nullable
    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    @NotNull
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        return new TreeSet<>(subSet(root, fromElement, toElement, new TreeSet<>())).
                subSet(fromElement, true, toElement,false);
    }

    public SortedSet<T> subSet(Node<T> root, T fromElement, T toElement, SortedSet<T> sortedSet) {
        if (root == null) return sortedSet;
        int moreStart = root.value.compareTo(fromElement);
        int lessFinish = toElement.compareTo(root.value);
        if (moreStart >= 0 && lessFinish > 0) {
            sortedSet.add(root.value);
            subSet(root.left, fromElement, toElement, sortedSet);
            subSet(root.right, fromElement, toElement, sortedSet);
        } else if (moreStart > 0) {
            subSet(root.left, fromElement, toElement, sortedSet);
        } else {
            subSet(root.right, fromElement, toElement, sortedSet);
        }
        return sortedSet;
    }

    @NotNull
    @Override
    public SortedSet<T> headSet(T toElement) {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public SortedSet<T> tailSet(T fromElement) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T first() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    @Override
    public T last() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }


    @Override
    public String toString() {
        List<List<Object>> str = toStr(root, 0, new ArrayList<>());
        StringBuilder result = new StringBuilder();
        int i = 6;
        for (List<Object> aList : str) {
            i--;
            result.append(spaceAdd((int) Math.pow(2.0, (double) i - 1)));
            for (Object bList : aList) {
                result.append(bList).append(spaceAdd((int) Math.pow(2.0, (double) i) - 1));
            }
            result.append("\n");
        }
        return result.toString();
    }

    @NotNull
    private String spaceAdd(int i) {
        StringBuilder result = new StringBuilder();
        for (; i > 0; i--)
            result.append(" ");
        return result.toString();
    }

    private void addNull(int depth, List<List<Object>> list) {
        if (depth >= 7) return;
        if (list.size() <= depth) list.add(new ArrayList<>());
        list.get(depth).add(" ");
        addNull(depth + 1, list);
        addNull(depth + 1, list);
    }

    public List<List<Object>> toStr(Node<T> root, int depth, List<List<Object>> list) {
        if (depth >= 11) return list;
        if (list.size() <= depth) list.add(new ArrayList<>());
        if (root == null) {
            list.get(depth).add(" ");
            addNull(depth + 1, list);
            addNull(depth + 1, list);
            return list;
        }
        if (list.size() <= depth) list.add(new ArrayList<>());
        list.get(depth).add(root.value);
        toStr(root.left, depth + 1, list);
        toStr(root.right, depth + 1, list);
        return list;
    }

}
