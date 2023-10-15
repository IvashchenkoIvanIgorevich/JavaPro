package leetcode;

public class LinkedListHelper {

    public static ListNode reverseList(ListNode head) {
        ListNode previous = null;

        while (head != null) {
            ListNode next = head.next;
            head.next = previous;
            previous = head;
            head = next;
        }

        return previous;
    }

    //TODO: got from youtube, saved it here for future
    public static ListNode reverseListRecursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // Reverse the rest of the list
        ListNode reversedList = reverseList(head.next);

        // Reverse the current node
        head.next.next = head;
        head.next = null;

        return reversedList;
    }
}

