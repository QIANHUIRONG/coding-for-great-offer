package class08;

// 本题测试链接 : https://leetcode.com/problems/container-with-most-water/
/*
题目：盛最多水的容器
	给定n个非负整数a1，a2，...an，每个数代表坐标中的一个点 (i, ai)，
	在坐标内画n条垂直线，垂直线i的两个端点分别为(i, ai)和(i, 0)，找出其中的两条线，
	使得它们与x轴共同构成的容器可以容纳最多的水
时间：51
时间复杂度：O（N）
是否属于高频内容打包课：是
 */
public class Code02_ContainerWithMostWater {

	/**
	 * 这个方法没讲，看下面的
	 * @param h
	 * @return
	 */
	public static int maxArea1(int[] h) {
		int max = 0;
		int N = h.length;
		for (int i = 0; i < N; i++) { // h[i]
			for (int j = i + 1; j < N; j++) { // h[j]
				max = Math.max(max, Math.min(h[i], h[j]) * (j - i));
			}
		}
		return max;
	}

	/**
	 * 双指针
	 * 只关心有没有推高答案的可能性，而不去纠结每个位置正确的答案。
	 * 这个思想在数组3连问题中的第三连讲过。
	 * @param h
	 * @return
	 */
	public static int maxArea2(int[] h) {
		int max = 0;
		int l = 0;
		int r = h.length - 1;
		while (l < r) {
			max = Math.max(max, Math.min(h[l], h[r]) * (r - l));
			if (h[l] > h[r]) {
				r--;
			} else {
				l++;
			}
		}
		return max;
	}

}
