package com.mylearning.loadbalancer;

public class LoadBalancerMain {

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        loadBalance();
    }

    public static void loadBalance() {
        doGetServer(new RoundRobin());
        doGetServer(new RandomLoadBalancer());
        doGetServer(new IpHash());
        doGetServer(new WeightedRoundRobin());
        doGetServer(new WeightedRandom());
    }


    public static void doGetServer(LoadBalancer loadBalancer) {
        doGetServer(loadBalancer, 100);
    }

    private static void doGetServer(LoadBalancer loadBalancer, int queryTimes) {
        for (int i = 0; i < queryTimes; i++) {
            String serverId = loadBalancer.getServer();
            System.out.println(String.format("[%s] index:%s,%s", loadBalancer.getClass().getSimpleName(), i, serverId));
        }
    }
}
