package com.example.androidapp.viewmodels.customer

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidapp.api.dto.CreateOrderDto
import com.example.androidapp.api.dto.CreateOrderItemDto
import com.example.androidapp.api.dto.IOService
import com.example.androidapp.api.dto.MenuDto
import com.example.androidapp.api.dto.OrderDto
import com.example.androidapp.api.dto.OrderItemDto
import com.example.androidapp.api.dto.RetrofitInstance
import com.example.androidapp.api.dto.Status
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CustomerViewModel: ViewModel() {
    var menu: MutableState<List<MenuDto>> = mutableStateOf(emptyList())
    private set
    var cart: MutableState<List<OrderItemDto>> = mutableStateOf(emptyList())
    private set
    var order: MutableState<OrderDto?> = mutableStateOf(null)
    private set

    var selectedTabIndex by mutableIntStateOf(0)


    var tableId: MutableState<Int?> = mutableStateOf(null)
    private set

    var ioClient: IOService? = null

    fun connectToSocketAsClient() {
        ioClient = IOService(tableId.value.toString())
        ioClient?.apply {
            onNextOrderStatusClient = { it ->
                if(it.status == Status.ARCHIVED) {
                    Unit
                }else {
                    order.value = it
                }
            }

            onCreateOrderClient = { it ->
                cart.value = emptyList()
                order.value = it
            }
        }
        ioClient?.connect()
    }

    fun addToCart(menuItem: MenuDto, quantity: Int = 1) {
        val newCart = cart.value.toMutableList()
        val existingItem = newCart.find { it.menuItem.id == menuItem.id }
        if(existingItem != null) {
            newCart.remove(existingItem)
            newCart.add(OrderItemDto(null, menuItem, existingItem.quantity + quantity))
        } else{
            newCart.add(OrderItemDto(null,menuItem, quantity))
        }
        cart.value = newCart

    }

    fun removeFromCart(orderItem: OrderItemDto) {
        val newCart = cart.value.toMutableList()
        newCart.remove(orderItem)
        cart.value = newCart
    }

    fun createOrder(tableId: Int) {
        if(ioClient == null) {
            return;
        }
        val createOrderDto = CreateOrderDto(
            items = cart.value.map {
                CreateOrderItemDto(it.menuItem.id.toInt(), it.quantity)
            }
        )
        ioClient?.createOrder(createOrderDto)
        Log.d("CustomerViewModel", "Creating order")
    }

    fun createOrder(){
        if(tableId.value == null){
            return
        }
        createOrder(tableId.value!!)
    }

    fun clearBasket() {
        cart.value = emptyList()
    }

    fun clearOrder() {
        order.value = null
    }

    init {
        val apiService = RetrofitInstance.api
        viewModelScope.launch {
            var response = apiService.getMenu()
            response = response.map { it ->
                it.copy(
                    images = it.images.map {
                        "http://srv21.mikr.us:30437/public/${it}"
                    },
                    video = if(it.video.isNullOrEmpty()) null else "http://srv21.mikr.us:30437/public/${it.video}"
                )
            }
            menu.value = response
        }
    }
}