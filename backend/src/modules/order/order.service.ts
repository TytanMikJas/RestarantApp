import { Injectable } from '@nestjs/common';
import CreateOrderDto from './dto/place-order.dto';
import OrderDto from './dto/order.dto';
import OrderRepository from './order.repository';
import { Status } from '@prisma/client';

@Injectable()
export default class OrderService {
  constructor(private readonly orderRepository: OrderRepository) {}

  async nextOrderStatus(orderId: number): Promise<OrderDto> {
    console.log(orderId);
    const order = await this.orderRepository.getOrderById(orderId);
    console.log(order, "after orderID");
    const nextOrderStatus = this.nextStatus(order.status);
    return await this.orderRepository.updateOrder(order, nextOrderStatus);
  }

  async createOrder(data: CreateOrderDto, userId: any): Promise<OrderDto> {
    return await this.orderRepository.createOrder(data, userId);
  }

  async getLatestOrder(userId: any): Promise<OrderDto> {
    return await this.orderRepository.getLatestOrder(userId);
  }

  async getOrders(): Promise<OrderDto[]> {
    return await this.orderRepository.getOrders();
  }

  nextStatus(currentStatus: string): Status {
    switch (currentStatus) {
      case Status.PLACED:
        return Status.IN_PROGRESS;
      case Status.IN_PROGRESS:
        return Status.DELIVERED;
      case Status.DELIVERED:
        return Status.ARCHIVED;
      default:
        throw new Error(`Invalid status: ${currentStatus}`);
    }
  }
}
