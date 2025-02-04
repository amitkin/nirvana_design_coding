# System Design Approach

## 1. Introduction
System design approach explains a basic approach that should be followed while solving any architectural or system level design questions. Questions like design Monitoring or Search & Recommendation Systems and products like AWS, Netflix, Uber etc. fall into these categories. The aim here is to create an efficient yet generic enough approach that can be applied to a variety of architectural & high level design problems.

## 2. Approach

### 2.1 Features - [2 mins]
* **2.1.1.** Scale - Data size, Message size, User numbers
* **2.1.2.** Main Features

### 2.2 Estimations - [5 mins]
* **2.2.1.** System Total Data & Storage Size
* **2.2.2.** Total Queries Per Seconds
* **2.2.3.** Minimum Machines

### 2.3 Design Goals - [2 mins]
* **2.3.1.** Latency, Consistency, Availability, Partition Tolerance
* **2.3.2.** CAP Theorem
* **2.3.3.** What's important for this system/product?

### 2.4 Skeleton Of Design - [5 mins]
* **2.4.1.** Operations Supported
* **2.4.2.** Client API Design - Input, Output, Data Structures & URLs
* **2.4.3.** APIs Usage Flow

### 2.5. Dive Deep - [20 - 30 mins]
* **2.5.1.** Application Layer - Load Balancers
* **2.5.2.** Database Layer - SQL or NoSQL?
   * **2.5.2.1.** Joins Required?
   * **2.5.2.2.** Data Size?
   * **2.5.2.3.** Read Write Pattern?
   * **2.5.2.4.** Sharding Required?
   * **2.5.2.5.** Fault Tolerance?
   * **2.5.2.6.** Availability?
* **2.5.3.** Improve Efficieny With Caching & Consistent Hashing


# Object Oriented Approach

## Approach
* Identify the basic system components which will translate to objects.
* Identify the possible actions for these componets which will translate to methods
