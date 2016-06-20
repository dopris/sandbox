package com.od.sandbox.socionet.format;

import org.junit.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for message formatter
 * <p/>
 * Author: DorinO
 */
public class MessageFormatterTest {

    private static String MESSAGE = "I love the weather today";
    private static final String ONE_SECOND_AGO_SUFFIX = "(1 second ago)";
    private static final String TEN_SECONDS_AGO_SUFFIX = "(10 seconds ago)";
    private static final String ONE_MINUTE_AGO_SUFFIX = "(1 minute ago)";
    private static final String TEN_MINUTES_AGO_SUFFIX = "(10 minutes ago)";
    private static final String ONE_HOUR_AGO_SUFFIX = "(1 hour ago)";
    private static final String TEN_HOURS_AGO_SUFFIX = "(10 hours ago)";
    private static final String ONE_DAY_AGO_SUFFIX = "(1 day ago)";
    private static final String TEN_DAYS_AGO_SUFFIX = "(10 days ago)";

    private static Instant ONE_SECOND_AGO = Instant.now().minus(1, ChronoUnit.SECONDS);
    private static Instant TEN_SECONDS_AGO = Instant.now().minus(10, ChronoUnit.SECONDS);
    private static Instant ONE_MINUTE_AGO = Instant.now().minus(1, ChronoUnit.MINUTES);
    private static Instant TEN_MINUTES_AGO = Instant.now().minus(10, ChronoUnit.MINUTES);
    private static Instant ONE_HOUR_AGO = Instant.now().minus(1, ChronoUnit.HOURS);
    private static Instant TEN_HOURS_AGO = Instant.now().minus(10, ChronoUnit.HOURS);
    private static Instant ONE_DAY_AGO = Instant.now().minus(1, ChronoUnit.DAYS);
    private static Instant TEN_DAYS_AGO = Instant.now().minus(10, ChronoUnit.DAYS);

    private MessageFormatter formatter = new MessageFormatter();

    @Test
    public void formatMessageForOneSecondAgo() {
        String formattedMessage = formatter.formatMessage(MESSAGE, ONE_SECOND_AGO);

        assertTrue(formattedMessage.startsWith(MESSAGE));
        assertTrue(formattedMessage.endsWith(ONE_SECOND_AGO_SUFFIX));
    }

    @Test
    public void formatMessageForTenSecondsAgo() {
        String formattedMessage = formatter.formatMessage(MESSAGE, TEN_SECONDS_AGO);

        assertTrue(formattedMessage.startsWith(MESSAGE));
        assertTrue(formattedMessage.endsWith(TEN_SECONDS_AGO_SUFFIX));
    }

    @Test
    public void formatMessageForOneMinuteAgo() {
        String formattedMessage = formatter.formatMessage(MESSAGE, ONE_MINUTE_AGO);

        assertTrue(formattedMessage.startsWith(MESSAGE));
        assertTrue(formattedMessage.endsWith(ONE_MINUTE_AGO_SUFFIX));
    }

    @Test
    public void formatMessageForTenMinutesAgo() {
        String formattedMessage = formatter.formatMessage(MESSAGE, TEN_MINUTES_AGO);

        assertTrue(formattedMessage.startsWith(MESSAGE));
        assertTrue(formattedMessage.endsWith(TEN_MINUTES_AGO_SUFFIX));
    }

    @Test
    public void formatMessageForOneHourAgo() {
        String formattedMessage = formatter.formatMessage(MESSAGE, ONE_HOUR_AGO);

        assertTrue(formattedMessage.startsWith(MESSAGE));
        assertTrue(formattedMessage.endsWith(ONE_HOUR_AGO_SUFFIX));
    }

    @Test
    public void formatMessageForTenHoursAgo() {
        String formattedMessage = formatter.formatMessage(MESSAGE, TEN_HOURS_AGO);

        assertTrue(formattedMessage.startsWith(MESSAGE));
        assertTrue(formattedMessage.endsWith(TEN_HOURS_AGO_SUFFIX));
    }

    @Test
    public void formatMessageForOneDayAgo() {
        String formattedMessage = formatter.formatMessage(MESSAGE, ONE_DAY_AGO);

        assertTrue(formattedMessage.startsWith(MESSAGE));
        assertTrue(formattedMessage.endsWith(ONE_DAY_AGO_SUFFIX));
    }

    @Test
    public void formatMessageForTenDaysAgo() {
        String formattedMessage = formatter.formatMessage(MESSAGE, TEN_DAYS_AGO);

        assertTrue(formattedMessage.startsWith(MESSAGE));
        assertTrue(formattedMessage.endsWith(TEN_DAYS_AGO_SUFFIX));
    }
}
