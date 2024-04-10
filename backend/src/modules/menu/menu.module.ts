import { Module } from '@nestjs/common';
import MenuService from './menu.service';
import MenuRepository from './menu.repository';
import MenuController from './menu.controller';

@Module({
  providers: [MenuService, MenuRepository],
  controllers: [MenuController],
  exports: [MenuService],
})
export default class MenuModule {}
