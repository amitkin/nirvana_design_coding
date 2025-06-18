package com.myleaning.interviews.uag.memory;

import com.myleaning.interviews.uag.api.Group;
import com.myleaning.interviews.uag.api.GroupService;
import com.myleaning.interviews.uag.api.MembershipService;
import com.myleaning.interviews.uag.core.AbstractService;
import com.myleaning.interviews.uag.core.Services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;

/**
 * An implementation of the group service that stores all groups in memory.
 */
@ParametersAreNonnullByDefault
public class MemoryGroupService extends AbstractService implements GroupService {
    private static final Logger LOG = LoggerFactory.getLogger(MemoryGroupService.class);

    private final Map<String, Group> groups = new ConcurrentHashMap<>(); //TODO: Added by Amit for 3

    public MemoryGroupService(Services services) {
            super(services);
    }

    public Group findByName(String name) {
        requireNonNull(name, "name");
        return groups.get(name);
    }

    public void create(Group group) {
        requireNonNull(group, "group");
        if (groups.containsKey(group.getName())) {
            throw new IllegalArgumentException("Group " + group.getName() + " already exists");
        }
        groups.putIfAbsent(group.getName(), group);
        LOG.debug("Created group: {}", group.getName());
    }

    public void delete(Group group) {
        requireNonNull(group, "group");
        MembershipService membershipService = services.getMembershipService();
        membershipService.removeGroup(group);
        groups.remove(group.getName());
        LOG.debug("Deleted group: {}", group.getName());
    }
}
