# 分治算法

## 定义
将原问题分解成规模较小的若干小问题，小问题解决后，把小问题的答案合并成原问题的完整答案。
"分"就是分解问题，"治"就是合并答案

分治算法是一种处理问题的思想，而递归是一种编程技巧

分治算法的一般处理思路：
1.分解小问题；原问题可以分解为规模较小但是模式相同的子问题，且子问题相互独立。这点是动态规划问题的区别
2.递归求解子问题，子问题足够小的时候，可以直接求解，也就是有终止条件
3.子问题答案合并成原问题答案，这个合并操作不能太复杂，否则就无法降低整体的复杂度了。其实合并子问题这一点往往是比较难的一步


## 经典场景

1.快速排序算法
2.合并排序算法
3.桶排序算法
4.基数排序算法
5.二分查找算法
6.利用递归树求解算法复杂度的思想
7.分布式数据库利用分片技术做数据处理
8.MapReduce模型处理思想
