Coding Exercises  [![Build Status](https://travis-ci.org/karlmutch/CodingExercises.svg?branch=master)](https://travis-ci.org/karlmutch/CodingExercises)
================

A set of classes typical of job interviews and solutions.

Motivation
----------

During my most recent job we have had several projects close due to redundancy within our product offerings.  Being given good notice people spent large amounts of time reading coding interview books and visiting various web sites to learn skills that they never exercised within their respective roles and probably would completely forget until the next occasion of job hunting.

All, both good and fair performers, ended up looking like stellar heros when subjected to job interviews that did little to actually differentiate them from each other.

So, to save time, and allow me to do my day job I offer the following coding examples.  Checking of parameters is suspended due to most whiteboard examples not having the space to check for null's, empty parameters and the like.

### Examples

* [Recursive string reversal] (https://github.com/karlmutch/CodingExercises/blob/master/src/main/java/com/karlmutch/OnlyInAnInterviewStrings.java)
* [A queue implemented using 2 stacks] (https://github.com/karlmutch/CodingExercises/blob/master/src/main/java/com/karlmutch/QueueBasedStack.java)
* [Simple B-Tree style class with traversals] (https://github.com/karlmutch/CodingExercises/blob/master/src/main/java/com/karlmutch/SearchTree.java)
* [LRU Caching without using Guava, JDK cache style classes etc] (https://github.com/karlmutch/CodingExercises/blob/master/src/main/java/com/karlmutch/LRUCacheLinkedHashMap.java)
* [Count the digit '1' in a number] (https://github.com/karlmutch/CodingExercises/blob/master/src/main/java/com/karlmutch/CountOnes.java)

I will add others as I discover common cases.

Compilation
-----------

A maven pom file is provided with a small set of Cucumber JVM tests implemented for many of the examples.