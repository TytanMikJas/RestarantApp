import { Status } from '@prisma/client';
import OrderItemDto from './order-item.dto';

export default interface OrderDto {
  id: number;
  tableId: number;
  status: Status;
  createdAt: Date;
  items: OrderItemDto[];
}
