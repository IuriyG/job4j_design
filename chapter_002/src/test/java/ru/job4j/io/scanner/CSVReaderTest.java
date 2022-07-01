package ru.job4j.io.scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ru.job4j.io.ArgsName;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;

import static org.junit.Assert.assertEquals;

/**
 * @author Iuriy Gaydarzhi.
 * @since 29.06.2022
 */
public class CSVReaderTest {
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final String data = String.join(
            System.lineSeparator(),
            "name;age;last_name;education",
            "Tom;20;Smith;Bachelor",
            "Jack;25;Johnson;Undergraduate",
            "William;30;Brown;Secondary special"
    );
    private final String expected = String.join(
            System.lineSeparator(),
            "name;age",
            "Tom;20",
            "Jack;25",
            "William;30"
    ).concat(System.lineSeparator());
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
    CSVReader csvReader = new CSVReader();

    @Test
    public void whenFilterTwoColumnsAndResultInFile() throws Exception {

        File file = temporaryFolder.newFile("source.csv");
        File target = temporaryFolder.newFile("target.csv");
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=" + target.getAbsolutePath(), "-filter=name,age"
        });
        Files.writeString(file.toPath(), this.data);

        csvReader.handle(argsName);
        assertEquals(this.expected, Files.readString(target.toPath()));
    }

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @Test
    public void whenFilterTwoColumnsAndResultInConsole() throws IOException {
        File file = temporaryFolder.newFile("source.csv");
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=stdout", "-filter=name,age"
        });
        Files.writeString(file.toPath(), this.data);
        csvReader.handle(argsName);
        assertEquals(this.expected, output.toString());
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void whenFilterAnotherTwoColumnsAndResultInFile() throws Exception {
        String expected = String.join(
                System.lineSeparator(),
                "name;last_name",
                "Tom;Smith",
                "Jack;Johnson",
                "William;Brown"
        ).concat(System.lineSeparator());

        File file = temporaryFolder.newFile("source.csv");
        File target = temporaryFolder.newFile("target.csv");
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=" + target.getAbsolutePath(), "-filter=name,last_name"
        });
        Files.writeString(file.toPath(), this.data);

        csvReader.handle(argsName);
        assertEquals(expected, Files.readString(target.toPath()));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenArgsOutIsWrong() throws Exception {
        File file = temporaryFolder.newFile("source.csv");
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=out", "-filter=name,last_name"
        });
        Files.writeString(file.toPath(), this.data);
        csvReader.handle(argsName);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenArgsPathIsWrong() throws Exception {
        File file = temporaryFolder.newFile("source.class");
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=stdout", "-filter=name,last_name"
        });
        Files.writeString(file.toPath(), this.data);
        csvReader.handle(argsName);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenArgsIsOneOfTwo() throws Exception {
        File file = temporaryFolder.newFile("source.csv");
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=stdout", "-filter=name"
        });
        Files.writeString(file.toPath(), this.data);
        csvReader.handle(argsName);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenDelimiterIsWrong() throws Exception {
        File file = temporaryFolder.newFile("source.csv");
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=.", "-out=stdout", "-filter=name,last_name"
        });
        Files.writeString(file.toPath(), this.data);
        csvReader.handle(argsName);
    }
}