package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChildModificationTest {
    private void check(Parent candidate) {
        assertEquals(TargetString.VALUE, candidate.exampleMethod());
    }

    @Test
    void testModifiedByInheritance() {
        check(new ModifiedByInheritance());
    }

    @Test
    void testModifiedByReflection() {
        check(new ModifiedByReflection());
    }

    @Test
    void testModifiedByCopyPaste() {
        check(new ModifiedByCopyPaste());
    }

    @Test
    void testModifiedByMethodHandles() {
        check(new ModifiedByMethodHandles());
    }

    @Test
    void testModifiedByMethodHandlesInKotlin() {
        check(new ModifiedByMethodHandlesInKotlin());
    }
}
