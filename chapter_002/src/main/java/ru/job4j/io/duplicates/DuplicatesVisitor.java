package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Класс обеспечивает поиск дубликатов файлов.
 * И выводит в консоль список путей дубликатов.
 *
 * @author Iuriy Gaydarzhi.
 * @since 07.06.2022
 */
public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    /**
     * Коллекция в которой будут храниться дубликаты.
     */
    private final Map<FileProperty, List<Path>> duplicates = new HashMap<>();

    /**
     * Метод сканирует каталог на наличие дубликатов файлов.
     * Если находит, добавляет полный путь к списку пути этого файла.
     *
     * @param file  a reference to the file
     * @param attrs the file's basic attributes
     * @return Перезапускает метод.
     * @throws IOException Исключение ввода-вывода.
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Path link = file.toAbsolutePath();
        String fileName = file.getFileName().toString();
        FileProperty fp = new FileProperty(attrs.size(), fileName);

        if (duplicates.containsKey(fp)) {
            duplicates.get(fp).add(link);
        } else {
            duplicates.put(fp, new ArrayList<>(List.of(link)));
        }
        return super.visitFile(file, attrs);
    }

    /**
     * Метод извлекает списки в которых больше одного элемента.
     *
     * @return Возвращает новый список с извлеченными элементами.
     */
    public List<Path> getDuplicates() {
        return duplicates.entrySet()
                .stream()
                .filter(fl -> fl.getValue().size() > 1)
                .flatMap(el -> el.getValue().stream())
                .collect(Collectors.toList());
    }
}
