package io.github.arrayv.sorts.templates;
  
import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.insert.BinaryInsertionSort;

public abstract class FlammerSorting extends Sort {
    private BinaryInsertionSort binaryInserter;
    protected FlammerSorting(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);
    };
    private void flammerMerge(int[] array, int[] tmp, int start, int mid, int end, double delay) {
        if (start==end) return;
        if (end - start < 32) {
            return;
        }
        else if (end - start < 64) {
            binaryInserter.customBinaryInsert(array, start, end, 0.333);
        }
        else {
            flammerMerge(array, tmp, start, (mid+start)/2, mid, delay);
            flammerMerge(array, tmp, mid, (mid+end)/2, end, delay);

            int low = start;
            int high = end;
            int compA = start;
            int compB = end;
            int finderA = start;
            int finderB = end;
            int newMid = mid;
			int w = 0;
			int st = start;
            boolean done = false;
            while(!done) {
                boolean compAFound = false;
                boolean compBFound = false;
                compA = start;
                compB = end;
                finderA = start;
                finderB = end - 1;
                newMid = mid;
                done = false;
                while (true) {
                    if (!compAFound) {
                        Highlights.markArray(1, finderA);
                        Highlights.markArray(2, finderA + 1);
                        if (Reads.compareValues(array[finderA], array[finderA + 1]) == 1) {
                            Highlights.clearMark(1);
                            Highlights.clearMark(2);
                            compAFound = true;
                            compA = finderA;
                            newMid = finderA + 1;
                        };
                        finderA++;
                        Delays.sleep(delay);
						if (finderA > (end - 1)) done = true;
						if (compAFound) break;
                    };
                    if (finderA > (end - 1)) {
                        done = true;
                        break;
                    }
				}
                if (done) break;
				while (true) {
                    if ((!compBFound) && compAFound) {
                        Highlights.markArray(3, compA);
                        Highlights.markArray(4, finderB);
                        if (Reads.compareValues(array[compA], array[finderB]) == 1) {
                            Highlights.clearMark(3);
                            Highlights.clearMark(4);
                            compBFound = true;
                            compB = finderB;
                        };
                        finderB--;
                        Delays.sleep(delay);
						if (compBFound) break;
                    };
                };
				if (compB < end - 1) {
					Writes.write(tmp, compB + 1, array[compB + 1], delay, true, true);
				}
				else {
					compB = end - 1;
				};
                int j = compB;
                int h = end - 1;
				boolean seth = false;
                for(int isc = start; isc < newMid; isc++) {
                    Highlights.markArray(1, isc);
                    Highlights.markArray(2, compB);
                    if (Reads.compareValues(array[isc], array[compB]) == 1) {
                        Writes.write(tmp, j, array[isc], delay, true, true);
                        j--;
                        if (isc < h) {
                            h = isc;
							seth = true;
                            Highlights.markArray(3, h);
                        }
                    };
					Delays.sleep(0.4);
                };
				Writes.reversal(tmp, j + 1, compB, 0.1, true, true);
                for(int k = compB; k > compA; k--) {
                    Highlights.markArray(1, k);
                    Writes.write(tmp, j, array[k], delay, true, true);
                    j--;
                };
				w = j + 1;
                for(int l = h; l < compB + 1; l++) {
					if (l < end) {
                    Writes.write(array, l, tmp[w], delay, true, false);
					w++;
					};
                };
            };
        };
    };
  protected void flammerSort(int[] array, int length, double delay) {
        binaryInserter = new BinaryInsertionSort(this.arrayVisualizer);

        if(length < 32) {
            binaryInserter.customBinaryInsert(array, 0, length, 0.333);
            return;
        };

        int[] tmp = Writes.createExternalArray(length);

        int start = 0;
        int end = length;
        int mid = start + ((end - start) / 2);

        flammerMerge(array, tmp, start, mid, end, delay);

        Writes.deleteExternalArray(tmp);
    };
};
