package io.github.arrayv.sorts.hybrid;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;
import io.github.arrayv.sorts.templates.MergeSorting;
import io.github.arrayv.sorts.templates.IterativeCircleSorting;
import io.github.arrayv.sorts.exchange.CircleSortIterative;
import io.github.arrayv.sorts.insert.BinaryInsertionSort;

// A sort optimized to adapt to every situation. O(1) space complexity and stability not included. WIP

public final class AdaptiveSort extends IterativeCircleSorting {
    private MergeSorting merger;
    private CircleSortIterative iterativeCircleSortertwo;
	private IterativeCircleSorting iterativeCircleSorter;
    private BinaryInsertionSort binaryInserter;
    private int sublistCount;
    
    public AdaptiveSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("Adaptive7");
        this.setRunAllSortsName("Adaptive Sort");
        this.setRunSortName("Adaptivesort");
        this.setCategory("Project Adaptive Sort");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(false);
        this.setUnreasonableLimit(0);
        this.setBogoSort(false);
    }; //me if the void starts to go public so i have to try and catch the food for the private super final party that extends 
    //  tragedies that import and package hot dogs so i have to switch to a different case and try their visualizing unicorn syrup while i do so so i can assert
    //    my dominance over them else if they dont die i have to run else i must return home to code protected volatile sort:
    // Compares two items, swapping them if they are out of order. Accounts for situations in which left > right.
    public void compareSwap(int[] array, int left, int right, double compSleep, double swapSleep, boolean mark, boolean auxwrite) {
        if (left > right) {
            int tmp = left;
            left = right;
            right = tmp;
        };
        if (mark) {
            Highlights.markArray(1, left);
            Highlights.markArray(2, right);
        }
        Delays.sleep(compSleep);
        if (Reads.compareValues(array[left], array[right]) == 1) {
            Writes.swap(array, left, right, swapSleep, mark, auxwrite);
        };
    }
    //Find all sublists, marking their starts and ends in the provided Auxillary array if writeAux is true.
    public int findSublists(int[] array, boolean writeAux, int[] aux, int start, int end, int checkLimit) {
        int count = 0;
        int subSiz = 0;
		boolean limitExceeded = false;
		boolean status = false; // new implementation to prevent an out of bounds error, idk though if array is 7 long and i try to do array[7] (which is outtabounds)
		// will i be thrown an exception or will it just report "null" / 0? if another programmer is seeing this, pls help
		
        if (writeAux) {
			Writes.write(aux, start, 2, 0.1, true, true);
		};
        for(int i = start; i < end; ++i) {
            Highlights.markArray(1, i);
            if (i < end) Highlights.markArray(2, i + 1);
            Delays.sleep(0.5);
			if (i < end) {
				status = Reads.compareValues(array[i], array[i + 1]) != 1;
			}
			else {
				status = false;
			};
            if(status) {
                subSiz++;
            }
            else {
                count++;
                subSiz = 0;
                if (writeAux) {
					Delays.sleep(0.5);
                    if (Reads.compareValues(array[i], 2) == 0) {
						Writes.write(aux, i, 3, 0.1, true, true);
					}
					else {
						Writes.write(aux, i, 2, 0.1, true, true);
					};
                    if (i < end) {
						Writes.write(aux, i + 1, 1, 1, true, true);
					};
                };
            };
            if (count > checkLimit) {
				limitExceeded = true;
				break;
			}
        };
		if (!limitExceeded) {
			return count;
		}
		else return -1;
    };
	// Find and flip reversed segments whose lengths are greater than 2.
	public void findReversedSublists(int[] array, int start, int end) {
		int fl = start;
		int length = 1;
		boolean status = false;
		for (int i = end - 1; i > start; --i) {
			Highlights.markArray(1, i);
			if (i > start - 1) Highlights.markArray(2, i - 1);
			Delays.sleep(0.5);
			if (i < end) {
				status = Reads.compareValues(array [i], array[i - 1]) != 1;
			}
			else {
				status = false;
			};
			if (status) {
				length++;
			}
			else{
				fl = i;
				if(length > 2) {
					Writes.reversal(array, fl, length - 1, 1, true, false);
				};
				length = 1;
			};
		}
	};
	// Totally didn't steal this from MergeSorting.java and removed recursion :) *angry MergeSorting.java noises in the back ground* uhh ingnore that :|
	public void merge(int[] array, int[] tmp, int start, int mid, int end, boolean binary) {
        if(start == mid) return;

        if(end - start < 64 && binary) {
			binaryInserter = new BinaryInsertionSort(this.arrayVisualizer);
            binaryInserter.customBinaryInsert(array, start, end, 0.333);
        }
        else {
            int low = start;
            int high = mid;

            for(int nxt = 0; nxt < end - start; nxt++){
                if(low >= mid && high >= end) break;

                Highlights.markArray(1, low);
                Highlights.markArray(2, high);

                if(low < mid && high >= end){
                    Highlights.clearMark(2);
                    Writes.write(tmp, nxt, array[low++], 1, false, true);
                }
                else if(low >= mid && high < end){
                    Highlights.clearMark(1);
                    Writes.write(tmp, nxt, array[high++], 1, false, true);
                }
                else if(Reads.compareValues(array[low], array[high]) <= 0){
                    Writes.write(tmp, nxt, array[low++], 1, false, true);
                }
                else{
                    Writes.write(tmp, nxt, array[high++], 1, false, true);
                }
            }
            Highlights.clearMark(2);

            for(int i = 0; i < end - start; i++){
                Writes.write(array, start + i, tmp[i], 1, true, false);
            }
        }
    }
    // Do 1 round of merging sublists, whose starts and ends are marked in the provided Auxillary array.
    public void mergeRound(int[] array, int[] aux, int[] tmp, int start, int end, boolean binary) {
        int left = 0;
        int right = 0;
        int mid = 0;
        int state = 0;
        Highlights.clearMark(2);
        for(int i = start; i < end; i++) {
            Highlights.markArray(1, i);
            Delays.sleep(1);
                // If you find a start point (1) and are in state 0, set left to I and go to state 1, if in state 2, do the same thing but instead set mid to I. 
                // If not in state 0 or 2, do nothing.
            if(Reads.compareValues(aux[i], 1) == 0 || Reads.compareValues(aux[i], 3) == 0) {
                switch(state) {
                    case 0:
                        left = i;
                        state = 1;
                        break;
                    case 2:
                        mid = i;
                        state = 3;
                        break;
                    default:
                };
            } 
            Delays.sleep(1);
            if(Reads.compareValues(array[i], 2) == 0 || Reads.compareValues(array[i], 3) == 0) {
                switch(state) {
                    case 1:
                        state = 2;
                        break;
                    case 3:
                        right = i;
                        state = 4;
                        break;
					default:
                }
            };
            if(state == 4) {
                Highlights.markArray(1, mid - 1);
                Highlights.markArray(2, mid);
                Delays.sleep(5);
                // If the two sublists consecutive are already sorted, there is no need to merge them
                if(Reads.compareValues(array[mid - 1], array[mid]) == 1) {
					sublistCount--; // Merging two sublists removes two and adds one, leading to a net loss of 1 sublist as you combine two.
                    Writes.write(aux, mid, 1, 4, true, true);
                    Writes.write(aux, mid - 1, 1, 4, true, true);
                    this.merge(array, tmp, left, mid, right, binary); // TODO: use a partial merge instead of a full merge, see PartialMergeSort.java. Make/Be sure to port
					// the partial merge function to here, as it is probably a private function. Found out when trying to compile the version with regular merge (fixed)
                };
                state = 0;
                left = i + 1;
                mid = 0;
                right = 0;
            };
        };
    };
    // PDQ's insertion with a customizable limit.
    private boolean partialInsertSort(int[] array, int begin, int end, int maxWriteLimit) {
        if (begin == end) return true;

        double sleep = 1/5d;

        int limit = 0;
        for (int cur = begin + 1; cur != end; ++cur) {
            if (limit > maxWriteLimit) return false;

            int sift = cur;
            int siftMinusOne = cur - 1;

            // Compare first so we can avoid 2 moves for an element already positioned correctly.
            if (Reads.compareValues(array[sift], array[siftMinusOne]) < 0) {
                int tmp = array[sift];

                do {
                    Writes.write(array, sift--, array[siftMinusOne], sleep, true, false);
                } while (sift != begin && Reads.compareValues(tmp, array[--siftMinusOne]) < 0);

                Writes.write(array, sift, tmp, sleep, true, false);
                limit += cur - sift;
            };
        };
        return true;
    };
    
    // Check if there are more than N/1.5 sublists. If so, the array is probably atleast mostly reversed, so do a circle pass, just in case it isn't mostly reversed.
    // If it's done exactly or more than N*Log(n) swaps in the circle pass, the list was probably reversed, or aslteast mostly reversed and is probably now at least mostly sorted,
    // so do an "optimistic insertion sort". If it does more than n/3 swaps, stop and continue sorting like normal.
    public boolean checkForManySublists(int[] array, int start, int end){
		int n = end - start;
        int maxSubs = (int) Math.floor(n / 1.5);
        int sublists = this.findSublists(array, false, array, start, end, maxSubs);
        boolean good = false;
        if (sublists == -1){
            int length = end - start;
            int swaps = this.circleSortRoutine(array, length, 0.1);
            int nlogn = (int) Math.floor(MathExtra.nlogn(length));
            if (swaps >= nlogn) {
                int maxWrites = (int) Math.floor(length/3);
                good = this.partialInsertSort(array, start, end, maxWrites);
                return good;
            };
        };
        return false;
    };
    // Do the sort in a range
    public void customAdaptiveSort(int[] array, int[] aux, int[] tmp, int start, int end, double limitBase) {
        if (end == start) return;
        if (end - start == 1) {
            this.compareSwap(array, start, end, 0.5, 1, true, false);
        };
        if (end - start < 32) {
			binaryInserter = new BinaryInsertionSort(this.arrayVisualizer);
            binaryInserter.customBinaryInsert(array, start, end, 0.05);
			return;
        };
		this.findReversedSublists(array, start, end);
		boolean done = false;
        int n = end - start;
        int maxSublists = (int) Math.floor((n / 1.5) + MathExtra.logBase(n, limitBase));
        sublistCount = this.findSublists(array, false, aux, start, end, maxSublists); //TODO:find segments that are reversed and reverse them so that they are full runs.
        if (sublistCount == -1) {
            done = this.checkForManySublists(array, start, end);
            if (done) return;
            sublistCount = this.findSublists(array, true, aux, start, end, n * 2); // N * 2 so that it never activates the limit.
			while (sublistCount > 1) {
                this.mergeRound(array, aux, tmp, start, end, false);
            };
        }
        else if (sublistCount > 1) {
            sublistCount = this.findSublists(array, true, aux, start, end, maxSublists);
            while (sublistCount > 1) {
                this.mergeRound(array, aux, tmp, start, end, false);
            };
        };
        if (done) return;
    };
    public void runSort(int[] array, int length, int bucketCount) {
        sublistCount = 0;
        int[] sublistMarkers = Writes.createExternalArray(length);
		int[] tmp = Writes.createExternalArray(length);
        this.customAdaptiveSort(array, sublistMarkers, tmp, 0, length - 1, 2);
    };
};
