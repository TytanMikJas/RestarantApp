import { Controller, Get } from '@nestjs/common';
import MenuService from './menu.service';
import { MenuDto } from './dto/menu.dto';

@Controller('menu')
export default class MenuController {
  constructor(private readonly menuService: MenuService) {}

  @Get()
  async getAll(): Promise<MenuDto[]> {
    return await this.menuService.getAll();
  }
}
