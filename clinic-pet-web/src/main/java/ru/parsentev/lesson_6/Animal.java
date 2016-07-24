package ru.parsentev.lesson_6;

/**
 * Базовый класс для животных.
 *
 * @author parsentev
 * @since 07.04.2015
 */
public class Animal implements Pet {

    /**
     * Имя.
     */
    private final String name;

    /**
     * Конструктор.
     * @param name имя
     */
    public Animal(String name) {
        this.name = name;
    }

    /**
     * Подать голос.
     */
    @Override
    public void makeSound() {
        System.out.println(String.format("%s say : %s", this.name, "beep"));
    }

    /**
     * Получить имя животного.
     * @return имя животного.
     */
    @Override
    public String getName() {
        return this.name;
    }

    private boolean isHungry() {
        return true;
    }
}
