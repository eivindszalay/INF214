**Task 1**

*Task 1A*
1,2 -> 1
1,2,3,4 -> = 1*(3+2+1) = 6
1,2,3,4,5,6 -> 6*(7+6+5+4+3+2+1) = 168


*Task 1B*
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