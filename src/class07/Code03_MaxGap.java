package class07;

// 测试链接 : https://leetcode.com/problems/maximum-gap/
/*
题目：给定一个数组arr，返回如果排序之后（注意是如果排序），相邻两数的最大差值。
	要求时间复杂度O(N)，不能使用非基于比较的排序
时间：1:12
时间复杂度：O（N）
是否属于高频内容打包课：是
 */
public class Code03_MaxGap {

	public static int maximumGap(int[] nums) {
		if (nums == null || nums.length < 2) {
			return 0;
		}
		int N = nums.length;
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			min = Math.min(min, nums[i]);
			max = Math.max(max, nums[i]);
		}
		if (min == max) {
			return 0;
		}
		boolean[] hasNum = new boolean[N + 1];
		int[] maxs = new int[N + 1];
		int[] mins = new int[N + 1];
		int bid = 0;
		for (int i = 0; i < N; i++) {
			bid = bucket(nums[i], N, min, max);
			mins[bid] = hasNum[bid] ? Math.min(mins[bid], nums[i]) : nums[i];
			maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], nums[i]) : nums[i];
			hasNum[bid] = true;
		}
		int res = 0;
		int lastMax = maxs[0];
		int i = 1;
		for (; i <= N; i++) {
			if (hasNum[i]) {
				res = Math.max(res, mins[i] - lastMax);
				lastMax = maxs[i];
			}
		}
		return res;
	}

	/*
	如果当前的数是num，整个范围是min-max,分成了N + 1份，返回num该进入几号捅
	 */
	public static int bucket(int num, int N, int min, int max) {
		// 一个桶的范围
		double range = (double) (max - min) / (double) N;
		// num和min之间的距离
		double distance = (double) (num - min);
		// 返回桶的编号，向下取整
		return (int) (distance / range);
	}

}
