package com.myleaning.interviews.uag.memory;

import com.myleaning.interviews.uag.api.Group;
import com.myleaning.interviews.uag.api.GroupService;
import com.myleaning.interviews.uag.api.MembershipService;
import com.myleaning.interviews.uag.api.User;
import com.myleaning.interviews.uag.api.UserService;
import com.myleaning.interviews.uag.core.ServiceFactory;
import com.myleaning.interviews.uag.core.Services;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.llorllale.cactoos.matchers.RunsInThreads;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MemoryMembershipServiceTest {
    private static final User FRED = new User("fred");
    private static final User GEORGE = new User("george");
    private static final User NOBODY = new User("nobody");
    private static final Group HACKERS = new Group("hackers");
    private static final Group ADMINS = new Group("admins");
    private static final Group NOGROUP = new Group("nogroup");

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private MembershipService membershipService;

    private GroupService memoryGroupService;

    private UserService memoryUserService;

    @Before
    public void setUp() {
        final Services services = ServiceFactory.createServices();
        services.getUserService().create(FRED);
        services.getUserService().create(GEORGE);
        services.getGroupService().create(ADMINS);
        services.getGroupService().create(HACKERS);
        membershipService = services.getMembershipService();
        memoryGroupService = services.getGroupService();
        memoryUserService = services.getUserService();
    }

    @Test
    public void testRemoveUserFromEmptyGroup_1A() {
        assertEquals(0, membershipService.getUsersInGroup(ADMINS).size());
        membershipService.removeUserFromGroup(FRED, ADMINS);
        assertFalse("fred is not an admin", membershipService.isUserInGroup(FRED, ADMINS));
    }

    @Test
    public void createDeleteGroupMembershipTest_1B() {
        membershipService.addUserToGroup(FRED, HACKERS);
        membershipService.addUserToGroup(GEORGE, HACKERS);

        assertEquals(2, membershipService.getUsersInGroup(HACKERS).size());

        memoryGroupService.delete(HACKERS);
        memoryGroupService.create(HACKERS);

        assertEquals(0, membershipService.getUsersInGroup(HACKERS).size());
    }

    @Test
    public void testUserNotInGroup_1C() {
        User ALEX = new User("alex");
        Group STAFF = new Group("staff");
        memoryUserService.create(ALEX);
        memoryGroupService.create(STAFF);
        membershipService.addUserToGroup(ALEX, STAFF);
        Collection<User> users = membershipService.getUsersInGroup(STAFF);
        assertEquals(1, users.size());
        assertTrue(membershipService.isUserInGroup(ALEX, STAFF));
    }

    @Test
    public void testNestedGroups_2() {
        Group PEOPLE = new Group("people");
        Group NETWORK_ADMINS = new Group("network_admins");
        memoryGroupService.create(PEOPLE);
        memoryGroupService.create(NETWORK_ADMINS);
        membershipService.addGroupToGroup(ADMINS, PEOPLE);
        membershipService.addGroupToGroup(HACKERS, PEOPLE);
        membershipService.addGroupToGroup(NETWORK_ADMINS, ADMINS);
        assertTrue(membershipService.isGroupInGroup(NETWORK_ADMINS, PEOPLE));
    }

    @Test
    public void testMultithreaded_3() {
        MatcherAssert.assertThat(
                t -> {
                    String number = String.format("#%d", t.getAndIncrement());
                    //System.out.println("Running test for thread #" + number);
                    User user = new User("User " + number);
                    Group group = new Group("Group " + number);
                    memoryUserService.create(user);
                    memoryGroupService.create(group);
                    membershipService.addUserToGroup(user, group);
                    memoryGroupService.delete(group);
                    memoryGroupService.create(group);
                    membershipService.addUserToGroup(user, group);
                    return membershipService.isUserInGroup(user, group);
                }, new RunsInThreads<>(new AtomicInteger(), 1000)
        );
    }

    @Test
    public void addUserToGroup_duplicate() {
        membershipService.addUserToGroup(FRED, HACKERS);
        membershipService.addUserToGroup(GEORGE, HACKERS);

        final Set<User> expected = new HashSet<>();
        expected.add(FRED);
        expected.add(GEORGE);
        assertEquals(asList(FRED, GEORGE), sorted(membershipService.getUsersInGroup(HACKERS)));

        membershipService.addUserToGroup(FRED, HACKERS);
        assertEquals(asList(FRED, GEORGE), sorted(membershipService.getUsersInGroup(HACKERS)));
    }

    @Test
    public void addUserToGroup_noSuchGroup() {
        thrown.expect(IllegalArgumentException.class);
        membershipService.addUserToGroup(FRED, NOGROUP);
    }

    @Test
    public void addUserToGroup_noSuchUser() {
        thrown.expect(IllegalArgumentException.class);
        membershipService.addUserToGroup(NOBODY, HACKERS);
    }

    @Test
    public void addUserToGroup_npeGroup() {
        thrown.expect(NullPointerException.class);
        membershipService.addUserToGroup(FRED, null);
    }

    @Test
    public void addUserToGroup_npeUser() {
        thrown.expect(NullPointerException.class);
        membershipService.addUserToGroup(null, HACKERS);
    }

    @Test
    public void testRemoveUserFromGroup() {
        membershipService.addUserToGroup(FRED, ADMINS);
        membershipService.addUserToGroup(GEORGE, HACKERS);
        assertTrue("fred is an admin", membershipService.isUserInGroup(FRED, ADMINS));

        membershipService.removeUserFromGroup(FRED, ADMINS);
        assertFalse("fred is not an admin anymore", membershipService.isUserInGroup(FRED, ADMINS));
    }

    @Test
    public void testIsUserInGroup_no() {
        membershipService.addUserToGroup(FRED, ADMINS);
        membershipService.addUserToGroup(GEORGE, HACKERS);

        assertFalse("fred is not a hacker", membershipService.isUserInGroup(FRED, HACKERS));
        assertFalse("george is not an admin", membershipService.isUserInGroup(GEORGE, ADMINS));
    }

    @Test
    public void testIsUserInGroup_yes() {
        membershipService.addUserToGroup(FRED, ADMINS);
        membershipService.addUserToGroup(GEORGE, HACKERS);

        assertTrue("fred is an admin", membershipService.isUserInGroup(FRED, ADMINS));
        assertTrue("george is a hacker", membershipService.isUserInGroup(GEORGE, HACKERS));
    }

    private static <T extends Comparable<T>> List<T> sorted(Collection<T> items) {
        final List<T> list = new ArrayList<>(items);
        Collections.sort(list);
        return list;
    }
}