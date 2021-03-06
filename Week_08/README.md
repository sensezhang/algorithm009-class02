学习笔记
## 位运算

|   含义   | 位运算 |        示例         |          说明           |
| ------- | ----- | ------------------- | ----------------------- |
| 左移     | <<    | 0011 -> 0110        | n*2                     |
| 右移     | >>     | 0011 -> 0001        | n / 2                   |
| 按位或   | \|    | 0011 \| 1011 = 1011 | 0 0 为 0；1 1为1；0 1为1 |
| 按位与   | &     | 0011 & 1011 = 0011  | 1 1为1；其它为0          |
| 按位取反 | ~     | ~0011 = 1100        | 1变0，0变1               |
| 按位异或 | ^      | 0011^1011 = 1000    | 相同为0，不同为1         |

1. 根据异或的特点，有很多算法题，需要熟记：  
x^0 = x
x^1s = ~x // 1s = ~0
x^(~x) = 1s
x^x = 0 // 可用来判断重复元素
c = a ^ b => a ^ c = b, b ^ c = a  // 交换两个数
a ^ b ^ c = a ^ (b ^ c) = (a ^ b) ^ c  

2. 指定位置的位运算
将x 最右边的 n 位清零：x&(~0<< n)
获取x 的第 n 位值（0 或者1）: (x >> n) & 1
获取x 的第 n 位的幂值: x & (1 << n)
仅将第 n 位置为1: x | (1<<n)
仅将第 n 位置为0：x & (~(1<<n))
将x 最高位至第 n 位（含）清零：x& ((1 << n) -1)
 将第 n 位至第0 位（含）清零：x& (~ ((1 << (n + 1)) -1))

3. 实战位运算
判断奇偶：
x % 2 == 1 —> (x & 1) == 1
x % 2 == 0 —> (x & 1) == 0

x >> 1 —> x / 2.
即： x = x / 2; —> x = x >> 1;
mid = (left + right) / 2; —> mid = (left + right) >> 1;

X = X & (X-1) 清零最低位的 1
X & -X => 得到最低位的 1
X & ~X => 0

## 布隆过滤器（Bloom Filter）

一个很长的二进制向量和一系列随即映射函数。布隆过滤器可以用于检索一个元素是否再一个集合中
优点：空间效率和查询时间都远远超过一般算法
缺点：有一定的误判率，以及删除困难（布隆过滤器判断一个元素存在，那么该元素可能存在，判断一个元素不存在，那么该元素一定不存在）

## 排序算法

冒泡排序、选择排序、插入排序
快排、归并排序、计数排序、桶排序等