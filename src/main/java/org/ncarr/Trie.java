package org.ncarr;

import java.util.ArrayList;
import java.util.List;

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String title, String value) {
        TrieNode node = root;
        for (char ch : title.toCharArray()) {
            node.children.putIfAbsent(ch, new TrieNode());
            node = node.children.get(ch);
            node.value = value;
        }
        node.isEndOfWord = true;
    }

    public void insertValueAtEnd(String title, String value) {
        TrieNode node = root;
        for (char ch : title.toCharArray()) {
            node.children.putIfAbsent(ch, new TrieNode());
            node = node.children.get(ch);
        }
        node.value = value;
        node.isEndOfWord = true;
    }

    public String getExactValue(String title) {
        TrieNode node = root;
        for (char ch : title.toCharArray()) {
            if (!node.children.containsKey(ch)) {
                return null;
            }
            node = node.children.get(ch);
        }
        return node.isEndOfWord ? node.value : null;
    }

    public String getValue(String title, int matchDepth) {
        for (int i = 0; i < title.length(); i++) {
            TrieNode node = root;
            int depth = 0;
            for (char ch : title.substring(i).toCharArray()) {
                if (node.children != null && !node.children.containsKey(ch)) {
                    break;
                }
                node = node.children.get(ch);
                depth++;
                if (depth >= matchDepth && node.value != null) {
                    return node.value;
                }
            }
            if (node != null && node.isEndOfWord) {
                return node.value;
            }
        }
        return null;
    }

    public List<String> getAllValues(String prefix, int matchDepth) {
        List<String> results = new ArrayList<>();
        TrieNode node = root;
        int depth = 0;
        for (char ch : prefix.toCharArray()) {
            if (!node.children.containsKey(ch)) {
                return results;
            }
            node = node.children.get(ch);
            depth++;
            if (depth >= matchDepth) {
                break;
            }
        }
        findAllValues(node, results);
        return results;
    }

    private void findAllValues(TrieNode node, List<String> results) {
        if (node.isEndOfWord) {
            results.add(node.value);
        }
        for (TrieNode child : node.children.values()) {
            findAllValues(child, results);
        }
    }
}
