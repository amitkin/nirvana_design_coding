package com.mylearning.loadbalancer;

import java.util.*;

/*
Weighted random method allocates different weights according to the different configuration and load of the back-end server.
The difference is that it randomly selects servers by weight, not by order.
*/
public class WeightedRandom implements LoadBalancer {

    @Override
    public String getServer() {
        Set<String> servers = IpPool.ipMap.keySet();
        List<String> serverList = new ArrayList<>();

        Iterator<String> iterator = servers.iterator();
        while (iterator.hasNext()) {
            String server = iterator.next();
            Integer weight = IpPool.ipMap.get(server);
            if (weight != null && weight > 0) {
                for (int i = 0; i < weight; i++) {
                    serverList.add(server);
                }
            }
        }

        Integer index = new Random().nextInt(serverList.size());
        String target = serverList.get(index);
        return target;
    }
}
