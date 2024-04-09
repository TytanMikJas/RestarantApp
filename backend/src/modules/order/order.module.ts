import { forwardRef, Module } from '@nestjs/common';
import OrderGateway from './order.gateway';
import OrderService from './order.service';
import OrderRepository from './order.repository';
import MenuModule from '../menu/menu.module';

@Module({
  providers: [OrderGateway, OrderService, OrderRepository],
  imports: [forwardRef(() => MenuModule)],
})
export default class OrderModule {}
