N, K = map(int,input().split())
person = [i for i in range(1,N+1)] 
arr = [] 
idx = 0
for j in range (N):
    idx += K-1
    if idx >= len(person):
        idx = idx % len(person)
    arr.append(str(person.pop(idx)))

print('<', ', '.join(arr)[:], '>', sep='')