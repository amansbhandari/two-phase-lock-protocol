# Two Phase Locking Protocol

### This application is a sample implementation to demonstrate 2PL (without semaphores). Please check this to get a better understanding of the concept.

## Why do we need 2PL?

Two Phase Locking Protocol also known as 2PL protocol is a method of concurrency control in DBMS that ensures serializability by applying a lock to the transaction data which blocks other transactions to access the same data simultaneously. Two Phase Locking protocol helps to eliminate the concurrency problem in DBMS.

## What is 2PL?

A transaction is said to follow the Two-Phase Locking protocol if Locking and Unlocking can be done in two phases. 

Growing Phase: New locks on data items may be acquired but none can be released.
Shrinking Phase: Existing locks may be released but no new locks can be acquired.

## Steps to run
* Go to the root diarectory.
* Run ```mvn clean install```
* Go to target folder and run command ```java -jar assignment3.jar```

![Activity Diagram](/images/activity_diagram.jpg "This is a sample image.")

## Output
![Screen Output.](/images/output.jpg "Output")

## Tools and Languages used

* Java
* Maven
