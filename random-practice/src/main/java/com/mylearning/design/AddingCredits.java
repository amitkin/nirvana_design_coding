package com.mylearning.design;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

/*
Consider a server that a large number of clients connect to. Each client is identified by a string. Each client has
a "credit", which is a non-negative integer value. The server needs to maintain a data structure to which clients can
be added, removed, queried, or updated. In addition, the server needs to be able to add a specified number of credits
to all clients simultaneously.
Design a data structure that implements the following methods:
• Insert: add a client with specified credit, replacing any existing entry for the client.
• Remove: delete the specified client.
• Lookup: return the number of credits associated with the specified client.
• Add-to-all: increment the credit count for all current clients by the specified amount.
• Max: return a client with the highest number of credits.

Solution:
A hash table is a natural data structure for this application. However, it does not support efficient max operations,
nor is there an obvious way to perform the simultaneous increment, short traversing all entries.
A BST does have efficient max operation, but it too does not natively support the global increment.

In our context, this suggests storing the clients in a BST, and having the wrapper track the total increment amount.
For example, if we have clients A,B,C, with credits 1,2,3, respectively, and want to add 5 credits to each, the wrapper
sets the total increment amount to 5. A lookup on B then is performed by looking up in the BST, which returns 2, and then
adding 5 before returning. If we want to add 4 more credits to each, we simply update the total increment amount to 9.

One issue to watch out for is what happens to clients inserted after a call to the add-to-all function. Continuing with
the given example, if we were to now add D with a credit of 6, the lookup would return 6 + 9, which is an error.
The solution is simple—subtract the increment from the credit, i.e., add D with a credit of 6 - 9 = -3 to the BST.
Now a lookup for D will return -3 + 9, which is the correct amount.

More specifically, the BST keys are credits, and the corresponding values are the clients with that credit. This makes
for fast max-queries. However, to perform lookups and removes by client quickly, the BST by itself is not enough (since
it is ordered by credit, not client id). We can solve this by maintaining an additional hash table in which keys are
clients, and values are credits. Lookup is trivial. Removes entails a lookup in the hash to get the credit, and then a
search into the BST to get the set of clients with that credit, and finally a delete on that set.
*/
public class AddingCredits {

    public static class ClientsCreditsInfo {

        private int offset = 0;
        private Map<String, Integer> clientToCredit = new HashMap<>();
        private NavigableMap<Integer, Set<String>> creditToClients = new TreeMap<>();

        public void insert(String clientID, int c) {
            remove(clientID);
            clientToCredit.put(clientID, c - offset);
            creditToClients.putIfAbsent(c - offset, new HashSet<>());
            Set<String> set = creditToClients.get(c - offset);
            set.add(clientID);
        }

        public boolean remove(String clientID) {
            Integer clientCredit = clientToCredit.get(clientID);
            if (clientCredit != null) {
                creditToClients.get(clientCredit).remove(clientID);
                if (creditToClients.get(clientCredit).isEmpty()) {
                    creditToClients.remove(clientCredit);
                }
                clientToCredit.remove(clientID);
                return true;
            }
            return false;
        }

        public int lookup(String clientID) {
            Integer clientCredit = clientToCredit.get(clientID);
            return clientCredit == null ? -1 : clientCredit + offset;
        }

        public void addAll(int C) {
            offset += C;
        }

        public String max() {
            return creditToClients.isEmpty() ? "" : creditToClients.lastEntry().getValue().iterator().next();
        }

        @Override
        public String toString() {
            return "{clientToCredit=" + clientToCredit + '}';
        }
    }
}
