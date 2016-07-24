package ru.parsentev.lesson_6;

/**
 * Описывает потерянного питомца.
 *
 * @author vlad
 * @since 24.07.2016
 */
public abstract class LostPets {

    /**
     * Опасен питомец или нет.
     * @return true если опасен
     */
    public boolean isDanger() {
        return true;
    }

    /**
     * Вернуть адрес если поймали.
     * @return адресс
     */
    abstract String getWhereCatch();
}
