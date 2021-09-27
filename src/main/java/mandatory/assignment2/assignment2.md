**Task 1A**

printer() could execute in 90 different orders. Here is my reasoning:

If we only have P1, the writes can only come in 1 order: **write(1), write(2)**.

If we now introduce P2, write(3) can happen 3 different places: 
* Before write(1): this gives 3 possible places for write(4) to happen.
* Between write(1) and write(2): this gives 2 possible places for write(4) to happen.
* After write(2): this gives 1 possible place for write(4) to happen.

In total, there are 3+2+1=6 different orders in which P1 and P2 could execute.

When we introduce P3, we can use a similar argument to get that there are 6*(5+4+3+2+1)=90 orders in which P1, P2, and P3 can execute. We multiply by 6 to account for the 6 possible orders for P1 and P2.

Note: I have assumed that write() is not an alien method and that it executes atomically.

****
**Task 1B**

Here I have included semaphores to force a certain ordering. I have used 3 semaphores. I also only release a semaphore when the process trying to aquire it should be next in the order of execution. After P3 has executed, I release sem1 again, this preserves the state of the program before and after the processes has executed. 


    printer() {
        sem sem1 = 1;
        sem sem2 = 0;
        sem sem3 = 0;

        <await (sem1 > 0 ) P(sem1)>
        process P1 { write("1");  write("2"); }
        V(sem2);

        <await (sem1 > 0 ) P(sem2)>
        process P2 { write("3");  write("4"); }
        V(sem3);

        <await (sem3 > 0 ) P(sem3)>
        process P3 { write("5");  write("6"); }
        V(sem1);
    }
****
**Task 2**

You can inspect the implementation of the semaphores under task/Sem.java
****

**Task 3**

To run the code, navigate to the source folder of this project in the terminal.
Compile with:

    javac mandatory/assignment2/task3/Main.java

And run the code with:

    java mandatory.assignment2.task3.Main

****
**Task 4A**

*CyclicBarrier* is a Java class for creating barriers when a program needs to wait for all threads to finish. 
It is made for programs with a fixed number of threads, like in this one, where every thread need to execute before the program continues.
It is prefixed with Cyclic to indicated that the barrier can be reused after releasing the threads.

*BrokenBarrierException* is thrown when a thread tries to wait upon a barrier that is in a broken state. 
****
**Task 4B**

To run the code, navigate to the source folder of this project in the terminal.
Compile with:

    javac mandatory/assignment2/task4/DollFactory.java

And run the code with:

    java mandatory.assignment2.task4.Dollfactory
