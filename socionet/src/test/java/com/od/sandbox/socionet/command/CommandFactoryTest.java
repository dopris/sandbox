package com.od.sandbox.socionet.command;

import com.od.sandbox.socionet.service.SocioNetService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * Unit test for CommandFactory class
 * <p/>
 * Author: DorinO
 */
public class CommandFactoryTest {

    private static final String READ_CMD = "a";
    private static final String POST_CMD = "Alice -> I love the weather today";
    private static final String FOLLOW_CMD = "Charlie follows Alice";
    private static final String WALL_CMD = "Charlie wall";
    private static final String QUIT_CMD = ":q";
    private static final String EMPTY_CMD = "";
    private static final String CMD_WITH_SPACES = "  ";

    @Mock
    private SocioNetService service;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getReadUserTimelineCommand() {
        Command command = CommandFactory.getCommandForService(READ_CMD, service);

        assertTrue(command instanceof ReadUserTimelineCommand);
        verifyZeroInteractions(service);
    }

    @Test
    public void getPostMessageCommand() {
        Command command = CommandFactory.getCommandForService(POST_CMD, service);

        assertTrue(command instanceof PostMessageCommand);
        verifyZeroInteractions(service);
    }

    @Test
    public void getFollowUserCommand() {
        Command command = CommandFactory.getCommandForService(FOLLOW_CMD, service);

        assertTrue(command instanceof FollowUserCommand);
        verifyZeroInteractions(service);
    }

    @Test
    public void getUserWallCommand() {
        Command command = CommandFactory.getCommandForService(WALL_CMD, service);

        assertTrue(command instanceof UserWallCommand);
        verifyZeroInteractions(service);
    }

    @Test
    public void getQuitCommand() {
        Command command = CommandFactory.getCommandForService(QUIT_CMD, service);

        assertTrue(command instanceof QuitCommand);
        verifyZeroInteractions(service);
    }

    @Test
    public void getCommandWithEmptyLine() {
        Command command = CommandFactory.getCommandForService(EMPTY_CMD, service);

        assertTrue(command instanceof NullCommand);
        verifyZeroInteractions(service);
    }

    @Test
    public void getCommandWithSpaces() {
        Command command = CommandFactory.getCommandForService(CMD_WITH_SPACES, service);

        assertTrue(command instanceof NullCommand);
        verifyZeroInteractions(service);
    }
}
