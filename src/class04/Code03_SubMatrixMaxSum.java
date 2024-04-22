package class04;

/*
题目：返回一个二维数组中子矩阵最大累加和
时间：26
时间复杂度：O（N^2 * M), N是行数、M是列数
是否属于高频内容打包课：是
 */
public class Code03_SubMatrixMaxSum {

	/**
	 * 方法1：只抓到最大值是啥
	 * @param m
	 * @return
	 */
	public static int maxSum(int[][] m) {
		if (m == null || m.length == 0 || m[0].length == 0) {
			return 0;
		}
		// O(N^2 * M)
		int N = m.length;
		int M = m[0].length;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			int[] s = new int[M];
			for (int j = i; j < N; j++) { // 2个for循环：必须包含从i到j行数据，且只包含i到j行这么多数据，最大值是啥
				for (int k = 0; k < M; k++) {
					s[k] += m[j][k];
				}
				max = Math.max(max, maxSubArray(s));
			}
		}
		return max;
	}
	
	public static int maxSubArray(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int max = Integer.MIN_VALUE;
		int cur = 0;
		for (int i = 0; i < arr.length; i++) {
			cur += arr[i];
			max = Math.max(max, cur);
			cur = cur < 0 ? 0 : cur;
		}
		return max;
	}

	// 本题测试链接 : https://leetcode-cn.com/problems/max-submatrix-lcci/
	/**
	 * 方法2：来自力扣，需要返回矩形的左上角点和右下角点。
	 * @param m
	 * @return
	 */
	public static int[] getMaxMatrix(int[][] m) {
		int N = m.length;
		int M = m[0].length;
		int max = Integer.MIN_VALUE;
		int cur = 0;
		int a = 0;
		int b = 0;
		int c = 0;
		int d = 0;
		for (int i = 0; i < N; i++) {
			int[] s = new int[M];
			for (int j = i; j < N; j++) {
				cur = 0;
				int begin = 0;
				for (int k = 0; k < M; k++) {
					s[k] += m[j][k];
					cur += s[k];
					if (max < cur) {
						max = cur;
						a = i;
						b = begin;
						c = j;
						d = k;
					}
					if (cur < 0) {
						cur = 0;
						begin = k + 1;
					}
				}
			}
		}
		return new int[] { a, b, c, d };
	}

}
