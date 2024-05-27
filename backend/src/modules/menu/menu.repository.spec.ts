//write integration test for repository (with db connection)
import { Test, TestingModule } from '@nestjs/testing';

import MenuRepository from './menu.repository';
import { PrismaClient } from '@prisma/client';

describe('menuRepository', () => {
    let menuRepository: MenuRepository;

    beforeEach(async () => {
        const module: TestingModule = await Test.createTestingModule({
        providers: [
            MenuRepository,
            {
                provide: PrismaClient,
                useValue: new PrismaClient(),
            }
        ],
        }).compile();

        menuRepository = module.get<MenuRepository>(MenuRepository);
    });

    it('should be defined', () => {
        expect(menuRepository).toBeDefined();
    });

    it('should return an array of menus', async () => {
        const menu = await menuRepository.getAll();
        expect(menu).toBeInstanceOf(Array);
        expect(menu).toBeTruthy();
    });
})