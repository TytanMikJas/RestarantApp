import { Injectable, NotImplementedException } from '@nestjs/common';
import OrderDto from './dto/order.dto';
import { PrismaClient } from '@prisma/client';

@Injectable()
export default class OrderRepository {
  private prisma: PrismaClient;
  constructor() {
    this.prisma = new PrismaClient();
  }
  async getOrderById(orderId: number): Promise<OrderDto> {
    throw new NotImplementedException('XD');
  }
}
