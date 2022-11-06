def sum_of_fifth_powers(n):
    sum = 0
    for i in str(n):
        sum += int(i) ** 5
    return sum

def main():
    answer = 0
    for i in range(2, 295245):
        if i == sum_of_fifth_powers(i):
            answer += i
    print(answer)

main()