package io.github.willdomkahari.generator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the CharacterGenerator
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
class CharacterGeneratorTest {
    private static CharacterGenerator generator;
    @BeforeAll
    static void setUp() {
        generator = new CharacterGenerator();
    }

    @Test
    void throws_an_IllegalArgumentException_if_no_rule_is_specified() {
        // Given
        final int length = 10;
        assertThrows(IllegalArgumentException.class, ()-> generator.generateCharacters(length));
    }

    @Test
    void throws_an_IllegalArgumentException_if_length_is_less_than_zero() {
        // Given
        final int length = -11;
        GeneratorRule rule = new GeneratorRule(Characters.SMALL, 2);
        assertThrows(IllegalArgumentException.class, () -> generator.generateCharacters(length, rule));
    }
    @Test
    void throws_an_IllegalArgumentException_if_length_is_zero() {
        // Given
        final int length = 0;
        GeneratorRule rule = new GeneratorRule(Characters.SMALL, 2);
        assertThrows(IllegalArgumentException.class, ()-> generator.generateCharacters(length, rule));
    }

    @Test
    void defined_length_must_be_equal_to_the_returned_length_with_characters_defined() {
        final int length = 2;
        GeneratorRule rule = new GeneratorRule(Characters.SMALL, 2);
        String s = generator.generateCharacters(length, rule);
        assertEquals(length, s.length());
        assertTrue(s.matches("[a-z]{2}"));
    }

    @Test
    void the_length_of_the_generator_limits_the_returned_length() {
        final int length = 5;
        GeneratorRule rule = new GeneratorRule(Characters.NUMERIC);
        String s = generator.generateCharacters(length, rule);
        assertEquals(length, s.length());
        assertTrue(s.matches("[0-9]{5}"));
    }

    @Test
    void defined_length_must_be_equal_to_the_returned_length_with_characters_defined_with_the_fluent_api() {
        final int length = 5;
        String build = CharacterGenerator.generateCharacters()
                .withLength(length)
                .withRule(new GeneratorRule(Characters.SMALL, 2))
                .withRule(new GeneratorRule(Characters.NUMERIC, 3))
                .build();


        assertEquals(length, build.length());
        assertTrue(build.matches("[a-z0-9]+"));
    }
}
