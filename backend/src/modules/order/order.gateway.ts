import { Injectable } from '@nestjs/common';
import {
  OnGatewayConnection,
  OnGatewayDisconnect,
  OnGatewayInit,
  WebSocketGateway,
} from '@nestjs/websockets';
import OrderService from './order.service';

@WebSocketGateway({ transports: ['websocket'], cors: true })
@Injectable()
export default class OrderGateway
  implements OnGatewayConnection, OnGatewayDisconnect, OnGatewayInit
{
  constructor(private readonly oderdService: OrderService) {}

  async afterInit(): Promise<void> {
    console.log('init');
  }

  async handleConnection(client: any) {
    console.log(client);
  }

  async handleDisconnect(client: any) {
    console.log(client);
  }
}
