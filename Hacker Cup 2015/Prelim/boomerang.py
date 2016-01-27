import time

start = time.time()

f = open("boomerang_constellations.txt")
g = open("boomerang_constellations_output2.txt",'w')

for T in xrange(1,1+int(f.readline())):
    lis = []
    for N in xrange(int(f.readline())):
        lis.append(tuple(int(i) for i in f.readline().split()))

    distances = {}
    count=0
    for i in xrange(len(lis)-1):
        for j in xrange(1,len(lis)-i):
            x = lis[i][0] - lis[i+j][0]
            y = lis[i][1] - lis[i+j][1]
            d2 = x*x+y*y

            if not d2 in distances:
                distances[d2] = {}
            if i in distances[d2]:
                distances[d2][i] += 1
            else:
                distances[d2][i] = 1
            if i+j in distances[d2]:
                distances[d2][i+j] += 1
            else:
                distances[d2][i+j] = 1
            
    tot = 0

    for i in distances:
        for j in distances[i]:
            l = distances[i][j]
            tot += l*(l-1)/2

    g.write("Case #%d: %d\n" % (T, tot))

end = time.time()
g.close()
raw_input(end-start)