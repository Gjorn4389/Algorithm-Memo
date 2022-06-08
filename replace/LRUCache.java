package replace;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    public static void main(String[] args) {
        LRUCache lru = new LRUCache(3);
        lru.put(1, 1);
        lru.put(2, 2);
        lru.put(3, 3);
        lru.put(4, 4);
        System.out.println(lru.get(4));
        System.out.println(lru.get(3));
        System.out.println(lru.get(2));
        System.out.println(lru.get(1));
        lru.put(5, 5);
        System.out.println(lru.get(1));
        System.out.println(lru.get(2));
        System.out.println(lru.get(3));
        System.out.println(lru.get(4));
        System.out.println(lru.get(5));
    }

    class Node {
        int key, value;
        Node prev, next;
        public Node(int x, int y) {
            this.key = x;
            this.value = y;
        }
    }
    private Map<Integer, Node> map = new HashMap<>();
    private Node head = new Node(-1, -1), tail = new Node(-2, -1);
    private int cap;

    private void delete(Node cur) {
        cur.prev.next = cur.next;
        cur.next.prev = cur.prev;
        map.remove(cur.key);
    }

    private void add(int key, int value) {
        Node cur = new Node(key, value);
        cur.prev = head;
        cur.next = head.next;
        cur.prev.next = cur;
        cur.next.prev = cur;
        map.put(key, cur);
    }

    private void update(int key, int value) {
        if (map.containsKey(key)) {
            delete(map.get(key));
        } else if (map.size() == cap) {
            delete(tail.prev);
        }
        add(key, value);
    }

    public LRUCache(int capacity) {
        this.cap = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node cur = map.get(key);
            update(key, cur.value);
            return cur.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        update(key, value);
    }
}
