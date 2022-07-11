package ru.job4j.io.log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Демонстрация работы плагина Log4j.
 *
 * @author Iuriy Gaydarzhi.
 * @since 09.07.2022
 */
public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte b = 1;
        short sh = 2;
        int i = 4;
        long l = 8L;
        float f = 4;
        double d = 8;
        char c = '2';
        boolean bo = true;
        LOG.debug("Type of variables: Byte: {},"
                + " Short: {},"
                + " Int: {},"
                + " Long: {},"
                + " Float: {},"
                + " Double: {},"
                + " Char: {}, "
                + "Boolean: {}", b, sh, i, l, f, d, c, bo);
    }
}
