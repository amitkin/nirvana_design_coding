package com.myleaning.interviews.uag.memory;

import com.myleaning.interviews.uag.api.Group;
import com.myleaning.interviews.uag.core.ServiceFactory;
import com.myleaning.interviews.uag.core.Services;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MemoryGroupServiceTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testCreateGroup_duplicate() {
        final MemoryGroupService service = createService();
        final Group hackers = new Group("hackers");
        service.create(hackers);

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Group hackers already exists");
        service.create(hackers);
    }


    @Test
    public void testCreateGroup_npe() {
        final MemoryGroupService service = createService();

        thrown.expect(NullPointerException.class);
        service.create(null);
    }

    @Test
    public void testCreateGroup_ok() {
        final MemoryGroupService service = createService();
        assertNull("hackers should not exist yet", service.findByName("hackers"));

        final Group hackers = new Group("hackers");
        service.create(hackers);

        assertEquals("hackers should exist now", hackers, service.findByName("hackers"));
    }

    @Test
    public void testDeleteGroup_notExists() {
        final MemoryGroupService service = createService();
        assertNull("hackers should not exist yet", service.findByName("hackers"));

        final Group hackers = new Group("hackers");
        service.delete(hackers);

        assertNull("hackers still should not exist", service.findByName("hackers"));
    }

    @Test
    public void testDeleteGroup_npe() {
        final MemoryGroupService service = createService();

        thrown.expect(NullPointerException.class);
        service.delete(null);
    }

    @Test
    public void testDeleteGroup_ok() {
        final MemoryGroupService service = createService();

        final Group hackers = new Group("hackers");

        service.create(hackers);
        assertEquals("hackers should exist", hackers, service.findByName("hackers"));

        service.delete(hackers);
        assertNull("hackers should be deleted", service.findByName("hackers"));
    }


    private MemoryGroupService createService() {
        final Services services = ServiceFactory.createServices();
        return new MemoryGroupService(services);
    }
}
