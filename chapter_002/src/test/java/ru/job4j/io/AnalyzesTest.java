package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Iuriy Gaydarzhi.
 * @since 31.05.2022
 */
public class AnalyzesTest {

    @Test
    public void whenTwoPeriods() {
        Analyzes analyzes = new Analyzes();
        String in = "./data/two_periods.log";
        String out = "./data/expected_two_periods.log";
        analyzes.unavailable(in, out);
        try (BufferedReader read = new BufferedReader(new FileReader(out))) {
            assertThat(read.readLine(), is("10:57:01;10:59:01;"));
            assertThat(read.readLine(), is("11:01:02;11:02:02;"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenOnePeriod() {
        Analyzes analyzes = new Analyzes();
        String in = "./data/one_period.log";
        String out = "./data/expected_one_period.log";
        analyzes.unavailable(in, out);
        try (BufferedReader read = new BufferedReader(new FileReader(out))) {
            assertThat(read.readLine(), is("10:57:01;11:02:02;"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}