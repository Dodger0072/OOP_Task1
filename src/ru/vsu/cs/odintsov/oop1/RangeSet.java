package ru.vsu.cs.odintsov.oop1;

import java.util.ArrayList;
import java.util.List;
class Range<T> {
    private int start;
    private int end;

    public Range(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public boolean overlaps(Range other) {
        return this.start <= other.end && other.start <= this.end;
    }

    public void merge(Range other) {
        this.start = Math.min(this.start, other.start);
        this.end = Math.max(this.end, other.end);

    }
}
public class RangeSet<T extends Comparable<T>> {
    private List<Range> ranges;

    public RangeSet() {
        this.ranges = new ArrayList<>();
    }

    public List<Range> getRanges() {
        return ranges;
    }

    public void setRanges(List<Range> ranges) {
        this.ranges = ranges;
    }

    public void addRange(int start, int end) {
        Range newRange = new Range(start, end);
        List<Range> mergedRanges = new ArrayList<>();
        boolean added = false;
        for (Range existingRange : ranges)
            if (newRange.overlaps(existingRange)) newRange.merge(existingRange);
            else {
                if (!added && newRange.getEnd() < existingRange.getStart()) {
                    mergedRanges.add(newRange);
                    added = true;
                }
                mergedRanges.add(existingRange);
            }
        if (!added) {
            mergedRanges.add(newRange);
        }

        ranges = mergedRanges;
    }

}
