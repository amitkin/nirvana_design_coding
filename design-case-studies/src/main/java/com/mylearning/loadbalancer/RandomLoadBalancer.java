package com.mylearning.loadbalancer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/*
It simply chooses next random IP from the pool and retrieves to the client. We can consider this implementation
as thread safe, because any concurrent get call makes only read operation from the list. So no locking is needed.
*/
public class RandomLoadBalancer implements LoadBalancer {

    @Override
    public String getServer() {
        Set<String> servers = IpPool.ipMap.keySet();
        List<String> serverList = new ArrayList<>();
        serverList.addAll(servers);
        int randomIndex = new Random().nextInt(serverList.size());
        String target = serverList.get(randomIndex);
        return target;
    }
}
