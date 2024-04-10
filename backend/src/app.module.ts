import { Module } from '@nestjs/common';
import OrderModule from './modules/order/order.module';
import MenuModule from './modules/menu/menu.module';
import { join } from 'path';
import { ServeStaticModule } from '@nestjs/serve-static';

@Module({
  imports: [
    OrderModule,
    ServeStaticModule.forRoot({
      rootPath: join(__dirname, '../../'),
      renderPath: '/public',
    }),
    MenuModule,
  ],

  providers: [],
})
export class AppModule {}
