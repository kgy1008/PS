import sys
input = sys.stdin.readline

t = int(input())

for _ in range(t):
  braket = input().rstrip()
  ans = True
  queue = []
  
  for i in braket:
    if i == '(':
      queue.append(i)
    elif i == ')':
      if queue:
        queue.pop()
      else:
        ans = False
        break
  if queue:
    ans = False
      
  if ans:
    print('YES')
  else:
    print('NO')