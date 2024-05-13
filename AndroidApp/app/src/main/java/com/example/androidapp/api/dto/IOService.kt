package com.example.androidapp.api.dto

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.engineio.client.transports.WebSocket
import org.json.JSONArray
import org.json.JSONObject
import java.net.URISyntaxException

class IOService(userId: String?) {
    private lateinit var socket: Socket
    //OUTGOING
    private val CREATE_ORDER = "createOrder"
    private val NEXT_ORDER_STATUS = "nextOrderStatus"
    //INCOMING
    private val ON_CONNECTED_WAITER = "connectedWaiter"
    private val ON_CONNECTED_CLIENT = "connectedClient"
    private val ON_NEXT_ORDER_STATUS_CLIENT = "onNextOrderStatusClient"
    private val ON_CREATE_ORDER_CLIENT = "onCreateOrderClient"
    private val ON_CREATE_ORDER_WAITER = "onCreateOrderWaiter"
    private val ON_NEXT_ORDER_STATUS_WAITER = "onNextOrderStatusWaiter"

    //handlers
    var onCreateOrderClient: (order:OrderDto) -> Unit = {}
    var onConnectedWaiter: (orderList:List<OrderDto>) -> Unit = {}
    var onNextOrderStatusClient: (order:OrderDto) -> Unit = {}
    var onCreateOrderWaiter: (order:OrderDto) -> Unit = {}
    var onNextOrderStatusWaiter: (order:OrderDto) -> Unit = {}
    var onConnectedClient: () -> Unit = {}
    var onConnect: () -> Unit = {}
    var onDisconnect: () -> Unit = {}
    init {
        try {
            val _auth = if(userId != null){
                mapOf("userId" to userId)
            } else {
                mapOf("userId" to null)
            }
            socket = IO.socket("http://srv21.mikr.us:30437", IO.Options().apply {
                reconnection = true
                reconnectionDelay = 1000
                reconnectionDelayMax = 5000
                reconnectionAttempts = 99999
                transports = arrayOf(WebSocket.NAME)
                auth = _auth

            }).apply {
                on(Socket.EVENT_CONNECT) {
                    onConnect()
                    Log.d("IOService","Connected")
                }
                on(Socket.EVENT_DISCONNECT) {
                    onDisconnect()
                    Log.d("IOService","Disconnected")
                }
                on(Socket.EVENT_CONNECT_ERROR) {
                    Log.d("IOService","Connection error")
                }
                on(ON_CONNECTED_CLIENT) {
                    Log.d("IOService Connected client","$it")
                    onConnectedClient()
                }
                on(ON_CONNECTED_WAITER) {
                    val json = it[0] as JSONArray
                    val orderListType = object : TypeToken<List<OrderDto>>() {}.type
                    val orderList = Gson().fromJson(json.toString(), orderListType) as List<OrderDto>
                    onConnectedWaiter(orderList)
                    Log.d("IOService Connected waiter","$orderList")
                }
                on(ON_CREATE_ORDER_CLIENT) {it ->
                    val json = it[0] as JSONObject
                    val order = Gson().fromJson(json.toString(), OrderDto::class.java)
                    onCreateOrderClient(order)
                    Log.d("IOService Create order client","$order")
                }
                on(ON_CREATE_ORDER_WAITER) {
                    val json = it[0] as JSONObject
                    val order = Gson().fromJson(json.toString(), OrderDto::class.java)
                    onCreateOrderWaiter(order)
                    Log.d("IOService Create order waiter","$it")
                }
                on(ON_NEXT_ORDER_STATUS_CLIENT) {
                    val json = it[0] as JSONObject
                    val order = Gson().fromJson(json.toString(), OrderDto::class.java)
                    onNextOrderStatusClient(order)
                    Log.d("IOService Next order status client","$order")
                }
                on(ON_NEXT_ORDER_STATUS_WAITER) {
                    val json = it[0] as JSONObject
                    val order = Gson().fromJson(json.toString(), OrderDto::class.java)
                    onNextOrderStatusWaiter(order)
                    Log.d("IOService Next order status waiter","$it")
                }

            }
        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }
    }

    fun connect() {
        socket.connect()
    }

    fun disconnect() {
        socket.disconnect()
    }

    fun createOrder(order: CreateOrderDto) {
        val json = Gson().toJson(order)
        val jsonObject: JSONObject = JSONObject(json)
        socket.emit(CREATE_ORDER, jsonObject)
    }

    fun createOrder() {
        socket.emit(CREATE_ORDER, CreateOrderDto(listOf(
            CreateOrderItemDto(1, 1),
            CreateOrderItemDto(2, 2),
            CreateOrderItemDto(3, 3)
        )))
    }

    fun nextOrderStatus(orderId: Int) {
        val json = Gson().toJson(NextOrderStatusDto(orderId))
        val jsonObject: JSONObject = JSONObject(json)
        socket.emit(NEXT_ORDER_STATUS, jsonObject)
    }
}