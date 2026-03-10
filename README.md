# Hashtag-fundamentals-and-implementation
Social Media Username Availability Checker
This project implements a system to check username availability for a social media platform. It allows users to verify if a username is available, suggests alternative usernames, and tracks the popularity of attempted usernames.

Features
Check username availability in O(1) time using a hash table

Suggest alternative usernames if the requested one is already taken

Track how many times a username has been attempted

Find the most frequently attempted username

Supports concurrent requests using ConcurrentHashMap

Data Structures Used
HashMap / ConcurrentHashMap

usernameMap – stores registered usernames and their user IDs

attemptFrequency – tracks how many times a username was searched

Methods
checkAvailability(String username)
Checks whether a username is available.

Example:

checkAvailability("john_doe") → false
checkAvailability("jane_smith") → true
suggestAlternatives(String username)
Returns a list of alternative usernames if the requested username is taken.

Example:

suggestAlternatives("john_doe")
Output:

["john_doe1", "john_doe2", "john.doe"]
getMostAttempted()
Returns the username that has been searched the most.

Example:

admin (10543 attempts)
Time Complexity
Operation	Complexity
Check username	O(1)
Register user	O(1)
Track attempts	O(1)
Suggest alternatives	O(k)
Find most attempted	O(n)
Use Cases
Social media username registration

Gaming platform username selection

Email address availability checking

Technologies Used
Java

HashMap / ConcurrentHashMap

Object-Oriented Programming