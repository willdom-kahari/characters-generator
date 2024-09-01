package io.github.willdomkahari.generator;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Tests for The GeneratorRule
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
class GeneratorRuleTest {
    private static GeneratorRule dynamicRule;
    private static GeneratorRule staticRule;
    @BeforeAll
    static void initialSetup(){
        dynamicRule = new GeneratorRule(Characters.SMALL, 5);
        staticRule = new GeneratorRule(Characters.SMALL);
    }

    @Test
    void length_of_the_rule_must_5() {
        assertEquals(5, dynamicRule.getLength());
    }

    @Test
    void characters_defined_by_the_rule_must_be_equal_to_those_in_the_characters_enum() {
        assertEquals(Characters.SMALL.getCharacters(), dynamicRule.getCharacterSet());
    }
    @Test
    void characters_defined_by_the_static_rule_must_be_equal_to_those_in_the_characters_enum() {
        assertEquals(Characters.SMALL.getCharacters(), staticRule.getCharacterSet());
    }
    @Test
    void length_of_the_static_rule_must_be_1() {
        assertEquals(1, staticRule.getLength());
    }

    @Test
    void rule_defined_by_builder() {
        GeneratorRule rule = GeneratorRule.create()
               .withLength(5)
               .withCharacters(Characters.SMALL)
               .build();
        assertEquals(5, rule.getLength());
        assertEquals(Characters.SMALL.getCharacters(), rule.getCharacterSet());
    }

    @Test
    void rule_defined_by_builder_no_length() {
        GeneratorRule rule = GeneratorRule.create()
                .withCharacters(Characters.SMALL)
                .build();
        assertEquals(1, rule.getLength());
        assertEquals(Characters.SMALL.getCharacters(), rule.getCharacterSet());
    }
}
