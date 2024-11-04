package project.Sorts;

import project.*;

public class QuickSort implements Runnable {

    private Integer[] toBeSorted;
    private VisualizerFrame frame;

    public QuickSort(Integer[] toBeSorted, VisualizerFrame frame) {
        this.toBeSorted = toBeSorted;
        this.frame = frame;
    }

    public void run() {
        sort(0, toBeSorted.length - 1);
        SortingVisualizer.isSorting = false;
    }

    private void sort(int low, int high) {
        if (low < high) {
            // Partition the array and visualize the partitioning
            int pi = partition(low, high);

            // Recursively sort the subarrays
            sort(low, pi - 1);
            sort(pi + 1, high);
        }
    }

    private int partition(int low, int high) {
        int pivot = toBeSorted[high];  // Pivot element
        int i = (low - 1);  // Index of the smaller element

        // Color the pivot element
        frame.reDrawArray(toBeSorted, high, -1, high);  // Pivot highlighted in yellow
        sleep();

        for (int j = low; j < high; j++) {
            // Highlight the elements being compared
            frame.reDrawArray(toBeSorted, i + 1, j, high);  // Comparing i and j with the pivot
            sleep();

            if (toBeSorted[j] < pivot) {
                i++;
                swap(i, j);

                // After swapping, redraw to show the swap
                frame.reDrawArray(toBeSorted, i, j, high);  // Highlight swapped elements in green
                sleep();
            }
        }

        // Swap the pivot element to its correct position
        swap(i + 1, high);
        frame.reDrawArray(toBeSorted, i + 1, high);  // Pivot placed in correct position
        sleep();

        return i + 1;
    }

    private void swap(int i, int j) {
        int temp = toBeSorted[i];
        toBeSorted[i] = toBeSorted[j];
        toBeSorted[j] = temp;
    }

    // Utility method to slow down the process for visualization
    private void sleep() {
        try {
            Thread.sleep(SortingVisualizer.sleep);  // Adjustable sleep time for better visualization
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
