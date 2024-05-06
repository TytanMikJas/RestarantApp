package com.example.androidapp.api.dto

import android.util.Log
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.engineio.client.transports.WebSocket
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
                    Log.d("IOService","Connected")
                }
                on(Socket.EVENT_DISCONNECT) {
                    Log.d("IOService","Disconnected")
                }
                on(Socket.EVENT_CONNECT_ERROR) {
                    Log.d("IOService","Connection error")
                }
                on(ON_CONNECTED_CLIENT) {
                    Log.d("IOService Connected client","$it")
                }
                on(ON_CONNECTED_WAITER) {
                    Log.d("IOService Connected waiter","$it")
                }
                on(ON_CREATE_ORDER_CLIENT) {
                    Log.d("IOService Create order client","$it")
                }
                on(ON_CREATE_ORDER_WAITER) {
                    Log.d("IOService Create order waiter","$it")
                }
                on(ON_NEXT_ORDER_STATUS_CLIENT) {
                    Log.d("IOService Next order status client","$it")
                }
                on(ON_NEXT_ORDER_STATUS_WAITER) {
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
        socket.emit(CREATE_ORDER, order)
    }
}