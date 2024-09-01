package io.github.willdomkahari.generator;

/**
 * Defines the generator rule required when generating characters.
 *
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public class GeneratorRule {
    private final ICharacters characters;
    private final int length;

    /**
     * Creates a new generator rule with default length of 1
     * @param characters The characters required to make a rule
     */
    public GeneratorRule(final ICharacters characters) {
        this.characters = characters;
        this.length = 1; // Default length
    }

    /**
     * Instantiate the generator rule required when generating characters
     *
     * @param characters The characters required to make a rule
     * @param length The number of characters desired
     */
    public GeneratorRule(final ICharacters characters, final int length) {
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
     * @return String - Representation of the character set specified by the rule
     */
    public String getCharacterSet() {
        return characters.getCharacters();
    }
    static GenerateRule create(){
        return new GenerateRule();
    }

    static class GenerateRule{
        private int length;
        private ICharacters c;
        GenerateRule(){
            this.length = 1; // Default length
        }
        public GenerateRule withLength(final int length) {
            this.length = length;
            return this;
        }
        public GenerateRule withCharacters(final ICharacters characters) {
            this.c = characters;
            return this;
        }
        public GeneratorRule build() {
            return new GeneratorRule(this.c, this.length);
        }

    }
}
