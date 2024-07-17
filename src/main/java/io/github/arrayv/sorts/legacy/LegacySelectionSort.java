package io.github.arrayv.sorts.legacy;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;

/*
 *
MIT License

Copyright (c) 2019 w0rthy

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

Selection sort but altered to sound like what it does in the originl worthy videos (the one that use that echo sound effect, Worthy's original sound effect (from the jar i think)
 *
 */

public final class SelectionSort extends Sort {
    public SelectionSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("Legacy Selection");
        this.setRunAllSortsName("Legacy Selection Sort");
        this.setRunSortName("Legacy Selection Sort");
        this.setCategory("Legacy");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(false);
        this.setUnreasonableLimit(0);
        this.setBogoSort(false);
    }

    @Override
    public void runSort(int[] array, int length, int bucketCount) {
        for (int i = 0; i < length - 1; i++) {
            int lowestindex = i;

            for (int j = i + 1; j < length; j++) {
                Delays.sleep(0.01);

                if (Reads.compareValues(array[j], array[lowestindex]) == -1){
                    lowestindex = j;
                    Highlights.markArray(1, lowestindex);
                    Delays.sleep(0.01);
                }
            }
            Writes.swap(array, i, lowestindex, 0.02, true, false);
        }
    }
}
