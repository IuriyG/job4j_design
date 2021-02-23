package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @author Iuriy Gaydarzhi.
 * @since 10.02.2021
 *
 * <b>8. Реализовать собственную структуру данных - HashMap</b>
 * Ассоциативный массив на базе хэш-таблицы должен быть унифицирован через generics и иметь методы:
 * boolean insert(K key, V value);
 * V get(K key);
 * boolean delete(K key);
 * Реализовывать итератор, обладающий fail-fast поведением.
 * Внутренняя реализация должна использовать массив. Нужно обеспечить фиксированное время вставки и получение.
 * Предусмотрите возможность роста хэш-таблицы при нехватке места для нового элемента.
 * Методы разрешения коллизий реализовывать не надо.
 */
public class SimpleHashMap<K, V> implements Iterable<K> {
    private final double loadFactor = 0.75;
    private Node<K, V>[] hashTable = new Node[8];
    private int size;
    private int modCount;

    /**
     * Метод служит для увеличения исходного массива в 2 раза,
     * при достижении или прохождения порога в 75% от текущего объёма массива.
     */
    private void resize() {
        if (this.size >= hashTable.length * loadFactor) {
            Node<K, V>[] tempHashTable = hashTable;
            hashTable = new Node[tempHashTable.length * 2];
            this.size = 0;
            this.modCount = 0;
            for (Node<K, V> node : tempHashTable) {
                if (node != null) {
                    insert(node.getKey(), node.getValue());
                }
            }
        }
    }

    /**
     * Метод вставляет элемент в массив, при условии того,
     * что по индексу хеша ключа нет пустой ячейки или значения ключей равны.
     *
     * @param key   Ключ.
     * @param value Значение.
     * @return Если ключ добавился возвращает true, иначе - false.
     */
    public boolean insert(K key, V value) {
        resize();
        Node<K, V> newNode = new Node<>(key, value);
        int index = hash(key);
        if (hashTable[index] == null || Objects.equals(hashTable[index].key, key)) {
            hashTable[index] = newNode;
            this.size++;
            this.modCount++;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Метод удаляет значение из массива, при условии того, что по индексу хеша ключа нет пустой ячейки и значение
     * входящего ключа совпадает со значением ключа в ячейке массива.
     *
     * @param key Ключ.
     * @return Если ключ удалился возвращает true, иначе - false.
     */
    public boolean delete(K key) {
        int index = hash(key);
        if (hashTable[index] != null && Objects.equals(hashTable[index].key, key)) {
            hashTable[index] = null;
            this.size--;
            this.modCount++;
            return true;
        }
        return false;
    }

    /**
     * Конструктор.
     *
     * @return Размер массива.
     */
    public int getSize() {
        return size;
    }

    /**
     * Метод служит для определения хеша по ключу.
     *
     * @param key Ключ.
     * @return Значение ключа в виде хеша.
     */
    public int hash(K key) {
        return key == null ? 0 : key.hashCode() % hashTable.length;
    }

    /**
     * Метод возвращает значение элемента, при условии того, что по индексу хеша ключа нет пустой ячейки
     * и значение входящего ключа равно значению ключа в ячейке массива.
     *
     * @param key Ключ.
     * @return Значение ключа.
     */
    public V get(K key) {
        Node<K, V> index = hashTable[hash(key)];
        if (index != null && (Objects.equals(index.key, key))) {
            return index.getValue();
        }
        return null;
    }

    /**
     * Итератор.
     *
     * @return Новый итератор.
     */
    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private int pointer = 0;

            /**
             * Метод проверяет есть ли следующий элемент в массиве.
             * Реализовано fail-fast поведение.
             * @return Возвращает true если есть, иначе - false.
             */
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (hashTable[pointer] == null && pointer < hashTable.length - 1) {
                    pointer++;
                }
                return hashTable[pointer] != null;
            }

            /**
             * Метод возвращает первый элемент.
             * @return Элемент ячейки.
             */
            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return hashTable[pointer++].key;
            }
        };
    }

    /**
     * Модель объекта Node.
     *
     * @param <K> Ключ.
     * @param <V> Значение.
     */
    protected static class Node<K, V> {
        private final K key;
        private final V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Геттер для значения.
         *
         * @return Значение.
         */
        public V getValue() {
            return value;
        }

        /**
         * Геттер для ключа.
         *
         * @return Значение.
         */
        public K getKey() {
            return key;
        }
    }
}
