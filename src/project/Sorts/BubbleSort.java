package project.Sorts;
import project.*;
public class BubbleSort implements Runnable {

    private Integer[] toBeSorted;
    private VisualizerFrame frame;

    public BubbleSort(Integer[] toBeSorted, VisualizerFrame frame) {
        this.toBeSorted = toBeSorted;
        this.frame = frame;
    }

    public void run() {
        sort();
        SortingVisualizer.isSorting = false;
    }

    public void sort() {
        int temp;
        boolean swapped;
        for (int i = 0; i < toBeSorted.length - 1; i++) {
            swapped = false;
            for (int j = 1; j < toBeSorted.length - i; j++) {
                // Highlight the current pair being compared (red for comparison)
                frame.reDrawArray(toBeSorted, j - 1, j);
                sleep();  // Slow down to visualize the comparison

                if (toBeSorted[j - 1] > toBeSorted[j]) {
                    // Swap elements and highlight the swap (green for swapping elements)
                    temp = toBeSorted[j - 1];
                    toBeSorted[j - 1] = toBeSorted[j];
                    toBeSorted[j] = temp;
                    swapped = true;

                    // Re-draw after the swap to visualize the change
                    frame.reDrawArray(toBeSorted, j - 1, j, j);
                    sleep();
                }
            }

            // After each pass, redraw the array to show progress
            frame.reDrawArray(toBeSorted, toBeSorted.length - i - 1);  // Mark sorted part with green

            if (!swapped) {
                break;  // Optimization: stop if no swaps were made in a pass
            }
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
