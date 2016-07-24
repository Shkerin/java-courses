package ru.lessons.lesson_13;

/**
 * TODO: comment
 * @author parsentev
 * @since 08.04.2015
 */
public interface UserAction {

    void initGame();

    void select(int x, int y, boolean bomb);
}
