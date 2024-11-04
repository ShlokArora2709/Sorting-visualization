package project.Sorts;

import project.*;

public class SelectionSort implements Runnable {

    private Integer[] toBeSorted;
    private VisualizerFrame frame;

    public SelectionSort(Integer[] toBeSorted, VisualizerFrame frame) {
        this.toBeSorted = toBeSorted;
        this.frame = frame;
    }

    public void run() {
        sort();
        SortingVisualizer.isSorting = false;
    }

    public void sort() {
        int temp;
        int selected;
        for (int i = 0; i < toBeSorted.length; i++) {
            selected = i;

            for (int j = i + 1; j < toBeSorted.length; j++) {
                frame.reDrawArray(toBeSorted, selected, j); // Highlight current comparison
                if (toBeSorted[j] < toBeSorted[selected]) {
                    selected = j;
                }
                sleep(); // Optional: sleep for better visibility
            }

            // Swap the found minimum element with the first element
            if (selected != i) {
                temp = toBeSorted[i];
                toBeSorted[i] = toBeSorted[selected];
                toBeSorted[selected] = temp;

                frame.reDrawArray(toBeSorted, i, selected); // Highlight the swap
                sleep();
            }

            // Final redraw to show the sorted part of the array
            frame.reDrawArray(toBeSorted);
        }
    }

    private void sleep() {
        try {
            Thread.sleep(SortingVisualizer.sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
