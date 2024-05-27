import { Module } from '@nestjs/common';
import MenuService from './menu.service';
import MenuRepository from './menu.repository';
import MenuController from './menu.controller';
import { PrismaModule } from '../prisma/prisma.module';

@Module({
  providers: [MenuService, MenuRepository],
  controllers: [MenuController],
  exports: [MenuService],
  imports: [PrismaModule],
})
export default class MenuModule {}
