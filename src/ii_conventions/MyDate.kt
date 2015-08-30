package ii_conventions

import com.google.common.collect.ComparisonChain

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return ComparisonChain.start()
            .compare(year, other.year)
            .compare(month, other.month)
            .compare(dayOfMonth, other.dayOfMonth)
            .result()
    }

    fun plus(interval: TimeInterval): MyDate = addTimeIntervals(interval, 1)

    fun plus(interval: MultiTimeInterval): MyDate = addTimeIntervals(interval.interval, interval.number)
}

fun MyDate.rangeTo(end: MyDate): DateRange = DateRange(this, end)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR;

    fun times(number: Int) = MultiTimeInterval(this, number)
}

data class MultiTimeInterval(val interval: TimeInterval, val number: Int)

class DateRange(public override val start: MyDate, public override val end: MyDate) : Iterable<MyDate>, Range<MyDate> {
    override fun iterator(): Iterator<MyDate> {
        var current = start
        return object : Iterator<MyDate> {
            override fun next(): MyDate {
                val result = current
                current = current.nextDay()
                return result
            }

            override fun hasNext(): Boolean {
                return current <= end
            }

        }
    }

    override fun contains(item: MyDate): Boolean {
        return item >= start && item <= end
    }
}
