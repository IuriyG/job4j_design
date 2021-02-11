package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>0. Что такое обобщенные типы (generics).</b>
 * Generics в данном случае это запись, заключенная в скобки <>, т.е. <String>.
 * Это означает, что в коллекцию можно будет добавлять только элементы,
 * которые являются экземплярами класса String.
 * При попытке добавить экземпляр другого класса - мы получим ошибку компиляции.
 * Существует такое понятие, связанное с generics, как необработанные типы (в литературе,
 * интернете еще можно встретить такое название как "сырые типы").
 * Обозначаются они также как и generics в скобках <>, в которых проставляются заглавные латинские символы,
 * зарезервированные специально для этих целей символы - полный список можно найти по ссылке:
 * <a href="https://docs.oracle.com/javase/tutorial/java/generics/types.html">источник</a>
 * <b>Задание.</b>
 * Добавьте 3 модели данных, которые образуют иерархию наследования: Animal - Predator - Tiger.
 * 1-ый метод - работает без ограничений, т.е. в него можно передавать коллекцию, которая хранит любые типы.
 * 2-ой метод - должен иметь ограничение сверху и ограничиваться классом Predator.
 * 3-ий метод - должен иметь ограничение снизу и ограничиваться классом Predator.
 * Загрузите исправленный код в репозиторий оставьте ссылку на коммит.
 * Переведите на ответственного.
 */
public class Generics {
    public static void main(String[] args) {
        Generics gen = new Generics();
        List<Animal> first = new ArrayList<>();
        List<Predator> second = new ArrayList<>();
        List<Tiger> third = new ArrayList<>();
        first.add(new Animal("male", 9));
        second.add(new Predator("male", 5));
        third.add(new Tiger("male", 3));

        gen.printObject(first);
        gen.printObject(second);
        gen.printObject(third);
        System.out.println();

        gen.printBoundedWildCard(first);
        gen.printBoundedWildCard(second);
        gen.printBoundedWildCard(third);
        System.out.println();

        gen.printLowerBoundedWildCard(first);
        gen.printLowerBoundedWildCard(second);
        gen.printLowerBoundedWildCard(third);
    }

    /**
     * <b>WildCard.</b>
     * Для того чтобы решить проблему ограничения используем WildCard (обозначает <?>).
     * В этом случае ограничений в использовании не будет (т.е. он имеет соответствие с любым типом).
     *
     * @param list Каждый элемент списка.
     */
    public void printObject(List<?> list) {
        for (Object next : list) {
            System.out.println("Текущий элемент: " + next);
        }
    }

    /**
     * <b>Bounded Wildcard.</b>
     * Такой подход используется, если метод который нужно реализовать использует определенный тип
     * и все его подтипы. Для того чтобы справиться с проблемой используется так называемое "Ограничение сверху".
     * В этом случае вместо <Predator> записывается конструкция <? extends Animal>.
     *
     * @param list Каждый элемент списка.
     */
    public void printBoundedWildCard(List<? extends Animal> list) {
        for (Object next : list) {
            System.out.println("Текущий элемент: " + next);
        }
    }

    /**
     * <b>Lower bounded wildcard.</b>
     * Ограниченный снизу wildcard выражается с помощью wildcard символа "?",
     * за которым следует ключевое слово super после которого указывается нижняя граница - <? super A>.
     * В нашем случае, вместо <Predator> записывается конструкция <? extends Tiger>.
     *
     * @param list Каждый элемент списка.
     */
    public void printLowerBoundedWildCard(List<? super Tiger> list) {
        for (Object next : list) {
            System.out.println("Текущий элемент: " + next);
        }
    }
}
