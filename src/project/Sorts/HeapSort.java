package project.Sorts;

import project.*;

public class HeapSort implements Runnable {
    
    private Integer[] toBeSorted;
    private VisualizerFrame frame;
    
    public HeapSort(Integer[] toBeSorted, VisualizerFrame frame) {
        this.toBeSorted = toBeSorted;
        this.frame = frame;
    }
    
    public void run() {
        sort();
        SortingVisualizer.isSorting = false;
    }
    
    public void sort() {
        int n = toBeSorted.length;

        // Build heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(n, i);
        }

        // One by one extract elements from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            swap(0, i);
            frame.reDrawArray(toBeSorted, i); // Highlight the sorted part
            sleep();

            // Call heapify on the reduced heap
            heapify(i, 0);
        }
    }
    
    private void heapify(int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // Check if left child exists and is greater than root
        if (left < n) {
            frame.reDrawArray(toBeSorted, i, left); // Highlight comparison
            if (toBeSorted[left] > toBeSorted[largest]) {
                largest = left;
            }
        }

        // Check if right child exists and is greater than largest so far
        if (right < n) {
            frame.reDrawArray(toBeSorted, largest, right); // Highlight comparison
            if (toBeSorted[right] > toBeSorted[largest]) {
                largest = right;
            }
        }

        // If largest is not root
        if (largest != i) {
            swap(i, largest);
            frame.reDrawArray(toBeSorted, largest, i); // Highlight swap
            sleep();

            // Recursively heapify the affected subtree
            heapify(n, largest);
        }
    }
    
    private void swap(int i, int j) {
        int temp = toBeSorted[i];
        toBeSorted[i] = toBeSorted[j];
        toBeSorted[j] = temp;
    }
    
    private void sleep() {
        try {
            Thread.sleep(SortingVisualizer.sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
