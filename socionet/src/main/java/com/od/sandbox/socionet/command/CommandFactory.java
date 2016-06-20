package com.od.sandbox.socionet.command;

import com.od.sandbox.socionet.service.SocioNetService;

import java.util.regex.Pattern;

/**
 * Class representing a SocioNet command factory
 * <p/>
 * Author: DorinO
 */
public class CommandFactory {

    private static final String POST_MARK = " -> ";
    private static final String WALL_MARK = " wall";
    private static final String FOLLOWS_MARK = " follows ";
    private static final String BLANK_MARK = "\\s+";
    private static final String QUIT_MARK = ":q";
    private static final String USERNAME_REGEXP = "^[a-zA-Z][a-zA-Z0-9\\-\\._]*$";

    public static Command getCommandForService(String commandLine, SocioNetService service) {
        Command command = new NullCommand();

        String[] cmdParts = commandLine.split(POST_MARK);
        if (isValidPostMessageCommand(cmdParts)) {
            return PostMessageCommand.getInstance(cmdParts[0].trim(), cmdParts[1], service);
        }
        cmdParts = commandLine.split(WALL_MARK);
        if (isValidUserWallCommand(commandLine, cmdParts)) {
            return UserWallCommand.getInstance(cmdParts[0].trim(), service);
        }
        cmdParts = commandLine.split(FOLLOWS_MARK);
        if (isValidFollowUserCommand(cmdParts)) {
            return FollowUserCommand.getInstance(cmdParts[0].trim(), cmdParts[1].trim(), service);
        }
        cmdParts = commandLine.split(BLANK_MARK);
        if (isReadUserTimelineCommand(cmdParts)) {
            return ReadUserTimelineCommand.getInstance(cmdParts[0].trim(), service);
        }
        if(QUIT_MARK.equals(commandLine)){
            return new QuitCommand();
        }

        return command;
    }

    private static boolean isValidPostMessageCommand(String[] cmdParts) {
        //Posting: <user name> -> <message>
        return cmdParts.length == 2 && isValidUsername(cmdParts[0].trim());
    }

    private static boolean isValidUserWallCommand(String commandLine, String[] cmdParts) {
        //Wall: <user name> wall
        return cmdParts.length == 1 && commandLine.contains(WALL_MARK) && isValidUsername(cmdParts[0].trim());
    }

    private static boolean isValidFollowUserCommand(String[] cmdParts) {
        //<user name> follows <another user>
        return cmdParts.length == 2 && isValidUsername(cmdParts[0].trim()) && isValidUsername(cmdParts[1].trim());
    }

    private static boolean isReadUserTimelineCommand(String[] cmdParts) {
        //Reading: <user name>
        return cmdParts.length == 1 && isValidUsername(cmdParts[0].trim());
    }

    private static boolean isValidUsername(String name) {
        return Pattern.matches(USERNAME_REGEXP, name);
    }
}
