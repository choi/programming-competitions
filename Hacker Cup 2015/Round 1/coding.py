import time

start = time.time()

f = open("coding_contest.in")
g = open("coding_contest_output.txt",'w')

for T in xrange(1,1+int(f.readline())):
    N = int(f.readline())
    lis = [int(i) for i in f.readline().split()]
    i = 0     # index in lis
    acc = 0   # accumulator
    count = 0 # index of question in contest 

    while i < len(lis)-1:
        if lis[i] >= lis[i+1]:                   # question value exceeds following value
            acc += 3-count                       # insert remaining questions
            count = 0                            # reset question index (new contest)
        elif lis[i+1] - lis[i] <= 10:            # valid question value
            count = (count+1)%4                  # set question index
        elif lis[i+1] - lis[i] <= 10*(2-count):  # insert one value between two questions
            acc += 1-count                       # inserting one value
            count = (count+2)%4                  # set proper question index
        elif lis[i+1] - lis[i] <= 10*(3-count):  # two values fit between two questions
            acc += 2-count                       # insert remaining questions
            i += 1                               # skip over question 4 in contest
            count = 0                            # reset question index (new contest)
        else:                                    # following value unreachable
            acc += 3-count                       # insert remaining questions
            count = 0                            # reset question index (new contest)
            
        i += 1                                   # move to next question

    acc += 3-count                               # insert remaining questions

    g.write("Case #%d: %d\n" % (T, acc))

end = time.time()
g.close()
raw_input(end-start)