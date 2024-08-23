# Characters Generator

![Maven Central Version](https://img.shields.io/maven-central/v/io.github.willdom-kahari/characters-generator)

## Summary

It defines a way to generate random characters based on a specific ruleset.
It is inspired by [passay](https://www.passay.org) but not as comprehensive.
It is an entry, light weight, characters generator library.

## Using Characters Generator

Characters Generator  is available on Maven Central.
* Add the following artifact to your maven build:

```
<dependency>
    <groupId>io.github.willdom-kahari</groupId>
    <artifactId>characters-generator</artifactId>
    <version>0.4.0</version>
</dependency>
```

* For your specific dependency manager visit [characters-generator](https://central.sonatype.com/artifact/io.github.willdom-kahari/characters-generator) on maven central.

## Examples

* Define a rule

```java
GeneratorRule rule = new GeneratorRule(Characters.SMALL);
```

* Initialise the CharacterGenerator

```java
CharacterGenerator generator = new CharacterGenerator();
```

* Get the generated random characters by invoking the CharacterGenerator with the desired length of characters

```java
String s = generator.generateCharacters(5, rule);
```


