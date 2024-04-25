package class10;

// 本题测试链接 : https://leetcode.com/problems/jump-game-ii/

/*
题目：跳跃游戏Ⅱ
	给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
	数组中的每个元素代表你在该位置可以跳跃的最大长度。
	你的目标是使用最少的跳跃次数到达数组的最后一个位置。
	假设你总是可以到达数组的最后一个位置。
时间：15
时间复杂度：
是否属于高频内容打包课：是
 */
public class Code01_JumpGame {

	public static int jump(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int step = 0; // 当前一共跳的步数
		int cur = 0; // step步，最多能到哪里
		int next = 0; // step+1步，最多能到哪里
		for (int i = 0; i < arr.length; i++) {
			if (cur < i) {
				step++;
				cur = next;
			}
			next = Math.max(next, i + arr[i]);
		}
		return step;
	}

}
