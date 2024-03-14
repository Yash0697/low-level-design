
#How does redis works over RESP?
A client sends the Redis server an array consisting of only bulk strings.
A Redis server replies to clients, sending any valid RESP data type as a reply.

The client sends the command LLEN mylist to get the length of the list stored at the key mylist. Then the server replies with an integer reply as in the following example (C: is the client, S: the server).

#How to run the Programme
1. Run ServerRunner.java
2. Run ClientRunner.java
3. Run commands
4. Exit
