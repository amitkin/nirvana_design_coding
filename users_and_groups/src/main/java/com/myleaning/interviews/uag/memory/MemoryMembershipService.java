package com.myleaning.interviews.uag.memory;

import com.myleaning.interviews.uag.api.Group;
import com.myleaning.interviews.uag.api.MembershipService;
import com.myleaning.interviews.uag.api.User;
import com.myleaning.interviews.uag.core.AbstractService;
import com.myleaning.interviews.uag.core.Services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;

/**
 * An implementation of the membership service that stores user and group relationships in memory.
 */
@ParametersAreNonnullByDefault
public class MemoryMembershipService extends AbstractService implements MembershipService {
    private static final Logger LOG = LoggerFactory.getLogger(MemoryMembershipService.class);

    //TODO: Added by Amit for 3
    private final Map<Group, Set<Group>> childGroupsByParent = new ConcurrentHashMap<>();
    private final Map<Group, Set<User>> usersByGroup = new ConcurrentHashMap<>();

    public MemoryMembershipService(Services services) {
        super(services);
    }

    @Override
    public void addGroupToGroup(Group child, Group parent) {
        requireExists(parent);
        requireExists(child);

        Set<Group> children = childGroupsByParent.get(parent);
        if (children == null) {
            children = ConcurrentHashMap.newKeySet();  //TODO: Added by Amit for 1c
            childGroupsByParent.put(parent, children);
        }
        children.add(child);

        LOG.debug("Added child group " + child + " to parent group " + parent);
    }

    public void addUserToGroup(User user, Group group) {
        requireExists(user);
        requireExists(group);

        Set<User> users = usersByGroup.get(group);
        if (users == null) {
            users = ConcurrentHashMap.newKeySet();  //TODO: Added by Amit for 1c
            usersByGroup.put(group, users);
        }
        users.add(user);

        LOG.debug("Added user " + user + " to group " + group);
    }

    public boolean isUserInGroup(User user, Group group) {
        requireNonNull(user, "user");
        requireNonNull(group, "group");

        // TODO... Only knows direct memberships right now
        // TODO: Added by Amit for 2
        if(getUsersInGroup(group).contains(user)){
            return true;
        }
        Set<Group> childGroups = childGroupsByParent.get(group);
        if(childGroups != null){
            //Check recursively
            for(Group childGroup: childGroups){
                if(isUserInGroup(user, childGroup)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isGroupInGroup(Group child, Group parent) {
        requireNonNull(child, "child");
        requireNonNull(parent, "parent");

        // TODO... Only doing one level for now
        // TODO: Added by Amit for 2
        Collection<Group> children = childGroupsByParent.get(parent);
        if(children != null) {
            if (children.contains(child)) {
                return true;
            } else {
                //Check recursively
                for(Group childGroup: children){
                    if (isGroupInGroup(child, childGroup)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Collection<User> getUsersInGroup(Group group) {
        requireNonNull(group, "group");

        // TODO: Added by Amit for 1a
        final Collection<User> users = usersByGroup.getOrDefault(group, Collections.emptySet());
        LOG.debug("Current users in group {}: {}", group.toString(), users.toString());
        return users;
    }

    // TODO: Added by Amit for 1b
    @Override
    public void removeGroup(Group group) {
        requireNonNull(group, "group");

        /*
        1. First remove the subscription of this group from other groups
        2. Then remove the subscription of all the groups from this group
        3. Then remove the subscription all the users from this group
        */
        childGroupsByParent.keySet().forEach(g -> {
            //Remove the group membership if it is subscribed to a group
            if(isGroupInGroup(group, g)){
                removeGroupFromGroup(group, g);
                LOG.debug(String.format("Removed group %s membership from group %s", group, g));
            }
        });
        //Remove the subscription of all the groups from this group
        childGroupsByParent.remove(group);
        //Remove all the subscribed users
        usersByGroup.remove(group);
        LOG.debug(String.format("Removed all the users membership from group %s", group));
    }

    @Override
    public void removeGroupFromGroup(Group child, Group parent) {
        requireNonNull(parent, "parent");
        requireNonNull(child, "child");

        Set<Group> children = childGroupsByParent.get(parent);
        if (children != null) {
            children.remove(child);
        }
    }

    @Override
    public void removeUserFromGroup(User user, Group group) {
        requireNonNull(user, "user");
        requireNonNull(group, "group");

        // TODO: Added by Amit for 1a
        if(getUsersInGroup(group).remove(user)){
            LOG.debug(String.format("Removed user %s from group %s", user, group));
        } else{
            LOG.debug("user %s not found in group %s", user, group);
        }
    }

    private void requireExists(User user) {
        requireNonNull(user, "user");
        if (services.getUserService().findByName(user.getName()) == null) {
            throw new IllegalArgumentException("User '" + user + "' does not exist!");
        }
    }

    private void requireExists(Group group) {
        requireNonNull(group, "group");
        if (services.getGroupService().findByName(group.getName()) == null) {
            throw new IllegalArgumentException("Group '" + group + "' does not exist!");
        }
    }
}
