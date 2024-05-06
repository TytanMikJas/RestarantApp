import { Injectable } from '@nestjs/common';
import OrderDto from './dto/order.dto';
import { PrismaClient, Status } from '@prisma/client';
import CreateOrderDto from './dto/place-order.dto';

@Injectable()
export default class OrderRepository {
  private prisma: PrismaClient;

  constructor() {
    this.prisma = new PrismaClient();
  }

  async getOrderById(orderId: number): Promise<OrderDto> {
    return await this.prisma.order.findUnique({
      where: { id: orderId },
      include: {
        items: { include: { menuItem: { include: { attachments: true } } } },
      },
    });
  }

  async updateOrder(
    order: OrderDto,
    nextOrderStatus: Status,
  ): Promise<OrderDto> {
    return await this.prisma.order.update({
      where: { id: order.id },
      data: { status: nextOrderStatus },
      include: {
        items: { include: { menuItem: { include: { attachments: true } } } },
      },
    });
  }

  async createOrder(data: CreateOrderDto, userId: any): Promise<OrderDto> {
    return await this.prisma.order.create({
      data: {
        tableId: userId,
        status: Status.PLACED,
        items: {
          create: data.items.map((item) => ({
            quantity: item.quantity,
            menuItem: { connect: { id: item.id } },
          })),
        },
      },
      include: {
        items: { include: { menuItem: { include: { attachments: true } } } },
      },
    });
  }

  async getLatestOrder(userId: any): Promise<OrderDto> {
    return this.prisma.order.findFirst({
      where: { tableId: userId },
      orderBy: { createdAt: 'desc' },
      include: {
        items: { include: { menuItem: { include: { attachments: true } } } },
      },
    });
  }

  async getOrders(): Promise<OrderDto[]> {
    return this.prisma.order.findMany({
      include: {
        items: { include: { menuItem: { include: { attachments: true } } } },
      },
    });
  }
}
