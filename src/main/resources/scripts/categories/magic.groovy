import io.github.arrayv.prompts.SortPrompt
import io.github.arrayv.utils.Shuffles

SortPrompt.setSortThreadForCategory('Magic Sorts', 7) {
    run StraighteningSort go 1024.numbers, 0.75.speed
    run DrillStraighteningSort go 1024.numbers, 0.75.speed
    run MagicBubbleSort go 256.numbers, 0.5.speed
    run MagicBaiaiSort go 256.numbers, 0.5.speed
    run MagicInsertionSort go 256.numbers, 0.5.speed
    run MagicSelectionSort go 256.numbers, 1.speed
    run OptimizedMagicSelectionSort go 256.numbers, 0.5.speed
}
