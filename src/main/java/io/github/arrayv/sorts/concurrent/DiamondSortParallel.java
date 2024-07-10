package io.github.arrayv.sorts.concurrent;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sortdata.SortMeta;
import io.github.arrayv.sorts.templates.Sort;

public final class DiamondSortParallel extends Sort {
    private final double DELAY = 0.05;
    public DiamondSortParallel(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);
        
        this.setSortListName("Diamond (Parallel)");
        this.setRunAllSortsName("Parallel Diamond Sort"); // next up: emerald sort
        this.setRunSortName("Parallel Diamond Sort");
        this.setCategory("Concurrent Sorts");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(false);
        this.setUnreasonableLimit(0);
        this.setBogoSort(false);
    }
    
    private int[] array;

    private class DiamondSort extends Thread {
        private int a, b;
        DiamondSort(int a, int b,) {
            this.a = a;
            this.b = b;
        }
        public void run() {
            DiamondSortParrarel.this.diamondSort(a, b);
        }
    }
    private void combine(int a, int b) {
        int len = b-a;
        if (b-a == 2) {
            if (Reads.compareValues(array[a], array[b]) == 1) Writes.swap(array, a, b, this.DELAY, true, false);
        }
        double d = (b - a) / 4d;
        int m = (b - a) / 2 + a;
        this.combine( (int) d + a, (int) (d * 3) + a);
        this.diamondSort(a, b, true);
        this.combine( (int) d + a, (int) (d * 3) + a);
    }
    private void diamondSort(int a, int b, boolean recurseless) {
        if (b-a < 2) break;
        double d = (b - a) / 4d;
        int m = (b - a) / 2 + a;
        DiamondSort diamA = new DiamondSort(a, m);
        DiamondSort diamB = new DiamondSort(m, b);

        diamA.start();
        diamB.start();

        try {
            diamA.join();
            diamB.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt()
        }

        if (!recurseless) this.combine(a, b);
    }
    @Override
    public void runSort(int[] arr, int length, int buckets) {
        this.array = array;
        this.diamondSort(0, length, false);
    }
}
