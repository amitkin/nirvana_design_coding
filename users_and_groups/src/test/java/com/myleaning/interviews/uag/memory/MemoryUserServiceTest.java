package com.myleaning.interviews.uag.memory;

import com.myleaning.interviews.uag.api.User;
import com.myleaning.interviews.uag.api.UserService;
import com.myleaning.interviews.uag.core.ServiceFactory;
import com.myleaning.interviews.uag.core.Services;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertNull;

public class MemoryUserServiceTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testCreateUser_duplicate() {
        final UserService service = createService();
        final User fred = new User("fred");
        service.create(fred);

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("User fred already exists");
        service.create(fred);
    }


    @Test
    public void testCreateUser_npe() {
        final UserService service = createService();

        thrown.expect(NullPointerException.class);
        service.create(null);
    }

    @Test
    public void testCreateUser_ok() {
        final UserService service = createService();
        assertNull("fred should not exist yet", service.findByName("fred"));

        final User fred = new User("fred");
        service.create(fred);

        Assert.assertEquals("fred should exist now", fred, service.findByName("fred"));
    }

    @Test
    public void testDeleteUser_notExists() {
        final UserService service = createService();
        assertNull("fred should not exist yet", service.findByName("fred"));

        final User fred = new User("fred");
        service.delete(fred);

        assertNull("fred still should not exist", service.findByName("fred"));
    }

    @Test
    public void testDeleteUser_npe() {
        final UserService service = createService();

        thrown.expect(NullPointerException.class);
        service.delete(null);
    }

    @Test
    public void testDeleteUser_ok() {
        final UserService service = createService();

        final User fred = new User("fred");

        service.create(fred);
        Assert.assertEquals("fred should exist", fred, service.findByName("fred"));

        service.delete(fred);
        assertNull("fred should be deleted", service.findByName("fred"));
    }


    private UserService createService() {
        final Services services = ServiceFactory.createServices();
        return services.getUserService();
    }
}
