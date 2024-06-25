import io.github.arrayv.prompts.SortPrompt
import io.github.arrayv.utils.Shuffles

SortPrompt.setSortThreadForCategory('Insertion Sorts', 14) {
    run InsertionSort go 1024.numbers, 1.speed
    run DoubleInsertionSort go 1024.numbers, 1.speed
    run BinaryInsertionSort go 1024.numbers, 8.speed
    run BinaryDoubleInsertionSort go 1024.numbers, 8.speed
    run ShellSort go 2048.numbers, 1.speed
    run RecursiveShellSort go 256.numbers, 0.1.speed
    run ShellSortParallel go 256.numbers, 0.1.speed
    run SimplifiedLibrarySort go 2048.numbers
    run LibrarySort go 2048.numbers
    run PatienceSort go 2048.numbers
    run ClassicTreeSort go 2048.numbers, ((arrayv.arrayManager.containsShuffle(Shuffles.RANDOM) ? 1 : 5).speed)
    run TreeSort go 2048.numbers, ((arrayv.arrayManager.containsShuffle(Shuffles.RANDOM) ? 1 : 5).speed)
    run AATreeSort go 2048.numbers
    run AVLTreeSort go 2048.numbers
    run SplaySort go 2048.numbers
}
