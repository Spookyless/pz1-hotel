package net.spookyless.util;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("MyMap tests")
public class MyMapTest {
    private MyMap<Integer, String> myMap = new MyMap<>();

    @BeforeEach
    public void beforeEach() {
        myMap = new MyMap<>();
        myMap.put(1, "a");
        myMap.put(2, "b");
        myMap.put(3, "c");
    }

    @Test
    public void ShouldCheckIfElementsAreContained() {
        myMap.put(1, "a");
        myMap.put(2, "b");
        myMap.put(3, "c");

        assertTrue(myMap.contains(1));
        assertTrue(myMap.contains(2));

        assertFalse(myMap.contains(4));
        assertFalse(myMap.contains(-1));
    }

    @Nested
    public class ShouldCorrectlyPutElementsIntoTheMap {
        @Test
        public void whenEntriesAreDefined() {
            boolean result = myMap.put(4, "d");

            assertTrue(result);
            assertTrue(myMap.contains(4));
        }

        @Test
        public void whenEntriesAreNull() {
            boolean result1 = myMap.put(null, "c");
            boolean result2 = myMap.put(3, null);

            assertFalse(result1);
            assertFalse(result2);
            assertEquals(myMap.get(3), "c");
        }

    }

    @Nested
    public class ShouldCorrectlyRemoveElementsFromTheMap {
        @Test
        public void whenKeyIsDefined() {
            boolean result1 = myMap.remove(3);
            boolean result2 = myMap.remove(3);

            assertTrue(result1);
            assertFalse(result2);
            assertFalse(myMap.contains(3));
        }

        @Test
        public void whenKeyIsNull() {
            boolean result = myMap.remove(null);

            assertFalse(result);
        }
    }

    @Nested
    public class GetTest {
        @Test
        public void whenKeyIsDefined() {
            String result1 = myMap.get(3);
            String result2 = myMap.get(4);

            assertEquals(result1, "c");
            assertNull(result2);
        }

        @Test
        public void whenKeyIsNull() {
            String result = myMap.get(null);

            assertNull(result);
        }
    }

    @Nested
    public class KeysTest {
        @Test
        public void whenMapIsEmpty() {
            MyMap<Integer, String> myMap2 = new MyMap<>();

            List<Integer> expectedKeys = Collections.emptyList();

            assertEquals(expectedKeys, myMap2.keys());
        }

        @Test
        public void whenMapIsFull() {
            List<Integer> expectedKeys = Arrays.asList(1, 2, 3);

            assertEquals(expectedKeys, myMap.keys());
        }

        @Test
        public void keysShouldBeCloned() {
            List<Integer> keys = myMap.keys();
            keys.add(4);

            assertFalse(myMap.contains(4));
        }
    }
}
