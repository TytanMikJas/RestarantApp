import { Injectable } from '@nestjs/common';
import {
  ConnectedSocket,
  MessageBody,
  OnGatewayConnection,
  OnGatewayDisconnect,
  OnGatewayInit,
  SubscribeMessage,
  WebSocketGateway,
  WebSocketServer,
} from '@nestjs/websockets';
import { Server, Socket } from 'socket.io';
import OrderService from './order.service';
import CreateOrderDto from './dto/place-order.dto';
import NextOrderStatusDto from './dto/oder-status-changed.dto';

@WebSocketGateway({ transports: ['websocket'], cors: true })
@Injectable()
export default class OrderGateway
  implements OnGatewayConnection, OnGatewayDisconnect, OnGatewayInit
{
  constructor(private readonly orderService: OrderService) {}
  @WebSocketServer() server: Server;
  private readonly WAITER_ROOM = 'WAITER_ROOM';

  async afterInit(): Promise<void> {
    console.log('init');
  }

  async handleConnection(client: any, ...args: any[]) {
    const userId = client.handshake.auth.userId;
    console.log(userId);
    if (!userId) {
      client.join(this.WAITER_ROOM);
      const orders = await this.orderService.getOrders();
      this.server.to(this.WAITER_ROOM).emit('connectedWaiter', orders);
      console.log(`emit connectedWaiter`, orders)
    } else {
      client.join(userId);
      const order = await this.orderService.getLatestOrder(Number(userId));
      if (order) {
        this.server.to(userId).emit('onNextOrderStatusClient', order);
        console.log(`emit onNextOrderStatusClient to ${userId}`, order )
      } else {
        this.server.to(userId).emit('connectedClient', 'connected');
      }
    }
  }

  async handleDisconnect(client: any) {
    console.log(client);
  }

  @SubscribeMessage('createOrder')
  async createOrder(
    @ConnectedSocket() client: Socket,
    @MessageBody() data: CreateOrderDto,
  ) {
    console.log("data" + data)
    const userId = client.handshake.auth.userId;
    const order = await this.orderService.createOrder(data, Number(userId));
    this.server.to(userId).emit('onCreateOrderClient', order);
    this.server.to(this.WAITER_ROOM).emit('onCreateOrderWaiter', order);
  }

  @SubscribeMessage('nextOrderStatus')
  async nextOrderStatus(@MessageBody() data: NextOrderStatusDto) {
    console.log(data);
    const order = await this.orderService.nextOrderStatus(data.orderId);
    this.server
      .to(order.tableId.toString())
      .emit('onNextOrderStatusClient', order);
    this.server.to(this.WAITER_ROOM).emit('onNextOrderStatusWaiter', order);
  }
}
