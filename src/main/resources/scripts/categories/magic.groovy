import io.github.arrayv.prompts.SortPrompt
import io.github.arrayv.utils.Shuffles

SortPrompt.setSortThreadForCategory('Magic Sorts', 7) {
    run StraighteningSort go 1024.numbers
    run DrillStraighteningSort go 1024.numbers
    run MagicBubbleSort go 256.numbers
    run MagicBaiaiSort go 256.numbers
    run MagicInsertionSort go 256.numbers
    run MagicSelectionSort go 256.numbers, 0.33.speed
    run OptimizedMagicSelectionSort go 256.numbers
}
