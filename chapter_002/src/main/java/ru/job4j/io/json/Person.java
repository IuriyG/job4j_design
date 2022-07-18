package ru.job4j.io.json;

import ru.job4j.io.serialization.Contact;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * Класс - Модель данных Личность.
 * Демонстрирует работу Преобразования XML в POJO (Plain Old Java Object).
 * @author Iuriy Gaydarzhi.
 * @since 16.07.2022
 */
@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {
    /**
     * Статус личности.
     */
    @XmlAttribute
    private boolean alive;
    /**
     * Возраст личности.
     */
    @XmlAttribute
    private int age;
    /**
     * Пол личности.
     */
    @XmlAttribute
    private String gender;
    /**
     * Объект {@linkplain Contact}.
     */
    private Contact contact;
    /**
     * Информация и книгах.
     */
    @XmlElementWrapper(name = "info")
    @XmlElement(name = "information")
    private String[] info;

    /**
     * Конструктор.
     *
     * @param alive   Статус.
     * @param age     Возраст.
     * @param gender  Пол.
     * @param contact Контакт.
     * @param info    Информация.
     */
    public Person(boolean alive, int age, String gender, Contact contact, String[] info) {
        this.alive = alive;
        this.age = age;
        this.gender = gender;
        this.contact = contact;
        this.info = info;
    }

    /**
     * Конструктор.
     */
    public Person() {
    }

    /**
     * Основной метод.
     *
     * @param args Входящие аргументы.
     * @throws JAXBException Корневой класс исключений для всех исключений JAXB.
     * @throws IOException   Исключение ввода-вывода.
     */
    public static void main(String[] args) throws JAXBException, IOException {
        Person person = new Person(true, 90, "Male",
                new Contact("Daniels", "Peter"),
                new String[]{"Книга1", "Книга2", "Книга3"});
        /* Получаем контекст для доступа к АПИ */
        JAXBContext context = JAXBContext.newInstance(Person.class);
        /* Создаем сериализатор */
        Marshaller marshaller = context.createMarshaller();
        /* Указываем, что нам нужно форматирование */
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        String rsl;
        try (StringWriter writer = new StringWriter()) {
            /* Сериализуем */
            marshaller.marshal(person, writer);
            rsl = writer.getBuffer().toString();
            System.out.println(rsl);
        }
        /* Для десериализации нам нужно создать десериализатор */
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(rsl)) {
            /* Десериализуем */
            Person result = (Person) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }

    /**
     * Переопределенный метод вывода в консоль.
     *
     * @return Форматированный вывод.
     */
    @Override
    public String toString() {
        return "Person{"
                + "Alive:" + alive
                + ", Age:" + age
                + ", Gender:'" + gender
                + '\''
                + ", Contact:" + contact
                + ", Info:" + Arrays.toString(info)
                + '}';
    }
}
