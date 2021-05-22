###Problem Statement

We have to do low level design for a Cache system. Cache that we will design will have to support following operations:

    Put: This will allow user to put a value against a key in the cache.
    Get: This will allow user to get the previously saved value using key.
    Eviction: Cache should also support removal of some key in case cache is full, and we try to add new key value.

Expectations

    Code should be functionally correct.
    Code should be modular and readable. Clean and professional level code.
    Code should be extensible and scalable. Means it should be able to accommodate new requirements with minimal changes.
    Code should have good OOPs design.


###Solution

#### Design Patterns
- Dependency Inversion by injecting EvictionPolicy and Storage in Cache.
- It uses immutability for EvictionPolicy and Storage in Cache.
- It uses Factory pattern for creating Cache.
- It uses Template pattern in EvictionPolicy.

#### Improvements
- EvictionPolicy could be full fledged object then it can take algorithm type as input in constructor. This is strategy pattern.
Algorithm interface will have behaviours keyAccessed() and evictKey()

#### Reference:
1. https://www.youtube.com/watch?v=B7iCXl_KSoM