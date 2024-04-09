import { Module } from '@nestjs/common';
import OrderModule from './modules/order/order.module';
import MenuModule from './modules/menu/menu.module';

@Module({
  imports: [OrderModule, MenuModule],
  providers: [],
})
export class AppModule {}
