package com.tubi.movies.util;

import androidx.annotation.VisibleForTesting;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUCache<K, V> {
    class Node<T, U> {
        T key;
        U value;

        public Node(T key, U value) {
            this.key = key;
            this.value = value;
        }
    }

    LinkedList<Node> list = new LinkedList<>();
    Map<K, Node> map = new HashMap<>();

    int capacity;
    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    public int size;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
    }

    public V get(K key) {
        Node<K, V> p = map.get(key);
        if (p != null) {
            list.remove(p);
            list.addLast(p);
            return p.value;
        }

        return null;
    }

    public void add(K key, V value) {
        if (!map.containsKey(key) && size == capacity) { // insert new and full cache
            // pick a victim -> first item
            Node p = list.removeFirst();

            // clear from the map
            map.remove(p.key);
        } else if (map.containsKey(key)) {
            // remove node from the list
            list.remove(map.get(key));
        } else {
            size++;
        }

        Node newNode = new Node(key, value);

        // add node to end of list
        list.addLast(newNode);

        // put new data to map
        map.put(key, newNode);
    }

    public boolean isValid(K key, V value) {
        V temp = get(key);
        return value.equals(temp) ? true : false;
    }
}



/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */