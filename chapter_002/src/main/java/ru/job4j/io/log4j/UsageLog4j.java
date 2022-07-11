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
    /**
     * Экземпляр Логера.
     */
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        try {
            throw new Exception("Not supported code");
        } catch (Exception e) {
            LOG.error("Exception in log example", e);
        }
    }
}
