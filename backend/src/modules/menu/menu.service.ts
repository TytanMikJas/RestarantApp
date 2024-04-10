import { Injectable } from '@nestjs/common';
import MenuRepository from './menu.repository';

Injectable();
export default class MenuService {
  constructor(private readonly menuRepository: MenuRepository) {
    this.menuRepository = menuRepository;
  }

  async getAll() {
    return await this.menuRepository.getAll();
  }
}
