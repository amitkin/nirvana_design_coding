package com.myleaning.interviews.uag.memory;

import com.myleaning.interviews.uag.api.User;
import com.myleaning.interviews.uag.api.UserService;
import com.myleaning.interviews.uag.core.AbstractService;
import com.myleaning.interviews.uag.core.Services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;

/**
 * An implementation of the user service that stores all users in memory.
 */
@ParametersAreNonnullByDefault
public class MemoryUserService extends AbstractService implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(MemoryUserService.class);

    private final Map<String, User> users = new ConcurrentHashMap<>(); //TODO: Added by Amit for 3

    public MemoryUserService(Services services) {
        super(services);
    }

    public User findByName(String name) {
        requireNonNull(name, "name");
        return users.get(name);
    }

    public void create(User user) {
        requireNonNull(user, "user");
        if (users.containsKey(user.getName())) {
            throw new IllegalArgumentException("User " + user.getName() + " already exists");
        }
        users.putIfAbsent(user.getName(), user);
        LOG.debug("Created user: {}", user.getName());
    }

    public void delete(User user) {
        requireNonNull(user, "user");
        users.remove(user.getName());
        LOG.debug("Deleted user: {}", user.getName());
    }
}
