**Task 1A**

1,2 -> 1

1,2,3,4 -> = 1*(3+2+1) = 6

1,2,3,4,5,6 -> 6*(7+6+5+4+3+2+1) = 168



**Task 1B**

Spørsmål: Istedenfor å in- og dekrementere, burde vi heller bruke P og V på semaforen?


    printer() {
        sem sem1 = 1;
        sem sem2 = 0;
        sem sem3 = 0;

        while (true) {
            <await (sem1 > 0 ) sem1 = sem1 - 1>
            process P1 { write("1");  write("2"); }
            sem1 = sem1 + 1;
            sem2 = sem2 + 1;
        }

        while (true) {
            <await (sem1 > 0 ) sem2 = sem2 - 1>
            process P2 { write("3");  write("4"); }
            sem2 = sem2 + 1;
            sem3 = sem3 + 1;
        }

        while (true) {
            <await (sem3 > 0 ) sem3 = sem3 - 1>
            process P3 { write("5");  write("6"); }
            sem3 = sem3 + 1;
        }
    }

**Task 4A**

*CyclicBarrier* is a Java class for creating barriers when a program needs to wait for all threads to finish. 
It is made for programs with a fixed number of threads, like in this one, where every thread need to execute before the program continues.
It is prefixed with Cyclic to indicated that the barrier can be reused after releasing the threads.

*BrokenBarrierException* is thrown when a thread tries to wait upon a barrier that is in a broken state. 