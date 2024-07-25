package io.github.willdomkahari.generator;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.CharBuffer;
import java.security.SecureRandom;
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

    public CharacterGenerator(final Random random) {
        this.random = random;
    }

    /**
     * Instantiate a new CharacterGenerator with default randomiser as the <code>SecureRandom</code>
     */
    public CharacterGenerator() {
        this(new SecureRandom());
    }

    public String generateCharacters(final int length, final GeneratorRule... rules) {
        return generateCharacters(length, Arrays.asList(rules));
    }


    public String generateCharacters(final int length, final List<GeneratorRule> rules) {
        if (length <= 0) {
            throw new IllegalArgumentException("length must be greater than 0");
        }

        final StringBuilder allChars = new StringBuilder();

        final CharBuffer buffer = CharBuffer.allocate(length);
        if (rules != null) {
            for (GeneratorRule rule : rules) {
                fillRandomCharacters(
                        rule.getCharacterSet(),
                        Math.min(length, rule.getLength()),
                        buffer
                );
                allChars.append(rule.getCharacterSet());
            }
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
        int length = buffer.limit() - buffer.position();
        char[] chars = new char[length];

        // Copy the buffer contents to the array
        for (int i = 0; i < length; i++) {
            chars[i] = buffer.get(buffer.position() + i);
        }

        int j;
        char temp;

        // Shuffle the array
        for (int i = length - 1; i > 0; i--) {
            j = random.nextInt(i + 1);
            temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }

        // Copy the shuffled array back to the buffer
        for (int i = 0; i < length; i++) {
            buffer.put(buffer.position() + i, chars[i]);
        }
    }

}
