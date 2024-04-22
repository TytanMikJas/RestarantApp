import { Injectable } from '@nestjs/common';
import MenuRepository from './menu.repository';
import { MenuDto } from './dto/menu.dto';

Injectable();
export default class MenuService {
  constructor(private readonly menuRepository: MenuRepository) {
    this.menuRepository = menuRepository;
  }

  async getAll(): Promise<MenuDto[]> {
    return await this.menuRepository.getAll();
  }
}
