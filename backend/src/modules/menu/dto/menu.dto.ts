import { $Enums } from '@prisma/client';

export interface MenuDto {
  id: number;
  name: string;
  description: string;
  alergens: string[];
  price: number;
  category: $Enums.Category;
  video: string;
  images: string[];
}
