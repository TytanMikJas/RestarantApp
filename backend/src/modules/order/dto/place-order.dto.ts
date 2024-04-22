import { OrderItem } from '@prisma/client';

export default interface CreateOrderDto {
  items: OrderItem[];
}
