package com.od.sandbox.socionet.application;

import com.od.sandbox.socionet.exception.CommandException;
import com.od.sandbox.socionet.model.Message;
import com.od.sandbox.socionet.model.User;
import com.od.sandbox.socionet.service.SocioNetService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit test for SocioNet application class
 * <p/>
 * Author: DorinO
 */
public class SocioNetTest {

    private static final String READ_CMD = " Bob ";
    private static final String POST_CMD = "Bob  ->  Damn! We lost!";
    private static final String FOLLOW_CMD = "Charlie follows  Bob ";
    private static final String WALL_CMD = "Charlie  wall ";
    private static final String EMPTY_CMD = "";
    private static final String INVALID_CMD = "##->follows";
    private static final String CMD_WITH_SPACES = "  ";

    @Mock
    private SocioNetService service;

    private SocioNet application;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        application = new SocioNet();
        application.setService(service);
    }

    @Test
    public void runReadUserTimelineCommand() throws CommandException {
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        application.runCommand(READ_CMD);

        verify(service, times(1)).displayUserTimeline(captor.capture());
        verifyNoMoreInteractions(service);

        User user = captor.getValue();
        assertEquals("Bob", user.getUserName());
    }

    @Test
    public void runPostMessageCommand() throws CommandException {
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        ArgumentCaptor<Message> messageCaptor = ArgumentCaptor.forClass(Message.class);
        application.runCommand(POST_CMD);

        verify(service, times(1)).postMessage(userCaptor.capture(), messageCaptor.capture());
        verifyNoMoreInteractions(service);

        User user = userCaptor.getValue();
        Message message = messageCaptor.getValue();
        assertEquals("Bob", user.getUserName());
        assertEquals(" Damn! We lost!", message.getMessage());
    }

    @Test
    public void runFollowUserCommand() throws CommandException {
        ArgumentCaptor<User> followingUserCaptor = ArgumentCaptor.forClass(User.class);
        ArgumentCaptor<User> followedUserCaptor = ArgumentCaptor.forClass(User.class);
        application.runCommand(FOLLOW_CMD);

        verify(service, times(1)).followUser(followingUserCaptor.capture(), followedUserCaptor.capture());
        verifyNoMoreInteractions(service);

        User followingUser = followingUserCaptor.getValue();
        User followedUser = followedUserCaptor.getValue();
        assertEquals("Charlie", followingUser.getUserName());
        assertEquals("Bob", followedUser.getUserName());
    }

    @Test
    public void runUserWallCommand() throws CommandException {
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        application.runCommand(WALL_CMD);

        verify(service, times(1)).displayUserWall(captor.capture());
        verifyNoMoreInteractions(service);

        User user = captor.getValue();
        assertEquals("Charlie", user.getUserName());
    }

    @Test
    public void runCommandWithEmptyLine() throws CommandException {
        application.runCommand(EMPTY_CMD);
        verifyZeroInteractions(service);
    }

    @Test
    public void runCommandWithSpaces() throws CommandException {
        application.runCommand(CMD_WITH_SPACES);
        verifyZeroInteractions(service);
    }

    @Test(expected = CommandException.class)
    public void runWrongCommand() throws CommandException {
        application.runCommand(INVALID_CMD);
        verifyZeroInteractions(service);
    }
}
