//write integration test for service (with db connection)
import { Test, TestingModule } from '@nestjs/testing';
import MenuService from './menu.service';
import MenuRepository from './menu.repository';
import { PrismaClient } from '@prisma/client';

describe('menuService', () => { 
    let menuService: MenuService;
    let menuRepository: MenuRepository;
    
    beforeEach(async () => {
        const module: TestingModule = await Test.createTestingModule({
        providers: [
            {
                provide: PrismaClient,
                useValue: new PrismaClient(),
            },
            MenuService,
            MenuRepository,
        ],
        }).compile();
    
        menuService = module.get<MenuService>(MenuService);
        menuRepository = module.get<MenuRepository>(MenuRepository);
    });
    
    it('should be defined', () => {
        expect(menuService).toBeDefined();
    });
    
    it('should return an array of menus', async () => {
        const menu = await menuService.getAll();
        expect(menu).toBeInstanceOf(Array);
        expect(menu).toBeTruthy();
    });
 })