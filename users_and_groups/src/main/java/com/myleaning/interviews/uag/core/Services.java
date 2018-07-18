package com.myleaning.interviews.uag.core;

import com.myleaning.interviews.uag.api.GroupService;
import com.myleaning.interviews.uag.api.MembershipService;
import com.myleaning.interviews.uag.api.UserService;

/**
 * Provides access to all of the services so that circular dependencies between them can be resolved.
 */
public interface Services {
    GroupService getGroupService();

    UserService getUserService();

    MembershipService getMembershipService();
}
