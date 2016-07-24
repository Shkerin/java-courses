package ru.lessons.lesson_6;

/**
 * Реализация собаки.
 *
 * @author vlad
 * @since 24.07.2016
 */
public class Dog implements Pet {

    /**
     * Базовая реализация питомца.
     */
    private final Pet pet;

    public Dog(final Pet pet) {
        this.pet = pet;
    }

    @Override
    public void makeSound() {
        this.pet.makeSound();
        System.out.println("Gav, Gav");
    }

    @Override
    public String getName() {
        return this.pet.getName();
    }
}
