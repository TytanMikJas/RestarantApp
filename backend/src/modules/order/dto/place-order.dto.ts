import { OrderItem } from '@prisma/client';

export default interface CreateOrderDto {
  items: CreateOrderItemDto[];
}


export interface CreateOrderItemDto {
  id: number;
  quantity: number;
}