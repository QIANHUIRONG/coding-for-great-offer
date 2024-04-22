package class03;

import java.util.Arrays;

/*
题目：给定一个正数数组arr，代表若干人的体重，再给定一个正数limit，表示所有船共同拥有的载重量，每艘船最多坐两人，且不能超过载重 想让所有的人同时过河，并且用最好的分配方法让船尽量少，返回最少的船数
时间：1：40 -> 推荐B站：50节，50分钟
时间复杂度：
是否属于高频内容打包课：是
 */
// 测试链接 : https://leetcode.com/problems/boats-to-save-people/
public class Code05_BoatsToSavePeople {

	/**
	 * 方法1：大厂刷题班讲解的(推荐方法2)
	 * @param arr
	 * @param limit
	 * @return
	 */
	public static int numRescueBoats1(int[] arr, int limit) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		Arrays.sort(arr);
		if (arr[N - 1] > limit) {
			return -1;
		}
		int lessR = -1;
		for (int i = N - 1; i >= 0; i--) {
			if (arr[i] <= (limit / 2)) {
				lessR = i;
				break;
			}
		}
		if (lessR == -1) {
			return N;
		}
		int L = lessR;
		int R = lessR + 1;
		int noUsed = 0;
		while (L >= 0) {
			int solved = 0;
			while (R < N && arr[L] + arr[R] <= limit) {
				R++;
				solved++;
			}
			if (solved == 0) {
				noUsed++;
				L--;
			} else {
				L = Math.max(-1, L - solved);
			}
		}
		int all = lessR + 1;
		int used = all - noUsed;
		int moreUnsolved = (N - all) - used;
		return used + ((noUsed + 1) >> 1) + moreUnsolved;
	}

	// 首尾双指针的解法
	// 时间复杂度O(n * logn)，因为有排序，额外空间复杂度O(1)
	/**
	 * 方法2：算法通关课讲解的。双指针
	 * 先排序，然后双指针，如果l和r位置的数++大于limit，因为此时l已经是最小了，没办法，r自己一组
	 * 如果l和r位置的数++ <= limit, 这2个位置一艘船。此时l是最小的，r是最大的，不吃亏
	 *
	 *
	 * @param people
	 * @param limit
	 * @return
	 */
	public static int numRescueBoats2(int[] people, int limit) {
		// 1.排序
		Arrays.sort(people);

		// 2.双指针
		int ans = 0;
		int l = 0;
		int r = people.length - 1;
		int sum = 0;
		while (l <= r) {
			sum = l == r ? people[l] : people[l] + people[r]; // 如果l和r撞上了，总和不要算重了
			if (sum > limit) {
				r--;
			} else {
				l++;
				r--;
			}
			ans++;
		}
		return ans;
	}

	/**
	 * 扩展：
	 * 再增加一个要求，如果两人一船那么体重之和必须是偶数，又该怎么做？（大厂真考过）
	 * 题解：
	 * 	因为奇数+偶数一定不是偶数，所以必须奇数+奇数 / 偶数+偶数；
	 * 	所以奇数一组；偶数一组；然后跑一遍上面的流程。
	 */

}
