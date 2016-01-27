import time

start = time.time()

f = open("the_price_is_correct.txt")
g = open("the_price_is_correct_output2.txt",'w')

for T in xrange(1,1+int(f.readline())):
    N, P = (int(i) for i in f.readline().split())
    lis = [int(i) for i in f.readline().split()]
    front, back = 0, 0
    acc = 0
    count = 0

    while back < len(lis):
        if acc + lis[back] > P:
            acc -= lis[front]
            front += 1
        else:
            acc += lis[back]
            count += back - front + 1
            back += 1

    g.write("Case #%d: %d\n" % (T, count))

end = time.time()
g.close()
raw_input(end-start)