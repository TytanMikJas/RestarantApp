import { Module } from '@nestjs/common';
import MenuService from './menu.service';
import MenuGateway from './menu.gateway';
import MenuRepository from './menu.repository';

@Module({
  providers: [MenuGateway, MenuService, MenuRepository],
  exports: [MenuService],
})
export default class MenuModule {}
