package io.github.willdomkahari.generator;

/**
 * Defines the generator rule required when generating characters.
 *
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public class GeneratorRule {
    private final Characters characters;
    private final int length;

    /**
     * Creates a new generator rule with default length of 1
     * @param characters - The characters required to make a rule
     */
    public GeneratorRule(final Characters characters) {
        this.characters = characters;
        this.length = 1; // Default length
    }

    /**
     * Instantiate the generator rule required when generating characters
     *
     * @param characters - The characters required to make a rule
     * @param length - The number of characters desired
     */
    public GeneratorRule(final Characters characters, final int length) {
        this.characters = characters;
        this.length = length;
    }

    /**
     * Retrieves the length of the characters defined by the rule
     *
     * @return int - The number of characters allowed by a rule
     */
    public int getLength() {
        return length;
    }

    /**
     * Returns the character set defined by the rule
     *
     * @return String - Representation of the character set specified by the rule
     */
    public String getCharacterSet() {
        return characters.getCharacters();
    }
}
