package ru.parsentev.lesson_6;

/**
 * Реализация кота.
 *
 * @author vlad
 * @since 24.07.2016
 */
public class Cat extends Animal {

    /**
     * Конструктор.
     *
     * @param name имя животного
     */
    public Cat(final String name) {
        super(name);
    }

    /**
     * Поймать мышь.
     */
    public void catchMouse() {
        //empty
    }

    /**
     * Получить имя кота.
     *
     * @return имя кота.
     */
    @Override
    public String getName() {
        return null;
    }

    @Override
    public void makeSound() {
        System.out.println(String.format("May %s", this.getName()));
    }
}
