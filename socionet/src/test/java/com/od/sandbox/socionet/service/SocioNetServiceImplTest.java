package com.od.sandbox.socionet.service;

import com.od.sandbox.socionet.format.MessageFormatter;
import com.od.sandbox.socionet.model.Message;
import com.od.sandbox.socionet.model.SocioGraph;
import com.od.sandbox.socionet.model.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.util.Set;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Unit test for SocioNetService implementation class
 * <p/>
 * Author: DorinO
 */
public class SocioNetServiceImplTest {

    private static final String ALICE = "Alice";
    private static final String CHARLIE = "Charlie";
    private static final String BOB = "Bob";
    private static final String ALICE_MESSAGE = "I love the weather today";
    private static final String ALICE_MESSAGE_PREFIX = ALICE + " - ";
    private static final String ALICE_MESSAGE_SUFFIX = "(5 minutes ago)";
    private static final String ALICE_MESSAGE_FORMATTED = ALICE_MESSAGE + " " + ALICE_MESSAGE_SUFFIX;
    private static final String CHARLIE_MESSAGE = "I'm in New York today! Anyone want to have a coffee?";
    private static final String CHARLIE_MESSAGE_PREFIX = CHARLIE + " - ";
    private static final String CHARLIE_MESSAGE_SUFFIX = "(2 seconds ago)";
    private static final String CHARLIE_MESSAGE_FORMATTED = CHARLIE_MESSAGE + " " + CHARLIE_MESSAGE_SUFFIX;
    private static final String BOB_MESSAGE_1 = "Damn! We lost!";
    private static final String BOB_MESSAGE_2 = "Good game though.";

    @Mock
    private MessageFormatter formatter;
    private SocioNetServiceImpl socioService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        socioService = new SocioNetServiceImpl();
        socioService.setFormatter(formatter);
    }

    @Test
    public void postMessage() {
        User user = new User(ALICE);
        Message message = new Message(ALICE_MESSAGE, ALICE);

        boolean isAdded = socioService.postMessage(user, message);

        assertTrue(isAdded);
        SocioGraph socioGraph = socioService.getSocioGraph();
        assertTrue(socioGraph.size() == 1);
        assertTrue(socioGraph.getUserByName(ALICE) != null);
    }

    @Test
    public void postTwoMessage() {
        User user = new User(BOB);
        Message message1 = new Message(BOB_MESSAGE_1, BOB);
        Message message2 = new Message(BOB_MESSAGE_2, BOB);

        socioService.postMessage(user, message1);
        socioService.postMessage(user, message2);

        SocioGraph socioGraph = socioService.getSocioGraph();
        assertTrue(socioGraph.size() == 1);
        assertTrue(socioGraph.getUserByName(BOB) != null);
        assertTrue(socioGraph.getUserByName(BOB).getPersonalMessages() != null);
        assertTrue(socioGraph.getUserByName(BOB).getPersonalMessages().size() == 2);
    }

    @Test
    public void displayUserTimeline() {
        User user = createTestPostMessage(ALICE, ALICE_MESSAGE);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        when(formatter.formatMessage(eq(ALICE_MESSAGE), any(Instant.class))).thenReturn(ALICE_MESSAGE_FORMATTED);

        socioService.displayUserTimeline(user);

        verify(formatter, times(1)).formatMessage(eq(ALICE_MESSAGE), any(Instant.class));
        verify(formatter, times(1)).displayFormattedMessage(captor.capture());
        verifyNoMoreInteractions(formatter);

        String formattedMessage = captor.getValue();
        assertTrue(formattedMessage.startsWith(ALICE_MESSAGE));
        assertTrue(formattedMessage.endsWith(ALICE_MESSAGE_SUFFIX));
    }

    @Test
    public void followUser() {
        User followedUser = new User(ALICE);
        User followingUser = new User(CHARLIE);

        boolean isFollowed = socioService.followUser(followingUser, followedUser);

        assertTrue(isFollowed);
        SocioGraph socioGraph = socioService.getSocioGraph();
        assertTrue(socioGraph.size() == 2);
        Set<User> followedSet = socioGraph.getAdjacentSet(followingUser);
        assertTrue(followedSet.contains(followedUser));
    }

    @Test
    public void displayUserWall() {
        User userAlice = createTestPostMessage(ALICE, ALICE_MESSAGE);
        User userCharlie = createTestPostMessage(CHARLIE, CHARLIE_MESSAGE);
        socioService.followUser(userCharlie, userAlice);
        ArgumentCaptor<String> captorAlice = ArgumentCaptor.forClass(String.class);
        when(formatter.formatMessage(eq(ALICE_MESSAGE_PREFIX + ALICE_MESSAGE), any(Instant.class)))
                .thenReturn(ALICE_MESSAGE_PREFIX + ALICE_MESSAGE_FORMATTED);
        when(formatter.formatMessage(eq(CHARLIE_MESSAGE_PREFIX + CHARLIE_MESSAGE), any(Instant.class)))
                .thenReturn(CHARLIE_MESSAGE_PREFIX + CHARLIE_MESSAGE_FORMATTED);

        socioService.displayUserWall(userCharlie);

        verify(formatter, times(1)).formatMessage(eq(CHARLIE_MESSAGE_PREFIX + CHARLIE_MESSAGE), any(Instant.class));
        verify(formatter, times(1)).formatMessage(eq(ALICE_MESSAGE_PREFIX + ALICE_MESSAGE), any(Instant.class));
        verify(formatter, times(2)).displayFormattedMessage(captorAlice.capture());
        verifyNoMoreInteractions(formatter);

        String formattedMessageAlice = captorAlice.getValue();
        assertTrue(formattedMessageAlice.startsWith(ALICE_MESSAGE_PREFIX));
        assertTrue(formattedMessageAlice.contains(ALICE_MESSAGE));
        assertTrue(formattedMessageAlice.endsWith(ALICE_MESSAGE_SUFFIX));

    }

    private User createTestPostMessage(String username, String messageText) {
        User user = new User(username);
        Message message = new Message(messageText, username);
        socioService.postMessage(user, message);
        return user;
    }
}
