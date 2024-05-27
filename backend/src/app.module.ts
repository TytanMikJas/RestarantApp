import { Module } from '@nestjs/common';
import OrderModule from './modules/order/order.module';
import MenuModule from './modules/menu/menu.module';
import { join } from 'path';
import { ServeStaticModule } from '@nestjs/serve-static';
import { PrismaClient } from '@prisma/client';
import { PrismaModule } from './modules/prisma/prisma.module';

@Module({
  imports: [
    OrderModule,
    ServeStaticModule.forRoot({
      rootPath: join(__dirname, '../../'),
      renderPath: '/public',
    }),
    MenuModule,
    PrismaModule,
  ],
  providers: [
    {
      provide: PrismaClient,
      useValue: new PrismaClient(),
    },
  ],
})
export class AppModule {}
