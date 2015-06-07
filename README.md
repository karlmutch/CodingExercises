Coding Exercises  [![Build Status](https://travis-ci.org/karlmutch/CodingExercises.svg?branch=master)](https://travis-ci.org/karlmutch/CodingExercises)
================

In response to job interview questions and interesting code challenges, a set of Java 8 classes, and their Cucumber based tests.

Motivation
----------

During a recent full time job assignment several projects were wound down due to redundancy within our product offerings.  Being given good notice people spent large amounts of time reading coding interview books and visiting various web sites to learn skills that they never exercised within their respective roles and probably would completely forget until the next occasion of job hunting.

All, both good and fair performers, ended up looking like stellar heros when subjected to job interviews that did little to actually differentiate them from each other.

So, to save time and give me a place to store small algo and coding experiments, I offer the following coding examples.

Checking of parameters is suspended due to most whiteboard examples not having the space to check for null's, empty parameters and the like.

### Examples

There are 30 plus examples available so looking at them all is probably not practical.  Choose some that are small enough or some that are interesting enough to warrant a look at.

* [Recursive string reversal] (https://github.com/karlmutch/CodingExercises/blob/master/src/main/java/com/karlmutch/OnlyInAnInterviewStrings.java)
* [A queue implemented using 2 stacks] (https://github.com/karlmutch/CodingExercises/blob/master/src/main/java/com/karlmutch/QueueBasedStack.java)
* [Simple B-Tree style class with traversals] (https://github.com/karlmutch/CodingExercises/blob/master/src/main/java/com/karlmutch/SearchTree.java)
* [LRU Caching without using Guava, JDK cache style classes etc] (https://github.com/karlmutch/CodingExercises/blob/master/src/main/java/com/karlmutch/LRUCacheLinkedHashMap.java)
* [Count the digit '1' in a number] (https://github.com/karlmutch/CodingExercises/blob/master/src/main/java/com/karlmutch/CountOnes.java)
* [Fibonacci numer series generator using Eigenvalues]
(https://github.com/karlmutch/CodingExercises/blob/master/src/main/java/com/karlmutch/EigenvalueFibonacci.java)
* [Fibonacci numer series generator using Memoization]
(https://github.com/karlmutch/CodingExercises/blob/master/src/main/java/com/karlmutch/LookasideFibonacci.java)
* [Insertion sorting - unoptimized, and optimized (3-4x faster)]
(https://github.com/karlmutch/CodingExercises/blob/master/src/main/java/com/karlmutch/InsertionSort.java)

New examples are being added on a semi regular basis.

Compilation
-----------

A maven pom file is provided with a small set of Cucumber JVM tests implemented for many of the examples.