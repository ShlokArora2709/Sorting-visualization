package project.Sorts;

import project.*;

public class InsertionSort implements Runnable {

    private Integer[] toBeSorted;
    private VisualizerFrame frame;

    public InsertionSort(Integer[] toBeSorted, VisualizerFrame frame) {
        this.toBeSorted = toBeSorted;
        this.frame = frame;
    }

    public void run() {
        sort();
        SortingVisualizer.isSorting = false;
    }

    public void sort() {
        int temp;
        int insert;

        for (int i = 1; i < toBeSorted.length; i++) {
            temp = toBeSorted[i]; // Element to be inserted
            insert = i; // Position where the element will be inserted

            // Compare with the previous elements
            for (int j = i - 1; j >= 0; j--) {
                // Highlight the current comparison
                frame.reDrawArray(toBeSorted, i, j); // Highlight the current element and the one being compared
                sleep();

                if (temp < toBeSorted[j]) {
                    insert = j; // Move insert position back
                } else {
                    break; // No need to keep checking
                }
            }

            // Shift elements to create the correct position for temp
            for (int j = i; j > insert; j--) {
                toBeSorted[j] = toBeSorted[j - 1]; // Shift
                frame.reDrawArray(toBeSorted, j, insert); // Highlight shifting
                sleep();
            }
            toBeSorted[insert] = temp; // Insert the element at the correct position
            frame.reDrawArray(toBeSorted, insert); // Final position of the inserted element
            sleep();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(SortingVisualizer.sleep); // Adjust as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
