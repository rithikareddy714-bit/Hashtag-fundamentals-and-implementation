Hash Table Applications – System Design Problems (Java)
Overview
This repository contains 10 real-world system design problems implemented using Hash Tables in Java.
Each problem demonstrates how hash-based data structures (such as HashMap, LinkedHashMap, and HashSet) can be used to build high-performance systems with O(1) average lookup time.

These problems simulate real-world systems like flash sale inventory managers, DNS caches, plagiarism detectors, analytics dashboards, rate limiters, and caching systems.

Problems Implemented
Problem 1 – Social Media Username Availability Checker
A system that checks whether a username is already taken and suggests alternatives.

Features
O(1) username lookup using HashMap

Tracks frequency of attempted usernames

Suggests alternative usernames

Maintains registered users

Data Structures Used
HashMap<String, Integer> → username → userId

HashMap<String, Integer> → attempt frequency

Example
checkAvailability("john_doe") → false
checkAvailability("jane_smith") → true
suggestAlternatives("john_doe") → [john_doe1, john_doe2, john_doe_123]
Problem 2 – Flash Sale Inventory Manager
Simulates an e-commerce flash sale where thousands of users try to purchase limited stock items simultaneously.

Features
Real-time stock tracking

Prevents overselling

Maintains waiting list when stock is finished

O(1) stock lookup

Data Structures
HashMap<String, Integer> → product stock

HashMap<String, Queue<Integer>> → waiting list

Example
checkStock("IPHONE15") → 100 units available
purchaseItem("IPHONE15", userId=123) → Success
purchaseItem("IPHONE15", userId=999) → Added to waiting list
Problem 3 – DNS Cache with TTL
A DNS resolver cache that stores domain-to-IP mappings and automatically expires them using TTL.

Features
Fast domain lookup

Cache expiration using TTL

Cache hit/miss tracking

Simulates upstream DNS queries

Data Structures
HashMap<String, DNSEntry>

DNSEntry
domain
ipAddress
timestamp
expiryTime
Example
resolve("google.com") → Cache MISS → Query upstream
resolve("google.com") → Cache HIT
Problem 4 – Plagiarism Detection System
Detects similarity between documents by comparing n-grams (word sequences).

Features
Extracts n-grams from documents

Stores them in a hash table

Finds matching documents quickly

Calculates similarity percentage

Data Structures
HashMap<String, Set<String>>

Key → n-gram

Value → documents containing that n-gram

Example
Extracted 450 n-grams
Found 67 matching n-grams
Similarity: 14.9%
Problem 5 – Real-Time Website Analytics
Processes millions of page views and provides a real-time analytics dashboard.

Features
Tracks page views

Tracks unique visitors

Tracks traffic sources

Displays top pages

Data Structures
HashMap<String, Integer> → page views

HashMap<String, Set<String>> → unique visitors

HashMap<String, Integer> → traffic sources

Example
/news → 15,423 views (8,234 unique visitors)
Traffic Sources:
Google → 45%
Direct → 30%
Facebook → 15%
Problem 6 – Distributed API Rate Limiter
Implements a Token Bucket rate limiting algorithm for APIs.

Features
Limits requests per client

Allows burst traffic

Automatically resets tokens

Prevents API abuse

Data Structures
HashMap<String, TokenBucket>

TokenBucket Structure
tokens
maxTokens
lastRefillTime
Example
Request 1 → Allowed
Request 2 → Allowed
Request 6 → Denied
Problem 7 – Search Engine Autocomplete
Provides Google-like search suggestions based on prefix and frequency.

Features
Stores search queries with frequency

Suggests top results for a prefix

Updates frequency dynamically

Data Structures
HashMap<String, Integer> → query frequency

Example
search("jav") →
1. java tutorial
2. javascript guide
3. java download
Problem 8 – Parking Lot Management (Open Addressing)
Implements a parking system using hashing with linear probing.

Features
Assigns parking spots using hash function

Resolves collisions with linear probing

Handles vehicle entry and exit

Data Structures
Array-based hash table

Linear probing collision resolution

Example
parkVehicle("ABC123") → Spot #3
parkVehicle("XYZ999") → Spot #4
exitVehicle("ABC123") → Spot freed
Problem 9 – Two-Sum Transaction Detection
Detects suspicious financial transactions whose amounts sum to a target.

Features
Classic Two-Sum algorithm

O(n) time complexity

Fraud detection use case

Data Structures
HashMap<Integer, Integer>

Example
Transactions = [500, 300, 200]
Target = 500

Output:
300 + 200 = 500
Problem 10 – Multi-Level Cache System
Simulates a Netflix-style caching architecture.

Cache Layers
L1 → Memory Cache (fastest)
L2 → SSD Cache
L3 → Database (slowest)
Features
LRU eviction policy

Cache promotion between layers

Faster repeated access

Data Structures
LinkedHashMap → L1 cache

HashMap → L2 cache

Example
getVideo("video1")
→ L1 MISS
→ L2 HIT
→ promoted to L1

Next request:
→ L1 HIT
Time Complexity Summary
Operation	Time Complexity
HashMap lookup	O(1) average
Insert	O(1) average
Search	O(1) average
Two-Sum	O(n)
Autocomplete prefix search	O(n log n)
Technologies Used
Java

HashMap

LinkedHashMap

HashSet

Queue / LinkedList

Arrays

Real World Applications
These implementations simulate systems used in:

Instagram / Twitter username checking

Amazon flash sales

Browser DNS caching

Turnitin plagiarism detection

Google Analytics dashboards

API gateways (AWS, Stripe, GitHub)

Google search autocomplete

Smart parking systems

Financial fraud detection

Netflix CDN caching

Author
Java Hash Table System Design Practice Implementation
