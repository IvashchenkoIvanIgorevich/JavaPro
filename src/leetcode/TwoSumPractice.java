package leetcode;

import java.util.Map;
import java.util.HashMap;

public class TwoSumPractice {

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> indexes = new HashMap<>();

        for (int index = 0; index < nums.length; index++) {
            int secondNumber = target - nums[index];
            if (indexes.containsKey(secondNumber)) {
                return new int[]{indexes.get(secondNumber), index};
            }
            indexes.put(nums[index], index);
        }
        return new int[0];
    }
}
