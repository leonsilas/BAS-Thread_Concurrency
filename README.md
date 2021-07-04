# Singlethreading and multithreading
### This simple summation array program is meant to show how threads work with java.

I want to mention something about the multithreading process though, as shown below.

```
public static synchronized void totalUp(int nextNum) {
        multipleTotal += nextNum;
    }
```

While synchronized methods can be great when you want to make sure not to cross threads on accessing and overwriting certain areas of memory, when doing a process like adding numbers in an array, you'll quickly learn that as each thread waits to use the method, it wastes even more time than simply using a single thread.  Running seperate counts and then adding it at the end will increase efficiency instead. (Shown by the output in the program.)