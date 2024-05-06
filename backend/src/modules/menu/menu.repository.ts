import { Injectable } from '@nestjs/common';
import { FileType, PrismaClient } from '@prisma/client';
import { MenuDto } from './dto/menu.dto';

@Injectable()
export default class MenuRepository {
  private prisma: PrismaClient;
  constructor() {
    this.prisma = new PrismaClient();
  }

  async getAll(): Promise<MenuDto[]> {
    const menuItems = await this.prisma.menuItem.findMany({
      include: { attachments: true },
    });

    

    return menuItems.map((item, index) => ({
      id: item.id,
      name: item.name,
      description: item.description,
      alergens: item.alergens,
      price: item.price,
      category: item.category,
      video: item.attachments.find(
        (attachment) => attachment.fileType === FileType.VIDEO,
      )?.fileName,
      images: item.attachments
        .filter((attachment) => attachment.fileType === FileType.IMAGE)
        .map((attachment) => attachment.fileName),
    }));
  }
}
