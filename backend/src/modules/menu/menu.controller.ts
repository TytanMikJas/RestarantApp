import { Controller, Get } from '@nestjs/common';
import MenuService from './menu.service';

@Controller('menu')
export default class MenuController {
  constructor(private readonly menuService: MenuService) {}

  @Get()
  async getAll() {
    return await this.menuService.getAll();
  }
}
