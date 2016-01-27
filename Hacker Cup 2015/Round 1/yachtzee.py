import time

start = time.time()

f = open("yachtzee.txt")
g = open("yachtzee_output.txt",'w')

for T in xrange(1,1+int(f.readline())):
    N,A,B = tuple(int(i) for i in f.readline().split())
    C = [int(i) for i in f.readline().split()]
    
    # we will split the probability into three parts
    #     yacht1       yachti       yachtN
    # |------------|----....----|------------|
    # |--A---------|----....----|-------B----|
    # |--|--start--|--complete--|--end--|----|

    # expected values for start, complete, and end
    EVcomplete = 0
    EVstart = 0 
    EVend = 0
    EV = 0
    
    cost = 0    #cost of a yacht. to be used later
    for i in C:
        cost += i
        EVcomplete += i*i*0.5

    startpoint = A%cost
    endpoint = B%cost

    startlen = cost-startpoint
    endlen = endpoint
    completelen = (B-endlen) - (A+startlen)

    if completelen >= 0: 
        for i in C:
            if startpoint > i:          # slower if startlen > complete/2
                startpoint -= i
            elif startpoint == 0:
                EVstart += i*i*0.5
            else:
                EVstart += (i+startpoint)*(i-startpoint)*0.5
                startpoint = 0

            if endpoint > i:            # slower if endlen < complete/2
                EVend += 0.5*i*i
                endpoint -= i
            elif endpoint != 0:
                EVend += endpoint*endpoint*0.5
                endpoint = 0
        EVcomplete /= cost
        if startlen != 0:
            EVstart /= startlen
        if endlen != 0:
            EVend /= endlen
        EV = (completelen*EVcomplete + startlen*EVstart + endlen*EVend) / (B-A)
    else:                               # when the difference between B and A is less than the cost of a yacht
        for i in C:
            if startpoint > i:          # slower if startlen > complete/2
                startpoint -= i
                endpoint -= i
            else:
                if endpoint > i:
                    if startpoint != 0:
                        EV += (i+startpoint)*(i-startpoint)*0.5
                        startpoint = 0
                    else:
                        EV += i*i*0.5
                    endpoint -= i
                elif endpoint != 0:
                    if startpoint == 0:
                        EV += endpoint*endpoint*0.5
                        endpoint = 0
                    else:
                        EV += (endpoint-startpoint)*(endpoint+startpoint)*0.5
                    endpoint = 0
        EV /= (B-A)

    g.write("Case #%d: %.9f\n" % (T, EV))

end = time.time()
g.close()
raw_input(end-start)