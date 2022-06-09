package replace;

import java.util.HashMap;
import java.util.Map;

public class LFUCache {
    public static void main(String[] args) {
        LFUCache lfuCache = new LFUCache(10);
        String[] opName = {"put","put","put","put","put","get","put","get","get","put","get","put","put","put","get","put","get","get","get","get","put","put","get","get","get","put","put","get","put","get","put","get","get","get","put","put","put","get","put","get","get","put","put","get","put","put","put","put","get","put","put","get","put","put","get","put","put","put","put","put","get","put","put","get","put","get","get","get","put","get","get","put","put","put","put","get","put","put","put","put","get","get","get","put","put","put","get","put","put","put","get","put","put","put","get","get","get","put","put","put","put","get","put","put","put","put","put","put","put"};
        int[][] ops = {{10,13},{3,17},{6,11},{10,5},{9,10},{13},{2,19},{2},{3},{5,25},{8},{9,22},{5,5},{1,30},{11},{9,12},{7},{5},{8},{9},{4,30},{9,3},{9},{10},{10},{6,14},{3,1},{3},{10,11},{8},{2,14},{1},{5},{4},{11,4},{12,24},{5,18},{13},{7,23},{8},{12},{3,27},{2,12},{5},{2,9},{13,4},{8,18},{1,7},{6},{9,29},{8,21},{5},{6,30},{1,12},{10},{4,15},{7,22},{11,26},{8,17},{9,29},{5},{3,4},{11,30},{12},{4,29},{3},{9},{6},{3,4},{1},{10},{3,29},{10,28},{1,20},{11,13},{3},{3,12},{3,8},{10,9},{3,26},{8},{7},{5},{13,17},{2,27},{11,15},{12},{9,19},{2,15},{3,16},{1},{12,17},{9,1},{6,19},{4},{5},{5},{8,1},{11,7},{5,2},{9,28},{1},{2,2},{7,4},{4,22},{7,24},{9,26},{13,28},{11,26}};
        for (int i = 0; i < opName.length; i++) {
            if ("put".equals(opName[i])) {
                lfuCache.put(ops[i][0], ops[i][1]);
                System.out.println("null");
            } else {
                System.out.println(lfuCache.get(ops[i][0]));
            }
        }
    }

    class Node {
        int key, value, freq;
        Node prev, next;

        public Node(int f) {
            this(-1, -1, f);
        }

        public Node(int k, int v, int f) {
            this.key = k;
            this.value = v;
            this.freq = f;
        }
    }

    class DoublyLinkedList {
        private Node head, tail;

        public DoublyLinkedList(int f) {
            head = new Node(f);
            tail = new Node(f);
            head.next = tail;
            tail.prev = head;
        }

        public void add(int key, int value, int freq) {
            Node node = new Node(key, value, freq);
            node.prev = head;
            node.next = head.next;
            node.next.prev = node;
            node.prev.next = node;
            keyMap.put(key, node);
            minFreq = Math.min(minFreq, freq);
        }

        public void delete(Node cur) {
            keyMap.remove(cur.key);
            cur.prev.next = cur.next;
            cur.next.prev = cur.prev;
            if (head.next == tail && head.freq == minFreq) {
                minFreq++;
            }
        }

        public void deleteLast() {
            delete(tail.prev);
        }
    }

    private int cap, minFreq = 1;
    private Map<Integer, Node> keyMap = new HashMap<>();
    private Map<Integer, DoublyLinkedList> freqMap = new HashMap<>();

    private int update(int key) {
        Node cur = keyMap.get(key);
        DoublyLinkedList deleteList = freqMap.get(cur.freq);
        deleteList.delete(cur);
        int newfreq = cur.freq + 1;
        DoublyLinkedList addList = freqMap.getOrDefault(newfreq, new DoublyLinkedList(newfreq));
        addList.add(cur.key, cur.value, newfreq);
        freqMap.put(newfreq, addList);
        return cur.value;
    }

    public LFUCache(int capacity) {
        this.cap = capacity;
    }

    public int get(int key) {
        if (!keyMap.containsKey(key)) {
            return -1;
        }
        return update(key);
    }

    public void put(int key, int value) {
        if (cap == 0) {
            return;
        }
        if (keyMap.containsKey(key)) {
            Node node = keyMap.get(key);
            node.value = value;
            update(key);
            return;
        }
        if (keyMap.size() == cap) {
            freqMap.get(minFreq).deleteLast();
        }
        DoublyLinkedList addList = freqMap.getOrDefault(1, new DoublyLinkedList(1));
        addList.add(key, value, 1);
        freqMap.put(1, addList);
    }
}
