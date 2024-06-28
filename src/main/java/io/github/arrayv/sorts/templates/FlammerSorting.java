package io.github.arrayv.sorts.templates
  
import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.insert.BinaryInsertionSort;

public abstract class FlammerSorting extends Sort {
    private BinaryInsertionSort binaryInserter;
    protected FlammerSorting(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);
    };
    private void flammerMerge(int[] array, int[] tmp, int start, int mid, int end) {
        if (start==end) {
            return;
        }
        else if (end - start < 64) {
            binaryInserter.customBinaryInsert(array, start, end, 0.333);
        }
        else {
            flammerMerge(array, tmp, start, (mid+start)/2, mid);
            flammerMerge(array, tmp, mid, (mid+end)/2, end);

            int low = start;
            int high = end;
            int compA = start;
            int compB = end;
            int finderA = start;
            int finderB = end;
            int newMid = mid;
            int done = false;
            while(!done) {
                boolean compAFound = false;
                boolean compBFound = false;
                compA = start;
                compB = end;
                finderA = start;
                finderB = end;
                newMid = mid;
                done = false;
                while (!compAFound || !compBFound) {
                    if (!compAFound) {
                        Highlights.markArray(1, finderA);
                        Highlights.markArray(2, finderA + 1);
                        if (Reads.compareValues(array[finderA], array[finderA + 1]) == 1) {
                            Highlights.clearMark(1);
                            Highlights.clearMarh(2);
                            compAFound = true;
                            compA = finderA;
                            newMid = finderA + 1;
                        };
                        finderA++;
                        Delays.sleep(0.1);
                    };
                    if (finderA > end) {
                        done = true;
                        break;
                    }
                    if (!compBFound && compAFound) {
                        Highlights.markArray(3, compA);
                        Highlights.markArray(4, finderB);
                        if (Reads.compareValues(array[compA], array[finderB) {
                            Highlights.clearMark(3);
                            Highlights.clearMark(4);
                            compBFound = true;
                            compB = finderB;
                        };
                        finderB++'
                    };
                };
                if (done) break;
                Writes.write(tmp, compB + 1, array[compB + 1], 0.1, true, true);
                int j = compB;
                int h = -1;
                for(int i = start; i < newMid - 1; i++) {
                    Highlights.markArray(1, i);
                    Highlights.markArray(2, compB);
                    if (Reads.compareValues(array[i], array[compB]) == 1) {
                        Writes.write(tmp, j, array[i], 0.1, true, true);
                        j--;
                        if (h == -1); {
                            h = i;
                            Highlights.markArray(3, h);
                        }
                    };
                };
                for(int k = compB; k > compA; k--) {
                    Highlights.markArray(1, k);
                    Writes.write(tmp, j, array[k], 0.1, true, true);
                    j--;
                };
                for(int l = h; l < compB; l++) {
                    Writes.write(array, l, tmp[l], 0.1, true, false);
                };
            };
        };
    };
};
