import { OrderItem } from '@prisma/client';

export default class CreateOrderDto {
  items: CreateOrderItemDto[];
}


export class CreateOrderItemDto {
  id: number;
  quantity: number;
}