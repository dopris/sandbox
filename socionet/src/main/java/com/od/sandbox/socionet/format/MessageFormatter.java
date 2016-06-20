package com.od.sandbox.socionet.format;

import com.od.sandbox.socionet.util.Util;

import java.time.Duration;
import java.time.Instant;

/**
 * Class representing a formatter for SocioNet message
 * <p/>
 * Author: DorinO
 */
public class MessageFormatter {
    public static final String SUFFIX_FORMAT = " (%d %s ago)";

    public void displayFormattedMessage(String formattedMessage){
        Util.output(formattedMessage);
    }

    public String formatMessage(String message, Instant messageTime) {
        Duration duration = Duration.between(messageTime, Instant.now());
        String formattedMessage = message;
        if (duration.toMinutes() == 0) {
            formattedMessage += createSuffix(duration.toMillis() / 1000, "second");
        } else if (duration.toHours() == 0) {
            formattedMessage += createSuffix(duration.toMinutes(), "minute");
        } else if (duration.toDays() == 0) {
            formattedMessage += createSuffix(duration.toHours(), "hour");
        } else {
            formattedMessage += createSuffix(duration.toDays(), "day");
        }
        return formattedMessage;
    }

    private String createSuffix(long elapsedTime, String timeUnit) {
        return String.format(SUFFIX_FORMAT, elapsedTime, timeUnit + ((elapsedTime > 1) ? "s" : ""));
    }


}
