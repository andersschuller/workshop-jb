package v_collections

fun example8() {
    val numbers = listOf(1, 3, -4, 2, -11)

    //the details (how multi-assignment works) were explained in 'Conventions' task earlier
    val (positive, negative) = numbers.partition { it > 0 }

    positive == listOf(1, 3, 2)
    negative == listOf(-4, -11)
}

fun Shop.getCustomersWithMoreUndeliveredOrdersThanDelivered(): Set<Customer> {
    // Return customers who have more undelivered orders than delivered
    return customers.map { Pair(it, it.orders.partition { it.isDelivered }) }
        .filter { it.second.first.size() < it.second.second.size() }
        .map { it.first }
        .toSet()
}
