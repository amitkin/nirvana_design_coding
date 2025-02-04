## Design a system that finds top N items in a stream of data

(This is an active area of research and There are many limited memory- or limited storage-based algorithms developed over the years: https://www.cs.berkeley.edu/~satishr/cs270/sp11/rough-notes/Streaming-two.pdf,  or http://dmac.rutgers.edu/Workshops/WGUnifyingTheory/Slides/cormode.pdf). Here, think about the problem in sense of an end-to-end system.

### Supported Operations
1. Query: A regular query sent by user. A sequence of such queries create a data stream
2. Top-N: A special query sent by the user that quickly returns top-N most frequent items searched so far (Can be approximate)

### Scale of the Problem
1. About ~10000 queries sent over a second (Order of Magnitude of Google)
2. About ~100 top-N queries perfomed per second
3. Most of the queries (90%) are repeat. That means ~1000 new words per second, with each word about 10 bytes long. ~300GB of storage required per year for storing new words.

### Abstract Architecture
1. Storage Layer: We have two options
  * Relational Database: Stores (word, count) tuples. Each new query requires two DB queries: One to find the word and second to insert updated count. Every top-N query performs a complex order-by query for the database.
  * Non-relational DataBase: Stores a word object: Every word object itself two attributes like relational database. Like relational database, reuires two queries to update a word on a new query and also complex query for top-N
2. Application Layer:
  * Dedicated app-servers for regular queries and top-N queries. Load-balancer routes requests to only relevant app-server (regular queries directed to regular app-server and vice-versa)
3. Worker Queues
  * A regular app server maintains a worker circularQueueDynamicSizing and pushes every new word to the circularQueueDynamicSizing. The circularQueueDynamicSizing has a responsibility to push new results in a bunch to the database. (Maybe update the cache first and then the cache updates the database).
4. Caching
  * Let's say that we decide to go with a relational database. We maintain a memcahced to cache top results. Every top-N query is routed to memcached and immediately gets an answer through cache. A worker circularQueueDynamicSizing updates memcached using its own results. Memcached updates the database at regular interval (every minute?) and refreshes itself using order-by query from the database. (Caching database index for order-by query to speed up memcache update). 

### Bottlenecks and Scaling
1. Cache contension and locking: Multiple write and refresh requests for cache creates contension. Maybe use a separate schedular/load-balancer for cache. Preference of operations top-N > worker-circularQueueDynamicSizing update > DB read/write.
2. App-server failure: A failing app-server implies that all its circularQueueDynamicSizing is lost. Can't do much in this case, except frequent writes from queues to cache. Also, this is one of the sources of approximation in top-N results. Assuming top-N results change slowly, a few lost words from the stream wouldn't affect them much.
3. Cache failure: A big issue. Maintain replicated/hirarchical cache
4. Database failure: Can be address with replicated (master/slave) database.
