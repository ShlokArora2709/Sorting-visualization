package project.Sorts;

import project.*;

public class MergeSort implements Runnable {

    private Integer[] toBeSorted;
    private VisualizerFrame frame;

    public MergeSort(Integer[] toBeSorted, VisualizerFrame frame) {
        this.toBeSorted = toBeSorted;
        this.frame = frame;
    }

    public void run() {
        sort(toBeSorted, 0, toBeSorted.length - 1);
        SortingVisualizer.isSorting = false;
    }

    private void sort(Integer[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            // Sort first and second halves
            sort(array, left, mid);
            sort(array, mid + 1, right);

            merge(array, left, mid, right);

            // Redraw the array after merging the two halves
            frame.reDrawArray(toBeSorted);
            sleep();
        }
    }

    private void merge(Integer[] array, int left, int mid, int right) {
        // Find sizes of two subarrays to be merged
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Create temp arrays
        Integer[] L = new Integer[n1];
        Integer[] R = new Integer[n2];

        // Copy data to temp arrays L[] and R[]
        for (int i = 0; i < n1; i++) {
            L[i] = array[left + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = array[mid + 1 + j];
        }

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray
        int k = left;

        // Merge the temp arrays back into array[left..right]
        while (i < n1 && j < n2) {
            // Color the elements being compared
            frame.reDrawArray(toBeSorted, left + i, mid + 1 + j);
            sleep();  // Slow down for visualization

            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;

            // Redraw after each placement
            frame.reDrawArray(toBeSorted);
            sleep();
        }

        // Copy remaining elements of L[] if any
        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;

            // Visualize placement of remaining elements
            frame.reDrawArray(toBeSorted);
            sleep();
        }

        // Copy remaining elements of R[] if any
        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;

            // Visualize placement of remaining elements
            frame.reDrawArray(toBeSorted);
            sleep();
        }
    }

    // Utility method to pause for visualization
    private void sleep() {
        try {
            Thread.sleep(SortingVisualizer.sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
