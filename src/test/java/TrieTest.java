import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ncarr.Trie;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TrieTest {
    private Trie trie;
    @BeforeEach
    public void setup() {
        trie = new Trie();
    }

    @Test
    public void testInsert() {
        trie.insert("test", "value");
        assertEquals("value", trie.getExactValue("test"));
    }

    @Test
    public void testInsertValueAtEnd() {
        trie.insertValueAtEnd("test", "value");
        assertEquals("value", trie.getExactValue(("test")));
    }

    @Test
    public void testGetExactValue() {
        trie.insert("test", "value");
        assertEquals("value", trie.getExactValue("test"));
        assertNull(trie.getExactValue("nonexistent"));
    }

    @Test
    public void testGetValue() {
        trie.insert("test", "value");
        assertEquals("value", trie.getValue("test", 4));
        assertNull(trie.getValue("nonexistent", 4));
    }

    @Test
    public void testGetAllValues() {
        trie.insert("tests", "value1");
        trie.insert("testing", "value2");
        trie.insert("tester", "value3");

        List<String> values = trie.getAllValues("test", 1);
        assertTrue(values.contains("value1"));
        assertTrue(values.contains("value2"));
        assertTrue(values.contains("value3"));
        assertEquals(3, values.size());
    }
}
