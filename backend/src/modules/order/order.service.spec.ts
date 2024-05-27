//write integration tests for OrderService
import { Test, TestingModule } from '@nestjs/testing';
import OrderService from './order.service';
import OrderRepository from './order.repository';
import CreateOrderDto from './dto/place-order.dto';
import OrderDto from './dto/order.dto';
import { PrismaClient } from '@prisma/client';
import { table } from 'console';
import { after } from 'node:test';

describe('OrderService', () => {
    let orderService: OrderService;
    let orderRepository: OrderRepository;
    let prisma: PrismaClient;
    const startId = 35000;
    const testOrders = [
        {
            id: startId,
            status: 'PLACED',
            tableId: 1,
        },
        {
            id: startId + 1,
            status: 'ARCHIVED',
            tableId: 1,
        },
        {
            id: startId + 2,
            status: 'IN_PROGRESS',
            tableId: 2,
        },
        {
            id: startId + 3,
            status: 'DELIVERED',
            tableId: 3,
        },
        {
            id: startId + 4,
            status: 'ARCHIVED',
            tableId: 4,
        }
    ] as const;

    beforeAll(async () => {
        prisma = new PrismaClient();
        const module: TestingModule = await Test.createTestingModule({
            providers: [
                OrderService,
                OrderRepository,
                {
                    provide: PrismaClient,
                    useValue: prisma,
                },
            ],
        }).compile();
        orderService = module.get<OrderService>(OrderService);
        orderRepository = module.get<OrderRepository>(OrderRepository);
    });

    beforeEach(async () => {
        await prisma.order.deleteMany();
        await prisma.order.createMany({
            data: [
                ...testOrders,
            ],
        });
    });

    afterAll(async () => {
        await prisma.order.deleteMany();  
    })

    it('should be defined', () => {
        expect(orderService).toBeDefined();
    });

    it('should return an array of orders', async () => {
        const order = await orderService.getOrders();
        expect(order).toMatchObject(testOrders);
        expect(order).toBeTruthy();
    });

    it('should return latest order', async () => {
        const order = await orderService.getLatestOrder(1);
        expect(order).toBeInstanceOf(Object);
        expect(order).toBeTruthy();
        expect(order).toMatchObject(testOrders[0]);
    });

    it('should give next status to non archived order', async () => {
        const allOrders = await prisma.order.findMany();
        const order1 = await orderService.nextOrderStatus(startId);
        const order2 = await orderService.nextOrderStatus(startId+ 2);
        const order3 = await orderService.nextOrderStatus(startId + 3);
        expect(order1).toMatchObject({
            status: 'IN_PROGRESS',
        });
        expect(order2).toMatchObject({
            status: 'DELIVERED',
        });
        expect(order3).toMatchObject({
            status: 'ARCHIVED',
        });
    });

    it('should throw error for next status of archived order', async () => {
        try {
            await orderService.nextOrderStatus(startId + 1);
        } catch (error) {
            expect(error).toBeInstanceOf(Error);
            expect(error.message).toBe('Invalid status: ARCHIVED');
        }
    });

    it('should create order', async () => {
        const data: CreateOrderDto = {
            items: [
                {
                    id: 1,
                    quantity: 2,
                },
                {
                    id: 2,
                    quantity: 1,
                },
            ],
        } as CreateOrderDto;
        const order = await orderService.createOrder(data, 1);
        expect(order).toBeInstanceOf(Object);
        expect(order).toMatchObject({
            status: 'PLACED',
            tableId: 1,
        });
    });
});