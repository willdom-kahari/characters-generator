package io.github.willdomkahari.generator;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.CharBuffer;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author <a href="mailto:willdomkahari@gmail.com">Willdom Kahari</a>
 */
public class CharacterGenerator {
    private final Random random;

    public CharacterGenerator(final Random random) {
        this.random = random;
    }

    public CharacterGenerator(){
        this(new SecureRandom());
    }

    public String generateCharacters(final int length, final GeneratorRule... rules)
    {
        return generateCharacters(length, Arrays.asList(rules));
    }

    public String generateCharacters(final int length, final List<GeneratorRule> rules)
    {
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
                        buffer);
                allChars.append(rule.getCharacterSet());
            }
        }
        fillRandomCharacters(allChars, length - buffer.position(), buffer);
        // cast to Buffer prevents NoSuchMethodError when compiled on JDK9+ and run on JDK8
        ((Buffer) buffer).flip();
        randomize(buffer);
        return buffer.toString();

    }

    protected void fillRandomCharacters(final CharSequence source, final int count, final Appendable target)
    {
        for (int i = 0; i < count; i++) {
            try {
                target.append(source.charAt(random.nextInt(source.length())));
            } catch (IOException e) {
                throw new RuntimeException("Error appending characters.", e);
            }
        }
    }

    protected void randomize(final CharBuffer buffer)
    {
        char c;
        int n;
        for (int i = buffer.position(); i < buffer.limit(); i++) {
            n = random.nextInt(buffer.length());
            c = buffer.get(n);
            buffer.put(n, buffer.get(i));
            buffer.put(i, c);
        }
    }

}
