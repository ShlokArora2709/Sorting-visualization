package project;

import java.util.ArrayList;
import java.util.Collections;

import project.Sorts.*;

public class SortingVisualizer {

    private static Thread sortingThread;

    public static VisualizerFrame frame;
    public static Integer[] toBeSorted;
    public static boolean isSorting = false;
    public static int sortDataCount = 100;
    public static int sleep = 20;
    public static int blockWidth;
    // Stepped depicts whether the values are incremental or random. True is incremental.
    public static boolean stepped = false;

    public static void main(String[] args) {
        frame = new VisualizerFrame();
        resetArray();
        frame.setLocationRelativeTo(null);
    }

    public static void resetArray() {
        if (isSorting) return;
        toBeSorted = new Integer[sortDataCount];
        blockWidth = (int) Math.max(Math.floor(500/sortDataCount), 1);
        for(int i = 0; i < toBeSorted.length; i++) {
            if (stepped) {
                toBeSorted[i] = i;
            } else {
                toBeSorted[i] = (int) (sortDataCount * Math.random());
            }
        }
        if (stepped) {
            ArrayList<Integer> shuffleThis = new ArrayList<>();
            for (int i = 0; i < toBeSorted.length; i++) {
                shuffleThis.add(toBeSorted[i]);
            }
            Collections.shuffle(shuffleThis);
            toBeSorted = shuffleThis.toArray(toBeSorted);
        }
        frame.preDrawArray(toBeSorted);
    }

    public static void startSort(String type) {
        if (sortingThread == null || !isSorting) {
            resetArray();
            isSorting = true;

            switch(type) {
                case "Bubble":
                    sortingThread = new Thread(new BubbleSort(toBeSorted, frame));
                    break;
                case "Selection":
                    sortingThread = new Thread(new SelectionSort(toBeSorted, frame));
                    break;
                case "Insertion":
                    sortingThread = new Thread(new InsertionSort(toBeSorted, frame));
                    break;
                case "Heap":
                    sortingThread = new Thread(new HeapSort(toBeSorted, frame));
                    break;
                case "Merge":
                    sortingThread = new Thread(new MergeSort(toBeSorted, frame));
                    break;
                case "Quick":
                    sortingThread = new Thread(new QuickSort(toBeSorted, frame));
                    break;
                default:
                    isSorting = false;
                    return;
            }

            sortingThread.start();
        }
    }
}