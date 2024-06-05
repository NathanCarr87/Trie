package org.ncarr;

import java.util.HashMap;

public class TrieNode {
    public boolean isEndOfWord;
    public HashMap<Character,TrieNode> children;
    public String value;

    TrieNode() {
        children = new HashMap<>();
        isEndOfWord = false;
        value = null;
    }
}
