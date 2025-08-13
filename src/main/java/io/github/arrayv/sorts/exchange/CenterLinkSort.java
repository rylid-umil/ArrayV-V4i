package io.github.arrayv.sorts.exchange;

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
 *
 */

public final class CenterLinkSort extends Sort {
    public CenterLinkSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("Center Link");
        this.setRunAllSortsName("Link Sort, Center Alignment");
        this.setRunSortName("Center Linksort");
        this.setCategory("Exchange Sorts");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(false);
        this.setUnreasonableLimit(0);
        this.setBogoSort(false);
    }

    public int linkSort(int[] array, int start, int end) {
        if(start == end) return 0;
        int mid = (start + end)/2+1;
        int s = 0;
        s += linkSort(array, start, mid - 1);
        s += linkSort(array, mid, end);
        if (Reads.compareValues(array[mid - 1], array[mid]) == 1) {
            Writes.swap(array, mid - 1, mid, 0.5, true, false);
            s++;
        }
        Highlights.markArray(1, mid - 1);
        Highlights.markArray(2, mid);
        Delays.sleep(0.25);
        return s;
    }
    @Override
    public void runSort(int[] array, int length, int bucketCount) {
        while(linkSort(array, 0, length - 1) > 0) {}
    }
}
