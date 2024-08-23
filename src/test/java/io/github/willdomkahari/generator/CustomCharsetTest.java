package io.github.willdomkahari.generator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomCharsetTest {
    @Test
    void characters_defined_in_the_rule_must_be_equal_to_those_in_the_enum(){
        GeneratorRule rule = new GeneratorRule(CustomCharset.SPECIAL);
        String generated = new CharacterGenerator().generateCharacters(4,rule);
        assertEquals(4, generated.length());
        assertTrue(generated.matches("[!@#$%^&*()_+{}|:\"<>?,./]{4}"));
    }
}

enum CustomCharset implements ICharacters {
    SPECIAL("!@#$%^&*()_+{}|:\"<>?,./");
    private final String characters;
    CustomCharset(String chars){
        this.characters = chars;
    }

    @Override
    public String getCharacters() {
        return characters;
    }
}
