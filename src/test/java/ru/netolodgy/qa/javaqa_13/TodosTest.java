package ru.netolodgy.qa.javaqa_13;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TodosTest {
    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    // Тест на метод поиска нахождения ровно одной задачи
    @Test
    public void shouldToSearchToTask() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected1 = {simpleTask};
        Task[] actual1 = todos.search("Позвонить");
        Assertions.assertArrayEquals(expected1, actual1);

        Task[] expected2 = {epic};
        Task[] actual2 = todos.search("Хлеб");
        Assertions.assertArrayEquals(expected2, actual2);

        Task[] expected3 = {meeting};
        Task[] actual3 = todos.search("приложения");
        Assertions.assertArrayEquals(expected3, actual3);

        Task[] expected4 = {meeting};
        Task[] actual4 = todos.search("НетоБанка");
        Assertions.assertArrayEquals(expected4, actual4);
    }

    // Тест на метод поиска когда находится несколько задач и 0 задач
    @Test
    public void shouldToSearchToTaskSeveralAndZero() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);
        Meeting meeting = new Meeting(
                555,
                "Позвонить родителям",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        // находится несколько задач (2шт)
        Task[] expected1 = {simpleTask, meeting};
        Task[] actual1 = todos.search("Позвонить");
        Assertions.assertArrayEquals(expected1, actual1);

        // Находиться 0 задач
        Task[] expected2 = {};
        Task[] actual2 = todos.search("кнопка");
        Assertions.assertArrayEquals(expected2, actual2);
    }

    @Test
    public void shouldToMatchesSimpleTask() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        Assertions.assertTrue(simpleTask.matches("родителям"));
        Assertions.assertFalse(simpleTask.matches("кнопка"));
    }

    @Test
    public void shouldToMatchesEpic() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Assertions.assertTrue(epic.matches("Молоко"));
        Assertions.assertFalse(epic.matches("кнопка"));
    }

    @Test
    public void shouldToMatchesMeeting() {
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Assertions.assertTrue(meeting.matches("Выкатка"));
        Assertions.assertTrue(meeting.matches("Приложение"));
        Assertions.assertFalse(meeting.matches("кнопка"));
    }
}
