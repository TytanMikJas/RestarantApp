import { Category } from '@prisma/client';
import AttachmentDto from './attachment.dto';

export default interface MenuItemDto {
  id: number;
  name: string;
  description: string;
  alergens: string[];
  price: number;
  category: Category;
}
