package io.github.willdomkahari.generator;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.CharBuffer;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Responsible for generating random characters based on the rules provided
 *
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public class CharacterGenerator {
    private final Random random;


    /**
     * Instantiate a new CharacterGenerator with the provided randomizer
     *
     * @param random - The randomizer to use for generating characters
     */
    public CharacterGenerator(final Random random) {
        this.random = new Random(random.nextLong());
    }

    /**
     * Instantiate a new CharacterGenerator with default randomizer as the <code>SecureRandom</code>
     */
    public CharacterGenerator() {
        this(new SecureRandom());
    }

    /**
     * Generates characters using the provided rules
     *
     * @param length - The length of the returned generated characters
     * @param rules  - The rules to use for generating characters
     * @return <code>String</code> - A string of characters based on the rules and length specified
     */
    public String generateCharacters(final int length, final GeneratorRule... rules) {
        return generateCharacters(length, Arrays.asList(rules));
    }
    static GenerateCharacters generateCharacters() {
        return new GenerateCharacters();
    }

    static class GenerateCharacters{
        private int length;
        private final List<GeneratorRule> rules = new ArrayList<>();
        GenerateCharacters(){
        }
        public GenerateCharacters withLength(final int length) {
            this.length = length;
            return this;
        }
        public GenerateCharacters withRule(final GeneratorRule rule) {
            this.rules.add(rule);
            return this;
        }
        public GenerateCharacters withRules(final List<GeneratorRule> rules) {
            this.rules.addAll(rules);
            return this;
        }
        public GenerateCharacters withRules(final GeneratorRule... rules) {
            this.rules.addAll(Arrays.asList(rules));
            return this;
        }
        public String build() {
            return new CharacterGenerator().generateCharacters(this.length, this.rules);
        }
    }



    /**
     * Generates characters using the provided rules
     *
     * @param length - The length of the returned generated characters
     * @param rules  - The rules to use for generating characters
     * @return <code>String</code> - A string of characters based on the rules and length specified
     */
    @SuppressWarnings("RedundantCast")
    public String generateCharacters(final int length, final List<GeneratorRule> rules) {
        if (length <= 0) {
            throw new IllegalArgumentException("length must be greater than 0");
        }
        if (rules == null || rules.isEmpty()) {
            throw new IllegalArgumentException("At least one rule must be provided");
        }

        final StringBuilder allChars = new StringBuilder();

        final CharBuffer buffer = CharBuffer.allocate(length);

        for (GeneratorRule rule : rules) {
            fillRandomCharacters(
                    rule.getCharacterSet(),
                    Math.min(length, rule.getLength()),
                    buffer
            );
            allChars.append(rule.getCharacterSet());
        }

        fillRandomCharacters(allChars, length - buffer.position(), buffer);
        // cast to Buffer prevents NoSuchMethodError when compiled on JDK9+ and run on JDK8
        ((Buffer) buffer).flip();
        shuffle(buffer);
        return buffer.toString();

    }

    private void fillRandomCharacters(final CharSequence source, final int count, final Appendable target) {
        for (int i = 0; i < count; i++) {
            try {
                target.append(source.charAt(random.nextInt(source.length())));
            } catch (IOException e) {
                throw new AppendingCharactersException("Error appending characters.", e);
            }
        }
    }

    private void shuffle(final CharBuffer buffer) {
        int bufferLength = buffer.limit() - buffer.position();
        char[] chars = new char[bufferLength];

        // Copy the buffer contents to the array
        for (int i = 0; i < bufferLength; i++) {
            chars[i] = buffer.get(buffer.position() + i);
        }

        int j;
        char temp;

        // Shuffle the array
        for (int i = bufferLength - 1; i > 0; i--) {
            j = random.nextInt(i + 1);
            temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }

        // Copy the shuffled array back to the buffer
        for (int i = 0; i < bufferLength; i++) {
            buffer.put(buffer.position() + i, chars[i]);
        }
    }

}
