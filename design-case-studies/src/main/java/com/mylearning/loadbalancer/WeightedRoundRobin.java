package com.mylearning.loadbalancer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/*
Different servers may have different machine configurations and loads of the current system, so their compressive capacity
is also different. The machines with high configuration and low load are given higher weights to handle more requests,
while the machines with low configuration and high load are given lower weights to reduce their system loads.
The weighted polling method can deal with this problem well and assign the request order to the back end according to the weight.
*/
public class WeightedRoundRobin implements LoadBalancer {
    private static Integer position = 0;

    @Override
    public String getServer() {
        Set<String> servers = IpPool.ipMap.keySet();
        List<String> serverList = new ArrayList<>();

        Iterator<String> iterator = servers.iterator();
        while(iterator.hasNext()) {
            String serverItem = iterator.next();
            Integer weight = IpPool.ipMap.get(serverItem);
            if (weight > 0) {
                for (int i = 0; i < weight; i++) {
                    serverList.add(serverItem);
                }
            }

        }

        synchronized (position) {
            if (position > serverList.size()) {
                position = 0;
            }

            String target = serverList.get(position);
            position++;
            return target;
        }
    }
}
