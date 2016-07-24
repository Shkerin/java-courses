package ru.parsentev.lesson_6;

/**
 * Описывает поведение питомца.
 *
 * @author vlad
 * @since 24.07.2016
 */
public interface Pet {
    /**
     * Издать звук.
     */
    void makeSound();

    /**
     * Имя питомца.
     */
    String getName();
}
