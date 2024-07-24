package io.github.willdomkahari.generator;

/**
 * @author <a href="mailto:willdomkahari@gmail.com">Willdom Kahari</a>
 */
public class GeneratorRule {
    private final Characters characters;
    private final int length;

    public GeneratorRule(final Characters characters) {
        this.characters = characters;
        this.length = 1; // Default length
    }

    public GeneratorRule(final Characters characters, final int length) {
        this.characters = characters;
        this.length = length;
    }
    public int getLength() {
        return length;
    }

    public String getCharacterSet() {
        return characters.getCharacters();
    }
}
