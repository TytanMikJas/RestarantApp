package com.example.androidapp.viewmodels.waiter

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.androidapp.api.dto.IOService
import com.example.androidapp.api.dto.OrderDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WaiterViewModel: ViewModel() {
    var ioClient: IOService? = null
    var orders: MutableState<List<OrderDto>> = mutableStateOf(emptyList())
    private set

    fun connectToSocketAsWaiter() {
        ioClient = IOService(null)
        ioClient?.apply {
            onNextOrderStatusWaiter = { newOrder ->
                val newOrders = orders.value.toMutableList()
                val index = newOrders.indexOfFirst { newOrder.id == it.id }
                if(index != -1) {
                    newOrders[index] = newOrder
                } else {
                    newOrders.add(newOrder)
                }
                orders.value = newOrders
            }
            onConnectedWaiter = {
                orders.value = it
            }

            onCreateOrderWaiter = { newOrder ->
                val newOrders = orders.value.toMutableList()
                newOrders.add(newOrder)
                orders.value = newOrders
            }
        }
        ioClient?.connect()
    }

    fun nextOrderStatus(orderId: Int) {
        ioClient?.nextOrderStatus(orderId)
    }
}