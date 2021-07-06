package com.mylearning.loadbalancer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/*
The advantage of source address hashing is that it ensures that the same client IP address will be hashed to the same
back-end server until the list of back-end servers changes. According to this feature, stateful session sessions can be
established between service consumers and service providers.
*/
public class IpHash implements LoadBalancer {

    @Override
    public String getServer() {
        Set<String> servers = IpPool.ipMap.keySet();
        List<String> serverList = new ArrayList<>();
        serverList.addAll(servers);
        String remoteId = "127.0.0.1"; //this should be clientIp
        Integer index = remoteId.hashCode() % serverList.size();
        String target = serverList.get(index);
        return target;
    }
}
